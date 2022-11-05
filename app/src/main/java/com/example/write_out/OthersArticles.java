package com.example.write_out;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;


public class OthersArticles extends Fragment {

    RecyclerView recview;
    DatabaseReference databaseReference;
    OtherArtsAdapter myAdapter;
    ArrayList<UserHelperClass> list;
    FirebaseAuth auth;
    FirebaseUser user;
    ShimmerFrameLayout shimmerFrameLayout;
    private OtherArtsAdapter.RecyclerViewClickListener Listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_others_articles, container, false);
        setOnClickListener();
        setHasOptionsMenu(true);
        recview = view.findViewById(R.id.recview2);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        recview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        databaseReference = FirebaseDatabase.getInstance().getReference("Articles");
        list = new ArrayList<>();
        myAdapter = new OtherArtsAdapter(getActivity().getApplicationContext(), list, Listener);
        recview.setAdapter(myAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recview.setVisibility(View.VISIBLE);

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
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

    private void setOnClickListener() {
        Listener = new OtherArtsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(v.getContext(), ArticleActivity.class);
                intent.putExtra("ArticleBody", list.get(position).getArticleBody());
                startActivity(intent);
            }
        };
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search By Category...");
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
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void filter(String text) {
        ArrayList<UserHelperClass> filteredList = new ArrayList<>();
        for (UserHelperClass item : list) {
            if (item.getCategory().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "No data found...", Toast.LENGTH_SHORT).show();
        } else {
            myAdapter.filterList(filteredList);
        }
    }
}
