package heck.mhpizzeria.model;

import heck.mhpizzeria.settings.AppTexts;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <h2>Repräsentation essbarer Pizza</h2>
 * z.B. Romana, Margherita, etc.
 * <ul>
 *     <li>name  : wiedererkennbarer Name der Pizzakreation</li>
 *     <li>price : Preis der Pizza in Euro</li>
 *     <li>pk_id : Datenbank-Schlüssel</li>
 * </ul>
 * DESIGNENTSCHEIDUNG: Dieses Programm ist nicht dafür gedacht Pizzen zu ändern oder neue Pizzen zu entwerfen.
 * Für die aktuelle Demonstration trage ich einfach Pizzen in die Datenbank über das Webinterface ein.
 * Man könnte ein spezielles Chef-Programm schreiben, dass die Pizzen in der Datenbank verwaltet.
 */
public class Pizza {
    //region attributes
    private StringProperty name;
    private StringProperty price;
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

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public int getPk_id() {
        return pk_id;
    }

    public void setPk_id(int pk_id) {
        this.pk_id = pk_id;
    }
    //endregion

    //region constructors
    public Pizza(){
        name = new SimpleStringProperty(AppTexts.DEFAULT_PIZZA_NAME);
        price = new SimpleStringProperty(AppTexts.DEFAULT_PIZZA_PRICE);
    }

    public Pizza(String name, String price){
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
    }
    //endregion

    //region methods&functions
    @Override
    public String toString() {
        return "Pizza{" +
                "name=" + name +
                ", price=" + price +
                ", pk_id=" + pk_id +
                '}';
    }
    //endregion
}
