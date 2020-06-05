package digital.solutis.viciadosemfilmes.repository

import androidx.lifecycle.MutableLiveData
import digital.solutis.viciadosemfilmes.model.Film
import digital.solutis.viciadosemfilmes.model.FilmResponse
import digital.solutis.viciadosemfilmes.retrofit.FilmRetrofit
import digital.solutis.viciadosemfilmes.retrofit.callback.BaseCallback
import retrofit2.Call


class FilmRepository() {

    private val service = FilmRetrofit().filmService

    fun findTopRatedMovies(): MutableLiveData<FilmResponse> {
        val filmData = MutableLiveData<FilmResponse>()
        val call = service.getTopRatedMovies()
        call.enqueue( BaseCallback ( object: BaseCallback.ResponseCallback<FilmResponse>{

            override fun whenSuccessful(result: FilmResponse) {
                filmData.value = result
            }

            override fun whenFails(error: String?) {
                filmData.value = null
            }
        }))
        return filmData
    }

    fun findUpcomingMovies() : MutableLiveData<FilmResponse> {
        val filmData = MutableLiveData<FilmResponse>()
        val call = service.getUpcomingMovies()

        call.enqueue( BaseCallback ( object: BaseCallback.ResponseCallback<FilmResponse>{

            override fun whenSuccessful(result: FilmResponse) {
                filmData.value = result
            }

            override fun whenFails(error: String?) {
                filmData.value = null
            }
        }))
        return filmData
    }


    fun findPopularMovies(): MutableLiveData<FilmResponse> {
        val filmData = MutableLiveData<FilmResponse>()
        val call = service.getPopularMovies()
        call.enqueue( BaseCallback ( object: BaseCallback.ResponseCallback<FilmResponse>{

            override fun whenSuccessful(result: FilmResponse) {
                filmData.value = result
            }

            override fun whenFails(error: String?) {
                filmData.value = null
            }
        }))
        return filmData
    }

    fun getFilmDetail(id: Int): MutableLiveData<Film> {
        val filmData = MutableLiveData<Film>()
        val call: Call<Film> = service.getMovieDetail(id)
        call.enqueue( BaseCallback ( object: BaseCallback.ResponseCallback<Film>{

            override fun whenSuccessful(result: Film) {
                filmData.value = result
            }

            override fun whenFails(error: String?) {
                filmData.value = null
            }
        }))
        return filmData
    }

    interface DadosCarregadosCallback<T> {
        fun whenSuccessful(result: T)
        fun whenFails(error: String?)
    }
}