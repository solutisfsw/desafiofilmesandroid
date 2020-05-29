package com.solutis.filmes.asynctask;

import android.os.AsyncTask;

public class BaseAsyncTask<T> extends AsyncTask<Void, Void, T> {

    private final ExecuteListener<T> executeListener;
    private final EndsListener<T> endListener;

    public BaseAsyncTask(ExecuteListener<T> executeListener,
                         EndsListener<T> endListener) {
        this.executeListener = executeListener;
        this.endListener = endListener;
    }

    @Override
    protected T doInBackground(Void... voids) {
        return executeListener.whenExecute();
    }

    @Override
    protected void onPostExecute(T result) {
        super.onPostExecute(result);
        endListener.whenEnds(result);
    }

    public interface ExecuteListener<T> {
        T whenExecute();
    }

    public interface EndsListener<T> {
        void whenEnds(T result);
    }

}
