package com.example.apprecettes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import android.view.View;



import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private Context context;
        private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recipeTitle.setText(dataList.get(position).getDataTitle());
        holder.recipeDesc.setText(dataList.get(position).getDataDescription());

        holder.recipeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DescriptionRecipe.class);
                intent.putExtra("Image",dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description",dataList.get(holder.getAdapterPosition()).getDataDescription());
                intent.putExtra("Title",dataList.get(holder.getAdapterPosition()).getDataTitle());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}
    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView recImage;
        TextView recipeTitle, recipeDesc;

        CardView recipeCard;

        public MyViewHolder (@NonNull View itemView) {
            super(itemView);

            recImage = itemView.findViewById(R.id.recImage);
            recipeTitle= itemView.findViewById(R.id.recTitle);
            recipeDesc = itemView.findViewById(R.id.recDescription);
            recipeCard = itemView.findViewById(R.id.recCard);
    }
}


