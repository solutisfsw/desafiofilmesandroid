package com.example.desafioandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.desafioandroid.model.Filme;
import com.example.desafioandroid.model.PageResult;
import com.example.desafioandroid.repository.FilmeRepository;
import com.example.desafioandroid.ui.main.adapter.TabAdapter;
import com.example.desafioandroid.ui.main.fragment.FilmeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

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
        FilmeRepository repository = new FilmeRepository();
        repository.getPopular(new FilmeRepository.DadosCarregadosCallback<PageResult<Filme>>() {
            @Override
            public void quandoSucesso(PageResult<Filme> result) {
                Log.d("aqui", "quandoSucesso: "+ result);
            }

            @Override
            public void quandoFalha(String erro) {
                //mostraErro(MENSAGEM_ERRO_BUSCA_PRODUTOS);
            }
        });
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
        Log.d("teste", "onListFragmentInteraction: teste");
    }
}