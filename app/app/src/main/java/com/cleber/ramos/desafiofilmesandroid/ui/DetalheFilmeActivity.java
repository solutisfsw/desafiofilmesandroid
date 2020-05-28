package com.cleber.ramos.desafiofilmesandroid.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.cleber.ramos.desafiofilmesandroid.R;
import com.cleber.ramos.desafiofilmesandroid.model.FilmeDetalhe;
import com.cleber.ramos.desafiofilmesandroid.model.Genre;
import com.cleber.ramos.desafiofilmesandroid.repository.FilmeRepository;
import com.cleber.ramos.desafiofilmesandroid.retrofit.FilmesRetrofit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.cleber.ramos.desafiofilmesandroid.ui.ConstantesActivities.CHAVE_ID_FILME;
import static com.cleber.ramos.desafiofilmesandroid.ui.ConstantesActivities.CHAVE_ID_FILME_FAVORITO;
import static com.cleber.ramos.desafiofilmesandroid.ui.ConstantesActivities.MENSAGEM_CARREGANDO_DETALHE_FILME;
import static com.cleber.ramos.desafiofilmesandroid.ui.ConstantesActivities.PREFERENCES_ID_FILME_FAVORITO;
import static com.cleber.ramos.desafiofilmesandroid.ui.ConstantesActivities.TITULO_APPBAR;

public class DetalheFilmeActivity extends AppCompatActivity {

    private ImageView imgemFavorito;
    private TextView tituloFilme;
    private TextView dataLancamentoFilme;
    private TextView taglineFilme;
    private TextView generosFilme;
    private TextView descricaoFilme;
    private ImageView posterFilme;
    private Bundle bundleActivity;
    private ProgressDialog dialog;
    private FilmeRepository filmeRepository;
    private SharedPreferences preferences;
    private int idFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);
        setTitle(TITULO_APPBAR);

        inicializacaoDosCampos();

        imgemFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verificaSeFilmeEhFavorito(idFilme)) {
                    salvaFilmeFavorito(idFilme);
                } else {
                    removeFilmeFavorito();
                }
            }
        });

        configuraSharedPreferences();
        configuraProgressDialog();

        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_ID_FILME)) {
            bundleActivity = intent.getExtras();
            idFilme = bundleActivity.getInt(CHAVE_ID_FILME);

            buscaDetalhesFilmeNaApi(idFilme);

            atribuiImagemFavoritoDesmarcado();

            if (verificaSeFilmeEhFavorito(idFilme)) {
                salvaFilmeFavorito(idFilme);
            }
        } else if (intent.hasExtra(CHAVE_ID_FILME_FAVORITO)) {
            bundleActivity = intent.getExtras();
            idFilme = bundleActivity.getInt(CHAVE_ID_FILME_FAVORITO);
            buscaDetalhesFilmeNaApi(idFilme);
            salvaFilmeFavorito(idFilme);
        }
    }

    private void atribuiImagemFavoritoDesmarcado() {
        Drawable drawable= getResources().getDrawable(R.drawable.ic_action_favorito_desmarcado);
        imgemFavorito.setImageDrawable(drawable);
    }

    private void atribuiImagemFavoritoMarcado() {
        Drawable drawable= getResources().getDrawable(R.drawable.ic_action_favorito_marcado);
        imgemFavorito.setImageDrawable(drawable);
    }

    private void configuraSharedPreferences() {
        preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
    }

    private void salvaFilmeFavorito(int idFilme) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PREFERENCES_ID_FILME_FAVORITO, idFilme);
        editor.commit();
        atribuiImagemFavoritoMarcado();
    }

    private boolean verificaSeFilmeEhFavorito(int idFilme) {
        int idFilmeFavorito;
        if (preferences.contains(PREFERENCES_ID_FILME_FAVORITO)) {
            idFilmeFavorito = preferences.getInt(PREFERENCES_ID_FILME_FAVORITO, -1);
            if (idFilmeFavorito != -1 && idFilmeFavorito == idFilme) {
                return true;
            }
        }
        return false;
    }

    private void removeFilmeFavorito() {
        salvaFilmeFavorito(0);
        atribuiImagemFavoritoDesmarcado();
    }

    private void buscaDetalhesFilmeNaApi(int idFilme) {
        mostraProgressDialog(MENSAGEM_CARREGANDO_DETALHE_FILME);
        filmeRepository = new FilmeRepository();
        filmeRepository.buscaDetalhesFilmeNaApi(new FilmeRepository.DadosCarregadosCallback<FilmeDetalhe>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void quandoSucesso(FilmeDetalhe filme) {
                try {
                    carregaInformacoesNaTela(filme);
                } catch (Exception ex) {
                    mostraErroNaTela("Erro ao buscar dados na API. Detalhes: " + ex.getMessage());
                } finally {
                    fechaProgressDialog();
                }
            }
            @Override
            public void quandoFalha(String erro) {
                try {
                    mostraErroNaTela(erro);
                } catch (Exception ex) {
                    mostraErroNaTela("Erro ao buscar dados na API. Detalhes: " + ex.getMessage());
                } finally {
                    fechaProgressDialog();
                }
            }
        }, idFilme);
    }

    private void configuraProgressDialog() {
        dialog = new ProgressDialog(DetalheFilmeActivity.this);
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
    }

    private void mostraProgressDialog(String msg) {
        dialog.setMessage(msg);
        dialog.show();
    }

    private void fechaProgressDialog() {
        dialog.dismiss();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void carregaInformacoesNaTela(FilmeDetalhe filme) {
        Glide.with(getBaseContext())
                .load(FilmesRetrofit.URL_BASE_IMAGEM + filme.getPosterPath())
                .placeholder(R.drawable.ic_action_imagem_carregando)
                .error(R.drawable.ic_action_imagem_erro)
                .centerCrop()
                .into(posterFilme);

        tituloFilme.setText(filme.getTitle());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        try {
            Date data = formatter.parse(filme.getReleaseDate());
            dataLancamentoFilme.setText(formatter.format(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        taglineFilme.setText(filme.getTagline());

        List<String> genres = new ArrayList<>();
        for (Genre g : filme.getGenres()) {
            genres.add(g.name);
        }

        generosFilme.setText(String.join(", ", genres));
        descricaoFilme.setText(filme.getOverview());
    }

    private void inicializacaoDosCampos() {
        imgemFavorito = findViewById(R.id.detalhe_filme_botao_favorito);
        posterFilme = findViewById(R.id.detalhe_filme_imagem_filme);
        tituloFilme = findViewById(R.id.detalhe_filme_title);
        dataLancamentoFilme = findViewById(R.id.detalhe_filme_release_date);
        taglineFilme = findViewById(R.id.detalhe_filme_tagline);
        generosFilme = findViewById(R.id.detalhe_filme_genres);
        descricaoFilme = findViewById(R.id.detalhe_filme_overview);
    }

    private void mostraErroNaTela(String msgErro) {
        new AlertDialog.Builder(this)
                .setTitle("Erro")
                .setMessage(msgErro)
                .setIcon(android.R.drawable.stat_notify_error)
                .show();
    }

}
