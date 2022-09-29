package heck.mhpizzeria.logic;

import heck.mhpizzeria.logic.db.DbManager;
import heck.mhpizzeria.model.Customer;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * <h2>Singleton zum Verwalten von {@link Customer}</h2>
 * Kunden werden in einer ObservableList gespeichert, die es erlaubt mit Hilfe von einem Listener Änderungen an die
 * Datenbank weiterzugeben. Die Liste kann and die Views weitergegeben werden für die Darstellung in der GUI.
 */
public class CustomerHolder {
    //region attributes
    private static CustomerHolder instance;
    private static ObservableList<Customer> customers;
    //endregion

    //region constructors
    private CustomerHolder(){
        customers = FXCollections.observableArrayList(customer -> new Observable[]{
                customer.nameProperty(), customer.tableNumberProperty()
        });

//        customers.addAll(TestData.getTestCustomers());
        customers.addAll(DbManager.getInstance().readAllCustomerRecords());

        //Listener wird erst hier aktiviert, weil das erste befüllen ohne Aktionen ablaufen soll.
        customers.addListener((ListChangeListener<Customer>) change -> {
            while (change.next()){
                if (change.wasAdded()) {
                    Customer customer = change.getAddedSubList().get(0);
                    DbManager.getInstance().insertCustomer(customer);

                } else if (change.wasRemoved()) {
                    Customer customer = change.getRemoved().get(0);
                    DbManager.getInstance().removeCustomer(customer);

                } else if (change.wasUpdated()) {
                    int updatedIndex = change.getFrom();
                    Customer customer = change.getList().get(updatedIndex);
                    DbManager.getInstance().updateCustomer(customer);
                }
            }
        });
    }

    public static synchronized CustomerHolder getInstance(){
        if (instance == null) instance = new CustomerHolder();
        return instance;
    }
    //endregion

    //region functions & methods
    public ObservableList<Customer> getCustomers(){
        return customers;
    }

    public void removeCustomer(Customer customer){
        customers.remove(customer);
    }

    public void saveCustomer(Customer customer){
        customers.add(customer);
    }
}
