package com.doorknocker.doorknocker.app;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nutjung on 3/15/14.
 */
public abstract class Dorm{

    protected String dormName;
    protected int floor;
    protected int wing=0;
    protected Activity c;
    protected LinearLayout ll;
    protected ArrayList<Room> roomList = new ArrayList<Room>();

    public Dorm(String d,int f,int ww,Activity m,LinearLayout l,ArrayList<Room> r){
        this.dormName=d;
        this.floor=f;
        this.wing=ww;
        this.c=m;
        this.ll=l;
        this.roomList = r;
    }

    public void setDorm(String d) {
        this.dormName=d;
    }
    public void setFloor(int f){
        this.floor=f;
    }
    public void setWing(int w){
        this.wing=w;
    }
    public String getDorm(){
        return this.dormName;
    }
    public int getFloor(){
        return this.floor;
    }
    public int getWing(){
        return this.wing;
    }

}
