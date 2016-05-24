/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.managedbeans;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosEquipoComplejo;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author David Useche
 */
@ManagedBean(name = "tiempoVida")
@RequestScoped
public class ReporteTiempoDeVidaManagedBean implements Serializable {

    private final ServiciosEquipoComplejo SERVICES = ServiciosEquipoComplejo.getInstance();

    private List<EquipoComplejo> equipos;

    /**
     * Obtiene todos los equipos guardados
     *
     * @return Todos los equipos guardados
     */
    public List<EquipoComplejo> getEquipos() {
        if(equipos==null || equipos.isEmpty()){
            try{
                equipos=SERVICES.consultarTodo();
            }catch(ExcepcionServicios ex){
                Registro.anotar(ex); 
            }
        }
        return equipos;
    }
}
