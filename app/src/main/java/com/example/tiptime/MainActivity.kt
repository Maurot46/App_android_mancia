package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }
    fun calculateTip(){
        //prelevo il testo dal campo di input e lo converto a stringa
        val testoInput = binding.costOfService.text.toString()

        //per farlo diventare un numero lo converto da stringa a Double(numero
        val costo = testoInput.toDouble()

        val selectedId = binding.tipOptions.checkedRadioButtonId

        val percentualeMancia = when (selectedId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //calcolo la mancia
        var mancia = percentualeMancia * costo

        val arrotondamento = binding.roundUpSwitch.isChecked

        if(arrotondamento){
            mancia = kotlin.math.ceil(mancia)
        }
        NumberFormat.getCurrencyInstance()

        val manciaFormattata = NumberFormat.getCurrencyInstance().format(mancia)
        binding.tipResult.text = getString(R.string.tip_amount, manciaFormattata)
    }
}