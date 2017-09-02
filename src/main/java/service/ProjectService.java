package service;

import bl.Util;
import dao.ProjectDAO;
import entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectService extends Util implements ProjectDAO {
    Connection conn = getConnection();

    @Override
    public void add(Project project) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO PROJECT (ID, TITLE) VALUES (?, ?)";

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, project.getId());
            preparedStatement.setString(2, project.getTitle());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("here you added an element with unique ID but you are not sure about his unique. DB can contain it already");
        }finally{
            closeConnectionAndStatement(preparedStatement, conn);
        }
    }

    @Override
    public List<Project> getAll() throws SQLException {
        List<Project> resultList = new ArrayList<>();
        Statement statement = null;
        String sql = "SELECT ID, TITLE FROM PROJECT";

        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Project project = new Project();
                project.setId(resultSet.getLong("ID"));
                project.setTitle(resultSet.getString("TITLE"));
                resultList.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnectionAndStatement(statement, conn);
        }
        return resultList;
    }

    @Override
    public Project getProjectById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID, TITLE FROM PROJECT WHERE ID=?";
        Project project = new Project();

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();


            project.setId(resultSet.getLong("ID"));
            project.setTitle(resultSet.getString("TITLE"));

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnectionAndStatement(preparedStatement, conn);
        }
        return project;
    }

    @Override
    public void update(Project project) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE PROJECT SET TITLE=? WHERE ID=?";

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setLong(2, project.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnectionAndStatement(preparedStatement, conn);
        }
    }

    @Override
    public void remove(Project project) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM PROJECT WHERE ID=?";

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, project.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnectionAndStatement(preparedStatement, conn);
        }
    }

    @Override
    public void closeConnectionAndStatement(Statement statement, Connection connection) throws SQLException {
        if(statement != null)
            statement.close();
        if(connection != null)
            connection.close();
    }
}
