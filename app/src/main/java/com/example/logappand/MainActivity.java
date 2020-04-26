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
    private Log log;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

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
        volume = findViewById(R.id.textView9);
        mass = findViewById(R.id.textView11);

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
                System.out.println(log.getDensity());
                System.out.println(log.calculateDryMass());
                System.out.println(log.calculateTotalMass());

                volume.setText("" + Double.parseDouble(decimalFormat.format(log.calculateVolume())) + " cubic ft");
                mass.setText(""+ Double.parseDouble(decimalFormat.format(log.calculateTotalMass())) + " lbs");

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
        } else if (text.equals(moistureText)){
            Toast.makeText(getApplicationContext(), "Moisture is defaulted to 0%", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show();
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
