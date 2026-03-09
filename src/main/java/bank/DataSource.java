package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
  public static Connection connect() {
    String dbFile = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(dbFile);
      System.out.println("We're connected\r\n---------------");
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }

  public static void main(String[] args) {
    connect();
  }
}
