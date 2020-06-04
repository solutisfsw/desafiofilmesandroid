package digital.solutis.viciadosemfilmes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import digital.solutis.viciadosemfilmes.repository.FilmRepository

class FilmListViewModelFactory (
    private val repository: FilmRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PageViewModel(repository) as T
    }
}
