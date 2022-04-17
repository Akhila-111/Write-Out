package com.example.write_out;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.myViewHolder>{
    Context context;
    ArrayList<UserHelperClass> dataholder;
    private FavouritesAdapter.RecyclerViewClickListener listen;

    public FavouritesAdapter(Context context, ArrayList<UserHelperClass> dataholder,RecyclerViewClickListener listen) {
        this.context = context;
        this.dataholder = dataholder;
        this.listen = listen;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourites_items,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        UserHelperClass helperClass = dataholder.get(position);
        holder.userName.setText(dataholder.get(position).getUserName());
        holder.ArticleTitle.setText(dataholder.get(position).getArticleTitle());
        holder.Category.setText(dataholder.get(position).getCategory());
        holder.DateOfPublication.setText(dataholder.get(position).getdateOfPublication());
        String s = helperClass.userName + "_" + helperClass.articleTitle;
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView userName,ArticleTitle,Category,DateOfPublication,ArticleBody;
        ImageView favourite;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username);
            ArticleTitle = itemView.findViewById(R.id.title);
            Category = itemView.findViewById(R.id.category);
            DateOfPublication = itemView.findViewById(R.id.date);
            favourite = itemView.findViewById(R.id.fav);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listen.onClick(view,getAdapterPosition());
        }
    }

    public interface  RecyclerViewClickListener{

        void onClick(View v,int position);
    }
}
