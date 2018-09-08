package in.ajitesh.wikisearch.pojos;

/**
 * Created by ajitesh on 07/09/18.
 */

public class PageInfo {

    private String index;

    private String title;

    private String ns;

    private Thumbnail thumbnail;

    private String pageid;

    private TermInfo terms;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    public TermInfo getTerms() {
        return terms;
    }

    public void setTerms(TermInfo terms) {
        this.terms = terms;
    }
}
