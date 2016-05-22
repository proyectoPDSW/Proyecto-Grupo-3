
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.managedbeans;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoException;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.entities.OrdenCompra;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.servicios.ExcepcionServicios;
import edu.eci.pdsw.servicios.ServiciosEquipoComplejo;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Daniela Sepulveda
 */
@ManagedBean(name = "RegistroEquiposComplejos")
@SessionScoped

public class RegistroEquipoComplejoManagedBean implements Serializable {

    private final ServiciosEquipoComplejo SERVICIOS;

    @Size(min = 1, max = 50, message = "Es un campo requerido*")
    private String nombreModelo;

    private Modelo modelo;

        private int vidaUtil;
        private String nombre;
        private String clase;
        private String marca;
        private long valorComercial;
        private String fotografia;
        private String descripcion;
        private String accesorios;

    private EquipoComplejo equipo;

        private boolean asegurado;
        private boolean disponibilidad;
        private String estado;
        private String serial;
        private String placa;
        private int tiempoDeUso;
    
    private OrdenCompra ordenCompra;
    
        private String proveedor;
        private String codOrdenCompra;
        private String codActivo;
        private Date fechaAdquisicion;
        private Date fechaGarantia;
    
    private String aseguradoEquipo = "";
    private String disponibilidadEquipo = "";
    
    ////////Mostrar paneles
    private boolean showPanelRegistroModelo=false;
    private boolean showPanelInformacionModelo=false;
    private boolean showPanelRegistroExitoso=false;

    /**
     * Constructor del Bean
     */
    public RegistroEquipoComplejoManagedBean() {
        this.fechaAdquisicion = new Date();
        SERVICIOS = ServiciosEquipoComplejo.getInstance();
    }

    /**
     * Resetea todos los parametros
     */
    public void limpiar() {
        vidaUtil = 0;
        nombre = "";
        clase = "";
        valorComercial = 0;
        fotografia = "";
        descripcion = "";
        accesorios = "";
        serial = "";
        placa = "0";
        marca = "";
        tiempoDeUso=0;
    }

    /**
     * Obtiene una lista de modelos por nombre aproximado
     * 
     * @param query la consulta
     * @return los modelos que se parecen a la consulta
     */
    public List<String> modelosAproximados(String query) {
        List<String> aproximados = new ArrayList<>();
        try {
            aproximados = SERVICIOS.consultarAproximado(query);
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
        }
        return aproximados;
    }

    /**
     * Consultar un modelo por su nombre
     */

    public void consultarModelo() {
        try {
            modelo = SERVICIOS.consultarModelo(nombreModelo);
            showPanelRegistroModelo=false;
            showPanelInformacionModelo=true;
            limpiar();
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
            facesInfo("Por favor, registre el modelo!");
            showPanelRegistroModelo=true;
            showPanelInformacionModelo=false;
        }
    }

    public void registrarEquipo() {
        try {
            equipo = new EquipoComplejo(modelo,serial, placa, getOrdenCompra(),tiempoDeUso);
            equipo.setAsegurado(asegurado);
            equipo.setPlaca(placa);
            equipo.setDisponibilidad(true);
            equipo.setEstado("Activo");
            SERVICIOS.registrarEquipoComplejo(equipo);
            facesInfo("El equipo ha sido registrado satisfactoriamente");
        } catch (ExcepcionServicios | EquipoException ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
        }
    }

    public void registrarEquipoModelo() {
        try {
            modelo = new Modelo(vidaUtil, nombre,marca, fotografia, clase, valorComercial);
            modelo.setDescripcion(descripcion);
            modelo.setAccesorios(accesorios);
            //SERVICIOS.registrarModelo(modelo);
            equipo = new EquipoComplejo(modelo,serial, placa, getOrdenCompra(),tiempoDeUso);
            equipo.setModelo_Eq(modelo);
            equipo.setAsegurado(asegurado);
            equipo.setPlaca(placa);
            equipo.setDisponibilidad(true);
            equipo.setEstado("en almacen");
            SERVICIOS.registrarEquipoComplejo(equipo);
            facesInfo("El equipo ha sido registrado satisfactoriamente");
            showPanelInformacionModelo=false;
            showPanelRegistroModelo=false;
            showPanelRegistroExitoso=true;
        } catch (ExcepcionServicios | EquipoException ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
        }
    }

