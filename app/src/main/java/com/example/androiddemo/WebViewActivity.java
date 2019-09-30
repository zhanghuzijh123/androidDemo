package com.example.androiddemo;


import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        initWebView();
    }

    private void initWebView() {
//        使webview支持javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);
//        我们希望目标网页仍在当前WebView中显示，而不是打开浏览器
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.baidu.com");
    }
}
