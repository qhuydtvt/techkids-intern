package vn.edu.techkids.mahr.enitity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by qhuydtvt on 3/16/2016.
 */
public class WorkerList {

    @SerializedName("items")
    private List<Worker> list;


    public List<Worker> getList() {
        return list;
    }

    public void setList(List<Worker> list) {
        this.list = list;
    }
}
