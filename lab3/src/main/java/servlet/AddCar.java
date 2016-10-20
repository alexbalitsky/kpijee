package servlet;

import dao.AbstractDAO;
import dao.CarDAO;
import dao.CarOwnerDAO;
import dao.DriverDAO;
import entity.Car;
import entity.CarOwner;
import entity.Driver;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by obalitskyi on 10/6/16.
 */
@WebServlet("/addCar")
public class AddCar extends HttpServlet {
    @EJB
    private CarDAO carDao;
    @EJB
    private DriverDAO driverDAO;
    @EJB
    private CarOwnerDAO carOwnerDAO;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        String brand = request.getParameter("brand");
        Integer number = getOrEmpty(request.getParameter("number"));
        String colour = request.getParameter("colour");
        Integer price = getOrEmpty(request.getParameter("price"));
        String driverIds = request.getParameter("driverIds");
        Integer carOwnerId = getOrEmpty(request.getParameter("carOwnerId"));
        List<Driver> drivers = new ArrayList<Driver>();
        if (driverIds != null){
            drivers = driverDAO.getByIDs(driverIds);
        }
        CarOwner carOwner = null;
        if (carOwnerId != null){
            carOwner = carOwnerDAO.find(carOwnerId);
        }

        Car car = new Car(brand,number,colour,price);
        car.setDrivers(drivers);
        car.setCarOwner(carOwner);


        try {
            carDao.save(car);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            getServletContext().getRequestDispatcher("/successful.jsp").forward(request, response);
        }
    }

    private Integer getOrEmpty(String val){
        try {
            return Integer.valueOf(val);
        }catch (NumberFormatException e){
            return null;
        }
    }
}
