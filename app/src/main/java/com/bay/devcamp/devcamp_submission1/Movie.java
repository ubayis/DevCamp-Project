package com.bay.devcamp.devcamp_submission1;

import org.json.JSONObject;

public class Movie {

    private String id = "id";
    private String judul = "judul";
    private String url = "url";
    private String srcMovie = "srcMovie";
    private String voteAvg = "voteAvg";
    private String img = "img";
    private String date = "date";
    private String popularity = "popularity";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSrcMovie() {
        return srcMovie;
    }

    public void setSrcMovie(String srcMovie) {
        this.srcMovie = srcMovie;
    }

    public String getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(String voteAvg) {
        this.voteAvg = voteAvg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getHalaman() {
        return halaman;
    }

    public void setHalaman(String halaman) {
        this.halaman = halaman;
    }

    private String halaman = "halaman";

    Movie(JSONObject obj) {
        try {
            String id = obj.getString("id");
            String judul = obj.getString("title");
//            String url = obj.getString("url"); url gak ada di Json nya
//            String src_movie = obj.getString("src_movie"); ini juga gak ada
            String vote_avg = obj.getString("vote_average");
            String gambar = obj.getString("poster_path");
            String tanggal = obj.getString("release_date");// ini "release_date" bukan tanggal
            String popularity = obj.getString("popularity");
            String halaman = obj.getString("overview");

            this.id = id;
            this.judul = judul;
//            this.url = url;
//            this.srcMovie = src_movie;
            this.voteAvg = vote_avg;
            this.img = gambar;
            this.date = tanggal;
            this.popularity = popularity;
            this.halaman = halaman;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

