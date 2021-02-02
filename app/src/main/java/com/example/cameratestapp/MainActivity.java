package com.example.cameratestapp;
// I am aware the name of this package name has nothing to do with the app, I was going to do something else //
// Please note that this application was built for practice purposes for myself, and therefore does not work in landscape mode//
// Alexandra Coates //
// 02/02/21 //

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    EditText editTextAge;
    CheckBox checkBox;
    TextView textViewPrice;
    EditText leftNumber;
    EditText rightNumber;
    TextView symbolChosen, textViewAnswer;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // When doing ICA don't use default names like this //
        editText = (EditText) findViewById(R.id.editTextTextPersonName);
        textView = (TextView) findViewById(R.id.textView);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        textViewPrice = (TextView) findViewById(R.id.showPrice);
        leftNumber = (EditText) findViewById(R.id.leftNumber);
        rightNumber = (EditText) findViewById(R.id.rightNumber);
        symbolChosen = (TextView) findViewById(R.id.symbolChosen);
        textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);

    }
    // For the button "COPY"; copies the text entered and displays it //
    public void onButtonClick(View view){
        textView.setText(editText.getText());
    }
    // For the button "Price?" calculates the price using an if statement and displays it //
    public void priceButtonClick(View view){
        int age = Integer.parseInt(editTextAge.getText().toString());
        boolean isSpecialShowing = checkBox.isChecked();
        double price;
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        if((age < 18 ||65 <= age) && !isSpecialShowing){
            price = 7.00;
        }else{
            price = 10.00;
        }textViewPrice.setText(currency.format(price));
    }

    // For the buttons with the symbols on them, calculates the answer using a switch statement //
    public void calcButtonClick(View view){
        double numberLeft =
                Double.parseDouble(leftNumber.getText().toString());
        double numberRight =
                Double.parseDouble(rightNumber.getText().toString());
        String operatorSymbol = "";
        double result;

        switch (view.getId()){
            case R.id.buttonAdd:
                operatorSymbol = "+";
                result = numberLeft + numberRight;
                break;
            case R.id.buttonMinus:
                operatorSymbol = "-";
                result = numberLeft - numberRight;
                break;
            case R.id.buttonTimes:
                operatorSymbol = "*";
                result = numberLeft * numberRight;
                break;
            case R.id.buttonDivide:
                operatorSymbol = "/";
                result = numberLeft / numberRight;
                break;
            default:
                operatorSymbol = "?";
                result = 0;
                break;

        }
        symbolChosen.setText(operatorSymbol);

        // Changing the unhelpful infinite and NaN messages //
        if (Double.isInfinite(result) || Double.isNaN(result)){
            textViewAnswer.setText("Bad Value");
        }else{
            textViewAnswer.setText(Double.toString(result));
        }
    }

    public void launchSecondActivity(View view){
        Log.d(LOG_TAG, "Button Clicked");
        Intent intent = new Intent(this, activityNext.class);
        startActivity(intent);
    }
}
