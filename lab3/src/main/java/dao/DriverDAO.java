package dao;

import entity.Driver;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by alex on 17.10.16.
 */
@Stateless
@Local
public class DriverDAO extends AbstractDAO<Driver>{
    public DriverDAO() {
        super(Driver.class);
    }

}
