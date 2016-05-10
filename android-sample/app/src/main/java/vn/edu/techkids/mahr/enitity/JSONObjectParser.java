package vn.edu.techkids.mahr.enitity;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by qhuydtvt on 3/16/2016.
 */
public interface JSONObjectParser {
    Object parse(String tag, InputStreamReader inputStreamReader) throws IOException;
}
