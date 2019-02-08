package com.agonaika.agonaika.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.agonaika.agonaika.Dashboard;
import com.agonaika.agonaika.R;
import com.agonaika.agonaika.Register2;
import com.agonaika.agonaika.Register3;
import com.agonaika.agonaika.Registration1;

public class CustomDialogclasssuccess{

    public static void showDialog(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialogue_success);

        TextView text = (TextView) dialog.findViewById(R.id.success_message);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.success_btn_dismiss);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 activity.startActivity(new Intent(activity.getBaseContext(), Dashboard.class));
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}
