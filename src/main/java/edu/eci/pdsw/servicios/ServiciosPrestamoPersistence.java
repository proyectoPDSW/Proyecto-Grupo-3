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

    private DAOPrestamo prestamoDao;
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
            prestamoDao = daoF.getDaoPrestamo();
            morosos = prestamoDao.loadMorosos();
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
            prestamoDao = daoF.getDaoPrestamo();
            prestamos = prestamoDao.loadByCarne(p);
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
            prestamoDao = daoF.getDaoPrestamo();
            prestamos = prestamoDao.loadByEquipoComplejo(ec);
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
            prestamoDao = daoF.getDaoPrestamo();
            prestamos = prestamoDao.loadAll();
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
            prestamoDao = daoF.getDaoPrestamo();
            if (pres == null) {
                throw new ExcepcionServicios("El prestamo no puede ser nulo");
            }
            Prestamo m = prestamoDao.load(pres.getFechaInicio(), pres.getElQuePideElPrestamo().getCarnet());
            if (m != null) {
                throw new ExcepcionServicios("El prestamo ya existe.");
            }
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
                prestamoDao.save(guardar);
                Thread.sleep(1000);
                cant = equiposC.size();
            }
            if (fp) {
                Prestamo guardar = new PrestamoTerminoFijo(pres.getElQuePideElPrestamo(), null, equiposS, Prestamo.calcularFechaEstimada(EquipoComplejo.p24h), EquipoComplejo.p24h);
                prestamoDao.save(guardar);
                Thread.sleep(1000);
            }*/
            prestamoDao.save(pres);
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
            prestamoDao = daoF.getDaoPrestamo();
            EquipoSencillo loaded = des.load(equipo);
            loaded.setCantidadTotal(loaded.getCantidadTotal() + cantidad);
            //Cargo a la persona que pidio el prestamo y sus prestamos
            Prestamo cargadosDePersona = prestamoDao.loadActualPersona(persona);
            EquipoSencillo tmp = cargadosDePersona.getSencillo(loaded);
            if (cantidad <= tmp.getCantidadTotal()) {
                tmp.setCantidadTotal(tmp.getCantidadTotal() - cantidad);
                cantidad = 0;
            } else {
                cantidad -= tmp.getCantidadTotal();
                tmp.setCantidadTotal(0);
            }
            //Despues de cambiar el equipo que cargue del prestamo, actualizo los
            //Faltantes a ver si ya deja de serlo, luego actualizo en la DB
            cargadosDePersona.getEquiposSencillosFaltantes();
            prestamoDao.update(cargadosDePersona);
            //Deben haberse devuelto todos los equipos sencillos
            if (cantidad > 0) {
                throw new ExcepcionServicios("Se devolvieron demasiados equipos");
            }
            des.update(loaded);
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
            prestamoDao = daoF.getDaoPrestamo();
            //Cargo el equipo a partir de su placa
            EquipoComplejo loaded = dec.load(equipo);
            String[] estadosValidos = {EquipoComplejo.diario, EquipoComplejo.p24h, EquipoComplejo.indefinido, EquipoComplejo.semestre};
            ArrayList<String> tmp = new ArrayList<>();
            tmp.addAll(Arrays.asList(estadosValidos));
            if (!tmp.contains(loaded.getEstado())) {
                throw new ExcepcionServicios("El equipo no esta prestado");
            }
            //Cargo el prestamo actual de ese equipo
            Prestamo prestamoActualEquipoCargado = prestamoDao.loadPrestamoActual(loaded);
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
            prestamoDao.update(prestamoActualEquipoCargado);
            daoF.commitTransaction();
        } catch (PersistenceException | PrestamoException e) {
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
        prestamoDao = daoF.getDaoPrestamo();
        now = prestamoDao.currDate();
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
            prestamoDao = daoF.getDaoPrestamo();
            Prestamo p1 = prestamoDao.load(p.getFechaInicio(), p.getElQuePideElPrestamo().getCarnet());
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
            prestamoDao = daoF.getDaoPrestamo();
            prestamoDao.update(p);
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
        prestamoDao = daoF.getDaoPrestamo();
        diff = prestamoDao.diffHours(prestamo);
        daoF.endSession();
        return diff;
    }

    @Override
    public void devolverTodo(String carnet) throws ExcepcionServicios {
        try {
            daoF.beginSession();
            DAOEquipoComplejo dec = daoF.getDaoEquipoComplejo();
            DAOEquipoSencillo des = daoF.getDaoEquipoSencillo();
            prestamoDao = daoF.getDaoPrestamo();
            Prestamo p = prestamoDao.loadActualPersona(carnet);
            for (EquipoComplejo ec : p.getEquiposComplejosFaltantes()) {
                ec.setEstado(EquipoComplejo.almacen);
                ec.setDisponibilidad(true);
                long diff = Prestamo.currDate().getTime() - p.getFechaInicio().getTime();
                ec.setTiempoRestante(ec.getTiempoRestante() - ((diff / 1000) / 3600));
                p.updateEquipoComplejo(ec);
                dec.update(ec);
                prestamoDao.update(p);
            }
            for (EquipoSencillo es : p.getEquiposSencillosFaltantes()) {
                EquipoSencillo loaded = des.load(es.getNombre());
                loaded.setCantidadTotal(loaded.getCantidadTotal() + es.getCantidadTotal());
                es.setCantidadTotal(0);
                des.update(loaded);
                prestamoDao.update(p);
            }
            daoF.commitTransaction();
        } catch (PersistenceException | PrestamoException ex) {
            daoF.rollbackTransaction();
            throw new ExcepcionServicios(ex, ex.getLocalizedMessage());
        } finally {
            daoF.endSession();
        }
    }

}