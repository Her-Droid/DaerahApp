package id.herdroid.daerahapp.api

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("login-customer")
    fun login(
        @Body requestBody: RequestBody
    ): Call<ResponseBody>

    @Headers(
        "Authorization: Bearer MEE4M3JPb2RpTExPdFVXRWtwVm5uVkl6S2lsUTF6MlFKRXVjQ3cvYnJxaExhS1hBam5mK2ZUMHNKVDQ1bFZBRzQ4N3k4YW51VEF3VFdLaUE5VG1vaWtJMFBUazdsZUc5djJsRGwrN3NQckZHNlI0VWxVS2VTeG1kcXVXYzVQcUZJOWgwaW81Vm5tcjN0MkJUellPbmRkZFpvcXdFODgvajhjM3BHNzZ4QWRidTVXb0NZUHM5blRvTEFFSDhqVnlYQ1RKb3pYRzhST3JhV251dW96M0Vzd2NtbVVDbkFvQjlCRy9KRGxCLzhPR3ZPT0svdjA0SzJyOCtKWXVvSzdRekRQN1pvM1gvODNpTndZM2VZOXkvdHZhRnJHS25pOHdOZExSVy9WNCswZUlWMlVBV1cwVlF3V0dzVS9oOHpSbjY=",
        "Key: dGRMTnhuOEMxRDJNZXRUMXBseCtSRm14WjNrellkQVowZFVFSUFmVGwrM1BadmFjUHkwcndkTjRnN3gxdTN4LzdTTElMOHpSS3JoZ0dPK0xQN3dCOEU1ZWRETkN3WElqMVppTmtKNTcyWTQ9",
        "Timestamp: 2020-01-10 18:02:00",
        "Signature: e24b2448cbf13681e24c1a7b02fa8b8c257898862b45b8d731f8dcaefa0f7f61"
    )
    @POST("get-provinsi")
    fun getProvince(@Body requestBody: RequestBody): Call<ResponseBody>

    @Headers(
        "Authorization: Bearer MEE4M3JPb2RpTExPdFVXRWtwVm5uVkl6S2lsUTF6MlFKRXVjQ3cvYnJxaExhS1hBam5mK2ZUMHNKVDQ1bFZBRzQ4N3k4YW51VEF3VFdLaUE5VG1vaWtJMFBUazdsZUc5djJsRGwrN3NQckZHNlI0VWxVS2VTeG1kcXVXYzVQcUZJOWgwaW81Vm5tcjN0MkJUellPbmRkZFpvcXdFODgvajhjM3BHNzZ4QWRidTVXb0NZUHM5blRvTEFFSDhqVnlYQ1RKb3pYRzhST3JhV251dW96M0Vzd2NtbVVDbkFvQjlCRy9KRGxCLzhPR3ZPT0svdjA0SzJyOCtKWXVvSzdRekRQN1pvM1gvODNpTndZM2VZOXkvdHZhRnJHS25pOHdOZExSVy9WNCswZUlWMlVBV1cwVlF3V0dzVS9oOHpSbjY=",
        "Key: dGRMTnhuOEMxRDJNZXRUMXBseCtSRm14WjNrellkQVowZFVFSUFmVGwrM1BadmFjUHkwcndkTjRnN3gxdTN4LzdTTElMOHpSS3JoZ0dPK0xQN3dCOEU1ZWRETkN3WElqMVppTmtKNTcyWTQ9",
        "Timestamp: 2020-01-10 18:02:00",
        "Signature: e24b2448cbf13681e24c1a7b02fa8b8c257898862b45b8d731f8dcaefa0f7f61"
    )
    @POST("get-kota")
    fun getKota(@Body requestBody: RequestBody): Call<ResponseBody>

    @Headers(
        "Authorization: Bearer MEE4M3JPb2RpTExPdFVXRWtwVm5uVkl6S2lsUTF6MlFKRXVjQ3cvYnJxaExhS1hBam5mK2ZUMHNKVDQ1bFZBRzQ4N3k4YW51VEF3VFdLaUE5VG1vaWtJMFBUazdsZUc5djJsRGwrN3NQckZHNlI0VWxVS2VTeG1kcXVXYzVQcUZJOWgwaW81Vm5tcjN0MkJUellPbmRkZFpvcXdFODgvajhjM3BHNzZ4QWRidTVXb0NZUHM5blRvTEFFSDhqVnlYQ1RKb3pYRzhST3JhV251dW96M0Vzd2NtbVVDbkFvQjlCRy9KRGxCLzhPR3ZPT0svdjA0SzJyOCtKWXVvSzdRekRQN1pvM1gvODNpTndZM2VZOXkvdHZhRnJHS25pOHdOZExSVy9WNCswZUlWMlVBV1cwVlF3V0dzVS9oOHpSbjY=",
        "Key: dGRMTnhuOEMxRDJNZXRUMXBseCtSRm14WjNrellkQVowZFVFSUFmVGwrM1BadmFjUHkwcndkTjRnN3gxdTN4LzdTTElMOHpSS3JoZ0dPK0xQN3dCOEU1ZWRETkN3WElqMVppTmtKNTcyWTQ9",
        "Timestamp: 2020-01-10 18:02:00",
        "Signature: e24b2448cbf13681e24c1a7b02fa8b8c257898862b45b8d731f8dcaefa0f7f61"
    )
    @POST("get-kecamatan")
    fun getKecamatan(@Body requestBody: RequestBody): Call<ResponseBody>

    @Headers(
        "Authorization: Bearer MEE4M3JPb2RpTExPdFVXRWtwVm5uVkl6S2lsUTF6MlFKRXVjQ3cvYnJxaExhS1hBam5mK2ZUMHNKVDQ1bFZBRzQ4N3k4YW51VEF3VFdLaUE5VG1vaWtJMFBUazdsZUc5djJsRGwrN3NQckZHNlI0VWxVS2VTeG1kcXVXYzVQcUZJOWgwaW81Vm5tcjN0MkJUellPbmRkZFpvcXdFODgvajhjM3BHNzZ4QWRidTVXb0NZUHM5blRvTEFFSDhqVnlYQ1RKb3pYRzhST3JhV251dW96M0Vzd2NtbVVDbkFvQjlCRy9KRGxCLzhPR3ZPT0svdjA0SzJyOCtKWXVvSzdRekRQN1pvM1gvODNpTndZM2VZOXkvdHZhRnJHS25pOHdOZExSVy9WNCswZUlWMlVBV1cwVlF3V0dzVS9oOHpSbjY=",
        "Key: dGRMTnhuOEMxRDJNZXRUMXBseCtSRm14WjNrellkQVowZFVFSUFmVGwrM1BadmFjUHkwcndkTjRnN3gxdTN4LzdTTElMOHpSS3JoZ0dPK0xQN3dCOEU1ZWRETkN3WElqMVppTmtKNTcyWTQ9",
        "Timestamp: 2020-01-10 18:02:00",
        "Signature: e24b2448cbf13681e24c1a7b02fa8b8c257898862b45b8d731f8dcaefa0f7f61"
    )
    @POST("get-kelurahan")
    fun getKelurahan(@Body requestBody: RequestBody): Call<ResponseBody>

    @Headers(
        "Authorization: Bearer MEE4M3JPb2RpTExPdFVXRWtwVm5uVkl6S2lsUTF6MlFKRXVjQ3cvYnJxaExhS1hBam5mK2ZUMHNKVDQ1bFZBRzQ4N3k4YW51VEF3VFdLaUE5VG1vaWtJMFBUazdsZUc5djJsRGwrN3NQckZHNlI0VWxVS2VTeG1kcXVXYzVQcUZJOWgwaW81Vm5tcjN0MkJUellPbmRkZFpvcXdFODgvajhjM3BHNzZ4QWRidTVXb0NZUHM5blRvTEFFSDhqVnlYQ1RKb3pYRzhST3JhV251dW96M0Vzd2NtbVVDbkFvQjlCRy9KRGxCLzhPR3ZPT0svdjA0SzJyOCtKWXVvSzdRekRQN1pvM1gvODNpTndZM2VZOXkvdHZhRnJHS25pOHdOZExSVy9WNCswZUlWMlVBV1cwVlF3V0dzVS9oOHpSbjY=",
        "Key: dGRMTnhuOEMxRDJNZXRUMXBseCtSRm14WjNrellkQVowZFVFSUFmVGwrM1BadmFjUHkwcndkTjRnN3gxdTN4LzdTTElMOHpSS3JoZ0dPK0xQN3dCOEU1ZWRETkN3WElqMVppTmtKNTcyWTQ9",
        "Timestamp: 2020-01-10 18:02:00",
        "Signature: e24b2448cbf13681e24c1a7b02fa8b8c257898862b45b8d731f8dcaefa0f7f61"
    )
    @POST("get-kodepos")
    fun getKodePos(@Body requestBody: RequestBody): Call<ResponseBody>
}
