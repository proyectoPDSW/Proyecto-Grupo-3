/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author german
 */
public class OrdenCompra implements Comparable<OrdenCompra>{
    private int id_Equipo=0;
    private String nombre="";
    private String serial="";
    private Timestamp adquisicion=Prestamo.currDate();
    private Timestamp garantia=Prestamo.currDate();
    private String proveedor="";
    
    public OrdenCompra(){
    }
    
    public OrdenCompra(int id,String nom,String ser,Timestamp adq,Timestamp gar,String provee) throws EquipoException{
        if(nom.length()==0) throw new EquipoException("Favor colocar un nombre adecuado al equipo");
        if(ser.length()==0) throw new EquipoException("Favor colocar un serial adecuado al equipo");
        if(provee.length()==0) throw new EquipoException("Favor colocar un proveedor del equipo adecuado");
        if(id<0) throw new EquipoException("Favor colocar una identificación adecuada");
        this.id_Equipo=id;
        this.nombre=nom;
        this.serial=ser;
        this.adquisicion=adq;
        this.garantia=gar;
        this.proveedor=provee;
    }
    
    //Por implementar 
    @Override
    public int compareTo(OrdenCompra o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * @return the adquisicion
     */
    public Timestamp getAdquisicion() {
        return adquisicion;
    }

    /**
     * @param adquisicion the adquisicion to set
     */
    public void setAdquisicion(Timestamp adquisicion) {
        this.adquisicion = adquisicion;
    }

    /**
     * @return the garantia
     */
    public Timestamp getGarantia() {
        return garantia;
    }

    /**
     * @param garantia the garantia to set
     */
    public void setGarantia(Timestamp garantia) {
        this.garantia = garantia;
    }

    /**
     * @return the proveedor
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    
    @Override
    public String toString() {
        String res = "Datos Generales:[" + getId_Equipo() + "," + nombre + "," + proveedor + "," +adquisicion.toString() +","+ garantia.toString() +","
                + serial +"]\n";
        return res;
    }

    /**
     * @return the serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial the serial to set
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * @return the id_Equipo
     */
    public int getId_Equipo() {
        return id_Equipo;
    }

    /**
     * @param id_Equipo the id_Equipo to set
     */
    public void setId_Equipo(int id_Equipo) {
        this.id_Equipo = id_Equipo;
    }
    
}
