package heck.mhpizzeria.test;

import heck.mhpizzeria.model.Customer;
import heck.mhpizzeria.model.OrderItem;
import heck.mhpizzeria.model.Pizza;

public class TestModelMain {
    public static void main(String[] args) {
        Pizza pizza = new Pizza("Romana", "3.50");
        System.out.println(pizza);

        Customer customer = new Customer("Heribert", "2");
        System.out.println(customer);

        OrderItem orderItem = new OrderItem(pizza, 3);
        System.out.println(orderItem);

        Customer customer2 = new Customer("K", "5");
        System.out.println(customer2);
        System.out.println(
                String.format("Name %-20s Table %20s", customer2.getName(), customer2.getTableNumber()));
    }
}
