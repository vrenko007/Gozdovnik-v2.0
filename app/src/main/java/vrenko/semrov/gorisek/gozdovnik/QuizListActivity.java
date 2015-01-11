package vrenko.semrov.gorisek.gozdovnik;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import vrenko.semrov.gorisek.gozdovnik.Adapters.RVQuizzesAdapter;
import vrenko.semrov.gorisek.gozdovnik.Models.Quiz;


public class QuizListActivity extends ActionBarActivity implements Quiz.OnQuizzesRecievedListener{

    private RecyclerView mQuizList;
    private RecyclerView.LayoutManager mLayoutManager;
    private RVQuizzesAdapter mQuizAdapter;
    private List<Quiz> mQuizzes;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);

        mQuizList = (RecyclerView) findViewById(R.id.quiz_list);

        mQuizList.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mQuizList.setLayoutManager(mLayoutManager);

        mQuizzes = new ArrayList<>();
        mProgress = ProgressDialog.show(this,null,"Loading...", true, false);
        Quiz.getQuizzes(this);
        mQuizAdapter = new RVQuizzesAdapter(mQuizzes);


        mQuizList.setAdapter(mQuizAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void quizzesReceived(List<Quiz> quizzes) {
        mQuizzes.addAll(quizzes);
        mQuizAdapter.notifyDataSetChanged();
        mProgress.dismiss();
    }
}
