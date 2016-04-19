/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdswgr3.pdsw.entities;

/**
 *
 * @author Zawsx
 */
public class Modelo {
    int vidaUtil;
    String nombre;
    String clase;
    long valorComercial;
    byte[] fotografia;

    public Modelo(int vidaU, String name, byte[] foto, String clas, long valor) {
       vidaUtil=vidaU;
       nombre=name;
       clase=clas;
       valorComercial=valor;
       fotografia=foto;
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
    
    public void setFotografia(byte[] f){
        this.fotografia=f;
    }
    
    public byte[] getFotografia(){
        return fotografia;
    }
    
    @Override
    public String toString(){
        String res="Modelo:["+vidaUtil+","+nombre+","+clase+","+valorComercial+"]\n";
        return res;
    }
    
}
