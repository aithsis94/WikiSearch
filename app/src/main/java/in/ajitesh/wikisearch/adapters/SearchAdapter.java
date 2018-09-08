package in.ajitesh.wikisearch.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ajitesh.wikisearch.IOnPageItemClickedListener;
import in.ajitesh.wikisearch.R;
import in.ajitesh.wikisearch.pojos.PageInfo;
import in.ajitesh.wikisearch.viewholders.SearchResultViewHolder;

/**
 * Created by ajitesh on 07/09/18.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchResultViewHolder>{

    private LayoutInflater inflater;
    private PageInfo[] pageInfos;
    private IOnPageItemClickedListener clickedListener;

    public SearchAdapter(LayoutInflater inflater, PageInfo[] pageInfos, IOnPageItemClickedListener clickedListener) {
        this.inflater = inflater;
        this.pageInfos = pageInfos;

        if(this.pageInfos == null)
            this.pageInfos = new PageInfo[0];

        this.clickedListener = clickedListener;
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.layout_search_result_item, parent, false);
        return new SearchResultViewHolder(itemView, this.clickedListener);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {

        PageInfo pageInfo = this.pageInfos[position];
        holder.bindView(pageInfo);
    }

    @Override
    public int getItemCount() {
        return pageInfos.length;
    }

    public void refreshData(PageInfo[] pageList){

        if(pageList == null)
            this.pageInfos = new PageInfo[0];
        else
            this.pageInfos = pageList;

        this.notifyDataSetChanged();
    }

}
