package com.example.pizzaactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class PizzaApp extends AppCompatActivity {

    Spinner spinnerPizza;
    RadioGroup radioGroupSize, radioGroupCrust;
    CheckBox checkboxPepperoni, checkboxMushrooms, checkboxOnions, checkboxBacon;
    Button btnProcess, btnClear;
    TextView txtOrderSummary, txtPriceBreakdown, txtTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerPizza = findViewById(R.id.spinnerPizza);
        radioGroupSize = findViewById(R.id.radioGroupSize);
        radioGroupCrust = findViewById(R.id.radioGroupCrust);
        checkboxPepperoni = findViewById(R.id.checkboxPepperoni);
        checkboxMushrooms = findViewById(R.id.checkboxMushrooms);
        checkboxOnions = findViewById(R.id.checkboxOnions);
        checkboxBacon = findViewById(R.id.checkboxBacon);
        btnProcess = findViewById(R.id.btnProcess);
        btnClear = findViewById(R.id.btnClear);
        txtOrderSummary = findViewById(R.id.txtOrderSummary);
        txtPriceBreakdown = findViewById(R.id.txtPriceBreakdown);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pizza_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPizza.setAdapter(adapter);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pizzaType = spinnerPizza.getSelectedItem().toString();
                String size = getSelectedRadioText(radioGroupSize);
                String crust = getSelectedRadioText(radioGroupCrust);
                String toppings = "";

                double price = 0;

                // Base price by size
                switch (size) {
                    case "Small": price += 200; break;
                    case "Medium": price += 300; break;
                    case "Large": price += 400; break;
                }

                // Crust price
                switch (crust) {
                    case "Thin": price += 0; break;
                    case "Thick": price += 20; break;
                    case "Cheese Stuffed": price += 40; break;
                }

                StringBuilder toppingList = new StringBuilder();
                double toppingCost = 0;

                if (checkboxPepperoni.isChecked()) {
                    toppingList.append("Pepperoni, ");
                    toppingCost += 25;
                }
                if (checkboxMushrooms.isChecked()) {
                    toppingList.append("Mushrooms, ");
                    toppingCost += 15;
                }
                if (checkboxOnions.isChecked()) {
                    toppingList.append("Onions, ");
                    toppingCost += 10;
                }
                if (checkboxBacon.isChecked()) {
                    toppingList.append("Bacon, ");
                    toppingCost += 30;
                }

                if (toppingList.length() > 0)
                    toppings = toppingList.substring(0, toppingList.length() - 2); // Remove last comma

                price += toppingCost;

                String summary = "You ordered a " + size + " " + pizzaType + " pizza\n"
                        + "Crust: " + crust + "\n"
                        + "Toppings: " + (toppings.isEmpty() ? "None" : toppings);

                txtOrderSummary.setText(summary);

                txtPriceBreakdown.setText("Size: ₱" + (int) (price - toppingCost) +
                        "\nToppings: ₱" + (int) toppingCost +
                        "\nCrust: included");

                txtTotalPrice.setText("Total: ₱" + (int) price);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerPizza.setSelection(0);
                radioGroupSize.clearCheck();
                radioGroupCrust.clearCheck();
                checkboxPepperoni.setChecked(false);
                checkboxMushrooms.setChecked(false);
                checkboxOnions.setChecked(false);
                checkboxBacon.setChecked(false);
                txtOrderSummary.setText("");
                txtPriceBreakdown.setText("Price Breakdown:");
                txtTotalPrice.setText("Total: ₱0");
            }
        });
    }

    private String getSelectedRadioText(RadioGroup group) {
        int selectedId = group.getCheckedRadioButtonId();
        if (selectedId == -1) return "";
        RadioButton radioButton = findViewById(selectedId);
        return radioButton.getText().toString();
    }
}
