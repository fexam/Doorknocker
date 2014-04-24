package com.doorknocker.doorknocker.app;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by nutjung on 4/24/2014.
 */
public class webConnection extends AsyncTask<String,String,String> {
    //get an information from the web server
    @Override
    protected String doInBackground(String... strings) {
        InputStream inStream = null;
        String result = "";
        String url = strings[0];
        // HTTP
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            inStream = entity.getContent();
        } catch(Exception e) {
            Log.e("exception", "failed to communicate with webpage: " + url, e);
            return null;
        }
        // Read response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream,"utf-8"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            inStream.close();
            result = sb.toString();
        } catch(Exception e) {
            Log.e("exception", "failed to translate response to string", e);
            return null;
        }
        return result;
    }
}
