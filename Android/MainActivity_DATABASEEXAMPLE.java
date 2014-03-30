package com.example.doorknocker;

import android.content.ContentValues;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("debugging", "START");
        databaseHelper db = new databaseHelper(this);

        /**
         * CRUD Operations
         * */
        // add Books
        db.deleteAllRooms();
        db.addRoom(new Room("cary hall", 101));
        db.addRoom(new Room("cary hall", 102));
        db.addRoom(new Room("cary hall", 103));
        db.addRoom(new Room("cary hall", 104));
        db.addRoom(new Room("cary hall", 105));
      //  db.vistWing("cary hall", 1, "A");
        db.updateRoom(new Room("cary hall", 101));

        // problem: dorm name needs to be in values. it currently isn't
        websiteCom com = new websiteCom();

       // com.printContentValueList(CV);
        db.syncDB("cary hall",1,"a");

        //com.printJsonArray(jsonArr);

        //db.addBook(new Book("Android Programming: The Big Nerd Ranch Guide", "Bill Phillips and Brian Hardy"));
        //db.addBook(new Book("Learn Android App Development", "Wallace Jackson"));

        // get all books
        //List<Room> list = db.getAllRooms();
        Log.d("debugging", "ENDING");


        // delete one book
      //  db.deleteBook(list.get(0));

        // get all books
      //  db.getAllBooks();

    }

}
