package vn.mrlongg71.vnfood.src.module.profile.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.model.User;
import vn.mrlongg71.vnfood.src.module.profile.presenter.PresenterProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements IViewProfile {

    private PresenterProfile presenterProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_profile, container, false);
         presenterProfile = new PresenterProfile(this);
         presenterProfile.getProfile();
         return v;
    }

    @Override
    public void onSuccess(User user) {

    }

    @Override
    public void onFailed() {

    }
}
