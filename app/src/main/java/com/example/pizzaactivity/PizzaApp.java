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

                // Determine base price based on pizza type and size
                int basePrice = 0;
                if (pizzaType.equals("Hawaiian")) {
                    switch (size) {
                        case "Small": basePrice = 100; break;
                        case "Medium": basePrice = 150; break;
                        case "Large": basePrice = 200; break;
                    }
                } else if (pizzaType.equals("Ham and Cheese")) {
                    switch (size) {
                        case "Small": basePrice = 200; break;
                        case "Medium": basePrice = 300; break;
                        case "Large": basePrice = 400; break;
                    }
                }

                // Crust pricing
                int crustPrice = 0;
                if (crust.equals("Thick")) {
                    crustPrice = (int)(basePrice * 0.5); // 50% extra for thick crust
                }

                // Toppings
                StringBuilder toppings = new StringBuilder();
                int toppingCost = 0;

                if (cbExtraCheese.isChecked()) {
                    toppings.append("Extra Cheese, ");
                    toppingCost += 20;
                }
                if (cbMushrooms.isChecked()) {
                    toppings.append("Mushrooms, ");
                    toppingCost += 20;
                }
                if (cbOnions.isChecked()) {
                    toppings.append("Onions, ");
                    toppingCost += 10;
                }
                if (cbTomatoes.isChecked()) {
                    toppings.append("Tomatoes, ");
                    toppingCost += 10;
                }
                if (cbPineapple.isChecked()) {
                    toppings.append("Pineapple, ");
                    toppingCost += 15;
                }

                if (toppings.length() > 0) {
                    toppings.setLength(toppings.length() - 2); // Remove last comma and space
                }

                int totalPrice = basePrice + crustPrice + toppingCost;

                String summary = "You ordered a " + size + " " + pizzaType + " Pizza\n"
                        + "Crust: " + crust + "\n"
                        + "Toppings: " + (toppings.length() > 0 ? toppings.toString() : "None") + "\n"
                        + "Total Price: ₱" + totalPrice;

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
