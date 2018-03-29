package com.test.user.testzimadapplication.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.user.testzimadapplication.MainActivity;
import com.test.user.testzimadapplication.R;
import com.test.user.testzimadapplication.fragments.ContentFragment;
import com.test.user.testzimadapplication.model.Data;

import java.util.ArrayList;

/**
 * Created by Navjacinth Mathew on 1/18/2017.
 */

public class CatsDogsAdapter extends RecyclerView.Adapter<CatsDogsAdapter.CatsViewHolder> {

    private ArrayList<Data> dataList;
    private Activity activity;
    public CatsDogsAdapter(ArrayList<Data> dataList) {
        this.dataList = dataList;
    }

    @Override
    public CatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_cat, parent, false);
        return new CatsViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.activity = (Activity) recyclerView.getContext();
    }

    @Override
    public void onBindViewHolder(CatsViewHolder holder, int position) {
        Glide.with(activity)
                .load(dataList.get(position).getUrl())
                .centerCrop()
                .into(holder.catImage);
        holder.catText.setText(dataList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CatsViewHolder extends RecyclerView.ViewHolder {

        ImageView catImage; TextView catText; LinearLayout linearLayout;

        CatsViewHolder(View itemView) {
            super(itemView);
            catImage = (ImageView) itemView.findViewById(R.id.cat_image);
            catText = (TextView) itemView.findViewById(R.id.txt_cat_image);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.cat_item);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) activity).showContentFragment(ContentFragment.TAG, dataList.get(getAdapterPosition()).getUrl(),
                            dataList.get(getAdapterPosition()).getTitle());
                }
            });
        }

    }
}