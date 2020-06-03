package com.cleber.ramos.desafiofilmesandroid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.cleber.ramos.desafiofilmesandroid.R;
import com.cleber.ramos.desafiofilmesandroid.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import static com.cleber.ramos.desafiofilmesandroid.ui.ConstantesActivities.CHAVE_ID_FILME_FAVORITO;
import static com.cleber.ramos.desafiofilmesandroid.ui.ConstantesActivities.PREFERENCES_ID_FILME_FAVORITO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        configuraFab();
    }

    private void configuraFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregaFilmeFavorito(view);
            }
        });
    }

    private void carregaFilmeFavorito(View view) {
        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        int idFilmeFavorito;
        if (preferences.contains(PREFERENCES_ID_FILME_FAVORITO)) {
            idFilmeFavorito = preferences.getInt(PREFERENCES_ID_FILME_FAVORITO, -1);
            if (idFilmeFavorito != -1 && idFilmeFavorito != 0) {
                Intent detalheFilmeIntent = new Intent(MainActivity.this, DetalheFilmeActivity.class);
                detalheFilmeIntent.putExtra(CHAVE_ID_FILME_FAVORITO, idFilmeFavorito);
                startActivity(detalheFilmeIntent);
            } else {
                mostraMensagemQueNaoExisteFavorito(view);
            }
        } else {
            mostraMensagemQueNaoExisteFavorito(view);
        }
    }

    private void mostraMensagemQueNaoExisteFavorito(View view) {
        Snackbar.make(view, "Nenhum favorito foi encontrado.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}