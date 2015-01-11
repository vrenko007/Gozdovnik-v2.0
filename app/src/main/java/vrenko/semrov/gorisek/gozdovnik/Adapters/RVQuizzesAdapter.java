package vrenko.semrov.gorisek.gozdovnik.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.artifex.mupdfdemo.MuPDFActivityBytes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import vrenko.semrov.gorisek.gozdovnik.Models.Achievement;
import vrenko.semrov.gorisek.gozdovnik.Models.Quiz;
import vrenko.semrov.gorisek.gozdovnik.QuizActivity;
import vrenko.semrov.gorisek.gozdovnik.R;


public class RVQuizzesAdapter extends RecyclerView.Adapter<RVQuizzesAdapter.ViewHolder> {


    private List<Quiz> mDataset;

    public RVQuizzesAdapter(List<Quiz> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public RVQuizzesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.setmName(mDataset.get(i).getName());

        viewHolder.mViewPDF.setOnClickListener(new OnPDFClickListener(mDataset.get(i).getFileAsByteArray()));

        viewHolder.mTakeQuiz.setOnClickListener(new OnQuizClickListener(mDataset.get(i).getObjectId()));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mName;
        Button mViewPDF;
        Button mTakeQuiz;

        public ViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name);
            mTakeQuiz = (Button) itemView.findViewById(R.id.takeQuiz);
            mViewPDF = (Button) itemView.findViewById(R.id.readPDF);
        }

        public void setmName(String mName) {        this.mName.setText(mName);      }

    }

    class OnPDFClickListener implements View.OnClickListener{

        byte[] mPDF;

        OnPDFClickListener(byte[] PDF) {
            mPDF = PDF;
        }

        @Override
        public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MuPDFActivityBytes.class);
                intent.setAction("android.intent.action.VIEW");
                intent.putExtra("buffer",mPDF);
                intent.putExtra("FileName", "PDF_NASLOV");

                intent.setType("application/pdf");
                v.getContext().startActivity(intent);
        }
    }

    class OnQuizClickListener implements View.OnClickListener{

        String mQuiz;

        OnQuizClickListener(String quizID) {
            mQuiz = quizID;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), QuizActivity.class);
            intent.setAction("android.intent.action.VIEW");
            intent.putExtra(Quiz.QUIZ_ID,mQuiz);

            intent.setType("application/pdf");
            v.getContext().startActivity(intent);
        }
    }

}