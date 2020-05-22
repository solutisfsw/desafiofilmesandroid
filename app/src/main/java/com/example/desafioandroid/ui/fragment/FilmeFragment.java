package com.example.desafioandroid.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FilmeFragment extends Fragment implements  FilmeRepository.DadosCarregadosCallback<PageResult<Filme>>{

    private static final String TYPE_MOVIE = "type_movie";
    public static final int TYPE_UPCOMING = 1;
    public static final int TYPE_POPULAR = 2;
    public static final int TYPE_TOP_RATED = 3;
    private int type = 1;
    private OnListFragmentInteractionListener mListener;
    private List<Filme> listMovie = new ArrayList<>();
    private Context context;
    private RecyclerView recyclerView;
    private ProgressDialog progressDoalog;


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
        progressDoalog = new ProgressDialog(context);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        switch (type){
            case TYPE_UPCOMING:
                repository.getUpcoming(FilmeFragment.this);
                break;
            case TYPE_POPULAR:
                repository.getPopular(FilmeFragment.this);
                break;
            case TYPE_TOP_RATED:
                repository.getTopRated(FilmeFragment.this);
                break;
            default:
                return;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filme_list, container, false);

        if (view instanceof RecyclerView) {
            context = view.getContext();
            recyclerView = (RecyclerView) view;
            getMovie();

        }
        return view;
    }

    private void configRecyclerView(RecyclerView view, List<Filme> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MyFilmeRecyclerViewAdapter(list, mListener,getContext()));
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
        configRecyclerView(recyclerView,result.getResults());
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
