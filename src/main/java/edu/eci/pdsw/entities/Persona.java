/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Hugo Alvarez
 */
public class Persona {

    private String carnet;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Set<String> departamentos;
    private List<Rol> roles;

    /**
     * Constructor de persona
     *
     * @param carnet
     * @param nombre
     * @param apellido
     * @param email
     * @param telefono
     * @param rol
     */
    public Persona(String carnet, String nombre, String apellido, String email, String telefono, List<Rol> rol) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.roles = rol;
    }

    public Persona() {
    }

    /**
     * Obj: Sirve para saber a que departamento es la persona. pre: un
     * departamento pos: Si, la persona es de ese departamento, No d.l.c.
     *
     * @param departamento, el departamento a revisar
     * @return True si es de ese departamento, False d.l.c
     */
    public boolean perteneceA(String departamento) {
        boolean estaElDepartamento = false;
        for (Iterator<String> iterator = getDepartamentos().iterator(); !estaElDepartamento && iterator.hasNext();) {
            String next = iterator.next();
            estaElDepartamento = next.equals(departamento);
        }
        return estaElDepartamento;
    }

    @Override
    public boolean equals(Object obj) {
        Persona p = (Persona) obj;
        return carnet.equals(p.getCarnet());
    }

    @Override
    public String toString() {
        String res;
        res = "Persona:[" + carnet + " " + nombre + " " + apellido + " " + email + " " + telefono + "] \n";
        if (roles != null) {
            for (Rol e : roles) {
                res += e.toString();
            }
        }
        return res;
    }

    /**
     * @return the carnet
     */
    public String getCarnet() {
        return carnet;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @return the departamentos
     */
    public Set<String> getDepartamentos() {
        return departamentos;
    }

    /**
     *
     * @param carnet
     */
    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @param apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     *
     * @param departamentos
     */
    public void setDepartamentos(Set<String> departamentos) {
        this.departamentos = departamentos;
    }

    /**
     * @return the roles
     */
    public List<Rol> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    /**
     * Ordena los roles por prioridad
     *
     * @return los roles ordenados por prioridad
     */
    public List<Rol> prioridad() {
        Collections.sort(roles);
        return roles;
    }

    /**
     * Retorna el rol mas valioso
     *
     * @return el rol mas valioso
     */
    public String rolMasValioso() {
        String r = "";
        if (!roles.isEmpty()) {
            Collections.sort(roles);
            r = roles.get(roles.size() - 1).getRol();
        }
        return r;
    }

    /**
     *
     * @return
     */
    public Map<String, String> getRolMasValioso2() {
        Map<String, String> r = null;
        if (!roles.isEmpty()) {
            Collections.sort(roles);
            r = roles.get(roles.size() - 1).getTiposPrestamo();
        }
        return r;
    }

}
