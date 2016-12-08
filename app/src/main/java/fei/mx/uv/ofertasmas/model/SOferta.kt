package fei.mx.uv.ofertasmas.model

import java.io.Serializable

class SOferta constructor( val idOferta: Int,
                           val nombreOferta: String,
                           val descripcionOferta: String,
                           val precioArticulo: Double,
                           val idEmpresa: Int) : Serializable