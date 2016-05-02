/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.managedbeans;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoException;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosEquipoComplejo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;

/**
 *
 * @author Daniela Sepulveda
 */

@ManagedBean(name="RegistroEquiposComplejos")
@SessionScoped

public class RegistroEquipoComplejoManagedBean implements Serializable{
    
    private final ServiciosEquipoComplejo SERVICIOS;
    
    @Size(min=1,max=50, message="Es un campo requerido*")
    private String nombreModelo;
    
    private Modelo modelo;

        private int vidaUtil;
        private String nombre;
        private String clase;
        private long valorComercial;
        private String fotografia;
        private String descripcion;
        private String accesorios;
    
    private EquipoComplejo equipo;
    
        private boolean asegurado;
        private boolean disponibilidad;
        private String estado;
        private String serial;
        private int placa;
        private String marca;
    
    private String aseguradoEquipo="";
    private String disponibilidadEquipo="";

    private boolean showPanelConsultaModelo = false;
    private boolean showPanelRegistroModelo = false;
    private boolean showPanelRegistroExitoso = false;
     
    public RegistroEquipoComplejoManagedBean() {
        SERVICIOS=ServiciosEquipoComplejo.getInstance();    
    }
    
    public void limpiar(){
       vidaUtil=0;
       nombre="";
       clase="";
       valorComercial=0;
       fotografia="";
       descripcion="";
       accesorios="";
       serial="";
       placa=0;
       marca="";
            
    }
    public boolean showPanelRegistroExitoso(){
        if(equipo!=null && equipo.getAsegurado()){
            aseguradoEquipo="Si";
        }else{
            aseguradoEquipo="No";
        }
        if(equipo!=null && equipo.getDisponibilidad()){
            disponibilidadEquipo="Si";
        }else{
            disponibilidadEquipo="No";
        }
        return showPanelRegistroExitoso;
    }
    public boolean showPanelConsulta(){
        return showPanelConsultaModelo;
    }
    
    public boolean showPanelRegistro(){
        return showPanelRegistroModelo;
    }
   
    public void consultarModelo(){
        try {
            modelo=SERVICIOS.consultarModelo(nombreModelo);
            showPanelRegistroModelo=false;
            showPanelConsultaModelo=true;
            showPanelRegistroExitoso = false;
            limpiar();
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
        }
        
    }
    
    public void registrarEquipo(){
        try {
            equipo=new EquipoComplejo(modelo, marca, serial,placa);
            equipo.setAsegurado(asegurado);
            equipo.setMarca(marca);
            equipo.setPlaca(placa);
            equipo.setDisponibilidad(true);
            equipo.setEstado("Activo");
            SERVICIOS.registrarEquipoComplejo(equipo);
            showPanelConsultaModelo=false;
            showPanelRegistroModelo=false;
            showPanelRegistroExitoso=true;
            facesInfo("El equipo ha sido registrado satisfactoriamente");
        } catch (ExcepcionServicios | EquipoException ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
        }
    }
    
    public void agregarModelo(){
        showPanelRegistroModelo=true;
        showPanelConsultaModelo=false;
        showPanelRegistroExitoso = false;
    }
  
    public void registrarEquipoModelo(){
        try {
            modelo=new Modelo(vidaUtil,nombre,fotografia,clase,valorComercial);
            modelo.setDescripcion(descripcion);
            modelo.setAccesorios(accesorios);
            //SERVICIOS.registrarModelo(modelo);
            equipo=new EquipoComplejo(modelo, marca, serial,placa);
            equipo.setModelo_Eq(modelo);
            equipo.setAsegurado(asegurado);
            equipo.setPlaca(placa);
            equipo.setMarca(marca);
            equipo.setDisponibilidad(true);
            equipo.setEstado("Activo");
            SERVICIOS.registrarEquipoComplejo(equipo);
            showPanelConsultaModelo=false;
            showPanelRegistroModelo=false;
            showPanelRegistroExitoso=true;
            facesInfo("El equipo ha sido registrado satisfactoriamente");
        } catch (ExcepcionServicios | EquipoException ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
        }
    }  
    
    
    public List<Modelo> getModelosAproximados(){
        try{
            return SERVICIOS.consultarAproximado(nombreModelo);
        }catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
            return new ArrayList<>();
        }
    }
    //////////////////////informacion Modelo
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
    
    public Modelo getModelo() {
        return modelo;
    }
    //////////////////informacion consulta
    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
    
    public String getNombreModelo() {
        return nombreModelo;
    }
/////////////////////////////////////////////////informacion Equipo
    public EquipoComplejo getEquipo() {
        return equipo;
    }
    
    public boolean isAsegurado() {
        return asegurado;
    }

    public void setAsegurado(boolean asegurado) {
        this.asegurado = asegurado;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
////////////////////////////////datos registro exitoso
    public String getAseguradoEquipo() {
        return aseguradoEquipo;
    }

    public void setAseguradoEquipo(String aseguradoEquipo) {
        this.aseguradoEquipo = aseguradoEquipo;
    }

    public String getDisponibilidadEquipo() {
        return disponibilidadEquipo;
    }

    public void setDisponibilidadEquipo(String disponibilidadEquipo) {
        this.disponibilidadEquipo = disponibilidadEquipo;
    }

}


