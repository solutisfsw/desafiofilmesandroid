package com.example.desafioandroid.ui.main.fragment;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.desafioandroid.R;
import com.example.desafioandroid.model.Filme;
import com.example.desafioandroid.ui.main.fragment.FilmeFragment.OnListFragmentInteractionListener;
import com.example.desafioandroid.ui.main.fragment.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFilmeRecyclerViewAdapter extends RecyclerView.Adapter<MyFilmeRecyclerViewAdapter.ViewHolder> {

    public static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w185";
    private final List<Filme> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context context;

    public MyFilmeRecyclerViewAdapter(List<Filme> items, OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_filme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.title.setText(mValues.get(position).getTitle());
        holder.description.setText(mValues.get(position).getOverview());
        Glide.with(context)
                .load(BASE_URL_IMG +mValues.get(position).getPostPath())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgPost);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final TextView description;
        public final ImageView imgPost;
        public Filme mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.id_title_movie);
            description = view.findViewById(R.id.id_description_movie);
            imgPost = view.findViewById(R.id.id_img_movie);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + description.getText() + "'";
        }
    }
}
