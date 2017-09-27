package com.example.android.justjava;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.displayQuantity(this.quantity);
        this.displayPrice(this.quantity*5);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = "Free";
        this.displayPrice(priceMessage);
    }

    /**
     * This method is called when the plus button is clicked and increment the quantity
     * @param view
     */

    public void incrementQuantity(View view){
        this.quantity ++;

        this.displayQuantity(this.quantity);
        this.displayPrice(this.quantity);
    }

    /**
     * This method is called when the minus button is clicked and decrement the quantity
     * @param view
     */

    public void decrementQuantity(View view){
        if (this.quantity > 0)
            this.quantity --;
        else
            this.quantity = 0;

        this.displayQuantity(this.quantity);
        this.displayPrice(this.quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity (int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_number_textview);
        quantityTextView.setText("" + number);
    }

    /**
     * This method display the given value on the screen.
     */

    private void displayPrice (int number) {
        float price = 2.5f;
        TextView priceTextView = (TextView) findViewById(R.id.price_number_textview);
        priceTextView.setText("Total: " + NumberFormat.getCurrencyInstance().format(number * price) + "\nThank you!");
    }



    private void displayPrice (String message){
        TextView priceTextView = (TextView) findViewById(R.id.price_number_textview);
        priceTextView.setText(message);
    }

}
