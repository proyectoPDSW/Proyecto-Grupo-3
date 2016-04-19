/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.entities;

/**
 *
 * @author amoto
 */
public class EquipoSencillo {
    private String nombre;
    private String clase;
    private long valorComercial;
    private int cantidadTotal;
    private byte[] fotografia;

    public EquipoSencillo(String name, String clas,long valorC, int cantidad) {
        nombre=name;
        clase=clas;
        valorComercial=valorC;
        cantidadTotal=cantidad;
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
     * @return the clase
     */
    public String getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * @return the valorComercial
     */
    public long getValorComercial() {
        return valorComercial;
    }

    /**
     * @param valorComercial the valorComercial to set
     */
    public void setValorComercial(long valorComercial) {
        this.valorComercial = valorComercial;
    }

    /**
     * @return the cantidadTotal
     */
    public int getCantidadTotal() {
        return cantidadTotal;
    }

    /**
     * @param cantidadTotal the cantidadTotal to set
     */
    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    /**
     * @return the fotografia
     */
    public byte[] getFotografia() {
        return fotografia;
    }

    /**
     * @param fotografia the fotografia to set
     */
    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }
    
    @Override
    public String toString(){
        String res="EquipoSencillo:["+nombre+","+clase+","+valorComercial+","+cantidadTotal+"]\n";
        return res;
    }
    
}
