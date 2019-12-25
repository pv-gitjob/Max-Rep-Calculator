package com.praveenv.maxrepcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView poundsText = findViewById(R.id.poundsTextView);
        final TextView kiloramsText = findViewById(R.id.kilogramsTextView);
        final Resources res = getResources();

        final Switch unitsSwitch = findViewById(R.id.unitsSwitch);
        unitsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    kiloramsText.setTextColor(res.getColor(R.color.colorAccent));
                    poundsText.setTextColor(res.getColor(R.color.colorPrimary));
                } else {
                    poundsText.setTextColor(res.getColor(R.color.colorAccent));
                    kiloramsText.setTextColor(res.getColor(R.color.colorPrimary));
                }
            }
        });

        Button calcBtn = findViewById(R.id.calcButton);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText weightText = findViewById(R.id.weightEditText);
                EditText repText = findViewById(R.id.repEditText);
                TextView maxTextView = findViewById(R.id.maxTextView);
                String temp = "";
                temp = weightText.getText().toString();
                if (temp.equals("")) {
                    weightText.setText("0");
                    temp = "0";
                }
                int weight = Integer.parseInt(temp);
                if (!unitsSwitch.isChecked()) {
                    weight = (int)Math.round(weight/2.205);
                }
                temp = repText.getText().toString();
                if (temp.equals("")) {
                    repText.setText("0");
                    temp = "0";
                }
                int rep = Integer.parseInt(temp);
                int max = (int)Math.round(weight / (1.0278 - 0.0278 * rep));
                if (!unitsSwitch.isChecked()) {
                    max = (int)Math.round(max*2.205);
                }
                if (max < weight) {
                    max = weight + (int)Math.round((weight * rep)/10);
                }
                String result = Integer.toString(max);
                maxTextView.setText(result);
            }
        });
    }
}
