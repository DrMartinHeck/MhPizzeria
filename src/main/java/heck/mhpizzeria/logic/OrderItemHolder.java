package heck.mhpizzeria.logic;

import heck.mhpizzeria.logic.db.DbManager;
import heck.mhpizzeria.model.OrderItem;
import heck.mhpizzeria.model.Pizza;
import heck.mhpizzeria.settings.AppTexts;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * <h2>Singleton zum Verwalten von {@link OrderItem}</h2>
 * Rechnungspositionen werden in einer ObservableList gespeichert, die es erlaubt mit Hilfe von einem Listener Änderungen an die
 * Datenbank weiterzugeben. Die Liste kann and die Views weitergegeben werden für die Darstellung in der GUI.
 */
public class OrderItemHolder {
    //region attributes
    private static OrderItemHolder instance;
    private static ObservableList<OrderItem> orderItems;
    //endregion

    //region constructors
    private OrderItemHolder(){
        orderItems = FXCollections.observableArrayList(orderItem -> new Observable[]{
                orderItem.countProperty()
        });

        //getPizzas and create an Order Item with count 0 for every Pizza
//        List<Pizza> pizzas = TestData.getTestPizzas();
        List<Pizza> pizzas = DbManager.getInstance().readAllPizzaRecords();

        if (pizzas.size() == 0) System.out.println(AppTexts.NO_PIZZAS_WARNING);

        for (Pizza pizza : pizzas) {
            System.out.println("Hinzugefügte Pizza: " + pizza.getName());
            orderItems.add(new OrderItem(pizza, 0));
        }

        //Listener wird erst hier aktiviert, weil das erste befüllen ohne Aktionen ablaufen soll.
        orderItems.addListener((ListChangeListener<OrderItem>) change -> {
            System.out.println("Listenänderung: " + change);
        });
    }

    public static synchronized OrderItemHolder getInstance(){
        if (instance == null) instance = new OrderItemHolder();
        return instance;
    }
    //endregion

    //region functions & methods
    public ObservableList<OrderItem> getOrderItems(){
        return orderItems;
    }

    public void setCountsZero(){
        for (OrderItem orderItem: orderItems) {
            orderItem.setCount(0);
            orderItem.setPositionPrice(0);
        }
    }

    public ObservableList<OrderItem> getNonZeroOrderItems(){
        ObservableList<OrderItem> nonZeroOrderItems = FXCollections.observableArrayList(orderItem -> new Observable[]{
                orderItem.countProperty()
        });
        for (OrderItem orderItem: orderItems) {
            if(orderItem.getCount() > 0)
                nonZeroOrderItems.add(orderItem);
        }
        return nonZeroOrderItems;
    }

    public float getTotalPrice(){
        float totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            float positionPrice = orderItem.getPositionPrice();
            totalPrice += positionPrice;
        }
        return totalPrice;
    }
}
