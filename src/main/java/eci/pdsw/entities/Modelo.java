/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.entities;

import java.sql.Blob;

/**
 *
 * @author David Useche
 */
public class Modelo {
    private int vidaUtil;
    private String nombre;
    private String clase;
    private long valorComercial;
    private String fotografia;
    private String descripcion;
    private String accesorios;


    public Modelo(int vidaU, String name, String foto, String clas, long valor) {
       vidaUtil=vidaU;
       nombre=name;
       clase=clas;
       valorComercial=valor;
       fotografia=foto;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getFotografia() {
        return fotografia;
    }
    
    public void setVidaUtil(int vu){
        this.vidaUtil=vu;
    }
    
    public int getVidaUtil(){
        return vidaUtil;
    }
    
    public void setNombre(String n){
        this.nombre=n;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setClase(String c){
        this.clase=c;
    }
    
    public String getClase(){
        return clase;
    }
    
    public void setValorComercial(int vc){
        this.valorComercial=vc;
    }
    
    public long getValorComercial(){
        return valorComercial;
    }
    
    public void setAccesorios(String ac){
        this.accesorios=ac;
    }
    
    public String getAccesorios(){
        return accesorios;
    }
    
    @Override
    public String toString(){
        String res="Modelo:["+vidaUtil+","+nombre+","+clase+","+valorComercial+"]\n";
        return res;
    }
    
}
