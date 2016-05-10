package vn.edu.techkids.mahr.enitity;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;

/**
 * Created by qhuydtvt on 3/9/2016.
 */
public class Lang {
    private int id;
    private boolean selected;

    public Lang(int id, boolean selected) {
        this.id = id;
        this.selected = selected;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getAPIParam() {
        switch (id){
            case R.string.vietnam: return Constants.API_VIETNAM;
            case R.string.indonesia: return Constants.API_INDONESIA;
            case R.string.taiwan: return Constants.API_TAIWAN;
        }
        return "";
    }
}
