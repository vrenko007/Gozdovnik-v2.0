package vrenko.semrov.gorisek.gozdovnik.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrenko on 06/01/15.
 */

@ParseClassName("Question")
public class Question extends ParseObject {


    public String getQuestion(){
        return getString("question");
    }

    public String getType(){
        return getString("type");
    }


    public List<String> getAnswers(){

        JSONArray json = getJSONArray("answers");
        List<String> array = new ArrayList<>();

        int len = json.length();

        for (int i = 0; i<len; i++){
            try {
                array.add(json.getString(i));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return array;
    }

    public List<Integer> getCorrectAnswers(){

        JSONArray json = getJSONArray("answers");
        List<Integer> array = new ArrayList<>();

        int len = json.length();

        for (int i = 0; i<len; i++){
            try {
                array.add(json.getInt(i));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return array;
    }
}
