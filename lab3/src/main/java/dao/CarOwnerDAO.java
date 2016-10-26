package dao;

import entity.CarOwner;
import entity.SecureData;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import java.util.Set;

/**
 * Created by alex on 17.10.16.
 */
@Stateless
@Local
public class CarOwnerDAO extends AbstractDAO<CarOwner> {

    public CarOwnerDAO() {
        super(CarOwner.class);
    }

    //TODO
}
