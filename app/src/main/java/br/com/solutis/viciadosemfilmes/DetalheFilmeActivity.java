package br.com.solutis.viciadosemfilmes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.Calendar;

import br.com.solutis.viciadosemfilmes.config.RetrofitConfig;
import br.com.solutis.viciadosemfilmes.model.Filme;
import br.com.solutis.viciadosemfilmes.model.Genero;
import br.com.solutis.viciadosemfilmes.service.FilmeService;
import br.com.solutis.viciadosemfilmes.ui.constants.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheFilmeActivity extends AppCompatActivity {

    private long id;
    private ImageView capa;
    private TextView titulo;
    private TextView ano;
    private TextView tagline;
    private TextView genero;
    private TextView sinopse;
    private ImageView favedIcon;
    private ImageView unFavedIcon;
    private SharedPreferences preferences;
    private ProgressBar progressBar;
    private ConstraintLayout telaDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);
        Intent dados = getIntent();
        preferences = getSharedPreferences(Constantes.USER_PREFERENCES, MODE_PRIVATE);
        carregaIdFilme(dados);

        try {
            consultaFilme();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregaIdFilme(Intent dados) {
        if(dados.hasExtra(Constantes.EXTRA_KEY)){
            id = (long) dados.getSerializableExtra(Constantes.EXTRA_KEY);
        }else{
            id = preferences.getLong(Constantes.PREFERENCES_KEY, 0L);
        }
    }

    private void consultaFilme() throws IOException {
        FilmeService service = new RetrofitConfig().getFilmeService();
        Call<Filme> call = service.buscarFilme(id, Constantes.API_KEY);
        call.enqueue(new Callback<Filme>() {
            @Override
            public void onResponse(Call<Filme> call, Response<Filme> response) {
                Filme filme = response.body();
                inicializaCampos();
                carregaCampos(filme);
            }

            @Override
            public void onFailure(Call<Filme> call, Throwable t) {
                Toast.makeText(DetalheFilmeActivity.this, Constantes.MSG_ERRO, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void carregaCampos(Filme filme) {
        Glide.with(this).load(Constantes.URL_IMAGEM_TMDB + filme.getCapa()).centerCrop().placeholder(R.drawable.icon_loading).into(capa);
        titulo.setText(filme.getTitulo());
        carregaAno(filme);
        tagline.setText(filme.getTagline());
        carregaGenero(filme);
        sinopse.setText(filme.getSinopse());
        carregaListenersFavoritos();
        carregaFavoritado(filme);
        progressBar.setVisibility(View.INVISIBLE);
        telaDetalhe.setVisibility(View.VISIBLE);
    }

    private void carregaListenersFavoritos() {
        SharedPreferences.Editor editor = preferences.edit();

        favedIcon.setOnClickListener(v ->{
            Toast.makeText(DetalheFilmeActivity.this, Constantes.FAV_REMOVIDO, Toast.LENGTH_SHORT).show();
            favedIcon.setVisibility(View.INVISIBLE);
            unFavedIcon.setVisibility(View.VISIBLE);
            editor.remove(Constantes.PREFERENCES_KEY);
            editor.commit();
        });
        unFavedIcon.setOnClickListener(v -> {
            Toast.makeText(DetalheFilmeActivity.this, Constantes.FAV_ADICIONADO, Toast.LENGTH_SHORT).show();
            favedIcon.setVisibility(View.VISIBLE);
            unFavedIcon.setVisibility(View.INVISIBLE);
            editor.putLong(Constantes.PREFERENCES_KEY, id);
            editor.commit();
        });
    }

    private void carregaFavoritado(Filme filme) {
        Long idFilme = preferences.getLong(Constantes.PREFERENCES_KEY, 0L);
        if(filme.getId() == idFilme){
            favedIcon.setVisibility(View.VISIBLE);
        }else{
            unFavedIcon.setVisibility(View.VISIBLE);
        }
    }

    private void carregaGenero(Filme filme) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Genero genero : filme.getGeneroList()) {
            if(i++ == filme.getGeneroList().size() -1 ){
                builder.append(genero.getName());
            }
            if(i < filme.getGeneroList().size()){
                builder.append(genero.getName() + ", ");
            }
        }
        genero.setText(builder);
    }

    private void carregaAno(Filme filme) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(filme.getLancamento());
        ano.setText(Integer.toString(cal.get(Calendar.YEAR)));
    }

    private void inicializaCampos() {
        capa = findViewById(R.id.detalhe_filme_capa);
        titulo = findViewById(R.id.detalhe_filme_titulo);
        ano = findViewById(R.id.detalhe_filme_ano);
        tagline = findViewById(R.id.detalhe_filme_tagline);
        genero = findViewById(R.id.detalhe_filme_genero);
        sinopse = findViewById(R.id.detalhe_filme_sinopse);
        favedIcon = findViewById(R.id.icone_faved);
        unFavedIcon = findViewById(R.id.icone_unfaved);
        progressBar = findViewById(R.id.detalhe_filme_progress);
        telaDetalhe = findViewById(R.id.detalhe_filme_tela);
    }

}
