package heck.mhpizzeria.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * <h2>Repräsentation einer Rechnungsposition,</h2>
 * von denen mehrere auf einer Rechnung auftauchen können.
 * <ul>
 *     <li>pizza         : Pizza-Objekt ({@link Pizza}), das im Rahmen der entsprechenden Rechnungsposition geordert wurde</li>
 *     <li>count         : Anzahl der Pizzen dieser Art, die geordert werden</li>
 *     <li>positionPrice : Preis der Pizza multipliziert mit der georderten Anzahl</li>
 *     <li>pk_id         : Datenbank-Schlüssel</li>
 * </ul>
 */
public class OrderItem {
    //region attributes
    private Pizza pizza;
    private IntegerProperty count;
    private FloatProperty positionPrice;
    private int pk_id = -1;
    //endregion

    //region setters&getters

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getCount() {
        return count.get();
    }

    public IntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
        this.positionPrice = new SimpleFloatProperty(Float.valueOf(pizza.getPrice()) * count);
    }

    public int getPk_id() {
        return pk_id;
    }

    public void setPk_id(int pk_id) {
        this.pk_id = pk_id;
    }

    public float getPositionPrice() {
        return positionPrice.get();
    }

    public FloatProperty positionPriceProperty() {
        return positionPrice;
    }

    public void setPositionPrice(float positionPrice) {
        this.positionPrice.set(positionPrice);
    }

    //endregion

    //region constructors
    public OrderItem(){
        pizza = new Pizza();
        count = new SimpleIntegerProperty(0);
        positionPrice = new SimpleFloatProperty(0.f);

    }

    public OrderItem(Pizza pizza, int count){
        this.pizza = pizza;
        this.count = new SimpleIntegerProperty(count);
        this.positionPrice = new SimpleFloatProperty(Float.valueOf(pizza.getPrice()) * count);
    }
    //endregion

    //region methods & functions
    public void increaseCount(){
        count.set(count.get()+1);
        System.out.println(count);
        positionPrice.set(Float.valueOf(pizza.getPrice()) * count.get());
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "pizza=" + pizza +
                ", count=" + count +
                ", pk_id=" + pk_id +
                '}';
    }
    //endregion
}
