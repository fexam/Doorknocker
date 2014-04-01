package com.doorknocker.doorknocker.app;

/**
 * Created by nutjung on 3/14/14.
 */
public class Room {
    private int number;
    private String dormName;
    private String full_name;
    private String note;
    private String time;
    private int status; //0 = Green , 1 = Yellow, 2=Red, 3=White

    public Room(){

    }

    public Room(String dorm,int roomNumber){
        this.dormName = dorm;
        this.number = roomNumber;
        this.full_name = dorm + " " + Integer.toString(roomNumber);
        this.note ="-";
        this.time ="-";
        this.status = 3;
    }

    public Room(String dorm,int roomNumber, int s, String t, String no){
        this.number = roomNumber;
        this.dormName = dorm;
        this.full_name = dorm + " " + Integer.toString(roomNumber);
        this.note = no;
        this.time = t;
        this.status = s;
    }

    public void setNumber(int n){
        this.number = n;
    }
    public void setName(String n){
        this.dormName = n;
    }
    public void setFull_name(String n){
        this.full_name = n;
    }
    public void setNote(String n){
        this.note =n;
    }
    public void setTime(String t){
        this.time = t;
    }
    public void setStatus(int s){
        this.status = s;
    }

    public int getNumber(){
        return this.number;
    }
    public String getName(){
        return this.dormName;
    }
    public String getFull_name(){
        return this.full_name;
    }
    public String getNote(){
        return this.note;
    }
    public String getTime(){
        return this.time;
    }
    public int getStatus(){
        return this.status;
    }

}
