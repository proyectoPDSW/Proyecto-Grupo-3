/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.managedbeans;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Prestamo;
import edu.eci.pdsw.entities.PrestamoIndefinido;
import edu.eci.pdsw.entities.PrestamoTerminoFijo;
import edu.eci.pdsw.persistence.DAOPersona;
import edu.eci.pdsw.persistence.PersistenceException;
import edu.eci.pdsw.persistence.mybatis.MyBatisDAOPersona;
import edu.eci.pdsw.persistence.mybatis.mappers.PersonaMapper;
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosPrestamo;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author 2105403
 */
@ManagedBean(name="RegistroPrestamo")
@SessionScoped
public class RegistroPrestamoManageBean implements Serializable{
    private final ServiciosPrestamo PRESTAMO;
    
    private Timestamp fechaInicio;
    private Timestamp fechaEstimadaDeEntrega;
    private Timestamp fechaRealEntregada;
    
    private String carne;

    
    private Set<EquipoComplejo> equiposComplejosPrestados;
    private List<EquipoComplejo> equiposComplejosFaltantes;
    private Set<EquipoSencillo> equiposSencillosPrestados;
    private List<EquipoSencillo> equiposSencillosFaltantes2;
    private List<Integer> equiposSencillosPrestadosCantidad2;
    private Persona elQuePideElPrestamo;
    private int tipo_prestamo;
    
    private boolean showPanelRegistro=false;
    private boolean showPanelRegistrado=false;
    private boolean showPanelPersona=true;
    
    private Prestamo prestamo;
    
    public RegistroPrestamoManageBean(){
        
        PRESTAMO=ServiciosPrestamo.getInstance();
    }
    
    public void consultarPersona(){
        try {
            elQuePideElPrestamo=PRESTAMO.personaCarne(carne);
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
            
        }
    }
    
