package heck.mhpizzeria.logic.db;

import heck.mhpizzeria.model.Customer;
import heck.mhpizzeria.settings.AppCommands;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Datenbank-Zugriff für {@link Customer}</h2>
 */
public class DaoCustomer implements Dao<Customer> {
    //region constants
    public static final String COL_NAME = "name";
    public static final String COL_COLOUR = "table_Number";
    public static final String COL_PK_ID = "pk_id";
    //endregion

    //region functions&methods
    @Override
    public void create(Connection connection, Customer customer) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(AppCommands.CUSTOMER_STATEMENT_INSERT, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getTableNumber());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            int insertId;

            if (resultSet.next()) {
                insertId = resultSet.getInt("insert_id");
                customer.setPk_id(insertId);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Customer> readAll(Connection connection) {
        List<Customer> customers = new ArrayList<>();

        Statement statement = null;

        try {
            statement =connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString(COL_NAME),
                        resultSet.getString(COL_COLOUR)
                );
                customer.setPk_id(resultSet.getInt(COL_PK_ID));

                customers.add(customer);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    @Override
    public Customer readById(Connection connection, int id) {
        return null;
    }

    @Override
    public void update(Connection connection, Customer customer) {
        Statement statement = null;
        System.out.println("Ich bin hier");
        try {
            statement = connection.createStatement();
            statement.executeQuery("UPDATE customers SET name=\'" + customer.getName()
                    + "\', table_number=\'" + customer.getTableNumber() + "\' WHERE pk_id=" + customer.getPk_id());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Connection connection, Customer customer) {
        Statement statement = null;
        System.out.println("DaoCustomer " + customer.getPk_id());
        try{
            statement = connection.createStatement();
            statement.executeQuery("DELETE FROM customers WHERE pk_id = " + customer.getPk_id());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //endregion
}