package vn.mrlongg71.vnfood.src.module.profile.presenter;

import vn.mrlongg71.vnfood.src.model.User;
import vn.mrlongg71.vnfood.src.module.profile.model.ModelProfile;
import vn.mrlongg71.vnfood.src.module.profile.view.IViewProfile;

public class PresenterProfile implements IPresenterProfile{
    IViewProfile iViewProfile;
    ModelProfile modelProfile;

    public PresenterProfile(IViewProfile iViewProfile) {
        this.iViewProfile = iViewProfile;
        modelProfile = new ModelProfile();
    }

    @Override
    public void getProfile() {
        modelProfile.profile();
    }

    @Override
    public void resultGetProfile(boolean success, User user) {

    }
}
