package fei.mx.uv.ofertasmas

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import fei.mx.uv.ofertasmas.model.Estado
import fei.mx.uv.ofertasmas.remoto.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaOfertasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ofertas)
    }


    private fun crearDialogoEstados() {
        val call = API.Factory.getIstance(this@ListaOfertasActivity).estados
        call.enqueue(object : Callback<List<Estado>> {
            override fun onResponse(call: Call<List<Estado>>, response: Response<List<Estado>>) {
                val estados = arrayOfNulls<String>(response.body().size)
                for (i in 0..response.body().size - 1) {
                    estados[i] = response.body()[i].nombreEstado
                }

                val builder = AlertDialog.Builder(this@ListaOfertasActivity)
                builder.setTitle("Elija su ubicacion")
                        .setItems(estados) { dialogInterface, i ->
                            //Llenar la lista con estados
                        }
                val dialog = builder.create()
                dialog.show()
            }

            override fun onFailure(call: Call<List<Estado>>, t: Throwable) {
                Toast.makeText(this@ListaOfertasActivity,
                        t.message,
                        Toast.LENGTH_LONG).show()
            }
        })
    }


}
