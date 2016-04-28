/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

/**
 *
 * @author 2105403
 */
public class Rol {
    private String rol;
    private String contraseña;
    private String sal;
    
    public Rol(){
    }
    
    public Rol(String r,String cont,String s){
        this.rol=r;
        this.contraseña=cont;
        this.sal=s;
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
    public String getConstraseña() {
        return contraseña;
    }

    /**
     * @param constraseña the contraseña to set
     */
    public void setConstraseña(String constraseña) {
        this.contraseña = constraseña;
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
        return "Persona:["+rol+ " "+contraseña+" "+sal+"] \n";
    }
    
    
}
