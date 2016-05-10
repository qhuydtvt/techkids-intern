package vn.edu.techkids.mahr.enitity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by qhuydtvt on 3/9/2016.
 */
public class Expertise {

    private int id;
    private String name;
    private String chinese_name;
    private boolean selected;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CHINESE_NAME = "chinese_name";

    private static final String KEY_ITEMS = "items";

    /*private static ArrayList<Expertise> expertiseArrayList = new ArrayList<>();*/

    public Expertise() {

    }

    public Expertise(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt(KEY_ID);
            this.name = jsonObject.getString(KEY_NAME);
            this.chinese_name = jsonObject.getString(KEY_CHINESE_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {return id; }

    public String getName() {
        if (Locale.getDefault().getDisplayLanguage().contains("vi")) {
            return name;
        } else {
            return chinese_name;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }

//    public void setChinese_name(String chinese_name) {
//        this.chinese_name = chinese_name;
//    }

    /*public static ArrayList<Expertise> getExpertiseList() {
        return expertiseArrayList;
    }*/

    /*public static void loadExperiseArrayList(JSONObject jsonObject) {
        //Log.d("loadExperiseArrayList", jsonObject.toString());
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_ITEMS);
            expertiseArrayList.clear();
            for(int i = 0; i < jsonArray.length(); i++) {
                Expertise expertise = new Expertise(jsonArray.getJSONObject(i));
                expertiseArrayList.add(expertise);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}
