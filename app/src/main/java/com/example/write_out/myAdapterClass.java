package com.example.write_out;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class myAdapterClass extends RecyclerView.Adapter<myAdapterClass.myviewholder>
{
    MyArticles context;
    ArrayList<UserHelperClass> dataholder;
    private RecyclerViewClickListener listener;

    public myAdapterClass(MyArticles context,ArrayList<UserHelperClass> dataholder,RecyclerViewClickListener listener)
    {
        this.context = context;
        this.dataholder = dataholder;
        this.listener = listener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_articles_items,parent,false);
       return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        UserHelperClass helperClass = dataholder.get(position);
        holder.userName.setText(dataholder.get(position).getUserName());
        holder.ArticleTitle.setText(dataholder.get(position).getArticleTitle());
        holder.Category.setText(dataholder.get(position).getCategory());
        holder.DateOfPublication.setText(dataholder.get(position).getdateOfPublication());
        holder.ArticleBody.setText(dataholder.get(position).getArticleBody());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView userName,ArticleTitle,Category,DateOfPublication,ArticleBody;



        public myviewholder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username);
            ArticleTitle = itemView.findViewById(R.id.title);
            Category = itemView.findViewById(R.id.category);
            DateOfPublication = itemView.findViewById(R.id.date);
            ArticleBody = itemView.findViewById(R.id.articleBody);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
    public interface  RecyclerViewClickListener{
        void onClick(View v,int position);
    }
}
