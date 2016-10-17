package dao;

import interceptor.TxInterceptorBinding;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by obalitskyi on 10/4/16.
 */

@TxInterceptorBinding
public abstract class AbstractDAO<T> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        em.persist(entity);
    }

    public void delete(T entity) {
        T entityToBeRemoved = em.merge(entity);
        em.remove(entityToBeRemoved);

    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public T find(int entityID) {
        return em.find(entityClass, entityID);
    }

    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
}
