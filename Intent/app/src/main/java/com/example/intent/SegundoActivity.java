package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class SegundoActivity extends AppCompatActivity {
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;

    public static final String EXTRA_REPLY =
            "com.example.intent.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        mainListView = (ListView) findViewById( R.id.mainListView2 );

        // Create and populate a List of planet names.
        String[] planets = new String[] { "Manzana", "Arroz", "Queso", "Azucar"};
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.listbutton, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        //listAdapter.add( "Leche" );

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );

    }

    public void Anterior(View view){
        Button buton = (Button)view;
        String producto = buton.getText().toString();

        Intent anterior= new Intent(this, MainActivity.class);
        anterior.putExtra(EXTRA_REPLY, producto);
        //startActivity(anterior);
        setResult(RESULT_OK, anterior);
        //anterior.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        //super.onBackPressed();
    }

}