    //////////////////////informacion Modelo
    public int getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public long getValorComercial() {
        return valorComercial;
    }

    public void setValorComercial(long valorComercial) {
        this.valorComercial = valorComercial;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(String accesorios) {
        this.accesorios = accesorios;

    }

    public Modelo getModelo() {
        return modelo;
    }

    //////////////////Informacion consulta
    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }
/////////////////////////////////////////////////informacion Equipo

    public EquipoComplejo getEquipo() {
        return equipo;
    }

    public boolean isAsegurado() {
        return asegurado;
    }

    public void setAsegurado(boolean asegurado) {
        this.asegurado = asegurado;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
///////////////////Informacion orden compra
    
    /**
     * @return the ordenCompra
     */
    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    /**
     * @param ordenCompra the ordenCompra to set
     */
    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
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

    /**
     * @return the codOrdenCompra
     */
    public String getCodOrdenCompra() {
        return codOrdenCompra;
    }

    /**
     * @param codOrdenCompra the codOrdenCompra to set
     */
    public void setCodOrdenCompra(String codOrdenCompra) {
        this.codOrdenCompra = codOrdenCompra;
    }

    /**
     * @return the codActivo
     */
    public String getCodActivo() {
        return codActivo;
    }

    /**
     * @param codActivo the codActivo to set
     */
    public void setCodActivo(String codActivo) {
        this.codActivo = codActivo;
    }

    /**
     * @return the fechaAdquisicion
     */
    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    /**
     * @param fechaAdquisicion the fechaAdquisicion to set
     */
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    /**
     * @return the fechaGarantia
     */
    public Date getFechaGarantia() {
        return fechaGarantia;
    }

    /**
     * @param fechaGarantia the fechaGarantia to set
     */
    public void setFechaGarantia(Date fechaGarantia) {
        this.fechaGarantia = fechaGarantia;
    }
///////////////////////////////////////////////////Mensajes
    /**
     * Muestra un mensaje de error en la vista
     *
     * @param message Mensaje de error
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + message, null));
    }

    /**
     * Muestra un mensaje de informacion en la vista
     *
     * @param message Mensaje de informativo
     */
    public void facesInfo(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    /**
     * Muestra un mensaje de alarma en la vista
     *
     * @param message Mensaje de Alarma
     */
    public void facesWarn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }

    /**
     * Muestra un mensaje de error grave en la vista
     *
     * @param message Mensaje fatal
     */
    public void facesFatal(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, null));
    }
////////////////////////////////datos registro exitoso

    public String getAseguradoEquipo() {
        return aseguradoEquipo;
    }

    public void setAseguradoEquipo(String aseguradoEquipo) {
        this.aseguradoEquipo = aseguradoEquipo;
    }

    public String getDisponibilidadEquipo() {
        return disponibilidadEquipo;
    }

    public void setDisponibilidadEquipo(String disponibilidadEquipo) {
        this.disponibilidadEquipo = disponibilidadEquipo;
    }
    
    //// Wizard
    public String onFlowProcessRegistro(FlowEvent event) {
        String pag=event.getNewStep();
        if(pag.equals("W1InfoEquipo")){
            
        }
        return pag;
    }
       public String onFlowProcessInformacion(FlowEvent event) {
        return event.getNewStep();
    }
////////Mostrar paneles
    /**
     * @return the showPanelRegistroModelo
     */
    public boolean showPanelRegistroModelo() {
        return showPanelRegistroModelo;
    }

    /**
     * @return the showPanelConsultaModelo
     */
    public boolean showPanelInformacionModelo() {
        return showPanelInformacionModelo;
    }
    /**
     * @return the showPanelConsultaModelo
     */
    public boolean showPanelRegistroExitoso() {
        return showPanelRegistroExitoso;
    }
}
