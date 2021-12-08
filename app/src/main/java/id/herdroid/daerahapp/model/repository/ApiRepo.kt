package id.herdroid.daerahapp.model.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.herdroid.daerahapp.api.ApiService
import id.herdroid.daerahapp.model.DaerahModel
import id.herdroid.daerahapp.model.UserModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import java.io.IOException

import okhttp3.OkHttpClient
import org.json.JSONArray
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

import javax.security.cert.CertificateException


class ApiRepo {

    companion object {
        private const val BASE_URL = "https://invent-integrasi.com/brum_core/mobile/bypass/"
    }

    fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }
            )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier({ hostname, session -> true })
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun getApi(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getUnsafeOkHttpClient().build())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun getDaerah(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    fun getLogin(userModel: UserModel): MutableLiveData<UserModel> {
        val json = JSONObject()
        json.put("user_name", userModel.email)
        json.put("password", userModel.password)
        val mutableLiveData = MutableLiveData<UserModel>()

        val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val responseBodyCall = getApi().login(requestBody)
        responseBodyCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body() != null) {
                    try {
                        val responses = response.body()!!.string()
                        var jsonObject = JSONObject(responses)
                        if (jsonObject.getString("status_message_ind") == "Sukses") {
                            jsonObject = jsonObject.getJSONObject("value")
                            mutableLiveData.value = UserModel(
                                email = jsonObject.getString("email"),
                                noTelp = jsonObject.getString("phone"),
                                customerName = jsonObject.getString("customer_name"),
                                sukses = true
                            )
                        } else {
                            mutableLiveData.value = UserModel(
                                sukses = false
                            )
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                Log.v("jajal", t.message + " a")
            }
        })
        return mutableLiveData
    }

    fun getProvinsi(): MutableLiveData<List<DaerahModel>> {
        val mutableLiveData = MutableLiveData<List<DaerahModel>>()

        val json = JSONObject()
        val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val responseBodyCall = getApi().getProvince(requestBody)

        responseBodyCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body() != null) {
                    try {
                        val responses = response.body()!!.string()
                        var jsonObject = JSONObject(responses)
                        val jsonArray = JSONArray(jsonObject.getString("value"))

                        val listProv = ArrayList<DaerahModel>()
                        for (i in 0 until jsonArray.length()) {
                            jsonObject = jsonArray.getJSONObject(i)
                            listProv.add(
                                DaerahModel(
                                    id = jsonObject.getString("region_id"),
                                    place = jsonObject.getString("region_name")
                                )
                            )
                        }
                        mutableLiveData.value = listProv
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {
                    Log.v("jajal", response.raw().toString() + " a")

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                Log.v("jajal", t.message + " a")
            }
        })
        return mutableLiveData
    }

    fun getKota(daerahModel: DaerahModel): MutableLiveData<List<DaerahModel>> {
        val mutableLiveData = MutableLiveData<List<DaerahModel>>()

        val json = JSONObject()
        json.put("region_id", daerahModel.id)
        val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val responseBodyCall = getApi().getKota(requestBody)

        responseBodyCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body() != null) {
                    try {
                        val responses = response.body()!!.string()
                        var jsonObject = JSONObject(responses)
                        val jsonArray = JSONArray(jsonObject.getString("value"))

                        val listKota = ArrayList<DaerahModel>()
                        for (i in 0 until jsonArray.length()) {
                            jsonObject = jsonArray.getJSONObject(i)
                            listKota.add(
                                DaerahModel(
                                    id = jsonObject.getString("district_id"),
                                    place = jsonObject.getString("district_name")
                                )
                            )
                        }
                        mutableLiveData.value = listKota
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {
                    Log.v("jajal", response.raw().toString() + " a")

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                Log.v("jajal", t.message + " a")
            }
        })
        return mutableLiveData
    }

    fun getKecamatan(daerahModel: DaerahModel): MutableLiveData<List<DaerahModel>> {
        val mutableLiveData = MutableLiveData<List<DaerahModel>>()

        Log.v("jajal", daerahModel.id)
        val json = JSONObject()
        json.put("district_id", daerahModel.id)
        val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val responseBodyCall = getApi().getKecamatan(requestBody)

        responseBodyCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body() != null) {
                    try {
                        val responses = response.body()!!.string()
                        var jsonObject = JSONObject(responses)
                        Log.v("jajal", jsonObject.toString() + " a")
                        val jsonArray = JSONArray(jsonObject.getString("value"))

                        val listKecamatan = ArrayList<DaerahModel>()
                        for (i in 0 until jsonArray.length()) {
                            jsonObject = jsonArray.getJSONObject(i)
                            listKecamatan.add(
                                DaerahModel(
                                    id = jsonObject.getString("area_id"),
                                    place = jsonObject.getString("area_name")
                                )
                            )
                        }
                        mutableLiveData.value = listKecamatan
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {
                    Log.v("jajal", response.raw().toString() + " a")

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                Log.v("jajal", t.message + " a")
            }
        })
        return mutableLiveData
    }

    fun getKelurahan(daerahModel: DaerahModel): MutableLiveData<List<DaerahModel>> {
        val mutableLiveData = MutableLiveData<List<DaerahModel>>()

        val json = JSONObject()
        json.put("area_id", daerahModel.id)
        val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val responseBodyCall = getApi().getKelurahan(requestBody)

        responseBodyCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body() != null) {
                    try {
                        val responses = response.body()!!.string()
                        var jsonObject = JSONObject(responses)
                        Log.v("jajal", jsonObject.toString() + " a")
                        val jsonArray = JSONArray(jsonObject.getString("value"))

                        val listKelurahan = ArrayList<DaerahModel>()
                        for (i in 0 until jsonArray.length()) {
                            jsonObject = jsonArray.getJSONObject(i)
                            listKelurahan.add(
                                DaerahModel(
                                    id = jsonObject.getString("branch_id"),
                                    place = jsonObject.getString("branch_name")
                                )
                            )
                        }
                        mutableLiveData.value = listKelurahan
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {
                    Log.v("jajal", response.raw().toString() + " a")

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                Log.v("jajal", t.message + " a")
            }
        })
        return mutableLiveData
    }
    fun getKodePos(daerahModel: DaerahModel): MutableLiveData<List<DaerahModel>> {
        val mutableLiveData = MutableLiveData<List<DaerahModel>>()

        val json = JSONObject()
        json.put("branch_id", daerahModel.id)
        val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val responseBodyCall = getApi().getKodePos(requestBody)

        responseBodyCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body() != null) {
                    try {
                        val responses = response.body()!!.string()
                        var jsonObject = JSONObject(responses)
                        Log.v("jajal", jsonObject.toString() + " a")
                        val jsonArray = JSONArray(jsonObject.getString("value"))

                        val listPost = ArrayList<DaerahModel>()
                        for (i in 0 until jsonArray.length()) {
                            jsonObject = jsonArray.getJSONObject(i)
                            listPost.add(
                                DaerahModel(
                                    place = jsonObject.getString("post_code")
                                )
                            )
                        }
                        mutableLiveData.value = listPost
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {
                    Log.v("jajal", response.raw().toString() + " a")

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                Log.v("jajal", t.message + " a")
            }
        })
        return mutableLiveData
    }
}