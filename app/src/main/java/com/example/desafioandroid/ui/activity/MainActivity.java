package com.example.desafioandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.desafioandroid.R;
import com.example.desafioandroid.model.Filme;
import com.example.desafioandroid.ui.adapter.TabAdapter;
import com.example.desafioandroid.ui.fragment.FilmeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import static com.example.desafioandroid.ui.activity.DetalheFilmeActivity.ID_MOVIE_DETALHE;

public class MainActivity extends AppCompatActivity implements FilmeFragment.OnListFragmentInteractionListener {

    public static final String RECENTES = "RECENTES";
    public static final String POPULARES = "POPULARES";
    public static final String TOP_RATED = "TOP RATED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configTab();
        configFloatButton();
    }

    private void configTab() {
        final TabAdapter adapter = getTabAdapter();
        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

    private TabAdapter getTabAdapter() {
        final TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(FilmeFragment.newInstance(FilmeFragment.TYPE_UPCOMING), RECENTES);
        adapter.addFragment(FilmeFragment.newInstance(FilmeFragment.TYPE_POPULAR), POPULARES);
        adapter.addFragment(FilmeFragment.newInstance(FilmeFragment.TYPE_TOP_RATED), TOP_RATED);
        return adapter;
    }

    private void configFloatButton() {
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public void onListFragmentInteraction(Filme item) {
        Intent i = new Intent(MainActivity.this, DetalheFilmeActivity.class);
        i.putExtra(ID_MOVIE_DETALHE, item.getId());
        startActivity(i);
    }
}