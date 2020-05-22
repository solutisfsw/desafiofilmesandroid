package com.example.desafioandroid.ui.activity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.example.desafioandroid.R;
import com.example.desafioandroid.model.Filme;
import com.example.desafioandroid.model.Genre;
import com.example.desafioandroid.repository.FilmeRepository;

import java.util.stream.Collectors;

import static com.example.desafioandroid.ui.adapter.MyFilmeRecyclerViewAdapter.BASE_URL_IMG;

public class DetalheFilmeActivity extends AppCompatActivity implements FilmeRepository.DadosCarregadosCallback<Filme>{

    public static final String ID_MOVIE_DETALHE = "id_movie_detalhe";
    private TextView title;
    private TextView ano;
    private TextView subTitle;
    private TextView category;
    private TextView description;
    private ImageView imgPost;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inicializarView();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Integer idFilme = (Integer) extras.get(ID_MOVIE_DETALHE);
            if(idFilme != null){
                FilmeRepository repository = new FilmeRepository();
                repository.getFilmeById(DetalheFilmeActivity.this, idFilme);
            }
        }

    }

    private void inicializarView() {
        title = findViewById(R.id.id_text_title_filme);
        ano = findViewById(R.id.id_text_ano_filme);
        subTitle = findViewById(R.id.id_text_subtitle_filme);
        category = findViewById(R.id.id_text_category_filme);
        description = findViewById(R.id.id_text_description_filme);
        imgPost = findViewById(R.id.id_img_detalhe_filme);
        imageButton = findViewById(R.id.id_icon_favorite);
        imageButton.setOnClickListener(v -> setFavorite());
    }

    private void setFavorite() {
        Resources res = getResources(); getResources();
        Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.ic_favorite_red_36dp, null);
        imageButton.setImageDrawable(drawable);
    }

    private void carreDadosNaTela(Filme result) {
        title.setText(result.getTitle());
        ano.setText(result.getReleaseDate());
        subTitle.setText(result.getTagline());
        description.setText(result.getOverview());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            category.setText(result.getGenres().stream()
                    .map(Genre::getName)
                    .collect(Collectors.joining(", ")));
        }
        carregarImg(result);

    }

    private void carregarImg(Filme result) {
        Glide.with(DetalheFilmeActivity.this)
                .load(BASE_URL_IMG +result.getPostPath())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imgPost);
    }

    @Override
    public void quandoSucesso(Filme resultado) {
        carreDadosNaTela(resultado);
    }

    @Override
    public void quandoFalha(String erro) {

    }
}
