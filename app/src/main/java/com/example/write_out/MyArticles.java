package com.example.write_out;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    FirebaseUser user;
    myAdapterClass Myadapter;
    ArrayList<UserHelperClass> dataholder;
    ShimmerFrameLayout shimmerFrameLayout;
    private myAdapterClass.RecyclerViewClickListener listener;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_my_articles, container, false);
        recyclerView = view.findViewById(R.id.recview);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();

        setOnClickListener();
        dataholder = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("Articles").child("Users Articles");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));



        dataholder = new ArrayList<>();
        Myadapter = new myAdapterClass(getActivity().getApplicationContext(),dataholder,listener);



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    UserHelperClass data = dataSnapshot.getValue(UserHelperClass.class);
                    //     Log.d("Tag",data.userName);
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        //  return true;
        super.onCreateOptionsMenu(menu,inflater);
    }

    private void filter(String text)
    {
        ArrayList<UserHelperClass> filteredList = new ArrayList<>();
        for(UserHelperClass item : dataholder ){
            if(item.getCategory().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(),"No data found...",Toast.LENGTH_SHORT).show();
        }
        else{
            Myadapter.filterList(filteredList);
        }
    }
}