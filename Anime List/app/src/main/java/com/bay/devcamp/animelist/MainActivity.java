package com.bay.devcamp.animelist;

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


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvCatagory)
    RecyclerView rvCatagory;
    AnimeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new AnimeAdapter(this);
        String url = "https://animeyou.net/api/home.php";
        DemoAsync demoAsync = new DemoAsync();
        demoAsync.execute(url);
    }


    private class DemoAsync extends AsyncTask<String,Void,ArrayList<Anime>> {

        @Override
        protected ArrayList<Anime> doInBackground(String... strings) {
            String url = strings[0];
            final ArrayList<Anime> animes = new ArrayList<>();
            SyncHttpClient client = new SyncHttpClient();

            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            client.get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                    try {
                        String hasil = new String(responseBody);
                        JSONObject jsonData = new JSONObject(hasil);
                        JSONArray jsonArray = jsonData.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject animeObj = jsonArray.getJSONObject(i);
                            Anime anime = new Anime(animeObj);
                            animes.add(anime);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("Tag", "onFailure: " + statusCode);
                }
            });
            return animes;
        }

        @Override
        protected void onPostExecute(ArrayList<Anime> anime) {
            super.onPostExecute(anime);
            rvCatagory.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter.setListAnime(anime);
            rvCatagory.setAdapter(adapter);
        }
    }
}

