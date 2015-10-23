/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidad.Stations;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author giiee
 */
@Stateless
public class StationsFacade extends AbstractFacade<Stations> {
    @PersistenceContext(unitName = "Idea_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StationsFacade() {
        super(Stations.class);
    }
    
}
