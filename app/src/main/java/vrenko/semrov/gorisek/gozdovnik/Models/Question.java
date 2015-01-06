package vrenko.semrov.gorisek.gozdovnik.Models;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrenko on 06/01/15.
 */

@ParseClassName("Question")
public class Question extends ParseObject {


    List<Answer> answers;

    public List<Answer> getAnswers(){
        answers = new ArrayList<>();

        ParseRelation<Answer> ans = getRelation("answers");

        ans.getQuery().findInBackground(new FindCallback<Answer>() {
            @Override
            public void done(List<Answer> parseObjects, ParseException e) {

                answers.addAll(parseObjects);

            }
        });

        return answers;
    }
}
