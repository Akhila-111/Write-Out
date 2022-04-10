package com.example.write_out;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OtherArtsAdapter extends RecyclerView.Adapter<OtherArtsAdapter.myViewHolder>{

    Context context;
    ArrayList<UserHelperClass> list;

    public OtherArtsAdapter(Context context, ArrayList<UserHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_articles_items,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        UserHelperClass helperClass = list.get(position);
        holder.userName.setText(list.get(position).getUserName());
        holder.ArticleTitle.setText(list.get(position).getArticleTitle());
        holder.Category.setText(list.get(position).getCategory());
        holder.DateOfPublication.setText(list.get(position).getdateOfPublication());
        //  holder.ArticleBody.setText(dataholder.get(position).getArticleBody());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView userName,ArticleTitle,Category,DateOfPublication,ArticleBody;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username);
            ArticleTitle = itemView.findViewById(R.id.title);
            Category = itemView.findViewById(R.id.category);
            DateOfPublication = itemView.findViewById(R.id.date);
            // ArticleBody = itemView.findViewById(R.id.articleBody);
        }
    }
}
