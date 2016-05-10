/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.util.HashMap;
import java.util.Map;
import org.h2.command.dml.SetTypes;

/**
 *
 * @author German Lopez
 */
public class Rol implements Comparable<Rol> {

    private String rol;
    private String contrasenia;
    private String sal;
    private Map tiposPrestamo;
    public static String laboratorista = "laboratorista", profesor = "profesor", estudiante = "estudiante";

    public Rol() {
    }

    /**
     * Constructor de la clase rol
     *
     * @param r
     * @param cont
     * @param s
     */
    public Rol(String r, String cont, String s) {
        this.rol = r;
        this.contrasenia = cont;
        this.sal = s;
        tiposPrestamo = new HashMap<>();
        if (r.equalsIgnoreCase(Rol.estudiante)) {
            tiposPrestamo.put(EquipoComplejo.p24h, EquipoComplejo.p24h);
            tiposPrestamo.put(EquipoComplejo.diario, EquipoComplejo.diario);
        } else if (r.equalsIgnoreCase(Rol.laboratorista) || r.equalsIgnoreCase(Rol.profesor)) {
            tiposPrestamo.put(EquipoComplejo.p24h, EquipoComplejo.p24h);
            tiposPrestamo.put(EquipoComplejo.diario, EquipoComplejo.diario);
            tiposPrestamo.put(EquipoComplejo.semestre, EquipoComplejo.semestre);
            tiposPrestamo.put(EquipoComplejo.indefinido, EquipoComplejo.indefinido);
        }
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
        if (rol != null) {
            tiposPrestamo = new HashMap<>();
            if (rol.equalsIgnoreCase(Rol.estudiante)) {
                tiposPrestamo.put(EquipoComplejo.p24h, EquipoComplejo.p24h);
                tiposPrestamo.put(EquipoComplejo.diario, EquipoComplejo.diario);
            } else if (rol.equalsIgnoreCase(Rol.laboratorista) || rol.equalsIgnoreCase(Rol.profesor)) {
                tiposPrestamo.put(EquipoComplejo.p24h, EquipoComplejo.p24h);
                tiposPrestamo.put(EquipoComplejo.diario, EquipoComplejo.diario);
                tiposPrestamo.put(EquipoComplejo.semestre, EquipoComplejo.semestre);
                tiposPrestamo.put(EquipoComplejo.indefinido, EquipoComplejo.indefinido);
            }
        }
    }

    /**
     * @return the contraseña
     */
    public String getConstrasenia() {
        return contrasenia;
    }

    /**
     * @param con the contraseña to set
     */
    public void setConstraseña(String con) {
        this.contrasenia = con;
    }

    /**
     * @return the sal
     */
    public String getSal() {
        return sal;
    }

    /**
     * @param sal the sal to set
     */
    public void setSal(String sal) {
        this.sal = sal;
    }

    @Override
    public String toString() {
        return "Persona:[" + rol + " " + contrasenia + " " + sal + "] \n";
    }

    @Override
    public int compareTo(Rol o) {
        if (rol.equalsIgnoreCase(Rol.laboratorista) && o.getRol().equalsIgnoreCase(Rol.profesor)) {
            return 1;
        } else if (rol.equalsIgnoreCase(Rol.laboratorista) && o.getRol().equalsIgnoreCase(Rol.estudiante)) {
            return 1;
        } else if (rol.equalsIgnoreCase(Rol.profesor) && o.getRol().equalsIgnoreCase(Rol.estudiante)) {
            return 1;
        } else if (rol.equalsIgnoreCase(o.getRol())) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * @return the tiposPrestamo
     */
    public Map getTiposPrestamo() {
        return tiposPrestamo;
    }

    /**
     * @param tiposPrestamo the tiposPrestamo to set
     */
    public void setTiposPrestamo(Map tiposPrestamo) {
        this.tiposPrestamo = tiposPrestamo;
    }

}
