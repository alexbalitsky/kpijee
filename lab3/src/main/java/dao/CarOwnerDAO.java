package dao;

import entity.CarOwner;

import javax.persistence.PersistenceContext;

/**
 * Created by alex on 17.10.16.
 */

public class CarOwnerDAO extends AbstractDAO<CarOwner> {

    public CarOwnerDAO() {
        super(CarOwner.class);
    }

    //TODO
}
