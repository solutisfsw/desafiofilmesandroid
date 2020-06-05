package digital.solutis.viciadosemfilmes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import digital.solutis.viciadosemfilmes.model.Film
import digital.solutis.viciadosemfilmes.model.FilmResponse
import digital.solutis.viciadosemfilmes.repository.FilmRepository

class PageViewModel(private val repository: FilmRepository) : ViewModel() {

    private val _index = MutableLiveData<Int>()

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun getFilmRepository(): LiveData<FilmResponse?>? {
        return when (_index.value) {
            1 -> repository.findUpcomingMovies()
            2 -> repository.findPopularMovies()
            else -> repository.findTopRatedMovies()
        }
    }

    fun getFilmDeatil(id: Int): LiveData<Film?>? {
        return repository.getFilmDetail(id)
    }
}