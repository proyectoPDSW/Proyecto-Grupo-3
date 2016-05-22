/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.managedbeans;

import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Prestamo;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.persistence.PersistenceException;
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosPersona;
import edu.eci.pdsw.servicios.ServiciosPrestamo;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Julian Devia
 */
@ManagedBean(name = "consultaMorosos")
@RequestScoped
public class ConsultaMorososManagedBean implements Serializable {

    private final ServiciosPrestamo sp = ServiciosPrestamo.getInstance();
    private final ServiciosPersona sper=ServiciosPersona.getInstance();

    public List<Prestamo> morosos;

    /**
     * Consulta los prestamos en mora
     *
     * @return the morosos, いいえ。
     */
    public List<Prestamo> getMorosos() {
        if (morosos == null || morosos.size() == 0) {
            morosos = null;
            try {
                morosos = sp.consultarPrestamosMorosos();
                //System.out.println(Arrays.toString(morosos.toArray()));
            } catch (ExcepcionServicios ex) {
                Registro.anotar(ex);
            }
        }
        return morosos;
    }

    /**
     * halla la diferencia en horas entre d y la hora actual
     *
     * @param d la hora con la que se quiere comparar la fecha actual
     * @return la diferencia en horas entre d y la hora actual
     */
    public int diffHoras(Timestamp d) {
        Timestamp curr = sp.currDate();
        //Timestamp curr=new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("Europe/Budapest")).getTime().getTime());
        int hoursCurr = curr.getHours() + curr.getDay() * 24 + curr.getMonth() * 30 * 24 + curr.getYear() * 12 * 30 * 24;
        int hoursD = d.getHours() + d.getDay() * 24 + d.getMonth() * 30 * 24 + d.getYear() * 12 * 30 * 24;
        //System.out.println(curr+" "+d+" "+hoursCurr+" "+hoursD);
        return hoursCurr - hoursD;
    }

    /**
     * Halla la fecha actual
     *
     * @return
     */
    public Timestamp currDate() {
        return sp.currDate();
    }
    
    public int cantMoras(String carnet){
        int moras=0;
        try{
            moras=sper.calcMoras(carnet);
        }catch(ExcepcionServicios e){
            Registro.anotar(e);
        }
        return moras;
    }
}
