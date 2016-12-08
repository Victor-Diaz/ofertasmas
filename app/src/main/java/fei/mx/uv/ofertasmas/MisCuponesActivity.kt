package fei.mx.uv.ofertasmas

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs
import fei.mx.uv.ofertasmas.adapters.CuponAdapter
import fei.mx.uv.ofertasmas.model.Cupon
import fei.mx.uv.ofertasmas.remoto.RestAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MisCuponesActivity : AppCompatActivity() {

    val MIS_CUPONES_STATUS = 1

    val rvCupones by lazy { findViewById(R.id.rvCupones) as RecyclerView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_cupones)
        checkLogin()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == MIS_CUPONES_STATUS) {
            getListaCupones()
        }
    }

    private fun checkLogin() {
        val correoUsuario = Prefs.getString("correoUsuario", "not_set")
        if (correoUsuario == "not_set") {
            val intent = Intent(this@MisCuponesActivity, LoginActivity::class.java)
            startActivityForResult(intent, MIS_CUPONES_STATUS)
        } else {
            getListaCupones()
        }
    }

    private fun getListaCupones() {
        val call = RestAPI.Factory.getIstance(this@MisCuponesActivity)
                .getCuponesPorUsuario(Prefs.getString("correoUsuario", "not_set"))
        call.enqueue( object : Callback<List<Cupon>> {
            override fun onResponse(call: Call<List<Cupon>>?, response: Response<List<Cupon>>) {
                setAdapterToRV(response.body())
            }
            override fun onFailure(call: Call<List<Cupon>>?, t: Throwable?) {
                Toast.makeText(this@MisCuponesActivity, t?.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setAdapterToRV(cupones: List<Cupon>) {
        rvCupones.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this@MisCuponesActivity)
        rvCupones.layoutManager = layoutManager
        val adapter = CuponAdapter(cupones)
        rvCupones.adapter = adapter
    }

}
