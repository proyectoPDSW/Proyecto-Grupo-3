
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;
import org.primefaces.component.wizard.Wizard;
import org.primefaces.context.RequestContext;
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
    private boolean disponibilidad=true;
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
    private boolean showPanelRegistroModelo = false;
    private boolean showPanelInformacionModelo = false;
    private boolean showPanelRegistroExitoso = false;
    
    //volver a primera tab
    private boolean reset=true;

    /**
     * Constructor del Bean
     */
    public RegistroEquipoComplejoManagedBean() {
        SERVICIOS = ServiciosEquipoComplejo.getInstance();
        this.fechaAdquisicion = SERVICIOS.currDate();
        this.fechaGarantia = SERVICIOS.currDate();
    }

    /**
     * Resetea todos los parametros
     */
    public void limpiar() {
        setVidaUtil(0);
        setNombre("");
        setClase("");
        setValorComercial(0);
        setFotografia("");
        setDescripcion("");
        setAccesorios("");
        setSerial("");
        setPlaca("");
        setMarca("");
        setTiempoDeUso(0);
        proveedor="";
        codOrdenCompra="";
        codActivo="";
        this.fechaAdquisicion = SERVICIOS.currDate();
        this.fechaGarantia = SERVICIOS.currDate();
        showPanelRegistroModelo = false;
        showPanelInformacionModelo = false;
        showPanelRegistroExitoso = false;
        reset=true;
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
            setModelo(SERVICIOS.consultarModelo(nombreModelo));
            showPanelRegistroModelo = false;
            showPanelInformacionModelo = true;
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
            facesInfo("Por favor, registre el modelo!");
            showPanelRegistroModelo = true;
            showPanelInformacionModelo = false;
            setNombre(nombreModelo);
        }
    }

    public void registrarEquipo() {
        try {
            SERVICIOS.registrarEquipoComplejo(getEquipo());
            showPanelInformacionModelo = false;
            showPanelRegistroModelo = false;
            showPanelRegistroExitoso = true;
            reset=true;
            facesInfo("El equipo ha sido registrado satisfactoriamente");
            Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot().findComponent("registro:wiz");
            wizard.setStep("WInfoModelo");
            RequestContext.getCurrentInstance().update("registro");
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
        }
    }

    public void registrarEquipoModelo() {
        try {
            SERVICIOS.registrarEquipoComplejo(getEquipo());
            facesInfo("El equipo ha sido registrado satisfactoriamente");
            showPanelInformacionModelo = false;
            showPanelRegistroModelo = false;
            showPanelRegistroExitoso = true;
            reset=true;
        } catch (ExcepcionServicios ex) {
            facesError(ex.getMessage());
            Registro.anotar(ex);
        }
    }

    //////////////////////informacion Modelo

    /**
     * @return the modelo
     */
    public Modelo getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the vidaUtil
     */
    public int getVidaUtil() {
        return vidaUtil;
    }

    /**
     * @param vidaUtil the vidaUtil to set
     */
    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
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
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the accesorios
     */
    public String getAccesorios() {
        return accesorios;
    }

    /**
     * @param accesorios the accesorios to set
     */
    public void setAccesorios(String accesorios) {
        this.accesorios = accesorios;
    }

    //////////////////Informacion consulta
    /**
     * @param nombreModelo the nombreModelo to set
     */
    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
    /**
     * @return the nombreModelo
     */
    public String getNombreModelo() {
        return nombreModelo;
    }
