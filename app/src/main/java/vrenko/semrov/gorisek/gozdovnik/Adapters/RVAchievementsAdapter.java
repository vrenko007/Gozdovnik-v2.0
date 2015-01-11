package vrenko.semrov.gorisek.gozdovnik.Adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vrenko.semrov.gorisek.gozdovnik.Models.Achievement;
import vrenko.semrov.gorisek.gozdovnik.R;


public class RVAchievementsAdapter extends RecyclerView.Adapter<RVAchievementsAdapter.ViewHolder> {


    private List<Achievement> mDataset;

    public RVAchievementsAdapter(List<Achievement> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public RVAchievementsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.setmName(mDataset.get(i).getName());
        viewHolder.setmValue(mDataset.get(i).getValue());
        viewHolder.setmDescription(mDataset.get(i).getDescription());
        viewHolder.setmPicture(mDataset.get(i).getPicture());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mName;
        TextView mValue;
        TextView mDescription;
        ImageView mPicture;

        public ViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name);
            mDescription = (TextView) itemView.findViewById(R.id.description);
            mValue = (TextView) itemView.findViewById(R.id.value);
            mPicture = (ImageView) itemView.findViewById(R.id.picture);
        }

        public void setmName(String mName) {        this.mName.setText(mName);      }
        public void setmValue(int mValue){           this.mValue.setText(String.format("%04d",mValue));    }
        public void setmDescription(String mDescription){this.mDescription.setText(mDescription);   }
        public void setmPicture(Bitmap mPicture){   this.mPicture.setImageBitmap(mPicture);}

    }

}