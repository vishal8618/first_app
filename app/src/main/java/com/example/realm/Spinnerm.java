package com.example.realm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.realm.m_realm.RealmHelper;
import com.example.realm.m_realm.Spacecraft;

import java.util.ArrayList;

import io.realm.RealmConfiguration;

public class Spinnerm extends AppCompatActivity {
    Realm realm;
    ArrayList<String> spacecrafts;
    ArrayAdapter adapter;
    Spinner sp;
    EditText nameEdiText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_spinnerm );


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        sp= (Spinner) findViewById(R.id.sp);
//REALM CONFIGURATION
        Realm.init(getApplicationContext());
        RealmConfiguration config=new RealmConfiguration.Builder()
               .build();
        realm=Realm.getInstance(config);
        RealmHelper helper=new RealmHelper(realm);
        spacecrafts=helper.retrieve();
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,spacecrafts);
        sp.setAdapter(adapter);
sp.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
        Toast.makeText(Spinnerm.this,spacecrafts.get(position),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView <?> parent) {

    }
} );

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
            }
        });
    }

    private void displayInputDialog() {

        Dialog d=new Dialog(this);
        d.setTitle("save To Realm");
        d.setContentView(R.layout.activity_spinner);
        nameEdiText= (EditText) d.findViewById(R.id.nameEditText);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spacecraft s=new Spacecraft();
                s.setName(nameEdiText.getText().toString());
                RealmHelper helper=new RealmHelper(realm);
                helper.save(s);
                nameEdiText.setText("");
                spacecrafts=helper.retrieve();
                adapter=new ArrayAdapter(Spinnerm.this,android.R.layout.simple_list_item_1,spacecrafts);
                sp.setAdapter(adapter);
            }
        });
        d.show();



    }

}
