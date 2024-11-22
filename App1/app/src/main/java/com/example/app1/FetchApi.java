package com.example.app1;

import static com.example.app1.MainActivity.img;
import static com.example.app1.MainActivity.process;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchApi extends AsyncTask<Void,Void,String> {
    String apiUrl;
    Boolean check;
    FetchApi(String api,Boolean check){
        this.apiUrl = api;
        this.check = check;
        if (check) {
            process.setVisibility(View.VISIBLE);
        } else {
            process.setVisibility(View.GONE);
        }
    }
    @Override
    protected String doInBackground(Void... voids){
    String res ="";
    try{
        URL url = new URL(apiUrl);
        HttpURLConnection hc = (HttpURLConnection)  url.openConnection();
        hc.setRequestMethod("GET");
        hc.setConnectTimeout(15000);
        hc.setReadTimeout(15000);
        BufferedReader in = new BufferedReader(new InputStreamReader(hc.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        hc.disconnect();
        res = response.toString();
        check=true;
      } catch (Exception e) {
        e.printStackTrace();
    }
    return  res;
    }

    protected  void onPostExecute(String res){
        super.onPostExecute(res);

        Gson gson = new Gson();
        getClass file = gson.fromJson(res, getClass.class);
        Log.d("Api",file.getName());
        Picasso.get()
                .load(file.getName())
                .into(img);
        new Handler().postDelayed(new  Runnable() {
            @Override
            public void run() {
                process.setVisibility(View.GONE);
            }
        },1000);
    }
}
