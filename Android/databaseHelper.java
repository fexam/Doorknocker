package com.example.doorknocker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrew on 3/21/14.
 */
public class databaseHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "myDoorknocker";
  private static final String KEY_DORM = "dormName";
  private static final String KEY_ROOM = "room_number";
  private static final String KEY_FLOOR = "dormFloor";
  private static final String KEY_WING = "dormWing";
  private static final String KEY_STATUS = "state";
  private static final String KEY_DATE = "date";
  private static final String KEY_NOTES = "notes";
  private static final String KEY_MODIFIED = "modified";
  private static final String[] COLUMNS = {KEY_DORM, KEY_ROOM, KEY_FLOOR, KEY_WING, KEY_STATUS, KEY_DATE, KEY_NOTES, KEY_MODIFIED};
  // Incremement Database_Version manually any time the database's layout is modified.
  private static final int DATABASE_VERSION = 7;


  /* Methods required for an sqlite database to be implemented */
  /* Purpose: Constructor */
  databaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }

  /* Purpose: defines what the fields of the database will be and the behavior of those fields,
         such as: can the value be null, will it default to a certain value, what are the keys, ect.*/
  public void onCreate(SQLiteDatabase database) {
    database.execSQL("create table " + DATABASE_NAME +
        " ( " + KEY_DORM + " text not null," +
        KEY_ROOM + " integer not null," +
        KEY_FLOOR + " integer," +
        KEY_WING + " text," +
        KEY_STATUS + " integer not null," +
        KEY_DATE + " text, " +
        KEY_NOTES + " text, " +
        KEY_MODIFIED + " integer default 0, " +
        "primary key (" + KEY_DORM + ", " + KEY_ROOM + "));"
    );
  }

  /* Purpose: Tells the application what to do when the database version has incresed.
         In this case whenever the database structure is changed, the database will try
         one last time to push any unrecorded changes to the server before dropping all data
         regardless of what it is. A new, updated table is then created.  */
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
    Log.w(databaseHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
        + newVersion + ", which will destroy all old data");
        // check for updates required to server and upload them if they exist
    database.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
    onCreate(database);
  }

   /* CRUD operations (create "add", read "get", update, delete). Basic operations*/

  /* Purpose: uses the fields from a Room object and enters them into the database for the user. */
  public void addRoom(Room room){
    //Log.d("addRoom", room.getFull_name());
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(KEY_DORM, room.getName());
    values.put(KEY_ROOM, room.getNumber());
    values.put(KEY_STATUS, room.getStatus());
    values.put(KEY_DATE, room.getTime());
    values.put(KEY_NOTES, room.getNote());

    db.insert(DATABASE_NAME, null, values);
    db.close();
  }

    /* Purpose: uses the fields from a Room object and updates the corrosponding entry in
           the database so that the Room's fields are stored.
       Assumes: The database will only ever be updated from a room object when changes have been
           made locally and need to be sent to the main server. Old data stored locally that
           was never sent to the main server will be overwritten as newer data is more
           important. */
  public int updateRoom(Room room) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(KEY_DORM, room.getName());
    values.put(KEY_ROOM, room.getNumber());
    values.put(KEY_STATUS, room.getStatus());
    values.put(KEY_DATE, room.getTime());
    values.put(KEY_NOTES, room.getNote());
    values.put(KEY_MODIFIED, 1);

    // updates the location where the primary key components match the Room's
    int i = db.update(DATABASE_NAME,
        values, // ContextValues
        KEY_DORM + "=? AND " + KEY_ROOM + "=?", // condition
        new String[] { String.valueOf(room.getName()), Integer.toString(room.getNumber()) }); // selections args

    db.close();
    return i;
  }

  /* Purpose: returns a Room object created from the information in the database as specified
         by the given primary key. */
  public Room getRoom ( String dormName, int roomNumber){
    SQLiteDatabase db = this.getReadableDatabase();

    // cursors let you step through the results of a query row-by-row
    Cursor cursor = db.query(DATABASE_NAME,
        COLUMNS, // column names
        KEY_DORM + "=? AND " + KEY_ROOM + "=?", // condition
        new String[] { dormName, Integer.toString(roomNumber) }, // selections args
        null, // group by
        null, // having
        null, // order by
        null); // limit

    Room room = new Room();
    if (cursor != null){
      // moveToFirst returns false if there isn't an object for it to move to.
      if (cursor.moveToFirst()){
        room.setName(cursor.getString(0));
        room.setNumber(Integer.parseInt(cursor.getString(1)));
        room.setFull_name(cursor.getString(0) + " " + cursor.getString(1));
        room.setStatus(Integer.parseInt(cursor.getString(2)));
        room.setTime(cursor.getString(3));
        room.setNote(cursor.getString(4));
        Log.d("getRoom( " + dormName + " , " + roomNumber + " )", room.toString());
      }
    }
    return room;
  }

  /* Purpose: removes from the database the entry that's primary key matches the given Room's */
  public void deleteRoom(Room room) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(DATABASE_NAME,
                KEY_DORM + "=? AND " + KEY_ROOM + "=?", // condition
                new String[] { String.valueOf(room.getName()), Integer.toString(room.getNumber()) }); // selection args

        db.close();
        Log.d("deleteRoom", room.toString());
    }

  /* Purpose: returns a list of all of the rooms in the database */
  public List<Room> selectRooms(String dorm, int floor, String wing) {
        List<Room> rooms = new LinkedList<Room>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + DATABASE_NAME +
            " WHERE " + KEY_DORM + "='" + dorm +"' AND "
            + KEY_FLOOR + "='" + floor +"' AND "
            + KEY_WING + "='" + wing +"'", null);


      Room room = null;
        if (cursor.moveToFirst()) {
            do {
                room = new Room();
                room.setName(cursor.getString(0));
                room.setNumber(Integer.parseInt(cursor.getString(1)));
                room.setFull_name(cursor.getString(0) + " " + Integer.parseInt(cursor.getString(1)) );
                room.setStatus(Integer.parseInt(cursor.getString(4)));
                room.setTime(cursor.getString(5));
                room.setNote(cursor.getString(6));

                 Log.d("TESTING", "0:" + cursor.getString(0)  +
                        "\n1:" + cursor.getString(1) +
                        "\n2:" + cursor.getString(2) +
                        "\n3:" + cursor.getString(3) +
                        "\n4:" + cursor.getString(4) +
                        "\n5:" + cursor.getString(5) +
                        "\n6:" + cursor.getString(6) +
                        "\n7:" + cursor.getString(7));

              rooms.add(room);
            } while (cursor.moveToNext());
        }

        return rooms;
    }

  /* Purpose: removes all rooms from the database regardless of their data */
  public void deleteAllRooms() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + DATABASE_NAME);
        db.close();
        Log.d("deleteAllRooms", "removed all Rooms");
    }

  /* functions required for database synchronization with the main server */

  /* Purpose: Convert a JSONObject into a ContentValues object, what's used to access the database.
     Note: SQLite dosn't distinguish between data-types apparently */
  private static ContentValues toContentValues(JSONObject json){
        ContentValues toReturn = new ContentValues();
        Iterator<String> iter = json.keys();

        while (iter.hasNext()) {
            String key = iter.next();
            // website database columns must match those of the local database
            if (key.equals(KEY_ROOM) || key.equals(KEY_STATUS) || key.equals(KEY_DATE) || key.equals(KEY_NOTES)){
                try {
                    String value = json.getString(key);
                    // place nulls in database instead of empty strings
                    if (value == ""){
                        value = null;
                    }
                    toReturn.put(key, value);
                } catch (JSONException e) {
                    Log.e("exception", "Trouble converting JSON to Content Value \n" + json.toString(), e);
                }
            }
            else {
                Log.e("application update required", "website database has unrecognized database column \"" + key + "\".");
                return toReturn;
            }
        }
        return toReturn;
    }

  /* Purpose: creates a list of contentValues, each JSON object in the JSONARR from the website
         becomes an entry in the contentValues list. Required fields for the local database
         are added into the contentValues. */
  private static List<ContentValues> getContentValuesList(String hall, int floor, String wing){
        websiteCom com = new websiteCom();
        JSONArray jsonArr = com.getJsonArr(hall, floor, wing);
        List<ContentValues> toReturn = new ArrayList<ContentValues>();

        try{
            ContentValues CV;
            for (int i = 0; i < jsonArr.length(); i++) {
                CV = toContentValues(jsonArr.getJSONObject(i));
                /* insert dorm name and set modified to 0. If the web site's data is
                   used to update the local database, the local database isn't modified */
                CV.put(KEY_DORM, hall);
                CV.put(KEY_FLOOR, floor);
                CV.put(KEY_WING, wing);
                CV.put(KEY_MODIFIED, 0);
                toReturn.add(CV);
            }
        }
        catch(Exception e){
            Log.e("exception", "error converting JSONArray from server to a list of ContentValues");
        }
        return toReturn;
    }

  /* Purpose: returns whether the given primary key hasmodifiedd set to 1 or not.
         Normally this kind of thing would be dealt with in SQL calls, but SQLite is very limited */
  private boolean updateAllowed(ContentValues CV){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DATABASE_NAME,
                        COLUMNS,
                        KEY_DORM + "=? AND " + KEY_ROOM + "=? AND " + KEY_MODIFIED + "=?" ,
                        new String[] { CV.getAsString(KEY_DORM), CV.getAsString(KEY_ROOM), Integer.toString(1) },
                        null,
                        null,
                        null,
                        null);
        if (cursor != null){
            if (!(cursor.moveToFirst())){
                // no results found, update away!
                return true;
            }
        }
        // specified keys correspond to an entry that is modified still. Can't update yet.
        return false;
    }

  /* Purpose: adds or updates all entries in the local database for the given hall, floor, and wing
              unless any of the entries have modified = 1. */
  public int syncDB(String hall, Integer floor, String wing){
        SQLiteDatabase db = this.getWritableDatabase();
        List<ContentValues> CVList = getContentValuesList(hall, floor, wing);
        printContentValueList(CVList);

        for (int i = 0; i < CVList.size(); i++) {
            ContentValues CV = new ContentValues();
            try {
                CV = CVList.get(i);
            }
            catch(Exception e){
                Log.e("exception", "Problem accessing CVList index " + i, e);
                return 1;
            }
            // check if modified = 1 and if it dosn't (or the entry dosn't exist) apply the update.
            if(updateAllowed(CV)){
                Log.d("debugging", "update allowed \n" + CV.toString());
                db.insertWithOnConflict(DATABASE_NAME, BaseColumns._ID, CV,
                        SQLiteDatabase.CONFLICT_REPLACE);
            }
            else {
                Log.d("debugging", "update NOT allowed \n" + CV.toString());
            }
        }

        db.close();
        return 0;
    }

    /* debugging statements */
  public void printContentValueList(List<ContentValues> list){
        Log.d("debugging", "PRINTING LIST<CONTENTVALUES>");
        for (int i = 0; i < list.size(); i++) {
            try {
                Log.d("debugging", i + ": " + list.get(i).toString());
            }
            catch(Exception e){
                Log.e("exception", "LIST<CONTENTVALUES> printing error", e);
            }
        }
    }

    /*
    Code that might be needed in the future but ran into issues while under development
     */

    // alerts the user that something is wrong with the local database and the app can not run
    // can't figure how how to end an activity (quit the app)
  /*  public void databaseIssueMsgBox()
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setTitle("Local Database Error");
        dlgAlert.setMessage("Unable to initalize local database properly; an application upgrade is likely required.");
        dlgAlert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
               Activity
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }*/

}
