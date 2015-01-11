package vrenko.semrov.gorisek.gozdovnik.Models;

import android.app.ProgressDialog;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.List;

/**
 * Created by vrenko on 06/01/15.
 */

@ParseClassName("Quiz")
public class Quiz extends ParseObject {

    public static final String QUIZ_ID = "vrenko.semrov.gorisek.gozdovnik.ARG_QUIZ_ID";

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

    public byte[] getFileAsByteArray() {
        try {
            return getParseFile("handout").getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ParseFile getFileAsParseFile() {
        return getParseFile("handout");
    }

    public static void getQuizzes(final OnQuizzesRecievedListener listener){
        ParseQuery<Quiz> pq = ParseQuery.getQuery(Quiz.class);
        pq.findInBackground(new FindCallback<Quiz>() {
            @Override
            public void done(List<Quiz> quizzes, com.parse.ParseException e) {
                listener.quizzesReceived(quizzes);
            }
        });

    }

    public static Quiz getQuizByID(String quizID){
        ParseQuery<Quiz> pq = ParseQuery.getQuery(Quiz.class);
        try {
            return pq.get(quizID);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public interface OnQuizzesRecievedListener{
        public void quizzesReceived(List <Quiz> quizzes);
    }

    public interface OnQuestionsRecievedListener {
        public void questionsRecieved(List<Question> questions);
    }
}
