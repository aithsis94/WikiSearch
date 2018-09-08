package in.ajitesh.wikisearch.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.claudiodegio.msv.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.ajitesh.wikisearch.R;
import in.ajitesh.wikisearch.adapters.SearchAdapter;

public class ItemWikiDisplayActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_item_wiki_display);

        ButterKnife.bind(this);

        this.initView();
        this.initData();
    }

    private void initView() {

        this.toolbar.setTitleTextColor(Color.WHITE);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("Info");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

        Intent intent = this.getIntent();
        String pageId = intent.getStringExtra("PAGE_ID");

        String url = "http://en.wikipedia.org/?curid=" + pageId;

        this.webView.setWebViewClient(new WebViewClient());
        this.webView.loadUrl(url);
    }
}
