package com.example.ccgr12024b_kdja

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class AcicloVida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aciclo_vida)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_ciclo_vida)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mostrarSnackbar("onCreate")
    }

    override fun onStart() {
        super.onStart()
        mostrarSnackbar("OnStart")
    }

    override fun onResume() {
        super.onResume()
        mostrarSnackbar("OnResume")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnackbar("OnRestart")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnackbar("OnPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarSnackbar("OnStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            //Guardar las variables
            putString("variableTextoGuardado",textGlobal)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //Recuperar las avriables
        val textoRecuperado:String? = savedInstanceState
            .getString("variableTextoGuardado")
        if (textoRecuperado != null){
            //textoGlobal = textoRecuperado
            mostrarSnackbar(textoRecuperado)//ya guarda el textoGlobal
        }
    }

    var textGlobal = ""
    fun mostrarSnackbar(text:String){
        textGlobal += text
        val snack = Snackbar.make(
            findViewById(R.id.cl_ciclo_vida),
            textGlobal,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}