package com.example.write_out;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Favourites#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Favourites extends Fragment {

    DatabaseReference databaseRef;
    RecyclerView recyclerView;
    ShimmerFrameLayout shimmerFrameLayout;
    FavouritesAdapter favAdapter;
    ArrayList<UserHelperClass> dataholder;
    FavouritesAdapter.RecyclerViewClickListener listen;





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Favourites() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Favourites.
     */
    // TODO: Rename and change types and number of parameters
    public static Favourites newInstance(String param1, String param2) {
        Favourites fragment = new Favourites();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setOnClickListener();
       // setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerView = view.findViewById(R.id.recview);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();

        dataholder = new ArrayList<>();
        databaseRef = FirebaseDatabase.getInstance().getReference("Articles");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        favAdapter = new FavouritesAdapter(getActivity().getApplicationContext(),dataholder,listen);

        databaseRef.addValueEventListener(new ValueEventListener() {
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
                favAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setAdapter(favAdapter);
        return view;

    }

    private void setOnClickListener() {
        listen = new FavouritesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(v.getContext(),ArticleActivity.class);
                intent.putExtra("ArticleBody",dataholder.get(position).getArticleBody());
                startActivity(intent);
            }
        };
    }

}