package fei.mx.uv.ofertasmas.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Empresa {

    @SerializedName("idEmpresa")
    @Expose
    private int idEmpresa;
    @SerializedName("nombreEmpresa")
    @Expose
    private String nombreEmpresa;
    @SerializedName("direccionEmpresa")
    @Expose
    private String direccionEmpresa;
    @SerializedName("telefonoEmpresa")
    @Expose
    private String telefonoEmpresa;

    /**
     *
     * @return
     * The idEmpresa
     */
    public int getIdEmpresa() {
        return idEmpresa;
    }

    /**
     *
     * @param idEmpresa
     * The idEmpresa
     */
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     *
     * @return
     * The nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     *
     * @param nombreEmpresa
     * The nombreEmpresa
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     *
     * @return
     * The direccionEmpresa
     */
    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    /**
     *
     * @param direccionEmpresa
     * The direccionEmpresa
     */
    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    /**
     *
     * @return
     * The telefonoEmpresa
     */
    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    /**
     *
     * @param telefonoEmpresa
     * The telefonoEmpresa
     */
    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

}

