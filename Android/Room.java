package com.example.app;

import android.text.Editable;
import android.text.format.Time;

/**
 * Created by nutjung on 3/14/14.
 */
public class Room {
    private int number;
    private String name;
    private String full_name;
    private String note;
    private String time;
    private int status; //0 = Green , 1 = Yellow, 2=Red, 3=White

    public Room()
    {
    }

    public Room(String na,int n)
    {
        this.number = n;
        this.name = na;
        this.full_name = na + " " + Integer.toString(n);
        this.note ="-";
        this.time ="-";
        this.status = 3;
    }

    public Room(int n, String na, String no, String t, int s)
    {
        this.number = n;
        this.name = na;
        this.full_name = na + " " + Integer.toString(n);
        this.note = no;
        this.time = t;
        this.status = s;
    }

    public void setNumber(int n)
    { this.number = n; }
    public void setName(String n)
    { this.name = n; }
    public void setFull_name(String n)
    { this.full_name = n; }
    public void setNote(String n)
    { this.note =n;}
    public void setTime(String t)
    { this.time = t; }
    public void setStatus(int s)
    { this.status = s; }

    public int getNumber()
    { return this.number; }
    public String getName()
    { return this.name; }
    public String getFull_name()
    { return this.full_name; }
    public String getNote()
    { return this.note; }
    public String getTime()
    { return this.time; }
    public int getStatus()
    { return this.status; }


}
