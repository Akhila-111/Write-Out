package com.example.write_out;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button LOGOUT ;
     FirebaseAuth mAuth;
     private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        LOGOUT = findViewById(R.id.button8);
        mAuth = FirebaseAuth.getInstance();
        floatingActionButton = findViewById(R.id.fab);

        tabLayout.setupWithViewPager(viewPager);
        Adapter adapter = new Adapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        Adapter.addFragment(new MyArticles(),"My Articles");
        Adapter.addFragment(new OthersArticles(),"Others Articles");
        Adapter.addFragment(new Favourites(),"Favourites");
        viewPager.setAdapter(adapter);

        floatingActionButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,ArticleDetail.class));
        });

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