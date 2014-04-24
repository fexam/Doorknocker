package com.doorknocker.doorknocker.app;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by nutjung on 4/21/2014.
 */
public class JSonTransmitter extends AsyncTask<JSONArray, JSONArray,HttpResponse> {
    //upload the data to the server
    @Override
    protected HttpResponse doInBackground(JSONArray... jsonArrays) {
        String url = "";
        JSONArray Jarray = jsonArrays[0];
        JSONArray Url = jsonArrays[1];
        HttpClient client = new DefaultHttpClient();
        HttpResponse jsonResponse = null;
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 100000);
        HttpPost post = null;
        try {
            url = Url.getString(0);
            url = url.replace("\\","");
            post = new HttpPost(url);
            StringEntity se = new StringEntity("json="+Jarray.toString());
            post.addHeader("content-type", "application/x-www-form-urlencoded");
            post.setEntity(se);

            HttpResponse response;
            response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String htmlResponse = EntityUtils.toString(entity);
            if(htmlResponse.equalsIgnoreCase("Success")){
                Log.d("debugging","Success");
            }else{
                Log.d("debugging","Failed");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }
}
