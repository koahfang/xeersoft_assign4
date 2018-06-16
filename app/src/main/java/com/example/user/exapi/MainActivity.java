package com.example.user.exapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.user.exapi.Model.Articles;
import com.example.user.exapi.Remote.APIDeclaration;
import com.example.user.exapi.Remote.ArticlesAdapter;
import com.example.user.exapi.Remote.ArticlesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    ArticlesService articlesService;
    List<Articles> list = new ArrayList<Articles>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(i);

            }
        });
        listView = (ListView) findViewById(R.id.listview);
        articlesService = APIDeclaration.getArticlesService();

    }
    @Override
    protected void onResume(){
        super.onResume();
        getListArticles();
    }
    public void getListArticles(){
        Call<List<Articles>> call = articlesService.getArticle();
        call.enqueue(new Callback<List<Articles>>() {
            @Override
            public void onResponse(Call<List<Articles>> call, Response<List<Articles>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new ArticlesAdapter(MainActivity.this, R.layout.list_item,list));
                }
            }
            @Override
            public void onFailure(Call<List<Articles>> call, Throwable t) {
                Log.e("Error ...",t.getMessage());
            }
        });

    }


            @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
