package dao;

import entity.SecureData;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

/**
 * Created by alex on 17.10.16.
 */
@Stateless
@Local
public class SecureDataDAO extends AbstractDAO<SecureData> {
    public SecureDataDAO() {
        super(SecureData.class);
    }

    //TODO
}
