package vrenko.semrov.gorisek.gozdovnik.Models;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by vrenko on 06/01/15.
 */

@ParseClassName("Handout")
public class Handout extends ParseObject {

    public String getName() {
        return getString("name");
    }

    public byte[] getFileAsByteArray() {
        try {
            return getParseFile("document").getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ParseFile getFileAsParseFile() {
        return getParseFile("document");
    }
}
