package com.example.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private ArrayList<Room> roomList = new ArrayList<Room>();
    private ID id = new ID();
    private boolean rotate = false, flip=false;
    public Spinner spin1, spin2;
    private ArrayList<DormList> dl = new ArrayList<DormList>();
    private String[] floorList={"Basement","1st floor","2nd floor","3rd floor","4th floor"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        addFloorOnSpinner2("BARH A");
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
                String d = (String) spin1.getSelectedItem();

                String f = (String) adapterView.getItemAtPosition(i);
                RadioButton sw = (RadioButton) findViewById(R.id.SWing);
                RadioButton nw = (RadioButton) findViewById(R.id.NWing);
                if( hasWing(d, f)){
                    sw.setEnabled(true);
                    nw.setEnabled(true);
                    if(!sw.isChecked()&&!nw.isChecked()){
                        sw.setChecked(true);
                    }
                }
                else{
                    sw.setChecked(false);
                    nw.setChecked(false);
                    sw.setEnabled(false);
                    nw.setEnabled(false);
                }
                onClickGo();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.WingGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                onClickGo();
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

    public void onClickRotate(View view){
        if(!rotate){
        rotate = true;
        ScrollView temp = (ScrollView) findViewById(R.id.scrollView);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) temp.getLayoutParams();
        layoutParams.setMargins(0,80,0,80);
        temp.setLayoutParams(layoutParams);
        temp.setRotation(270);
        onClickGo();
        }
        else{
        rotate = false;
        ScrollView temp = (ScrollView) findViewById(R.id.scrollView);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) temp.getLayoutParams();
        layoutParams.setMargins(0,0,0,0);
        temp.setLayoutParams(layoutParams);
        temp.setRotation(0);
        onClickGo();
        }
    }

    public void onClickGo(View view){
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
                dd.Create(rotate,false);
            }
        }
    }

    public void onClickGo(){
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
                dd.Create(rotate,flip);
            }
        }
    }

    public void onClickFlip(View view){
        if(flip){
            flip=false;
        }else{
            flip=true;
        }
        onClickGo();
    }

    public void addDormOnSpinner1(){
        spin1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.add("BARH A");
        list.add("BARH B");
        list.add("BARH C");
        list.add("BARH D");
        list.add("Blitman");
        list.add("Bray");
        list.add("Cary");
        list.add("Crockett");
        list.add("Hall");
        list.add("Nason");
        list.add("Quad Caldwell");
        list.add("Quad ChurchI");
        list.add("Quad ChurchII");
        list.add("Quad ChurchIII");
        list.add("Quad Copper");
        list.add("Quad MacDonald");
        list.add("Quad Roebling");
        list.add("Quad Pardee");
        list.add("Quad HuntI");
        list.add("Quad HuntII");
        list.add("Quad HuntIII");
        list.add("Quad Buck");
        list.add("Quad WhiteI");
        list.add("Quad WhiteII");
        list.add("Quad WhiteIII");
        list.add("Quad WhiteIV");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(dataAdapter);

    }
    public void addFloorOnSpinner2(String t){
        int num = numberFloor(t);
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

    public int numberFloor(String t){
        if(t.equalsIgnoreCase("BARH A")||t.equalsIgnoreCase("BARH D")
                ||t.equalsIgnoreCase("Blitman")){
            return 4;
        }
        return 3;
    }

    public boolean hasWing(String t,String f){
        if(t.equalsIgnoreCase("Bray")||t.equalsIgnoreCase("Cary")||t.equalsIgnoreCase("Crockett")
                ||t.equalsIgnoreCase("Hall")||t.equalsIgnoreCase("Nason")||t.equalsIgnoreCase("Blitman"))
            return true;
        if(t.equalsIgnoreCase("BARH A")&&f.equalsIgnoreCase("4th floor")) return true;
        if(t.equalsIgnoreCase("BARH D")&&f.equalsIgnoreCase("4th floor")) return true;
        return false;
    }

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
        dl.add(new createDorm("Blitman",1,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Blitman",1,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Blitman",2,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Blitman",2,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Blitman",3,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Blitman",3,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Blitman",4,1,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("Blitman",4,2,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
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
        dl.add(new createDorm("QuadCaldWell",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadCaldWell",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadCaldWell",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadChurchI",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadChurchI",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadChurchI",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadChurchII",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadChurchII",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadChurchII",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadChurchIII",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadChurchIII",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadChurchIII",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadCooper",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadCooper",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadCooper",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadMacDonald",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadMacDonald",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadMacDonald",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadRoebling",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadRoebling",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadRoebling",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadPardee",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadPardee",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadPardee",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadHuntI",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadHuntI",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadHuntI",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        //dl.add(new createDorm("QuadHuntII",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadHuntII",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadHuntII",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadHuntIII",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadHuntIII",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadHuntIII",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadBuck",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadBuck",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadBuck",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteI",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteI",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteI",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteII",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteII",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteII",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteIII",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteIII",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteIII",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteIV",1,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteIV",2,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
        dl.add(new createDorm("QuadWhiteIV",3,0,MainActivity.this,(LinearLayout) findViewById(R.id.linearLayout2),roomList));
    }
}
