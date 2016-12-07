package fei.mx.uv.ofertasmas.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Cupon {

    @SerializedName("codigoCupon")
    @Expose
    private String codigoCupon;
    @SerializedName("vigenciaCupon")
    @Expose
    private String vigenciaCupon;
    @SerializedName("idOferta")
    @Expose
    private int idOferta;
    @SerializedName("idUsuario")
    @Expose
    private String idUsuario;

    /**
     *
     * @return
     * The codigoCupon
     */
    public String getCodigoCupon() {
        return codigoCupon;
    }

    /**
     *
     * @param codigoCupon
     * The codigoCupon
     */
    public void setCodigoCupon(String codigoCupon) {
        this.codigoCupon = codigoCupon;
    }

    /**
     *
     * @return
     * The vigenciaCupon
     */
    public String getVigenciaCupon() {
        return vigenciaCupon;
    }

    /**
     *
     * @param vigenciaCupon
     * The vigenciaCupon
     */
    public void setVigenciaCupon(String vigenciaCupon) {
        this.vigenciaCupon = vigenciaCupon;
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

    @Override
    public String toString() {
        return codigoCupon;
    }
}