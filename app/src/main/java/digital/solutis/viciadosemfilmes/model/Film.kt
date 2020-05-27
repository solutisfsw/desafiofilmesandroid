package digital.solutis.viciadosemfilmes.model

import com.google.gson.annotations.SerializedName

data class Film (
    val id: Int,
    val title: String,
    val overview: String,
    val tagline: String,
    val genres: List<Genre>,
    @SerializedName("poster_path")val posterPath: String){

    companion object{
   //     private const val "https://image.tmdb.org/t/p/w500/"
    }
}
