package com.example.write_out;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MyArticles extends Fragment {

    private AlertDialog.Builder mySpinner;
    RecyclerView recyclerView;
    DatabaseReference database;
    myAdapterClass Myadapter;
    ArrayList<UserHelperClass> dataholder;
    private myAdapterClass.RecyclerViewClickListener listener;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_articles, container, false);
        recyclerView = view.findViewById(R.id.recview);

        setOnClickListener();
        dataholder = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

  

        dataholder = new ArrayList<>();
        Myadapter = new myAdapterClass(this,dataholder,listener);



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                   UserHelperClass data = dataSnapshot.getValue(UserHelperClass.class);
                   Log.d("Tag",data.userName);
                   dataholder.add(data);

                }
                Myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setAdapter(Myadapter);
        return view;

    }

    private void setOnClickListener() {
        listener = new myAdapterClass.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(v.getContext(),ArticleActivity.class);
                intent.putExtra("ArticleBody",dataholder.get(position).getArticleBody());
                startActivity(intent);
            }
        };
    }


}