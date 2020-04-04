package vn.mrlongg71.vnfood.src.module.login.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ScrollView;

import vn.mrlongg71.vnfood.src.model.StoreKey;
import vn.mrlongg71.vnfood.src.module.login.model.ModelLogin;
import vn.mrlongg71.vnfood.src.module.login.view.IViewLogin;

import static android.content.Context.MODE_PRIVATE;

public class PresenterLogin implements IPresenterLogin {
    private Context context;
    private IViewLogin iViewLogin;
    private ModelLogin modelLogin;
    public static  String token;
    public PresenterLogin(IViewLogin iViewLogin,Context context) {
        this.iViewLogin = iViewLogin;
        this.context = context;
        modelLogin = new ModelLogin();
    }



    @Override
    public void handlerLogin(String email, String password) {
        modelLogin.loginUser(email,password,this);
    }

    @Override
    public void resultLogin(boolean success, String msg) {
        if(success){
            //login success -> save token shared
            //msg = token
            SharedPreferences pref = context.getSharedPreferences("User", MODE_PRIVATE);
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = pref.edit();
            editor.putString("token" , msg);
            editor.apply();
            token = msg;
            StoreKey.setToken(msg);
            iViewLogin.onSuccess();
        }else {
            iViewLogin.onFailed(msg);

        }

    }
}
