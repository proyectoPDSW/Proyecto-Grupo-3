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
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosEquipoComplejo;
import edu.eci.pdsw.servicios.ServiciosEquipoComplejoPersistence;
import edu.eci.pdsw.servicios.ServiciosEquipoSencillo;
import edu.eci.pdsw.servicios.ServiciosPrestamo;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author German Lopez
 */
@ManagedBean(name="RegistroPrestamo")
@SessionScoped
public class RegistroPrestamoManageBean implements Serializable{
    private final ServiciosPrestamo PRESTAMO;
    private final ServiciosEquipoComplejo EQCOMPLEJO;
    private final ServiciosEquipoSencillo EQSENCILLO;
    //Atributos de prestamo
    private Timestamp fechaEstimadaDeEntrega;
    //Consultar persona
    private String carne;
    //Consultar modelo prestamo termino fijo
    private String modelo;
    //Consultar modelo prestamo indefinido
    private String modelo2;
    //Equipo Complejo prestamo termino fijo
    private EquipoComplejo selectEquipoComplejo;
    //Equipo Sencillo prestamo termino fijo
    private EquipoSencillo selectEquipoSencillo;
    //Equipo Complejo prestamo indefinido
    private EquipoComplejo selectEquipoComplejo2;
    //Equipo Sencillo prestamo indefinido
    private EquipoSencillo selectEquipoSencillo2;
    //Consultar equipo sencillo por nombre prestamo termino fijo
    private String nombre;
    //Consultar equipo sencillo por nombre prestamo indefinido
    private String nombre2;
    //Cantidad de equipo sencillo de prestamo termino fijo
    private int cantidad;
    //Cantidad de equipo sencillo de prestamo indefinido
    private int cantidad2;
    
    
    //Atributos de prestamo
    //Prestamo termino fijo
    private Set<EquipoComplejo> equiposComplejosPrestados;
    private Set<EquipoSencillo> equiposSencillosPrestados;
    //Prestamo indefinido
    private Set<EquipoComplejo> equiposComplejosPrestados2;
    private Set<EquipoSencillo> equiposSencillosPrestados2;
    
    private List<Integer> equiposSencillosPrestadosCantidad;
    private Persona elQuePideElPrestamo;
    private int tipo_prestamo;
    //Pantallas
    private boolean showPanelRegistro=false;
    private boolean showPanelRegistrado=false;
    private boolean showPanelPersona=true;
    
    // Lista de equipo complejo para consultar los equipos prestamo termino fijo
    private List<EquipoComplejo> eqC;
    //Lista de equipo sencillo para consultar los equipos prestamo termino fijo
    private List<EquipoSencillo> eqS;
    //Lista de equipo complejo para consultar los equipos prestamo indefinido
    private List<EquipoComplejo> eqC2;
    //Lista de equipo sencillo para consultar los equipos prestamo indefinido
    private List<EquipoSencillo> equS2;
    //Lista de los tiempos que posee un prestamo, equipo complejo prestamo termino fijo
    private List<String> tipoPrestamo;
    //Lista de los tiempos que posee un prestamo, equipo sencillo prestamo termino fijo
    private List<String> tipoPrestamo2;
    //Tiempo de vida del prestamo, equipo complejo prestamo termino fijo
    private String fechaTipoPrestamo;
    //Tiempo de vida del prestamo, equipo sencillo prestamo termino fijo
    private String fechaTipoPrestamo2;

    
    private Prestamo prestamo;
    private String laPersona;
    

    
    
    
    public RegistroPrestamoManageBean(){
        
        PRESTAMO=ServiciosPrestamo.getInstance();
        EQCOMPLEJO=ServiciosEquipoComplejoPersistence.getInstance();
        EQSENCILLO=ServiciosEquipoSencillo.getInstance();
        equiposComplejosPrestados=new LinkedHashSet<>();
        equiposSencillosPrestados=new LinkedHashSet<>();
        equiposComplejosPrestados2=new LinkedHashSet<>();
        equiposSencillosPrestados2=new LinkedHashSet<>();
        tipoPrestamo=new ArrayList<>();
        tipoPrestamo2=new ArrayList<>();
        tipoPrestamo.add("Diario");
        tipoPrestamo.add("Semanal");
        tipoPrestamo.add("Mensual");
        tipoPrestamo.add("Semestral");
        tipoPrestamo.add("Anual");
        tipoPrestamo2=tipoPrestamo;
    }
    /**
     * Consulta una persona por su carne para 
     * realizar un prestamo hacia el
     */
    public void consultarPersona(){
        try {
            elQuePideElPrestamo=PRESTAMO.personaCarne(carne);
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
            
        }
    }
    public List<EquipoComplejo> sacarEqC(){
        return eqC;
    }
    
    public List<EquipoSencillo> sacarEqS(){
        return eqS;
    }
    
    public List<EquipoComplejo> sacarEqC2(){
        return eqC2;
    }
    
