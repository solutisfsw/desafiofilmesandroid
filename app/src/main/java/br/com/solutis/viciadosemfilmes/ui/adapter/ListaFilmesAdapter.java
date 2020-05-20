package br.com.solutis.viciadosemfilmes.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.solutis.viciadosemfilmes.DetalheFilmeActivity;
import br.com.solutis.viciadosemfilmes.R;
import br.com.solutis.viciadosemfilmes.model.Filme;
import br.com.solutis.viciadosemfilmes.ui.constants.Constantes;

public class ListaFilmesAdapter extends RecyclerView.Adapter<ListaFilmesAdapter.FilmeViewHolder> {

    private final Context context;
    private final List<Filme> listaFilmes;

    public ListaFilmesAdapter(Context context, List<Filme> listaFilmes) {
        this.context = context;
        this.listaFilmes = listaFilmes;
    }

    @NonNull
    @Override
    public FilmeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_filme, parent, false);

        return new FilmeViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmeViewHolder holder, int position) {
        Filme filme = listaFilmes.get(position);
        holder.vincula(filme);
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    class FilmeViewHolder extends RecyclerView.ViewHolder{
        private final TextView titulo;
        private final TextView descricao;
        private final ImageView imageView;
        private Filme filme;
        private Context context;

        FilmeViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.item_filme_titulo);
            descricao = itemView.findViewById(R.id.item_filme_descricao);
            imageView = itemView.findViewById(R.id.item_filme_capa);
            configuraItemClique(itemView);
            context = itemView.getContext();
        }

        void vincula(Filme filme){
            this.filme = filme;
            titulo.setText(filme.getTitulo());
            descricao.setText(filme.getSinopse());
            Glide.with(context).load(Constantes.URL_IMAGEM_TMDB + filme.getCapa()).centerCrop().placeholder(R.drawable.icon_loading).into(imageView);
        }

        private void configuraItemClique(@NonNull View itemView) {
            itemView.setOnClickListener(v -> {
                Intent intentDetail = new Intent(context, DetalheFilmeActivity.class);
                intentDetail.putExtra(Constantes.EXTRA_KEY, filme.getId());
                context.startActivity(intentDetail);
            });
        }
    }
}
