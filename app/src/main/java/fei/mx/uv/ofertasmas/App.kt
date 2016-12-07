package fei.mx.uv.ofertasmas

import android.app.Application
import android.content.ContextWrapper

import com.pixplicity.easyprefs.library.Prefs

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName("ofertas_mas")
                .setUseDefaultSharedPreference(true)
                .build()
    }

}
