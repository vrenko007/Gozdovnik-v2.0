package vrenko.semrov.gorisek.gozdovnik.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by vrenko on 06/01/15.
 */

@ParseClassName("Answer")
public class Answer extends ParseObject {

    public String getAnswer(){
        return getString("answer");
    }
}
