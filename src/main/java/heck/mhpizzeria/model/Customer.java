package heck.mhpizzeria.model;

import heck.mhpizzeria.settings.AppTexts;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <h2>Repräsentation eines Kunden,</h2>
 * der Pizza vor Ort bestellt.
 * <ul>
 *     <li>name       : Name des Kunden, wie er in der Pizzaria bekannt ist, z.B. Willi oder vorne links</li>
 *     <li>tableNumber: Tischnummer im Restaurant</li>
 *     <li>pk_id      : Datenbank-Schlüssel</li>
 * </ul>
 */
public class Customer {
    //region attributes
    private StringProperty name;
    private StringProperty tableNumber;
    private int pk_id = -1;
    //endregion

    //region setters&getters
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTableNumber() {
        return tableNumber.get();
    }

    public StringProperty tableNumberProperty() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber.set(tableNumber);
    }

    public int getPk_id() {
        return pk_id;
    }

    public void setPk_id(int pk_id) {
        this.pk_id = pk_id;
    }
    //endregion

    //region constructors
    public Customer (){
        name = new SimpleStringProperty(AppTexts.DEFAULT_CUSTOMER_NAME);
        tableNumber = new SimpleStringProperty(AppTexts.DEFAULT_CUSTOMER_TABLE_NUMBER);
    }

    public Customer (String name, String tableNumber){
        this.name = new SimpleStringProperty(name);
        this.tableNumber = new SimpleStringProperty(tableNumber);
    }
    //endregion

    //region methods&functions
    @Override
    public String toString() {
        return "Customer{" +
                "name=" + name +
                ", tableNumber=" + tableNumber +
                ", pk_id=" + pk_id +
                '}';
    }
    //endregion
}
