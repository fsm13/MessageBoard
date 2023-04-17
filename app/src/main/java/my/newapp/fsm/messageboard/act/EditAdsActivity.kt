package my.newapp.fsm.messageboard.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import my.newapp.fsm.messageboard.databinding.ActivityEditAdsBinding
import my.newapp.fsm.messageboard.dialogs.DialogSpinnerHelper
import my.newapp.fsm.messageboard.utils.CityHelper

class EditAdsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityEditAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listCountry =  CityHelper.getAllCountries(this)
        val dialog = DialogSpinnerHelper()
        dialog.showSpinnerDialog(this, listCountry)
    }
}