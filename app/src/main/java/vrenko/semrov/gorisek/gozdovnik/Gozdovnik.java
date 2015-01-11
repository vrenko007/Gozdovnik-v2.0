package vrenko.semrov.gorisek.gozdovnik;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;

import vrenko.semrov.gorisek.gozdovnik.Models.Achievement;
import vrenko.semrov.gorisek.gozdovnik.Models.Handout;
import vrenko.semrov.gorisek.gozdovnik.Models.Question;
import vrenko.semrov.gorisek.gozdovnik.Models.Quiz;

/**
 * Created by vrenko on 06/01/15.
 */
public class Gozdovnik extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "cKkLmoyfUpuvui5Q9g0FElWs7OmfThxncBc2gVyH", "qAw7ELMqm2RoUxgjO40BaWfuFrVfiVANisf9ze44");
        ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));

        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Achievement.class);
        ParseObject.registerSubclass(Quiz.class);
        ParseObject.registerSubclass(Question.class);
        ParseObject.registerSubclass(Handout.class);
    }
}
