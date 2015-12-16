package com.example.kasia.indexapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

/**
 * Created by Kasia on 2015-12-03.
 * Okno dialogowe podczas usuwania bazy (do potwierdzenia)
 */
public class DialogDeleteDatabase extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_deleteDatabase)
                .setPositiveButton(R.string.usunBaze, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.db.onUpgrade(DatabaseHandler.db,DatabaseHandler.DATABASE_VERSION,DatabaseHandler.DATABASE_VERSION+1);
                    }
                })
                .setNegativeButton(R.string.anuluj, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }


}
