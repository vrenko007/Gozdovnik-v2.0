package vrenko.semrov.gorisek.gozdovnik.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by vrenko on 11/01/15.
 */

@ParseClassName("Achievement")
public class Achievement extends ParseObject {

    public String getName(){ return getString("name"); }
    public int getValue(){  return getInt("value"); }
    public String getDescription(){ return getString("description"); }
    public Bitmap getPicture(){
        try {
            byte[] byteArray  = getParseFile("picture").getData();
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void getAchievements(final OnAchievementsRecievedListener listener){
        ParseQuery<Achievement> pqa = ParseQuery.getQuery(Achievement.class);
        pqa.findInBackground(new FindCallback<Achievement>() {
            @Override
            public void done(List<Achievement> achievements, com.parse.ParseException e) {
                listener.achievementReceived(achievements);
            }
        });

    }
    public interface OnAchievementsRecievedListener{
        public void achievementReceived(List <Achievement> achievement);
    }
}
