package fei.mx.uv.ofertasmas.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Usuario {

    @SerializedName("correoUsuario")
    @Expose
    private String correoUsuario;
    @SerializedName("nombreUsuario")
    @Expose
    private String nombreUsuario;
    @SerializedName("contrasenaUsuario")
    @Expose
    private String contrasenaUsuario;
    @SerializedName("celularUsuario")
    @Expose
    private String celularUsuario;

    /**
     *
     * @return
     * The correoUsuario
     */
    public String getCorreoUsuario() {
        return correoUsuario;
    }

    /**
     *
     * @param correoUsuario
     * The correoUsuario
     */
    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    /**
     *
     * @return
     * The nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     *
     * @param nombreUsuario
     * The nombreUsuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     *
     * @return
     * The contrasenaUsuario
     */
    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    /**
     *
     * @param contrasenaUsuario
     * The contrasenaUsuario
     */
    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    /**
     *
     * @return
     * The celularUsuario
     */
    public String getCelularUsuario() {
        return celularUsuario;
    }

    /**
     *
     * @param celularUsuario
     * The celularUsuario
     */
    public void setCelularUsuario(String celularUsuario) {
        this.celularUsuario = celularUsuario;
    }

}