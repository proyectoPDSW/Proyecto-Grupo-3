/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.sql.Timestamp;


/**
 *
 * @author German
 */
public class OrdenCompra implements Comparable<OrdenCompra>{
    private Timestamp adquisicion;
    private Timestamp garantia;
    private String proveedor;
    private String codigoOrdenCompra;
    private String codigoActivo;
    
    public OrdenCompra(){
    }
    
    public OrdenCompra(Timestamp adq,Timestamp gar,String provee,String codOrden,String codAct) throws EquipoException{
        if(provee.length()==0) throw new EquipoException("Favor colocar un proveedor del equipo adecuado");
        if(adq==null) throw new EquipoException("Favor colocar una fecha de adquisicion adecuada");
        if(gar==null) throw new EquipoException("Favor colocar una fecha de garantia adecuada");
        this.adquisicion=adq;
        this.garantia=gar;
        this.proveedor=provee;
        this.codigoActivo=codAct;
        this.codigoOrdenCompra=codOrden;
    }
    
    //Por implementar 
    @Override
    public int compareTo(OrdenCompra o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        String res = "Datos Generales:[" + adquisicion.toString() + "," + garantia.toString() + "," + proveedor +"]\n";
        return res;
    }

    /**
     * @return the codigoOrdenCompra
     */
    public String getCodigoOrdenCompra() {
        return codigoOrdenCompra;
    }

    /**
     * @param codigoOrdenCompra the codigoOrdenCompra to set
     */
    public void setCodigoOrdenCompra(String codigoOrdenCompra) {
        this.codigoOrdenCompra = codigoOrdenCompra;
    }

    /**
     * @return the codigoActivo
     */
    public String getCodigoActivo() {
        return codigoActivo;
    }

    /**
     * @param codigoActivo the codigoActivo to set
     */
    public void setCodigoActivo(String codigoActivo) {
        this.codigoActivo = codigoActivo;
    }

    
}
