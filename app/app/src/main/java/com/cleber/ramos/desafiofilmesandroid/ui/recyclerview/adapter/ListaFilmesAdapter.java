package com.cleber.ramos.desafiofilmesandroid.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cleber.ramos.desafiofilmesandroid.R;
import com.cleber.ramos.desafiofilmesandroid.model.Filme;
import com.cleber.ramos.desafiofilmesandroid.retrofit.FilmesRetrofit;

import java.util.ArrayList;
import java.util.List;

public class ListaFilmesAdapter extends RecyclerView.Adapter<ListaFilmesAdapter.FilmeViewHolder> {

    private final Context context;
    private final List<Filme> filmes = new ArrayList<>();
    private final OnItemClickListener onItemClickListener;

    public ListaFilmesAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FilmeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_filmes, parent, false);
        return new FilmeViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmeViewHolder holder, int position) {
        Filme filme = filmes.get(position);
        holder.vincula(filme);
    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    public void carregaListaFilmes(List<Filme> filmes) {
        this.notifyItemRangeRemoved(0, this.filmes.size());
        this.filmes.clear();
        this.filmes.addAll(filmes);
        this.notifyItemRangeInserted(0, this.filmes.size());
    }

    public void adicionaFilme(Filme filme) {
        this.filmes.add(filme);
    }

    public void adicionaListaFilmes(List<Filme> filmes) {
        this.filmes.addAll(filmes);
    }

    public void notificaAdapter() {
        this.notifyDataSetChanged();
    }

    class FilmeViewHolder extends RecyclerView.ViewHolder {

        private final TextView titulo;
        private final TextView descricao;
        private final ImageView imagem;
        private Filme filme;

        public FilmeViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_titulo_filme);
            descricao = itemView.findViewById(R.id.item_descricao_filme);
            imagem = itemView.findViewById(R.id.item_imagem_filme);
            configuraItemClique(itemView);
        }

        private void configuraItemClique(@NonNull View itemView) {
            itemView.setOnClickListener(v -> onItemClickListener
                    .onItemClick(getAdapterPosition(), filme));
        }

        public void vincula(Filme filme) {
            preencheCampo(filme);
        }

        private void preencheCampo(Filme filme) {
            this.filme = filme;
            titulo.setText(filme.getTitle());
            descricao.setText(filme.getOverview());
            Glide.with(context)
                    .load(FilmesRetrofit.URL_BASE_IMAGEM + filme.getPosterPath())
                    .placeholder(R.drawable.ic_action_imagem_carregando)
                    .error(R.drawable.ic_action_imagem_erro)
                    .centerCrop()
                    .into(imagem);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int posicao, Filme filme);
    }
}
