package com.example.write_out;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class myAdapterClass extends RecyclerView.Adapter<myAdapterClass.myviewholder>
{
    Context context;
    ArrayList<UserHelperClass> dataholder;
    ArrayList<UserHelperClass> dataholderAll;
    FirebaseUser user;
    FirebaseAuth mAuth;

    private RecyclerViewClickListener listener;


    public myAdapterClass(Context context,ArrayList<UserHelperClass> dataholder,RecyclerViewClickListener listener)
    {
        this.context = context;
        this.dataholder = dataholder;
        this.dataholderAll = new ArrayList<>(dataholder);
        this.listener = listener;
    }

    public void filterList(ArrayList<UserHelperClass> filterllist){
        dataholder = filterllist;
        notifyDataSetChanged();
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
        //holder.ArticleBody.setText(dataholder.get(position).getArticleBody());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView userName,ArticleTitle,Category,DateOfPublication,ArticleBody;
        Button delete;
        Button favourite;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username);
            ArticleTitle = itemView.findViewById(R.id.title);
            Category = itemView.findViewById(R.id.category);
            DateOfPublication = itemView.findViewById(R.id.date);
            delete = itemView.findViewById(R.id.delete);
            favourite = itemView.findViewById(R.id.favourite);
           // ArticleBody = itemView.findViewById(R.id.articleBody);

            itemView.setOnClickListener(this);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

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
