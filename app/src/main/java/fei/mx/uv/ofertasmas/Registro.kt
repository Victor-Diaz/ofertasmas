package fei.mx.uv.ofertasmas

import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

import org.json.JSONException
import org.json.JSONObject

import fei.mx.uv.ofertasmas.model.Mensaje
import fei.mx.uv.ofertasmas.remoto.API
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registro : AppCompatActivity() {

    private var correo: String? = null
    private var telefono: String? = null
    private var usuario: String? = null

    private var edt_correo: EditText? = null//este es el uno
    private var edt_telefono: EditText? = null
    private var edt_usuario: EditText? = null
    private var edt_contrasena: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        edt_correo = findViewById(R.id.edt_correo) as EditText
        edt_telefono = findViewById(R.id.edt_telefono) as EditText
        edt_usuario = findViewById(R.id.edt_usuarrio) as EditText
        edt_contrasena = findViewById(R.id.edt_contrasena) as EditText
    }

    fun registrar(v: View) {
        var contador_campos = 0
        if (!(edt_correo!!.text != null && !edt_correo!!.text.toString().isEmpty())) {
            edt_correo!!.error = "Debes ingresar correo electrónico."
        } else {
            correo = edt_correo!!.text.toString()
            contador_campos += 1
        }
        if (!(edt_telefono!!.text != null && !edt_telefono!!.text.toString().isEmpty())) {
            edt_telefono!!.error = "Debes ingresar número telefonico."
        } else {
            telefono = edt_telefono!!.text.toString()
            contador_campos = contador_campos + 1
        }
        if (!(edt_usuario!!.text != null && !edt_usuario!!.text.toString().isEmpty())) {
            edt_usuario!!.error = "Debes ingresar nombre de usuario."
        } else {
            usuario = edt_usuario!!.text.toString()
            contador_campos += 1
        }
        if (!(edt_contrasena!!.text != null && !edt_contrasena!!.text.toString().isEmpty())) {
            edt_contrasena!!.error = "Debes ingresar una contraseña."
        } else {
            contador_campos += 1
        }

        if (contador_campos == 4) {
            val dialogo = AlertDialog.Builder(this@Registro).create()
            dialogo.setTitle("Valores ingresados...")
            dialogo.setMessage(String.format("Los valores ingresados son \n" +
                    "Correo: %s\nTeléfonon: %s\nUsuario: %s\nUna contrasaña" +
                    " \n\n¿Deseas continuar?",
                    correo,
                    telefono,
                    usuario))

            dialogo.setButton(AlertDialog.BUTTON_POSITIVE,
                    "Aceptar"
            ) { dialog, which -> registrarUsuario() }

            dialogo.setButton(AlertDialog.BUTTON_NEGATIVE,
                    "Cancelar"
            ) { dialog, which -> dialog.dismiss() }
            dialogo.show()
        }
    }

    fun registrarUsuario() {
        val `object` = JSONObject()
        try {
            `object`.put("correoUsuario", edt_correo!!.text.toString())
            `object`.put("nombreUsuario", edt_usuario!!.text.toString())
            `object`.put("contrasenaUsuario", edt_contrasena!!.text.toString())
            `object`.put("celularUsuario", edt_telefono!!.text.toString())
        } catch (ex: JSONException) {
            ex.printStackTrace()
        }


        val body = RequestBody
                .create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        JSONObject().toString())
        val call = API.Factory.getIstance(this@Registro).registrarUsuario(body)
        call.enqueue(object : Callback<Mensaje> {
            override fun onResponse(call: Call<Mensaje>, response: Response<Mensaje>) {
                if (!response.body().error) {
                    Toast.makeText(this@Registro,
                            response.body().mensaje,
                            Toast.LENGTH_LONG)
                            .show()
                } else {
                    Toast.makeText(this@Registro,
                            response.body().mensaje,
                            Toast.LENGTH_LONG)
                            .show()
                }
            }

            override fun onFailure(call: Call<Mensaje>, t: Throwable) {
                Toast.makeText(this@Registro,
                        t.message,
                        Toast.LENGTH_LONG)
                        .show()
            }
        })
    }
}
