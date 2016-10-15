import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by obalitskyi on 10/3/16.
 * class to get JDBC connection
 */
public class JDBCConnectionFactory {

    public static Connection getConnection(String host, Integer port, String db, String user, String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found");
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, password);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (connection != null) {
            return connection;
        } else {
            System.out.println("Failed to make connection!");
            return null;
        }
    }
}
