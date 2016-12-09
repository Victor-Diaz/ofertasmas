package fei.mx.uv.ofertasmas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs
import fei.mx.uv.ofertasmas.model.Mensaje
import fei.mx.uv.ofertasmas.remoto.RestAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val LOGIN_STATUS = 1

    val edtCorreo by lazy { findViewById(R.id.edtCorreo) as EditText }
    val edtPassword by lazy { findViewById(R.id.edtContrasena) as EditText }
    val btnLogin by lazy { findViewById(R.id.btnLogin) as Button }
    val btnRegistro by lazy { findViewById(R.id.btnRegUsuario) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener { login() }
        btnRegistro.setOnClickListener { irRegistro() }
    }

    private fun login() {
        if (valido()) {
            val correo = edtCorreo.text.toString()
            val password = edtPassword.text.toString()
            val call = RestAPI.Factory.getIstance(this@LoginActivity).login(correo, password)
            call.enqueue(object : Callback<Mensaje> {
                override fun onResponse(call: Call<Mensaje>?, response: Response<Mensaje>?) {
                    val mensaje = response?.body() ?: Mensaje()
                    if (!mensaje.error) {
                        Toast.makeText(this@LoginActivity, mensaje.mensaje, Toast.LENGTH_LONG).show()
                        Prefs.putString("correoUsuario", correo)
                        setResult(1, Intent())
                        finish()
                    } else Toast.makeText(this@LoginActivity, mensaje.mensaje, Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Mensaje>?, t: Throwable?) {
                    Toast.makeText(this@LoginActivity, t?.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun valido(): Boolean {
        val correo = edtCorreo.text.isNullOrEmpty()
        if (correo) edtCorreo.error = "Campo obligatorio"
        val password = edtPassword.text.isNullOrEmpty()
        if (password) edtPassword.error = "Campo obligatorio"
        return !(correo || password)
    }

    private fun irRegistro() {
        //TODO hacer onResult
        val intent = Intent(this@LoginActivity, Registro::class.java)
        startActivityForResult(intent, LOGIN_STATUS)
    }

}
