package com.example.write_out;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ArticleDetail extends AppCompatActivity {

    private Button button;
    private EditText articleTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);


        Spinner mySpinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button6);
        articleTitle = findViewById(R.id.editTextTextPersonName);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ArticleDetail.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        String title = articleTitle.getText().toString();
        if(TextUtils.isEmpty(title)){
            articleTitle.setError("Title cannot be empty!");
            articleTitle.requestFocus();
        }

        if(articleTitle != null) {
            button.setOnClickListener(view -> {
                startActivity(new Intent(ArticleDetail.this, ArticleWriting.class));
            });
        }
    }
}