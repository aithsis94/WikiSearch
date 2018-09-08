package in.ajitesh.wikisearch.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.claudiodegio.msv.MaterialSearchView;
import com.claudiodegio.msv.OnSearchViewListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.ajitesh.wikisearch.IOnPageItemClickedListener;
import in.ajitesh.wikisearch.R;
import in.ajitesh.wikisearch.adapters.SearchAdapter;
import in.ajitesh.wikisearch.apis.ApiManager;
import in.ajitesh.wikisearch.pojos.PageInfo;
import in.ajitesh.wikisearch.pojos.QueryInfo;
import in.ajitesh.wikisearch.pojos.SearchResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements OnSearchViewListener, IOnPageItemClickedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.searchView)
    MaterialSearchView searchView;

    @BindView(R.id.recyclerViewSearch)
    RecyclerView searchResultRecyclerView;

    private SearchAdapter searchAdapter;

    private Call<SearchResult> searchResultCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        this.initView();
        this.initData();
    }

    private void initView() {

        this.toolbar.setTitleTextColor(Color.WHITE);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("Wikipedia");
        this.searchView.setOnSearchViewListener(this);
    }

    @Override
    protected void onPause() {

        try {
            if (this.searchResultCall != null)
                this.searchResultCall.cancel();
        }catch (Exception ex){
        }

        super.onPause();
    }

    private void initData() {

        this.searchAdapter = new SearchAdapter(LayoutInflater.from(this), null, this);
        this.searchResultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.searchResultRecyclerView.setAdapter(this.searchAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        this.searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void onSearchViewShown() {
    }

    @Override
    public void onSearchViewClosed() {
    }

    @Override
    public boolean onQueryTextSubmit(String searchParam) {
        return false;
    }

    @Override
    public void onQueryTextChange(String searchParam) {
        this.fireSearchApi(searchParam);
    }

    private void fireSearchApi(String param) {

        try {
            if (this.searchResultCall != null) {
                this.searchResultCall.cancel();
            }
        } catch (Exception ex) {
        }

        this.searchResultCall = ApiManager.getWikipediaService().search(param);

        this.searchResultCall.enqueue(new Callback<SearchResult>() {

            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {

                PageInfo[] pageList = null;

                try {

                    SearchResult result = response.body();
                    QueryInfo queryInfo = result.getQuery();
                    pageList = queryInfo.getPages();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                searchAdapter.refreshData(pageList);
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                //TODO
            }
        });
    }

    @Override
    public void onPageItemClicked(PageInfo pageInfo) {

        if(pageInfo != null){

            Intent intent = new Intent(this, ItemWikiDisplayActivity.class);
            intent.putExtra("PAGE_ID", pageInfo.getPageid());
            this.startActivity(intent);
        }
    }
}
