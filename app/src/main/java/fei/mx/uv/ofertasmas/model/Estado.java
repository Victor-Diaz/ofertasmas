package fei.mx.uv.ofertasmas.model;

/**
 * Created by hola1 on 21/11/2016.
 */

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Estado {

    @SerializedName("idEstado")
    @Expose
    private int idEstado;
    @SerializedName("nombreEstado")
    @Expose
    private String nombreEstado;

    /**
     * @return The idEstado
     */
    public int getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado The idEstado
     */
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * @return The nombreEstado
     */
    public String getNombreEstado() {
        return nombreEstado;
    }

    /**
     * @param nombreEstado The nombreEstado
     */
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

}

