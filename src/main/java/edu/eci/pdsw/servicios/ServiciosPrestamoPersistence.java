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
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.DAOEquipoSencillo;
import edu.eci.pdsw.persistence.DAOFactory;
import edu.eci.pdsw.persistence.DAOPersona;
import edu.eci.pdsw.persistence.DAOPrestamo;
import edu.eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian Devia
 */
public class ServiciosPrestamoPersistence extends ServiciosPrestamo {

    private DAOPrestamo basePaciente;
    private DAOFactory daoF;

    public ServiciosPrestamoPersistence() {
        try {

            InputStream input = getClass().getClassLoader().getResource("applicationconfig.properties").openStream();

            Properties properties = new Properties();
            properties.load(input);
            daoF = DAOFactory.getInstance(properties);

        } catch (IOException ex) {
            Registro.anotar(ex);
        }
    }

    @Override
    public List<Prestamo> consultarPrestamosMorosos() throws ExcepcionServicios {
        List<Prestamo> morosos;
        try {
            daoF.beginSession();
            basePaciente = daoF.getDaoPrestamo();
            morosos = basePaciente.loadMorosos();
            //Collections.sort(morosos);
            return morosos;
        } catch (PersistenceException e) {
            throw new ExcepcionServicios(e, e.getLocalizedMessage());
        } finally {
            daoF.endSession();
        }

    }

    @Override
    public List<Prestamo> consultarPrestamosPersona(String p) throws ExcepcionServicios {
        List<Prestamo> prestamos;
        try {
            daoF.beginSession();
            basePaciente = daoF.getDaoPrestamo();
            prestamos = basePaciente.loadByCarne(p);
            Collections.sort(prestamos);
            return prestamos;
        } catch (PersistenceException e) {
            throw new ExcepcionServicios(e, e.getLocalizedMessage());
        } finally {
            daoF.endSession();

        }
    }

    @Override
    public List<Prestamo> consultarPrestamosEquipoComplejo(EquipoComplejo ec) throws ExcepcionServicios {
        List<Prestamo> prestamos;
        try {
            daoF.beginSession();
            basePaciente = daoF.getDaoPrestamo();
            prestamos = basePaciente.loadByEquipoComplejo(ec);
            Collections.sort(prestamos);
            return prestamos;
        } catch (PersistenceException e) {
            throw new ExcepcionServicios(e, e.getLocalizedMessage());
        } finally {
            daoF.endSession();
            //return prestamos;
        }
    }

    @Override
    public List<Prestamo> consultarTodos() throws ExcepcionServicios {
        List<Prestamo> prestamos;
        try {
            daoF.beginSession();
            basePaciente = daoF.getDaoPrestamo();
            prestamos = basePaciente.loadAll();
            Collections.sort(prestamos);
            return prestamos;
        } catch (PersistenceException e) {
            throw new ExcepcionServicios(e, e.getLocalizedMessage());
        } finally {
            daoF.endSession();
        }
    }

    @Override
    public void registrarPrestamo(Prestamo pres) throws ExcepcionServicios {
        try {
            daoF.beginSession();
            basePaciente = daoF.getDaoPrestamo();
            if (pres == null)throw new ExcepcionServicios("El prestamo no puede ser nulo");
            Prestamo m = basePaciente.load(pres.getFechaInicio(), pres.getElQuePideElPrestamo().getCarnet());
            if(m!=null) throw new ExcepcionServicios("El prestamo ya existe.");
            /*
            Set<EquipoComplejo> equiposC = new HashSet<>(pres.getEquiposComplejosPrestados());
            Set<EquipoSencillo> equiposS = new HashSet<>(pres.getEquiposSencillosPrestados());
            boolean fp = true;
            int cant = equiposC.size();
            while (cant != 0) {
                EquipoComplejo ec = null;
                Set<EquipoComplejo> aAgregar = new HashSet<>();
                boolean first = true;
                for (EquipoComplejo e : equiposC) {
                    if (first) {
                        ec = e;
                        aAgregar.add(e);
                        first = false;
                    } else if (e.getEstado().equals(ec.getEstado())) {
                        aAgregar.add(e);
                    }
                }
                for (EquipoComplejo e : aAgregar) {
                    equiposC.remove(e);
                }
                Prestamo guardar = null;
                if (ec.getEstado().equalsIgnoreCase(EquipoComplejo.indefinido)) {
                    guardar = new PrestamoIndefinido(pres.getElQuePideElPrestamo(), aAgregar, null);
                } else {
                    guardar = new PrestamoTerminoFijo(pres.getElQuePideElPrestamo(), aAgregar, null, Prestamo.calcularFechaEstimada(ec.getEstado()), ec.getEstado());
                }
                if (fp) {
                    guardar.setEquiposSencillosPrestados(equiposS);
                    fp = false;
                }
                basePaciente.save(guardar);
                Thread.sleep(1000);
                cant = equiposC.size();
            }
            if (fp) {
                Prestamo guardar = new PrestamoTerminoFijo(pres.getElQuePideElPrestamo(), null, equiposS, Prestamo.calcularFechaEstimada(EquipoComplejo.p24h), EquipoComplejo.p24h);
                basePaciente.save(guardar);
                Thread.sleep(1000);
            }*/
            basePaciente.save(pres);
            daoF.commitTransaction();
        } catch (PersistenceException e) {
            daoF.rollbackTransaction();
            throw new ExcepcionServicios(e, e.getLocalizedMessage());
        } finally {

            daoF.endSession();
        }
    }

