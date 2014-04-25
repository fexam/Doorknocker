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
 * Created by nutjung on 4/10/14.
 */
public abstract class methodDorm extends Dorm{

    protected Activity c;
    protected LinearLayout ll;
    protected boolean rotate = false;
    protected ArrayList<Room> roomList = new ArrayList<Room>();
    protected ID search = new ID();
    protected int w,cen,one,small;
    protected LinearLayout.LayoutParams parmsWW,pb,sm;
    protected LinearLayout lleft,lright,lcenter;
    protected int[] ColorList = {Color.GREEN,Color.YELLOW,Color.RED,Color.WHITE};

    public methodDorm(String d,int f,int ww,Activity m,LinearLayout l,ArrayList<Room> r){
        super(d,f,ww);
        this.c=m;
        this.ll=l;
        this.roomList = r;
        //initiate the value need for the size of the room
        Resources resource = c.getResources();
        w = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 100, resource.getDisplayMetrics());
        cen = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50, resource.getDisplayMetrics());
        one = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, resource.getDisplayMetrics());
        small = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 60, resource.getDisplayMetrics());
        parmsWW = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        pb = new LinearLayout.LayoutParams(w, w);
        sm = new LinearLayout.LayoutParams(w,small);

    }

    protected void buildLayout(){

        lleft = new LinearLayout(c);
        lleft.setOrientation(LinearLayout.VERTICAL);
        lleft.setBackgroundColor(Color.BLACK);
        lleft.setLayoutParams(parmsWW);
        lleft.setPadding(one, one, one, one);
        ll.addView(lleft);

        lcenter = new LinearLayout(c);
        lcenter.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams parms2 = new
                LinearLayout.LayoutParams(cen, LinearLayout.LayoutParams.MATCH_PARENT);
        lcenter.setLayoutParams(parms2);
        lcenter.setBackgroundColor(Color.BLACK);
        lcenter.setPadding(one*3, one*3, one*3, one*3);
        ll.addView(lcenter);

        lright = new LinearLayout(c);
        lright.setBackgroundColor(Color.BLACK);
        lright.setOrientation(LinearLayout.VERTICAL);
        lright.setLayoutParams(parmsWW);
        lright.setPadding(one, one, one, one);
        ll.addView(lright);
    }

    protected void buildNormalRoom(int roomNumber,String direction){

        String name = dormName+" ";
        String temp = name + Integer.toString(roomNumber);
        String subname = dormName.substring(0,4);
        String roomName = Integer.toString(roomNumber);
        if(subname.equalsIgnoreCase("BARH")){
            roomName = dormName.substring(4,5) + Integer.toString(roomNumber);
        }
        final LinearLayout roomLayout = new LinearLayout(c);
        roomLayout.setBackgroundColor(ColorList[roomList.get(search.getRoomID(temp)).getStatus()]);
        roomLayout.setLayoutParams(parmsWW);

        Button hall = new
                VerticalButton(c,c,roomList.get(search.getRoomID(temp)),roomLayout,rotate);
        if(direction.equalsIgnoreCase("left")) {
            lleft.addView(roomLayout);
            hall.setBackgroundResource(R.drawable.left_room);
        }
        else if(direction.equalsIgnoreCase("right")){
            lright.addView(roomLayout);
            hall.setBackgroundResource(R.drawable.right_room);
        }
        hall.setLayoutParams(pb);
        hall.setText("\n" + roomName);  // add line for big room
        roomLayout.addView(hall);
    }

    protected void buildSmallRoom(int roomNumber,String direction,boolean flip){

        String name = dormName+" ";
        String temp = name + Integer.toString(roomNumber);

        LinearLayout roomLayoutSP1 = new LinearLayout(c);
        roomLayoutSP1.setBackgroundColor(
                ColorList[roomList.get(search.getRoomID(temp)).getStatus()]);
        roomLayoutSP1.setLayoutParams(parmsWW);

        Button hall2 = new VerticalButton(
                c,c,roomList.get(search.getRoomID(temp)),roomLayoutSP1,rotate);
        if(direction.equalsIgnoreCase("left")) {
            if(flip) {
                lleft.addView(roomLayoutSP1);
                hall2.setBackgroundResource(R.drawable.small_left_flip_room);
            }else{
                lleft.addView(roomLayoutSP1);
                hall2.setBackgroundResource(R.drawable.small_left_room);
            }
        }else if(direction.equalsIgnoreCase("right")){
            if(flip){
                lright.addView(roomLayoutSP1);
                hall2.setBackgroundResource(R.drawable.small_right_flip_room);
            }else {
                lright.addView(roomLayoutSP1);
                hall2.setBackgroundColor(R.drawable.small_right_room);
            }
        }
        hall2.setLayoutParams(sm);
        if(rotate){ hall2.setText("\n"+Integer.toString(roomNumber)); }
        else
        {
            hall2.setPadding(48,25,0,0);
            hall2.setText(Integer.toString(roomNumber));
        }
        hall2.setTextSize((float) 16);

        roomLayoutSP1.addView(hall2);
    }

    protected void buildEmptyRoom(String direction){
        LinearLayout roomLayoutSP1 = new LinearLayout(c);
        roomLayoutSP1.setBackgroundColor(Color.WHITE);
        roomLayoutSP1.setLayoutParams(parmsWW);
        Button hall = new Button(c);
        if(direction.equalsIgnoreCase("left")){
            lleft.addView(roomLayoutSP1);
            hall.setBackgroundResource(R.drawable.left_none);
        }else if(direction.equalsIgnoreCase("right")) {
            lright.addView(roomLayoutSP1);
            hall.setBackgroundResource(R.drawable.right_none);
        }
        hall.setLayoutParams(pb);
        hall.setText("");
        roomLayoutSP1.addView(hall);
    }

    protected void buildSmallEmptyRoom(String direction){
        LinearLayout roomLayoutSP1 = new LinearLayout(c);
        roomLayoutSP1.setBackgroundColor(Color.WHITE);
        roomLayoutSP1.setLayoutParams(parmsWW);
        Button hall = new Button(c);
        if(direction.equalsIgnoreCase("left")){
            lleft.addView(roomLayoutSP1);
            hall.setBackgroundResource(R.drawable.left_none);
        }else if(direction.equalsIgnoreCase("right")) {
            lright.addView(roomLayoutSP1);
            hall.setBackgroundResource(R.drawable.right_none);
        }
        hall.setLayoutParams(sm);
        hall.setText("");
        roomLayoutSP1.addView(hall);
    }

    protected void buildOpenRoom(String word, String direction,boolean top){
        LinearLayout roomLayout = new LinearLayout(c);
        roomLayout.setBackgroundColor(Color.WHITE);
        roomLayout.setLayoutParams(parmsWW);

        Button hall = new VerticalButton(c,rotate);
        if(direction.equalsIgnoreCase("left")){
            lleft.addView(roomLayout);
            if(top) {
                hall.setBackgroundResource(R.drawable.open_top_left_room);
            }else {
                hall.setBackgroundResource(R.drawable.open_down_left_room);
            }
        }else if(direction.equalsIgnoreCase("right")){
            lright.addView(roomLayout);
            if(top) {
                hall.setBackgroundResource(R.drawable.open_top_right_room);
            }else {
                hall.setBackgroundResource(R.drawable.open_down_right_room);
            }
        }
        hall.setLayoutParams(pb);
        hall.setText("\n"+word);
        roomLayout.addView(hall);
    }

    protected void buildWalkWay(){

        TextView tv = new VerticalButton(c,rotate);
        tv.setText("");
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams parms6 =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
        tv.setLayoutParams(parms6);
        lcenter.addView(tv);

    }

}
