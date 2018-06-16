package com.example.user.exapi.Remote;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.exapi.ArticlesActivity;
import com.example.user.exapi.Model.Articles;
import com.example.user.exapi.R;

import java.util.List;

public class ArticlesAdapter extends ArrayAdapter {
    private Context context;
    private List<Articles> articles;

    public ArticlesAdapter(@NonNull Context context, int resource,@NonNull List<Articles> object) {
        super(context, resource,object);
        this.context = context;
        this.articles = object;
    }

    @Override

    public View getView (final int pos, View converView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item,parent,false);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtTitle);
        txtTitle.setText(articles.get(pos).getTitle());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,ArticlesActivity.class);
                i.putExtra("art_id",String.valueOf(articles.get(pos).getId()));
                i.putExtra("art_title",articles.get(pos).getTitle());
                i.putExtra("art_description",articles.get(pos).getDescription());
                context.startActivity(i);
            }
        });

        return rowView;
    }
}
