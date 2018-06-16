package com.example.user.exapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Articles {


        @SerializedName("art_id")
        @Expose
        private  int id;

        @SerializedName("art_title")
        @Expose
        private String title;

        @SerializedName("art_description")
        @Expose
        private String description;

        public Articles(){

        }

        public Articles(int id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

