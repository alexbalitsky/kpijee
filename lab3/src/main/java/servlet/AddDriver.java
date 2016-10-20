package servlet;

import dao.CarDAO;
import dao.CarOwnerDAO;
import dao.DriverDAO;
import entity.Car;
import entity.CarOwner;
import entity.Driver;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by obalitskyi on 10/20/16.
 */
@WebServlet("/addDriver")
public class AddDriver extends HttpServlet {
    @EJB
    private CarDAO carDao;
    @EJB
    private DriverDAO driverDAO;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Integer salary = getOrEmpty(request.getParameter("salary"));

        String carIDs = request.getParameter("carIDs");
        Set<Car> cars = null;
        if (carIDs != null){
            cars = driverDAO.getByIDs(carIDs);
        }

        Driver driver = new Driver(name, surname, salary);
        driver.setCars(cars);

        try {
            driverDAO.save(driver);

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