package digital.solutis.viciadosemfilmes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.TextKeyListener.clear
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
import digital.solutis.viciadosemfilmes.ui.activity.FilmDetailActivity
import digital.solutis.viciadosemfilmes.ui.main.PlaceholderFragment
import digital.solutis.viciadosemfilmes.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {




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
            val prefs = getSharedPreferences(FilmDetailActivity.MY_PREFS_NAME, Context.MODE_PRIVATE)
            val favorite =  prefs.getInt(FilmDetailActivity.FILM_ID, 0)
            if(favorite == 0){
                Toast.makeText(this, "Nenhum filme ainda foi favoritado.", Toast.LENGTH_SHORT).show()
            }else{
                openFavorite(favorite)
            }
        }
    }

    private fun openFavorite(id: Int) {
        val intent = Intent(this, FilmDetailActivity::class.java)
        intent.putExtra(PlaceholderFragment.FILM_ID, id)
        startActivity(intent)
    }



}