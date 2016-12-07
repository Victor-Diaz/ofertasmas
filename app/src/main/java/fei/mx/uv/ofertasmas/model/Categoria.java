package fei.mx.uv.ofertasmas.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Categoria {

    @SerializedName("idCategoria")
    @Expose
    private int idCategoria;
    @SerializedName("nombreCategoria")
    @Expose
    private String nombreCategoria;

    /**
     *
     * @return
     * The idCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     *
     * @param idCategoria
     * The idCategoria
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     *
     * @return
     * The nombreCategoria
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     *
     * @param nombreCategoria
     * The nombreCategoria
     */
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        return nombreCategoria;
    }

}