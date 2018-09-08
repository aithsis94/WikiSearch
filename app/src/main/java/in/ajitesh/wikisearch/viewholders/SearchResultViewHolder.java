package in.ajitesh.wikisearch.viewholders;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.ajitesh.wikisearch.IOnPageItemClickedListener;
import in.ajitesh.wikisearch.R;
import in.ajitesh.wikisearch.pojos.PageInfo;
import in.ajitesh.wikisearch.pojos.TermInfo;
import in.ajitesh.wikisearch.pojos.Thumbnail;

/**
 * Created by ajitesh on 07/09/18.
 */

public class SearchResultViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.textViewMain)
    TextView mainTextView;

    @BindView(R.id.textViewSub)
    TextView subTextView;

    private PageInfo pageInfo;

    private IOnPageItemClickedListener clickedListener;

    public SearchResultViewHolder(View itemView, IOnPageItemClickedListener clickedListener) {
        super(itemView);
        this.clickedListener = clickedListener;
        ButterKnife.bind(this, itemView);

        this.wireEventHandlers();
    }

    private void wireEventHandlers(){

        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(clickedListener != null){
                    clickedListener.onPageItemClicked(pageInfo);
                }
            }
        });
    }

    public void bindView(PageInfo pageInfo){

        this.pageInfo = pageInfo;

        String title = pageInfo.getTitle();
        String description = "";
        String thumbnailUrl = null;

        TermInfo termInfo = pageInfo.getTerms();
        if(termInfo != null){
            String[] descriptionItems = termInfo.getDescription();
            description = StringUtils.join(descriptionItems, ", ");
        }

        Thumbnail thumbnail = pageInfo.getThumbnail();
        if(thumbnail != null){
            thumbnailUrl = pageInfo.getThumbnail().getSource();
        }

        this.mainTextView.setText(title);
        this.subTextView.setText(description);

        Picasso.get()
                .load(thumbnailUrl)
                .fit()
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .into(this.imageView);
    }
}
