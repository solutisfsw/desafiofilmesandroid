package digital.solutis.viciadosemfilmes.retrofit.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.internal.EverythingIsNonNull


class BaseCallback<T>(private val callback: ResponseCallback<T>) :
    Callback<T> {
    @EverythingIsNonNull
    override fun onResponse(
        call: Call<T>,
        response: Response<T>
    ) {
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                callback.whenSuccessful(result)
            }
        } else {
            callback.whenFails("Resposta não sucedida.")
        }
    }

    @EverythingIsNonNull
    override fun onFailure(call: Call<T>, t: Throwable) {
        callback.whenFails("Falha de comunicação: " + t.message)
    }

    interface ResponseCallback<T> {
        fun whenSuccessful(result: T)
        fun whenFails(error: String?)
    }

}
