package dao;

import entity.Address;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Андрей on 29.08.2017.
 */
public interface AddressDAO {
    //create
    void add(Address address) throws SQLException;

    //read
    List<Address> getAll() throws SQLException;
    Address getAddressById(long id) throws SQLException;

    //update
    void update(Address address) throws SQLException;

    //delete
    void remove(Address address) throws SQLException;

    //close connection and preparedStatement
    void closeConnectionAndStatement(Statement statement, Connection connection) throws SQLException;
}
