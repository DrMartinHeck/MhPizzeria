package heck.mhpizzeria.test;

import heck.mhpizzeria.model.Customer;
import heck.mhpizzeria.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    private static final int CUSTOMER_COUNT = 30;

    public static List<Customer> getTestCustomers(){
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Heinzi", "3"));
        customers.add(new Customer("Willi", "7"));
        customers.add(new Customer("NachbarsKinder", "12"));
        customers.add(new Customer("Willi", "1"));
        return customers;
    }
    public static List<Pizza> getTestPizzas(){
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza("Romana", "  8.00"));
        pizzas.add(new Pizza("Hawai", "  8.00"));
        pizzas.add(new Pizza("Elvis", "12.50"));
        pizzas.add(new Pizza("Vegetaria", "  7.00"));
        return pizzas;
    }
}
