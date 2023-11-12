package com.example.calculator_java;

import android.app.Application;
import java.util.List;

public class MyApp extends Application {
    private boolean isAdvancedMode;
    private StringBuilder history;
    public boolean isAdvancedMode() {
        return isAdvancedMode;
    }
    public void setAdvancedMode(boolean advancedMode) {
        this.isAdvancedMode = advancedMode;
    }
    public StringBuilder getHistory() {
        return history;
    }
    public void setHistory(StringBuilder history) {
        this.history = history;
    }
}
