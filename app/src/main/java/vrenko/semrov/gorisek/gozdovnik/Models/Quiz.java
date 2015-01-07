package vrenko.semrov.gorisek.gozdovnik.Models;

import android.app.ProgressDialog;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;

import java.util.List;

/**
 * Created by vrenko on 06/01/15.
 */

@ParseClassName("Quiz")
public class Quiz extends ParseObject {

    public String getName(){
        return getString("name");
    }

    public void getQuestionsAsync(final OnQuestionsRecievedListener listener){
        ParseRelation<Question> questionParseRelation = getRelation("questions");

        questionParseRelation.getQuery().findInBackground(new FindCallback<Question>() {
            @Override
            public void done(List<Question> questionList, ParseException e) {
                listener.questionsRecieved(questionList);
            }
        });
    }

    public interface OnQuestionsRecievedListener {
        public void questionsRecieved(List<Question> questions);
    }
}
