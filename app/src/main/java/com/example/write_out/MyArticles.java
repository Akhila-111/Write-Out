package com.example.write_out;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
    ArrayList<dataModelClass> dataholder;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_articles, container, false);
        recyclerView = view.findViewById(R.id.recview);
    /*    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));*/
        dataholder = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

     /*   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);   */

      /*  FirebaseRecyclerOptions<dataModelClass> options =
                new FirebaseRecyclerOptions.Builder<dataModelClass>()
                         .setQuerry(FirebaseDatabase.getInstance().getReference().child("Users"),dataModelClass.class); */

        dataholder = new ArrayList<>();
        Myadapter = new myAdapterClass(this,dataholder);
        recyclerView.setAdapter(Myadapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                   dataModelClass data = dataSnapshot.getValue(dataModelClass.class);
                   dataholder.add(data);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

      /*  dataModelClass ob1 = new dataModelClass("Akhila","CEO","Business","date");
        dataholder.add(ob1);

        dataModelClass ob2 = new dataModelClass("Akhila","CEO","Business","date");
        dataholder.add(ob1);

        dataModelClass ob3 = new dataModelClass("Akhila","CEO","Business","date");
        dataholder.add(ob1);

        dataModelClass ob4 = new dataModelClass("Akhila","CEO","Business","date");
        dataholder.add(ob1);

        dataModelClass ob5 = new dataModelClass("Akhila","CEO","Business","date");
        dataholder.add(ob1);

        recyclerView.setAdapter(new myAdapterClass(dataholder)); */
        return view;

    }
}