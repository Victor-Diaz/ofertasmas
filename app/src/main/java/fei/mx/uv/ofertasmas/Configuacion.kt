package fei.mx.uv.ofertasmas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.pixplicity.easyprefs.library.Prefs
import fei.mx.uv.ofertasmas.model.Ciudad
import fei.mx.uv.ofertasmas.model.Estado
import fei.mx.uv.ofertasmas.remoto.RestAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Configuacion : AppCompatActivity() {

    val spnEstados by lazy { findViewById(R.id.spnEstados) as Spinner }
    val spnCiudades by lazy { findViewById(R.id.spnCiudades) as Spinner }
    val btnGuardar by lazy { findViewById(R.id.btnGuardarConfig) as Button }
    val btnLogOut by lazy { findViewById(R.id.btnLogOut) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuacion)
        getEstados()
        setListenerToSpinner()
        btnGuardar.setOnClickListener { guardarConfig() }
        btnLogOut.setOnClickListener { logOut() }
    }

    private fun logOut() {
        Prefs.putString("correoUsuario", "not_set")
        setResult(1, Intent())
        finish()
    }

    private fun guardarConfig() {
        if (validarSpinners()) {
            Prefs.putString("ciudad", (spnCiudades.selectedItem as Ciudad).nombreCuidad)
            Prefs.putString("idCiudad", (spnCiudades.selectedItem as Ciudad).idCiudad.toString())
            val intent = Intent()
            setResult(2, intent)
            finish()
        } else {
            Toast.makeText(this@Configuacion, "Verifique su conexión a Internet", Toast.LENGTH_LONG).show()
            Prefs.putString("ciudad", "not_set")
            Prefs.putString("idCiudad", "not_set")
        }
    }

    private fun validarSpinners(): Boolean {
        val ciudadIsNull = spnCiudades.selectedItem == null
        return !ciudadIsNull
    }

    private fun getEstados() {
        val call = RestAPI.Factory.getIstance(this@Configuacion).estados()
        call.enqueue( object : Callback<List<Estado>> {
            override fun onResponse(call: Call<List<Estado>>?, response: Response<List<Estado>>?) {
                fillSpinnerEstados(response?.body())
            }
            override fun onFailure(call: Call<List<Estado>>?, t: Throwable?) {
                Toast.makeText(this@Configuacion, t?.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fillSpinnerEstados(body: List<Estado>?) {
        val estados = body ?: listOf<Estado>()
        val adapter = ArrayAdapter<Estado>(
                this@Configuacion,
                android.R.layout.simple_spinner_item,
                estados)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnEstados.adapter = adapter
    }

    private fun setListenerToSpinner() {
        spnEstados.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) { }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getCiudadesPorEstado((spnEstados.getItemAtPosition(p2) as Estado).idEstado)
            }
        }
    }

    private fun getCiudadesPorEstado(idEstado: Int) {
        val call = RestAPI.Factory.getIstance(this@Configuacion).ciudadesPorEstado(idEstado)
        call.enqueue( object : Callback<List<Ciudad>> {
            override fun onResponse(call: Call<List<Ciudad>>?, response: Response<List<Ciudad>>?) {
                fillSpinnerCiudades(response?.body())
            }
            override fun onFailure(call: Call<List<Ciudad>>?, t: Throwable?) {
                Toast.makeText(this@Configuacion, t?.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fillSpinnerCiudades(body: List<Ciudad>?) {
        val ciudades = body ?: listOf<Ciudad>()
        val adapter = ArrayAdapter<Ciudad>(
                this@Configuacion,
                android.R.layout.simple_spinner_item,
                ciudades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCiudades.adapter = adapter
    }

}
