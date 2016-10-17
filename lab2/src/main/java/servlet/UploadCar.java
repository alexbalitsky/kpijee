package servlet;

import dao.AbstractDAO;
import dao.CarDAO;
import entity.Car;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by obalitskyi on 10/6/16.
 */
@WebServlet("/uploadCar")
public class UploadCar extends HttpServlet {

    private String DBName = "cars";
    private String tablename = "cars";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Connection conn = null;

        AbstractDAO<Car> dao = new CarDAO(DBName, tablename);
        String brand = request.getParameter("brand");
        Integer number = Integer.valueOf(request.getParameter("number"));
        String colour = request.getParameter("colour");
        Integer price = Integer.valueOf(request.getParameter("price"));
        Car car = new Car(brand,number,colour,price);

        try {
            DataSource ds = (DataSource) new InitialContext().lookup("lab2jndi");
            conn = ds.getConnection();
            dao.addRecord(car, conn);

        } catch (SQLException | NamingException | NumberFormatException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            getServletContext().getRequestDispatcher("/successful.jsp").forward(request, response);
        }
    }
}
