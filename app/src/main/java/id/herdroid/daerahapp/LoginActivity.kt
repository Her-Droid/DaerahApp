package id.herdroid.daerahapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import id.herdroid.daerahapp.databinding.ActivityLoginBinding
import id.herdroid.daerahapp.model.UserModel
import id.herdroid.daerahapp.viewmodel.ApiRepoVeiwModelFactory
import id.herdroid.daerahapp.viewmodel.ApiViewModel


class LoginActivity : AppCompatActivity() {
    // menggunakan viewbinding lihat pada gradle buildfeature
    private lateinit var binding: ActivityLoginBinding

    // untuk key share preference
    companion object {
        const val SHARE = "SHARE"
        const val EMAIL = "EMAIL"
        const val PHONE = "PHONE"
        const val CUSTOMER_NAME = "CUSTOMER_NAME"
        const val SESSION = "SESSION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // inisialisasi shared preferences simpan di cache
        val sharedPreferences = getSharedPreferences(SHARE, Context.MODE_PRIVATE)

        // jika sudah login atau belom
        if (sharedPreferences.getBoolean(SESSION, false)){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            //inisialisasi viewmodel class
            val apiViewModel =
                ViewModelProvider(
                    viewModelStore,
                    ApiRepoVeiwModelFactory()
                )[ApiViewModel::class.java]

            // button login click
            binding.btnLogin.setOnClickListener {

                val email = binding.edtEmail.text.toString().trim()
                val password = binding.edtPassword.text.toString().trim()

                if (email.isEmpty()) {
                    binding.edtEmail.error = "Email required"
                    binding.edtEmail.requestFocus()
                } else if (password.isEmpty()) {
                    binding.edtPassword.error = "Password required"
                    binding.edtPassword.requestFocus()
                }

                //kirim parameter ke viewmodel
                apiViewModel.userModel = UserModel(
                    email = email,
                    password = password
                )

                //observer viewmodel livedata
                apiViewModel.getLogin().observe(this, { userModel ->

                    //simpan data ke cache untuk profil
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString(EMAIL, userModel.email)
                    editor.putString(PHONE, userModel.noTelp)
                    editor.putString(CUSTOMER_NAME, userModel.customerName)
                    editor.putBoolean(SESSION, true)
                    editor.apply()

                    startActivity(Intent(this, MainActivity::class.java))
                })
            }
        }
    }
}