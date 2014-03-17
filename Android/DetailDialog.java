package com.example.app;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by nutjung on 2/22/14.
 */
public class DetailDialog extends Dialog implements View.OnClickListener{

    public Activity c;
    public Dialog d;
    public Button confirm,cancel,room;
    public RadioButton a,p,r,u;
    public LinearLayout ll;
    private Room rr;

    public DetailDialog(Activity a,View temp, LinearLayout l){
        super(a);
        this.room = (Button) temp;
        this.c=a;
        this.ll = l;
    }

    public DetailDialog(Activity a,View temp, LinearLayout l,Room rl){
        super(a);
        this.room = (Button) temp;
        this.c=a;
        this.ll = l;
        this.rr = rl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.detail_dialog);
        confirm = (Button) findViewById(R.id.dialog_confirm);
        cancel = (Button) findViewById(R.id.dialog_cancel);
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
        a = (RadioButton) findViewById(R.id.available);
        p = (RadioButton) findViewById(R.id.pending);
        r = (RadioButton) findViewById(R.id.reject);
        u = (RadioButton) findViewById(R.id.unvisited);

        TextView r_name = (TextView) findViewById(R.id.Room_Number);
        String fullName = rr.getFull_name();
        String subname = fullName.substring(0,4);
        if(subname.equalsIgnoreCase("BARH")){
            fullName = subname +" "+fullName.substring(4,5) + fullName.substring(6);
        }
        r_name.setText(fullName);
        if( rr.getStatus()==0) {
            a.setChecked(true);
        }else if(rr.getStatus()==1){
            p.setChecked(true);
        }else if(rr.getStatus()==2){
            r.setChecked(true);
        }
        else if(rr.getStatus()==3){
            u.setChecked(true);
        }
        TextView r_time = (TextView) findViewById(R.id.last_updated);
        r_time.setText(rr.getTime());
        EditText r_edit = (EditText) findViewById(R.id.Note);
        r_edit.setText(rr.getNote());
    }

    @Override
    public void onClick(View view) {
        //update the color of the room
        //update the object
        int v_id = view.getId();
        if(v_id==R.id.dialog_confirm){

            if(a.isChecked()){
                ll.setBackgroundColor(Color.GREEN);
                rr.setStatus(0);
            }
            else if(p.isChecked()){
                ll.setBackgroundColor(Color.YELLOW);
                rr.setStatus(1);
            }
            else if(r.isChecked()){
                ll.setBackgroundColor(Color.RED);
                rr.setStatus(2);
            }
            else if(u.isChecked()){
                ll.setBackgroundColor(Color.WHITE);
                rr.setStatus(3);
            }
            Time now = new Time();
            now.setToNow();
            rr.setTime(now.format("%m/%d/%Y"));
            EditText r_edit = (EditText) findViewById(R.id.Note);
            rr.setNote(r_edit.getText().toString());
            //room.setText(now.format("%m/%d/%Y"));
        }
        else if(v_id==R.id.dialog_cancel)
        {
            dismiss();
        }
        dismiss();
    }
}
