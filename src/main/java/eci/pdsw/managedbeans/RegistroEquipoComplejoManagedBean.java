/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.managedbeans;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.BlobFromLocator;
import eci.pdsw.entities.Modelo;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
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
        
    private String nombreModelo;
    private Modelo modelo;

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
        modelo=new Modelo(55, "model",null, "asda", new Long("54654"));
        if(!nombreModelo.equals(modelo.getNombre())){
           facesError("No se ha encontrado el modelo");
        }
    }
    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }
}
