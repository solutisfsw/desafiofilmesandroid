package digital.solutis.viciadosemfilmes.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import digital.solutis.viciadosemfilmes.R
import digital.solutis.viciadosemfilmes.model.Film
import digital.solutis.viciadosemfilmes.repository.FilmRepository
import digital.solutis.viciadosemfilmes.ui.activity.FilmDetailActivity
import digital.solutis.viciadosemfilmes.ui.recyclerview.adapter.FilmListAdapter
import digital.solutis.viciadosemfilmes.ui.viewmodel.PageViewModel
import digital.solutis.viciadosemfilmes.ui.viewmodel.factory.FilmListViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {


    private  val adapter by lazy {
        FilmListAdapter(context = activity)
    }

    private val pageViewModel by lazy {
        val repository = FilmRepository()
        val factory =
            FilmListViewModelFactory(
                repository
            )
        val provedor = ViewModelProviders.of(this, factory)
        provedor.get(PageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pageViewModel.apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_film_recycleview.adapter = adapter
        adapter.onClickItem = this::openFilmDetail
        findFilms()
    }

    private fun openFilmDetail(it: Film) {
        val intent = Intent(activity, FilmDetailActivity::class.java)
        intent.putExtra(FILM_ID, it.id)
        startActivity(intent)
    }

    private fun findFilms() {
        pageViewModel.getFilmRepository()!!.observe(viewLifecycleOwner, Observer {
            if(it == null){
                Toast.makeText(activity, "Ocorreu um erro ao buscar os filmes.", Toast.LENGTH_SHORT).show()
            }else{
               adapter.update(it.results)
            }
        })
    }


    companion object {

        const val FILM_ID = "FILM_ID"

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}