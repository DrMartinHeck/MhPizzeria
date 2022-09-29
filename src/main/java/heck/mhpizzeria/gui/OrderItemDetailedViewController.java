package heck.mhpizzeria.gui;


import heck.mhpizzeria.model.Customer;
import heck.mhpizzeria.model.OrderItem;
import heck.mhpizzeria.settings.AppCommands;
import heck.mhpizzeria.settings.AppTexts;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * <h2>Controller für orderItemDetailedViewLayout.fxml</h2>
 * Zeigt die Informationen der ausgewählten Position, also die {@link heck.mhpizzeria.model.Pizza}-Infos (unveränderbar)
 * und die Anzahl der gewünschten Pizzen dieser Art (im Textfeld) an. Null oder eine natürliche Zahl werden bei Drücken
 * des 'Speichern'-Buttons übernommen, andere Werte führen zu einer Fehlermeldung.
 * Löschen setzt die Zahl auf Null. Der 'zurück'-Button führt zurück zur Übersicht
 * der Positionen ohne eine Änderung vorzunehmen.
 */
public class OrderItemDetailedViewController {
    //region attributes
    @FXML
    private Label lblName;
    @FXML
    private Label lblPrice;
    @FXML
    private TextField txtCount;
    @FXML
    private Label lblError;

    private OrderItem orderItem;
    private Customer customer;
    //endregion

    //region setter & getter
    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;

        if (orderItem != null) {
            lblName.setText(orderItem.getPizza().getName());
            lblPrice.setText(String.valueOf(orderItem.getPizza().getPrice()));
        } else {
            System.out.println(AppTexts.ORDERITEM_DETAILED_NO_PIZZA_SELECTED);
        }

        this.txtCount.setText(String.valueOf(orderItem.getCount()));
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    //endregion

    //region functions & methods
    @FXML
    private void goToOrderItemView(){
        SceneManager.getInstance().stageOrderItemScene(customer);
    }

    @FXML
    private void saveOrderItem(){
        int count = -1;
        try {
            count = Integer.valueOf(txtCount.getText());

            if (count > -1){
                orderItem.setCount(count);
                goToOrderItemView();
            }
            else {
                lblError.setText(AppTexts.ORDERITEM_DETAILED_NONNEGATIVE_NUMBER_REQUESTED);
            }
        }
        catch (NumberFormatException e) {
            lblError.setText(AppTexts.ORDERITEM_DETAILED_NUMBER_REQUESTED);
        }
    }

    @FXML
    private void deleteOrderItem(){
        txtCount.setText(AppCommands.ORDERITEM_DETAILED_COUNT_ZERO);
        saveOrderItem();
    }
    //endregion
}
