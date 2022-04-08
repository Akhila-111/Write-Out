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
import java.util.Locale;
import java.util.prefs.Preferences;

public class ArticleWriting extends AppCompatActivity {

    public TextView UserName;
    public TextView Title;
    public TextView category;
    public TextView DateOfPublication;
    public EditText add_text;
    public Button button ;

    DatabaseReference reference ;

    SharedPreferences sharedPreferences ;
    private static final String Shared_pref_name = "mypref";
    private static final String Key_Name = "name";
    private static final String Key_articleTitle = "title";
    private static final String Key_category = "category";
    private static final String Key_dataOfPublication  = "date";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_writing);

        UserName = findViewById(R.id.edittext5);
        add_text = findViewById(R.id.addText);
        button = findViewById(R.id.button7);
        Title = findViewById(R.id.textView10);
        category = findViewById(R.id.textView11);
        DateOfPublication = findViewById(R.id.textView12);

        sharedPreferences = getSharedPreferences(Shared_pref_name,MODE_PRIVATE);
        String name = sharedPreferences.getString(Key_Name,null);
        String title = sharedPreferences.getString(Key_articleTitle,null);
        String categoryShPref  = sharedPreferences.getString(Key_category,null);
        String date = sharedPreferences.getString(Key_dataOfPublication,null);


        UserName.setText(name);
        Title.setText(title);
        category.setText(categoryShPref);
        DateOfPublication.setText(date);

        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        button.setOnClickListener(view -> {
            String Articlebody = add_text.getText().toString();
            String userName = UserName.getText().toString();
            String Category = category.getText().toString();
            String ArtTitle = Title.getText().toString();
            String dateOfPub = DateOfPublication.getText().toString();



        //    String category = Category.getText().toString();
           // String Arttitle = Title.getText().toString();
         //   String dataOfPublication = DateOfPublication.getText().toString();
         //  reference.setValue(ArticleBody);

            UserHelperClass helperClass = new UserHelperClass(userName,ArtTitle,Category,dateOfPub,Articlebody);
            reference.child(helperClass.userName).setValue(helperClass);

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
