package digital.solutis.viciadosemfilmes.model

import com.google.gson.annotations.SerializedName

data class Film (
    val id: Int,
    val title: String,
    val overview: String,
    val tagline: String,
    val genres: List<Genre>,
    @SerializedName("poster_path")val posterPath: String,
    @SerializedName("release_date")val releaseDate: String){

    companion object{
        private const val baseUrl = "https://image.tmdb.org/t/p/w500/"
    }

    fun getFullPath () =  baseUrl+posterPath

    fun getYear () =  releaseDate.substring(0,4)

    fun getGenresString() = genres.joinToString( separator = ", ") { it -> it.name }

}
