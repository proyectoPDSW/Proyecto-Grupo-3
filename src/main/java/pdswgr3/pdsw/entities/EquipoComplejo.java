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
public class EquipoComplejo {
    int id_Eq;
    private boolean asegurado;
    private boolean disponibilidad;
    private String estado;
    private String serial;
    private int placa;
    private String marca;
    private Modelo modelo_Eq;

    public EquipoComplejo(Modelo mod,String mar, String ser) {
        marca=mar;
        serial=ser;
        modelo_Eq=mod;
    }
    
    public void setId(int id){
        this.id_Eq=id;
    }
    
    public int getId(){
        return id_Eq;
    }
    
    public void setAsegurado(boolean ase){
        this.asegurado=ase;
    }
    
    public boolean getAsegurado(){
        return asegurado;
    }
    
    public void setDisponibilidad(boolean dis){
        this.disponibilidad=dis;
    }
    
    public boolean getDisponibilidad(){
        return disponibilidad;
    }
    
    public void setEstado(String est){
        this.estado=est;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setSerial(String ser){
        this.serial=ser;
    }
    
    public String getSerial(){
        return serial;
    }
    
    public void setPlaca(int pla){
        this.placa=pla;
    }
    
    public int getPlaca(){
        return placa;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Modelo getModelo_Eq() {
        return modelo_Eq;
    }

    public void setModelo_Eq(Modelo modelo_Eq) {
        this.modelo_Eq = modelo_Eq;
    }
    
    @Override
    public String toString(){
        String res="EquipoComplejo:["+id_Eq+","+asegurado+","+disponibilidad+","+estado+","+serial+","+placa+","+marca+"]\n";
        res+=modelo_Eq.toString();
        return res;
    }
    
}
