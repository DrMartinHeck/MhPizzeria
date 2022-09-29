package heck.mhpizzeria.gui;

import heck.mhpizzeria.gui.listViewBills.ListViewBillCellFactory;
import heck.mhpizzeria.logic.BillHolder;
import heck.mhpizzeria.model.Bill;
import heck.mhpizzeria.settings.AppTexts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h2>Controller für masterViewLayout.fxml</h2>
 * Dieses Fenster wird bei Start des Programms geladen. Die Optionen sind
 * <ul>
 *     <li>Erstellen einer neuen Rechnung: Dieser Button führt durch einen mehrstufigen Prozess, der eine neue Rechnung
 *     erstellt.</li>
 *     <li>Ansehen der Details einer bestehenden Rechnung: Die Listenansicht der Rechnungen zeigt nicht die einzelnen
 *     Positionen der Rechnung. Diese können durch die Detailansicht in Erfahrung gebracht werden. Falls keine
 *     Rechnung ausgewählt wurde, wird eine Fehlermeldung angezeigt.</li>
 *     <li>Löschen einer bestehenden Rechnung: Falls keine Rechnung ausgewählt wurde, wird wieder eine Fehlermeldung
 *     angezeigt.</li>
 * </ul>
 */
public class MasterViewController implements Initializable {
    //region attributes
    @FXML
    private ListView<Bill> listViewOfBill;
    @FXML
    private Label lblError;
    //endregion

    //region methods&functions
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewOfBill.setCellFactory(new ListViewBillCellFactory());
        listViewOfBill.setItems(BillHolder.getInstance().getBills());
        lblError.setText("");

        listViewOfBill.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2
                    && mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                Bill bill = listViewOfBill.getSelectionModel().getSelectedItem();
                SceneManager.getInstance().stageBillDetailedScene(bill);
            }
        });

        listViewOfBill.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                Bill bill = listViewOfBill.getSelectionModel().getSelectedItem();
                SceneManager.getInstance().stageBillDetailedScene(bill);
            }
        });
    }

    @FXML
    private void goToCustomerView() {
        SceneManager.getInstance().stageCustomerScene();
    }

    @FXML
    private void goToBillDetailedView() {
        Bill bill = listViewOfBill.getSelectionModel().getSelectedItem();
        if (bill == null) {
            lblError.setText(AppTexts.MASTER_NO_BILL_SELECTED);
        } else {
            SceneManager.getInstance().stageBillDetailedScene(bill);
        }
    }

    @FXML
    private void deleteBill() {
        Bill bill = listViewOfBill.getSelectionModel().getSelectedItem();
        if (bill == null) {
            lblError.setText(AppTexts.MASTER_NO_BILL_SELECTED);
        } else {
            lblError.setText("");
            BillHolder.getInstance().deleteBill(bill);
        }
    }
    //endregion
}