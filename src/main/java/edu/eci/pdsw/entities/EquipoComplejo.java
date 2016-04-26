/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.util.Objects;

/**
 *
 * @author David Useche
 */
public class EquipoComplejo implements Comparable<EquipoComplejo> {
    int id_Eq;
    private boolean asegurado;
    private boolean disponibilidad;
    private String estado;
    private String serial;
    private int placa;
    private String marca;
    private Modelo modelo_Eq;
    
    /**
     * Constructor de equipo complejo
     * @param mod
     * @param mar
     * @param ser
     * @throws EquipoException 
     */
    public EquipoComplejo(Modelo mod,String mar, String ser)throws EquipoException {
        if(mod==null) throw new EquipoException(EquipoException.EQUIPOC_SIN_MODELO);
        if(ser.length()<=0) throw new EquipoException(EquipoException.EQUIPOC_SIN_SERIAL);
        if(mar.length()<=0) throw new EquipoException(EquipoException.EQUIPOC_SIN_MARCA);
        marca=mar;
        serial=ser;
        modelo_Eq=mod;
    }

    public EquipoComplejo(int id_Eq, boolean asegurado, boolean disponibilidad, String estado, String serial, int placa, String marca, Modelo modelo_Eq) {
        this.id_Eq = id_Eq;
        this.asegurado = asegurado;
        this.disponibilidad = disponibilidad;
        this.estado = estado;
        this.serial = serial;
        this.placa = placa;
        this.marca = marca;
        this.modelo_Eq = modelo_Eq;
    }

    public EquipoComplejo() {
    }
    
    /**
     * 
     * @return el identificador del equipo complejo
     */
    public int getId_Eq() {
        return id_Eq;
    }
    
    /**
     * 
     * @param id_Eq 
     */
    public void setId_Eq(int id_Eq) {
        this.id_Eq = id_Eq;
    }
    
    /**
     * 
     * @param ase 
     */
    public void setAsegurado(boolean ase){
        this.asegurado=ase;
    }
    
    /**
     * 
     * @return si el equipo esta asegurado o no
     */
    public boolean getAsegurado(){
        return asegurado;
    }
    
    /**
     * 
     * @param dis 
     */
    public void setDisponibilidad(boolean dis){
        this.disponibilidad=dis;
    }
    
    /**
     * 
     * @return si el equipo complejo esta disponible o no
     */
    public boolean getDisponibilidad(){
        return disponibilidad;
    }
    
    /**
     * 
     * @param est 
     */
    public void setEstado(String est){
        this.estado=est;
    }
    
    /**
     * 
     * @return el estado del equipo complejo
     */
    public String getEstado(){
        return estado;
    }
    
    /**
     * 
     * @param ser 
     */
    public void setSerial(String ser){
        this.serial=ser;
    }
    
    /**
     * 
     * @return el serial del equipo complejo
     */
    public String getSerial(){
        return serial;
    }
    
    /**
     * 
     * @param pla 
     */
    public void setPlaca(int pla){
        this.placa=pla;
    }
    
    /**
     * 
     * @return la placa del equipo complejo
     */
    public int getPlaca(){
        return placa;
    }

    /**
     * 
     * @return la marca del equipo complejo
     */
    public String getMarca() {
        return marca;
    }
    
    /**
     * 
     * @param marca 
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    /**
     * 
     * @return el modelo del equipo complejo
     */
    public Modelo getModelo_Eq() {
        return modelo_Eq;
    }
    
    /**
     * 
     * @param modelo_Eq 
     */
    public void setModelo_Eq(Modelo modelo_Eq) {
        this.modelo_Eq = modelo_Eq;
    }
    
    /**
     * Metodo que retorna una cadena con toda la informacion de un equipo complejo
     * @return res, es la cadena que posee toda la informacion del equipo complejo
     */
    @Override
    public String toString(){
        String res="EquipoComplejo:["+id_Eq+","+asegurado+","+disponibilidad+","+estado+","+serial+","+placa+","+marca+"]\n";
        res+=modelo_Eq.toString();
        return res;
    }
    
    /**
     * Metodo que compara el valor comercial de un equipo complejo con otro
     * @param o
     * @return -1 si el valor comercial de un equipo es menor que otro, de lo contrario retorna 1
     */
    @Override
    public int compareTo(EquipoComplejo o) {
        if(modelo_Eq.getValorComercial()<o.modelo_Eq.getValorComercial()) return -1;
        else return 1;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EquipoComplejo other = (EquipoComplejo) obj;
        if (this.placa != other.placa) {
            return false;
        }
        if (!Objects.equals(this.serial, other.serial)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        return true;
    }
    
}