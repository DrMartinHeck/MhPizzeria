package heck.mhpizzeria.gui.tableViewOrderItems;

import heck.mhpizzeria.model.OrderItem;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * <h2>Darstellung des Preis der Pizza</h2>
 * In der Zelle des TableView sollen Properties der Modellklassenelemente angezeigt werden.
 * Es ist nicht ganz klar, wie die simple PropertyValueFactory, die im {@link heck.mhpizzeria.gui.OrderItemViewController}
 * für die Properties des OrderItems verwendet wird auf die Eigenschaften der Pizza zugreifen kann.
 * Daher wird hier eine neue Factory definiert, die den Zwischenschritt über die Pizza zu gehen macht.
 */
public class TableViewPriceCellFactory implements Callback<TableColumn.CellDataFeatures<OrderItem, String>, ObservableValue<String>> {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<OrderItem, String> orderItemFloatCellDataFeatures) {
        return orderItemFloatCellDataFeatures.getValue().getPizza().priceProperty();
    }
}