package digital.solutis.viciadosemfilmes.retrofit

import digital.solutis.viciadosemfilmes.retrofit.service.FilmService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmRetrofit() {
    companion object{
        const val HOST = "https://api.themoviedb.org"
    }
    var filmService : FilmService
        private set

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofit = Retrofit.Builder().baseUrl(HOST).client(client).addConverterFactory(
            GsonConverterFactory.create()).build()
        filmService = retrofit.create(FilmService::class.java)
    }
}