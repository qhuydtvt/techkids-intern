package vn.edu.techkids.mahr.enitity;

/**
 * Created by hungtran on 3/11/16.
 */
public class Cloud {
    private String urlLink;

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    private static Cloud ourInstance = new Cloud();

    public static Cloud getInstance() {
        return ourInstance;
    }

    private Cloud() {
    }
}
