/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;


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


    /**
     * Constructor de modelo de equipo complejo
     * @param vidaU
     * @param name
     * @param foto
     * @param clas
     * @param valor
     * @throws EquipoException 
     */
    public Modelo(int vidaU, String name, String foto, String clas, long valor) throws EquipoException {
       if(vidaU<=0) throw new EquipoException(EquipoException.MODELO_VIDA_UTIL_INADECUADA);
       if(name.length()<=0) throw new EquipoException(EquipoException.MODELO_SIN_NOMBRE);
       if(clas.length()<=0) throw new EquipoException(EquipoException.MODELO_SIN_CLASE);
       if(valor<=0) throw new EquipoException(EquipoException.MODELO_VALOR_COMERCIAL_INADECUADO);
       vidaUtil=vidaU;
       nombre=name;
       clase=clas;
       valorComercial=valor;
       fotografia=foto;
    }

    public Modelo(int vidaUtil, String nombre, String clase, long valorComercial, String fotografia, String descripcion, String accesorios) {
        this.vidaUtil = vidaUtil;
        this.nombre = nombre;
        this.clase = clase;
        this.valorComercial = valorComercial;
        this.fotografia = fotografia;
        this.descripcion = descripcion;
        this.accesorios = accesorios;
    }

    public Modelo() {
    }
    
    /**
     * 
     * @param valorComercial 
     */
    public void setValorComercial(long valorComercial) {
        this.valorComercial = valorComercial;
    }
    
    /**
     * 
     * @return la descripcion del modelo
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * 
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * 
     * @param fotografia 
     */
    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }
    
    /**
     * 
     * @return la fotografia del modelo
     */
    public String getFotografia() {
        return fotografia;
    }
    
    /**
     * 
     * @param vu 
     */
    public void setVidaUtil(int vu){
        this.vidaUtil=vu;
    }
    
    /**
     * 
     * @return la vida util del modelo
     */
    public int getVidaUtil(){
        return vidaUtil;
    }
    
    /**
     * 
     * @param n 
     */
    public void setNombre(String n){
        this.nombre=n;
    }
    
    /**
     * 
     * @return el nombre del modelo
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * 
     * @param c 
     */
    public void setClase(String c){
        this.clase=c;
    }
    
    /**
     * 
     * @return la clase del equipo
     */
    public String getClase(){
        return clase;
    }
    
    /**
     * 
     * @param vc 
     */
    public void setValorComercial(int vc){
        this.valorComercial=vc;
    }
    
    /**
     * 
     * @return el valor comercial del modelo
     */
    public long getValorComercial(){
        return valorComercial;
    }
    
    /**
     * 
     * @param ac 
     */
    public void setAccesorios(String ac){
        this.accesorios=ac;
    }
    
    /**
     * 
     * @return los accesorios del modelo
     */
    public String getAccesorios(){
        return accesorios;
    }
    
    /**
     * Metodo que retorna una cadena donde esta toda la informacion del modelo
     * @return res, cadena que posee toda la informacion del modelo
     */
    @Override
    public String toString(){
        String res="Modelo:["+vidaUtil+","+nombre+","+clase+","+valorComercial+"]\n";
        return res;
    }
    
}
