package com.example.user.exapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.exapi.Model.Articles;
import com.example.user.exapi.Remote.APIDeclaration;
import com.example.user.exapi.Remote.ArticlesService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    EditText edtTitle,edtDescription;
    Button btnSave,btnCancel;
    ArticlesService articlesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Articles articles = new Articles();
                articles.setTitle(edtTitle.getText().toString());
                articles.setDescription(edtDescription.getText().toString());
                insertArticles(articles);
                finish();

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void init(){
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtDescription = (EditText) findViewById(R.id.edtDescription);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        articlesService = APIDeclaration.getArticlesService();
    }
    private void insertArticles(Articles articles){
        Call<Articles> call = articlesService.addArticle(articles);
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                Toast.makeText(InsertActivity.this, "Insert Article Success!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

}
