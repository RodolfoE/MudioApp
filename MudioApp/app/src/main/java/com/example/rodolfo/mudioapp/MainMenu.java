package com.example.rodolfo.mudioapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rodolfo.mudioapp.fragment.Dia1;
import com.example.rodolfo.mudioapp.fragment.Dia2;
import com.example.rodolfo.mudioapp.fragment.Dia3;


import org.joda.time.DateTime;

import java.util.Calendar;

/**
 * Created by Rodolfo on 22/06/2015.
 */
public class MainMenu extends ActionBarActivity {
    private int aux = 0;

    private final String DIA1 = "Dia 1";
    private final String DIA2 = "Dia 2";
    private final String DIA3 = "Dia 3";

    private long mTimeStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_menu);

        setUpActionBar();

        setFragment1(new Dia1(), DIA1);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mTimeStart = System.currentTimeMillis();
            }
        }, new IntentFilter(Intent.ACTION_SCREEN_ON));

        registerReceiver(new BroadcastReceiver() {

            public long getTime(){
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                c.add(Calendar.SECOND, 50);
                long time = c.getTimeInMillis();
                return time;
            }
            @Override
            public void onReceive(Context context, Intent intent) {
                long elapsedTime =  System.currentTimeMillis() - mTimeStart ;
                Log.d("TesteOff", elapsedTime + "");
                if (elapsedTime < 2300){
                    Handler handler = new Handler();

                    recursao(0);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator) MainMenu.this.getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                        }
                    }, 50000);



                    /*Log.d("TesteOff - inside", elapsedTime + "");
                    Intent intent1 = new Intent("Disparar");
                    PendingIntent pintent = PendingIntent.getBroadcast(MainMenu.this, 0, intent1, 0);

                    AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                    alarm.set(AlarmManager.RTC, getTime(), pintent);*/
                }

            }
        }, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        setDay();
    }

    private void recursao(int aux){
        Handler handler = new Handler();

        if (aux < 50){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainMenu.this.aux += 5;
                    Toast.makeText(MainMenu.this, MainMenu.this.aux + " seconds", Toast.LENGTH_SHORT).show();
                    recursao(MainMenu.this.aux);
                }
            }, 5000);
        } else {
            MainMenu.this.aux = 0;
        }
    }

    private void setDay(){
        DateTime today = new DateTime();

       /* Toast.makeText(this, today.dayOfWeek().getAsText(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, today.dayOfWeek().getAsText().toString()  +  " | sexta-feira = " + (today.dayOfWeek().getAsText().toString().equals("sexta-feira")), Toast.LENGTH_LONG).show();
*/

        if (today.dayOfWeek().getAsText().toString() == "segunda-feira" || today.dayOfWeek().getAsText().toString() == "quinta-feira"){
            setFragment1(new Dia2(), "dia2");
        } else if (today.dayOfWeek().getAsText().toString() == "terça-feira" || today.dayOfWeek().getAsText().toString() == "sexta-feira") {
            setFragment1(new Dia3(), "dia3");
            Log.d("teste", "Inside sexta-feira");
        } else if (today.dayOfWeek().getAsText().toString() == "quarta-feira" || today.dayOfWeek().getAsText().toString() == "sábado"){
            setFragment1(new Dia1(), "dia1");
        }
    }


    private void setFragment1(Fragment frag, String tag){
        FragmentManager m = getSupportFragmentManager();
        FragmentTransaction ft1 = m.beginTransaction();
        ft1.replace(R.id.container, frag, tag);
        ft1.commit();
    }
    private void setUpActionBar(){

        ActionBar action = getSupportActionBar();

        action.setDisplayHomeAsUpEnabled(true);
        action.setTitle("Nome da academia");
        action.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        action.addTab(action.newTab().setText(DIA1).setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                setFragment1(new Dia1(), DIA1);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        }));
        action.addTab(action.newTab().setText(DIA2).setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                setFragment1(new Dia2(), DIA2);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        }));
        action.addTab(action.newTab().setText(DIA3).setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                setFragment1(new Dia3(), DIA2);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        }));
        Button btn = (Button) findViewById(R.id.getIn);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(onsearch());
        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnQueryTextListener onsearch() {
        return new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainMenu.this, "What is done cannot be undone", Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainMenu.this, "Don't change that, boy", Toast.LENGTH_LONG).show();
                return false;
            }
        };
    }

}
