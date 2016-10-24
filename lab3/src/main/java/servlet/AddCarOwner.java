package servlet;

import dao.CarDAO;
import dao.CarOwnerDAO;
import dao.DriverDAO;
import entity.Car;
import entity.CarOwner;
import entity.Driver;
import entity.SecureData;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by obalitskyi on 10/20/16.
 */
@WebServlet("/addCarOwner")
public class AddCarOwner extends HttpServlet {
    @EJB
    private CarDAO carDao;
    @EJB
    private CarOwnerDAO carOwnerDAO;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        SecureData secureData = null;
        if (userName != null && password != null){
            secureData = new SecureData(userName, password);
        }

        String carIDs = request.getParameter("carIDs");
        Set<Car> cars = null;
        if (carIDs != null){
            cars = (carDao.getByIDs(carIDs));
        }

        CarOwner carOwner = new CarOwner(name, surname, address);
        carOwner.setCars(cars);
        carOwner.setSecureData(secureData);

        try {
            carOwnerDAO.save(carOwner);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            getServletContext().getRequestDispatcher("/successful.jsp").forward(request, response);
        }
    }
}