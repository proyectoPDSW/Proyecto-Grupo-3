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
import edu.eci.pdsw.servicios.ServiciosPrestamo;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

    
    private List<EquipoComplejo> equiposComplejosPrestados;
    private List<EquipoComplejo> equiposComplejosFaltantes;
    private List<EquipoSencillo> equiposSencillosPrestados;
    private List<EquipoSencillo> equiposSencillosFaltantes2;
    private List<Integer> equiposSencillosPrestadosCantidad2;
    private Persona elQuePideElPrestamo;
    private int tipo_prestamo;
    
    private boolean showPanelRegistro=true;
    private boolean showPanelRegistrado=false;
    
    private Prestamo prestamo;
    
    public RegistroPrestamoManageBean(){
        PRESTAMO=ServiciosPrestamo.getInstance();
    }
    
    public void registrarPrestamo(){
        try{
            if(elQuePideElPrestamo.prioridad().get(0).getRol().equals("Estudiante")){
                prestamo=new PrestamoTerminoFijo(elQuePideElPrestamo,equiposComplejosPrestados,equiposSencillosPrestados,fechaEstimadaDeEntrega);
                PRESTAMO.registrarPrestamo(prestamo);
                facesInfo("El equipo ha sido registrado satisfactoriamente");
            }
            else if(elQuePideElPrestamo.prioridad().get(0).getRol().equals("Laboratorista") || elQuePideElPrestamo.prioridad().get(0).getRol().equals("Profesor")){
                prestamo=new PrestamoIndefinido(elQuePideElPrestamo,equiposComplejosPrestados,equiposSencillosPrestados);
            }
        }
    }
    
}
