/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;

import clases.estaciones;
import dao.StationsFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author giiee
 */
@ManagedBean
@ViewScoped
public class estacionesControlador {

    /**
     * Creates a new instance of estacionesControlador
     */
    private List<estaciones> listaEstaciones;
    private estaciones estacion;
    @EJB
    private StationsFacade estacionEjb;

    public List<estaciones> getListaEstaciones() {
        return listaEstaciones;
    }

    public void setListaEstaciones(List<estaciones> listaEstaciones) {
        this.listaEstaciones = listaEstaciones;
    }

    public estaciones getEstacion() {
        return estacion;
    }

    public void setEstacion(estaciones estacion) {
        this.estacion = estacion;
    }
    
    

    @PostConstruct
    public void inicar() {
        listaEstaciones = estacionEjb.getByStations(1, 2);
        estacion=new estaciones();
        System.out.println(listaEstaciones.size());
    }

    public estacionesControlador() {
    }

}
