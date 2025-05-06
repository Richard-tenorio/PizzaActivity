package com.example.pizzaactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class PizzaApp extends AppCompatActivity {

    RadioButton rbHawaiian, rbHamCheese;
    RadioButton rbSmall, rbMedium, rbLarge;
    RadioButton rbThin, rbThick;
    CheckBox cbExtraCheese, cbMushrooms, cbOnions, cbTomatoes, cbPineapple;
    Button btnProcess, btnNewOrder;
    TextView txtOrderSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbHawaiian = findViewById(R.id.rbHawaiian);
        rbHamCheese = findViewById(R.id.rbHamCheese);

        rbSmall = findViewById(R.id.rbSmall);
        rbMedium = findViewById(R.id.rbMedium);
        rbLarge = findViewById(R.id.rbLarge);

        rbThin = findViewById(R.id.rbThin);
        rbThick = findViewById(R.id.rbThick);

        cbExtraCheese = findViewById(R.id.cbExtraCheese);
        cbMushrooms = findViewById(R.id.cbMushrooms);
        cbOnions = findViewById(R.id.cbOnions);
        cbTomatoes = findViewById(R.id.cbTomatoes);
        cbPineapple = findViewById(R.id.cbPineapple);

        btnProcess = findViewById(R.id.btnProcess);
        btnNewOrder = findViewById(R.id.btnNewOrder);

        txtOrderSummary = findViewById(R.id.txtOrderSummary);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pizzaType = rbHawaiian.isChecked() ? "Hawaiian" :
                        rbHamCheese.isChecked() ? "Ham and Cheese" : "Unknown";

                String size = rbSmall.isChecked() ? "Small" :
                        rbMedium.isChecked() ? "Medium" :
                                rbLarge.isChecked() ? "Large" : "Unknown";

                String crust = rbThin.isChecked() ? "Thin" :
                        rbThick.isChecked() ? "Thick" : "Unknown";

                int price = 0;
                switch (size) {
                    case "Small": price += 200; break;
                    case "Medium": price += 300; break;
                    case "Large": price += 400; break;
                }

                if (crust.equals("Thick")) {
                    price += 20;
                }

                StringBuilder toppings = new StringBuilder();
                int toppingCost = 0;

                if (cbExtraCheese.isChecked()) {
                    toppings.append("Extra Cheese, ");
                    toppingCost += 20;
                }
                if (cbMushrooms.isChecked()) {
                    toppings.append("Mushrooms, ");
                    toppingCost += 15;
                }
                if (cbOnions.isChecked()) {
                    toppings.append("Onions, ");
                    toppingCost += 10;
                }
                if (cbTomatoes.isChecked()) {
                    toppings.append("Tomatoes, ");
                    toppingCost += 12;
                }
                if (cbPineapple.isChecked()) {
                    toppings.append("Pineapple, ");
                    toppingCost += 18;
                }

                if (toppings.length() > 0) {
                    toppings.setLength(toppings.length() - 2); // Remove last comma
                }

                price += toppingCost;

                String summary = "You ordered a " + size + " " + pizzaType + " Pizza\n"
                        + "Crust: " + crust + "\n"
                        + "Toppings: " + (toppings.length() > 0 ? toppings.toString() : "None") + "\n"
                        + "Total Price: ₱" + price;

                txtOrderSummary.setText(summary);
            }
        });

        btnNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbHawaiian.setChecked(false);
                rbHamCheese.setChecked(false);

                rbSmall.setChecked(false);
                rbMedium.setChecked(false);
                rbLarge.setChecked(false);

                rbThin.setChecked(false);
                rbThick.setChecked(false);

                cbExtraCheese.setChecked(false);
                cbMushrooms.setChecked(false);
                cbOnions.setChecked(false);
                cbTomatoes.setChecked(false);
                cbPineapple.setChecked(false);

                txtOrderSummary.setText("");
            }
        });
    }
}