/////////////////////////////////////////////////informacion Equipo
    
    /**
     * @return the equipo
     */
    public EquipoComplejo getEquipo() {
        return equipo;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(EquipoComplejo equipo) {
        this.equipo = equipo;
    }
 
    /**
     * @return the asegurado
     */
    public boolean isAsegurado() {
        return asegurado;
    }

    /**
     * @param asegurado the asegurado to set
     */
    public void setAsegurado(boolean asegurado) {
        this.asegurado = asegurado;
    }

    /**
     * @return the disponibilidad
     */
    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    /**
     * @param disponibilidad the disponibilidad to set
     */
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial the serial to set
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    /**
     * @return the tiempoDeUso
     */
    public int getTiempoDeUso() {
        return tiempoDeUso;
    }

    /**
     * @param tiempoDeUso the tiempoDeUso to set
     */
    public void setTiempoDeUso(int tiempoDeUso) {
        this.tiempoDeUso = tiempoDeUso;
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
        String pag = event.getNewStep();
        if (pag!=null && pag.equalsIgnoreCase("WInfoEquipo") && showPanelRegistroModelo) {
            try {
                setModelo(new Modelo(getVidaUtil(), getNombre(), getMarca(), getFotografia(), getClase(), getValorComercial()));
                getModelo().setDescripcion(getDescripcion());
                getModelo().setAccesorios(getAccesorios());
            } catch (EquipoException ex) {
                Registro.anotar(ex);
                facesError(ex.getMessage());
                pag = "WInfoModelo";
            }

        }
        else if(pag!=null && pag.equalsIgnoreCase("WConfirm")){
             try {
                if(fechaAdquisicion!=null && fechaGarantia!=null){
                    ordenCompra = new OrdenCompra();
                    setEquipo(new EquipoComplejo(getModelo(), getSerial(), getPlaca(), ordenCompra, getTiempoDeUso()));
                    getEquipo().setAsegurado(isAsegurado());
                    getEquipo().setPlaca(getPlaca());
                    getEquipo().setDisponibilidad(true);
                    getEquipo().setEstado("en almacen");
                    ordenCompra = new OrdenCompra(new Timestamp(fechaAdquisicion.getTime()), new Timestamp(fechaGarantia.getTime()), proveedor, codActivo, codOrdenCompra);
                    getEquipo().setOrdenCompra(ordenCompra);
                    if(isDisponibilidad()){
                        disponibilidadEquipo="Disponible";
                    }else{
                        disponibilidadEquipo="No disponible";
                    }
                    if(isAsegurado()){
                        aseguradoEquipo="Sí";
                    }else{
                        aseguradoEquipo="No";
                    }
                }else{
                    facesError("Por favor ingresar una fecha válida");
                    pag = "WInfoEquipo";
                }
                
            } catch (EquipoException ex) {
                Registro.anotar(ex);
                facesError(ex.getMessage());
                pag = "WInfoEquipo";
            }      
        }
        return pag;
    }

    public String onFlowProcessInformacion(FlowEvent event) {
        String pag = event.getNewStep();
        if (pag.equals("Wconfirm")) {
            try {
                if(fechaAdquisicion!=null && fechaGarantia!=null){
                    ordenCompra = new OrdenCompra();
                    setEquipo(new EquipoComplejo(getModelo(), getSerial(), getPlaca(), ordenCompra, getTiempoDeUso()));
                    getEquipo().setAsegurado(isAsegurado());
                    getEquipo().setPlaca(getPlaca());
                    getEquipo().setDisponibilidad(true);
                    getEquipo().setEstado("en almacen");
                    ordenCompra = new OrdenCompra(new Timestamp(fechaAdquisicion.getTime()), new Timestamp(fechaGarantia.getTime()), proveedor, codActivo, codOrdenCompra);
                    getEquipo().setOrdenCompra(ordenCompra);
                    if(isDisponibilidad()){
                        disponibilidadEquipo="Disponible";
                    }else{
                        disponibilidadEquipo="No disponible";
                    }
                    if(isAsegurado()){
                        aseguradoEquipo="Sí";
                    }else{
                        aseguradoEquipo="No";
                    }
                }else{
                    facesError("Por favor ingresar una fecha valida");
                    pag = "WInfoEquipo";
                }
            } catch (EquipoException ex) {
                Registro.anotar(ex);
                facesError(ex.getMessage());
                pag = "WInfoEquipo";
            }
        }
        return pag;
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
