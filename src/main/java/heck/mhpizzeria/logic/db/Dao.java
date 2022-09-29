package heck.mhpizzeria.logic.db;

import java.sql.Connection;
import java.util.List;

/**
 * Interface für <h2><i>D</i>ata <i>A</i>ccess <i>O</i>bjects für CRUD-Operationen,</h2>
 * die an die Datenbank (mit {@link Connection}) weitergeleitet werden. <br>
 * (<i>C</i>reate, <i>R</i>ead, <i>U</i>pdate, <i>D</i>elete)
 */
public interface Dao<T> {
    void create(Connection connection, T object);
    List<T> readAll(Connection connection);
    T readById(Connection connection, int id);
    void update (Connection connection, T object);
    void delete (Connection connection, T object);
}
