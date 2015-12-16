package com.example.kasia.indexapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class Archiwalne_Dane extends ActionBarActivity {

    ListView listView;
    CheckBox cb;
    ArrayAdapter<Model> adapter;
    List<Model> list = new ArrayList<Model>();
    static String[] zaznaczoneWskaznikiNazwa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archiwalne__dane);


        listView = (ListView) findViewById(R.id.my_list);

        adapter = new MyAdapter(this,getModel());
        listView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_archiwalne__dane, menu);
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

    private Boolean isCheckedOrNot(CheckBox checkbox) {

        if(checkbox.isChecked())
            return true;
        else
            return false;
    }

    private List<Model> getModel() {
        list.add(new Model("Produkt Krajowy Brutto r/r"));
        list.add(new Model("Inflacja r/r"));
        list.add(new Model("Inflacja m/m"));
        list.add(new Model("Import towarów"));
        list.add(new Model("Eksport towarów"));
        list.add(new Model("Stopa referencyjna NBP"));
        list.add(new Model("Stopa lombardowa NBP"));
        list.add(new Model("Stopa redyskontowa NBP"));
        list.add(new Model("Stopa depozytowa"));
        list.add(new Model("Stopa bezrobocia"));
        list.add(new Model("Bezrobotni zarejestrowani"));
        list.add(new Model("Wynagrodzenie minimalne"));
        list.add(new Model("Wynagrodzenie przeciętne roczne w gospodarce"));
        list.add(new Model("EUR"));
        list.add(new Model("USD"));
        list.add(new Model("CHF"));
        list.add(new Model("GBP"));
        list.add(new Model("JPY"));

        return list;
    }



    public void wyswietlZaznaczoneArchiwum(View view) {

        List<Model> wskaznikiList = list;
        zaznaczoneWskaznikiNazwa = new String[wskaznikiList.size()];
        for (int i = 0; i < wskaznikiList.size(); i++) {
            Model wskaznik = wskaznikiList.get(i);
            if (wskaznik.isSelected()) {
                zaznaczoneWskaznikiNazwa[i]=wskaznik.getName();

            }
        }

        Intent archiwumWynikiIntent = new Intent(this, archiwum_wyniki.class);
        startActivity(archiwumWynikiIntent);
    }


}
