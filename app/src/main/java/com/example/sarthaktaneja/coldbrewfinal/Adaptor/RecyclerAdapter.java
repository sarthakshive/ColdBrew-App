package com.example.sarthaktaneja.coldbrewfinal.Adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarthaktaneja.coldbrewfinal.Model.Pojo1;
import com.example.sarthaktaneja.coldbrewfinal.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sarthaktaneja on 11/11/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DetailViewHolder> {
    List<Pojo1> data;
    Context context;

    public RecyclerAdapter(List<Pojo1> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemrecycle, parent, false);

        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetailViewHolder holder, int position) {

        holder.title.setText(data.get(position).getText());
        String imgUrl=data.get(position).getImage_url();
        Picasso.with(context).load(imgUrl).error(R.drawable.baseline_error_outline_black_36).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                holder.loader.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                holder.loader.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;
        ImageView loader;

        public DetailViewHolder(View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.recyclename);
            image=(ImageView) itemView.findViewById(R.id.image);
            loader=(ImageView) itemView.findViewById(R.id.image_loader);

        }
    }

}
