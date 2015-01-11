package vrenko.semrov.gorisek.gozdovnik;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Iterator;
import java.util.List;

import vrenko.semrov.gorisek.gozdovnik.Fragments.MultipleAnswerFragment;
import vrenko.semrov.gorisek.gozdovnik.Fragments.QuizFinishFragment;
import vrenko.semrov.gorisek.gozdovnik.Fragments.SingleAnswerFragment;
import vrenko.semrov.gorisek.gozdovnik.Interfaces.OnNextQuestionListener;
import vrenko.semrov.gorisek.gozdovnik.Models.Question;
import vrenko.semrov.gorisek.gozdovnik.Models.Quiz;


public class QuizActivity extends ActionBarActivity implements Quiz.OnQuestionsRecievedListener, OnNextQuestionListener {

    private Quiz mQuiz;
    private List<Question> mQuestions;
    private ProgressDialog mProgressDialog;
    private Iterator<Question> mIterator;
    private int mScore;

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String quizID = getIntent().getStringExtra(Quiz.QUIZ_ID);
        mQuiz = Quiz.getQuizByID(quizID);

        mFragmentManager = getFragmentManager();

        mScore = 0;

        mProgressDialog = ProgressDialog.show(this,null,"Loading Questions...", true, false);
        mQuiz.getQuestionsAsync(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
    public void questionsRecieved(List<Question> questions) {
        mQuestions = questions;

        mIterator = mQuestions.iterator();

        mProgressDialog.dismiss();

        nextQuestion(0);
    }

    @Override
    public void nextQuestion(int value) {


        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        mScore += value;

        if(mCurrentFragment != null){
            fragmentTransaction.remove(mCurrentFragment);
        }

        if(mIterator.hasNext()){
            Question nq = mIterator.next();
            if(nq.getType().equals(Question.SINGLE_ANSWER)){
                mCurrentFragment = SingleAnswerFragment.newInstance(nq);
            }
            if(nq.getType().equals(Question.MULTIPLE_ANSWER)){
                mCurrentFragment = MultipleAnswerFragment.newInstance(nq);
            }
        }else{
            mCurrentFragment = QuizFinishFragment.newInstance(mQuiz,mScore/mQuestions.size());
        }

        fragmentTransaction.add(R.id.fragment_container, mCurrentFragment);
        fragmentTransaction.commit();
    }
}
