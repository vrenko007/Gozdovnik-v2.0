package vrenko.semrov.gorisek.gozdovnik.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vrenko.semrov.gorisek.gozdovnik.Interfaces.OnNextQuestionListener;
import vrenko.semrov.gorisek.gozdovnik.Models.Question;
import vrenko.semrov.gorisek.gozdovnik.R;

public class SingleAnswerFragment extends Fragment {



    private OnNextQuestionListener mListener;
    private Question mQuestion;

    private TextView mQuestionText;
    private RadioGroup mAnswers;

    public static SingleAnswerFragment newInstance(Question question) {
        SingleAnswerFragment fragment = new SingleAnswerFragment();
        Bundle args = new Bundle();
        args.putString(Question.QUESTION_ID, question.getObjectId());
        fragment.setArguments(args);
        return fragment;
    }

    public SingleAnswerFragment() {
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

        mQuestionText = (TextView) v.findViewById(R.id.question);
        mQuestionText.setText(mQuestion.getQuestion());

        mAnswers = (RadioGroup) v.findViewById(R.id.answers);


        List<String> answers = mQuestion.getAnswers();
        for(int i = 0; i<answers.size(); i++){

            RadioButton rb = new RadioButton(getActivity());
            rb.setText(answers.get(i));
            rb.setId(i);
            mAnswers.addView(rb,i);
        }

        mAnswers.invalidate();

        Button btn = (Button) v.findViewById(R.id.next);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int answer = mAnswers.getCheckedRadioButtonId();
                List<Integer> list = new ArrayList<>();
                list.add(Integer.valueOf(answer));

                mListener.nextQuestion(mQuestion.checkCorrect(list));

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
