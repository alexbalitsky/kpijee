package service;

import dao.CarDAO;
import entity.Car;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by obalitskyi on 10/25/16.
 */
@Stateless
@Local
public class CarService {
    @EJB
    private CarDAO carDAO;

    public void save(String brand, String number, String colour, String price){
        Car car = new Car(brand, Integer.valueOf(number), colour, Integer.valueOf(price));
        carDAO.save(car);
    }


}
