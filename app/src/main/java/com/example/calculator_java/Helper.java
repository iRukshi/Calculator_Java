package com.example.calculator_java;

import java.util.List;

public class Helper {
    // Method to convert an array of String to a string
    public static String arrayToString(List<String> array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String value : array) {
            stringBuilder.append(value);
        }
        return stringBuilder.toString();
    }

    public static boolean hasConsecutiveNumbers(String[] expressionArray) {
        for (int i = 0; i < expressionArray.length - 1; i++) {
            if (isNumeric(expressionArray[i]) && isNumeric(expressionArray[i + 1])) {
                // Consecutive numbers found
                return true;
            }
        }
        return false;
    }

    public static boolean hasConsecutiveOperators(String[] expressionArray) {
        for (int i = 0; i < expressionArray.length - 1; i++) {
            if (isOperator(expressionArray[i]) && isOperator(expressionArray[i + 1])) {
                // Consecutive operators found
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public static boolean isOperator(String str) {
        return str.matches("[+\\-*/]");
    }
}
