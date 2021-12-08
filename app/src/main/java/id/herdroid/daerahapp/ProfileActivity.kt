package id.herdroid.daerahapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.herdroid.daerahapp.LoginActivity.Companion.CUSTOMER_NAME
import id.herdroid.daerahapp.LoginActivity.Companion.EMAIL
import id.herdroid.daerahapp.LoginActivity.Companion.PHONE
import id.herdroid.daerahapp.LoginActivity.Companion.SHARE
import id.herdroid.daerahapp.R
import id.herdroid.daerahapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences(SHARE, MODE_PRIVATE)

        binding.tvNamaCustomer.text = sharedPreferences.getString(CUSTOMER_NAME, "").toString()
        binding.tvEmail.text = sharedPreferences.getString(EMAIL, "").toString()
        binding.tvNoTel.text = sharedPreferences.getString(PHONE, "").toString()

        binding.imgBack.setOnClickListener{finish()}
    }
}