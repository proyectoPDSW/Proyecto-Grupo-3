/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.managedbeans;

import com.mysql.jdbc.Blob;
import eci.pdsw.entities.Modelo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 
 */

@ManagedBean(name="RegistroEquiposComplejos")
@SessionScoped

public class RegistroEquipoComplejoManagedBean {
        
    private String nombreModelo;
    private Modelo modelo;

    public Blob getFotografia() {
        return modelo.getFotografia();
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
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
    
    public Modelo consultarModelo(){
        return modelo;
    }
    
}
