/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.managedbeans;

import edu.eci.pdsw.entities.Prestamo;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosPersona;
import edu.eci.pdsw.servicios.ServiciosPrestamo;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Julian Devia
 */
@ManagedBean(name = "consultaMorosos")
@RequestScoped
public class ConsultaMorososManagedBean implements Serializable {

    private final ServiciosPrestamo sp = ServiciosPrestamo.getInstance();
    private final ServiciosPersona sper=ServiciosPersona.getInstance();

    public List<Prestamo> morosos;

    /**
     * Consulta los prestamos en mora
     *
     * @return the morosos
     */
    public List<Prestamo> getMorosos() {
        if (morosos == null || morosos.isEmpty()) {
            morosos = null;
            try {
                morosos = sp.consultarPrestamosMorosos();
            } catch (ExcepcionServicios ex) {
                Registro.anotar(ex);
                facesFatal("Ups! ha ocurrido un error inesperado");
            }
        }
        return morosos;
    }

    /**
     * Calcula la diferencia en horas entre la fecha actual y la fecha de fin estimada del prestamo
     * 
     * @param prestamo el prestamo que se usará para consultar
     * @return la diferencia en horas entre la fecha actual y la fecha de fin estimada del prestamo
     */
    public long diffHoras(Prestamo prestamo) {
        long diff=0;
        
        try {
            diff=sp.diffHours(prestamo);
        } catch (ExcepcionServicios ex) {
            Registro.anotar(ex);
            facesFatal("Ups! ha ocurrido un error inesperado");
        }
        return diff;
    }

    /**
     * Halla la fecha actual
     *
     * @return
     */
    public Timestamp currDate() {
        Timestamp now=null;
        try {
            now= sp.currDate();
        } catch (ExcepcionServicios ex) {
            Registro.anotar(ex);
            facesFatal("Ups! ha ocurrido un error inesperado");
        }
        return now;
    }
    
    public int cantMoras(String carnet){
        int moras=0;
        try{
            moras=sper.calcMoras(carnet);
        }catch(ExcepcionServicios e){
            Registro.anotar(e);
            facesFatal("Ups! ha ocurrido un error inesperado");
        }
        return moras;
    }
    
    /**
     * Muestra un mensaje de error en la vista
     *
     * @param message Mensaje de error
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + message, null));
    }

    /**
     * Muestra un mensaje de informacion en la vista
     *
     * @param message Mensaje de informativo
     */
    public void facesInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    /**
     * Muestra un mensaje de alarma en la vista
     *
     * @param message Mensaje de Alarma
     */
    public void facesWarn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }

    /**
     * Muestra un mensaje de error grave en la vista
     *
     * @param message Mensaje fatal
     */
    public void facesFatal(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, null));
    }
}
