package service;

import dao.CarDAO;
import dao.DriverDAO;
import entity.Car;
import entity.CarOwner;
import entity.Driver;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by obalitskyi on 10/25/16.
 */
@Stateless
@Local
public class CarService {

    private final static Logger LOG = Logger.getLogger(CarService.class);

    @EJB
    private CarDAO carDAO;
    @EJB
    private DriverDAO driverDAO;

    public void save(String brand, String number, String colour, String price, List<String> driversOfCarIds){
        Car car = new Car(brand, Integer.valueOf(number), colour, Integer.valueOf(price));
        car.setDrivers(getDriversByIds(driversOfCarIds));
        for (Driver driver : car.getDrivers()){
            driver.getCars().add(car);
        }
        LOG.info("saving car ...");
        carDAO.save(car);
    }
    public void update(String id, String brand, String number, String colour, String price, List<String> driversOfCarIds){
        Car car = carDAO.find(Long.valueOf(id));
        Set<Driver> drivers = getDriversByIds(driversOfCarIds);
        updateDriverRefs(car, drivers);

        car.setBrand(brand);
        car.setNumber(Integer.valueOf(number));
        car.setColour(colour);
        car.setPrice(Integer.valueOf(price));
        car.setDrivers(drivers);
        LOG.info("updating car ...");
        carDAO.update(car);
    }

    private void updateDriverRefs(Car car, Set<Driver> drivers){
        List<Driver> driversToRemoveCars = driverDAO.getThatAreNotInList(car, drivers);
        for (Driver driver : driversToRemoveCars){
            driver.getCars().remove(car);
        }
        for (Driver driver : drivers){
            driver.getCars().add(car);
        }
    }

    private List<Long> convertToLong(List<String> stringList){
        List<Long> res = new ArrayList<>();
        for (String s : stringList){
            res.add(Long.valueOf(s));
        }
        return res;
    }

    public Set<Car> getAll(){
        return carDAO.findAll();
    }

    public Set<Driver> getAllDrivers(){
        return driverDAO.findAll();
    }

    public Set<Driver> getDriversByIds(List<String> ids){
        Set<Driver> result;
        try {
            result = driverDAO.getByIDs(ids);
        }catch (NumberFormatException nfe){
            throw new RuntimeException("id is not Long!", nfe);
        }catch (NullPointerException npe){
            throw new RuntimeException("there is no object with such id!", npe);
        }

        return result;
    }

    public void delete(String id){
        try {
            Car carToDelete = carDAO.find(Long.valueOf(id));
            CarOwner carOwner = carToDelete.getCarOwner();
            if (carOwner != null){
                carOwner.getCars().remove(carToDelete);
            }
            for (Driver driver : carToDelete.getDrivers()){
                driver.getCars().remove(carToDelete);
            }
            LOG.info("deleting car ...");
            carDAO.delete(carToDelete);
        }catch (NumberFormatException nfe){
            throw new RuntimeException("id is not Long!", nfe);
        }catch (NullPointerException npe){
            throw new RuntimeException("there is no object with such id!", npe);
        }
    }

}
