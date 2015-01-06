package vrenko.semrov.gorisek.gozdovnik;

import com.parse.ui.ParseLoginDispatchActivity;

/**
 * Created by vrenko on 06/01/15.
 */
public class LoginDispatchActivity extends ParseLoginDispatchActivity {
    @Override
    protected Class<?> getTargetClass() {
        return HomeActivity.class;
    }
}
