package heck.mhpizzeria.logic.db;

import heck.mhpizzeria.model.Pizza;
import heck.mhpizzeria.settings.AppTexts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Datenbank-Zugriff für {@link Pizza}</h2>
 */
public class DaoPizza implements Dao<Pizza> {
    //region functions&methods
    @Override
    public List<Pizza> readAll(Connection connection) {
        List<Pizza> pizzas = new ArrayList<>();
        Statement statement = null;

        try {
            statement =connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM pizzas");

            while (resultSet.next()) {
                Pizza pizza = new Pizza(
                        resultSet.getString(AppTexts.PIZZA_DB_COL_NAME),
                        resultSet.getString(AppTexts.PIZZA_DB_COL_PRICE)
                );
                pizza.setPk_id(resultSet.getInt(AppTexts.PIZZA_DB_COL_PK_ID));

                pizzas.add(pizza);
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
        return pizzas;
    }

    @Override
    public Pizza readById(Connection connection, int id) {
        return null;
    }

    @Override
    public void create(Connection connection, Pizza object) {}

    @Override
    public void update(Connection connection, Pizza object) {}

    @Override
    public void delete(Connection connection, Pizza object) {}
    //endregion
}