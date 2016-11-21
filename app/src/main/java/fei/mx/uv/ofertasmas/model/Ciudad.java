package fei.mx.uv.ofertasmas.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Ciudad {

    @SerializedName("idCiudad")
    @Expose
    private int idCiudad;
    @SerializedName("nombreCuidad")
    @Expose
    private String nombreCuidad;
    @SerializedName("idEstado")
    @Expose
    private int idEstado;

    /**
     *
     * @return
     * The idCiudad
     */
    public int getIdCiudad() {
        return idCiudad;
    }

    /**
     *
     * @param idCiudad
     * The idCiudad
     */
    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    /**
     *
     * @return
     * The nombreCuidad
     */
    public String getNombreCuidad() {
        return nombreCuidad;
    }

    /**
     *
     * @param nombreCuidad
     * The nombreCuidad
     */
    public void setNombreCuidad(String nombreCuidad) {
        this.nombreCuidad = nombreCuidad;
    }

    /**
     *
     * @return
     * The idEstado
     */
    public int getIdEstado() {
        return idEstado;
    }

    /**
     *
     * @param idEstado
     * The idEstado
     */
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

}

