package heck.mhpizzeria.logic.db;

import heck.mhpizzeria.model.Bill;
import heck.mhpizzeria.model.Customer;
import heck.mhpizzeria.model.Pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * <h2>Singleton zur Verwaltung der Datenbankverbindungen</h2>
 *
 * <br>
 * DESIGNENTSCHEIDUNG: Anstatt sehr generisch mit Objekten wie in "insertDataRecord(Object object) zu arbeiten,
 * und dann in der Funktion auf die eigentliche Klasse mit "instanceof" zu testen, wähle ich bereits für
 * die Signatur Animal spezifische Parameter.
 */
public class DbManager {
    //region constants
    private static final String SERVER_IP = "localhost";
    //Datenbankname
    private static final String DB_NAME = "pizzeria_db";
    //URL zur Verbindung der Datenbank
    private static final String CONNECTION_URL = "jdbc:mariadb://"
            + SERVER_IP + "/" + DB_NAME;
    //Nutzername des Datenbanknutzers
    private static final String DB_USERNAME = "root";
    //Passwort des Datenbanknutzers
    private static final String DB_PASSWORD = "";

    private final DaoCustomer daoCustomer;
    private final DaoPizza daoPizza;
    private final DaoBill daoBill;
    //endregion

    //region attributes
    private static DbManager instance;
    //endregion

    //region constructors
    private DbManager(){
        daoCustomer = new DaoCustomer();
        daoPizza = new DaoPizza();
        daoBill = new DaoBill();
    }

    public static synchronized DbManager getInstance(){
        if (instance == null) instance = new DbManager();
        return instance;
    }
    //endregion

    //region methods & functions
    public void insertCustomer(Customer customer){
        daoCustomer.create(getConnection(), customer);
    }
    public void insertBill(Bill bill){
        daoBill.create(getConnection(), bill);
    }

    public List<Customer> readAllCustomerRecords() {
        return daoCustomer.readAll(getConnection());
    }

    public List<Pizza> readAllPizzaRecords() {
        return daoPizza.readAll(getConnection());
    }

    public List<Bill> readAllBillRecords(){
        return daoBill.readAll(getConnection());
    }

    public void removeCustomer(Customer customer){
        daoCustomer.delete(getConnection(), customer);
    }
    public void removeBill(Bill bill){
        daoBill.delete(getConnection(), bill);
    }

    public void updateCustomer(Customer customer){
        daoCustomer.update(getConnection(), customer);
    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
    //endregion
}