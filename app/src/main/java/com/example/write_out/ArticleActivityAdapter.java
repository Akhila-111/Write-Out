package com.example.write_out;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArticleActivityAdapter extends RecyclerView.Adapter<ArticleActivityAdapter.myViewHolder>
{

    Context context;
    ArrayList<UserHelperClass> dataholder;

    public ArticleActivityAdapter(Context context, ArrayList<UserHelperClass> dataholder) {
        this.context = context;
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public ArticleActivityAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_layout,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleActivityAdapter.myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        UserHelperClass helperClass = dataholder.get(position);
        holder.userName.setText(dataholder.get(position).getUserName());
        holder.ArticleTitle.setText(dataholder.get(position).getArticleTitle());
        holder.ArticleBody.setText(dataholder.get(position).getArticleBody());
        holder.Category.setText(dataholder.get(position).getCategory());
        holder.DateOfPublication.setText(dataholder.get(position).getdateOfPublication());

        String s = helperClass.userName + "_" + helperClass.articleTitle;
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        Button UpdateBtn,EditBtn;
        TextView userName,ArticleTitle,Category,DateOfPublication,ArticleBody;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
           // UpdateBtn = itemView.findViewById(R.id.button9);
           // EditBtn = itemView.findViewById(R.id.button10);
            userName = itemView.findViewById(R.id.user);
            ArticleTitle = itemView.findViewById(R.id.art_tit);
            ArticleBody = itemView.findViewById(R.id.addText1);
            Category = itemView.findViewById(R.id.cat);
            DateOfPublication = itemView.findViewById(R.id.pubdate);
        }
    }
}
