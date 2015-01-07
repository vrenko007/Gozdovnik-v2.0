package vrenko.semrov.gorisek.gozdovnik.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vrenko.semrov.gorisek.gozdovnik.Models.Question;
import vrenko.semrov.gorisek.gozdovnik.R;

/**
 * Created by vrenko on 07/01/15.
 */
public class RVQuestionsAdapter extends RecyclerView.Adapter<RVQuestionsAdapter.ViewHolder> {

    private List<Question> mDataset;

    public RVQuestionsAdapter(List<Question> mDataset) {
        this.mDataset = mDataset;

    }

    @Override
    public RVQuestionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //...

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.setmName(mDataset.get(i).getQuestion());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView mName;

        public ViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name);
        }

        public void setmName(String mName) {
            this.mName.setText(mName);
        }
    }
}
