package com.example.write_out;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.myViewHolder>{
    Context context;
    ArrayList<UserHelperClass> dataholder;
    private FavouritesAdapter.RecyclerViewClickListener listen;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference,fvrtref,fvrt_listRef;
    Boolean fvrtChecker = false;



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

                                //String id = fvrt_listRef.push().getKey();
                                fvrt_listRef.child(s).setValue(dataholder);
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
        return dataholder.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView userName,ArticleTitle,Category,DateOfPublication,ArticleBody;
        ImageView favouriteBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username);
            ArticleTitle = itemView.findViewById(R.id.title);
            Category = itemView.findViewById(R.id.category);
            DateOfPublication = itemView.findViewById(R.id.date);
            favouriteBtn = itemView.findViewById(R.id.fav);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listen.onClick(view,getAdapterPosition());
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