package digital.solutis.viciadosemfilmes

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import digital.solutis.viciadosemfilmes.model.Film
import digital.solutis.viciadosemfilmes.model.FilmResponse
import digital.solutis.viciadosemfilmes.repository.FilmRepository
import digital.solutis.viciadosemfilmes.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {


    private lateinit var repository: FilmRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        repository = FilmRepository()
        findPopularMovies()
        getFilmDetail(419704)
    }

    private fun findPopularMovies() {
        repository.findPopularMovies(object : FilmRepository.DadosCarregadosCallback<FilmResponse> {
            override fun whenSuccessful(result: FilmResponse) {
                Log.i("aaaa", result.toString())
            }

            override fun whenFails(error: String?) {
                Toast.makeText(this@MainActivity , error, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun findTopRatedMovies() {
        repository.findTopRatedMovies(object : FilmRepository.DadosCarregadosCallback<FilmResponse> {
            override fun whenSuccessful(result: FilmResponse) {
                TODO("Not yet implemented")
            }

            override fun whenFails(error: String?) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun findUpcomingMovies() {
        repository.findUpcomingMovies(object : FilmRepository.DadosCarregadosCallback<FilmResponse> {
            override fun whenSuccessful(result: FilmResponse) {
                TODO("Not yet implemented")
            }

            override fun whenFails(error: String?) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getFilmDetail(id: Int) {
        repository.getFilmDetail(id, object : FilmRepository.DadosCarregadosCallback<Film> {
            override fun whenSuccessful(result: Film) {
                Log.i("aaaa", result.toString())
            }

            override fun whenFails(error: String?) {
                Toast.makeText(this@MainActivity , error, Toast.LENGTH_LONG).show()
            }

        })
    }



}