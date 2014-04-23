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
    @Override
    protected HttpResponse doInBackground(JSONArray... jsonArrays) {
        String url = "";
        JSONArray Jarray = jsonArrays[0];
        JSONArray Url = jsonArrays[1];
        HttpClient client = new DefaultHttpClient();
        HttpResponse jsonResponse = null;
        HttpPost post = null;
        try {
            url = Url.getString(0);
            url = url.replace("\\","");
            post = new HttpPost(url);
            StringEntity se = new StringEntity("json="+Jarray.toString());
            post.setEntity(se);

            HttpResponse response;
            response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String htmlResponse = EntityUtils.toString(entity);
            System.out.println(htmlResponse);
            if(htmlResponse.equalsIgnoreCase("Success")){
                System.out.println("Success");
            }else{
                System.out.println("Failed");
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
