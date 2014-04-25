package com.doorknocker.doorknocker.app;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private ArrayList<Room> roomList = new ArrayList<Room>();
    private ID id = new ID();
    private boolean rotate = false, flip=false, modified = false;
    private boolean radioChange=false;
    public Spinner spin1, spin2,spin3;
    private ArrayList<DormList> dl = new ArrayList<DormList>();
    private String[] floorList={"Basement","1st floor","2nd floor","3rd floor","4th floor"};
    private databaseHelper db;
    private ArrayList<Room> currentWorkingRoom = new ArrayList<Room>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    //*** TO DO LIST INSIDE THE METHOD
    /* Purpose: initiate all the necessary variables
        and show the floor plan window
    * */
    protected void onCreateAfterLogin(){
        db = new databaseHelper(this);
        //TO DO: delete this After local database fully sync with web server
        db.deleteAllRooms();
        setContentView(R.layout.activity_main);
        for(int i=0;i<id.dorm_list.length;i++)
        {
            String temp = id.dorm_list[i];
            String[] parts = temp.split(" ");
            String parts1 = parts[0];
            String parts2 = parts[1];
            Room r = new Room(parts1,Integer.parseInt(parts2));
            roomList.add(r);
        }
        addDormOnSpinner1();
        addFloorOnSpinner2("BARH A"); //show the first dorm

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                addFloorOnSpinner2((String) adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                radioChange=false;
                String d = (String) spin1.getSelectedItem();
                Boolean radioCheck =false;
                String f = (String) adapterView.getItemAtPosition(i);
                RadioButton sw = (RadioButton) findViewById(R.id.SWing);
                RadioButton nw = (RadioButton) findViewById(R.id.NWing);
                if( hasWing(d, f)){
                    sw.setEnabled(true);
                    nw.setEnabled(true);
                    if(!sw.isChecked()&&!nw.isChecked()){
                        sw.setChecked(true);
                        radioCheck=true;
                    }
                }
                else{
                    if(sw.isChecked()||nw.isChecked()){
                        radioCheck=true;
                    }
                    ((RadioGroup) findViewById(R.id.WingGroup)).clearCheck();
                    sw.setEnabled(false);
                    nw.setEnabled(false);
                }
                if(!radioChange) {
                    updateLayout(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.WingGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                updateLayout(true);
                radioChange=true;
            }
        });
        addDormList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* Purpose: sync the working memory with local database
    * */
    public void updateLocal(){
        if(currentWorkingRoom!=null){
        for(Room r:currentWorkingRoom){
            String fullName = r.getFull_name();
            fullName = fullName.replace(" Hall","");
            if(r.getNumber()!=0){
                db.updateRoom(roomList.get(id.getRoomID(fullName) ) );
            }
        }}
    }

    /*Purpose: get dorm name to the format for Url*/
    public String properCase(String inputVal){
        if(inputVal.length()==0) return"";
        if(inputVal.length()==1) return inputVal.toUpperCase();
        return inputVal.substring(0,1).toUpperCase() +
                inputVal.substring(1).toLowerCase();
    }

    /*Purpose: sync the working memory with web server*/
    public void updateWebserver()  {
        modified = false;
        JSONArray toSend = new JSONArray();
        JSONArray Url = new JSONArray();
        if(currentWorkingRoom!=null){
            String url = "http://doorknocker.myrpi.org/scripts/android/upload.php?dorm=";
            String fName="";
            for(Room r:currentWorkingRoom){
                String fullName = r.getFull_name();
                fName = fullName.split(" ")[0];
                fullName = fullName.replace(" Hall","");
                if(r.getNumber()!=0) {
                    Room temp = roomList.get(id.getRoomID(fullName));
                    if(fName.equalsIgnoreCase("")){
                        fName = temp.getName();
                    }
                    JSONObject JObj = new JSONObject();
                    try {
                        JObj.put("room_number", Integer.toString(temp.getNumber()));
                        JObj.put("state", Integer.toString(temp.getStatus()));
                        JObj.put("date", temp.getTime());
                        JObj.put("notes", temp.getNote());
                        toSend.put(JObj);
                        System.out.println("Posting: RoomName: " + fName + " Number :"
                                + temp.getNumber() + " State: "+temp.getStatus()+
                                " date: "+temp.getTime()+" notes: "+temp.getNote());
                        System.out.println("RPosting: RoomName: " + fName + " Number :"
                                + r.getNumber() + " State: "+r.getStatus()+
                                " date: "+r.getTime()+" notes: "+r.getNote());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(temp.getNumber()!=r.getNumber()){
                        System.out.print("*****SOMETHING DOES NOT RIGHT******");
                    }
                    if(temp.getStatus()!=r.getStatus()||temp.getNote().compareTo(r.getNote())!=0
                            ||temp.getTime().compareTo(r.getTime())!=0){
                        modified=true;
                        System.out.println("*****MODIFIED******");
                    }
                }
            }
            if(fName!="") {
                if(fName.equalsIgnoreCase("BARHA")||fName.equalsIgnoreCase("BARHB")||
                        fName.equalsIgnoreCase("BARHC")||fName.equalsIgnoreCase("BARHD")){
                    fName = "BARH-"+fName.substring(4,5);
                }else {
                    fName = properCase(fName);
                    fName += "%20Hall";
                }
                url += fName;
                System.out.println(url);
                Url.put(url);
                if(modified){
                    JSonTransmitter transmitter = new JSonTransmitter();
                    transmitter.execute(toSend, Url);
                    System.out.println("----Post----");
                }
            }
        }
    }

    /* Purpose: get the data from the web server and using that data to update the
        RoomList
    * */
     public void updateRoomList(DormList d){

        String strWing ="";
        if(d.getWing()==1){
            strWing ="A";
        }else if(d.getWing()==2){
            strWing ="B";
        }
        String strDorm = d.getDorm() +" Hall";
        //get data from the webserver and store in local database
        db.syncDB(strDorm, d.getFloor(), strWing);

        //get data from local database
        if(currentWorkingRoom!=null){
            currentWorkingRoom.clear();
        }
        List<Room> r_temp = db.selectRooms(strDorm,d.getFloor(),strWing);
        for(Room r:r_temp){
            currentWorkingRoom.add(new Room(r.getName(),r.getNumber(),r.getStatus(),r.getTime(),
                    r.getNote()));
        }
        for(Room r:currentWorkingRoom){
            if(r.getNumber()!=0){
                Room temp_r = new Room(r.getName().replace(" Hall",""),r.getNumber(),
                        r.getStatus(),r.getTime(),r.getNote());
                roomList.set(id.getRoomID(temp_r.getFull_name()), temp_r);
            }
        }
    }

    /* Update the floor plan based on the current selected Dorm,floor,wing
    if sync is true, it will sync data with server & local
    * */
    public void updateLayout(boolean sync){
        String d = (String) spin1.getSelectedItem();
        String f = (String) spin2.getSelectedItem();
        int floor=0;
        for(int i=0;i<floorList.length;i++){
            if(f.equalsIgnoreCase(floorList[i]))
            { floor=i;}
        }
        RadioButton sw = (RadioButton) findViewById(R.id.SWing);
        RadioButton nw = (RadioButton) findViewById(R.id.NWing);
        int wing=0;
        if(sw.isChecked()) {
            wing=1;
        }
        if(nw.isChecked()) {
            wing=2;
        }
        String temp[] = d.split(" ");
        if(temp.length>1){
            d=temp[0]+temp[1];
        }
        for(DormList dd : dl){
            if(d.equalsIgnoreCase(dd.getDorm())&&floor==dd.getFloor()&&wing==dd.getWing()) {
                //update & synch with server here
                if(sync) {
                    updateLocal();
                    updateWebserver();
                    updateRoomList(dd);
                }
                System.out.println("Creating: "+dd.getDorm());
                dd.Create(rotate,flip);
            }
        }
    }

    /* Purpose: response to rotate button to rotate the floor plan 90 degree
        * */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onClickRotate(View view){
        if(!rotate){
            rotate = true;
            ScrollView temp = (ScrollView) findViewById(R.id.scrollView);
            RelativeLayout.LayoutParams layoutParams
                    = (RelativeLayout.LayoutParams) temp.getLayoutParams();
            layoutParams.setMargins(0,80,0,80);
            temp.setLayoutParams(layoutParams);
            temp.setRotation(270);
            updateLayout(false);
        }
        else{
            rotate = false;
            ScrollView temp = (ScrollView) findViewById(R.id.scrollView);
            RelativeLayout.LayoutParams layoutParams
                    = (RelativeLayout.LayoutParams) temp.getLayoutParams();
            layoutParams.setMargins(0,0,0,0);
            temp.setLayoutParams(layoutParams);
            temp.setRotation(0);
            updateLayout(false);
        }
    }

    /* Purpose: mark all the room red
    * */
    public void onClickMarkAll(View view){
        if(currentWorkingRoom!=null){
            for(Room r:currentWorkingRoom){
                String fullName = r.getFull_name();
                fullName = fullName.replace(" Hall","");
                if(r.getNumber()!=0){
                    Room temp = roomList.get(id.getRoomID(fullName));
                    temp.setStatus(2); // 2 = red
                    roomList.set(id.getRoomID(fullName),temp);
                }
            }}
        updateLayout(true);
    }

    /* Purpose: response to the flip button to flip all rooms left-right
    * */
    public void onClickFlip(View view){
        if(flip){
            flip=false;
        }else{
            flip=true;
        }
        updateLayout(false);
    }

    /* Purpose: response to the login button,
        if successfully login, call onCreateAfterLogin()
        else show the message to tell user
    * */
    public void onClickLogin(View view){
        boolean success=false;
        websiteCom com = new websiteCom();
        EditText username = (EditText) findViewById(R.id.UsernameEditText);
        EditText password = (EditText) findViewById(R.id.PasswordEditText);
        String user = username.getText().toString();
        String pass = password.getText().toString();
        success = com.authorizeUser(user,pass);
        if(success){
            onCreateAfterLogin();
        }else{
            Toast.makeText(getApplicationContext(),
                    "Invalid Username or Password",Toast.LENGTH_LONG).show();
        }
    }

    public void onClickDropDown(final View view){
        String[] items = new String[] {"Rotate","Flip","Mark All"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,items);
        new AlertDialog.Builder(this)
                .setTitle("Option")
                .setAdapter(adapter, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0){
                            onClickRotate(view);
                        }else if(i==1){
                            onClickFlip(view);
                        }else if(i==2){
                            onClickMarkAll(view);
                        }
                    }
                }).create().show();
    }

    /* Purpose: add all the dorms' name to the spinner1
    * */
    public void addDormOnSpinner1(){
        spin1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.add("BARH A");
        list.add("BARH B");
        list.add("BARH C");
        list.add("BARH D");
        list.add("Barton");
        list.add("Bray");
        list.add("Cary");
        list.add("Crockett");
        list.add("Hall");
        list.add("Nason");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(dataAdapter);

    }

    /* Purpose: add the number of floor to the Spinner2 to match
        the number of floor of given dorm's name
    * */
    public void addFloorOnSpinner2(String dormName){
        int num = numberFloor(dormName);
        spin2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        for(int i=1;i<=num;i++){
            list.add(floorList[i]);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(dataAdapter);
    }

    /* Purpose: get the number of floor from the given dorm name
    * */
    public int numberFloor(String dormName){
        if(dormName.equalsIgnoreCase("BARH A")||dormName.equalsIgnoreCase("BARH D")
                ||dormName.equalsIgnoreCase("Barton")){
            return 4;
        }
        return 3;
    }

    /* Purpose: check whatever the given dorm name and floor has wing or not
    * */
    public boolean hasWing(String dormName,String floor){
        if(dormName.equalsIgnoreCase("Bray")||dormName.equalsIgnoreCase("Cary")
                ||dormName.equalsIgnoreCase("Crockett")||dormName.equalsIgnoreCase("Hall")
                ||dormName.equalsIgnoreCase("Nason")) {
            return true;
        }
        if(dormName.equalsIgnoreCase("BARH A")&&floor.equalsIgnoreCase("4th floor")){
            return true;
        }
        if(dormName.equalsIgnoreCase("BARH D")&&floor.equalsIgnoreCase("4th floor")){
            return true;
        }
        if(dormName.equalsIgnoreCase("Barton")&&!floor.equalsIgnoreCase("1st floor")){
            return true;
        }
        return false;
    }

    /* Purpose: add a list of all dorms to the ArrayList<Dorm> dl
    * */
    public void addDormList(){
        dl.add(new createDorm("BARHA",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHA",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHA",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHA",4,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHA",4,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHB",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHB",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHB",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHC",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHC",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHC",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHD",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHD",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHD",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHD",4,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BARHD",4,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Barton",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Barton",2,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Barton",2,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Barton",3,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Barton",3,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Barton",4,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Barton",4,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BRAY",1,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BRAY",1,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BRAY",2,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BRAY",2,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BRAY",3,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("BRAY",3,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("HALL",1,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("HALL",1,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("HALL",2,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("HALL",2,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("HALL",3,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("HALL",3,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CARY",1,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CARY",1,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CARY",2,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CARY",2,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CARY",3,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CARY",3,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CROCKETT",1,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CROCKETT",1,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CROCKETT",2,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CROCKETT",2,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CROCKETT",3,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("CROCKETT",3,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("NASON",1,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("NASON",1,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("NASON",2,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("NASON",2,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("NASON",3,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("NASON",3,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
    }
}
