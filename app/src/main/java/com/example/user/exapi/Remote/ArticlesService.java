package com.example.user.exapi.Remote;

import com.example.user.exapi.Model.Articles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ArticlesService {
    @GET("articles/")
    Call<List<Articles>> getArticle();

    @POST("articles/")
    Call<Articles> addArticle(@Body Articles articles );

    @PUT("articles/{id}")
    Call<Articles> updateArticle(@Path("id") int id,@Body Articles articles);

    @DELETE("articles/{id}")
    Call<Articles> deleteArticle(@Path("id") int id);

}
