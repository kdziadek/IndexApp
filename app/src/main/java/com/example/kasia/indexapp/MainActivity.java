package com.example.kasia.indexapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


public class MainActivity extends ActionBarActivity {

    public static DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Menu usuwanie bazy
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.deleteDatabase:

                DialogDeleteDatabase dialog = new DialogDeleteDatabase();

                Bundle b = new Bundle();

                dialog.show(getFragmentManager(), "potwierdzanie usuwanie");

                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    //Przyciski

    public void pobierzDane(View view) {
        Intent pobierzIntent = new Intent(this,Aktualne_Dane.class);
        startActivity(pobierzIntent);
    }

    public void wyswietlDane(View view) {
        Intent wyswietlDaneIntent = new Intent(this,Archiwalne_Dane.class);
        startActivity(wyswietlDaneIntent);
    }

    public void wyswietlWizytowke(View view) {
        Intent wyswietlWizytowkeIntent = new Intent(this,Wizytowka.class);
        startActivity(wyswietlWizytowkeIntent);
    }
}
