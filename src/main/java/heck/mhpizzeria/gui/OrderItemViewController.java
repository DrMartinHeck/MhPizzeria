package heck.mhpizzeria.gui;

import heck.mhpizzeria.gui.tableViewOrderItems.TableViewNameCellFactory;
import heck.mhpizzeria.gui.tableViewOrderItems.TableViewPriceCellFactory;
import heck.mhpizzeria.logic.BillHolder;
import heck.mhpizzeria.logic.OrderItemHolder;
import heck.mhpizzeria.model.Bill;
import heck.mhpizzeria.model.Customer;
import heck.mhpizzeria.model.OrderItem;
import heck.mhpizzeria.settings.AppCommands;
import heck.mhpizzeria.settings.AppTexts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * <h2>Controller für orderItemViewLayout.fxml</h2>
 * Der View zeigt alle möglichen Pizzen {@link heck.mhpizzeria.model.Pizza} aus der Datenbank an zusammen mit der Anzahl
 * der gewünschten Pizzen. Durch Doppelklick oder das Drücken der 'Enter'-Taste kann die Anzahl der gewünschten Pizzen
 * um eine Einheit erhöht werden. Jede Änderung führt zu einer Neuberechnung des Positionspreises und des Gesamtpreises.
 * Alternativ können Buttons für die Änderung der Anzahl der gewünschten Pizzen benutzt werden. Dazu muss eine
 * Pizza ausgewählt worden sein, sonst gibt es eine Fehlermeldung. Ist eine Pizza selektiert
 * <ul>
 *     <li>wechselt der 'Anzahl ändern'-Button zur Detailansicht für die betreffende Position.</li>
 * </ul>
 * Die anderen Buttons bewirken:
 * <ul>
 *     <li>'Alles auf Null' setzt die Zahl bei allen gewünschten Pizzen auf Null.</li>
 *     <li>'zurück' wechselt zurück in die Kundenübersicht ohne die Positionen zu verändern.</li>
 *     <li>'Speichern' speichert die Rechnung in der Datenbank und wechselt zurück in die Masteransicht.</li>
 * </ul>
 */
public class OrderItemViewController implements Initializable {
    //region attributes
    @FXML
    TableView<OrderItem> tableViewOfOrderItem;
    @FXML
    TableColumn<OrderItem, String> columnOfName;
    @FXML
    TableColumn<OrderItem, String> columnOfPrice;
    @FXML
    TableColumn<OrderItem, Integer> columnOfCount;
    @FXML
    TableColumn<OrderItem, String> columnOfPositionPrice;
    @FXML
    private Label lblCustomer;
    @FXML
    private Label lblFinalPrice;
    @FXML
    private Label lblError;

    private Customer customer;
    //endregion

    //region setters&getters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        lblCustomer.setText(customer.getName() + "\n" + customer.getTableNumber());
    }
    //endregion

    //region methods&functions
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<OrderItem> orderItems = OrderItemHolder.getInstance().getOrderItems();
        calculateFinalPrice();

        tableViewOfOrderItem.setItems(OrderItemHolder.getInstance().getOrderItems());
        columnOfCount.setCellValueFactory(new PropertyValueFactory(AppCommands.ORDERITEM_TABLE_ATTRIBUTE_COUNT));
        columnOfName.setCellValueFactory(new TableViewNameCellFactory());
        columnOfPrice.setCellValueFactory(new TableViewPriceCellFactory());
        columnOfPositionPrice.setCellValueFactory(new PropertyValueFactory(AppCommands.ORDERITEM_TABLE_ATTRIBUTE_POSITION_PRICE));

        tableViewOfOrderItem.getColumns().setAll(columnOfName, columnOfPrice, columnOfCount, columnOfPositionPrice);

        tableViewOfOrderItem.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2
                    && mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                OrderItem selectedOrderItem = tableViewOfOrderItem.getSelectionModel().getSelectedItem();
                selectedOrderItem.increaseCount();
                lblError.setText("");
                calculateFinalPrice();
            }
        });

        tableViewOfOrderItem.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                OrderItem selectedOrderItem = tableViewOfOrderItem.getSelectionModel().getSelectedItem();
                selectedOrderItem.increaseCount();
                lblError.setText("");
                calculateFinalPrice();
            }
        });
    }

    @FXML
    private void goToCustomerView(){
        SceneManager.getInstance().stageCustomerScene();
    }

    @FXML
    private void setCountsZero(){
        OrderItemHolder.getInstance().setCountsZero();
        lblFinalPrice.setText("00.00");
    }

    @FXML
    private void calculateFinalPrice(){
        lblFinalPrice.setText(String.format(AppTexts.ORDERITEM_FINAL_PRICE_FORMAT, OrderItemHolder.getInstance().getTotalPrice()));
    }

    @FXML
    private void saveBill(){
        if (OrderItemHolder.getInstance().getNonZeroOrderItems().size() == 0){
            lblError.setText(AppTexts.BILL_NEEDS_POSITIONS_ERROR);
        } else {
            Bill bill = new Bill();
            bill.setCustomer(customer);
            //Reminder: The following makes a deep copy, therefore counts can be set to zero without destroying the bill
            bill.setOrderItems(OrderItemHolder.getInstance().getNonZeroOrderItems());
            bill.setSum(OrderItemHolder.getInstance().getTotalPrice());
            BillHolder.getInstance().saveBill(bill);
            setCountsZero();
            SceneManager.getInstance().stageMasterScene();
        }
    }

    @FXML
    private void goToDetailedView(){
        OrderItem orderItem = tableViewOfOrderItem.getSelectionModel().getSelectedItem();
        if (orderItem != null) {
            SceneManager.getInstance().stageOrderItemDetailedScene(orderItem, customer);
        } else {
            lblError.setText(AppTexts.ORDERITEM_NO_POSITION_SELECTED_ERROR);
        }
    }
    //endregion
}
