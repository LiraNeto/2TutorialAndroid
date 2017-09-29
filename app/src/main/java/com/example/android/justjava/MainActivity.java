package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private int quantity = 1;

    private final float pricePerCup = 5f,
                        chocolatePricePerCup = 2f,
                        whippedCreamPricePerCup = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.displayQuantity();

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String message = this.createOrderSummary ();

        String [] address = new String[20];

        address[0] = "xandsagro@hotmail.com";

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                                    this.getString(R.string.order_summary_email_subject,
                                    this.getName()));
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        if (emailIntent.resolveActivity(getPackageManager()) != null)
            startActivity(emailIntent);

    }

    private String createOrderSummary () {
        String message = "",
                priceFormated = NumberFormat.getCurrencyInstance().format(this.calculatePrice());;

        message += this.getString(R.string.order_summary_name,this.getName());

        message += this.checkToppingsMessage();

        message += "\n" + this.getString(R.string.order_summary_quantity, this.quantity);
        message += "\n" + this.getString(R.string.order_summary_price, priceFormated);
        message += "\n" + this.getString(R.string.thank_you);

        return message;
    }

    private String getName () {
        EditText name = (EditText) this.findViewById(R.id.name_edittext);

        return name.getText().toString();
    }

    private String checkToppingsMessage () {
        CheckBox whippedCream = (CheckBox) this.findViewById(R.id.whipped_cream_checkbox),
                chocolate = (CheckBox) this.findViewById(R.id.chocolate_checkbox);
        String message = "";

        if (whippedCream.isChecked())
            message += "\n" + this.getString(R.string.order_summary_whipped_cream,
                                this.getString(R.string.yes));
        else
            message += "\n" + this.getString(R.string.order_summary_whipped_cream,
        this.getString(R.string.no));

        if (chocolate.isChecked())
            message += "\n" + this.getString(R.string.order_summary_chocolate,
                    this.getString(R.string.yes));
        else
            message += "\n" + this.getString(R.string.order_summary_chocolate,
                    this.getString(R.string.no));

        return message;
    }

    private float checkToppingsPrice () {
        CheckBox whipped_cream = (CheckBox) this.findViewById(R.id.whipped_cream_checkbox),
                chocolate = (CheckBox) this.findViewById(R.id.chocolate_checkbox);
        float price = 0f;


        if (whipped_cream.isChecked())
            price += this.whippedCreamPricePerCup;

        if (chocolate.isChecked())
            price += this.chocolatePricePerCup;


        return price;
    }

    /**
     * This method is called when the plus button is clicked and increment the quantity
     * @param view
     */

    public void incrementQuantity(View view){
        if (this.quantity < 100)
            this.quantity ++;
        else
            this.quantity = 100;

        this.displayQuantity();
    }

    /**
     * This method is called when the minus button is clicked and decrement the quantity
     * @param view
     */

    public void decrementQuantity(View view){
        if (this.quantity > 1)
            this.quantity --;
        else
            this.quantity = 1;

        this.displayQuantity();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity () {
        TextView quantityTextView = (TextView) this.findViewById(R.id.quantity_number_textview);
        quantityTextView.setText("" + this.quantity);
    }


    private float calculatePrice() {

        return this.quantity * (this.pricePerCup + this.checkToppingsPrice());
    }

}
