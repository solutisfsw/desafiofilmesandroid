package com.cleber.ramos.desafiofilmesandroid.repository;

import com.cleber.ramos.desafiofilmesandroid.model.Acervo;
import com.cleber.ramos.desafiofilmesandroid.model.FilmeDetalhe;
import com.cleber.ramos.desafiofilmesandroid.retrofit.FilmesRetrofit;
import com.cleber.ramos.desafiofilmesandroid.retrofit.callback.CallbackComRetorno;
import com.cleber.ramos.desafiofilmesandroid.retrofit.service.ApiService;

import retrofit2.Call;

public class FilmeRepository {

    public static ApiService service;

    public FilmeRepository() {
        service = new FilmesRetrofit().getApiService();
    }

    public void buscaFilmesMaisRecentesNaApi(DadosCarregadosCallback<Acervo> callback) {
        Call<Acervo> call = service.buscaFilmesMaisRecentes();
        call.enqueue(new CallbackComRetorno<Acervo>(new CallbackComRetorno.RespostaCallback() {
            @Override
            public void quandoSucesso(Object resultado) {
                Acervo acervo = (Acervo)resultado;
                callback.quandoSucesso(acervo);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    public void buscaFilmesMaisRecentesNaApi(DadosCarregadosCallback<Acervo> callback, Integer numPagina) {
        Call<Acervo> call = service.buscaFilmesMaisRecentes(numPagina);
        call.enqueue(new CallbackComRetorno<Acervo>(new CallbackComRetorno.RespostaCallback() {
            @Override
            public void quandoSucesso(Object resultado) {
                Acervo acervo = (Acervo)resultado;
                callback.quandoSucesso(acervo);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    public void buscaFilmesMaisPopularesNaApi(DadosCarregadosCallback<Acervo> callback) {
        Call<Acervo> call = service.buscaFilmesMaisPopulares();
        call.enqueue(new CallbackComRetorno<Acervo>(new CallbackComRetorno.RespostaCallback() {
            @Override
            public void quandoSucesso(Object resultado) {
                Acervo acervo = (Acervo)resultado;
                callback.quandoSucesso(acervo);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    public void buscaFilmesMaisPopularesNaApi(DadosCarregadosCallback<Acervo> callback, Integer numPagina) {
        Call<Acervo> call = service.buscaFilmesMaisPopulares(numPagina);
        call.enqueue(new CallbackComRetorno<Acervo>(new CallbackComRetorno.RespostaCallback() {
            @Override
            public void quandoSucesso(Object resultado) {
                Acervo acervo = (Acervo)resultado;
                callback.quandoSucesso(acervo);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    public void buscaFilmesMaisAvaliadosNaApi(DadosCarregadosCallback<Acervo> callback) {
        Call<Acervo> call = service.buscaFilmesMaisAvaliados();
        call.enqueue(new CallbackComRetorno<Acervo>(new CallbackComRetorno.RespostaCallback() {
            @Override
            public void quandoSucesso(Object resultado) {
                Acervo acervo = (Acervo)resultado;
                callback.quandoSucesso(acervo);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    public void buscaFilmesMaisAvaliadosNaApi(DadosCarregadosCallback<Acervo> callback, Integer numPagina) {
        Call<Acervo> call = service.buscaFilmesMaisAvaliados(numPagina);
        call.enqueue(new CallbackComRetorno<Acervo>(new CallbackComRetorno.RespostaCallback() {
            @Override
            public void quandoSucesso(Object resultado) {
                Acervo acervo = (Acervo)resultado;
                callback.quandoSucesso(acervo);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    public void buscaDetalhesFilmeNaApi(DadosCarregadosCallback<FilmeDetalhe> callback, Integer idFilme) {
        Call<FilmeDetalhe> call = service.buscaDetalhesDeUmFilme(idFilme);
        call.enqueue(new CallbackComRetorno<FilmeDetalhe>(new CallbackComRetorno.RespostaCallback() {
            @Override
            public void quandoSucesso(Object resultado) {
                FilmeDetalhe filmeDetalhe = (FilmeDetalhe)resultado;
                callback.quandoSucesso(filmeDetalhe);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    public interface DadosCarregadosCallback <T> {
        void quandoSucesso(T resultado);
        void quandoFalha(String erro);
    }

}
