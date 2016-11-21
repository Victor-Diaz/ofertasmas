package fei.mx.uv.ofertasmas.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ROfertaCiudad {

    @SerializedName("idROfertaCuidad")
    @Expose
    private int idROfertaCuidad;
    @SerializedName("idOferta")
    @Expose
    private int idOferta;
    @SerializedName("idCuidad")
    @Expose
    private int idCuidad;

    /**
     *
     * @return
     * The idROfertaCuidad
     */
    public int getIdROfertaCuidad() {
        return idROfertaCuidad;
    }

    /**
     *
     * @param idROfertaCuidad
     * The idROfertaCuidad
     */
    public void setIdROfertaCuidad(int idROfertaCuidad) {
        this.idROfertaCuidad = idROfertaCuidad;
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
     * The idCuidad
     */
    public int getIdCuidad() {
        return idCuidad;
    }

    /**
     *
     * @param idCuidad
     * The idCuidad
     */
    public void setIdCuidad(int idCuidad) {
        this.idCuidad = idCuidad;
    }

}