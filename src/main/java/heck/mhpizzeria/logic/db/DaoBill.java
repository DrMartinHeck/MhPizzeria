package heck.mhpizzeria.logic.db;

import heck.mhpizzeria.model.Bill;
import heck.mhpizzeria.model.Customer;
import heck.mhpizzeria.model.OrderItem;
import heck.mhpizzeria.model.Pizza;
import heck.mhpizzeria.settings.AppCommands;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Datenbank-Zugriff für {@link Bill}</h2>
 */
public class DaoBill implements Dao<Bill> {

    //region functions&methods
    @Override
    public List<Bill> readAll(Connection connection) {
        List<Bill> bills = new ArrayList<>();

        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(AppCommands.DAO_BILL_SELECT_ALL);


            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setPk_id(resultSet.getInt(AppCommands.COL_PK_ID));

                //Handle customer reading for the bill
                int customerKey = resultSet.getInt(AppCommands.COL_CUSTOMER_KEY);
                ResultSet resultSetCustomer = statement.executeQuery(AppCommands.DAO_BILL_SELECT_CUSTOMER + customerKey);
                resultSetCustomer.next();
                String customerName = resultSetCustomer.getString(AppCommands.DAO_BILL_CUSTOMER_NAME);
                String customerTableNumber = resultSetCustomer.getString(AppCommands.DAO_BILL_CUSTOMER_TABLE_NUMBER);
                Customer customer = new Customer(customerName, customerTableNumber);
                customer.setPk_id(customerKey);
                bill.setCustomer(customer);


                //Handle sum of bill
                bill.setSum(resultSet.getFloat(AppCommands.DAO_BILL_SUM));

                //Handle orderItems of bill
                List<OrderItem> orderItems = new ArrayList<>();
                ResultSet resultSetOrderItems = statement.executeQuery(AppCommands.DAO_BILL_SELECT_ORDERITEMS + bill.getPk_id());
                while (resultSetOrderItems.next()) {
                    OrderItem orderItem = new OrderItem();
                    //Handle Pizza of Order Item
                    int pizzaKey = resultSetOrderItems.getInt(AppCommands.DAO_BILL_COL_PIZZA_KEY);
                    ResultSet resultSetPizza = statement.executeQuery(AppCommands.DAO_BILL_SELECT_PIZZA + pizzaKey);
                    resultSetPizza.next();
                    String pizzaName = resultSetPizza.getString(AppCommands.DAO_BILL_PIZZA_NAME);
                    String pizzaPrice = resultSetPizza.getString(AppCommands.DAO_BILL_PIZZA_PRICE);
                    Pizza pizza = new Pizza(pizzaName, pizzaPrice);
                    pizza.setPk_id(pizzaKey);
                    orderItem.setPizza(pizza);

                    orderItem.setPk_id(resultSetOrderItems.getInt(AppCommands.COL_PK_ID));

                    orderItem.setCount(resultSetOrderItems.getInt(AppCommands.DAO_BILL_ORDERITEM_COUNT));

                    orderItems.add(orderItem);
                }

                bill.setOrderItems(orderItems);

                bills.add(bill);
            }

        } catch (Exception e) {
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
        return bills;
    }

    @Override
    public Bill readById(Connection connection, int id) {
        return null;
    }

    @Override
    public void create(Connection connection, Bill bill) {
        PreparedStatement statement = null;

        try {
            //insert bill
            //customer will be already there at this point due to its own handling
            statement = connection.prepareStatement(AppCommands.DAO_BILL_INSERT_INTO_BILLS, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, bill.getCustomer().getPk_id());
            statement.setFloat(2, bill.getSum());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            int insertId;

            if (resultSet.next()) {
                insertId = resultSet.getInt("insert_id");
                bill.setPk_id(insertId);
            }

            //insert orderItems
            //Pizza should already be there, as an existing Pizza was chosen
            for (OrderItem orderItem:bill.getOrderItems()) {
                statement = connection.prepareStatement(AppCommands.DAO_BILL_INSERT_INTO_ORDERITEMS, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, orderItem.getPizza().getPk_id());
                statement.setInt(2, orderItem.getCount());
                statement.setFloat(3, orderItem.getPositionPrice());
                statement.setInt(4, bill.getPk_id());

                statement.execute();
                ResultSet resultSetOrderItem = statement.getGeneratedKeys();

                int insertIdOrderItem;

                if (resultSetOrderItem.next()) {
                    insertIdOrderItem = resultSetOrderItem.getInt("insert_id");
                    orderItem.setPk_id(insertIdOrderItem);
                }
            }

        } catch (Exception e) {
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
    public void update(Connection connection, Bill object) {

    }

    @Override
    public void delete(Connection connection, Bill bill) {
        Statement statement = null;
        try{
            statement = connection.createStatement();
            statement.executeQuery(AppCommands.DAO_BILL_DELETE_ID + bill.getPk_id());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //endregion
}