package com.example.logappand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private String speciesText;
    private Button button;
    private EditText smallDiameterText, largeDiameterText, lengthText, moistureText;
    private TextView volume, mass;
    private ImageView diagram;
    private Log log;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Credit to https://www.spikevm.com/calculators/logging/huber-formula.php
        diagram = findViewById(R.id.imageView);

        log = new Log();

        addSpinnerItems();
        button = findViewById(R.id.button);
        smallDiameterText = findViewById(R.id.editText2);
        largeDiameterText = findViewById(R.id.editText3);
        lengthText = findViewById(R.id.editText4);
        moistureText = findViewById(R.id.editText5);
        volume = findViewById(R.id.textView9);
        mass = findViewById(R.id.textView11);


        //Highlight different parts of the diagram
        smallDiameterText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                diagram.setImageResource(R.drawable.small);
            }
        });

        largeDiameterText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                diagram.setImageResource(R.drawable.large);
            }
        });

        lengthText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                diagram.setImageResource(R.drawable.length);
            }
        });

        moistureText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                diagram.setImageResource(R.drawable.huber);
            }
        });

        //Calculate + present
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diagram.setImageResource(R.drawable.huber);
                log.setSmallDiameter(checkValueValidity(smallDiameterText));
                log.setLargeDiameter(checkValueValidity(largeDiameterText));
                log.setLength(checkValueValidity(lengthText));
                log.setMoisture(checkValueValidity(moistureText));
                log.setSpecies(speciesText);

                System.out.println(log.calculateVolume());

                System.out.println(log.toString());
                System.out.println(log.getDensity());
                System.out.println(log.calculateDryMass());
                System.out.println(log.calculateTotalMass());

                volume.setText("" + Double.parseDouble(decimalFormat.format(log.calculateVolume())) + " cubic ft");
                mass.setText(""+ Double.parseDouble(decimalFormat.format(log.calculateTotalMass())) + " lbs");

            }
        });
    }

    //Make sure value isnt empty
    private double checkValueValidity(EditText text){
        double value = 0;
        String s =text.getText().toString();
        if(!s.isEmpty() && !s.trim().equals("")) {
            try {
                value = Double.parseDouble(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (text.equals(moistureText)){
            Toast.makeText(getApplicationContext(), "Moisture is defaulted to 0%", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show();
        }

        return value;
    }

    //Setup spinner for species
    private void addSpinnerItems() {
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.species_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                diagram.setImageResource(R.drawable.huber);
                speciesText = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, speciesText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
