package com.example.kasia.indexapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasia on 2015-11-02.
 */
public class Kursy_walut extends ActionBarActivity {

    ListView listView;
    ArrayAdapter<Model> adapter;
    List<Model> list = new ArrayList<Model>();
    static String[] zaznaczoneWskaznikiNazwa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kursy_walut);

        listView = (ListView) findViewById(R.id.my_list);
        adapter = new MyAdapter(this,getModel());
        listView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kursy_walut, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.deleteDatabase:
                DialogDeleteDatabase dialog = new DialogDeleteDatabase();
                dialog.show(getFragmentManager(), "Potwierd? usuwanie");
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private String isCheckedOrNot(CheckBox checkbox) {
        if(checkbox.isChecked())
            return "is checked";
        else
            return "is not checked";
    }

    private List<Model> getModel() {
        list.add(new Model("EUR"));
        list.add(new Model("USD"));
        list.add(new Model("CHF"));
        list.add(new Model("GBP"));
        list.add(new Model("JPY"));

        return list;
    }

    public void zaznaczWszystko(View view) {
        if (listView.getCount() > 0) {
            for (int i = 0; i < listView.getCount(); i++) {
                view = listView.getChildAt(i);
                CheckBox check=(CheckBox)view.findViewById(R.id.check);
                check.setChecked(true);
            }
        }
    }


    public void odznaczWszystko(View view) {
        if (listView.getCount() > 0) {
            for (int i = 0; i < listView.getCount(); i++) {
                view = listView.getChildAt(i);
                CheckBox check=(CheckBox)view.findViewById(R.id.check);
                check.setChecked(false);
            }
        }
    }


    public void wyswietlZaznaczone(View view){

        List<Model> wskaznikiList = list;
        zaznaczoneWskaznikiNazwa = new String[wskaznikiList.size()];
        for (int i = 0; i < wskaznikiList.size(); i++) {
            Model wskaznik = wskaznikiList.get(i);
            if (wskaznik.isSelected()) {
                zaznaczoneWskaznikiNazwa[i]=wskaznik.getName();
            }
        }


        Toast toast = Toast.makeText(getApplicationContext(), "Pobieram dane...", Toast.LENGTH_SHORT);
        toast.show();
        Intent kursyWynikiIntent = new Intent(this, Kursy_wyniki.class);
        startActivity(kursyWynikiIntent);
    }
}
