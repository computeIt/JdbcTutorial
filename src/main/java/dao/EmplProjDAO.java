package dao;

import entity.EmplProj;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Андрей on 30.08.2017.
 */
public interface EmplProjDAO {
    //create
    void add(EmplProj emplProj) throws SQLException;

    //read
    List<EmplProj> getAll() throws SQLException;
    EmplProj getByEmployeeIdAndProjectId(long employeeId, long projectId) throws SQLException;

    //update
    void update(EmplProj emplProj) throws SQLException;

    //delete
    void remove(EmplProj emplProj) throws SQLException;

    //close connection and preparedStatement
    void closeConnectionAndStatement(Statement statement, Connection connection) throws SQLException;
}
