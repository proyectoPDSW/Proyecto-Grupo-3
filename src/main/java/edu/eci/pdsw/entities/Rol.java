/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 2105403
 */
public class Rol implements Comparable<Rol>{
    private String rol;
    private String contrasenia;
    private String sal;
    private Map tiposPrestamo;
    
    public Rol(){
    }
    /**
     * Constructor de la clase rol
     * @param r
     * @param cont
     * @param s 
     */
    public Rol(String r,String cont,String s){
        this.rol=r;
        this.contrasenia=cont;
        this.sal=s;
        tiposPrestamo=new HashMap<>();
        if(r.equals("Estudiante")){
           tiposPrestamo.put("24 horas","24 horas");
           tiposPrestamo.put("Diario", "Diario");
        }
        else if(r.equals("Laboratorista") || r.equals("Profesor")){
            tiposPrestamo.put("24 horas","24 horas");
            tiposPrestamo.put("Diario", "Diario");
            tiposPrestamo.put("Semestral","Semestral");
            tiposPrestamo.put("Indefinido", "Indefinido");
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
    public String toString(){
        return "Persona:["+rol+ " "+contrasenia+" "+sal+"] \n";
    }

    @Override
    public int compareTo(Rol o) {
        if(rol.equals("laboratorista") && o.getRol().equals("profesor")) return 1;
        else if(rol.equals("laboratorista") && o.getRol().equals("estudiante")) return 1; 
        else if(rol.equals("profesor") && o.getRol().equals("estudiante")) return 1;
        else if(rol.equals(o.getRol())) return 0;
        else return -1;
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
