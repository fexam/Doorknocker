package com.example.doorknocker;

import android.content.ContentValues;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrew on 3/27/14.
 */
public class websiteCom {

    private static final String baseURL = "http://doorknocker.myrpi.org/scripts/android/";

    /* the websiteCom class hides the baseURL that information is collected through and
       provides methods that interact directly with the website */
    public websiteCom() {
    }

    /* Purpose: returns the response from the give URL as a string object */
    private static String getString(String url){
        InputStream inStream = null;
        String result = "";

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
                sb.append(line + "\n");
            }
            inStream.close();
            result = sb.toString();
        } catch(Exception e) {
            Log.e("exception", "failed to translate response to string", e);
            return null;
        }
        return result;
    }

    /* Purpose: creates the url to access the data at the requred hall, floor, and wing,
           gets the text from said URL, and converts it into the JSONArray the string represents */
    public static JSONArray getJsonArr(String hall, int floor, String wing){
        hall = hall.replaceAll(" ", "%20");
        String url = baseURL + "rooms.php?dorm=" + hall + "&floor=" + floor + "&wing=" + wing;
        JSONArray jsonArray = null;
        String temp = getString(url);
        try {
            jsonArray = new JSONArray(temp);
        } catch(JSONException e) {
            Log.e("exception", "JSON conversion failure\n url = " + url + "\n response = " + temp, e);
            return null;
        }
        return jsonArray;
    }

    /* Purpose: contacts the server with the given username and password. Converts the servers
           string response to a boolean. */
    public static boolean authorizeUser(String username, String password){
        String url = baseURL + "login.php?username=" + username + "&password=" + password;
        if(getString(url)=="true"){
            return true;
        }
        else {
            return false;
        }
    }


    /*
    Debugging statements from while code was being developed.
     */
    public void printJsonArray(JSONArray array){
        Log.d("debugging", "PRINTING JSONARRAY");
        for (int i = 0; i < array.length(); i++) {
            try {
                Log.d("debugging", i + ": " + array.getJSONObject(i).toString());
            }
            catch(Exception e){
                Log.e("exception", "JSON printing error", e);
            }
        }
    }


}
