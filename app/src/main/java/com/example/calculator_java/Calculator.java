package com.example.calculator_java;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    final private List<String> inputList;
    private boolean isAdvancedMode;
    private StringBuilder history;

    public Calculator() {
        inputList = new ArrayList<>();
        history = new StringBuilder();
        isAdvancedMode = false;
    }
    public void push(String value) {
        inputList.add(value);
    }
    public int calculate() {
        String[] inputArray = inputList.toArray(new String[0]);
        if (Helper.hasConsecutiveNumbers(inputArray)) {
            System.out.println("Consecutive numbers found.");
            return 1000;
        } else if (Helper.hasConsecutiveOperators(inputArray)) {
            System.out.println("Consecutive operators found.");
            return 2000;
        } else {

            int result = 0; // Initialize with a default value
            int currentOperand = 0;
            String currentOperator = "+";

            for (String item : inputList) {
                if (Helper.isNumeric(item)) {
                    int operand = Integer.parseInt(item);
                    if (currentOperator.equals("+")) {
                        result += operand;
                    } else if (currentOperator.equals("-")) {
                        result -= operand;
                    } else if (currentOperator.equals("*")) {
                        result *= operand;
                    } else if (currentOperator.equals("/")) {
                        if (operand != 0) {
                            result /= operand;
                        } else {
                            System.out.println("Invalid Operation found.");
                            return 3000;
                        }
                    }
                    currentOperand = operand;
                } else {
                    currentOperator = item;
                }
            }
            // Update history
            if (isAdvancedMode) {
                String inputString = Helper.arrayToString(inputList);
                history.append(inputString).append("=").append(result).append("\n");
            }
            return result;
        }
    }
    public void clearInputs() {
        inputList.clear();
    }
    public void toggleAdvancedMode() {
        isAdvancedMode = !isAdvancedMode;
    }
    public List<String> getInputList() {
        return inputList;
    }
    public boolean isAdvancedMode() {
        return isAdvancedMode;
    }
    public StringBuilder getHistory() {
        return history;
    }
    public void setAdvancedMode(boolean advancedMode) {
        isAdvancedMode = advancedMode;
    }
    public void setHistory(StringBuilder history) {
        this.history = history;
    }
    public void clearOperatorHistory() {
        history = new StringBuilder();
    }
}

