package vrenko.semrov.gorisek.gozdovnik.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import vrenko.semrov.gorisek.gozdovnik.Adapters.RVAchievementsAdapter;
import vrenko.semrov.gorisek.gozdovnik.Models.Achievement;
import vrenko.semrov.gorisek.gozdovnik.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AchievementListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AchievementListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AchievementListFragment extends Fragment implements Achievement.OnAchievementsRecievedListener {
    
    private TwoWayView mAchievementList;
    private RVAchievementsAdapter mAchievementAdapter;
    private List<Achievement> mAchievements;
    private ProgressDialog mProgress;
    
    
    
    public static AchievementListFragment newInstance() {
        AchievementListFragment fragment = new AchievementListFragment();
        return fragment;
    }

    public AchievementListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_achievement_list, container, false);

        mAchievementList = (TwoWayView) v.findViewById(R.id.achievement_list);

        mAchievementList.setHasFixedSize(true);

        mAchievements = new ArrayList<>();
        Achievement.getAchievements(this);
        mProgress = ProgressDialog.show(getActivity(), null, "Loading...", true, false);
        mAchievementAdapter = new RVAchievementsAdapter(mAchievements);
        mAchievementList.setAdapter(mAchievementAdapter);
        
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

    @Override
    public void achievementReceived(List<Achievement> achievement) {
        mAchievements.addAll(achievement);
        mAchievementAdapter.notifyDataSetChanged();
        mProgress.dismiss();
    }


    public interface OnFragmentInteractionListener {
        
    }

}