    /**
     * Registra un prestamo dependiendo de la persona que haga el prestamo se 
     * obtiene el tipo del prestamo si es indefinido o termino fijo
     */
    public void registrarPrestamo(){
        showPanelPersona=false;
        try{
            if(elQuePideElPrestamo.prioridad().get(0).getRol().equals("Estudiante")){
                prestamo=new PrestamoTerminoFijo(elQuePideElPrestamo,equiposComplejosPrestados,equiposSencillosPrestados,fechaEstimadaDeEntrega,EquipoComplejo.diario);
                PRESTAMO.registrarPrestamo(prestamo);
                facesInfo("El prestamo ha sido registrado satisfactoriamente");
                showPanelRegistro=false;
                showPanelRegistrado=true;
            }
            else if(getElQuePideElPrestamo().prioridad().get(-1).getRol().equals("Laboratorista") || getElQuePideElPrestamo().prioridad().get(-1).getRol().equals("Profesor")){
                setPrestamo(new PrestamoIndefinido(elQuePideElPrestamo, equiposComplejosPrestados, equiposSencillosPrestados));
                PRESTAMO.registrarPrestamo(prestamo);
                facesInfo("El prestamo ha sido registrado satisfactoriamente");
                showPanelRegistro=false;
                showPanelRegistrado=true;
            }
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
        }
    }
    /**
     * Reinicia todas las variables para realizar otro prestamo
     */
    public void registrarOtroPrestamo(){
        setFechaInicio(null);
        setFechaEstimadaDeEntrega(null);
        setFechaRealEntregada(null);

    
        setEquiposComplejosPrestados(null);
        setEquiposComplejosFaltantes(null);
        setEquiposSencillosPrestados(null);
        setEquiposSencillosFaltantes2(null);
        setEquiposSencillosPrestadosCantidad2(null);
        setElQuePideElPrestamo(null);
        setTipo_prestamo(0);
        showPanelRegistro=true;
        showPanelRegistrado=false;
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

    /**
     * @return the fechaInicio
     */
    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaEstimadaDeEntrega
     */
    public Timestamp getFechaEstimadaDeEntrega() {
        return fechaEstimadaDeEntrega;
    }

    /**
     * @param fechaEstimadaDeEntrega the fechaEstimadaDeEntrega to set
     */
    public void setFechaEstimadaDeEntrega(Timestamp fechaEstimadaDeEntrega) {
        this.fechaEstimadaDeEntrega = fechaEstimadaDeEntrega;
    }

    /**
     * @return the fechaRealEntregada
     */
    public Timestamp getFechaRealEntregada() {
        return fechaRealEntregada;
    }

    /**
     * @param fechaRealEntregada the fechaRealEntregada to set
     */
    public void setFechaRealEntregada(Timestamp fechaRealEntregada) {
        this.fechaRealEntregada = fechaRealEntregada;
    }

    /**
     * @return the equiposComplejosPrestados
     */
    public Set<EquipoComplejo> getEquiposComplejosPrestados() {
        return equiposComplejosPrestados;
    }

    /**
     * @param equiposComplejosPrestados the equiposComplejosPrestados to set
     */
    public void setEquiposComplejosPrestados(Set<EquipoComplejo> equiposComplejosPrestados) {
        this.equiposComplejosPrestados = equiposComplejosPrestados;
    }

    /**
     * @return the equiposComplejosFaltantes
     */
    public List<EquipoComplejo> getEquiposComplejosFaltantes() {
        return equiposComplejosFaltantes;
    }

    /**
     * @param equiposComplejosFaltantes the equiposComplejosFaltantes to set
     */
    public void setEquiposComplejosFaltantes(List<EquipoComplejo> equiposComplejosFaltantes) {
        this.equiposComplejosFaltantes = equiposComplejosFaltantes;
    }

    /**
     * @return the equiposSencillosPrestados
     */
    public Set<EquipoSencillo> getEquiposSencillosPrestados() {
        return equiposSencillosPrestados;
    }

    /**
     * @param equiposSencillosPrestados the equiposSencillosPrestados to set
     */
    public void setEquiposSencillosPrestados(Set<EquipoSencillo> equiposSencillosPrestados) {
        this.equiposSencillosPrestados = equiposSencillosPrestados;
    }

    /**
     * @return the equiposSencillosFaltantes2
     */
    public List<EquipoSencillo> getEquiposSencillosFaltantes2() {
        return equiposSencillosFaltantes2;
    }

    /**
     * @param equiposSencillosFaltantes2 the equiposSencillosFaltantes2 to set
     */
    public void setEquiposSencillosFaltantes2(List<EquipoSencillo> equiposSencillosFaltantes2) {
        this.equiposSencillosFaltantes2 = equiposSencillosFaltantes2;
    }

    /**
     * @return the equiposSencillosPrestadosCantidad2
     */
    public List<Integer> getEquiposSencillosPrestadosCantidad2() {
        return equiposSencillosPrestadosCantidad2;
    }

    /**
     * @param equiposSencillosPrestadosCantidad2 the equiposSencillosPrestadosCantidad2 to set
     */
    public void setEquiposSencillosPrestadosCantidad2(List<Integer> equiposSencillosPrestadosCantidad2) {
        this.equiposSencillosPrestadosCantidad2 = equiposSencillosPrestadosCantidad2;
    }

    /**
     * @return the elQuePideElPrestamo
     */
    public Persona getElQuePideElPrestamo() {
        return elQuePideElPrestamo;
    }

    /**
     * @param elQuePideElPrestamo the elQuePideElPrestamo to set
     */
    public void setElQuePideElPrestamo(Persona elQuePideElPrestamo) {
        this.elQuePideElPrestamo = elQuePideElPrestamo;
    }

    /**
     * @return the tipo_prestamo
     */
    public int getTipo_prestamo() {
        return tipo_prestamo;
    }

    /**
     * @param tipo_prestamo the tipo_prestamo to set
     */
    public void setTipo_prestamo(int tipo_prestamo) {
        this.tipo_prestamo = tipo_prestamo;
    }

    /**
     * @return the prestamo
     */
    public Prestamo getPrestamo() {
        return prestamo;
    }

    /**
     * @param prestamo the prestamo to set
     */
    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
    
    public void setCarne(String car){
        this.carne=car;
    }
    
    public String getCarne(){
        return carne;
    }

    /**
     * @return the showPanelPersona
     */
    public boolean isShowPanelPersona() {
        return showPanelPersona;
    }

    /**
     * @param showPanelPersona the showPanelPersona to set
     */
    public void setShowPanelPersona(boolean showPanelPersona) {
        this.showPanelPersona = showPanelPersona;
    }
    
}
