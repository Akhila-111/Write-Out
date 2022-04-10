package com.example.write_out;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;


public class OthersArticles extends Fragment {

    RecyclerView recview;
    DatabaseReference databaseReference;
    OtherArtsAdapter myAdapter;
    ArrayList<UserHelperClass> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_others_articles, container, false);

       recview= view.findViewById(R.id.recview2);
       recview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
       databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        list = new ArrayList<>();
        myAdapter = new OtherArtsAdapter(getActivity().getApplicationContext(),list);
        recview.setAdapter(myAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserHelperClass user = dataSnapshot.getValue(UserHelperClass.class);
                    list.add(user);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       return view;
    }
}