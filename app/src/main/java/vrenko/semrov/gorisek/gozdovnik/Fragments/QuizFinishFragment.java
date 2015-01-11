package vrenko.semrov.gorisek.gozdovnik.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseUser;

import java.net.PasswordAuthentication;

import vrenko.semrov.gorisek.gozdovnik.HomeActivity;
import vrenko.semrov.gorisek.gozdovnik.Models.Achievement;
import vrenko.semrov.gorisek.gozdovnik.Models.Question;
import vrenko.semrov.gorisek.gozdovnik.Models.Quiz;
import vrenko.semrov.gorisek.gozdovnik.QuizActivity;
import vrenko.semrov.gorisek.gozdovnik.R;

public class QuizFinishFragment extends Fragment {

    public static String SCORE = "vrenko.semrov.gorisek.gozdovnik.ARG_SCORE";

    private Quiz mQuiz;
    private Achievement mAchivement;
    private Bitmap mBadge;
    private int mScore;


    public static QuizFinishFragment newInstance(Quiz question, int score) {
        QuizFinishFragment fragment = new QuizFinishFragment();
        Bundle args = new Bundle();
        args.putString(Quiz.QUIZ_ID, question.getObjectId());
        args.putInt(SCORE, score);
        fragment.setArguments(args);
        return fragment;
    }

    public QuizFinishFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mScore = getArguments().getInt(SCORE);
            String quizID = getArguments().getString(Quiz.QUIZ_ID);
            mQuiz = Quiz.getQuizByID(quizID);
            if(mScore > 7){
                mAchivement = Achievement.getFromQuizID(quizID);
                mBadge = mAchivement.getPicture();
                ParseUser.getCurrentUser().getRelation("activements").add(mAchivement);
                ParseUser.getCurrentUser().getRelation("completedQuizzes").add(mQuiz);
                ParseUser.getCurrentUser().saveEventually();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz_finish, container, false);

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.layout);

        if(mScore>7){
            ImageView iv = (ImageView) v.findViewById(R.id.badge);
            iv.setImageBitmap(mBadge);
            TextView tv = new TextView(getActivity());
            tv.setText("Congratulations");
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            ll.addView(tv);
            tv = new TextView(getActivity());
            tv.setText("Back");
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }
            });
            ll.addView(tv);


        }else{
            TextView tv = new TextView(getActivity());
            tv.setText("Better luck next time");
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            ll.addView(tv);
            tv = new TextView(getActivity());
            tv.setText("Try again");
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            ll.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), QuizActivity.class);
                    intent.putExtra(Quiz.QUIZ_ID, mQuiz.getObjectId());
                    v.getContext().startActivity(intent);
                    getActivity().finish();
                }
            });
            tv = new TextView(getActivity());
            tv.setText("Back");
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }
            });
            ll.addView(tv);
        }
        ll.invalidate();

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
