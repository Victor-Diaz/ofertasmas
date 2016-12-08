package fei.mx.uv.ofertasmas.remoto

import android.content.Context
import java.util.concurrent.TimeUnit

import fei.mx.uv.ofertasmas.model.Categoria
import fei.mx.uv.ofertasmas.model.Ciudad
import fei.mx.uv.ofertasmas.model.Cupon
import fei.mx.uv.ofertasmas.model.Empresa
import fei.mx.uv.ofertasmas.model.Estado
import fei.mx.uv.ofertasmas.model.Mensaje
import fei.mx.uv.ofertasmas.model.Oferta
import fei.mx.uv.ofertasmas.model.RCategoriaOferta
import fei.mx.uv.ofertasmas.model.ROfertaCiudad
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RestAPI {

    @GET("api/categorias")
    fun categorias(): Call<List<Categoria>>

    @GET("api/ciudades")
    fun ciudades(): Call<Ciudad>

    @GET("api/ciudadesPorEstado/{idEstado}")
    fun ciudadesPorEstado(@Path("idEstado") idEstado: Int) : Call<List<Ciudad>>

    @GET("api/cupones")
    fun cupones(): Call<Cupon>

    @POST("api/cupon")
    fun generarCupon(@Body cupon: RequestBody): Call<Mensaje>

    @GET("api/empresas")
    fun empresas(): Call<Empresa>

    @GET("api/empresa/{idEmpresa}")
    fun empresaPorId(@Path("idEmpresa") idEmpresa: Int) : Call<Empresa>

    @GET("api/estados")
    fun estados(): Call<List<Estado>>

    @GET("api/ofertas")
    fun ofertas(): Call<Oferta>

    @GET("api/ofertaPorCategoria/{nombreCategoria}")
    fun getOfertasPorCategoria(@Path("nombreCategoria") nombreCat: String): Call<List<Oferta>>

    @GET("api/ofertas/{idCiudad}/{idCategoria}")
    fun getOfertasPorCiudadCategoria(@Path("idCiudad") idCiudad: Int,
                                     @Path("idCategoria") idCategoria: Int): Call<List<Oferta>>

    @GET("api/rCategoriaOfertas")
    fun rCategoriaOfertas(): Call<RCategoriaOferta>

    @GET("api/rOfertaCiudades")
    fun rOfertaCiudad(): Call<ROfertaCiudad>

    @POST("api/usuario")
    fun registrarUsuario(@Body usuario: RequestBody): Call<Mensaje>

    @FormUrlEncoded
    @POST("api/login")
    fun login(@Field("correo") correo: String,
              @Field("contrasena") contrasena: String) : Call<Mensaje>

    object Factory {
        fun getIstance(context: Context): RestAPI {
            val service: RestAPI
            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(15, TimeUnit.SECONDS)
            builder.connectTimeout(10, TimeUnit.SECONDS)
            builder.writeTimeout(10, TimeUnit.SECONDS)

            val cacheSize = 10 * 1024 * 1024 // 10 MiB
            val cache = Cache(context.cacheDir, cacheSize.toLong())
            builder.cache(cache)

            val retrofit = Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL).build()
            service = retrofit.create(RestAPI::class.java)
            return service
        }
    }

    companion object {
        val BASE_URL = "http://10.0.2.2:9000/"
    }


}
