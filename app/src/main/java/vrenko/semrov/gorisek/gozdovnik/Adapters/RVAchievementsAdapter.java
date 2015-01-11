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
        viewHolder.setmPicture(mDataset.get(i).getPicture());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            mPicture = (ImageView) itemView.findViewById(R.id.picture);
        }
        public void setmPicture(Bitmap mPicture){   this.mPicture.setImageBitmap(mPicture);}

    }

}