package heck.mhpizzeria.gui;

import heck.mhpizzeria.logic.CustomerHolder;
import heck.mhpizzeria.logic.OrderItemHolder;
import heck.mhpizzeria.model.Customer;
import heck.mhpizzeria.settings.AppCommands;
import heck.mhpizzeria.settings.AppTexts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h2>Controller für customerViewLayout.fxml</h2>
 *  Die View besteht hauptsächlich aus einer Tabelle mit Kunden. Doppelklick oder das Drücken der 'Enter'-Taste
 *  auf einem Eintrag wechselt zur Ansicht der möglichen Positionen/Pizzen. Ansonsten kann mit 4 Buttons interagiert werden:
 *  <ul>
 *      <li>'Erstellen' Wechselt in dei Detailansicht für Kunden, um einen neuen Kunden zu erstellen.</li>
 *      <li>'Bearbeiten' führt zu einer Fehlermeldung, falls kein Kunde ausgewählt ist. Falls ein Kunde
 *      markiert ist, werden dessen Informationen in der Detailansicht für Kunden geladen und angezeigt.</li>
 *      <li>'Abbrechen' bricht den Prozess der Rechnungserstellung komplett ab und wechselt zurück in die Master-Ansicht.</li>
 *      <li>'Weiter' zeigt eine Fehlermeldung, falls kein Kunde in der Tabelle blau markiert ist
 *      (z.B. durch Einzelklick oder mit den Pfeiltasten). Ist ein Kunde markiert ist, gibt es wie bei Doppelklick
 *      den Wechsel zur Positions/pizza-Ansicht.</li>
 *  </ul>
 */
public class CustomerViewController implements Initializable {
    //region attributes
    @FXML
    private TableView<Customer> tableViewOfCustomer;
    @FXML
    private TableColumn<Customer, String> tableColumnOfName;
    @FXML
    private TableColumn<Customer, String> tableColumnOfTableNumber;
    @FXML
    private Label lblError;
    //endregion

    //region methods&functions
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewOfCustomer.setItems(CustomerHolder.getInstance().getCustomers());

        tableColumnOfName.setCellValueFactory(new PropertyValueFactory(AppCommands.CUSTOMER_TABLE_ATTRIBUTE_NAME));
        tableColumnOfTableNumber.setCellValueFactory(new PropertyValueFactory(AppCommands.CUSTOMER_TABLE_ATTRIBUTE_NUMBER));

        tableViewOfCustomer.getColumns().setAll(tableColumnOfName, tableColumnOfTableNumber);

        tableViewOfCustomer.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2
                    && mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                goToOrderItems();
            }
        });

        tableViewOfCustomer.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                goToOrderItems();
            }
        });
    }

    @FXML
    private void goToNewCustomer() {
        SceneManager.getInstance().stageCustomerDetailedScene(null);
    }

    @FXML
    private void goToEditCustomer() {
        Customer customer = tableViewOfCustomer.getSelectionModel().getSelectedItem();
        if (customer != null) {
            SceneManager.getInstance().stageCustomerDetailedScene(customer);
        }
        lblError.setText(AppTexts.CUSTOMER_NOONE_SELECTED);
    }

    @FXML
    private void goToMaster(){
        OrderItemHolder.getInstance().setCountsZero();
        SceneManager.getInstance().stageMasterScene();
    }

    @FXML
    private void goToOrderItems() {
        Customer selectedCustomer = tableViewOfCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            SceneManager.getInstance().stageOrderItemScene(selectedCustomer);
        }
        lblError.setText(AppTexts.CUSTOMER_NOONE_SELECTED);
    }
    //endregion
}
