package fei.mx.uv.ofertasmas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.pixplicity.easyprefs.library.Prefs
import fei.mx.uv.ofertasmas.model.*
import fei.mx.uv.ofertasmas.remoto.RestAPI
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp
import java.util.*

class OfertaActivity : AppCompatActivity() {

    private var oferta: SOferta? = null
    val OFERTA_STATUS = 1

    val tvNomOf by lazy { findViewById(R.id.tvNombreOferta) as TextView }
    val tvDescrOf by lazy { findViewById(R.id.tvDescripOfer) as TextView }
    val tvPrecio by lazy { findViewById(R.id.tvPrecio) as TextView }
    val tvNomEmp by lazy { findViewById(R.id.tvNomEmp) as TextView }
    val tvDirEmp by lazy { findViewById(R.id.tvDirEmp) as TextView }
    val tvTelEmp by lazy { findViewById(R.id.tvTelEmp) as TextView }
    val btnGenCup by lazy { findViewById(R.id.btnGenCup) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oferta)
        oferta = intent.getSerializableExtra("oferta") as SOferta
        fillDataOferta(oferta as SOferta)
        btnGenCup.setOnClickListener { generarCupon() }
    }

    private fun fillDataOferta(oferta: SOferta) {
        tvNomOf.text = oferta.nombreOferta
        tvDescrOf.text = oferta.descripcionOferta
        tvPrecio.text = oferta.precioArticulo.toString()
        getDataEmp(oferta.idEmpresa)
    }

    private fun getDataEmp(idEmpresa: Int) {
        val call = RestAPI.Factory.getIstance(this@OfertaActivity).empresaPorId(idEmpresa)
        call.enqueue( object : Callback<Empresa> {
            override fun onResponse(call: Call<Empresa>?, response: Response<Empresa>?) {
                fillDataEmpresa(response?.body())
            }
            override fun onFailure(call: Call<Empresa>?, t: Throwable?) {
                Toast.makeText(this@OfertaActivity, t?.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fillDataEmpresa(body: Empresa?) {
        tvNomEmp.text = body?.nombreEmpresa
        tvDirEmp.text = body?.direccionEmpresa
        tvTelEmp.text = body?.telefonoEmpresa
    }

    private fun generarCupon() {
        val correoUsuario = Prefs.getString("correoUsuario", "not_set")
        if (correoUsuario == "not_set") {
            val intent = Intent(this@OfertaActivity, LoginActivity::class.java)
            startActivityForResult(intent, OFERTA_STATUS)
        } else {
            crearCupon(correoUsuario)
        }
    }

    private fun crearCupon(correoUsuario: String) {
        val body = RequestBody.create(
                okhttp3.MediaType.parse("application/json; charset=utf-8"),
                crearJson(correoUsuario))
        val call = RestAPI.Factory.getIstance(this@OfertaActivity).generarCupon(body)
        call.enqueue( object : Callback<Mensaje> {
            override fun onResponse(call: Call<Mensaje>?, response: Response<Mensaje>) {
                val res = response.body()
                //TODO ir a actividad de Cupones
                if (!res.error) {
                    Toast.makeText(this@OfertaActivity, res.mensaje, Toast.LENGTH_LONG).show()
                }
                else  Toast.makeText(this@OfertaActivity, res.mensaje, Toast.LENGTH_LONG).show()
            }
            override fun onFailure(call: Call<Mensaje>?, t: Throwable?) {
                Toast.makeText(this@OfertaActivity, t?.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun crearJson(correo: String): String {
        val cupon = Cupon(0, Timestamp(Calendar.getInstance().time.time).toString(), oferta?.idOferta ?: 0, correo, 1)
        return Gson().toJson(cupon)
    }

}
