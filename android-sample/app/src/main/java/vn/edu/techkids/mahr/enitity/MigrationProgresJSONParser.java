package vn.edu.techkids.mahr.enitity;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by qhuydtvt on 3/18/2016.
 */
public class MigrationProgresJSONParser implements JSONObjectParser {

    @Override
    public Object parse(String tag, InputStreamReader inputStreamReader) {
        MigrationProgress migrationProgress = MigrationProgress.getInst().loadFromJSON(inputStreamReader);
        return migrationProgress;
    }
}
