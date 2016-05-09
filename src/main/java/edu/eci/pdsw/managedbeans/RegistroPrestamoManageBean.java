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
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosEquipoComplejo;
import edu.eci.pdsw.servicios.ServiciosEquipoComplejoPersistence;
import edu.eci.pdsw.servicios.ServiciosEquipoSencillo;
import edu.eci.pdsw.servicios.ServiciosPrestamo;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
    private final  ServiciosPrestamo PRESTAMO;
    private final ServiciosEquipoComplejo EQCOMPLEJO;
    private final ServiciosEquipoSencillo EQSENCILLO;
    //Atributos de prestamo
    private Timestamp fechaEstimadaDeEntrega;
    //Consultar persona
    private String carne;
    //Consultar modelo prestamo termino fijo
    private String modelo;
    //Equipo Complejo prestamo termino fijo
    private EquipoComplejo selectEquipoComplejo;
    //Equipo Sencillo prestamo termino fijo
    private EquipoSencillo selectEquipoSencillo;

    //Consultar equipo sencillo por nombre prestamo termino fijo
    private String nombre;
    //Cantidad de equipo sencillo de prestamo termino fijo
    private int cantidad;
    
    //Atributos de prestamo
    //Prestamo termino fijo
    private Set<EquipoComplejo> equiposComplejosPrestados;
    private Set<EquipoSencillo> equiposSencillosPrestados;
    
    private List<Integer> equiposSencillosPrestadosCantidad;
    private Persona elQuePideElPrestamo;
    private int tipo_prestamo;
    //Pantallas
    private boolean showPanelRegistro=false;
    private boolean showPanelRegistrado=false;
    private boolean showPanelPersona=true;
    private String selectEqSe;
    
    // Lista de equipo complejo para consultar los equipos prestamo termino fijo
    private List<EquipoComplejo> eqC;
    //Lista de equipo sencillo para consultar los equipos prestamo termino fijo
    private List<EquipoSencillo> eqS;
    //Mapa de los tiempos que posee un prestamo, equipo complejo prestamo termino fijo
    private Map<String,String> tipoPrestamo;
    //Tiempo de vida del prestamo, equipo complejo prestamo termino fijo
    private String fechaTipoPrestamo;
    //lista para saber la cantidad de los equipos
    private List<EquipoSencillo> es;
    private Prestamo prestamo;
    private String laPersona;
    private String placa;

    public List<String> modelosAproximados(String query){
        List<String> aproximados=new ArrayList<>();
        try{
            aproximados=EQCOMPLEJO.consultarAproximado(query);
        }catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
        }
        return aproximados;
    }
    
    
    public  RegistroPrestamoManageBean(){
        
        PRESTAMO=ServiciosPrestamo.getInstance();
        EQCOMPLEJO=ServiciosEquipoComplejoPersistence.getInstance();
        EQSENCILLO=ServiciosEquipoSencillo.getInstance();
        equiposComplejosPrestados=new LinkedHashSet<>();
        equiposSencillosPrestados=new LinkedHashSet<>();
        fechaTipoPrestamo=null;
        //tipoPrestamo=elQuePideElPrestamo.rolMasValioso2();
        tipoPrestamo=new HashMap<>();
        tipoPrestamo.put("24 horas","24 horas");
        tipoPrestamo.put("Diario","Diario");
        tipoPrestamo.put("Semestral","Semestral");
        tipoPrestamo.put("Indefinido","Indefinido");
        es=new ArrayList<>();
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
    

    /**
     *Consulta la lista de equipos complejos que tengan
     * un modelo especifico
     * 
     */
    public void consultarEqModelo(){
        List<EquipoComplejo> equipos=new ArrayList<>();
        try{
            equipos=EQCOMPLEJO.consultarEnAlmacenModelo(modelo);
            showPanelRegistro=true;
        }catch(ExcepcionServicios ex){
            showPanelRegistro=false;
            facesError(ex.getMessage());
        }
        eqC=equipos;
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
           showPanelRegistro=false;
           facesError(ex.getMessage());
       }
       eqS=equiposS;
    }
    
    
    /**
     * Agrega equipos complejos al prestamo termino fijo
    */
    public void agregarEquipoC(){
        if(selectEquipoComplejo!=null){
        selectEquipoComplejo.setEstado(fechaTipoPrestamo);
        actualizarEquipos(selectEquipoComplejo);
        consultarEqModelo();
        equiposComplejosPrestados.add(selectEquipoComplejo);
        }
    }
    
    /**
     * Actualiza los equipos despues de haber sufrido un
     * cambio en su estado
     * @param nuevo 
     */
    public void actualizarEquipos(EquipoComplejo nuevo){
        try{
            EQCOMPLEJO.actualizarEquipo(nuevo);
        }catch(ExcepcionServicios ex){
            facesError(ex.getMessage());
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
     * Registra un prestamo dependiendo de la persona que haga el prestamo se 
     * obtiene el tipo del prestamo si es indefinido o termino fijo
     */
    public void registrarPrestamo(){
        try{
            if(elQuePideElPrestamo.rolMasValioso().equals("Estudiante")){
                prestamo=new PrestamoTerminoFijo(elQuePideElPrestamo,equiposComplejosPrestados,equiposSencillosPrestados,fechaEstimadaDeEntrega,fechaTipoPrestamo);
            }
            else if(getElQuePideElPrestamo().rolMasValioso().equals("Laboratorista") || getElQuePideElPrestamo().rolMasValioso().equals("Profesor")){
                if(fechaTipoPrestamo==null){
                setPrestamo(new PrestamoIndefinido(elQuePideElPrestamo, equiposComplejosPrestados, equiposSencillosPrestados));
                }else{
                    prestamo=new PrestamoTerminoFijo(elQuePideElPrestamo,equiposComplejosPrestados,equiposSencillosPrestados,fechaEstimadaDeEntrega,fechaTipoPrestamo);
                }
            PRESTAMO.registrarPrestamo(prestamo);
            facesInfo("El prestamo ha sido registrado satisfactoriamente");
            }
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
        }
    }
    
       /**
     * A la fecha actual se le suma la fecha dependiendo si el 
     * prestamo es para el dia siguiente, si es semanal, si es mensual
     * o si es semestral
     */
    public void obtenerFechaEstimada(){
        /*Calendar calen= Calendar.getInstance();
        calen.setTime(fechaEstimadaDeEntrega);
        if(fechaTipoPrestamo.equals("24 horas")){
            calen.add(Calendar.DAY_OF_MONTH, 1);
        }
        else if(fechaTipoPrestamo.equals("Diario")){
            calen.set(Calendar.HOUR, 19);
        }
        else if(fechaTipoPrestamo.equals("Semestral")){
            calen.set(Calendar.MONTH,6);
        }
        fechaEstimadaDeEntrega=(Timestamp) calen.getTime();*/
        fechaEstimadaDeEntrega=Prestamo.calcularFechaEstimada(fechaTipoPrestamo);
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

    
    /**        restriccion();
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
    
    public List<String> mostrarListaEquipoSencillo(){
        es = new ArrayList<>();
        List<String> es2 = new ArrayList<>();
        if(laPersona!=null && laPersona.length()>0){
            List<Prestamo> p = PRESTAMO.consultarPrestamosPersona(laPersona);
            for (Prestamo p1 : p) 
                if(p1.getFechaRealEntregada()==null)
                    for (EquipoSencillo es1 : p1.getEquiposSencillosFaltantes()) 
                        es.add(es1);
        }
        for (EquipoSencillo e : es) {
            es2.add(e.getNombre());
        }
        return es2;
    }
    
    public EquipoSencillo getSelectEquipoSencillo() {
        return selectEquipoSencillo;
    }

    public void setSelectEquipoSencillo(EquipoSencillo equiS) {
        this.selectEquipoSencillo = equiS;
    }
    
    public void onEquipoChange(){
    }
    
    public int maxValue(){
        System.out.println("Entro con "+Arrays.toString(es.toArray())+" y "+selectEqSe);
        for (EquipoSencillo esqs : es) {
            System.out.println("Entro con "+esqs+" y "+selectEqSe);
            if(esqs.getNombre().equals(selectEqSe)) return esqs.getCantidadTotal();
        }
        return 1;
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
     * @return the tipoPrestamo
     */
    public Map<String,String> getTipoPrestamo() {
        return tipoPrestamo;
    }

    /**
     * @param tipoPrestamo the tipoPrestamo to set
     */
    public void setTipoPrestamo(Map<String,String> tipoPrestamo) {
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

    public String getSelectEqSe() {
        return selectEqSe;
    }

    public void setSelectEqSe(String selectEqSe) {
        this.selectEqSe = selectEqSe;
    }

    public void registroDevolucionEquipoSencillo(){
        try {
            PRESTAMO.registarDevolucion(laPersona, selectEqSe, cantidad);
            System.out.println("SADSADASDADASDASDASDSADASDADSA");
            facesInfo("Se realizo con exito la devolución");
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
        }
    }
    public void registroDevolucionEquipoComplejo(){
        try {
            PRESTAMO.registrarDevolucion(placa);
            System.out.println("SADSADASDADASDASDASDSADASDADSA");
            facesInfo("Se realizo con exito la devolución");
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
        }
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
}
