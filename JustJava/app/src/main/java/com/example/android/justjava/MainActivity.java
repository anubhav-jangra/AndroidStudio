package com.example.android.justjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if(quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity <= 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
    }

    public int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){
        int basePrice = 5;
        if(hasChocolate){
            basePrice += 2;
        }
        if(hasWhippedCream){
            basePrice += 1;
        }
        return quantity*basePrice;
    }

    public void submitOrder(View view) {
        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox ChocolateCheckBok = (CheckBox) findViewById(R.id.chocolate_cream_checkbox);
        boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();
        boolean hasChocolate = ChocolateCheckBok.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        EditText nameInputText = (EditText) findViewById(R.id.name_text_input);
        String name = nameInputText.getText().toString();
        String priceMessage = createOrederSummary(price, hasWhippedCream, hasChocolate, name);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        String from_email = "emailofanubhav@gmail.com";
        String subject = "JustJava order for " + name;
        intent.putExtra(Intent.EXTRA_EMAIL, from_email);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    public String createOrederSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name){
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\nAdd whipped cream? " +hasWhippedCream;
        priceMessage += "\nAdd whipped cream? " +hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: " + price;
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;

    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
}
