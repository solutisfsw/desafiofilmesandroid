package com.example.desafioandroid.repository;

import com.example.desafioandroid.model.Filme;
import com.example.desafioandroid.model.PageResult;
import com.example.desafioandroid.retrofit.FilmeRetrofit;
import com.example.desafioandroid.retrofit.callback.CallbackComRetorno;
import com.example.desafioandroid.retrofit.service.FilmeService;

import retrofit2.Call;

public class FilmeRepository {
    private final FilmeService service;

    public FilmeRepository() {
        service = new FilmeRetrofit().getFilmeService();
    }

    public void getPopular(final DadosCarregadosCallback<PageResult<Filme>> callback, int page){
        final Call<PageResult<Filme>> call = service.getPopular(page);
        call.enqueue(new CallbackComRetorno<>(
            new CallbackComRetorno.RespostaCallback<PageResult<Filme>>() {
                @Override
                public void quandoSucesso(PageResult<Filme> result) {
                    callback.quandoSucesso(result);
                }

                @Override
                public void quandoFalha(String erro) {
                    callback.quandoFalha(erro);
                }
            }));
    }

    public void getTopRated(final DadosCarregadosCallback<PageResult<Filme>> callback, int page){
        final Call<PageResult<Filme>> call = service.getTopRated(page);
        call.enqueue(new CallbackComRetorno<>(
                new CallbackComRetorno.RespostaCallback<PageResult<Filme>>() {
                    @Override
                    public void quandoSucesso(PageResult<Filme> result) {
                        callback.quandoSucesso(result);
                    }

                    @Override
                    public void quandoFalha(String erro) {
                        callback.quandoFalha(erro);
                    }
                }));
    }

    public void getUpcoming(final DadosCarregadosCallback<PageResult<Filme>> callback, int page){
        final Call<PageResult<Filme>> call = service.getUpcoming(page);
        call.enqueue(new CallbackComRetorno<>(
                new CallbackComRetorno.RespostaCallback<PageResult<Filme>>() {
                    @Override
                    public void quandoSucesso(PageResult<Filme> result) {
                        callback.quandoSucesso(result);
                    }

                    @Override
                    public void quandoFalha(String erro) {
                        callback.quandoFalha(erro);
                    }
                }));
    }

    public void getFilmeById(final DadosCarregadosCallback<Filme> callback, Integer id){
        final Call<Filme> call = service.getMovieById(id);
        call.enqueue(new CallbackComRetorno<>(
                new CallbackComRetorno.RespostaCallback<Filme>() {
                    @Override
                    public void quandoSucesso(Filme result) {
                        callback.quandoSucesso(result);
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
