package com.example.user.exapi.Remote;

public class APIDeclaration {
    public APIDeclaration(){

    }

    public static final String API_URL = "https://xsintern-api.learnbalance.com/api/";

    public static ArticlesService getArticlesService(){
        return  RetrofitConfig.getClient(API_URL).create(ArticlesService.class);
    }
}
