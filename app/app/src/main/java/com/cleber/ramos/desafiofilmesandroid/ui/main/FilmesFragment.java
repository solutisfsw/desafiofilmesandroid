package com.cleber.ramos.desafiofilmesandroid.ui.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cleber.ramos.desafiofilmesandroid.R;
import com.cleber.ramos.desafiofilmesandroid.model.Acervo;
import com.cleber.ramos.desafiofilmesandroid.model.Filme;
import com.cleber.ramos.desafiofilmesandroid.repository.FilmeRepository;
import com.cleber.ramos.desafiofilmesandroid.ui.DetalheFilmeActivity;
import com.cleber.ramos.desafiofilmesandroid.ui.recyclerview.adapter.ListaFilmesAdapter;

import static com.cleber.ramos.desafiofilmesandroid.ui.ConstantesActivities.CHAVE_ID_FILME;
import static com.cleber.ramos.desafiofilmesandroid.ui.ConstantesActivities.MENSAGEM_CARREGANDO_FILME;

public class FilmesFragment extends Fragment {

    private static final int INDEX_FILMES_MAIS_RECENTES = 0;
    private static final int INDEX_FILMES_MAIS_POPULARES = 1;
    private static final int INDEX_FILMES_MAIS_AVALIADOS = 2;

    private View rootViewFilmesMaisRecentes;
    private View rootViewFilmesMaisPopulares;
    private View rootViewFilmesMaisAvaliados;

    private ProgressBar progressBar;
    private ListaFilmesAdapter adapterMaisRecentes;
    private ListaFilmesAdapter adapterFilmesPopulares;
    private ListaFilmesAdapter adapterFilmesMaisAvaliados;
    private FilmeRepository filmeRepository;
    private ProgressDialog dialog;

    private final Context mContext;

    private boolean isScrolling = false;
    private int index;
    private int currentItems, totalItems, scrollOutItems;
    private int numeroPaginaAtual, totalPaginas;

    public static FilmesFragment newInstance(int index, Context mContext) {
        FilmesFragment fragment = new FilmesFragment(index, mContext);
        return fragment;
    }

