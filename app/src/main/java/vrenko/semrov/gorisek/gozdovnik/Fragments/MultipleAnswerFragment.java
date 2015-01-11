package vrenko.semrov.gorisek.gozdovnik.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vrenko.semrov.gorisek.gozdovnik.Interfaces.OnNextQuestionListener;
import vrenko.semrov.gorisek.gozdovnik.Models.Question;
import vrenko.semrov.gorisek.gozdovnik.R;

public class MultipleAnswerFragment extends Fragment {



    private OnNextQuestionListener mListener;
    private Question mQuestion;

    private TextView mQuestionText;
    private LinearLayout mAnswers;
    private List<Integer> mCheckedAnswers;

    public static MultipleAnswerFragment newInstance(Question question) {
        MultipleAnswerFragment fragment = new MultipleAnswerFragment();
        Bundle args = new Bundle();
        args.putString(Question.QUESTION_ID, question.getObjectId());
        fragment.setArguments(args);
        return fragment;
    }

    public MultipleAnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String questionID = getArguments().getString(Question.QUESTION_ID);
            mQuestion = Question.getQuestionByID(questionID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_single_answer, container, false);

        mCheckedAnswers = new ArrayList<>();

        mQuestionText = (TextView) v.findViewById(R.id.question);
        mQuestionText.setText(mQuestion.getQuestion());

        mAnswers = (LinearLayout) v.findViewById(R.id.answers);


        List<String> answers = mQuestion.getAnswers();
        for(int i = 0; i< answers.size(); i++){

            CheckBox cb = new CheckBox(getActivity());
            cb.setText(answers.get(i));

            final int finalI = i;
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        mCheckedAnswers.add(Integer.valueOf(finalI));
                    }else{
                        mCheckedAnswers.remove(Integer.valueOf(finalI));
                    }
                }
            });

            mAnswers.addView(cb);
        }

        mAnswers.invalidate();

        Button btn = (Button) v.findViewById(R.id.next);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.nextQuestion(mQuestion.checkCorrect(mCheckedAnswers));

            }
        });




        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnNextQuestionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnNextQuestionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