    public List<EquipoSencillo> sacarEqS2(){
        return equS2;
    }
    /**
     *Consulta la lista de equipos complejos que tengan
     * un modelo especifico
     * @return La lista con los equipos complejos
     */
    public void consultarEqModelo(){
        List<EquipoComplejo> equipos=new ArrayList<>();
        try{
            equipos=EQCOMPLEJO.consultarPorModelo(modelo);
            showPanelRegistro=true;
        }catch(ExcepcionServicios ex){
            ex.printStackTrace();
            showPanelRegistro=false;
            facesError(ex.getMessage());
        }
        eqC=equipos;
    }
    
    public void consultarEqModelo2(){
        List<EquipoComplejo> equipos=new ArrayList<>();
        try{
            equipos=EQCOMPLEJO.consultarPorModelo(modelo2);
            showPanelRegistro=true;
        }catch(ExcepcionServicios ex){
            ex.printStackTrace();
            showPanelRegistro=false;
            facesError(ex.getMessage());
        }
        eqC2=equipos;
    }
    
    /**
     * Consulta un equipo sencillo por su nombre
     */
    public void consultarEqSNombre(){
       List<EquipoSencillo> equiposS=new ArrayList<>();
       try{
           equiposS.add(EQSENCILLO.consultarPorNombre(nombre));
           showPanelRegistro=true;
       }catch(ExcepcionServicios ex){
           ex.printStackTrace();
           showPanelRegistro=false;
           facesError(ex.getMessage());
       }
       eqS=equiposS;
    }
    
    public void consultarEqSNombre2(){
       List<EquipoSencillo> equiposS=new ArrayList<>();
       try{
           equiposS.add(EQSENCILLO.consultarPorNombre(nombre2));
           showPanelRegistro=true;
       }catch(ExcepcionServicios ex){
           ex.printStackTrace();
           showPanelRegistro=false;
           facesError(ex.getMessage());
       }
       equS2=equiposS;
    }
    
    /**
     * Agrega equipos complejos al prestamo termino fijo
    */
    public void agregarEquipoC(){
        if(selectEquipoComplejo!=null){
        equiposComplejosPrestados.add(selectEquipoComplejo);
        }
    }
    /**
     * Agrega equipos sencillos al prestamo termino fijo
     */
    public void agregarEquipoS(){
        if(getSelectEquipoSencillo()!=null){
        equiposSencillosPrestados.add(getSelectEquipoSencillo());
        }
    }
    
    /**
     * Agrega equipos complejos al prestamo indefinido
     */
    public void agregarEquipoC2(){
        if(selectEquipoComplejo!=null){
            equiposComplejosPrestados2.add(selectEquipoComplejo);
        }
    }
    
    /**
     * Agrega equipos sencillos al prestamo indefinido
     */
    public void agregarEquipoS2(){
        if(selectEquipoSencillo!=null){
            equiposSencillosPrestados2.add(selectEquipoSencillo);
        }
    }
    
