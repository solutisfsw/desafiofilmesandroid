package br.com.solutis.viciadosemfilmes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import br.com.solutis.viciadosemfilmes.config.RetrofitConfig;
import br.com.solutis.viciadosemfilmes.model.Filme;
import br.com.solutis.viciadosemfilmes.model.FilmesList;
import br.com.solutis.viciadosemfilmes.service.FilmeService;
import br.com.solutis.viciadosemfilmes.ui.adapter.SectionsPagerAdapter;
import br.com.solutis.viciadosemfilmes.ui.constants.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Filme> filmesRecentes;
    private List<Filme> filmesPopulares;
    private List<Filme> filmesTopRated;
    private SectionsPagerAdapter sectionsPagerAdapter;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(Constantes.USER_PREFERENCES, MODE_PRIVATE);

        consultaRecentes();
    }

    private void consultaRecentes() {
        FilmeService service = new RetrofitConfig().getFilmeService();
        Call<FilmesList> call = service.buscarFilmesRecentes(Constantes.API_KEY);
        call.enqueue(new Callback<FilmesList>() {
            @Override
            public void onResponse(Call<FilmesList> call, Response<FilmesList> response) {
                FilmesList filmesList = response.body();
                filmesRecentes = filmesList.getFilmesList();
                consultaPopulares();
            }

            @Override
            public void onFailure(Call<FilmesList> call, Throwable t) {
                Toast.makeText(MainActivity.this, Constantes.MSG_ERRO, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void consultaPopulares() {
        FilmeService service = new RetrofitConfig().getFilmeService();
        Call<FilmesList> call = service.buscarFilmesPopulares(Constantes.API_KEY);
        call.enqueue(new Callback<FilmesList>() {
            @Override
            public void onResponse(Call<FilmesList> call, Response<FilmesList> response) {
                FilmesList filmesList = response.body();
                filmesPopulares = filmesList.getFilmesList();
                consultaTopRated();
            }

            @Override
            public void onFailure(Call<FilmesList> call, Throwable t) {
                Toast.makeText(MainActivity.this, Constantes.MSG_ERRO, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void consultaTopRated() {
        FilmeService service = new RetrofitConfig().getFilmeService();
        Call<FilmesList> call = service.buscarFilmesTopRated(Constantes.API_KEY);
        call.enqueue(new Callback<FilmesList>() {
            @Override
            public void onResponse(Call<FilmesList> call, Response<FilmesList> response) {
                FilmesList filmesList = response.body();
                filmesTopRated = filmesList.getFilmesList();
                carregaPosProcessamento();
            }

            @Override
            public void onFailure(Call<FilmesList> call, Throwable t) {
                Toast.makeText(MainActivity.this, Constantes.MSG_ERRO, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void carregaPosProcessamento() {
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), filmesRecentes, filmesPopulares, filmesTopRated);

        exibeFaved();
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private void exibeFaved() {
        FloatingActionButton fab = findViewById(R.id.main_fab_favorite);
        fab.setOnClickListener(v -> {
            if (preferences.contains(Constantes.PREFERENCES_KEY)) {
                startActivity(new Intent(MainActivity.this, DetalheFilmeActivity.class));
            } else {
                Toast.makeText(MainActivity.this, Constantes.MSG_FAV_NAO_ENCONTRADO, Toast.LENGTH_SHORT).show();
            }
        });
    }

}