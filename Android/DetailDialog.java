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
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by nutjung on 2/22/14.
 */
public class DetailDialog extends Dialog implements View.OnClickListener{

    public Activity c;
    public Dialog d;
    public Button confirm,cancel,room;
    public RadioButton a,p,r,u;


    public DetailDialog(Activity a,View temp){
        super(a);
        this.room = (Button) temp;
        this.c=a;
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
        //(TO DO) update the dialog based on object
        //Below isn't
        TextView r_name = (TextView) findViewById(R.id.Room_Number);
        r_name.setText(room.getText());
        ColorDrawable buttonColor = (ColorDrawable) room.getBackground();
        int colorID = buttonColor.getColor();
        if( colorID==Color.GREEN) {
            a.setChecked(true);
        }else if(colorID==Color.YELLOW){
            p.setChecked(true);
        }else if(colorID==Color.RED){
            r.setChecked(true);
        }
        else if( colorID==Color.WHITE){
            u.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        //update the color of the room
        //update the object
        int v_id = view.getId();
        if(v_id==R.id.dialog_confirm){

            if(a.isChecked()){
                room.setBackgroundColor(Color.GREEN);
            }
            else if(p.isChecked()){
                room.setBackgroundColor(Color.YELLOW);
            }
            else if(r.isChecked()){
                room.setBackgroundColor(Color.RED);
            }
            else if(u.isChecked()){
                room.setBackgroundColor(Color.WHITE);
            }
            Time now = new Time();
            now.setToNow();
            //Testing Time
            //room.setText(now.format("%m/%d/%Y"));
        }
        else if(v_id==R.id.dialog_cancel)
        {
            dismiss();
        }
        dismiss();
    }
}
