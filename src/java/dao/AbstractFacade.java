/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import clases.estaciones;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author giiee
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public List<estaciones> getByStations(int latitude, int longitude) {
        try {
            List<estaciones> listaEstaciones=new ArrayList<estaciones>();
            Query q = getEntityManager().createNativeQuery("SELECT \n"
                    + "	variable_id, name_variable\n"
                    + "FROM\n"
                    + "	stations\n"
                    + "NATURAL JOIN\n"
                    + "	samples\n"
                    + "NATURAL JOIN\n"
                    + "	variables\n"
                    + "WHERE\n"
                    + "	station_id=1");
            //q.setParameter(1, latitude);
            //q.setParameter(2, longitude);
            List<Object[]>lista=q.getResultList();
           
            for ( Object[] obj :lista) {
              
                estaciones est=new estaciones();
                est.setName_variable(obj[1].toString());
                est.setVariable_id(obj[0].toString());
                listaEstaciones.add(est);
            }
            return listaEstaciones ;
        } catch (Exception e) {
            return null;
        }
    }
    
}
