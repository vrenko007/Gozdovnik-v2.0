package vrenko.semrov.gorisek.gozdovnik.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

import vrenko.semrov.gorisek.gozdovnik.Helpers.RoundedImage;
import vrenko.semrov.gorisek.gozdovnik.LoginDispatchActivity;
import vrenko.semrov.gorisek.gozdovnik.QuizListActivity;
import vrenko.semrov.gorisek.gozdovnik.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserInfoFragment extends Fragment {
    public static UserInfoFragment newInstance() {
        UserInfoFragment fragment = new UserInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_info, container, false);

        TextView tv = (TextView)v.findViewById(R.id.userName);

        tv.setText(ParseUser.getCurrentUser().getString("name"));

        ImageView iv1 = (ImageView) v.findViewById(R.id.userImage);

        iv1.setImageBitmap(RoundedImage.getCroppedBitmap(BitmapFactory.decodeResource(getActivity().getResources(),
                R.drawable.placeholder_user),50));

        ImageView iv = (ImageView) v.findViewById(R.id.logoutImage);



        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                startActivity(new Intent(getActivity(), LoginDispatchActivity.class));
                getActivity().finish();
            }
        });

        Button quizi = (Button) v.findViewById(R.id.quizzes);

        quizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizListActivity.class);
                startActivity(intent);
            }
        });

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


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
