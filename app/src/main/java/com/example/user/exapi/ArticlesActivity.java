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

public class ArticlesActivity extends AppCompatActivity {

    EditText edtTitle,edtDescription;
    Button btnSave,btnCancle,btnDelete;
    ArticlesService articlesService;
    String artcleId,articleTitle,articleDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        init();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Articles articles = new Articles();
                articles.setTitle(edtTitle.getText().toString());
                articles.setDescription(edtDescription.getText().toString());

                updateArticle(Integer.parseInt(artcleId),articles);
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteArticle(Integer.parseInt(artcleId));
                finish();
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void deleteArticle(final  int id){
        Call<Articles> call = articlesService.deleteArticle(id);
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ArticlesActivity.this,"Article Delete Success!!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
    public void updateArticle(int id,Articles articles){
        Call<Articles> call = articlesService.updateArticle(id, articles);
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ArticlesActivity.this,"Article Update Success!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }


    public void init(){
        edtTitle = (EditText) findViewById(R.id.edtTitle_art);
        edtDescription = (EditText) findViewById(R.id.edtDescription_art);

        btnSave = (Button) findViewById(R.id.btnSave_art);
        btnCancle = (Button) findViewById(R.id.btnCancel_art);
        btnDelete = (Button) findViewById(R.id.btnDelete_art);

        articlesService = APIDeclaration.getArticlesService();

        Bundle extras = getIntent().getExtras();
        artcleId = extras.getString("art_id");
        articleTitle = extras.getString("art_tile");
        articleDescription = extras.getString("art_description");

        edtTitle.setText(articleTitle);
        edtDescription.setText(articleDescription);

    }


}
