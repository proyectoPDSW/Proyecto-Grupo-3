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
    
    
    public boolean showPanelInfo(){
        return showPanelInfoPrestamista;
    }
    
    public RegistroDevolucionManageBean() {
        PRESTAMO = ServiciosPrestamo.getInstance();
        EQCOMPLEJO = ServiciosEquipoComplejoPersistence.getInstance();
        equiposComplejosPrestados=new HashSet<>();
        equiposComplejosFaltantes=new HashSet<>();
    }
    
    public List<String> mostrarListaEquipoSencillo() {
        es = new ArrayList<>();
        List<String> es2 = new ArrayList<>();
        if (laPersona != null && laPersona.length() > 0) {
            List<Prestamo> p = PRESTAMO.consultarPrestamosPersona(laPersona);
            for (Prestamo p1 : p) {
                if (p1.getFechaRealEntregada() == null) {
                    
                    for (EquipoSencillo es1 : p1.getEquiposSencillosFaltantes()) {
                        es.add(es1);
                    }
                }
            }
        }
        for (EquipoSencillo e : es) {
            es2.add(e.getNombre());
        }
        return es2;
    }
    public int maxValue() {
        //System.out.println("Entro con "+Arrays.toString(es.toArray())+" y "+selectEqSe);
        for (EquipoSencillo esqs : es) {
            //System.out.println("Entro con "+esqs+" y "+selectEqSe);
            if (esqs.getNombre().equals(selectEqSe)) {
                return esqs.getCantidadTotal();
            }
        }
        return 1;
    }
    
    public void registroDevolucionEquipoSencillo() {
        try {
            PRESTAMO.registarDevolucion(laPersona, selectEqSe, cantidad);
            facesInfo("Se realizo con exito la devolución");
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
        }
    }

    public void registroDevolucionEquipoComplejo() {
        try {
            boolean check=false;
            EquipoComplejo eqcomp = EQCOMPLEJO.consultarPorPlaca(placa);
            List<Prestamo> prestamos = PRESTAMO.consultarPrestamosEquipoComplejo(eqcomp);
            for (Prestamo p1 : prestamos) {
                list<Prestamo> otros = PRESTAMO.consultarPrestamosPersona(placa);
                if (!p1.terminado()) {
                    
                    System.out.println("ERSDGFSDGSDF");
                    Set<EquipoComplejo> ecp = p1.getEquiposComplejosFaltantes();
                    
                    for (EquipoComplejo ecp1 : ecp) {
                        System.out.println(ecp1.toString() +"\n"+eqcomp.toString());
                        if (ecp1.equals(eqcomp)) {
                            check=true;
                            elQuePideElPrestamo = p1.getElQuePideElPrestamo();
                            equiposComplejosPrestados=p1.getEquiposComplejosPrestados();
                            equiposComplejosFaltantes=p1.getEquiposComplejosFaltantes();
                        }
                    }
                }
            }
            eqcompl = eqcomp.getModelo_Eq().getNombre();
            PRESTAMO.registrarDevolucion(placa);
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

    public List<EquipoSencillo> getEs() {
        return es;
    }

    public void setEs(List<EquipoSencillo> es) {
        this.es = es;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public String getLaPersona() {
        return laPersona;
    }

    public void setLaPersona(String laPersona) {
        this.laPersona = laPersona;
    }

    public String getPlaca() {
        return placa;
    }

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
