package heck.mhpizzeria.gui.listViewBills;

import heck.mhpizzeria.model.Bill;
import heck.mhpizzeria.settings.AppTexts;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;

/**
 * <h2>Darstellung der Rechnung im Master</h2>
 * Kundenname und -Tischnummer, sowie Gesamtsumme der Rechnung werden angezeigt.
 * <br><br>
 * <h4>DESIGNENTSCHEIDUNG:</h4> Um im Listview einen aufgeräumteren Anblick zu ermöglichen,
 * werden die Texte in 'Monospaced' angezeigt, also so, dass jedes Zeichen die gleiche
 * Breite bei der Darstellung hat.
 */
public class ListViewBillCell extends ListCell<Bill> {
    @Override
    protected void updateItem(Bill bill, boolean isEmpty) {
        super.updateItem(bill, isEmpty);
        if (bill == null || isEmpty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(String.format(AppTexts.BILL_LISTVIEW_FORMAT,
                    bill.getCustomer().getName(), bill.getCustomer().getTableNumber(), bill.getSum()));
            setFont(Font.font("Monospaced", 14));
        }
    }
}