    private FilmesFragment(int index, Context mContext) {
        this.mContext = mContext;
        this.filmeRepository = new FilmeRepository();
        this.index = index;
        configuraProgressDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_filmes, container, false);
        if (this.index == INDEX_FILMES_MAIS_RECENTES) {
            if (rootViewFilmesMaisRecentes == null) {
                rootViewFilmesMaisRecentes = root;
                configuraRecyclerView(rootViewFilmesMaisRecentes);
            }
            return rootViewFilmesMaisRecentes;
        } else if (this.index == INDEX_FILMES_MAIS_POPULARES) {
            if (rootViewFilmesMaisPopulares == null) {
                rootViewFilmesMaisPopulares = root;
                configuraRecyclerView(rootViewFilmesMaisPopulares);
            }
            return rootViewFilmesMaisPopulares;
        } else if (this.index == INDEX_FILMES_MAIS_AVALIADOS) {
            if (rootViewFilmesMaisAvaliados == null) {
                rootViewFilmesMaisAvaliados = root;
                configuraRecyclerView(rootViewFilmesMaisAvaliados);
            }
            return rootViewFilmesMaisAvaliados;
        }
        return null;
    }

    private void configuraRecyclerView(View root) {
        RecyclerView listaFilmes = root.findViewById(R.id.lista_filmes_mais_recentes);
        progressBar = root.findViewById(R.id.progressBar);
        configuraAdapter(listaFilmes);
    }

    private void configuraAdapter(RecyclerView listaFilmes) {
        if (this.index == INDEX_FILMES_MAIS_RECENTES) {
            adapterMaisRecentes = new ListaFilmesAdapter(mContext, (posicao, filme) -> selecionaFilme(posicao, filme));
            configuraScrollingNoAdapter(listaFilmes);
            carregaFilmesMaisRecentes();
        } else if (this.index == INDEX_FILMES_MAIS_POPULARES) {
            adapterFilmesPopulares = new ListaFilmesAdapter(mContext, (posicao, filme) -> selecionaFilme(posicao, filme));
            configuraScrollingNoAdapter(listaFilmes);
            carregaFilmesMaisPopulares();
        } else if (this.index == INDEX_FILMES_MAIS_AVALIADOS) {
            adapterFilmesMaisAvaliados = new ListaFilmesAdapter(mContext, (posicao, filme) -> selecionaFilme(posicao, filme));
            configuraScrollingNoAdapter(listaFilmes);
            carregaFilmesMaisAvaliados();
        }
    }

    private void configuraScrollingNoAdapter(RecyclerView listaFilmes) {
        if (this.index == INDEX_FILMES_MAIS_RECENTES) {
            listaFilmes.setAdapter(adapterMaisRecentes);
        } else if (this.index == INDEX_FILMES_MAIS_POPULARES) {
            listaFilmes.setAdapter(adapterFilmesPopulares);
        } else if (this.index == INDEX_FILMES_MAIS_AVALIADOS) {
            listaFilmes.setAdapter(adapterFilmesMaisAvaliados);
        }

        listaFilmes.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    carregaMaisFilmes();
                }
            }
        });
    }

    private void carregaMaisFilmes() {
        numeroPaginaAtual++;
        if (numeroPaginaAtual <= totalPaginas) progressBar.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (numeroPaginaAtual <= totalPaginas && index == INDEX_FILMES_MAIS_RECENTES) {
                    carregaMaisFilmesMaisRecentes();
                } else if (numeroPaginaAtual <= totalPaginas && index == INDEX_FILMES_MAIS_POPULARES)  {
                    carregaMaisFilmesMaisPopulares();
                } else if (numeroPaginaAtual <= totalPaginas && index == INDEX_FILMES_MAIS_AVALIADOS) {
                    carregaMaisFilmesMaisAvaliados();
                }
            }
        }, 3000);
    }

    private void carregaFilmesMaisRecentes() {
        mostraProgressDialog(MENSAGEM_CARREGANDO_FILME);
        filmeRepository.buscaFilmesMaisRecentesNaApi(new FilmeRepository.DadosCarregadosCallback<Acervo>() {
            @Override
            public void quandoSucesso(Acervo resultado) {
                numeroPaginaAtual = resultado.getPage();
                totalPaginas = resultado.getTotalPages();
                adapterMaisRecentes.carregaListaFilmes(resultado.getFilmes());
                fechaProgressDialog();
            }

            @Override
            public void quandoFalha(String erro) {
                progressBar.setVisibility(View.GONE);
                fechaProgressDialog();
                mostraErroNaTela(erro);
            }
        });
    }

    private void carregaMaisFilmesMaisRecentes() {
        filmeRepository.buscaFilmesMaisRecentesNaApi(new FilmeRepository.DadosCarregadosCallback<Acervo>() {
            @Override
            public void quandoSucesso(Acervo resultado) {
                numeroPaginaAtual = resultado.getPage();
                totalPaginas = resultado.getTotalPages();

                adapterMaisRecentes.adicionaListaFilmes(resultado.getFilmes());
                adapterMaisRecentes.notificaAdapter();

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void quandoFalha(String erro) {
                progressBar.setVisibility(View.GONE);
                mostraErroNaTela(erro);
            }
        }, numeroPaginaAtual);
    }

    private void carregaFilmesMaisPopulares() {
        mostraProgressDialog(MENSAGEM_CARREGANDO_FILME);
        filmeRepository.buscaFilmesMaisPopularesNaApi(new FilmeRepository.DadosCarregadosCallback<Acervo>() {
            @Override
            public void quandoSucesso(Acervo resultado) {
                numeroPaginaAtual = resultado.getPage();
                totalPaginas = resultado.getTotalPages();
                adapterFilmesPopulares.carregaListaFilmes(resultado.getFilmes());
                fechaProgressDialog();
            }

            @Override
            public void quandoFalha(String erro) {
                progressBar.setVisibility(View.GONE);
                fechaProgressDialog();
                mostraErroNaTela(erro);
            }
        });
    }

    private void carregaMaisFilmesMaisPopulares() {
        filmeRepository.buscaFilmesMaisPopularesNaApi(new FilmeRepository.DadosCarregadosCallback<Acervo>() {
            @Override
            public void quandoSucesso(Acervo resultado) {
                numeroPaginaAtual = resultado.getPage();
                totalPaginas = resultado.getTotalPages();

                adapterFilmesPopulares.adicionaListaFilmes(resultado.getFilmes());
                adapterFilmesPopulares.notificaAdapter();

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void quandoFalha(String erro) {
                progressBar.setVisibility(View.GONE);
                mostraErroNaTela(erro);
            }
        }, numeroPaginaAtual);
    }

    private void carregaFilmesMaisAvaliados() {
        mostraProgressDialog(MENSAGEM_CARREGANDO_FILME);
        filmeRepository.buscaFilmesMaisAvaliadosNaApi(new FilmeRepository.DadosCarregadosCallback<Acervo>() {
            @Override
            public void quandoSucesso(Acervo resultado) {
                numeroPaginaAtual = resultado.getPage();
                totalPaginas = resultado.getTotalPages();
                adapterFilmesMaisAvaliados.carregaListaFilmes(resultado.getFilmes());
                fechaProgressDialog();
            }

            @Override
            public void quandoFalha(String erro) {
                progressBar.setVisibility(View.GONE);
                fechaProgressDialog();
                mostraErroNaTela(erro);
            }
        });
    }

    private void carregaMaisFilmesMaisAvaliados() {
        filmeRepository.buscaFilmesMaisAvaliadosNaApi(new FilmeRepository.DadosCarregadosCallback<Acervo>() {
            @Override
            public void quandoSucesso(Acervo resultado) {
                numeroPaginaAtual = resultado.getPage();
                totalPaginas = resultado.getTotalPages();

                adapterFilmesMaisAvaliados.adicionaListaFilmes(resultado.getFilmes());
                adapterFilmesMaisAvaliados.notificaAdapter();

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void quandoFalha(String erro) {
                progressBar.setVisibility(View.GONE);
                mostraErroNaTela(erro);
            }
        }, numeroPaginaAtual);
    }

    private void configuraProgressDialog() {
        dialog = new ProgressDialog(mContext);
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

    private void selecionaFilme(int posicao, Filme filme) {
        Intent detalheFilmeIntent = new Intent(getActivity(), DetalheFilmeActivity.class);
        detalheFilmeIntent.putExtra(CHAVE_ID_FILME, filme.getId());
        startActivity(detalheFilmeIntent);
    }

    private void mostraErroNaTela(String msgErro) {
        new AlertDialog.Builder(mContext)
                .setTitle("Erro")
                .setMessage(msgErro)
                .setIcon(android.R.drawable.stat_notify_error)
                .show();
    }

}
