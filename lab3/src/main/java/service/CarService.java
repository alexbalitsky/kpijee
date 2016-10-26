package service;

import dao.CarDAO;
import dao.DriverDAO;
import entity.Car;
import entity.Driver;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Set;

/**
 * Created by obalitskyi on 10/25/16.
 */
@Stateless
@Local
public class CarService {
    @EJB
    private CarDAO carDAO;
    @EJB
    private DriverDAO driverDAO;

    public void save(String brand, String number, String colour, String price, List<String> driversOfCar){
        Car car = new Car(brand, Integer.valueOf(number), colour, Integer.valueOf(price));
        car.setDrivers(getDriversByIds(driversOfCar));
        carDAO.save(car);
    }
    public void update(String id, String brand, String number, String colour, String price, List<String> driversOfCar){
        Car car = carDAO.find(Long.valueOf(id));
        car.setBrand(brand);
        car.setNumber(Integer.valueOf(number));
        car.setColour(colour);
        car.setPrice(Integer.valueOf(price));
        car.setDrivers(getDriversByIds(driversOfCar));
        carDAO.update(car);
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
            throw new RuntimeException("id is not Integer!", nfe);
        }catch (NullPointerException npe){
            throw new RuntimeException("there is no object with such id!", npe);
        }

        return result;
    }

}
