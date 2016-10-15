import dao.AbstractCRUD;
import dao.CarCRUD;
import model.Car;
import org.junit.*;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by obalitskyi on 10/3/16.
 */
public class CRUDTest {
    private static Connection connection = null;
    static AbstractCRUD<Car> dao = null;

    private static String host = "localhost";
    private static Integer port = 3306;
    private static String user = "root";
    private static String password = "root";
    private static String DBName = "cars";
    private static String tableName = "cars";

    @BeforeClass
    public static void init() throws SQLException {
        connection = JDBCConnectionFactory.getConnection(host, port, DBName, user, password);
        assert connection != null;
        dao = new CarCRUD(DBName, tableName);
        dao.createDB(connection);
    }

    @Before
    public void setup() throws SQLException {
        dao.dropTable(connection);
        dao.createTable(connection);
    }

    @AfterClass
    public static void cleanup() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void addCarTest() throws SQLException {
        Car car = new Car("mercedes", 1111, "black", 100000);
        dao.addRecord(car, connection);
        List<Car> cars = dao.getRecords(connection);
        assertNotNull(cars);
        assertEquals("Must be one car in table!", 1, cars.size());
        assertEquals("Must be black colour!", "black", cars.get(0).getColour());
    }

    @Test
    public void updateCarTest() throws SQLException {
        addCarTest();
        List<Car> cars = dao.getRecords(connection);
        Car car = cars.get(0);

        assertEquals("The price must be 100000!", 100000, (int) car.getPrice());
        assertEquals("The number must be 1111!", 1111, (int) car.getNumber());

        car.setPrice(0);
        car.setNumber(2222);
        dao.updateRecord(car, connection);
        cars = dao.getRecords(connection);
        car = cars.get(0);

        assertEquals("The price must be 0!", 0, (int) car.getPrice());
        assertEquals("The number must be 2222!", 2222, (int) car.getNumber());
    }

    @Test
    public void deleteCarTest() throws SQLException {
        addCarTest();
        List<Car> cars = dao.getRecords(connection);
        dao.deleteRecord(cars.get(0), connection);
        cars = dao.getRecords(connection);

        assertEquals("Must be zero cars in table!", 0, cars.size());
    }
}