    /**
     * Registra un prestamo dependiendo de la persona que haga el prestamo se 
     * obtiene el tipo del prestamo si es indefinido o termino fijo
     */
    public void registrarPrestamo(){
        showPanelPersona=false;
        try{
            if(elQuePideElPrestamo.rolMasValioso().equals("Estudiante")){
                prestamo=new PrestamoTerminoFijo(elQuePideElPrestamo,equiposComplejosPrestados,equiposSencillosPrestados,fechaEstimadaDeEntrega,EquipoComplejo.diario);
                PRESTAMO.registrarPrestamo(prestamo);
                facesInfo("El prestamo ha sido registrado satisfactoriamente");
                showPanelRegistro=false;
                showPanelRegistrado=true;
            }
            else if(getElQuePideElPrestamo().rolMasValioso().equals("Laboratorista") || getElQuePideElPrestamo().rolMasValioso().equals("Profesor")){
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
        setFechaEstimadaDeEntrega(null);

        setEquiposComplejosPrestados(null);
        setEquiposSencillosPrestados(null);
        setEquiposSencillosPrestadosCantidad2(null);
        setElQuePideElPrestamo(null);
        setTipo_prestamo(0);
        showPanelRegistro=false;
        showPanelRegistrado=false;
    }
    
    public boolean ShowPanelRegistro() {
        return showPanelRegistro;
    }


    public boolean ShowPanelRegistrado() {
        return showPanelRegistrado;
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
     * @return the equiposSencillosPrestadosCantidad2
     */
    public List<Integer> getEquiposSencillosPrestadosCantidad2() {
        return equiposSencillosPrestadosCantidad;
    }

    /**
     * @param equiposSencillosPrestadosCantidad2 the equiposSencillosPrestadosCantidad2 to set
     */
    public void setEquiposSencillosPrestadosCantidad2(List<Integer> equiposSencillosPrestadosCantidad2) {
        this.equiposSencillosPrestadosCantidad = equiposSencillosPrestadosCantidad2;
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
    
    public void setNombre(String n){
        this.nombre=n;
    }
    
    public String getNombre(){
        return nombre;
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
    
    
    public void setModelo(String mo){
        this.modelo=mo;
    }
    
    public String getModelo(){
        return modelo;
    } 
   
    public String getLaPersona() {
        return laPersona;
    }

    public void setLaPersona(String laPersona) {
        this.laPersona = laPersona;
    }
    
    public List<EquipoSencillo> mostrarListaEquipoSencillo(){
        List<EquipoSencillo> es = new ArrayList<>();
        if(laPersona!=null && laPersona.length()>0){
            List<Prestamo> p = PRESTAMO.consultarPrestamosPersona(laPersona);
            for (Prestamo p1 : p) {
                System.out.println(p1.toString());
                System.out.println("Entro a revisar los prestamos "+p1.getFechaRealEntregada());
                if(p1.getFechaRealEntregada()==null){
                    System.out.println("Entro "+p1.getEquiposSencillosFaltantes().size());
                    for (EquipoSencillo es1 : p1.getEquiposSencillosFaltantes()) {
                        es.add(es1);
                    }
                }
            }
        }
        return es;
    }
    
    public EquipoSencillo getSelectEquipoSencillo() {
        return selectEquipoSencillo;
    }

    public void setSelectEquipoSencillo(EquipoSencillo equiS) {
        this.selectEquipoSencillo = equiS;
    }
    
    public void onEquipoChange(){
        System.out.println("Funciona :)");
    }

    /**
     * @return the selectEqC
     */
    public EquipoComplejo getSelectEquipoComplejo() {
        return selectEquipoComplejo;
    }

    /**
     * @param selectEqC the selectEqC to set
     */
    public void setSelectEquipoComplejo(EquipoComplejo selectEqC) {
        this.selectEquipoComplejo = selectEqC;
    }
    
    public void setEquiposComplejosPrestados2(Set<EquipoComplejo> equiC){
        this.equiposComplejosPrestados2=equiC;
    }
    
    public Set<EquipoComplejo> getEquipoComplejosPrestados2(){
        return equiposComplejosPrestados2;
    }
    
    public void setEquiposSencillosPrestados2(Set<EquipoSencillo> equiS){
        this.equiposSencillosPrestados2=equiS;
    }
    
    public Set<EquipoSencillo> getEquipoSencillosPrestados2(){
        return equiposSencillosPrestados2;
    }

    /**
     * @return the modelo2
     */
    public String getModelo2() {
        return modelo2;
    }

    /**
     * @param modelo2 the modelo2 to set
     */
    public void setModelo2(String modelo2) {
        this.modelo2 = modelo2;
    }

    /**
     * @return the nombre2
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * @param nombre2 the nombre2 to set
     */
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    /**
     * @return the eqC2
     */
    public List<EquipoComplejo> getEqC2() {
        return eqC2;
    }

    /**
     * @param eqC2 the eqC2 to set
     */
    public void setEqC2(List<EquipoComplejo> eqC2) {
        this.eqC2 = eqC2;
    }

    /**
     * @return the equS2
     */
    public List<EquipoSencillo> getEquS2() {
        return equS2;
    }

    /**
     * @param equS2 the equS2 to set
     */
    public void setEquS2(List<EquipoSencillo> equS2) {
        this.equS2 = equS2;
    }
    
    public void setSelectEquipoComplejo2(EquipoComplejo ec2){
        this.selectEquipoComplejo2=ec2;
    }
    
    public EquipoComplejo getSelectEquipoComplejo2(){
        return selectEquipoComplejo2;
    }
    
    public void setSelectEquipoSencillo2(EquipoSencillo es2){
        this.selectEquipoSencillo2=es2;
    }
    
    public EquipoSencillo getSelectEquipoSencillo2(){
        return selectEquipoSencillo2;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the cantidad2
     */
    public int getCantidad2() {
        return cantidad2;
    }

    /**
     * @param cantidad2 the cantidad2 to set
     */
    public void setCantidad2(int cantidad2) {
        this.cantidad2 = cantidad2;
    }

    /**
     * @return the tipoPrestamo
     */
    public List<String> getTipoPrestamo() {
        return tipoPrestamo;
    }

    /**
     * @param tipoPrestamo the tipoPrestamo to set
     */
    public void setTipoPrestamo(List<String> tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    /**
     * @return the fechaTipoPrestamo
     */
    public String getFechaTipoPrestamo() {
        return fechaTipoPrestamo;
    }

    /**
     * @param fechaTipoPrestamo the fechaTipoPrestamo to set
     */
    public void setFechaTipoPrestamo(String fechaTipoPrestamo) {
        this.fechaTipoPrestamo = fechaTipoPrestamo;
    }

    /**
     * @return the fechaTipoPrestamo2
     */
    public String getFechaTipoPrestamo2() {
        return fechaTipoPrestamo2;
    }

    /**
     * @param fechaTipoPrestamo2 the fechaTipoPrestamo2 to set
     */
    public void setFechaTipoPrestamo2(String fechaTipoPrestamo2) {
        this.fechaTipoPrestamo2 = fechaTipoPrestamo2;
    }

    /**
     * @return the tipoPrestamo2
     */
    public List<String> getTipoPrestamo2() {
        return tipoPrestamo2;
    }

    /**
     * @param tipoPrestamo2 the tipoPrestamo2 to set
     */
    public void setTipoPrestamo2(List<String> tipoPrestamo2) {
        this.tipoPrestamo2 = tipoPrestamo2;
    }
}
