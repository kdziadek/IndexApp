package com.example.kasia.indexapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Aktualne_Dane extends ActionBarActivity {

    public static DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktualne__dane);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.deleteDatabase:
                DialogDeleteDatabase dialog = new DialogDeleteDatabase();
                dialog.show(getFragmentManager(), "Potwierdź usuwanie");
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
    //Każdy przycisk tworzy nowa intencje
    //przycisk Wskazniki makroekonomiczne
    public void pokazMakro(View view) {
        Intent makroIntent = new Intent(this, Makro_wskazniki.class);
        startActivity(makroIntent);
    }

    //przycisk Rynek pracy
    public void pokazPraca(View view) {
        Intent pracaIntent = new Intent(this, Praca_wskazniki.class);
        startActivity(pracaIntent);
    }

    //Przycisk Wskazniki monetarne i finansowe
    public void pokazFinanse(View view) {
        Intent finanseIntent = new Intent(this, Finanse_wskazniki.class);
        startActivity(finanseIntent);
    }

    //Przycisk Kursy walut
    public void pokazKursy(View view) {
        Intent kursyIntent = new Intent(this, Kursy_walut.class);
        startActivity(kursyIntent);
    }


}
