package uz.inha.tis;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    WebView mWebView;
    Toolbar mToolbar;
    ProgressBar progressBar;
    String mUrl = "http://www.pictureofadad.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initUI();
        handleIntent(getIntent());
    }

    private void initUI() {
        mWebView = (WebView) findViewById(R.id.activity_search_web_view);
        mToolbar = (Toolbar) findViewById(R.id.activity_search_toolbar);
        progressBar = (ProgressBar) findViewById(R.id.search_progress_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            setTitle(query);
            query = query.replace(" ", "+");
            mUrl = String.format("%s%s%s", "http://www.pictureofadad.com/?s=", query, "&submit=Search");
            initWebView();
        }
    }

    private void initWebView() {
        progressBar.setVisibility(View.VISIBLE);
        mWebView.setVerticalScrollBarEnabled(false);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUserAgentString("Android");
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                mWebView.loadUrl("javascript:(function() { " +
                        "document.getElementById('branding').style.display='none';" +
                        "document.getElementById('icons').style.display='none';"
                        +
                        "})()");


                progressBar.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
            }
        });
        mWebView.loadUrl(mUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }


    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
