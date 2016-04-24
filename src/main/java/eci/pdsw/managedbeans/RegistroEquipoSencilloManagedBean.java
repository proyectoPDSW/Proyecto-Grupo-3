/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.managedbeans;

import eci.pdsw.entities.EquipoException;
import eci.pdsw.entities.EquipoSencillo;
import eci.pdsw.persistence.PersistenceException;
import eci.pdsw.servicios.ExcepcionServicios;
import eci.pdsw.servicios.ServiciosEquipoSencillo;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author Daniela Sepulveda
 */

@ManagedBean(name="RegistroEquipoSencillo")
@SessionScoped
public class RegistroEquipoSencilloManagedBean  implements Serializable {
    
    private final ServiciosEquipoSencillo SERVICIOS;
    
        private String nombre;
        private String clase;
        private long valorComercial;
        private int cantidadTotal;
        private String fotografia;

    private boolean showPanelRegistro=true;
    private boolean showPanelRegistrado=false;
    private EquipoSencillo equipoSencillo;
    
       
    public RegistroEquipoSencilloManagedBean(){
        SERVICIOS=ServiciosEquipoSencillo.getInstance();
    }
    
    public void registrarEquipo(){ 
        try {                  
                equipoSencillo=new EquipoSencillo(nombre, clase, valorComercial, cantidadTotal);
                equipoSencillo.setFotografia(fotografia);
                SERVICIOS.registrarEquipoSencillo(equipoSencillo);
                facesInfo("El equipo ha sido registrado satisfactoriamente");
                showPanelRegistro=false;
                showPanelRegistrado=true;
        } catch (ExcepcionServicios | EquipoException ex) {
                facesError(ex.getMessage());
        }
        
    }
    
    public void registrarOtroEquipo(){
        nombre="";
        clase="";
        fotografia="";
        showPanelRegistro=true;
        showPanelRegistrado=false;
        valorComercial=new Long("0");
        cantidadTotal=0;
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

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public EquipoSencillo getEquipoSencillo() {
        return equipoSencillo;
    }

    public void setEquipoSencillo(EquipoSencillo equipoSencillo) {
        this.equipoSencillo = equipoSencillo;
    }
    
    
    public boolean isShowPanelRegistro() {
        return showPanelRegistro;
    }

    public void setShowPanelRegistro(boolean showPanelRegistro) {
        this.showPanelRegistro = showPanelRegistro;
    }

    public boolean isShowPanelRegistrado() {
        return showPanelRegistrado;
    }

    public void setShowPanelRegistrado(boolean showPanelRegistrado) {
        this.showPanelRegistrado = showPanelRegistrado;
    }
    
    /**
     * Muestra un mensaje de error en la vista
     * @param message Mensaje de error
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: "+message, null));
    }
    
    /**
     * Muestra un mensaje de informacion en la vista
     * @param message Mensaje de informativo
     */
    public void facesInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
    /**
     * Muestra un mensaje de alarma en la vista
     * @param message Mensaje de Alarma
     */
    public void facesWarn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }
    
    /**
     * Muestra un mensaje de error grave en la vista
     * @param message Mensaje fatal
     */
    public void facesFatal(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, null));
    }
}
