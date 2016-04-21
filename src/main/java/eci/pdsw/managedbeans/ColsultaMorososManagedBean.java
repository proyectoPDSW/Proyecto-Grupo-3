/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.managedbeans;

import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.EquipoSencillo;
import eci.pdsw.entities.Modelo;
import eci.pdsw.entities.Persona;
import eci.pdsw.entities.Prestamo;
import eci.pdsw.entities.PrestamoTerminoFijo;
import eci.pdsw.servicios.ServiciosPrestamo;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Julian Devia
 */
@ManagedBean(name="consultaMorosos")
@SessionScoped
public class ColsultaMorososManagedBean {
    private final ServiciosPrestamo sp=ServiciosPrestamo.getInstance();
    
    //private List<Prestamo> morosos;

    /**
     * @return the morosos
     */
    public List<Prestamo> getMorosos() {
        /**Persona per=new Persona("2105684", "Julian", "Devia", "yo@asdads.com", "1234567");
        Modelo lod=new Modelo(5, "yonose", null, "grande", 1234);
        EquipoComplejo ec2=new EquipoComplejo(lod, "esta", "132456789");
        EquipoComplejo ec1=new EquipoComplejo(lod, "otra", "213456789");
        List<EquipoComplejo> pres=new LinkedList<>();
        List<EquipoComplejo> falt=new LinkedList<>();
        EquipoSencillo es1=new EquipoSencillo("hola", "cable", 1235, 5);
        HashMap<EquipoSencillo,Integer> es=new HashMap<>();es.put(es1,2);
        pres.add(ec1);pres.add(ec2);falt.add(ec2);
        Prestamo p= new PrestamoTerminoFijo(per, pres, es, new Timestamp(System.currentTimeMillis()));
        p.setEquiposComplejosFaltantes(falt);
        p.setEquiposSencillosFaltantes(es);
        morosos=new LinkedList<>();morosos.add(p);
        return morosos;**/
        return sp.consultarPrestamosMorosos();
    }
    
    public int diffHoras(Timestamp d){
        Timestamp curr=new Timestamp(System.currentTimeMillis());
        int hoursCurr=curr.getHours()+curr.getDay()*24+curr.getMonth()*30*24+curr.getYear()*12*30*24;
        int hoursD=d.getHours()+d.getDay()*24+d.getMonth()*30*24+d.getYear()*12*30*24;
        return hoursCurr-hoursD;
    }
    

    
}
