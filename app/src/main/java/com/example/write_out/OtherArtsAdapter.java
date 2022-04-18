package com.example.write_out;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OtherArtsAdapter extends RecyclerView.Adapter<OtherArtsAdapter.myViewHolder>{

    Context context;
    ArrayList<UserHelperClass> list;
    private static OtherArtsAdapter.RecyclerViewClickListener Listener;
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference,fvrtref,fvrt_listRef;
    Boolean fvrtChecker = false;


    public OtherArtsAdapter(Context context, ArrayList<UserHelperClass> list,OtherArtsAdapter.RecyclerViewClickListener Listener) {
        this.context = context;
        this.list = list;
        this.Listener = Listener;
    }

    public void filterList(ArrayList<UserHelperClass> filterllist){
        list = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.others_articles_items,parent,false);
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

        String s = helperClass.userName + "_" + helperClass.articleTitle;

        fvrtref = database.getReference("favourites");
        fvrt_listRef = database.getReference("favouriteList");

        holder.favouriteChecker(s);
        holder.favouriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fvrtChecker = true;

                fvrtref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(fvrtChecker.equals(true)){
                            if(snapshot.hasChild(s)){
                                fvrtref.child(s).removeValue();
                                delete(s);
                                fvrtChecker = false;
                            } else {
                                fvrtref.child(s).setValue(true);
                                helperClass.setCategory(helperClass.category);
                                helperClass.setArticleBody(helperClass.articleBody);
                                helperClass.setArticleTitle(helperClass.articleTitle);
                                helperClass.setUserName(helperClass.userName);
                                helperClass.setDateOfPublication(helperClass.dateOfPublication);

                                String id = fvrt_listRef.push().getKey();
                                fvrt_listRef.child(s).setValue(list.get(position));
                                fvrtChecker = false;
                            }
                        }

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }

    public void delete (String s){
        Query query = fvrt_listRef.child(s);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    dataSnapshot1.getRef().removeValue();

                    Toast.makeText(context.getApplicationContext(),"Removed from Favourites",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView userName,ArticleTitle,Category,DateOfPublication,ArticleBody;
        ImageView favouriteBtn;
        private DatabaseReference fvrtref;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username);
            ArticleTitle = itemView.findViewById(R.id.title);
            Category = itemView.findViewById(R.id.category);
            DateOfPublication = itemView.findViewById(R.id.date);
            favouriteBtn = itemView.findViewById(R.id.fav);
            // ArticleBody = itemView.findViewById(R.id.articleBody);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Listener.onClick(view,getAdapterPosition());
        }

        public void favouriteChecker(String s) {

            favouriteBtn = itemView.findViewById(R.id.fav);
            fvrtref = database.getReference("favourites");

            fvrtref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.hasChild(s)){
                        favouriteBtn.setImageResource(R.drawable.ic_baseline_red_favorite_24);
                    } else {
                        favouriteBtn.setImageResource(R.drawable.ic_outline_favorite_border_24);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
    public interface  RecyclerViewClickListener{
        void onClick(View v,int position);
    }



}