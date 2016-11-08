package service;

import dao.CarDAO;
import dao.DriverDAO;
import entity.Car;
import entity.Driver;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Set;

/**
 * Created by obalitskyi on 10/25/16.
 */
@Stateless
@Local
public class DriverService {

    private final static Logger LOG = Logger.getLogger(DriverService.class);

    @EJB
    private DriverDAO driverDAO;
    @EJB
    private CarDAO carDAO;

    public void save(String name, String surname, String salary, List<String> carsOfDriver){
        Driver driver = new Driver(name, surname, Integer.valueOf(salary));
        driver.setCars(getCarsByIds(carsOfDriver));
        LOG.info("saving driver ...");
        driverDAO.save(driver);
    }

    public void update(String id, String name, String surname, String salary, List<String> carsOfDriver){
        Driver driver = driverDAO.find(Long.valueOf(id));
        driver.setName(name);
        driver.setSurname(surname);
        driver.setSalary(Integer.valueOf(salary));
        driver.setCars(getCarsByIds(carsOfDriver));
        LOG.info("updating driver ...");
        driverDAO.update(driver);
    }

    public Set<Car> getAllCars(){
        return carDAO.findAll();
    }

    public Set<Car> getCarsByIds(List<String> ids){
        Set<Car> result;
        try {
            result = carDAO.getByIDs(ids);
        }catch (NumberFormatException nfe){
            throw new RuntimeException("id is not Long!", nfe);
        }catch (NullPointerException npe){
            throw new RuntimeException("there is no object with such id!", npe);
        }

        return result;
    }

    public Set<Driver> getAll(){
        return driverDAO.findAll();
    }

    public void delete(String id){
        try {
            Driver driverToDelete = driverDAO.find(Long.valueOf(id));
            for (Car car : driverToDelete.getCars()){
                car.getDrivers().remove(driverToDelete);
            }
            LOG.info("deleting driver ...");
            driverDAO.delete(driverToDelete);
        }catch (NumberFormatException nfe){
            throw new RuntimeException("id is not Long!", nfe);
        }catch (NullPointerException npe){
            throw new RuntimeException("there is no object with such id!", npe);
        }
    }
}
