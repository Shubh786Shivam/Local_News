package com.example.loginactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListArticleAdapter extends RecyclerView.Adapter<ListArticleAdapter.ListSummaryViewHolder>{
    private ArrayList<listArticleSummaryItem> summaryItemsList;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public static  class ListSummaryViewHolder extends RecyclerView.ViewHolder{
        public TextView summary;
        public ListSummaryViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            summary = itemView.findViewById(R.id.summary);
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

    public ListArticleAdapter(Context context, ArrayList<listArticleSummaryItem> summaryItemsList) {
        this.summaryItemsList = summaryItemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_summary_row, parent, false);
        ListSummaryViewHolder lv = new ListSummaryViewHolder(v, mListener);
        return lv;
    }

    @Override
    public void onBindViewHolder(@NonNull ListSummaryViewHolder holder, int position) {
          listArticleSummaryItem currentItem = summaryItemsList.get(position);
          holder.summary.setText(currentItem.getSummaryArticle());
    }

    @Override
    public int getItemCount() {
        return summaryItemsList.size();
    }
}
