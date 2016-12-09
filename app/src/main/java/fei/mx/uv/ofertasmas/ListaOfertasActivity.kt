package fei.mx.uv.ofertasmas

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.pixplicity.easyprefs.library.Prefs
import fei.mx.uv.ofertasmas.model.Categoria
import fei.mx.uv.ofertasmas.model.Oferta
import fei.mx.uv.ofertasmas.model.SOferta
import fei.mx.uv.ofertasmas.remoto.RestAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaOfertasActivity : AppCompatActivity() {

    //TODO implement a refresh mechanism

    val CONFIG_REQUEST = 2
    val MIS_CUPONES_REQUEST = 3

    private val spinnerCategorias by lazy { findViewById(R.id.spinnerCategorias) as Spinner }
    private val lvOfertas by lazy { findViewById(R.id.lvOfertas) as ListView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ofertas)
        initialize()
        setListenerToSpinner()
    }

    private fun initialize() {
        obtenerCiudadInfo()
        getCategorias()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.mConfig -> {
                irAConfigActivity()
                return true
            }
            R.id.mOfertas -> {
                val intent = Intent(this@ListaOfertasActivity, MisCuponesActivity::class.java)
                startActivityForResult(intent, MIS_CUPONES_REQUEST)
                return true
            }
            R.id.mRefresh -> {
                initialize()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CONFIG_REQUEST) {
            getCategorias()
            if (prefsValidas()) {
                val (ciudad, idCiudad) = obtenerCiudadInfo()
                if (spinnerCategorias.selectedItem != null) {
                    val cat = spinnerCategorias.selectedItem as Categoria
                    getOfertas(idCiudad.toInt(), cat.idCategoria)
                }
            }
        }
    }

    private fun prefsValidas(): Boolean {
        val (ciudad, idCiudad) = obtenerCiudadInfo()
        val esCiudad = ciudad != "not_set"
        return esCiudad
    }

    private fun irAConfigActivity() {
        val intent = Intent(this@ListaOfertasActivity, Configuacion::class.java)
        startActivityForResult(intent, CONFIG_REQUEST)
    }

    private fun obtenerCiudadInfo(): Pair<String, String> {
        val ciudad = Prefs.getString("ciudad", "not_set")
        val idCiudad = Prefs.getString("idCiudad", "not_set")
        if (ciudad == "not_set") {
            irAConfigActivity()
            return Pair("not_set","not_set")
        } else {
            return Pair(ciudad, idCiudad)
        }
    }

    private fun getCategorias() {
        val call = RestAPI.Factory.getIstance(this@ListaOfertasActivity).categorias()
        call.enqueue(object : Callback<List<Categoria>> {
            override fun onResponse(call: Call<List<Categoria>>?, response: Response<List<Categoria>>?) {
                fillSpinner(response?.body())
            }
            override fun onFailure(call: Call<List<Categoria>>?, t: Throwable?) {
                Toast.makeText(this@ListaOfertasActivity, t?.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fillSpinner(cats: List<Categoria>?) {
        val categorias = cats ?: listOf<Categoria>()
        val adapter = ArrayAdapter<Categoria>(
                this@ListaOfertasActivity,
                android.R.layout.simple_spinner_item,
                categorias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategorias.adapter = adapter
    }

    private fun setListenerToSpinner() {
        spinnerCategorias.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val cat = spinnerCategorias.getItemAtPosition(p2) as Categoria
                val (ciudad, idCiudad) = obtenerCiudadInfo()
                getOfertas(idCiudad.toInt(), cat.idCategoria)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) { }
        }
    }

    private fun getOfertas(idCiudad: Int, idCategoria: Int) {
        val call = RestAPI.Factory.getIstance(this@ListaOfertasActivity)
                .getOfertasPorCiudadCategoria(idCiudad, idCategoria)
        call.enqueue(object : Callback<List<Oferta>> {
            override fun onResponse(call: Call<List<Oferta>>?, response: Response<List<Oferta>>?) {
                fillListView(response?.body())
            }
            override fun onFailure(call: Call<List<Oferta>>?, t: Throwable?) {
                Toast.makeText(this@ListaOfertasActivity, t?.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fillListView(body: List<Oferta>?) {
        val ofertas = body ?: listOf<Oferta>()
        val adapter = ArrayAdapter<Oferta>(
                this@ListaOfertasActivity,
                android.R.layout.simple_list_item_1,
                ofertas)
        lvOfertas.adapter = adapter
        addListenerToLV()
    }

    private fun addListenerToLV() {
        lvOfertas.setOnItemClickListener { adapterView, view, i, l ->
            val o = lvOfertas.getItemAtPosition(i) as Oferta
            val sOferta = SOferta(o.idOferta, o.nombreOferta, o.descripcionOferta, o.precioArticulo, o.idEmpresa)
            val intent = Intent(this@ListaOfertasActivity, OfertaActivity::class.java)
            intent.putExtra("oferta", sOferta)
            startActivity(intent)
        }
    }

}
