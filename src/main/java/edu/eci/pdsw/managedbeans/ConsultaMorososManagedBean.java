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
     * Calcula la diferencia en horas entre la fecha actual y la fecha de fin estimada del prestamo
     * 
     * @param prestamo el prestamo que se usará para consultar
     * @return la diferencia en horas entre la fecha actual y la fecha de fin estimada del prestamo
     */
    public long diffHoras(Prestamo prestamo) {
        return sp.diffHours(prestamo);
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
