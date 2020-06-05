package digital.solutis.viciadosemfilmes.model

data class FilmResponse (val page: Int, val totalResults: Int, val totalPages: Int, val results: List<Film> )