package com.example.desafioandroid;

import android.os.Bundle;

import com.example.desafioandroid.model.Filme;
import com.example.desafioandroid.model.PageResult;
import com.example.desafioandroid.repository.FilmeRepository;
import com.example.desafioandroid.ui.main.PlaceholderFragment;
import com.example.desafioandroid.ui.main.adapter.TabAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.desafioandroid.ui.main.SectionsPagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FilmeRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlaceholderFragment(),"teste");
        adapter.addFragment(new PlaceholderFragment(),"teste 1");
        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        configFloatButton();
        repository = new FilmeRepository();
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

    private void configFloatButton() {
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}