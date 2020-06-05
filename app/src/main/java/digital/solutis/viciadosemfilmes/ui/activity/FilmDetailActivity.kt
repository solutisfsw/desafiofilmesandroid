package digital.solutis.viciadosemfilmes.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import digital.solutis.viciadosemfilmes.R
import digital.solutis.viciadosemfilmes.model.Film
import digital.solutis.viciadosemfilmes.repository.FilmRepository
import kotlinx.android.synthetic.main.activity_film_detail.*

class FilmDetailActivity : AppCompatActivity() {

    private val filmId: Int by lazy {
        intent.getIntExtra("FILM_ID", 0)
    }

    private val repository: FilmRepository by lazy {
        FilmRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        title = "Viciado em filmes"
        getFilmDetail(filmId)
    }

        private fun getFilmDetail(id: Int) {
        repository.getFilmDetail(id, object : FilmRepository.DadosCarregadosCallback<Film> {
            override fun whenSuccessful(result: Film) {
                activity_film_detail_title.text = result.title
                activity_film_detail_tagline.text = result.tagline
                activity_film_detail_overview.text = result.overview
                activity_film_detail_year.text = result.getYear()
                activity_film_detail_genres.text = result.getGenresString()
                Glide.with(this@FilmDetailActivity)
                    .load(result.getFullPath())
                    .into(activity_film_detail_poster)
            }

            override fun whenFails(error: String?) {
                Toast.makeText(this@FilmDetailActivity , error, Toast.LENGTH_LONG).show()
            }

        })
    }
}