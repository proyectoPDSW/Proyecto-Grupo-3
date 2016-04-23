/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.managedbeans;

import eci.pdsw.entities.Modelo;
import eci.pdsw.persistence.PersistenceException;
import eci.pdsw.servicios.ExcepcionServicios;
import eci.pdsw.servicios.ServiciosEquipoComplejo;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Daniela Sepulveda
 */

@ManagedBean(name="RegistroEquiposComplejos")
@SessionScoped

public class RegistroEquipoComplejoManagedBean implements Serializable{
    
    private final ServiciosEquipoComplejo servicios;
        
    private String nombreModelo;
    
    private Modelo modelo;

        private int vidaUtil;
        private String nombre;
        private String clase;
        private long valorComercial;
        private String fotografia;
        private String descripcion;
        private String accesorios;
    

    private boolean showPanelConsultaModelo = false;
    private boolean showPanelRegistroModelo = false;
    
  
    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: "+message, null));
    }
    
    public void facesInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
     
    public void facesWarn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }
    
    public void facesFatal(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, null));
    }
    
    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
    
    public String getNombreModelo() {
        return nombreModelo;
    }
    public Modelo getModelo() {
        return modelo;
    }
    public boolean showPanelConsulta(){
        return showPanelConsultaModelo;
    }
    
    public boolean showPanelRegistro(){
        return showPanelRegistroModelo;
    }
    
    public RegistroEquipoComplejoManagedBean() {
        servicios=ServiciosEquipoComplejo.getInstance();
        
    }
    
    public void agregarModelo(){
        showPanelConsultaModelo=false;
        showPanelRegistroModelo=true;
    }
    public void consultarModelo(){
        try {
            modelo=servicios.consultarModelo(nombreModelo);
            showPanelRegistroModelo=false;
            showPanelConsultaModelo=true;
        } catch (ExcepcionServicios ex) {
            facesError("No se ha encontrado el modelo con nombre "+nombreModelo);
            Logger.getLogger(RegistroEquipoComplejoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void registrarModelo() throws PersistenceException{
        try {
            showPanelConsultaModelo=false;
            showPanelRegistroModelo=true;
            modelo=new Modelo(vidaUtil,nombre,fotografia,clase,valorComercial);
            if(!(descripcion==null || descripcion.length()==0)){
                modelo.setDescripcion(descripcion);
            }
            if(!(accesorios==null || accesorios.length()==0)){
                modelo.setAccesorios(accesorios);
            }
            servicios.registrarModelo(modelo);
        } catch (ExcepcionServicios ex) {
            facesError("Los datos no son correctos");
            Logger.getLogger(RegistroEquipoComplejoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    public int getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public long getValorComercial() {
        return valorComercial;
    }

    public void setValorComercial(long valorComercial) {
        this.valorComercial = valorComercial;
    }


    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(String accesorios) {
        this.accesorios = accesorios;
    }
}
