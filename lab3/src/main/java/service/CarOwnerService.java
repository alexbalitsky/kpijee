package service;

import dao.CarDAO;
import dao.CarOwnerDAO;
import entity.Car;
import entity.CarOwner;
import entity.SecureData;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by obalitskyi on 10/25/16.
 */
@Stateless
@Local
public class CarOwnerService {

    private final static Logger LOG = Logger.getLogger(CarOwnerService.class);

    @EJB
    private CarOwnerDAO carOwnerDAO;
    @EJB
    private CarDAO carDAO;

    public void save(String name, String surname, String address, String userName, String password, List<String> ownerCarsIds){
        CarOwner carOwner = new CarOwner(name, surname, address);
        SecureData secureData = new SecureData(userName, password);
        carOwner.setSecureData(secureData);
        carOwner.setCars(getCarsByIds(ownerCarsIds));
        LOG.info("saving car owner...");
        carOwnerDAO.save(carOwner);
    }

    public void update(String id, String name, String surname, String address, String userName, String password, List<String> ownerCarsIds){
        CarOwner carOwner = carOwnerDAO.find(Long.valueOf(id));
        Set<Car> cars = getCarsByIds(ownerCarsIds);
        for (Car car : carOwner.getCars()){
            if (!cars.contains(car)){
                car.setCarOwner(null);
                carDAO.update(car);
            }
        }
        carOwner.setName(name);
        carOwner.setSurname(surname);
        carOwner.setAddress(address);
        SecureData secureData = carOwner.getSecureData();
        secureData.setUsername(userName);
        secureData.setPassword(password);
        carOwner.setSecureData(secureData);
        carOwner.setCars(cars);
        LOG.info("updating car owner...");
        carOwnerDAO.update(carOwner);
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

    public Set<Car> getCarsWithoutOwners(){
        return new HashSet<>(carDAO.getCarsWithoutOwners());
    }

    public Set<Car> getCarsWithoutOwners(String carOwnerId){
        Set<Car> result;
        try {
            CarOwner carOwner = carOwnerDAO.find(Long.valueOf(carOwnerId));
            result = new HashSet<>(carDAO.getCarsWithoutOwners(carOwner));
        }
        catch (NumberFormatException nfe){
            throw new RuntimeException("id is not Long!", nfe);
        }catch (NullPointerException npe){
            throw new RuntimeException("there is no object with such id!", npe);
        }
        return result;
    }

    public Set<CarOwner> getAll(){
        return carOwnerDAO.findAll();
    }

    public void delete(String id){
        try {
            CarOwner carOwnerToDelete = carOwnerDAO.find(Long.valueOf(id));
            for (Car car : carOwnerToDelete.getCars()){
                car.setCarOwner(null);
            }
            LOG.info("deleting car owner...");
            carOwnerDAO.delete(carOwnerToDelete);
        }catch (NumberFormatException nfe){
            throw new RuntimeException("id is not Long!", nfe);
        }catch (NullPointerException npe){
            throw new RuntimeException("there is no object with such id!", npe);
        }
    }

}
