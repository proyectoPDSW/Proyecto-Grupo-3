/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.managedbeans;

import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.EquipoException;
import eci.pdsw.entities.EquipoSencillo;
import eci.pdsw.entities.Modelo;
import eci.pdsw.entities.Persona;
import eci.pdsw.entities.Prestamo;
import eci.pdsw.entities.PrestamoTerminoFijo;
import eci.pdsw.persistence.PersistenceException;
import eci.pdsw.servicios.ServiciosPrestamo;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
    
    private List<Prestamo> morosos;

    /**
     * Consulta los prestamos en mora
     * @return the morosos
     */
    public List<Prestamo> getMorosos() throws PersistenceException, EquipoException {
        Persona per=new Persona("2105684", "Julian", "Devia", "yo@asdads.com", "1234567");
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
        
        Persona oper=new Persona("2107646", "Daniela", "Sepulveda", "ella@asdads.com", "1234567");
        Modelo mod=new Modelo(5, "yosise", null, "pequeño", 4321);
        EquipoComplejo oec2=new EquipoComplejo(mod, "este", "13456789");
        EquipoComplejo oec1=new EquipoComplejo(mod, "otro", "21356789");
        List<EquipoComplejo> opres=new LinkedList<>();
        List<EquipoComplejo> ofalt=new LinkedList<>();
        EquipoSencillo oes1=new EquipoSencillo("chao", "tornillos", 125, 5);
        HashMap<EquipoSencillo,Integer> oes=new HashMap<>();es.put(oes1,2);
        opres.add(oec1);opres.add(oec2);ofalt.add(oec2);
        Prestamo op= new PrestamoTerminoFijo(oper, opres, oes, new Timestamp(System.currentTimeMillis()-10000));
        op.setEquiposComplejosFaltantes(ofalt);
        op.setEquiposSencillosFaltantes(oes);
        morosos=new LinkedList<>();morosos.add(op);morosos.add(p);
        Collections.sort(morosos);
        return morosos;
        //return sp.consultarPrestamosMorosos();
    }
    /**
     * halla la diferencia en horas entre d y la hora actual
     * @param d la hora con la que se quiere comparar la fecha actual
     * @return la diferencia en horas entre d y la hora actual
     */
    public int diffHoras(Timestamp d){
        Timestamp curr=new Timestamp(System.currentTimeMillis());
        int hoursCurr=curr.getHours()+curr.getDay()*24+curr.getMonth()*30*24+curr.getYear()*12*30*24;
        int hoursD=d.getHours()+d.getDay()*24+d.getMonth()*30*24+d.getYear()*12*30*24;
        return hoursCurr-hoursD;
    }
    public List<EquipoSencillo> convert(Set<EquipoSencillo> s){

        List<EquipoSencillo> es=new LinkedList<EquipoSencillo>();
        es.addAll(s);
        Collections.sort(es);
        return es;
    }
    

    
}
