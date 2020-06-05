package digital.solutis.viciadosemfilmes.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import digital.solutis.viciadosemfilmes.repository.FilmRepository
import digital.solutis.viciadosemfilmes.ui.viewmodel.PageViewModel

class FilmListViewModelFactory (
    private val repository: FilmRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PageViewModel(
            repository
        ) as T
    }
}
