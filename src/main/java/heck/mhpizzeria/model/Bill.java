package heck.mhpizzeria.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Repräsentation einer Rechnung,</h2>
 * die ein einzelner Kunde nach einer Bestellung zu begleichen hat.
 * <ul>
 *     <li>customer  : Kundenobjekt ({@link Customer}) für Kunden, an den die Rechnung geht</li>
 *     <li>orderItems: Liste von Rechnungspositionen ({@link OrderItem}), die bestellt wurden</li>
 *     <li>sum       : Geldsumme, die zu begleichen ist</li>
 *     <li>pk_id     : Datenbank-Schlüssel</li>
 * </ul>
 */
public class Bill {
    //region attributes
    private Customer customer;
    private List<OrderItem> orderItems;
    private FloatProperty sum;
    private int pk_id;
    //endregion

    //region setters&getters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        //This needs to be a deep copy, because OrderItems are set to zero, after Bill is saved to reuse OrderItemHolder
        for (OrderItem orderItem : orderItems) {
            this.orderItems.add(new OrderItem(orderItem.getPizza(), orderItem.getCount()));
        }
    }

    public float getSum() {
        return sum.get();
    }

    public FloatProperty sumProperty() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum.set(sum);
    }

    public int getPk_id() {
        return pk_id;
    }

    public void setPk_id(int pk_id) {
        this.pk_id = pk_id;
    }
    //endregion

    //region constructors
    public Bill(){
        sum = new SimpleFloatProperty(0);
        orderItems = new ArrayList<>();
        customer = new Customer();

    }
    //endregion
}
