/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.util.Objects;

/**
 *
 * @author Julian Devia
 */
public class EquipoSencillo implements Comparable<EquipoSencillo> {

    private String nombre;
    private String clase;
    private long valorComercial;
    private int cantidadTotal;
    private String fotografia;

    /**
     * Constructor de equipo sencillo
     *
     * @param name, nombre del equipo
     * @param clas, clase del equipo
     * @param valorC, valor comercial del equipo
     * @param cantidad, cantidad del equipo
     * @throws EquipoException
     */
    public EquipoSencillo(String name, String clas, long valorC, int cantidad) throws EquipoException {
        if (name.length() <= 0) {
            throw new EquipoException(EquipoException.EQUIPO_S_SIN_NOMBRE);
        }
        if (clas.length() <= 0) {
            throw new EquipoException(EquipoException.EQUIPO_S_SIN_CLASE);
        }
        if (valorC <= 0) {
            throw new EquipoException(EquipoException.EQUIPO_S_COMERCIAL_INADECUADO);
        }
        if (cantidad < 0) {
            throw new EquipoException(EquipoException.EQUIPO_S_CANTIDAD_INADECUADA);
        }
        nombre = name;
        clase = clas;
        valorComercial = valorC;
        cantidadTotal = cantidad;
    }

    public EquipoSencillo() {
    }

    /**
     * Constructor de equipo sencillo
     *
     * @param nombre, nombre del equipo
     * @param clase, clase del equipo
     * @param valorComercial, valor comercial del equipo
     * @param cantidadTotal, cantidad del equipo
     * @param fotografia, la fotografia del equipo
     * @throws EquipoException
     */
    public EquipoSencillo(String nombre, String clase, long valorComercial, int cantidadTotal, String fotografia) {
        this.nombre = nombre;
        this.clase = clase;
        this.valorComercial = valorComercial;
        this.cantidadTotal = cantidadTotal;
        this.fotografia = fotografia;
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
    public String getFotografia() {
        return fotografia;
    }

    /**
     * @param fotografia the fotografia to set
     */
    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    /**
     * Metodo para retornar en un cadena toda la informacion del equipo sencillo
     *
     * @return res, cadena que posee toda la informacion
     */
    @Override
    public String toString() {
        String res = "EquipoSencillo:[" + nombre + "," + clase + "," + valorComercial + "," + cantidadTotal + "]\n";
        return res;
    }

    /**
     * Metodo para comparar equipos sencillos
     *
     * @param o
     * @return -1 si el valor comercial de un equipo es menor a otro, de lo
     * contrario retorna 1
     */
    @Override
    public int compareTo(EquipoSencillo o) {
        if (valorComercial < o.valorComercial) {
            return -1;
        } else {
            return 1;
        }
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
        final EquipoSencillo other = (EquipoSencillo) obj;
        if (this.valorComercial != other.valorComercial) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + (int) (this.valorComercial ^ (this.valorComercial >>> 32));
        return hash;
    }

}
