package digital.solutis.viciadosemfilmes.ui.recyclerview.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import digital.solutis.viciadosemfilmes.R
import digital.solutis.viciadosemfilmes.model.Film


class FilmListAdapter ( private val context: Context?,
                          private val films: MutableList<Film> = mutableListOf(),
                        var onClickItem: (film: Film) -> Unit = {}
): RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.item_film, parent, false)
        return FilmViewHolder(viewCriada)
    }

    override fun getItemCount(): Int {
        return films.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = films[position]
        holder.bind(film, context)

    }

    fun update(films: List<Film>) {
        notifyItemRangeRemoved(0, this.films.size)
        this.films.clear()
        this.films.addAll(films)
        notifyItemRangeInserted(0, this.films.size)
    }

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var film: Film

        private val title: TextView = itemView.findViewById(R.id.item_film_title)
        private val overview: TextView = itemView.findViewById(R.id.item_film_overview)
        private val poster: ImageView = itemView.findViewById(R.id.item_film_poster)

        init {
            itemView.setOnClickListener {
                if (::film.isInitialized) {

                    Log.i("aaaa",film.toString())
                    onClickItem(film)
                }
            }
        }

        fun bind(film : Film, context: Context?){
            title.text = film.title
            overview.text = film.overview
            Glide.with(context!!)
                .load(film.getFullPath())
                .into(poster)
        }
    }
}
