package com.bay.devcamp.devcamp_submission1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.judul.setText(getListMovie().get(i).getJudul());
        viewHolder.popularity.setText(getListMovie().get(i).getPopularity());
        viewHolder.voteAvg.setText(getListMovie().get(i).getVoteAvg());
//        kalo request gambar harus tambah link nya lagi coba cek di alamat di bawah
//        https://developers.themoviedb.org/3/getting-started/images

        String url = "https://image.tmdb.org/t/p/w500";
        Glide.with(context)
                .load(url+getListMovie().get(i).getImg())// teknik menambahkan string dengan data yang ada url + data JSON jdnya url penuh
                .into(viewHolder.poster);// into bukan load lagi,

    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvJudul)
        TextView judul;
        @BindView(R.id.imgMovie)
        ImageView poster;
        @BindView(R.id.tvPopularity)
        TextView popularity;
        @BindView(R.id.tvRate)
        TextView voteAvg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    MovieAdapter(Context context) {
        this.context = context;
    }

    ArrayList<Movie> listMovie;
    Context context;

    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }
}
