package vn.edu.techkids.mahr.enitity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by qhuydtvt on 3/16/2016.
 */
public class ExpertiseList {
    @SerializedName("items")
    private List<Expertise> list;

    public List<Expertise> getList() {
        return list;
    }

    public void setList(List<Expertise> list) {
        this.list = list;
    }
}
