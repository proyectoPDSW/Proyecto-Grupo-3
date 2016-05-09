/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.servicios;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Prestamo;
import edu.eci.pdsw.entities.PrestamoException;
import edu.eci.pdsw.entities.PrestamoIndefinido;
import edu.eci.pdsw.entities.PrestamoTerminoFijo;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.DAOEquipoSencillo;
import edu.eci.pdsw.persistence.DAOFactory;
import edu.eci.pdsw.persistence.DAOPersona;
import edu.eci.pdsw.persistence.DAOPrestamo;
import edu.eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2105684
 */
public class ServiciosPrestamoPersistence extends ServiciosPrestamo {

    private DAOPrestamo basePaciente;
    private DAOFactory daoF;
    private DAOPersona basePersona;
    
    public ServiciosPrestamoPersistence() {
    	try {
            
            InputStream input = getClass().getClassLoader().getResource("applicationconfig.properties").openStream();
            
            Properties properties=new Properties();
            properties.load(input);
            daoF = DAOFactory.getInstance(properties);
          
        } catch (IOException ex) {
            Registro.anotar(ex);
        }
    }

    @Override
    public List<Prestamo> consultarPrestamosMorosos() throws ExcepcionServicios {
        List<Prestamo> morosos=new LinkedList<>();
        try{
            daoF.beginSession();
            basePaciente=daoF.getDaoPrestamo();
            morosos=basePaciente.loadMorosos();
            //Collections.sort(morosos);
            //System.out.println("serv: "+Arrays.toString(morosos.toArray()));
        }catch(PersistenceException e){
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
            return morosos;
        }
        
    }

    @Override
    public List<Prestamo> consultarPrestamosPersona(String p) {
        List<Prestamo> prestamos=new LinkedList<>();
        try{
            daoF.beginSession();
            basePaciente=daoF.getDaoPrestamo();
            prestamos=basePaciente.loadByCarne(p);
            Collections.sort(prestamos);
        }catch(PersistenceException e){
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
            return prestamos;
        }
    }

    @Override
    public List<Prestamo> consultarPrestamosEquipoComplejo(EquipoComplejo ec) {
        List<Prestamo> prestamos=new LinkedList<>();
        try{
            daoF.beginSession();
            basePaciente=daoF.getDaoPrestamo();
            prestamos=basePaciente.loadByEquipoComplejo(ec);
            Collections.sort(prestamos);
        }catch(PersistenceException e){
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
            return prestamos;
        }
    }

    @Override
    public List<Prestamo> consultarTodos() {
        List<Prestamo> prestamos=new LinkedList<>();
        try{
            daoF.beginSession();
            basePaciente=daoF.getDaoPrestamo();
            prestamos=basePaciente.loadAll();
            Collections.sort(prestamos);
        }catch(PersistenceException e){
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
            return prestamos;
        }
    }

    @Override
    public void registrarPrestamo(Prestamo pres) throws ExcepcionServicios {
       /*Set<EquipoComplejo> presHoras=new LinkedHashSet<>();
       Set<EquipoComplejo> presDiario=new LinkedHashSet<>();
       Set<EquipoComplejo> presSemestre=new LinkedHashSet<>();
       Set<EquipoComplejo> presIndefinido=new LinkedHashSet<>();
       Set<EquipoComplejo> equiposC=new LinkedHashSet<>();
       Set<EquipoSencillo> equiposS=new LinkedHashSet<>();
       Prestamo horas=null;
       Prestamo diario=null;
       Prestamo semestral=null;EquipoComp   lejo ec
       Prestamo indefinido=null;*/
       try{
           daoF.beginSession();
           basePaciente=daoF.getDaoPrestamo();
           
           Set<EquipoComplejo> equiposC=pres.getEquiposComplejosPrestados();
           Set<EquipoSencillo> equiposS=pres.getEquiposSencillosPrestados();
           boolean fp=true;
           while(!equiposC.isEmpty()){
               EquipoComplejo ec=null;
               Set<EquipoComplejo> aAgregar=new HashSet<>();boolean first=true;
               for (EquipoComplejo e : equiposC) {
                   if(first){
                       ec=e;
                       aAgregar.add(e);
                       equiposC.remove(e);
                       first=false;
                   }else{
                       if(e.getEstado().equals(ec.getEstado())){
                           aAgregar.add(e);
                           equiposC.remove(e);
                       }
                   }
               }
               Prestamo guardar=null;
               if(ec.getEstado().equals(EquipoComplejo.indefinido)){
                   guardar=new PrestamoIndefinido(pres.getElQuePideElPrestamo(), aAgregar, null);
               }else{
                   guardar=new PrestamoTerminoFijo(pres.getElQuePideElPrestamo(), aAgregar, null, Prestamo.calcularFechaEstimada(ec.getEstado()), ec.getEstado());
               }
               if(fp){
                   guardar.setEquiposSencillosPrestados(equiposS);
                   fp=false;
               }
               basePaciente.save(guardar);
               daoF.commitTransaction();
           }
           /*for (EquipoComplejo c:equiposC){
               if(c.getEstado().equals("24 horas")){
                   presHoras.add(c);
               }
               else if(c.getEstado().equals("Diario")){
                   presDiario.add(c);
               }
               else if(c.getEstado().equals("Semestral")){
                   presSemestre.add(c);
               }
               else if(c.getEstado().equals("Indefinido")){
                   presIndefinido.add(c);
               }
           }
           if( !presHoras.isEmpty()){
               horas=new PrestamoTerminoFijo(pres.getElQuePideElPrestamo(),presHoras,equiposS,pres.getFechaEstimadaDeEntrega(),pres.getTipo_prestamo());
           }
           else if(!presDiario.isEmpty()){
               diario=new PrestamoTerminoFijo(pres.getElQuePideElPrestamo(),presDiario,equiposS,pres.getFechaEstimadaDeEntrega(),pres.getTipo_prestamo());
           }
           else if(!presSemestre.isEmpty()){
               semestral=new PrestamoTerminoFijo(pres.getElQuePideElPrestamo(),presSemestre,equiposS,pres.getFechaEstimadaDeEntrega(),pres.getTipo_prestamo());
           }
           else if(!presIndefinido.isEmpty()){
               indefinido=new PrestamoIndefinido(pres.getElQuePideElPrestamo(),presIndefinido,equiposS);
           }
           if(horas!=null){
              basePaciente.save(horas);
              daoF.commitTransaction();
           }
           else if(diario!=null){
              basePaciente.save(diario);
              daoF.commitTransaction();
           }
           else if(semestral!=null){
               basePaciente.save(semestral);
               daoF.commitTransaction();
           }
           else if(indefinido!=null){
               basePaciente.save(indefinido);
               daoF.commitTransaction();
           }*/
       }catch(PersistenceException e){
           daoF.rollbackTransaction();
           throw new ExcepcionServicios(e,e.getLocalizedMessage());
       }finally{
           daoF.endSession();
       }
    }

