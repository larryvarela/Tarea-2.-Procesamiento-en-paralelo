package mx.tecnm.tepic.ladm_u2_tarea2_procesamiento_en_paralelo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var hilo = Hilo(this)

        //iniciar
        button.setOnClickListener {
            try {
                hilo.terminar()
                hilo = Hilo(this)
                hilo.start()
                button.text = "REINICIAR"

            }catch (io:Exception){
                Toast.makeText(this, "ERROR HILO EN EJECUCION",Toast.LENGTH_LONG)
            }
        }
        //pausar
        button2.setOnClickListener {
            hilo.pausar()
        }
        //detener
        button3.setOnClickListener {
            hilo.terminar()
            button.text = "INICAR"
        }
    }
}

class Hilo(p: MainActivity): Thread(){
    var contador = 1
    val puntero = p
    var pausado = false
    var continuarCiclo = true

    override fun run(){
        super.run()
        while(continuarCiclo){
            if(!pausado) {
                puntero.runOnUiThread {
                    puntero.textView.text = "CONTADOR: ${contador}"
                }
                contador++
            }
            sleep(200)
        }
    }

    fun pausar(){
        pausado = !pausado
    }
    fun terminar(){
        puntero.textView.text = "CONTADOR: 0"
        continuarCiclo = false
    }
}