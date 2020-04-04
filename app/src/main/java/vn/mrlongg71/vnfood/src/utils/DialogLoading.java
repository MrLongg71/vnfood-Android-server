package vn.mrlongg71.vnfood.src.utils;

import android.view.View;

import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;

public class DialogLoading {
    public static void LoadingGoogle(boolean isLoading, GoogleProgressBar progressbar){
        if(isLoading == true){
            progressbar.setVisibility(View.VISIBLE);
        }else{
            progressbar.setVisibility(View.GONE);

        }
    }
}
