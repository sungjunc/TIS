package uz.inha.tis.menu;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import uz.inha.tis.R;

public class MenuDetailActivity extends AppCompatActivity {

    String mUrl;
    String mTitle;
    WebView mWebView;
    Toolbar mToolbar;
    ProgressBar progressBar;
    private final String googleDocs = "https://docs.google.com/viewer?url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        parseIntent(getIntent());
        initUI();
        initWebView();
    }

    private void parseIntent(Intent intent) {
        Bundle args = intent.getExtras();
        mUrl = args.getString("url");
        mTitle = args.getString("title");
        //geting information
    }

    private void initUI() {
        mWebView = (WebView) findViewById(R.id.activity_menu_detail_web_view);
        mToolbar = (Toolbar) findViewById(R.id.activity_menu_detail_toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(mTitle);
        //title and web view
    }

    private void initWebView() {
        progressBar.setVisibility(View.VISIBLE);
        mWebView.setVerticalScrollBarEnabled(false);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUserAgentString("Android");
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.v(MenuDetailActivity.class.getName(), "onPageFinished: " + url);
                if (url.endsWith(".pdf")) {
                    startPdf(url, view);
                } else if (url.startsWith("mailto:")) {
                    mailTo(url);
                } else {
                    loadPage();
                }
            }
        });
        mWebView.loadUrl(mUrl);
        // load the web page from the website
    }
// blue button is clicked
    private void startPdf(String url, WebView view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), "application/pdf");
        try {
            view.getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            String pdfUrl = googleDocs + url;
            gotToUri(pdfUrl);
            // load pdf
        }
    }

    private void loadPage() {
        mWebView.loadUrl("javascript:(function() { " +
                "document.getElementById('main-header').style.display='none';" +
                "var list = document.getElementsByClassName('et_pb_section  et_pb_section_5 et_pb_with_background et_section_regular');"
                + "for (i = 0; i < list.length; i++) {\n" +
                "    list[i].style.display = 'none';" +
                "}"
                +
                "})()");
        progressBar.setVisibility(View.GONE);
        // loading the page

    }

    private void mailTo(String url) {
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
        startActivity(i);
        //write down the mail
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
        // otions menu
    }

    public void gotToUri(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
        // going to url
    }


    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
            // android phone back press
        }
    }
}
