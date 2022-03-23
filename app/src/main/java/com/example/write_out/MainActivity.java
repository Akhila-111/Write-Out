package com.example.write_out;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button LOGOUT ;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        LOGOUT = findViewById(R.id.button8);
        mAuth = FirebaseAuth.getInstance();


        tabLayout.setupWithViewPager(viewPager);
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new MyArticles(),"MyArticles" );
        vpAdapter.addFragment(new OthersArticles(),"OthersArticles");
        vpAdapter.addFragment(new Favourites(),"Favourites");
        vpAdapter.addFragment(new ReadingList(),"ReadingList");


        LOGOUT.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user== null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }
}