    @Override
    public void registarDevolucion(String persona, String equipo, int cantidad) throws ExcepcionServicios {
        try {
            daoF.beginSession();
            DAOEquipoSencillo des = daoF.getDaoEquipoSencillo();
            basePaciente = daoF.getDaoPrestamo();
            EquipoSencillo loaded = des.load(equipo);
            //Cargo a la persona que pidio el prestamo y sus prestamos
            List<Prestamo> cargadosDePersona = basePaciente.loadByCarne(persona);
            for (int i = 0; i < cargadosDePersona.size() && cantidad > 0; i++) {
                //Para cada prestamo donde me falte entregar algo del equipo lo entrego
                //Asi hasta que la cantidad que estoy entregando es 0

                if (cargadosDePersona.get(i).isFaltante(loaded)) {
                    EquipoSencillo tmp = cargadosDePersona.get(i).getSencillo(loaded);
                    if (cantidad <= tmp.getCantidadTotal()) {
                        tmp.setCantidadTotal(tmp.getCantidadTotal() - cantidad);
                        cantidad = 0;
                    } else {
                        cantidad -= tmp.getCantidadTotal();
                        tmp.setCantidadTotal(0);
                    }
                    //Despues de cambiar el equipo que cargue del prestamo, actualizo los
                    //Faltantes a ver si ya deja de serlo, luego actualizo en la DB
                    cargadosDePersona.get(i).getEquiposSencillosFaltantes();
                    basePaciente.update(cargadosDePersona.get(i));
                }
            }
            //Deben haberse devuelto todos los equipos sencillos
            if (cantidad > 0) {
                throw new ExcepcionServicios("Se devolvieron demasiados equipos");
            }
            daoF.commitTransaction();
        } catch (ExcepcionServicios | PersistenceException | PrestamoException e) {
            daoF.rollbackTransaction();
            throw new ExcepcionServicios(e, e.getLocalizedMessage());
        } finally {
            daoF.endSession();
        }
    }

    @Override
    public void registrarDevolucion(String equipo) throws ExcepcionServicios {
        try {
            daoF.beginSession();
            DAOEquipoComplejo dec = daoF.getDaoEquipoComplejo();
            basePaciente = daoF.getDaoPrestamo();
            //Cargo el equipo a partir de su placa
            EquipoComplejo loaded = dec.load(equipo);
            String[] estadosValidos = {EquipoComplejo.diario, EquipoComplejo.p24h, EquipoComplejo.indefinido, EquipoComplejo.semestre};
            ArrayList<String> tmp = new ArrayList<>();
            tmp.addAll(Arrays.asList(estadosValidos));
            if (!tmp.contains(loaded.getEstado())) {
                throw new ExcepcionServicios("El equipo no esta prestado");
            }
            //Cargo el prestamo actual de ese equipo
            Prestamo prestamoActualEquipoCargado = basePaciente.loadPrestamoActual(loaded);
            //Cambio el equipo para que se pueda volver a prestar
            loaded.setEstado(EquipoComplejo.almacen);
            loaded.setDisponibilidad(true);
            //Resto las fechas para saber cuantos milisegundos estuvo prestado
            long diff = Prestamo.currDate().getTime() - prestamoActualEquipoCargado.getFechaInicio().getTime();
            //El resultado esta en milisegundos, lo paso a horas
            loaded.setTiempoRestante(loaded.getTiempoRestante() - ((diff / 1000) / 3600));
            //Actualizo el equipo en el prestamo
            prestamoActualEquipoCargado.updateEquipoComplejo(loaded);
            //Actualizo el equipo y el prestamo en la base de datos
            dec.update(loaded);
            basePaciente.update(prestamoActualEquipoCargado);
            daoF.commitTransaction();
        } catch (PersistenceException |PrestamoException e) {
            daoF.rollbackTransaction();
            throw new ExcepcionServicios(e, e.getLocalizedMessage());
        } finally {
            daoF.endSession();
        }
    }

    @Override
    public Persona personaCarne(String carne) throws ExcepcionServicios {
        Persona p;
        try {
            daoF.beginSession();
            DAOPersona dp = daoF.getDaoPersona();
            p = dp.loadPersRoles(carne);
        } catch (PersistenceException e) {
            throw new ExcepcionServicios(e, e.getLocalizedMessage());
        } finally {
            daoF.endSession();
        }
        return p;
    }

    @Override
    public Timestamp currDate() {
        Timestamp now = null;
        daoF.beginSession();
        basePaciente = daoF.getDaoPrestamo();
        now = basePaciente.currDate();
        daoF.endSession();
        return now;

    }

    @Override
    public Prestamo consultarPrestamosPrestamo(Prestamo p) throws ExcepcionServicios {
        try {
            if (p == null) {
                throw new ExcepcionServicios("El prestamo a consultar no debe ser nulo");
            }
            daoF.beginSession();
            basePaciente = daoF.getDaoPrestamo();
            Prestamo p1 = basePaciente.load(p.getFechaInicio(), p.getElQuePideElPrestamo().getCarnet());
            return p1;
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex, ex.getLocalizedMessage());
        } finally {
            daoF.endSession();
        }
    }

    @Override
    public void actualizarPrestamo(Prestamo p) throws ExcepcionServicios {
        try {
            daoF.beginSession();
            basePaciente = daoF.getDaoPrestamo();
            basePaciente.update(p);
            daoF.commitTransaction();
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex, ex.getLocalizedMessage());
        } finally {
            daoF.endSession();
        }

    }

    @Override
    public long diffHours(Prestamo prestamo) {
        long diff;
        daoF.beginSession();
        basePaciente =daoF.getDaoPrestamo();
        diff=basePaciente.diffHours(prestamo);
        daoF.endSession();
        return diff;
    }

}
