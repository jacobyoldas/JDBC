import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.RandomAccess;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JDBC_Test {

  Connection connection;

  @BeforeClass
  public void setupConnection() {
    //JDBC URL
    String url = "jdbc:mysql://db-technostudy.ckr1jisflxpv.us-east-1.rds.amazonaws.com:3306/classicmodels";
    String username = "root";
    String password = "'\"-LhCB'.%k[4S]z";

    try {
      connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  @Test
  public void test() {
    try {
      Statement statement = connection.createStatement();
      ResultSet allCustomers = statement.executeQuery("SELECT * FROM customers");
      allCustomers.next(); // cursor is on the first row

      System.out.println(allCustomers.getString("customerName"));
      System.out.println(allCustomers.getString("phone"));
      System.out.println(allCustomers.getString("country"));
      System.out.println();

      allCustomers.next();
      System.out.println(allCustomers.getString("customerName"));
      System.out.println(allCustomers.getString("phone"));
      System.out.println(allCustomers.getString("country"));

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void test2() throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet allCustomers = statement.executeQuery("select * from products ");
    allCustomers.next(); // cursor is on the first row

    System.out.println(allCustomers.getString("productName"));
    allCustomers.next();
    System.out.println(allCustomers.getString("productName"));
    allCustomers.next();
    System.out.println(allCustomers.getString("productName"));

  }

  @Test
  public void test3() throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet allEmployees = statement.executeQuery("select * from employees ");

    while (allEmployees.next()){
      System.out.print(allEmployees.getString("firstName")+" ");
      System.out.println(allEmployees.getString("jobTitle"));
      System.out.println("******************");
    }
  }

  @Test
  public void test4() throws SQLException {
    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
    ResultSet allCustomers = statement.executeQuery("select * from customers ");

    allCustomers.absolute(25); // cursor on row 25th
    System.out.println(allCustomers.getString("customerName"));
    System.out.println(allCustomers.getString("country"));

    System.out.println("************");

    allCustomers.absolute(10);
    System.out.println(allCustomers.getString("customerName"));
    System.out.println(allCustomers.getString("country"));

    System.out.println("************");

    allCustomers.last(); //cursor on the last row
    System.out.println(allCustomers.getString("customerName"));
    System.out.println(allCustomers.getString("country"));

    System.out.println("************");

    allCustomers.first();
    System.out.println(allCustomers.getString("customerName"));
    System.out.println(allCustomers.getString("country"));

    System.out.println("************");

    allCustomers.previous();
    System.out.println(allCustomers.getString("customerName"));
    System.out.println(allCustomers.getString("country"));

  }

    @AfterClass
  public void closeConnection() throws SQLException {
    connection.close();

  }
}
