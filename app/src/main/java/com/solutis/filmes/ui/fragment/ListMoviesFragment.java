package com.solutis.filmes.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.solutis.filmes.R;
import com.solutis.filmes.model.Movie;
import com.solutis.filmes.model.PagedMovie;
import com.solutis.filmes.ui.activity.HomeActivity;
import com.solutis.filmes.ui.adapter.MovieListAdapter;
import com.solutis.filmes.ui.listener.PaginationListener;

import java.util.ArrayList;

public abstract class ListMoviesFragment extends Fragment {

    private Context context;
    private MovieListAdapter movieListAdapter;
    private LinearLayoutManager layoutManager;
    private int currentPage = 1;

    public ListMoviesFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView popularList = (RecyclerView) inflater.inflate(R.layout.fragment_movie_list, container, false);
        setupMoviesRecycleView(popularList);
        return popularList;
    }


    private void setupMoviesRecycleView(RecyclerView recyclerView) {
        layoutManager = new LinearLayoutManager(getContext());

        movieListAdapter = new MovieListAdapter(new ArrayList<Movie>());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieListAdapter);


        recyclerView.addOnScrollListener(new PaginationListener(layoutManager) {

            @Override
            protected void loadMoreItems() {
                currentPage++;
                loadPage(currentPage, new ListMoviesResponse() {
                    @Override
                    public void onLoad(PagedMovie pagedMovie) {
                        movieListAdapter.addAll(pagedMovie.getResults());
                    }
                });
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        });

        loadPage(currentPage, new ListMoviesResponse() {
            @Override
            public void onLoad(PagedMovie pagedMovie) {
                movieListAdapter.addAll(pagedMovie.getResults());
            }
        });
    }

    public abstract void loadPage(int page, ListMoviesResponse listMoviesResponse);


    public interface ListMoviesResponse {
        void onLoad(PagedMovie pagedMovie);
    }

}
