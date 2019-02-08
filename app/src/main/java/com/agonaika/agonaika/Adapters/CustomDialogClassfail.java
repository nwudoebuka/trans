package com.agonaika.agonaika.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.agonaika.agonaika.R;

public class CustomDialogClassfail {

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialogue_failed);

        TextView text = (TextView) dialog.findViewById(R.id.error_message);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dismiss);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
