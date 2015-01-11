package vrenko.semrov.gorisek.gozdovnik.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrenko on 11/01/15.
 */
@ParseClassName("Achievement")
public class Achievement extends ParseObject {

    public boolean isAchievementAchieved() {
        return achievementAchieved;
    }

    public void setAchievementAchieved(boolean achievementAchieved) {
        this.achievementAchieved = achievementAchieved;
    }

    private boolean achievementAchieved = false;

    public String getName() {
        return getString("name");
    }

    public Bitmap getPicture() {
        try {
            byte[] byteArray = getParseFile("picture").getData();
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void getAchievements(final OnAchievementsRecievedListener listener) {
        ParseQuery<Achievement> pqa = ParseQuery.getQuery(Achievement.class);
        pqa.findInBackground(new FindCallback<Achievement>() {
            @Override
            public void done(final List<Achievement> achievements, com.parse.ParseException e) {
                ParseRelation<Achievement> pr = ParseUser.getCurrentUser().getRelation("achivements");
                pr.getQuery().findInBackground(new FindCallback<Achievement>() {
                    @Override
                    public void done(List<Achievement> parseObjects, ParseException e) {
                        for(Achievement a:achievements){
                            if(parseObjects.contains(a)){
                                a.setAchievementAchieved(true);
                            }
                        }
                        listener.achievementReceived(achievements);
                    }
                });

            }
        });

    }

    public static Achievement getFromQuizID(String quizID) {
        ParseQuery<Achievement> pq = ParseQuery.getQuery(Achievement.class);
        pq.whereStartsWith("quizID", quizID);

        List<Achievement> achivements = new ArrayList<>();

        try {
            achivements = pq.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (achivements.size() < 1) {
            return null;
        }

        return achivements.get(0);
    }

    public interface OnAchievementsRecievedListener {
        public void achievementReceived(List<Achievement> achievement);
    }
}
