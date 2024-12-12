package com.example.uptoskills.Adapters;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebInterface {
    Context context;

    public WebInterface(Context context) {
        this.context = context;
    }

    // Example: Perform action when a button is clicked
    @JavascriptInterface
    public void onButtonClick(String message) {
        Toast.makeText(context, "Button clicked: " + message, Toast.LENGTH_SHORT).show();
    }
}