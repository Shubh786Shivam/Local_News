package com.example.loginactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ListItemViewHolder>{
    private ArrayList<homeItem> homeItemsList;
    private Context context;
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public static class ListItemViewHolder extends RecyclerView.ViewHolder{
        public ImageView thumbnailHomePage;
        public TextView titleHomePage;
        public TextView dateAddedHomePage;

        public ListItemViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            thumbnailHomePage = itemView.findViewById(R.id.thumbnailHomePage);
            titleHomePage = itemView.findViewById(R.id.titleHomePage);
            dateAddedHomePage = itemView.findViewById(R.id.dateAddedHomePage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     if(listener != null){
                         int position = getAdapterPosition();
                         if(position != RecyclerView.NO_POSITION){
                             listener.onItemClick(position);
                         }
                     }
                }
            });
        }
    }

    public HomePageAdapter(Context context, ArrayList<homeItem> homeItemArrayList) {
        this.context = context;
        homeItemsList = homeItemArrayList;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_row, parent, false);
        ListItemViewHolder lvh = new ListItemViewHolder(v, mListener);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
          homeItem currentItem = homeItemsList.get(position);
        Picasso.with(context)
                .load(currentItem.getImageUrl())
                .fit().into(holder.thumbnailHomePage);
          holder.dateAddedHomePage.setText(currentItem.getDate());
          holder.titleHomePage.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return homeItemsList.size();
    }
}
