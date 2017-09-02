package dao;

import entity.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Андрей on 29.08.2017.
 */
public interface EmployeeDAO {
    //create
    void add(Employee employee) throws SQLException;

    //read
    List<Employee> getAll() throws SQLException;
    Employee getEmployeeById(long id) throws SQLException;

    //update
    void update(Employee employee) throws SQLException;

    //delete
    void remove(Employee employee) throws SQLException;

    //close connection and preparedStatement
    void closeConnectionAndStatement(Statement statement, Connection connection) throws SQLException;

}
