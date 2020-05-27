package digital.solutis.viciadosemfilmes.repository

import digital.solutis.viciadosemfilmes.model.Film
import digital.solutis.viciadosemfilmes.model.FilmResponse
import digital.solutis.viciadosemfilmes.retrofit.FilmRetrofit
import digital.solutis.viciadosemfilmes.retrofit.callback.BaseCallback
import retrofit2.Call


class FilmRepository() {

    private val service = FilmRetrofit().filmService

    fun findTopRatedMovies(callback: DadosCarregadosCallback<FilmResponse>) {
        val call = service.getTopRatedMovies()
        call.enqueue( BaseCallback ( object: BaseCallback.ResponseCallback<FilmResponse>{

            override fun whenSuccessful(result: FilmResponse) {
                callback.whenSuccessful(result)
            }

            override fun whenFails(error: String?) {
                callback.whenFails(error)
            }
        }))
    }

    fun findUpcomingMovies(callback: DadosCarregadosCallback<FilmResponse>) {
        val call = service.getUpcomingMovies()
        call.enqueue( BaseCallback ( object: BaseCallback.ResponseCallback<FilmResponse>{

            override fun whenSuccessful(result: FilmResponse) {
                callback.whenSuccessful(result)
            }

            override fun whenFails(error: String?) {
                callback.whenFails(error)
            }
        }))
    }


    fun findPopularMovies(callback: DadosCarregadosCallback<FilmResponse>) {
        val call = service.getPopularMovies()
        call.enqueue( BaseCallback ( object: BaseCallback.ResponseCallback<FilmResponse>{

            override fun whenSuccessful(result: FilmResponse) {
                callback.whenSuccessful(result)
            }

            override fun whenFails(error: String?) {
                callback.whenFails(error)
            }
        }))
    }

    fun getFilmDetail(id: Int, callback: DadosCarregadosCallback<Film>) {
        val call: Call<Film> = service.getMovieDetail(id)
        call.enqueue( BaseCallback ( object: BaseCallback.ResponseCallback<Film>{

            override fun whenSuccessful(result: Film) {
                callback.whenSuccessful(result)
            }

            override fun whenFails(error: String?) {
                callback.whenFails(error)
            }
        }))
    }

    interface DadosCarregadosCallback<T> {
        fun whenSuccessful(result: T)
        fun whenFails(error: String?)
    }
}