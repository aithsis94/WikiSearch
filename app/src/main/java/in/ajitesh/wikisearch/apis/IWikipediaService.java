package in.ajitesh.wikisearch.apis;

import in.ajitesh.wikisearch.pojos.SearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ajitesh on 07/09/18.
 */

public interface IWikipediaService {

    @GET("w/api.php?action=query&format=json&prop=pageimages|pageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpslimit=10")
    Call<SearchResult> search(@Query("gpssearch") String searchParam);
}
