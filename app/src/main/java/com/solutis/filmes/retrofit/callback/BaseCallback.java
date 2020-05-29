package com.solutis.filmes.retrofit.callback;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class BaseCallback<T> implements retrofit2.Callback<T> {

    private final CallBackResponse<T> callback;

    public BaseCallback(CallBackResponse<T> callback) {
        this.callback = callback;
    }

    @Override
    @EverythingIsNonNull
    public void onResponse(Call<T> call, Response<T> response) {
        if(!response.isSuccessful()){
            this.callback.fail(response.message());
            return;
        }


        this.callback.success(response.body());
    }

    @Override
    @EverythingIsNonNull
    public void onFailure(Call<T> call, Throwable t) {
        this.callback.fail(t.getMessage());
    }

    public interface CallBackResponse<T> {
        void success(T result);
        void fail(String error);
    }
}
