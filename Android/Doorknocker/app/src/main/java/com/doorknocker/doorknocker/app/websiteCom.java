package com.doorknocker.doorknocker.app;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

/**
 * Created by Andrew on 3/27/14.
 */
public class websiteCom {

    private static final String baseURL = "http://doorknocker.myrpi.org/scripts/android/";
    /* the websiteCom class hides the baseURL that information is collected through and
       provides methods that interact directly with the website */
    public websiteCom() {
    }

    /* Purpose: creates the url to access the data at the requred hall, floor, and wing,
           gets the text from said URL, and converts it into the JSONArray the string represents */
    public static JSONArray getJsonArr(String hall, int floor, String wing){
        if(wing.equalsIgnoreCase("")){
            wing = "A";
        }
        if((hall.substring(0,4)).equalsIgnoreCase("BARH")){
            hall = "BARH-"+hall.substring(4,5);
        }
        webConnection webCon = new webConnection();
        hall = hall.replaceAll(" ", "%20");
        String url = baseURL + "rooms.php?dorm=" + hall + "&floor=" + floor + "&wing=" + wing;
        JSONArray jsonArray = null;
        //webCon.execute(url);
        String temp = null;
        try {
            temp = webCon.execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            jsonArray = new JSONArray(temp);
        } catch(JSONException e) {
            Log.e("exception", "JSON conversion failure\n url = " + url + "\n response = " + temp, e);
            return null;
        }
        return jsonArray;
    }

    /* Purpose: creates the url to access the data at the request hall, floor, and wing,
        and gets the text from said URL*/
    public static String getString(String hall, int floor, String wing){
        webConnection webCon = new webConnection();
        hall = hall.replaceAll(" ", "%20");
        String url = baseURL + "rooms.php?dorm=" + hall + "&floor=" + floor + "&wing=" + wing;
        String temp = null;
        try {
            temp = webCon.execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /* Purpose: contacts the server with the given username and password. Converts the servers
           string response to a boolean. */
    public static boolean authorizeUser(String username, String password){
        webConnection webCon = new webConnection();
        String url = baseURL + "login.php?username=" + username + "&password=" + password;
        String temp = null;
        try {
            temp = webCon.execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(temp.equalsIgnoreCase("true")){
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
