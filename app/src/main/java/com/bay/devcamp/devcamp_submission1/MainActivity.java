package com.bay.devcamp.devcamp_submission1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvCatagory)
    RecyclerView rvCatagory;
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        adapter = new MovieAdapter(this);
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=561bb4afbcb0702110c3341bb3fd5250&language=en-US";
        DemoAsync demoAsync = new DemoAsync();
        demoAsync.execute(url);
    }

    private class DemoAsync extends AsyncTask<String, Void, ArrayList<Movie>> {
        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {
            String url = strings[0];
            final ArrayList<Movie> movies = new ArrayList<>();
            SyncHttpClient client = new SyncHttpClient();

            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            client.get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String hasil = new String (responseBody);
                        JSONObject jsonData = new JSONObject(hasil);
                        JSONArray jsonArray = jsonData.getJSONArray("results");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject movieObj = jsonArray.getJSONObject(i);
                            Movie movie  = new Movie(movieObj);
                            movies.add(movie);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("Tag", "onFailure:" + statusCode);

                }
            });
            return movies;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movie) {
            super.onPostExecute(movie);
            rvCatagory.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter.setListMovie(movie);
            rvCatagory.setAdapter(adapter);
        }
    }
}
