package com.example.write_out;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ArticleDetail extends AppCompatActivity {

    private TextView UserName ;
    private Button button;
    private EditText articleTitle;
    private Spinner category;
    private EditText dataOfPublication;

    DatabaseReference reference;

    SharedPreferences sharedPreferences;
    private static final String Shared_pref_name = "mypref";
    private static final String Key_Name = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);


        Spinner mySpinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button6);
        articleTitle = findViewById(R.id.editTextTextPersonName);
        UserName = findViewById(R.id.textView11);
        dataOfPublication = findViewById(R.id.editTextTextPersonName3);
        category = findViewById(R.id.spinner);

        sharedPreferences = getSharedPreferences(Shared_pref_name,MODE_PRIVATE);
        String name = sharedPreferences.getString(Key_Name,null);
        UserName.setText("User Name : " + name);

        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ArticleDetail.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        String title = articleTitle.getText().toString();
        if(TextUtils.isEmpty(title)){
            articleTitle.setError("Title cannot be empty!");
            articleTitle.requestFocus();
        }


        button.setOnClickListener(view -> {

            String userName = UserName.getText().toString();
            String ArticleTitle = articleTitle.getText().toString();
            String Category = category.getSelectedItem().toString();
            String DateOfPublication = dataOfPublication.getText().toString();

            UserHelperClass helperClass = new UserHelperClass(userName,ArticleTitle,Category,DateOfPublication);
            reference.setValue(helperClass);


            Toast.makeText(ArticleDetail.this,"Data inserted!",Toast.LENGTH_SHORT).show();

            startActivity(new Intent(ArticleDetail.this, ArticleWriting.class));
        });

    }
}