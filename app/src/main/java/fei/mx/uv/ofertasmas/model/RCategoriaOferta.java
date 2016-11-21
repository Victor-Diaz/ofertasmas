package fei.mx.uv.ofertasmas.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class RCategoriaOferta {

    @SerializedName("idRCategoriaOferta")
    @Expose
    private int idRCategoriaOferta;
    @SerializedName("idCategoria")
    @Expose
    private int idCategoria;
    @SerializedName("idOferta")
    @Expose
    private int idOferta;

    /**
     *
     * @return
     * The idRCategoriaOferta
     */
    public int getIdRCategoriaOferta() {
        return idRCategoriaOferta;
    }

    /**
     *
     * @param idRCategoriaOferta
     * The idRCategoriaOferta
     */
    public void setIdRCategoriaOferta(int idRCategoriaOferta) {
        this.idRCategoriaOferta = idRCategoriaOferta;
    }

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

}