package com.example.write_out;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;
import java.util.prefs.Preferences;

public class ArticleWriting extends AppCompatActivity {

    public TextView UserName;
    public EditText add_text;
    public Button button ;
    DatabaseReference reference;

    SharedPreferences sharedPreferences;
    private static final String Shared_pref_name = "mypref";
    private static final String Key_Name = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_writing);

        UserName = findViewById(R.id.textView9);
        add_text = findViewById(R.id.addText);
        button = findViewById(R.id.button7);

        sharedPreferences = getSharedPreferences(Shared_pref_name,MODE_PRIVATE);
        String name = sharedPreferences.getString(Key_Name,null);
        UserName.setText("User Name : " + name);

        reference = FirebaseDatabase.getInstance().getReference().child(name).child("ArticleBody");

        button.setOnClickListener(view -> {
            String ArticleBody = add_text.getText().toString();
            String userName = UserName.getText().toString();

            reference.setValue(ArticleBody);

            Toast.makeText(this, "ARTICLE UPLOADED", Toast.LENGTH_SHORT).show();
        });

        add_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(view.getId()==R.id.addText)
                {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (motionEvent.getAction() & MotionEvent.ACTION_MASK)
                    {
                        case MotionEvent.ACTION_UP:view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    }
                }

                return false;
            }
        });

    }

}
