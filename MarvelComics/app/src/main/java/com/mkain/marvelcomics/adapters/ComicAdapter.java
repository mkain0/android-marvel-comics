package com.mkain.marvelcomics.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mkain.marvelcomics.R;
import com.mkain.marvelcomics.domain.Comic;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder> {

    private List<Comic> comics;
    private Context context;

    public static class ComicViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView image;
        public TextView price;

        public ComicViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txt_comic_title);
            image = (ImageView) view.findViewById(R.id.img_comic);
            price = (TextView) view.findViewById(R.id.txt_comic_price);
        }

    }

    public ComicAdapter(List<Comic> comics, Context context) {
        this.comics = comics;
        this.context = context;
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comics_row, parent, false);
        return new ComicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ComicViewHolder holder, int position) {
        Comic comic = comics.get(position);
        holder.title.setText(comic.getTitle());
        Picasso.with(context).load(comic.getImage()).resize(400, 600).into(holder.image);
        holder.price.setText(comic.getPrice());
    }

    @Override
    public int getItemCount() {
        return comics == null ? 0 : comics.size();
    }

}
