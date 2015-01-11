package vrenko.semrov.gorisek.gozdovnik.Models;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrenko on 06/01/15.
 */

@ParseClassName("Question")
public class Question extends ParseObject {

    public static final String SINGLE_ANSWER = "vrenko.semrov.gorisek.gozdovnik.question.SINGLE_ANSWER_TYPE";
    public static final String MULTIPLE_ANSWER = "vrenko.semrov.gorisek.gozdovnik.question.MULTIPLE_ANSWER_TYPE";
    public static final String QUESTION_ID = "vrenko.semrov.gorisek.gozdovnik.question.ARG_QUESTION_ID";


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

        JSONArray json = getJSONArray("correctAnswers");
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

    public static Question getQuestionByID(String questionID){
        ParseQuery<Question> pq = ParseQuery.getQuery(Question.class);
        try {
            return pq.get(questionID);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int checkCorrect(List<Integer> answers) {

        List<Integer> correctAnswers = getCorrectAnswers();
        float i = 0;
        for(Integer j : answers){
            if(correctAnswers.contains(j)){
                i+=1.0;
            }else{
                i-=0.5;
            }
        }

        if (i < 0) i = 0;

        return Math.round(i / correctAnswers.size() * 10);
    }
}
