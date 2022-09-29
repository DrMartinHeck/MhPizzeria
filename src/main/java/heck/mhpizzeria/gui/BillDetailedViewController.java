package heck.mhpizzeria.gui;

import heck.mhpizzeria.logic.BillHolder;
import heck.mhpizzeria.model.Bill;
import heck.mhpizzeria.model.OrderItem;
import heck.mhpizzeria.settings.AppTexts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h2>Controller für billDetailedViewLayout.fxml</h2>
 * Der View stellt eine im Master ausgewählte Rechnung dar.
 * Die einzige mögliche Interaktion ist die Löschung, wonach wie beim 'zurück' Button auch,
 * in den Master gewechselt wird.
 * <br><br>
 * <h4>DESIGNENTSCHEIDUNG</h4>
 * Die Schriftart 'Monospaced' ist notwendig damit die verschiedenen Zeilen im Label bei den Positionen sinnvoll
 * untereinander angeordnet werden.<br><br>
 * Das Label passt seine Größe über mehrere Zeilen an, wenn es bereits im 'initialize' gesetzt wird. Falls
 * erst in einer späteren setBill Funktion der Text angepasst wird, passt sich die Labelgröße nicht mehr an.
 * Um schon im 'initialize' die Bill zur Verfügung zu haben, habe ich im BillHolder die 'currentBill' eingefügt, die
 * hier benutzt wird.
 */
public class BillDetailedViewController implements Initializable {
    //region attributes
    @FXML
    private Label lblPositions;
    @FXML
    private Label lblName;
    @FXML
    private Label lblTableNumber;
    @FXML
    private Label lblSum;
    //endregion

    //region methods&functions
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bill bill = BillHolder.getCurrentBill();

        lblName.setText(bill.getCustomer().getName());
        lblTableNumber.setText(bill.getCustomer().getTableNumber());
        lblSum.setText(String.valueOf(bill.getSum()));

        String labelText = new String("");
        for (OrderItem orderItem : bill.getOrderItems()) {
            labelText += String.format(AppTexts.BILL_DETAILED_FORMAT, orderItem.getPizza().getName(),
                    orderItem.getPizza().getPrice(), orderItem.getCount(), orderItem.getPositionPrice())+ "\n";
        }
        lblPositions.setFont(Font.font("Monospaced", 14));
        lblPositions.setText(labelText);
    }

    @FXML
    private void goToMasterView(){
        SceneManager.getInstance().stageMasterScene();
    }

    @FXML
    private void deleteBill(){
        BillHolder.getInstance().deleteBill(BillHolder.getCurrentBill());
        goToMasterView();
    }
    //endregion
}