package id.herdroid.daerahapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import id.herdroid.daerahapp.LoginActivity.Companion.SHARE
import id.herdroid.daerahapp.databinding.ActivityMainBinding
import id.herdroid.daerahapp.model.DaerahModel
import id.herdroid.daerahapp.viewmodel.ApiRepoVeiwModelFactory
import id.herdroid.daerahapp.viewmodel.ApiViewModel

class MainActivity : AppCompatActivity() {
    // menggunakan viewbinding lihat pada gradle buildfeature
    private lateinit var binding: ActivityMainBinding
    private lateinit var apiViewModel: ApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // click profil
        binding.imgProfil.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ProfileActivity::class.java
                )
            )
        }
        //button logout click
        binding.tvLogOut.setOnClickListener {
            //inisialisasi sharedpreference  cache
            val sharedPreferences = getSharedPreferences(SHARE, MODE_PRIVATE)

            // ubah data cache menjadi null semua
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(LoginActivity.EMAIL, null)
            editor.putString(LoginActivity.PHONE, null)
            editor.putString(LoginActivity.CUSTOMER_NAME, null)
            editor.putBoolean(LoginActivity.SESSION, false)
            editor.apply()

            // masuk ke halaman login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        //inisialisasi viewmodel class
        apiViewModel =
            ViewModelProvider(
                viewModelStore,
                ApiRepoVeiwModelFactory()
            )[ApiViewModel::class.java]
        getProvinsi()
    }

    //fungsi getProvinsi
    private fun getProvinsi() {
        val provValues = HashMap<String?, String?>()
        //observer live data observer
        apiViewModel.getProvinsi().observe(this, { listProv ->
            val id =
                arrayOfNulls<String>(listProv.size)
            val nama =
                arrayOfNulls<String>(listProv.size)
            val listProv1: MutableList<String> = ArrayList()

            var i = 0
            while (i < listProv.size) {
                listProv1.add(listProv[i].place)
                id[i] = listProv[i].id
                nama[i] = listProv[i].place
                provValues[nama[i]] = id[i]
                i++
            }

            binding.spinnerProvinsi.item = listProv1 as List<Any>?
        })

        //ketika user click provinsi kota dan memilih item
        binding.spinnerProvinsi.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    //clear data sebelumnya yang telah dipilih
                    binding.spinnerKota.clearSelection()
                    binding.spinnerKecamatan.clearSelection()
                    binding.spinnerKelurahan.clearSelection()
                    binding.spinnerKodePos.clearSelection()
                    binding.spinnerProvinsi.isEnableErrorLabel = false

                    getKota(provValues[binding.spinnerProvinsi.selectedItem.toString()]!!)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    //fungsi getkota
    private fun getKota(idProv: String) {
        val kotaValues = HashMap<String?, String?>()
        apiViewModel.daerahModel = DaerahModel(
            id = idProv
        )
        apiViewModel.getKota().observe(this, { listKota ->
            val id =
                arrayOfNulls<String>(listKota.size)
            val nama =
                arrayOfNulls<String>(listKota.size)
            val listKota1: MutableList<String> = ArrayList()

            var i = 0
            while (i < listKota.size) {
                listKota1.add(listKota[i].place)
                id[i] = listKota[i].id
                nama[i] = listKota[i].place
                kotaValues[nama[i]] = id[i]
                i++
            }

            binding.spinnerKota.item = listKota1 as List<Any>?
        })

        //ketika user click spinner kota dan memilih item
        binding.spinnerKota.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    //clear data sebelumnya yang telah dipilih
                    binding.spinnerKecamatan.clearSelection()
                    binding.spinnerKelurahan.clearSelection()
                    binding.spinnerKodePos.clearSelection()
                    binding.spinnerKota.isEnableErrorLabel = false

                    getKecamatan(kotaValues[binding.spinnerKota.selectedItem.toString()]!!)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun getKecamatan(idKota: String) {
        val kecamatanValues = HashMap<String?, String?>()
        apiViewModel.daerahModel = DaerahModel(
            id = idKota
        )

        //observer livedata viewmodel
        apiViewModel.getKecamatan().observe(this, { listKecamatan ->
            val id =
                arrayOfNulls<String>(listKecamatan.size)
            val nama =
                arrayOfNulls<String>(listKecamatan.size)
            val kotaProv1: MutableList<String> = ArrayList()

            var i = 0
            while (i < listKecamatan.size) {
                kotaProv1.add(listKecamatan[i].place)
                id[i] = listKecamatan[i].id
                nama[i] = listKecamatan[i].place
                kecamatanValues[nama[i]] = id[i]
                i++
            }

            binding.spinnerKecamatan.item = kotaProv1 as List<Any>?
        })

//ketika user click spinner kecamatan dan memilih item
        binding.spinnerKecamatan.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    //clear data sebelumnya yang telah dipilih
                    binding.spinnerKelurahan.clearSelection()
                    binding.spinnerKodePos.clearSelection()
                    binding.spinnerKecamatan.isEnableErrorLabel = false

                    // panggil fungsi getkelurahan
                    getKelurahan(kecamatanValues[binding.spinnerKecamatan.selectedItem.toString()]!!)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    //fungsi getkelurahan
    private fun getKelurahan(idKecamatan: String) {
        val kelurahanValues = HashMap<String?, String?>()

        //lempar data id kecamatan ke viewmodel
        apiViewModel.daerahModel = DaerahModel(
            id = idKecamatan
        )

        //observer live data viewmodel
        apiViewModel.getKelurahan().observe(this, { listKelurahan ->
            val id =
                arrayOfNulls<String>(listKelurahan.size)
            val nama =
                arrayOfNulls<String>(listKelurahan.size)
            val kotaProv1: MutableList<String> = ArrayList()

            var i = 0
            //untuk mengubah list DaerahModel ke masing"value
            while (i < listKelurahan.size) {
                kotaProv1.add(listKelurahan[i].place)
                id[i] = listKelurahan[i].id
                nama[i] = listKelurahan[i].place
                kelurahanValues[nama[i]] = id[i]
                i++
            }

            // masukkan list item ke spiner kelurahan
            binding.spinnerKelurahan.item = kotaProv1 as List<Any>?
        })

        //ketika user click spinner kelurahan dan memilih item
        binding.spinnerKelurahan.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    //clear data yang dipilih sebelumnya yang ada di spinner
                    binding.spinnerKodePos.clearSelection()

                    // untuk menghilangkan label error pada smart spiner
                    binding.spinnerKelurahan.isEnableErrorLabel = false

                    //memanggil fungsi kode pos
                    getKodePos(kelurahanValues[binding.spinnerKelurahan.selectedItem.toString()]!!)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    //fungsi memanggil kodepos
    private fun getKodePos(idKelurahan: String) {
        //lempar data parameter ke viewmodel
        apiViewModel.daerahModel = DaerahModel(
            id = idKelurahan
        )

        //observer livedata kodepos
        apiViewModel.getKodePos().observe(this, { listKodePos ->
            val listKodePos1: MutableList<String> = ArrayList()

            var i = 0
            while (i < listKodePos.size) {
                listKodePos1.add(listKodePos[i].place)
                i++
            }

            //masukkan ke smartspinner
            binding.spinnerKodePos.item = listKodePos1 as List<Any>?
        })
    }
}