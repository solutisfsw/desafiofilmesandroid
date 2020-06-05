package digital.solutis.viciadosemfilmes.retrofit

import digital.solutis.viciadosemfilmes.retrofit.service.FilmService
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class FilmRetrofit {
    companion object{
        const val API_KEY = "b8a50c65d87318122acbafe4d8c24b4b"
        const val LANGUAGE = "pt-BR"
        const val HOST = "https://api.themoviedb.org"
    }
    var filmService : FilmService
        private set

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val parameterInterceptor = (object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                var request: Request = chain.request()
                val url: HttpUrl =
                    request.url.newBuilder().addQueryParameter("api_key", API_KEY).addQueryParameter("language", LANGUAGE).build()
                request = request.newBuilder().url(url).build()
                return chain.proceed(request)
            }
        })
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(parameterInterceptor)
            .build()
        val retrofit = Retrofit.Builder().baseUrl(HOST).client(client).addConverterFactory(
            GsonConverterFactory.create()).build()
        filmService = retrofit.create(FilmService::class.java)
    }
}