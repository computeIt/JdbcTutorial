package dao;

import entity.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Андрей on 30.08.2017.
 */
public interface ProjectDAO {
    //create
    void add(Project project) throws SQLException;

    //read
    List<Project> getAll() throws SQLException;
    Project getProjectById(long id) throws SQLException;

    //update
    void update(Project project) throws SQLException;

    //delete
    void remove(Project project) throws SQLException;

    //close connection and preparedStatement
    void closeConnectionAndStatement(Statement statement, Connection connection) throws SQLException;
}
