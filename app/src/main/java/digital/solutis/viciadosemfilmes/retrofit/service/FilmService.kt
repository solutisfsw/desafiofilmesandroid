package digital.solutis.viciadosemfilmes.retrofit.service


import digital.solutis.viciadosemfilmes.model.Film
import digital.solutis.viciadosemfilmes.model.FilmResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {

    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey:String ): Call<FilmResponse>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey:String): Call<FilmResponse>

    @GET("/3/movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey:String): Call<FilmResponse>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") id: Int, @Query("api_key") apiKey:String ): Call<Film>

}