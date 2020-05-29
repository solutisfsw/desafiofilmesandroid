package com.solutis.filmes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.solutis.filmes.R;
import com.solutis.filmes.model.Movie;
import com.solutis.filmes.model.PagedMovie;
import com.solutis.filmes.repository.MovieRepository;
import com.solutis.filmes.ui.adapter.HomePagerAdapter;
import com.solutis.filmes.ui.fragment.ListMoviesFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static MovieRepository movieRepository;
    private TabLayout homeTabs;
    private ViewPager homePager;
    private FloatingActionButton favoriteActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        movieRepository = new MovieRepository(this);

        setupTabLayout();
        setupPager();

        setupFloatingFavoriteButton();

        super.onCreate(savedInstanceState);
    }

    private void setupFloatingFavoriteButton() {
        favoriteActionButton = findViewById(R.id.home_floating_action_button);
        favoriteActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieRepository.getFavoriteMovie(new MovieRepository.MovieCallback() {
                    @Override
                    public void success(Object result) {
                        Intent intent = new Intent(HomeActivity.this, MovieDetailActivity.class);
                        intent.putExtra("movie",(Movie) result);
                        startActivity(intent);
                    }

                    @Override
                    public void fail(String error) {
                        Toast.makeText(HomeActivity.this, R.string.fail_load_fav_movie, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private void setupPager() {
        homePager = findViewById(R.id.home_pager);
        homePager.setAdapter(new HomePagerAdapter(getSupportFragmentManager(), getFragments()));
    }

    private void setupTabLayout() {
        homeTabs = findViewById(R.id.home_tabs);

        homeTabs.addTab(homeTabs.newTab().setText(R.string.tab_item_popular));
        homeTabs.addTab(homeTabs.newTab().setText(R.string.tab_item_recent));
        homeTabs.addTab(homeTabs.newTab().setText(R.string.tab_item_top_rated));

        homeTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                homePager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private List<Fragment> getFragments() {
        List<Fragment> listFragments = new ArrayList<>();

        listFragments.add(new UpcomingListMoviesFragment());
        listFragments.add(new PopularListMoviesFragment());
        listFragments.add(new TopRatedListMoviesFragment());

        return listFragments;
    }

    public static class UpcomingListMoviesFragment extends ListMoviesFragment {

        @Override
        public void loadPage(int page, ListMoviesResponse listMoviesResponse) {
            movieRepository.getUpcomingMovies(page, new MovieRepository.MovieCallback<PagedMovie>() {
                @Override
                public void success(PagedMovie result) {
                    listMoviesResponse.onLoad(result);
                }

                @Override
                public void fail(String error) {
                    Toast.makeText(getContext(), R.string.error_list_movies, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static class PopularListMoviesFragment extends ListMoviesFragment {

        @Override
        public void loadPage(int page, ListMoviesResponse listMoviesResponse) {
            movieRepository.getPopularMovies(page, new MovieRepository.MovieCallback<PagedMovie>() {
                @Override
                public void success(PagedMovie result) {
                    listMoviesResponse.onLoad(result);
                }

                @Override
                public void fail(String error) {
                    Toast.makeText(getContext(), R.string.error_list_movies, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static class TopRatedListMoviesFragment extends ListMoviesFragment {

        @Override
        public void loadPage(int page, ListMoviesResponse listMoviesResponse) {
            movieRepository.getTopRatedMovies(page, new MovieRepository.MovieCallback<PagedMovie>() {
                @Override
                public void success(PagedMovie result) {
                    listMoviesResponse.onLoad(result);
                }

                @Override
                public void fail(String error) {
                    Toast.makeText(getContext(), R.string.error_list_movies, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
