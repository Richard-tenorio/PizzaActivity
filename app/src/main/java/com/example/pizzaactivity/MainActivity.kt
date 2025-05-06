package com.example.pizzaactivity

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val rootView: View = findViewById(android.R.id.content)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rbHawaiian = findViewById<RadioButton>(R.id.rbHawaiian)
        val rbHamCheese = findViewById<RadioButton>(R.id.rbHamCheese)

        val rbSmall = findViewById<RadioButton>(R.id.rbSmall)
        val rbMedium = findViewById<RadioButton>(R.id.rbMedium)
        val rbLarge = findViewById<RadioButton>(R.id.rbLarge)

        val rbThin = findViewById<RadioButton>(R.id.rbThin)
        val rbThick = findViewById<RadioButton>(R.id.rbThick)

        val cbExtraCheese = findViewById<CheckBox>(R.id.cbExtraCheese)
        val cbMushrooms = findViewById<CheckBox>(R.id.cbMushrooms)
        val cbOnions = findViewById<CheckBox>(R.id.cbOnions)
        val cbTomatoes = findViewById<CheckBox>(R.id.cbTomatoes)
        val cbPineapple = findViewById<CheckBox>(R.id.cbPineapple)

        val btnProcess = findViewById<Button>(R.id.btnProcess)
        val btnNewOrder = findViewById<Button>(R.id.btnNewOrder)

        val txtSummary = findViewById<TextView>(R.id.txtOrderSummary)

        btnProcess.setOnClickListener {
            val pizzaType = when {
                rbHawaiian.isChecked -> "Hawaiian"
                rbHamCheese.isChecked -> "Ham and Cheese"
                else -> "Unknown"
            }

            val size = when {
                rbSmall.isChecked -> "Small"
                rbMedium.isChecked -> "Medium"
                rbLarge.isChecked -> "Large"
                else -> "Unknown"
            }

            val crust = when {
                rbThin.isChecked -> "Thin"
                rbThick.isChecked -> "Thick"
                else -> "Unknown"
            }

            // Updated base price based on pizza type and size
            val basePrice = when (pizzaType) {
                "Hawaiian" -> when (size) {
                    "Small" -> 100
                    "Medium" -> 150
                    "Large" -> 200
                    else -> 0
                }
                "Ham and Cheese" -> when (size) {
                    "Small" -> 200
                    "Medium" -> 300
                    "Large" -> 400
                    else -> 0
                }
                else -> 0
            }

            // Updated crust pricing (50% of base price for Thick)
            val crustPrice = when (crust) {
                "Thick" -> (basePrice * 0.5).toInt()
                else -> 0
            }

            val toppings = mutableListOf<String>()
            var toppingCost = 0

            if (cbExtraCheese.isChecked) { toppings.add("Extra Cheese"); toppingCost += 20 }
            if (cbMushrooms.isChecked) { toppings.add("Mushrooms"); toppingCost += 20 }
            if (cbOnions.isChecked) { toppings.add("Onions"); toppingCost += 10 }
            if (cbTomatoes.isChecked) { toppings.add("Tomatoes"); toppingCost += 10 }
            if (cbPineapple.isChecked) { toppings.add("Pineapple"); toppingCost += 15 }

            val price = basePrice + crustPrice + toppingCost

            val summary = """
                You ordered a $size $pizzaType Pizza
                Crust: $crust
                Toppings: ${if (toppings.isEmpty()) "None" else toppings.joinToString(", ")}
                Total Price: ₱$price
            """.trimIndent()

            txtSummary.text = summary
        }

        btnNewOrder.setOnClickListener {
            rbHawaiian.isChecked = false
            rbHamCheese.isChecked = false
            rbSmall.isChecked = false
            rbMedium.isChecked = false
            rbLarge.isChecked = false
            rbThin.isChecked = false
            rbThick.isChecked = false

            cbExtraCheese.isChecked = false
            cbMushrooms.isChecked = false
            cbOnions.isChecked = false
            cbTomatoes.isChecked = false
            cbPineapple.isChecked = false

            txtSummary.text = ""
        }
    }
}

