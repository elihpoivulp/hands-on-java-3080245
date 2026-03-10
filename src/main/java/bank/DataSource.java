package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  public static Connection connect() {
    String dbFile = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(dbFile);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }

  public static Customer getCustomer(String username) {
    String sql = "select * from customers where username = ?";
    Customer customer = null;
    try (
        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, username);
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"),
              resultSet.getString("password"), resultSet.getInt("account_id"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return customer;
  }

  public static Account getAccount(int id) {
    String sql = "select * from accounts where id = ?";
    Account account = null;

    try (
        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          account = new Account(resultSet.getInt("id"), resultSet.getString("type"), resultSet.getDouble("balance"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return account;
  }

  public static void updateAccountBalance(Account account) {
    String sql = "update accounts set balance = ? where id = ?";

    try (
        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setDouble(1, account.getBalance());
      statement.setInt(2, account.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
