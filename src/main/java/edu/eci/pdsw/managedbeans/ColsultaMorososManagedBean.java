/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.managedbeans;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoException;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Prestamo;
import edu.eci.pdsw.entities.PrestamoTerminoFijo;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.persistence.PersistenceException;
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosPrestamo;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Julian Devia
 */
@ManagedBean(name="consultaMorosos")
@SessionScoped
public class ColsultaMorososManagedBean implements Serializable{
    private final ServiciosPrestamo sp=ServiciosPrestamo.getInstance();
    
    private List<Prestamo> morosos;

    /**
     * Consulta los prestamos en mora
     * @return the morosos
     */
    public List<Prestamo> getMorosos()  {
        List <Prestamo> morosos=null;
        try {
            morosos=sp.consultarPrestamosMorosos();
        } catch (ExcepcionServicios ex) {
            Registro.anotar(ex);
        }
        return morosos;
    }
    /**
     * halla la diferencia en horas entre d y la hora actual
     * @param d la hora con la que se quiere comparar la fecha actual
     * @return la diferencia en horas entre d y la hora actual
     */
    public int diffHoras(Timestamp d){
        Timestamp curr=new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("America/Bogota")).getTimeInMillis());
        //Timestamp curr=new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("Europe/Budapest")).getTime().getTime());
        int hoursCurr=curr.getHours()+curr.getDay()*24+curr.getMonth()*30*24+curr.getYear()*12*30*24;
        int hoursD=d.getHours()+d.getDay()*24+d.getMonth()*30*24+d.getYear()*12*30*24;
        //System.out.println(curr+" "+d+" "+hoursCurr+" "+hoursD);
        return hoursCurr-hoursD;
    }
    public List<EquipoSencillo> convert(Set<EquipoSencillo> s){
        if(s==null) s=new HashSet<>();
        List<EquipoSencillo> es=new LinkedList<EquipoSencillo>();
        es.addAll(s);
        Collections.sort(es);
        return es;
    }
    

    
}
