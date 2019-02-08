package com.agonaika.agonaika.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.agonaika.agonaika.R;

public class CustomDialogClass3 {
    Context context;
    Activity mactivity;
    public void showDialog(Activity activity, String msg){
        mactivity = activity;
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialogue3);

//        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
//        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_no);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();


            }
        });

        dialog.show();

    }
}