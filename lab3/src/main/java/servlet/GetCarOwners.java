package servlet;

import dao.CarDAO;
import dao.CarOwnerDAO;
import entity.Car;
import entity.CarOwner;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by obalitskyi on 10/20/16.
 */

@WebServlet("/getCarOwners")
public class GetCarOwners extends HttpServlet {
    @EJB
    private CarOwnerDAO carOwnerDAO;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Set<CarOwner> carOwners = new HashSet<CarOwner>();

        try {
            // connects to the database
            carOwners.addAll(carOwnerDAO.findAll());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // sets the message in request scope
            request.setAttribute("carOwners", carOwners);

            // forwards to the message page
            getServletContext().getRequestDispatcher("/getCarOwners.jsp").forward(request, response);
        }
    }
}