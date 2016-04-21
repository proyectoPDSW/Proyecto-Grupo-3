/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.managedbeans;

import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.Prestamo;
import eci.pdsw.servicios.ServiciosPrestamo;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2105684
 */
@ManagedBean(name="consultaMorosos")
@SessionScoped
public class ColsultaMorososManagedBean {
    private final ServiciosPrestamo sp=ServiciosPrestamo.getInstance();
    
    private List<Prestamo> morosos;

    /**
     * @return the morosos
     */
    public List<Prestamo> getMorosos() {
        morosos=sp.consultarPrestamosMorosos();
        return morosos;
    }
    
    public Date diffDate(Date d){
        return new Date(new Date().getTime()-d.getTime());
    }
    

    
}
