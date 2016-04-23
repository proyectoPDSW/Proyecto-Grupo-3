/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.managedbeans;

import eci.pdsw.entities.EquipoSencillo;
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
        
    private EquipoSencillo equipoSencillo;
    
       
    public RegistroEquipoSencilloManagedBean(){
        SERVICIOS=ServiciosEquipoSencillo.getInstance();
    }
    
    public void registrarEquipo(){
        if(0==fotografia.length()){
            fotografia="http://deloresvan.com/wp-content/themes/nucleare-pro/images/no-image-box.png";
        }
        equipoSencillo=new EquipoSencillo(nombre, clase, valorComercial, cantidadTotal);
        try {
            SERVICIOS.registrarEquipoSencillo(equipoSencillo);
        } catch (ExcepcionServicios ex) {
            facesError("asdsa");
        }
    
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
}
