package vn.edu.techkids.mahr.enitity;

import android.provider.ContactsContract;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.util.List;

/**
 * Created by qhuydtvt on 3/30/2016.
 */
public class Agency {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("chinese_name")
    private String chineseName;

    private boolean selected = false;

    private DataChangeListener dataChangeListener;

    public boolean getSelected() { return  selected; }

    public String getChineseName() {
        return chineseName;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setDataChangeListener(DataChangeListener dataChangeListener) {
        this.dataChangeListener = dataChangeListener;
    }

    public void notifyChange() {
        this.dataChangeListener.onDataChange(this);
    }
}
