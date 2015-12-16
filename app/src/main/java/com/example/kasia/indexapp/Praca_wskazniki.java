package com.example.kasia.indexapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class Praca_wskazniki extends ActionBarActivity {

    ListView listView;
    Button zaznacz,odznacz;
    ArrayAdapter<Model> adapter;
    List<Model> list = new ArrayList<Model>();
    static String[] zaznaczoneWskaznikiNazwa;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praca_wskazniki);

        listView = (ListView) findViewById(R.id.my_list);
        adapter = new MyAdapter(this,getModel());
        listView.setAdapter(adapter);
    }



    /*public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
        TextView label = (TextView) v.getTag(R.id.label);
        CheckBox checkbox = (CheckBox) v.getTag(R.id.check);
        nazwy = label.getText().toString()+" "+isCheckedOrNot(checkbox);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_praca_wskazniki, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private String isCheckedOrNot(CheckBox checkbox) {
        if(checkbox.isChecked())
            return "is checked";
        else
            return "is not checked";
    }

    private List<Model> getModel() {

        list.add(new Model("Stopa bezrobocia"));
        list.add(new Model("Bezrobotni zarejestrowani"));
        list.add(new Model("Wynagrodzenie minimalne"));
        list.add(new Model("Wynagrodzenie przeciętne roczne w gospodarce"));

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

        Intent pracaWynikiIntent = new Intent(this, praca_wyniki.class);
        startActivity(pracaWynikiIntent);
    }



}


