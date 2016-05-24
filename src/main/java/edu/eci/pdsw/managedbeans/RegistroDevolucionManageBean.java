/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.managedbeans;

import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Prestamo;
import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.servicios.ServiciosEquipoComplejo;
import edu.eci.pdsw.servicios.ServiciosEquipoComplejoPersistence;
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosPrestamo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hugo Alvarez
 */
@ManagedBean(name = "RegistroDevolucion")
@SessionScoped
public class RegistroDevolucionManageBean implements Serializable{
    private final ServiciosPrestamo PRESTAMO;
    private final ServiciosEquipoComplejo EQCOMPLEJO;
    private List<EquipoSencillo> es;
    //cantidad disponible del equipo sencillo
    private int cantidadDisponible;
    private Prestamo prestamo;
    private String laPersona;
    private String placa;
    private String eqcompl;
    private String selectEqSe;
    private int cantidad;
    private Persona elQuePideElPrestamo;
    private Set<EquipoComplejo> equiposComplejosPrestados;
    private Set<EquipoComplejo> equiposComplejosFaltantes;
    
    private boolean showPanelInfoPrestamista=false;
    
    /**
     * Me dice si hay que mostrar la informacion
     * @return si hay que mostrar la informacion
     */
    public boolean showPanelInfo(){
        return showPanelInfoPrestamista;
    }
    
    /**
     * Creador
     */
    public RegistroDevolucionManageBean() {
        PRESTAMO = ServiciosPrestamo.getInstance();
        EQCOMPLEJO = ServiciosEquipoComplejoPersistence.getInstance();
        equiposComplejosPrestados=new HashSet<>();
        equiposComplejosFaltantes=new HashSet<>();
    }
    
    /**
     * Muestra la lista de equipos sencillos
     * @return los equipos sencillos a mostrar
     */
    public List<String> mostrarListaEquipoSencillo() {
        es = new ArrayList<>();
        List<String> es2 = new ArrayList<>();
        if (laPersona != null && laPersona.length() > 0) {
            try {
                List<Prestamo> p = PRESTAMO.consultarPrestamosPersona(laPersona);
                for (Prestamo p1 : p){
                    if (!p1.terminado()){
                        for (EquipoSencillo es1 : p1.getEquiposSencillosFaltantes()){
                            if(!es.contains(es1)) es.add(es1);
                        }
                    }
                }
            } catch (ExcepcionServicios ex) {
                facesError(ex.getMessage());
            }
        }
        for (EquipoSencillo e : es) {
            es2.add(e.getNombre());
        }
        return es2;
    }
    
    /**
     * Registra una devolucion de equipo sencillo
     */
    public void registroDevolucionEquipoSencillo() {
        try {
            PRESTAMO.registarDevolucion(laPersona, selectEqSe, cantidad);
            facesInfo("Se realizo con exito la devolución");
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
        }
    }

    /**
     * Registra una devolucion de equipo complejo
     */
    public void registroDevolucionEquipoComplejo() {
        try {
            boolean check=false;
            
            EquipoComplejo eqcomp = EQCOMPLEJO.consultarPorPlaca(placa);
            List<Prestamo> prestamos = PRESTAMO.consultarPrestamosEquipoComplejo(eqcomp);
            for (Prestamo p1 : prestamos) {
                Prestamo otros = PRESTAMO.consultarPrestamosPrestamo(p1);
                if (!otros.terminado()) {
                    Set<EquipoComplejo> ecp = otros.getEquiposComplejosFaltantes();
                    for (EquipoComplejo ecp1 : ecp) {
                        if (ecp1.equals(eqcomp)) {
                            check=true;
                            elQuePideElPrestamo = otros.getElQuePideElPrestamo();
                            equiposComplejosPrestados=otros.getEquiposComplejosPrestados();
                            equiposComplejosFaltantes=otros.getEquiposComplejosFaltantes();
                        }
                    }
                }
            }
            eqcompl = eqcomp.getModelo_Eq().getNombre();
            PRESTAMO.registrarDevolucion(placa);
            equiposComplejosFaltantes.remove(eqcomp);
            if(check) {
                showPanelInfoPrestamista=true;
                facesInfo("Se realizo con exito la devolución");
            }
            else facesError("El equipo no esta prestado");
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
            showPanelInfoPrestamista=false;
        }
    }
    
    /**
     * Resetea y no muestra la informacion de prestamista
     */
    public void reset(){
        showPanelInfoPrestamista=false;
    }

    /**
     * Obtener es
     * @return es
     */
    public List<EquipoSencillo> getEs() {
        return es;
    }

    /**
     * Setear es
     * @param es 
     */
    public void setEs(List<EquipoSencillo> es) {
        this.es = es;
    }

    /**
     * Obtener la cantidad disponible
     * @return la cantidad disponible
     */
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    /**
     * Setear la cantidad disponible
     * @param cantidadDisponible 
     */
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    /**
     * Obtener el prestamo
     * @return 
     */
    public Prestamo getPrestamo() {
        return prestamo;
    }

    /**
     * Setear el prestamo
     * @param prestamo 
     */
    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    /**
     * Obtener el carnet de la persona
     * @return el carnet de la persona
     */
    public String getLaPersona() {
        return laPersona;
    }

    /**
     * Setear el carnet de la persona
     * @param laPersona 
     */
    public void setLaPersona(String laPersona) {
        this.laPersona = laPersona;
    }

    /**
     * Obtener la placa
     * @return la placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Setear la placa
     * @param placa 
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getEqcompl() {
        return eqcompl;
    }

    public String getSelectEqSe() {
        return selectEqSe;
    }

    public void setSelectEqSe(String selectEqSe) {
        this.selectEqSe = selectEqSe;
    }

    public void setEqcompl(String eqcompl) {
        this.eqcompl = eqcompl;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Persona getElQuePideElPrestamo() {
        return elQuePideElPrestamo;
    }

    public void setElQuePideElPrestamo(Persona elQuePideElPrestamo) {
        this.elQuePideElPrestamo = elQuePideElPrestamo;
    }

    public boolean isShowPanelInfoPrestamista() {
        return showPanelInfoPrestamista;
    }

    public void setShowPanelInfoPrestamista(boolean showPanelInfoPrestamista) {
        this.showPanelInfoPrestamista = showPanelInfoPrestamista;
    }

    public Set<EquipoComplejo> getEquiposComplejosPrestados() {
        return equiposComplejosPrestados;
    }

    public void setEquiposComplejosPrestados(Set<EquipoComplejo> equiposComplejosPrestados) {
        this.equiposComplejosPrestados = equiposComplejosPrestados;
    }

    public Set<EquipoComplejo> getEquiposComplejosFaltantes() {
        return equiposComplejosFaltantes;
    }

    public void setEquiposComplejosFaltantes(Set<EquipoComplejo> equiposComplejosFaltantes) {
        this.equiposComplejosFaltantes = equiposComplejosFaltantes;
    }
    
    /**
     * restriccion(); Muestra un mensaje de error en la vista
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
