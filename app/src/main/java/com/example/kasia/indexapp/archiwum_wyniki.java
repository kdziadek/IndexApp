package com.example.kasia.indexapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;


public class archiwum_wyniki extends ActionBarActivity {
    public String archiwumWynikiText="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archiwum_wyniki);

        wyswietlWynikiArchiwum();
        TextView textView = (TextView) findViewById(R.id.archiwumWynikiTextView);
        textView.setText(archiwumWynikiText);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.deleteDatabase:
                DialogDeleteDatabase dialog = new DialogDeleteDatabase();
                dialog.show(getFragmentManager(), "Potwierdz usuwanie");
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private String wyswietlWynikiArchiwum() {
        List<Wskaznik> wskaznikiDb = MainActivity.db.getAllIndexes();
        for (Wskaznik wskaznik : wskaznikiDb) {
           for (int i=0;i<Archiwalne_Dane.zaznaczoneWskaznikiNazwa.length; i++) {

                if (wskaznik.getNazwa().equalsIgnoreCase(Archiwalne_Dane.zaznaczoneWskaznikiNazwa[i]))
                        archiwumWynikiText += wskaznik.getNazwa()+ ":\n\t\t"+wskaznik.getData() + " | " + wskaznik.getWartosc() + "\n";
                    }
          }

        return archiwumWynikiText;
    }
}
