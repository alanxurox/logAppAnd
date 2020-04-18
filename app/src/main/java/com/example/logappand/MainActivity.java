package com.example.logappand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private String speciesText;
    private Button button;
    private EditText smallDiameterText, largeDiameterText, lengthText, moistureText;
    private Log log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = new Log();

        addSpinnerItems();
        button = findViewById(R.id.button);
        smallDiameterText = findViewById(R.id.editText2);
        largeDiameterText = findViewById(R.id.editText3);
        lengthText = findViewById(R.id.editText4);
        moistureText = findViewById(R.id.editText5);

        //android.util.Log.d("Species Text", speciesText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log.setSmallDiameter(checkValueValidity(smallDiameterText));
                log.setLargeDiameter(checkValueValidity(largeDiameterText));
                log.setLength(checkValueValidity(lengthText));
                log.setMoisture(checkValueValidity(moistureText));
                log.setSpecies(speciesText);

                //Volume is working! But how do I get Math.PI working?>?????
                System.out.println(log.calculateVolume());
                System.out.println(log.toString());
                //log.calculateWeight();
            }
        });
    }

    private double checkValueValidity(EditText text){
        double value = 0;
        String s =text.getText().toString();
        if(!s.isEmpty() && !s.trim().equals("")) {
            try {
                value = Double.parseDouble(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "The " + text.toString() + " field is empty!", Toast.LENGTH_SHORT).show();
        }

        return value;
    }


    private void addSpinnerItems() {
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.species_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                speciesText = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, speciesText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
