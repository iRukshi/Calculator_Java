

package com.example.calculator_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    private MyApp myApp;
    private TextView resultTextView;
    private TextView historyTextView;
    private Button historyButton;
    private Button[] digitButtons;
    private Button[] operatorButtons;
    private Button clearButton;
    private Button equalsButton;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();

        // Initialize UI components
        resultTextView = findViewById(R.id.resultTextView);
        historyTextView = findViewById(R.id.historyTextView);
        historyButton = findViewById(R.id.buttonHistory);
        clearButton = findViewById(R.id.buttonClear);
        equalsButton = findViewById(R.id.buttonEquals);
        digitButtons = new Button[10];//0-9
        operatorButtons = new Button[4]; // +, -, *, /

        for (int i = 0; i < 10; i++) {
            int resourceId = getResources().getIdentifier("button" + i, "id", getPackageName());
            digitButtons[i] = findViewById(resourceId);
        }

        operatorButtons[0] = findViewById(R.id.buttonPlus);
        operatorButtons[1] = findViewById(R.id.buttonMinus);
        operatorButtons[2] = findViewById(R.id.buttonMultiply);
        operatorButtons[3] = findViewById(R.id.buttonDivide);

        // Set click listeners for digit buttons
        for (Button button : digitButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDigitClick((Button) v);
                }
            });
        }

        // Set click listeners for operator buttons
        for (Button button : operatorButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOperatorClick((Button) v);
                }
            });
        }

        // Set click listener for the clear button
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClick();
            }
        });

        // Set click listener for the equals button
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualsClick();
            }
        });

        // Set click listener for the history button
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHistoryClick();
            }
        });

        myApp = (MyApp) getApplication();
        if (myApp.isAdvancedMode() == true) {
            calculator.setAdvancedMode(myApp.isAdvancedMode());
            calculator.setHistory(myApp.getHistory());
            historyTextView.setText(calculator.getHistory());
            updateHistoryVisibility();
        }
    }
    public void onDigitClick(Button button) {
        String digit = button.getText().toString();
        calculator.push(digit);
        updateResultText(calculator.getInputList());
    }
    public void onOperatorClick(Button button) {
        String operator = button.getText().toString();
        calculator.push(operator);
        updateResultText(calculator.getInputList());
    }
    public void onClearClick() {
        calculator.clearInputs();
        resultTextView.setText("");
    }
    public void onEqualsClick() {
        int result = calculator.calculate();
        if (result == 1000){
            Toast.makeText(this, "Consecutive numbers found.", Toast.LENGTH_SHORT).show();
        }
        else if (result == 2000) {
            Toast.makeText(this, "Consecutive operators found.", Toast.LENGTH_SHORT).show();
        }
        else if (result == 3000) {
            Toast.makeText(this, "Invalid Operation found.", Toast.LENGTH_SHORT).show();
        }
        else  {
            resultTextView.setText(Integer.toString(result));
            if (calculator.isAdvancedMode()) {
                historyTextView.setText(calculator.getHistory());
                myApp.setHistory(calculator.getHistory());
            }
        }
    }
    public void onHistoryClick() {
        calculator.toggleAdvancedMode();
        updateHistoryVisibility();
        myApp.setAdvancedMode(calculator.isAdvancedMode());
    }
    private void updateHistoryVisibility() {
        if (calculator.isAdvancedMode()) {
            historyButton.setText("STANDARD – NO HISTORY");
        } else {
            historyButton.setText("ADVANCE - WITH HISTORY");
            historyTextView.setText("");
            calculator.clearOperatorHistory();
        }
    }
    private void updateResultText(List<String> inputList) {
        StringBuilder resultText = new StringBuilder();
        for (String item : inputList) {
            resultText.append(item);
        }
        resultTextView.setText(resultText.toString());
    }
}



