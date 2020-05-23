package com.example.desafioandroid.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafioandroid.R;
import com.example.desafioandroid.model.Filme;
import com.example.desafioandroid.model.PageResult;
import com.example.desafioandroid.repository.FilmeRepository;
import com.example.desafioandroid.ui.adapter.MyFilmeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FilmeFragment extends Fragment implements  FilmeRepository.DadosCarregadosCallback<PageResult<Filme>> {

    private static final String TYPE_MOVIE = "type_movie";
    public static final int TYPE_UPCOMING = 1;
    public static final int TYPE_POPULAR = 2;
    public static final int TYPE_TOP_RATED = 3;
    private int type = 1;
    private OnListFragmentInteractionListener mListener;
    private Context context;
    private RecyclerView recyclerView;
    private ProgressDialog progressDoalog;
    private PageResult<Filme> pageResult;
    private int firstVisibleItem, totalItemCount;
    private boolean loading = true;
    private int previousTotal = 0;
    private int visibleThreshold = 4;
    private List<Filme> filmeList = new ArrayList<>();


    public FilmeFragment() {
    }

    @SuppressWarnings("unused")
    public static FilmeFragment newInstance(int type) {
        FilmeFragment fragment = new FilmeFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE_MOVIE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(TYPE_MOVIE);
        }
    }

    private void getMovie() {
        FilmeRepository repository = new FilmeRepository();
        progressShow();
        switch (type){
            case TYPE_UPCOMING:
                repository.getUpcoming(FilmeFragment.this, pageResult != null ? pageResult.getPage() + 1 : 1);
                break;
            case TYPE_POPULAR:
                repository.getPopular(FilmeFragment.this, pageResult != null ? pageResult.getPage() + 1 : 1);
                break;
            case TYPE_TOP_RATED:
                repository.getTopRated(FilmeFragment.this, pageResult != null ? pageResult.getPage() + 1 : 1);
                break;
            default:
                return;
        }
    }

    private void progressShow() {
        progressDoalog = new ProgressDialog(context);
        progressDoalog.setMessage("Loading....");
        progressDoalog.setCancelable(false);
        progressDoalog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filme_list, container, false);

        if (view instanceof RecyclerView) {
            context = view.getContext();
            recyclerView = (RecyclerView) view;
            getMovie();
            configRecyclerView(recyclerView);
        }
        return view;
    }

    private void configRecyclerView(RecyclerView view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MyFilmeRecyclerViewAdapter(filmeList, mListener,getContext()));
        LinearLayoutManager layout = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layout.getItemCount();
                firstVisibleItem = layout.findFirstVisibleItemPosition();
                //se tiver chamado a nova pagina pega o novo total
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if( !loading && totalItemCount <= (firstVisibleItem + visibleThreshold)){
                    getMovie();
                    loading = true;
                }
            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void quandoSucesso(PageResult<Filme> result) {
        Log.d("aqui", "quandoSucesso: "+ result);
        pageResult = result;
        filmeList.addAll(pageResult.getResults());
        recyclerView.getAdapter().notifyDataSetChanged();

        progressDoalog.dismiss();
    }

    @Override
    public void quandoFalha(String erro) {
        //mostraErro(MENSAGEM_ERRO_BUSCA_PRODUTOS);
        progressDoalog.dismiss();
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Filme item);
    }
}
