import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.testng.annotations.BeforeClass;

public class Practice_JDBC {

  Connection connection ;
  @BeforeClass
  public void setupConnection(){

    String url= "jdbc:mysql://db-technostudy.ckr1jisflxpv.us-east-1.rds.amazonaws.com:3306/yoldas";
    String username = "root";
    String password = "'\"-LhCB'.%k[4S]z";

    try {
      connection = DriverManager.getConnection(url,username,password);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
