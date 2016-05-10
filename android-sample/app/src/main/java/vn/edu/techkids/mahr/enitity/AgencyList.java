package vn.edu.techkids.mahr.enitity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by qhuydtvt on 3/30/2016.
 */
public class AgencyList {

    @SerializedName("items")
    private List<Agency> list;

    public List<Agency> getList() {
        return list;
    }

//    private static AgencyList inst;
//    public static AgencyList getInst() {
//        return inst;
//    }
//
//    public static Object fromJsonToList(InputStreamReader inputStreamReader) {
//        inst =  (new Gson()).fromJson(inputStreamReader, AgencyList.class);
//        return inst;
//    }
}
