/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.entities;

import java.util.Iterator;
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
    private Set<String> roles;
    private Set<String> departamentos;

    public Persona(String carnet, String nombre, String apellido, String email, String telefono) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    
    /**
     * Obj: Sirve para saber a que departamento es la persona.
     * pre: un departamento
     * pos: Si, la persona es de ese departamento, No d.l.c.
     * @param departamento, el departamento a revisar
     * @return True si es de ese departamento, False d.l.c
     */
    public boolean perteneceA(String departamento){
        boolean estaElDepartamento = false;
        for (Iterator<String> iterator = getRoles().iterator();!estaElDepartamento && iterator.hasNext();) {
            String next = iterator.next();
            estaElDepartamento = next.equals(departamento);
        }
        return estaElDepartamento;
    }
    /**
     * Obj: Sirve para saber que rol es la persona.
     * pre: un rol
     * pos: Si, la persona tiene ese rol, No d.l.c.
     * @param rol, el rol a revisar
     * @return True si tiene ese rol, False d.l.c
     */
    public boolean esUn(String rol){
        boolean estaElRol = false;
        for (Iterator<String> iterator = getRoles().iterator();!estaElRol && iterator.hasNext();) {
            String next = iterator.next();
            estaElRol = next.equals(rol);
        }
        return estaElRol;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
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
     * @return the roles
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * @return the departamentos
     */
    public Set<String> getDepartamentos() {
        return departamentos;
    }
    
}
