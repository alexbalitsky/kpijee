package dao;

import entity.SecureData;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alex on 17.10.16.
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SecureDataDAO {
    public SecureDataDAO() {
    }

    @PersistenceContext(unitName = "DEVMODE")
    private EntityManager em;

    @Resource
    private UserTransaction tx;

    public void save(SecureDataDAO entity) {
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
        }catch (Exception e){
            try {
                tx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }

    }

    public void delete(SecureDataDAO entity) {
        try {
            tx.begin();
            SecureDataDAO entityToBeRemoved = em.merge(entity);
            em.remove(entityToBeRemoved);
            tx.commit();
        }catch (Exception e) {
            try {
                tx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
    }

    public SecureDataDAO update(SecureDataDAO entity) {
        SecureDataDAO secureDataDAO = null;
        try {
            tx.begin();
            secureDataDAO = em.merge(entity);
            tx.commit();
        }catch (Exception e) {
            try {
                tx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
        return secureDataDAO;
    }

    public SecureDataDAO find(Long entityID) {
        SecureDataDAO secureDataDAO = null;
        try {
            tx.begin();
            secureDataDAO = em.find(SecureDataDAO.class, entityID);
            tx.commit();
        }catch (Exception e) {
            try {
                tx.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
        return secureDataDAO;
    }
}
