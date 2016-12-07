package fei.mx.uv.ofertasmas.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Oferta {

    @SerializedName("idOferta")
    @Expose
    private int idOferta;
    @SerializedName("nombreOferta")
    @Expose
    private String nombreOferta;
    @SerializedName("descripcionOferta")
    @Expose
    private String descripcionOferta;
    @SerializedName("precioArticulo")
    @Expose
    private double precioArticulo;
    @SerializedName("idEmpresa")
    @Expose
    private int idEmpresa;

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
     * The nombreOferta
     */
    public String getNombreOferta() {
        return nombreOferta;
    }

    /**
     *
     * @param nombreOferta
     * The nombreOferta
     */
    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

    /**
     *
     * @return
     * The descripcionOferta
     */
    public String getDescripcionOferta() {
        return descripcionOferta;
    }

    /**
     *
     * @param descripcionOferta
     * The descripcionOferta
     */
    public void setDescripcionOferta(String descripcionOferta) {
        this.descripcionOferta = descripcionOferta;
    }

    /**
     *
     * @return
     * The precioArticulo
     */
    public double getPrecioArticulo() {
        return precioArticulo;
    }

    /**
     *
     * @param precioArticulo
     * The precioArticulo
     */
    public void setPrecioArticulo(double precioArticulo) {
        this.precioArticulo = precioArticulo;
    }

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

    @Override
    public String toString() {
        return nombreOferta;
    }

}