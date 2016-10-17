/*
package servlet;

import dao.AbstractDAO;
import dao.CarDAO;
import entity.Car;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/getCars")
public class GetCars extends HttpServlet {


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        AbstractDAO<Car> dao = new CarDAO();
        List<Car> cars = null;

        try {
            // connects to the database
            DataSource ds = (DataSource) new InitialContext().lookup("lab2jndi");
            conn = ds.getConnection();
            cars = dao.getRecords(conn);

        } catch (SQLException | NamingException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            // sets the message in request scope
            request.setAttribute("cars", cars);

            // forwards to the message page
            getServletContext().getRequestDispatcher("/getCars.jsp").forward(request, response);
        }
    }
}
*/
