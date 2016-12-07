package fei.mx.uv.ofertasmas

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import fei.mx.uv.ofertasmas.model.Mensaje
import fei.mx.uv.ofertasmas.remoto.RestAPI
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registro : AppCompatActivity() {

    private val edtCorreo by lazy { findViewById(R.id.edtCorreo) as EditText }
    private val edtTelefono by lazy { findViewById(R.id.edtTelefono) as EditText }
    private val edtUsuario by lazy { findViewById(R.id.edtUsuarrio) as EditText }
    private val edtContrasena by lazy { findViewById(R.id.edtContrasena) as EditText }
    private val btnRegistrar by lazy { findViewById(R.id.btnRegistrar) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        btnRegistrar.setOnClickListener { registrar() }
    }

    fun esValido(): Boolean {
        val correo = edtCorreo.text.isNullOrEmpty()
        val telefono = edtTelefono.text.isNullOrEmpty()
        val usuario = edtUsuario.text.isNullOrEmpty()
        val contrasena = edtContrasena.text.isNullOrEmpty()
        return !(correo || telefono || usuario || contrasena)
    }

    fun registrar() {
        if (esValido()) {
            val dialogo = AlertDialog.Builder(this@Registro).create()
            dialogo.setTitle("Registro de Usuario")
            dialogo.setMessage("¿Esta seguro de haber terminado el registro?")
            dialogo.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar") { dialog, which -> registrarUsuario() }
            dialogo.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar") { dialog, which -> dialog.dismiss() }
            dialogo.show()
        }
    }

    fun registrarUsuario() {
        val body = RequestBody.create(
                okhttp3.MediaType
                .parse("application/json; charset=utf-8"),
                crearJson().toString())
        val call = RestAPI.Factory.getIstance(this@Registro).registrarUsuario(body)
        call.enqueue(object : Callback<Mensaje> {
            override fun onResponse(call: Call<Mensaje>, response: Response<Mensaje>) {
                if (!response.body().error) {
                    Toast.makeText(this@Registro, response.body().mensaje, Toast.LENGTH_LONG).show()
                    val intent = Intent(this@Registro, LoginActivity::class.java)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this@Registro, response.body().mensaje, Toast.LENGTH_LONG).show()
            }
            override fun onFailure(call: Call<Mensaje>, t: Throwable) {
                Toast.makeText(this@Registro, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun crearJson(): JSONObject {
        val json = JSONObject()
        try {
            json.put("correoUsuario", edtCorreo.text.toString())
            json.put("nombreUsuario", edtUsuario.text.toString())
            json.put("contrasenaUsuario", edtContrasena.text.toString())
            json.put("celularUsuario", edtTelefono.text.toString())
        } catch (ex: JSONException) {
            ex.printStackTrace()
        }
        return json
    }
}
