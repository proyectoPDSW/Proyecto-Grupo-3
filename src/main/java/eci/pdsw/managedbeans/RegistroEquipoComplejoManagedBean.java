/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.managedbeans;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.BlobFromLocator;
import eci.pdsw.entities.Modelo;
import eci.pdsw.servicios.ExcepcionServicios;
import eci.pdsw.servicios.ServiciosEquipoComplejo;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author 
 */

@ManagedBean(name="RegistroEquiposComplejos")
@SessionScoped

public class RegistroEquipoComplejoManagedBean implements Serializable{
    
    private final ServiciosEquipoComplejo servicios=ServiciosEquipoComplejo.getInstance();
        
    private String nombreModelo;
    private Modelo modelo;
     

    private boolean showPassword = true;
    
    public Blob getFotografia() {
        return modelo.getFotografia();
    }

    public Modelo getModelo() {
        return modelo;
    }
    
    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
    
    public String getNombreModelo() {
        return nombreModelo;
    }
    
    public void consultarModelo(){
        try {
            servicios.consultarPorModelo(nombreModelo);
        } catch (ExcepcionServicios ex) {
            facesError("");
            Logger.getLogger(RegistroEquipoComplejoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void registrarModelo(){
        
    }
     
    public boolean isShowPassword(){
        return showPassword;
    }
    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }
}
