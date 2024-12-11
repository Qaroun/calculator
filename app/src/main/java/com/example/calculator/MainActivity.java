package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView resultView;
    private String currentInput = "";
    private double firstNumber = 0;
    private String operator = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = findViewById(R.id.textViewResult);

        View.OnClickListener listener = view -> {
            Button button = (Button) view;
            String value = button.getText().toString();

            switch (value) {
                case "C":
                    currentInput = "";
                    firstNumber = 0;
                    operator = "";
                    resultView.setText("0");
                    break;

                case "=":
                    if (!currentInput.isEmpty() && !operator.isEmpty()) {
                        double secondNumber = Double.parseDouble(currentInput);
                        double result = calculate(firstNumber, secondNumber, operator);
                        resultView.setText(String.valueOf(result));
                        firstNumber = result;
                        currentInput = "";
                        operator = "";
                    }
                    break;

                case "+":
                case "-":
                case "*":
                case "/":
                    if (!currentInput.isEmpty()) {
                        firstNumber = Double.parseDouble(currentInput);
                        operator = value;
                        currentInput = "";
                    }
                    break;

                default: // For numbers
                    currentInput += value;
                    resultView.setText(currentInput);
                    break;
            }
        };

        // Attaching listeners to all buttons
        int[] buttonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide, R.id.btnEqual, R.id.btnClear};

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }
}