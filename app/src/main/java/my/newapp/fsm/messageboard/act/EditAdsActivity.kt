package my.newapp.fsm.messageboard.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import my.newapp.fsm.messageboard.R
import my.newapp.fsm.messageboard.databinding.ActivityEditAdsBinding
import my.newapp.fsm.messageboard.dialogs.DialogSpinnerHelper
import my.newapp.fsm.messageboard.utils.CityHelper

class EditAdsActivity : AppCompatActivity() {
    lateinit var binding:ActivityEditAdsBinding
    private val dialog = DialogSpinnerHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()


    }

    private fun init() {
    }

    fun onClickSelectCountry(view: View) {
        val listCountry =  CityHelper.getAllCountries(this)
        dialog.showSpinnerDialog(this, listCountry, binding.tvCountry)
        if (binding.tvCity.text.toString() != getString(R.string.select_city)) {
            binding.tvCity.text = getString(R.string.select_city)
        }
    }

    fun onClickSelectCity(view: View) {
        val selectedCountry = binding.tvCountry.toString()
        if (selectedCountry != getString(R.string.select_country)) {
            val listCity = CityHelper.getAllCities(selectedCountry, this)
            dialog.showSpinnerDialog(this, listCity, binding.tvCity)
        } else {
            Toast.makeText(this, "Страна не выбрана", Toast.LENGTH_LONG).show()
        }
    }
}