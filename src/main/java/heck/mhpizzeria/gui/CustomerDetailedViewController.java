package heck.mhpizzeria.gui;

import heck.mhpizzeria.logic.CustomerHolder;
import heck.mhpizzeria.model.Customer;
import heck.mhpizzeria.settings.AppTexts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * <h2>Controller für customerDetailedView.fxml</h2>
 * Dieser View regelt sowohl das Erstellen von neuen Kunden, als auch das Bearbeiten von bestehenden Kunden.
 * Name und Tischnummer können eingegeben werden, wobei ein leeres Namensfeld bei neuen Kunden zu einer Fehlermeldung führt.
 * Bei Bestandskunden wird ein leeres Feld nicht übernommen. Allerdings können Bestandskunden gelöscht werden.
 * Alternativ können die aktuellen Eingaben gespeichert werden, oder über den "zurück"-Button zur Kundenauswahl ohne
 * Speichern zurückgekehrt werden.
 */
public class CustomerDetailedViewController {
    //region attributes
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtTableNumber;
    @FXML
    private Label lblError;
    @FXML
    private Button btnDelete;

    private Customer customer;
    //endregion

    //region setter & getter
    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (customer != null) {
            txtName.setText(customer.getName());
            txtTableNumber.setText(String.valueOf(customer.getTableNumber()));
        }
    }

    public Customer getCustomer() {
        return customer;
    }
    //endregion

    //region functions & methods
    @FXML
    private void goToCustomerView(){
        SceneManager.getInstance().stageCustomerScene();
    }

    public void disableDeleteBtn(){
        btnDelete.setDisable(true);
    }

    @FXML
    private void deleteCustomer(){
        CustomerHolder.getInstance().removeCustomer(customer);
        goToCustomerView();
    }

    @FXML
    private void saveCustomer(){
        if (customer!= null){
            if (fieldIsNotBlankEmptyOrEqual(txtName.getText(), customer.getName()))
                customer.setName(txtName.getText());
            if (fieldIsNotBlankEmptyOrEqual(txtName.getText(), customer.getTableNumber()))
                customer.setTableNumber(txtTableNumber.getText());
            goToCustomerView();
        } else {
            if (txtName.getText().isEmpty() || txtName.getText().isBlank()) {
                lblError.setText(AppTexts.CUSTOMER_DETAILED_NO_EMPTY_NAME);
            } else {
                customer = new Customer(txtName.getText(), txtTableNumber.getText());
                CustomerHolder.getInstance().saveCustomer(customer);
                goToCustomerView();
            }
        }
    }

    private boolean fieldIsNotBlankEmptyOrEqual(String fieldText, String customerProperty) {
        return !fieldText.isBlank() && !fieldText.isEmpty() && !fieldText.equals(customerProperty);
    }
    //endregion
}
