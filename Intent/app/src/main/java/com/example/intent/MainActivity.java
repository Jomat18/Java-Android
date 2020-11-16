package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    private static ArrayList<String> planetList = new ArrayList<String>();

    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainListView = (ListView) findViewById( R.id.mainListView );

        if (savedInstanceState != null) {
            planetList = savedInstanceState.getStringArrayList("myKey");
            listAdapter = new ArrayAdapter<String>(this, R.layout.listtext, planetList);
            mainListView.setAdapter( listAdapter );
        }


    }

    public void Siguiente(View view){
        Intent siguiente= new Intent(this, SegundoActivity.class);
        //startActivity(siguiente);
        startActivityForResult(siguiente, TEXT_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedState) {

        super.onSaveInstanceState(savedState);
        ArrayList<String> values = new ArrayList<String>();

        // Note: getValues() is a method in your ArrayAdapter subclass
        for(int i=0 ; i<listAdapter.getCount() ; i++){
            values.add(listAdapter.getItem(i));
        }

        savedState.putStringArrayList("myKey", values);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {

                listAdapter = new ArrayAdapter<String>(this, R.layout.listtext, planetList);

                Bundle extras = data.getExtras();
                if (extras != null) {
                    String producto = data.getStringExtra(SegundoActivity.EXTRA_REPLY);
                    planetList.add(producto);
                    listAdapter.notifyDataSetChanged();
                    mainListView.setAdapter( listAdapter );
                }

            }
        }
    }

}