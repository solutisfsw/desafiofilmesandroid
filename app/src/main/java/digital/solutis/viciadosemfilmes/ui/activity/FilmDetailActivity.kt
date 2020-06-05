package digital.solutis.viciadosemfilmes.ui.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import digital.solutis.viciadosemfilmes.R
import digital.solutis.viciadosemfilmes.repository.FilmRepository
import digital.solutis.viciadosemfilmes.ui.viewmodel.PageViewModel
import digital.solutis.viciadosemfilmes.ui.viewmodel.factory.FilmListViewModelFactory
import kotlinx.android.synthetic.main.activity_film_detail.*


class FilmDetailActivity : AppCompatActivity() {

    private val pageViewModel by lazy {
        val repository = FilmRepository()
        val factory =
            FilmListViewModelFactory(
                repository
            )
        val provedor = ViewModelProviders.of(this, factory)
        provedor.get(PageViewModel::class.java)
    }

    companion object{
        const val MY_PREFS_NAME = "digital.solutis.viciadosemfilmes"
        const val FILM_ID = "FILM_ID"
    }
    private val filmId: Int by lazy {
        intent.getIntExtra(FILM_ID, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        title = "Viciado em filmes"
        val prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE)
        val favorite =  prefs.getInt(FILM_ID, 0)
        if(favorite == filmId){
            activity_film_detail_favorite.setImageResource(R.drawable.ic_favorite_red)
        }
        activity_film_detail_favorite.setOnClickListener {
            val editor = prefs.edit()
            editor.putInt(FILM_ID, filmId)
            editor.apply()
            activity_film_detail_favorite.setImageResource(R.drawable.ic_favorite_red)
        }
        getFilmDetail(filmId)
    }

        private fun getFilmDetail(id: Int) {
            pageViewModel.getFilmDeatil(id)!!.observe(this, Observer {
                if(it == null){
                    Toast.makeText(this, "Ocorreu um erro ao busca o filme.", Toast.LENGTH_SHORT).show()
                }else{
                    activity_film_detail_title.text = it.title
                    activity_film_detail_tagline.text = it.tagline
                    activity_film_detail_overview.text = it.overview
                    activity_film_detail_year.text = it.getYear()
                    activity_film_detail_genres.text = it.getGenresString()
                    Glide.with(this@FilmDetailActivity)
                        .load(it.getFullPath())
                        .into(activity_film_detail_poster)
                }
            })
    }
}