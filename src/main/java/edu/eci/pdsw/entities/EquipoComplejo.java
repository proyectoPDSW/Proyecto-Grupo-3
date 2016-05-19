/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author David Useche
 */
public class EquipoComplejo implements Comparable<EquipoComplejo> {

    private boolean asegurado;
    private boolean disponibilidad;
    private String estado;
    private String serial;
    private String placa;
    private String marca;
    private Modelo modelo_Eq;
    private DatosGenerales ordenCompra;
    private long tiempoRestante;
    public static String diario = "prestamo diario", p24h = "prestamo 24 horas", mantenimiento = "mantenimiento", almacen = "en almacen", indefinido = "prestamo indefinido", semestre = "prestamo por semestre";
    public static String baja = "dado de baja", reparacion = "en reparacion";

    /**
     * Constructor de equipo complejo
     *
     * @param mod modelo del equipo
     * @param mar marca del equipo
     * @param ser serial del equipo
     * @param plac placa del equipo
     * @param dg datos generales del equipo
     * @param tiempoDeUso cuanto tiempo ha sido usado el equipo
     * @throws EquipoException
     */
    public EquipoComplejo(Modelo mod, String mar, String ser, String plac, DatosGenerales dg, long tiempoDeUso) throws EquipoException {
        if (mod == null) {
            throw new EquipoException(EquipoException.EQUIPOC_SIN_MODELO);
        }
        if (ser.length() <= 0) {
            throw new EquipoException(EquipoException.EQUIPOC_SIN_SERIAL);
        }
        if (mar.length() <= 0) {
            throw new EquipoException(EquipoException.EQUIPOC_SIN_MARCA);
        }
        if (new BigInteger(plac).compareTo(BigInteger.ZERO) < 0) {
            throw new EquipoException(EquipoException.EUIPOC_SIN_PLACA);
        }
        if (dg == null) {
            throw new EquipoException(EquipoException.EUIPOC_SIN_ORDEN_DE_COMPRA);
        }
        if (mod.getVidaUtil() - tiempoDeUso < 0) {
            tiempoRestante = 0;
        } else {
            tiempoRestante = mod.getVidaUtil() - tiempoDeUso;
        }
        this.marca = mar;
        this.serial = ser;
        this.modelo_Eq = mod;
        this.placa = plac;
        this.ordenCompra = dg;
    }

    /**
     * Constructor de equipo complejo
     *
     * @param asegurado
     * @param disponibilidad
     * @param estado en que estado inicia el equipo
     * @param serial del equipo
     * @param placa del equipo
     * @param marca del equipo
     * @param modelo_Eq del equipo
     * @param dg datos generales del equipo
     * @param tiempoDeUso e tiempo que ha sido usado el equipo
     * @throws EquipoException
     */
    public EquipoComplejo(boolean asegurado, boolean disponibilidad, String estado, String serial, String placa, String marca, Modelo modelo_Eq, DatosGenerales dg, long tiempoDeUso) throws EquipoException {
        if (modelo_Eq == null) {
            throw new EquipoException(EquipoException.EQUIPOC_SIN_MODELO);
        }
        if (serial.length() <= 0) {
            throw new EquipoException(EquipoException.EQUIPOC_SIN_SERIAL);
        }
        if (marca.length() <= 0) {
            throw new EquipoException(EquipoException.EQUIPOC_SIN_MARCA);
        }
        if (new BigInteger(placa).compareTo(BigInteger.ZERO) < 0) {
            throw new EquipoException(EquipoException.EUIPOC_SIN_PLACA);
        }
        if (dg == null) {
            throw new EquipoException(EquipoException.EUIPOC_SIN_ORDEN_DE_COMPRA);
        }
        if (modelo_Eq.getVidaUtil() - tiempoDeUso < 0) {
            tiempoRestante = 0;
        } else {
            tiempoRestante = modelo_Eq.getVidaUtil() - tiempoDeUso;
        }
        this.asegurado = asegurado;
        this.disponibilidad = disponibilidad;
        this.estado = estado;
        this.serial = serial;
        this.placa = placa;
        this.marca = marca;
        this.modelo_Eq = modelo_Eq;
        this.ordenCompra = dg;
    }

    public EquipoComplejo() {
    }

    public DatosGenerales getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(DatosGenerales ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public long getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(long tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    /**
     *
     * @param ase
     */
    public void setAsegurado(boolean ase) {
        this.asegurado = ase;
    }

    /**
     *
     * @return si el equipo esta asegurado o no
     */
    public boolean getAsegurado() {
        return asegurado;
    }

    /**
     *
     * @param dis
     */
    public void setDisponibilidad(boolean dis) {
        this.disponibilidad = dis;
    }

    /**
     *
     * @return si el equipo complejo esta disponible o no
     */
    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    /**
     *
     * @param est
     */
    public void setEstado(String est) {
        this.estado = est;
    }

    /**
     *
     * @return el estado del equipo complejo
     */
    public String getEstado() {
        return estado;
    }

    /**
     *
     * @param ser
     */
    public void setSerial(String ser) {
        this.serial = ser;
    }

    /**
     *
     * @return el serial del equipo complejo
     */
    public String getSerial() {
        return serial;
    }

    /**
     *
     * @param pla
     */
    public void setPlaca(String pla) {
        this.placa = pla;
    }

    /**
     *
     * @return la placa del equipo complejo
     */
    public String getPlaca() {
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
     * Metodo que retorna una cadena con toda la informacion de un equipo
     * complejo
     *
     * @return res, es la cadena que posee toda la informacion del equipo
     * complejo
     */
    @Override
    public String toString() {
        String res = "EquipoComplejo:[" + asegurado + "," + disponibilidad + "," + estado + "," + serial + "," + placa + "," + marca + "]\n";
        res += modelo_Eq.toString();
        return res;
    }

    /**
     * Metodo que compara el valor comercial de un equipo complejo con otro
     *
     * @param o
     * @return -1 si el valor comercial de un equipo es menor que otro, de lo
     * contrario retorna 1
     */
    @Override
    public int compareTo(EquipoComplejo o) {
        if (modelo_Eq.getValorComercial() < o.modelo_Eq.getValorComercial()) {
            return -1;
        } else {
            return 1;
        }
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
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        if (!Objects.equals(this.serial, other.serial)) {
            return false;
        }
        if (!Objects.equals(this.modelo_Eq, other.modelo_Eq)) {
            return false;
        }
        return true;
    }

}
