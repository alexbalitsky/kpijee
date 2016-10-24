package servlet;

import dao.CarOwnerDAO;
import dao.DriverDAO;
import entity.CarOwner;
import entity.Driver;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by obalitskyi on 10/20/16.
 */
@WebServlet("/getDrivers")
public class GetDrivers extends HttpServlet {
    @EJB
    private DriverDAO driverDAO;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Set<Driver> drivers = new HashSet<Driver>();

        try {
            // connects to the database
            drivers.addAll(driverDAO.findAll());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // sets the message in request scope
            request.setAttribute("drivers", drivers);

            // forwards to the message page
            getServletContext().getRequestDispatcher("/getDrivers.jsp").forward(request, response);
        }
    }
}