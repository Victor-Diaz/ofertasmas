package fei.mx.uv.ofertasmas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Cupon {

    @SerializedName("codigoCupon")
    @Expose
    private int codigoCupon;
    @SerializedName("fechaCreacion")
    @Expose
    private String fechaCreacion;
    @SerializedName("idOferta")
    @Expose
    private int idOferta;
    @SerializedName("idUsuario")
    @Expose
    private String idUsuario;
    @SerializedName("idEstadoCupon")
    @Expose
    private int idEstadoCupon;

    /**
     *
     * @param idEstadoCupon
     * @param idUsuario
     * @param codigoCupon
     * @param idOferta
     * @param fechaCreacion
     */
    public Cupon(int codigoCupon, String fechaCreacion, int idOferta, String idUsuario, int idEstadoCupon) {
        this.codigoCupon = codigoCupon;
        this.fechaCreacion = fechaCreacion;
        this.idOferta = idOferta;
        this.idUsuario = idUsuario;
        this.idEstadoCupon = idEstadoCupon;
    }

    /**
     *
     * @return
     * The codigoCupon
     */
    public int getCodigoCupon() {
        return codigoCupon;
    }

    /**
     *
     * @param codigoCupon
     * The codigoCupon
     */
    public void setCodigoCupon(int codigoCupon) {
        this.codigoCupon = codigoCupon;
    }

    /**
     *
     * @return
     * The fechaCreacion
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     *
     * @param fechaCreacion
     * The fechaCreacion
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     *
     * @return
     * The idOferta
     */
    public int getIdOferta() {
        return idOferta;
    }

    /**
     *
     * @param idOferta
     * The idOferta
     */
    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    /**
     *
     * @return
     * The idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     *
     * @param idUsuario
     * The idUsuario
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     *
     * @return
     * The idEstadoCupon
     */
    public int getIdEstadoCupon() {
        return idEstadoCupon;
    }

    /**
     *
     * @param idEstadoCupon
     * The idEstadoCupon
     */
    public void setIdEstadoCupon(int idEstadoCupon) {
        this.idEstadoCupon = idEstadoCupon;
    }

}