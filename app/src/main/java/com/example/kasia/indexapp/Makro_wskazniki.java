package com.example.kasia.indexapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Makro_wskazniki extends ActionBarActivity {

    private static final String TAG = "IndexApp";
    ListView listView;
    ArrayAdapter<Model> adapter;
    List<Model> list = new ArrayList<Model>();
    static String[] zaznaczoneWskaznikiNazwa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makro_wskazniki);

        listView = (ListView) findViewById(R.id.my_list);
        adapter = new MyAdapter(this,getModel());
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_makro_wskazniki, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private List<Model> getModel() {
        list.add(new Model("Produkt Krajowy Brutto r/r"));
        list.add(new Model("Inflacja r/r"));
        list.add(new Model("Inflacja m/m"));
        list.add(new Model("Import towarów"));
        list.add(new Model("Eksport towarów"));

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


    public void wyswietlZaznaczone(View view) {

        List<Model> wskaznikiList = list;
        zaznaczoneWskaznikiNazwa = new String[wskaznikiList.size()];
        for (int i = 0; i < wskaznikiList.size(); i++) {
            Model wskaznik = wskaznikiList.get(i);
            if (wskaznik.isSelected()) {
                zaznaczoneWskaznikiNazwa[i]=wskaznik.getName();
            }
        }


        Intent makroWynikiIntent = new Intent(this, makro_wyniki.class);
        startActivity(makroWynikiIntent);
    }
}
