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
    private String nombre="";
    private String marca="";
    private String serial="";
    private String estado;
    private String referenciaInterna;
    private List<EquipoSencillo> accesorios;
    private String descripcion="";
    private Timestamp adquisicion=Prestamo.currDate();
    private Timestamp garantia=Prestamo.currDate();
    private String proveedor="";
    private int valor=0;
    private String categoria;
    private int ordenC=0;
    private boolean asegurado;
    
    public OrdenCompra(){
    }
    
    public OrdenCompra(String nom,String mar,String ser,Timestamp adqu,Timestamp garan,String provee,int val,int orden) throws EquipoException{
        if(nom.length()==0) throw new EquipoException("Favor colocar un nombre adecuado al equipo");
        if(mar.length()==0) throw new EquipoException("Favor colocar una marca adecuada al equipo");
        if(ser.length()==0) throw new EquipoException("Favor colocar un serial adecuado al equipo");
        if(provee.length()==0) throw new EquipoException("Favor colocar un proveedor del equipo adecuado");
        if(val<0) throw new EquipoException("Favor colocar un valor de compra adecuado");
        if(orden<0) throw new EquipoException("Favor colocar una orden de compra adecuada");
        this.nombre=nom;
        this.marca=mar;
        this.serial=ser;
        this.adquisicion=adqu;
        this.garantia=garan;
        this.proveedor=provee;
        this.valor=val;
        this.ordenC=orden;
    }
    
    public OrdenCompra(String nom,String mar,String ser,String est,String ref,List<EquipoSencillo> acc,String desc,Timestamp adqu,Timestamp garan,String provee,int va,String cat,int orden,boolean ase){
        this.nombre=nom;
        this.marca=mar;
        this.serial=ser;
        this.estado=est;
        this.referenciaInterna=ref;
        this.accesorios=acc;
        this.descripcion=desc;
        this.adquisicion=adqu;
        this.garantia=garan;
        this.proveedor=provee;
        this.valor=va;
        this.categoria=cat;
        this.ordenC=orden;
        this.asegurado=ase;
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
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the referenciaInterna
     */
    public String getReferenciaInterna() {
        return referenciaInterna;
    }

    /**
     * @param referenciaInterna the referenciaInterna to set
     */
    public void setReferenciaInterna(String referenciaInterna) {
        this.referenciaInterna = referenciaInterna;
    }

    /**
     * @return the accesorios
     */
    public List<EquipoSencillo> getAccesorios() {
        return accesorios;
    }

    /**
     * @param accesorios the accesorios to set
     */
    public void setAccesorios(List<EquipoSencillo> accesorios) {
        this.accesorios = accesorios;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the ordenC
     */
    public int getOrdenC() {
        return ordenC;
    }

    /**
     * @param ordenC the ordenC to set
     */
    public void setOrdenC(int ordenC) {
        this.ordenC = ordenC;
    }

    /**
     * @return the asegurado
     */
    public boolean isAsegurado() {
        return asegurado;
    }

    /**
     * @param asegurado the asegurado to set
     */
    public void setAsegurado(boolean asegurado) {
        this.asegurado = asegurado;
    }
    
    @Override
    public String toString() {
        String res = "Datos Generales:[" + nombre + "," + marca + "," + estado + "," + referenciaInterna + "," + descripcion + "," +adquisicion.toString() +","+ garantia.toString() +","
                + proveedor +","+ valor +","+ categoria +","+ ordenC +","+ asegurado +"]\n";
        for(EquipoSencillo s:accesorios){
            res += s.toString();
        }
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
    
}
