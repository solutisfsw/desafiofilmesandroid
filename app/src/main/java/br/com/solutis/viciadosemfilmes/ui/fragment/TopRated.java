package br.com.solutis.viciadosemfilmes.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.solutis.viciadosemfilmes.R;
import br.com.solutis.viciadosemfilmes.model.Filme;
import br.com.solutis.viciadosemfilmes.ui.adapter.ListaFilmesAdapter;


public class TopRated extends Fragment {

    private Context context;
    private List<Filme> listaFilmes;

    public TopRated(Context context, List<Filme> listaFilmes) {
        this.context = context;
        this.listaFilmes = listaFilmes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View topRatedView = inflater.inflate(R.layout.fragment_top_rated, container, false);
        RecyclerView recyclerView = topRatedView.findViewById(R.id.top_rated_recyclerView);

        recyclerView.setAdapter(new ListaFilmesAdapter(context, listaFilmes));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return topRatedView;
    }
}
