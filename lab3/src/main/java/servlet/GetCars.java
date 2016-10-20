package servlet;

import dao.AbstractDAO;
import dao.CarDAO;
import entity.Car;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
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
    @EJB
    private CarDAO carDAO;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Set<Car> cars = new HashSet<Car>();

        try {
            // connects to the database
            cars.addAll(carDAO.findAll());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // sets the message in request scope
            request.setAttribute("cars", cars);

            // forwards to the message page
            getServletContext().getRequestDispatcher("/getCars.jsp").forward(request, response);
        }
    }
}
