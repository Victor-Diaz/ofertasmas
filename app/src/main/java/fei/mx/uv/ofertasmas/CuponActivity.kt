package fei.mx.uv.ofertasmas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bottlerocketstudios.barcode.generation.ui.BarcodeView

class CuponActivity : AppCompatActivity() {

    val barCodeView by lazy { findViewById(R.id.generation_barcode_image) as BarcodeView }
    val tvCodigo by lazy { findViewById(R.id.tvCuponCodigo) as TextView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cupon)
        getCuponCode()
    }

    private fun getCuponCode() {
        val codigo = intent.getIntExtra("codigoCupon", 1).toString()
        barCodeView.setBarcodeText(codigo)
        tvCodigo.text = codigo
    }

}
