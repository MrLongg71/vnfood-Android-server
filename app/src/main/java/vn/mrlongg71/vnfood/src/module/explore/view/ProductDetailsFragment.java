package vn.mrlongg71.vnfood.src.module.explore.view;


import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chabbal.slidingdotsplash.SlidingSplashView;

import java.util.Objects;

import vn.mrlongg71.vnfood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {

    private SlidingSplashView introProductDetail;
    private LinearLayout bodyProductDetail,layoutWriteComment;
    private TextView txtWriteComment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        init(view);
//        getSizeDevice();
        return view;
    }
    private void getSizeDevice() {
        ViewGroup.LayoutParams paramsBody = bodyProductDetail.getLayoutParams();
        ViewGroup.LayoutParams paramsSplash = introProductDetail.getLayoutParams();
        Display display = Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        paramsBody.height = (int) (size.y * 0.6);
        paramsSplash.height = (int) (size.y * 0.42);
        introProductDetail.setLayoutParams(paramsSplash);
        bodyProductDetail.setLayoutParams(paramsBody);

    }

    @SuppressLint("CutPasteId")
    private void init(View view) {
        introProductDetail = view.findViewById(R.id.introProductDetail);

        bodyProductDetail = view.findViewById(R.id.bodyProductDetail);
        txtWriteComment = view.findViewById(R.id.txtWriteComment);
        layoutWriteComment = view.findViewById(R.id.layoutWriteComment);
        txtWriteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutWriteComment.setVisibility(View.VISIBLE);
            }
        });
        introProductDetail.setImageResources(new String[]{"https://media.cooky.vn/recipe/g6/50312/s320x240/cooky-recipe-637081295692148739.JPG"});

    }

}
