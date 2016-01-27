package com.example.kasia.indexapp;

import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public class praca_wyniki extends ActionBarActivity {

    private static final String TAG = "indexApp";
    public static final String SERVER_URL = "http://v-ie.uek.krakow.pl/~s166530/";
    public static final String QUERY_FILE = "baza.xml";
    public static final String QUERY_URL = SERVER_URL + QUERY_FILE;
    public String wynik="";
    public static String toastText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praca_wyniki);


        Log.i(TAG, "³¹czenie z serwerem...");
        AsyncDownloader downloader = new AsyncDownloader();
        downloader.execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private class AsyncDownloader extends AsyncTask<Object, String, Integer> {

        private void handleNewRecord(String wynik) {
            TextView textView = (TextView) findViewById(R.id.pracaListaWyniki);
            textView.setText(wynik);
        }

        @Override
        protected Integer doInBackground (Object...arg0){
            XmlPullParser receivedData = tryDownloadingXmlData();
            int recordsFound = tryParsingXmlData(receivedData);
            runOnUiThread (new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
                }
            });
            return recordsFound;
        }

        private XmlPullParser tryDownloadingXmlData() {
            try {
                Log.i(TAG, "Pobieram dane...");
                URL xmlUrl = new URL(QUERY_URL);
                XmlPullParser receivedData = XmlPullParserFactory.newInstance().newPullParser();
                receivedData.setInput(xmlUrl.openStream(), null);
                return receivedData;
            } catch (XmlPullParserException e) {
                Log.e(TAG, "XmlPullParserException", e);
                toastText="Problem z pobieraniem danych XmlPullParserExecpetion";
            } catch (IOException e) {
                Log.e(TAG, "XmlPullParserException", e);
                toastText="Problem z pobieraniem danych XmlPullParserExecpetion";
            }
            return null;
        }

        private int tryParsingXmlData(XmlPullParser receivedData) {
            if (receivedData != null) {
                try {
                    return processReceivedData(receivedData);
                } catch (XmlPullParserException e) {
                    Log.e(TAG, "Pull Parser failure", e);
                    toastText="Problem z parserem";
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception parsing XML", e);
                    toastText="Problem z parserem";
                }
            }
            return 0;
        }

        private int processReceivedData(XmlPullParser xmlData) throws XmlPullParserException, IOException {
            int recordsFound = 0;
            String text = "";
            String wartoscWskaznika = "";
            String nazwaWskaznika = "";
            String dataWskaznika = "";

            int eventType = -1;
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                String tagName = xmlData.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = xmlData.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagName.equals("nazwa")) {
                            nazwaWskaznika = text;
                        } else if (tagName.equals("data")) {
                            dataWskaznika = text;
                        } else if (tagName.equals("wartosc")) {
                            wartoscWskaznika = text;
                        } else if (tagName.equals("wskaznik")) {
                            recordsFound++;
                            publishProgress(nazwaWskaznika, dataWskaznika, wartoscWskaznika);
                            //Log.i(TAG, nazwaWskaznika + ", " + dataWskaznika + ", " + wartoscWskaznika);
                        }

                        break;
                }

                eventType = xmlData.next();

            }


            if (recordsFound == 0) {
                publishProgress();
            }
            Log.i(TAG, "Pobrano " + recordsFound + " rekordów.");
            toastText="Pobieranie danych zakonczono sukcesem";
            return recordsFound;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if (values.length == 0) {
                Log.i(TAG, "Nie pobrano ¿adnych danych");
            }
            if (values.length == 3) {
                String nazwaWskaznika = values[0];
                String dataWskaznika = values[1];
                String wartoscWskaznika = values[2];
                for (int i=0;i<Praca_wskazniki.zaznaczoneWskaznikiNazwa.length; i++) {
                    if (nazwaWskaznika.equalsIgnoreCase(Praca_wskazniki.zaznaczoneWskaznikiNazwa[i])){

                        Log.i(TAG, nazwaWskaznika + ": " + wartoscWskaznika + ": "+dataWskaznika);
                        wynik += nazwaWskaznika + ": " + wartoscWskaznika + "\n";

                        if (MainActivity.db.sprawdz(nazwaWskaznika,wartoscWskaznika,dataWskaznika)){
                            MainActivity.db.addIndex(new Wskaznik(nazwaWskaznika,wartoscWskaznika,dataWskaznika));
                        }

                    }
                }

                handleNewRecord(wynik);
            }

            super.onProgressUpdate(values);
        }
    }



}