    @Override
    public void registarDevolucion(String persona, String equipo, int cantidad) throws ExcepcionServicios{
        try{
            daoF.beginSession();
            DAOEquipoSencillo des=daoF.getDaoEquipoSencillo();
            basePaciente=daoF.getDaoPrestamo();
            EquipoSencillo loaded=des.load(equipo);
            //Cargo a la persona que pidio el prestamo y sus prestamos
            List<Prestamo> cargadosDePersona=basePaciente.loadByCarne(persona);
            for(int i=0;i<cargadosDePersona.size() && cantidad>0;i++){
                //Para cada prestamo donde me falte entregar algo del equipo lo entrego
                //Asi hasta que la cantidad que estoy entregando es 0
                if(cargadosDePersona.get(i).isFaltante(loaded)){
                    EquipoSencillo tmp=cargadosDePersona.get(i).getSencillo(loaded);
                    if(cantidad<=tmp.getCantidadTotal()){
                        tmp.setCantidadTotal(tmp.getCantidadTotal()-cantidad);
                        cantidad=0;
                    }else{
                        cantidad-=tmp.getCantidadTotal();
                        tmp.setCantidadTotal(0);
                    }
                    //Despues de cambiar el equipo que cargue del prestamo, actualizo los
                    //Faltantes a ver si ya deja de serlo, luego actualizo en la DB
                    cargadosDePersona.get(i).getEquiposSencillosFaltantes();
                    System.out.println(cargadosDePersona.get(i).getElQuePideElPrestamo());
                    System.out.println(Arrays.toString(cargadosDePersona.get(i).getEquiposSencillosFaltantes().toArray()));
                    basePaciente.update(cargadosDePersona.get(i));
                }
            }
            //Deben haberse devuelto todos los equipos sencillos
            if(cantidad>0){
                throw new ExcepcionServicios("Se devolvieron demasiados equipos");
            }
        }catch(ExcepcionServicios|PersistenceException|PrestamoException e){
            daoF.rollbackTransaction();
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
        }
    }

    @Override
    public void registrarDevolucion(String equipo) throws ExcepcionServicios{
        try{
            daoF.beginSession();
            DAOEquipoComplejo dec=daoF.getDaoEquipoComplejo();
            basePaciente=daoF.getDaoPrestamo();
            //Cargo el equipo a partir de su placa
            EquipoComplejo loaded=dec.load(equipo);
            String[] estadosValidos={EquipoComplejo.diario, EquipoComplejo.p24h,EquipoComplejo.indefinido,EquipoComplejo.semestre};
            ArrayList<String> tmp=new ArrayList<>();
            tmp.addAll(Arrays.asList(estadosValidos));
            //Cambio el estado del equipo a "en almacen"
            if(!tmp.contains(loaded.getEstado()))throw new ExcepcionServicios("El equipo no esta prestado");
            loaded.setEstado(EquipoComplejo.almacen);
            loaded.setDisponibilidad(true);
            //Actualizo el equipo
            dec.update(loaded);
            
            List<Prestamo> prestamosEquipoCargado=basePaciente.loadByEquipoComplejo(loaded);
            //A los prestamos les actualizo los equipos complejos faltantes
            for(int i=0;i<tmp.size();i++){
                prestamosEquipoCargado.get(i).getEquiposComplejosFaltantes();
                basePaciente.update(prestamosEquipoCargado.get(i));
            }
            daoF.commitTransaction();
        }catch(PersistenceException e){
            daoF.rollbackTransaction();
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
        }
    }

    @Override
    public Persona personaCarne(String carne)throws ExcepcionServicios {
        Persona p;
        try{
            daoF.beginSession();
            DAOPersona dp=daoF.getDaoPersona();
            p=dp.loadPersRoles(carne);
        }catch(PersistenceException e){
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
        }
        return p;
    }
    
}
