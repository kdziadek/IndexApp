package com.example.kasia.indexapp;

/**
 * Created by Kasia on 2015-05-27.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    // stałe bazy
    public static final int DATABASE_VERSION = 2; // wersja bazy
    private static final String DATABASE_NAME = "bazaWskaznikow"; // nazwa bazy
    private static final String TABLE_INDEX = "wskazniki"; // tabela z wskaznikami

    //  kolumn  tabeli
    private static final String KEY_ID = "id";
    private static final String KEY_NAZWA = "nazwa";
    private static final String KEY_WARTOSC = "wartosc";
    private static final String KEY_DATA = "data";

    // referencja do bazy
    public static SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    // tworzenie tabeli
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INDEX_TABLE = "CREATE TABLE " + TABLE_INDEX + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_NAZWA + " TEXT,"+ KEY_DATA + " TEXT," + KEY_WARTOSC + " TEXT" + ")";
        db.execSQL(CREATE_INDEX_TABLE);
    }

    // aktualizacja bazy
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INDEX); // usunięcie tabeli
        onCreate(db);  // ponowne utworzenie tabeli
    }

    // dodanie wskaznika
    public void addIndex(Wskaznik wskaznik) {

        ContentValues values = new ContentValues();

        values.put(KEY_NAZWA, wskaznik.getNazwa()); // nazwa wskaznika
        values.put(KEY_WARTOSC, wskaznik.getWartosc());  // wartosc wskaznika
        values.put(KEY_DATA, wskaznik.getData());  // data ostatniej aktualizacji wskazniki

        db.insert(TABLE_INDEX, null, values);  // dodanie wskaznika do bazy
    }

    public Wskaznik getIndex(String nazwa) {
        Cursor cursor = db.query(TABLE_INDEX, new String[] { KEY_ID, KEY_NAZWA, KEY_DATA, KEY_WARTOSC },
                KEY_NAZWA + "=?", new String[] { String.valueOf(nazwa) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Wskaznik wskaznik = new Wskaznik(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2), cursor.getString(3));

        return wskaznik;
    }

    // pobranie pojedynczego wskaznika
    public Wskaznik getIndex(int id) {

        Cursor cursor = db.query(TABLE_INDEX, new String[] { KEY_ID, KEY_NAZWA, KEY_DATA, KEY_WARTOSC },
                KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Wskaznik wskaznik = new Wskaznik(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2), cursor.getString(3));

        return wskaznik;
    }

    public List<Wskaznik> getAllIndexes(String nazwa) {

        List<Wskaznik> indexList = new ArrayList<Wskaznik>();
        String nazwaWskaznika=nazwa;

        String selectQuery = "SELECT * FROM " + TABLE_INDEX + " WHERE "+KEY_NAZWA+"='" + nazwaWskaznika +"'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // pętla przez wszystkie elementy z dodawaniem ich do listy
        if (cursor.moveToFirst()) {
            do {
                Wskaznik wskaznik = new Wskaznik();
                wskaznik.setId(Integer.parseInt(cursor.getString(0)));
                wskaznik.setNazwa(cursor.getString(1));
                wskaznik.setData(cursor.getString(2));
                wskaznik.setWartosc(cursor.getString(3));

                indexList.add(wskaznik);

            } while (cursor.moveToNext());
        }

        return indexList;
    }

    // pobranie wszystkich wskaznikow
    public List<Wskaznik> getAllIndexes() {

        List<Wskaznik> indexList = new ArrayList<Wskaznik>();

        String selectQuery = "SELECT  * FROM " + TABLE_INDEX ;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // dodawanie wszystkich elementow do listy
        if (cursor.moveToFirst()) {
            do {
                Wskaznik wskaznik = new Wskaznik();
                wskaznik.setId(Integer.parseInt(cursor.getString(0)));
                wskaznik.setNazwa(cursor.getString(1));
                wskaznik.setData(cursor.getString(2));
                wskaznik.setWartosc(cursor.getString(3));

                indexList.add(wskaznik);

            } while (cursor.moveToNext());
        }

        return indexList;
    }

    // pobranie liczby rekordow znajdujacych sie w bazie
    public int getIndexCount() {

        String countQuery = "SELECT  * FROM " + TABLE_INDEX;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public int getIndexCount(String nazwa) {

        String countQuery = "SELECT  * FROM " + TABLE_INDEX +" WHERE nazwa='"+nazwa+"'";
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    // aktualizacja wskaznika
    public int updateIndex(Wskaznik wskaznik) {

        ContentValues values = new ContentValues();
        values.put(KEY_NAZWA, wskaznik.getNazwa());
        values.put(KEY_DATA, wskaznik.getData());
        values.put(KEY_WARTOSC, wskaznik.getWartosc());

        return db.update(TABLE_INDEX, values, KEY_ID + " = ?",
                new String[] { String.valueOf(wskaznik.getId()) });
    }

    // usunięcie pojedynczego wskaznika
    public void deleteIndex(Wskaznik wskaznik) {

        db.delete(TABLE_INDEX, KEY_ID + " = ?",
                new String[] { String.valueOf(wskaznik.getId()) });

    }

    //sprawdzanie czy istnieje w bazie rekord o podanych wartosciach

    public boolean sprawdz(String nazwa, String wartosc, String data) throws SQLException {
        Cursor mCursor = db.query(TABLE_INDEX,
                new String[]{KEY_NAZWA, KEY_WARTOSC, KEY_DATA},
                KEY_NAZWA + " = ? and " + KEY_WARTOSC + " = ? and " + KEY_DATA + " = ?",
                new String[]{nazwa, wartosc, data},
                null, null, null, null);
        if(mCursor.getCount()>0) {
            //Log.i("Rekord: ","istnieje");
            return false;
        }
        else{
            //Log.i("Rekord: ","nie istnieje");
            return true;
        }
    }
}