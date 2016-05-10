/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.managedbeans;

import edu.eci.pdsw.entities.Prestamo;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosPrestamo;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Julian Devia
 */
@ManagedBean(name = "consultaMorosos")
@SessionScoped
public class ConsultaMorososManagedBean implements Serializable {

    private final ServiciosPrestamo sp = ServiciosPrestamo.getInstance();

    public List<Prestamo> morosos;

    /**
     * Consulta los prestamos en mora
     *
     * @return the morosos
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
        Timestamp curr = Prestamo.currDate();
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
        return Prestamo.currDate();
    }

}
