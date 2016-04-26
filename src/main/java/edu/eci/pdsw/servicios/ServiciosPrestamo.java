/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.servicios;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Prestamo;
import java.util.List;

/**
 *
 * @author 2105684
 */
public abstract class ServiciosPrestamo {
    
    private static final ServiciosPrestamo instance=new ServiciosPrestamoPersistence();

    public static ServiciosPrestamo getInstance() throws RuntimeException{        
        return instance;
    }
    /**
     * Consulta todos los prestamos con algun equipo en mora
     * @return los prestamos que posean algun equipo en mora de ser entregado
     */
    public abstract List<Prestamo> consultarPrestamosMorosos() throws ExcepcionServicios;
    
    /**
     * Consulta todos los prestamos de una persona determinada
     * @param p la persona de la que se quieren conocer los prestamos
     * @return Los prestamos de p
     */
    public abstract List<Prestamo> consultarPrestamosPersona(Persona p);
    
    /**
     * Consulta todos los prestamos de un equipo complejo determinado
     * @param ec el equipo complejo del que se quieren conocer los prestamos
     * @return Los prestamos de ec
     */
    public abstract List<Prestamo> consultarPrestamosEquipoComplejo(EquipoComplejo ec);
    
    /**
     * Consulta todos los prestamos
     * @return todos los prestamos registrados
     */
    public abstract List<Prestamo> consultarTodos();
}
