package heck.mhpizzeria.gui.listViewBills;

import heck.mhpizzeria.model.Bill;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ListViewBillCellFactory implements Callback<ListView<Bill>, ListCell<Bill>> {
    @Override
    public ListCell<Bill> call(ListView<Bill> billListView) {
        return new ListViewBillCell();
    }
}
