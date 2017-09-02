package service;

import bl.Util;
import dao.EmplProjDAO;
import entity.EmplProj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmplProjService extends Util implements EmplProjDAO {
    Connection conn = getConnection();

    @Override
    public void add(EmplProj emplProj) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO EMPL_PROJ(EMPLOYEE_ID, PROJECT_ID) VALUES(?, ?)";

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, emplProj.getEmployeeId());
            preparedStatement.setLong(2, emplProj.getProjectId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnectionAndStatement(preparedStatement, conn);
        }
    }

    @Override
    public List<EmplProj> getAll() throws SQLException {
        List<EmplProj> resultList = new ArrayList<>();
        Statement statement = null;
        String sql = "SELECT EMPLOYEE_ID, PROJECT_ID FROM EMPL_PROJ";

        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                EmplProj emplProj = new EmplProj();
                emplProj.setEmployeeId(resultSet.getLong("EMPLOYEE_ID"));
                emplProj.setProjectId(resultSet.getLong("PROJECT_ID"));
                resultList.add(emplProj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnectionAndStatement(statement, conn);
        }
        return resultList;
    }

    @Override
    public EmplProj getByEmployeeIdAndProjectId(long employeeId, long projectId) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT EMPLOYEE_ID, PROJECT_ID FROM EMPL_PROJ WHERE EMPLOYEE_ID=? AND PROJECT_ID=?";
        EmplProj emplProj = new EmplProj();

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, employeeId);
            preparedStatement.setLong(2, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();

            emplProj.setEmployeeId(resultSet.getLong("EMPLOYEE_ID"));
            emplProj.setProjectId(resultSet.getLong("PROJECT_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnectionAndStatement(preparedStatement, conn);
        }
        return emplProj;
    }

    @Override
    public void update(EmplProj emplProj) throws SQLException {
        String sql = "UPDATE EMPL_PROJ SET EMPLOYEE_ID=?, PROJECT_ID=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, emplProj.getEmployeeId());
            preparedStatement.setLong(2, emplProj.getProjectId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnectionAndStatement(preparedStatement, conn);
        }

    }

    @Override
    public void remove(EmplProj emplProj) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM EMPL_PROJ WHERE EMPLOYEE_ID=? AND PROJECT_ID=?";

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, emplProj.getEmployeeId());
            preparedStatement.setLong(2, emplProj.getProjectId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnectionAndStatement(preparedStatement, conn);
        }

    }

    @Override
    public void closeConnectionAndStatement(Statement statement, Connection connection) throws SQLException {
        if (statement != null)
            statement.close();
        if (connection != null)
            connection.close();
    }
}
