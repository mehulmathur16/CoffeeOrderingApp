package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {


        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        EditText nameField = (EditText) findViewById(R.id.nameField);
        String name = nameField.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String str = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT , "Just Java Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT , str);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        //displayMessage(str);
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int price = quantity * 5;

        if (hasWhippedCream == true)
            price += quantity * 1;

        if (hasChocolate == true)
            price += quantity * 2;

        return price;
    }

    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {

        return "Name : " + name + "\nAdd whipped cream? " + hasWhippedCream + "\nAdd chocolate? " + hasChocolate + "\nQuantity : " + quantity + "\nTotal : $" + price + "\nThank You!";

    }

    public void increment(View view) {

        if (quantity == 100) {
            Toast.makeText(getBaseContext(), "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity++;
        displayQuantity(quantity);


    }

    public void decrement(View view) {


        if (quantity == 1) {

            Toast.makeText(getBaseContext(), "You should have atleast 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);


    }

    private void displayQuantity(int number) {
        TextView QuantityTextView = (TextView) findViewById(R.id.Quantity_text_view);
        QuantityTextView.setText("" + number);
    }

}

