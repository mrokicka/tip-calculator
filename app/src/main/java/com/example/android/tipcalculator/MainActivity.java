package com.example.android.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText billEdit;
    private EditText dinerEdit;
    private SeekBar tipPercent;
    private TextView tipAmount;
    private TextView oweAmount;
    private TextView progressBox;
    private CheckBox split;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBox = findViewById(R.id.progress);
        billEdit = findViewById(R.id.bill_edit);
        dinerEdit = findViewById(R.id.diner_edit);
        tipPercent = findViewById(R.id.seek);
        tipPercent.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressBox.setText(progress + "");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        tipAmount = findViewById(R.id.tip_amountEdit);
        oweAmount = findViewById(R.id.owe_amountEdit);
        split = findViewById(R.id.checkbox);
    }

    public void buttonPressed(View v) {
        double price;
        if(billEdit.getText().toString().equals("")) {
            price = 0;
            billEdit.setText("0");
        } else {
            price = Double.parseDouble(billEdit.getText().toString());
        }

        int diners;
        if(dinerEdit.getText().toString().equals("") || Integer.parseInt(dinerEdit.getText().toString()) == 0) {
            diners = 1;
            dinerEdit.setText("1");
        } else {
            diners = Integer.parseInt(dinerEdit.getText().toString());
        }

        int percent = tipPercent.getProgress();

        double tip = price * percent / 100;
        double result;
        if(split.isChecked()) {
            result = (price + tip) / diners;
        } else {
            result = (price + tip);
        }

        tipAmount.setText("$" + String.format("%.2f", tip));
        oweAmount.setText("$" + String.format("%.2f", result));
    }
}
