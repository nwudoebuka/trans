package com.agonaika.agonaika.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.agonaika.agonaika.Dashboard;
import com.agonaika.agonaika.R;
import com.agonaika.agonaika.Register2;
import com.agonaika.agonaika.Register3;
import com.agonaika.agonaika.Registration1;

public class CustomDialogclassgps{

    public static void showDialog(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog_gps);

        TextView text = (TextView) dialog.findViewById(R.id.message);
        text.setText(msg);
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        Button dialogButton = (Button) dialog.findViewById(R.id.btn_ok);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity.getBaseContext(), Dashboard.class));
                activity.startActivity(new Intent(action));
                dialog.dismiss();

            }
        });

        dialog.show();

    }

}
