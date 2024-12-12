package com.example.uptoskills.Adapters;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uptoskills.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript
        webView.getSettings().setDomStorageEnabled(true); // Enable DOM storage
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); // For popups
//        webView.setWebChromeClient(new WebChromeClient()); For JavaScript alerts

        webView.loadUrl("https://example.com");
        webView.addJavascriptInterface(new WebInterface(this), "Android");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Inject JavaScript to add event listeners to buttons
                view.evaluateJavascript(
                        "javascript:(function() {" +
                                "document.querySelector('button').addEventListener('click', function() {" +
                                "Android.onButtonClick('Button text: ' + this.innerText);" +
                                "});" +
                                "})()",
                        null
                );
            }
        });
    }
}