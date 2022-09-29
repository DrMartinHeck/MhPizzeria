package heck.mhpizzeria.settings;

public class AppCommands {
    //region constants
    //customers war animals; muss prüfen, ob das die Tabelle oder die Datenbank ist
    public static final String CUSTOMER_STATEMENT_INSERT = "INSERT INTO customers (name, table_number) VALUES (?,?)";
    public static final String COL_PK_ID = "pk_id";
    public static final String CUSTOMER_TABLE_ATTRIBUTE_NAME = "name";
    public static final String CUSTOMER_TABLE_ATTRIBUTE_NUMBER = "tableNumber";
    public static final String ORDERITEM_DETAILED_COUNT_ZERO = "0";
    public static final String ORDERITEM_TABLE_ATTRIBUTE_COUNT = "count";
    public static final String ORDERITEM_TABLE_ATTRIBUTE_POSITION_PRICE = "positionPrice";
    public static final String DAO_BILL_SELECT_ALL = "SELECT * FROM bills";
    public static final String COL_CUSTOMER_KEY = "customer";
    public static final String DAO_BILL_DELETE_ID = "DELETE FROM bills WHERE pk_id = ";
    public static final String DAO_BILL_SELECT_CUSTOMER = "SELECT * FROM customers WHERE pk_id=";
    public static final String DAO_BILL_CUSTOMER_NAME = "name";
    public static final String DAO_BILL_CUSTOMER_TABLE_NUMBER = "table_number";
    public static final String DAO_BILL_SUM = "sum";
    public static final String DAO_BILL_SELECT_ORDERITEMS = "SELECT * FROM order_items WHERE bill = ";
    public static final String DAO_BILL_COL_PIZZA_KEY = "pizza";
    public static final String DAO_BILL_SELECT_PIZZA = "SELECT * FROM pizzas WHERE pk_id=";
    public static final String DAO_BILL_PIZZA_NAME = "name";
    public static final String DAO_BILL_PIZZA_PRICE = "price";
    public static final String DAO_BILL_ORDERITEM_COUNT = "count";
    public static final String DAO_BILL_INSERT_INTO_BILLS = "INSERT INTO bills (customer, sum) VALUES (?,?)";
    public static final String DAO_BILL_INSERT_INTO_ORDERITEMS = "INSERT INTO order_items (pizza, count, position_price, bill) VALUES (?,?,?,?)";
}
