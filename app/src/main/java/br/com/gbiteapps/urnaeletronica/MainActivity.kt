package br.com.gbiteapps.urnaeletronica

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Handler


class MainActivity : AppCompatActivity() {

    companion object {
        const val BOLSONARO = "Bolsonaro"
        const val HADDAD: String = "Haddad"
    }

    var numVotosBolsonaro: Int = 0
    var numVotosHaddad: Int = 0
    var numVotosNulos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonVotar.setOnClickListener {
            if (radioBolsonaro.isChecked) {
                numVotosBolsonaro += 1
                toast(getString(R.string.vote_bolsonaro), Toast.LENGTH_SHORT)
                showSumOfVotes()
            } else if (radioHaddad.isChecked) {
                numVotosHaddad += 1
                toast(getString(R.string.vote_haddad), Toast.LENGTH_SHORT)
                showSumOfVotes()
            } else if (radioNulo.isChecked) {
                numVotosNulos += 1
                toast(getString(R.string.vote_nulo_branco), Toast.LENGTH_SHORT)
                showSumOfVotes()
            } else {
                toast(getString(R.string.err_choose_your_candidate), Toast.LENGTH_LONG)
            }
        }
    }

    fun showSumOfVotes() {
        val handler = Handler()
        handler.postDelayed(Runnable {
            toast("Jair Bolsonaro somou " + numVotosBolsonaro +
                    " votos, Fernando Haddad somou " + numVotosHaddad +
                    " votos e o nulo/branco somou " + numVotosNulos + " votos.", Toast.LENGTH_LONG)
        }, 1500)

        clearScreen()
    }

    fun clearScreen() {
        radioGroupCandidatos.clearCheck()
        imageViewCandidato.setImageResource(R.mipmap.selecione_candidato)
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.radioBolsonaro ->
                    if (checked)
                        imageViewCandidato.setImageResource(R.mipmap.bolsonaro)
                R.id.radioHaddad ->
                    if (checked)
                        imageViewCandidato.setImageResource(R.mipmap.haddad)
                R.id.radioNulo ->
                    if (checked)
                        imageViewCandidato.setImageResource(R.mipmap.branco_nulo)
            }
        }
    }
}
