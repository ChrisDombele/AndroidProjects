
package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    //list of all the checkboxes
//    CheckBox whippedCream = (CheckBox) findViewById(R.id.chk_whippedCream);
//    CheckBox chocolate = (CheckBox) findViewById(R.id.chk_chocolate);
//    CheckBox caramel = (CheckBox) findViewById(R.id.chk_caramel);
//    CheckBox nuts = (CheckBox) findViewById(R.id.chk_nuts);
//    CheckBox oreo = (CheckBox) findViewById(R.id.chk_oreo);
//    CheckBox marshmallow = (CheckBox) findViewById(R.id.chk_marshmallow);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = null;
        String name = null;

        EditText nameText = (EditText) findViewById(R.id.name_field);
        name = nameText.getText().toString();


        CheckBox whippedCream = (CheckBox) findViewById(R.id.chk_whippedCream);
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chk_chocolate);
        boolean hasChocolate = chocolate.isChecked();

        CheckBox caramel = (CheckBox) findViewById(R.id.chk_caramel);
        boolean hasCaramel = caramel.isChecked();

        CheckBox nuts = (CheckBox) findViewById(R.id.chk_nuts);
        boolean hasNuts = nuts.isChecked();

        CheckBox oreo = (CheckBox) findViewById(R.id.chk_oreo);
        boolean hasOreo = oreo.isChecked();

        CheckBox marshmallow = (CheckBox) findViewById(R.id.chk_marshmallow);
        boolean hasMarshmallow = marshmallow.isChecked();


        if (quantity == 0) {
            priceMessage = "Empty" + "\nPrice: Free";
            TextView priceTextMessageView = (TextView) findViewById(R.id.price_text_message_view);
            displayMessage(priceMessage);
            whippedCream.setChecked(false);
            displayQuantity(0);
            nameText.setText("");
        } else {
            int total = 0;
            total = calculatePrice(hasWhippedCream, hasChocolate, hasCaramel, hasNuts, hasOreo, hasMarshmallow);
            whippedCream.setChecked(false);
            //add more setChecked false for other toppings
            priceMessage = orderSummary(total, hasWhippedCream, name);// add the rest of the toppings
            displayMessage(priceMessage);
            displayQuantity(0);
            nameText.setText("");
        }

        //sends email - this is an implicit intent because of the ACTION_SENDTO - which is not speciffic
        String addresses = "chrisdombele@gmail.com";
        String subject = "Order Summary";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //either add this to not display on the app in the orderSummary or remove line: 79 and 69
//        TextView summaryText = (TextView) findViewById(R.id.price_text_message_view);
//        summaryText.setText("");

//        to display a quick message
//        Toast.makeText(this,"hi", Toast.LENGTH_SHORT).show();
    }

    public String orderSummary(int total, boolean chkWhippedCream, String name) {
        String summary = "" + name;
        summary+= "\nNumber of orders: " + quantity;
        if (!chkWhippedCream) {
            summary+= "\nToppings: None";
        } else {
            summary+= "\nToppings: ";
        }
        if (chkWhippedCream) {
            summary+= "\nWhipped Cream";
        }
        //add the rest of the toppings
        summary += "\nPrice: " + NumberFormat.getCurrencyInstance().format(total);
        return summary;
    }


    /**
     *
     * @increment
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this,"You can not more than 100 orders.", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(++quantity);
    }

    /**
     *
     * @param view
     */
    public void decrement(View view) {
        if (quantity == 0) {
            Toast.makeText(this,"You can not have less than 0 orders.", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(--quantity);
    }

    /**
     *
     * @param view
     */
    public void reset(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.chk_whippedCream);
        EditText nameText = (EditText) findViewById(R.id.name_field);

        displayQuantity(0);
        TextView summaryText = (TextView) findViewById(R.id.price_text_message_view);
        summaryText.setText("");
        whippedCream.setChecked(false);
        nameText.setText("");
        quantity = 0;
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate, boolean hasCaramel,
                               boolean hasNuts, boolean hasOreo, boolean hasMarshmallow) {
        int basePrice = quantity * 5;

        if (hasWhippedCream) {
            basePrice+= 1;
        }
        if (hasChocolate) {
            basePrice+= 2;
        }
        if (hasCaramel) {
            basePrice+= 3;
        }
        if (hasNuts) {
            basePrice+= 4;
        }
        if (hasOreo) {
            basePrice+= 5;
        }
        if (hasMarshmallow) {
            basePrice+= 6;
        }

        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextMessageView = (TextView) findViewById(R.id.price_text_message_view);
        priceTextMessageView.setText(message);

        //why does this work? is it because you already foundbyid in submitorder method?
//        TextView priceTextView = new TextView(this);
//        priceTextView.setText(message);
    }
}