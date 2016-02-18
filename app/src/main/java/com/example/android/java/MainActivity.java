package com.example.android.java;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
int q=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    String msg;
    int total;

    public void submitOrder(View view) {
        int amt=5;
        String priceMeaasge;
        EditText newedit = (EditText) findViewById(R.id.edit_text_view);
        msg = newedit.getText().toString();
        if(msg.trim().equals(""))
        {
            newedit.setError("Name reqiured");
           // newedit.setHint("Name please");
        }else {
            if(a1==true)
                amt+=+1;
            if(a2==true)
                amt+=2;
            total=q*amt;
             priceMeaasge = msg + " !!! \nPrice: $" + total + " Only\nWhipped cream ? : " + a1 + "\nChoco Cream? : " + a2 + "\nThank You";
            displayMessage(priceMeaasge);

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/r822");
            intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order");
            intent.putExtra(Intent.EXTRA_TEXT, priceMeaasge);
            if(intent.resolveActivity(getPackageManager())!=null)
                startActivity(intent);
            //displayPrice(q*5);
        }

    }



    public void increment(View view) {
        q++;
        if(q>100)
        {   q=100;
            Context con = getApplicationContext();
            int duration= Toast.LENGTH_SHORT;
            Toast inc = Toast.makeText(con,"CANT EXCEED 100",duration);
            inc.show();
            return;
        }
        display(q);
    }

    public void decrement(View view)
    {
        q--;
        if(q<0) {
            q=0;
            Context con = getApplicationContext();
            int duration= Toast.LENGTH_SHORT;
            Toast inc = Toast.makeText(con,"CANT BE BELOW 0",duration);
            inc.show();
            return;
        }
            display(q);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    boolean a1=false,a2=false;
    public void ischecked(View v)
    {   boolean a;
        a=((CheckBox) v).isChecked();
        switch (v.getId())
        {
            case R.id.whip_text_view :
            {
                a1=a;
                break;
            }

            case R.id.choco_text_view:
            {
                a2=a;
                break;
            }
        }
    }

}