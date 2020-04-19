package com.chabbal.slidingdotsplash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;

import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Johny on 28/01/2017.
 */

public class SlidingSplashView extends FrameLayout {


    private ViewPager mViewPager;
    private ImageViewPagerAdapter mViewPagerAdapter;
    private OnSetImageListener mOnSetImageListener;
    ViewPager.OnPageChangeListener mOnPageChangeListener;
    TabLayout tabLayout;
    int currentPage = 0;
    int NUM_PAGES = 4;

    public SlidingSplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public SlidingSplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    @SuppressLint("Range")
    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.sliding_splash_view,this);
        mViewPager = (ViewPager) findViewById(R.id.pager_splash);
        mViewPagerAdapter = new ImageViewPagerAdapter(context,mOnSetImageListener);
        if(!isInEditMode())
        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlidingSplashView, 0,
                    0);
            int id = typedArray.getResourceId(R.styleable.SlidingSplashView_imageResources,0);
//            if(id != 0){
//                TypedArray typed = context.getResources().obtainTypedArray(id);
//                String[] drawables = new String[typed.length()];
//                for(int i = 0 ; i < drawables.length ; ++i){
//                    drawables[i] = typed.getString(i);
//                }
//                typed.recycle();
//                mViewPagerAdapter.setImageResources(drawables);
//            }
            typedArray.recycle();
        }
        mViewPager.setAdapter(mViewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mViewPager,true);

//
    }

    public void setImageResources(String[] imageResources){
        mViewPagerAdapter.setImageResources(imageResources);
        NUM_PAGES = imageResources.length;

    }
    public void setPaddingBottom(int top,int bottom,int right,int left){
        tabLayout.setPadding(left,top,right,bottom);
    }
    public void setAutoPage(){
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES - 1) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);

            }
        };
       Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 0, 4000);
    }
    public void setAnimPage(){
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

    }



    public void setOnShowImageListener(OnSetImageListener onShowImageListener){
        mOnSetImageListener = onShowImageListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mViewPagerAdapter.setOnPagerItemClick(onItemClickListener);
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener){
        mViewPager.addOnPageChangeListener(mOnPageChangeListener = onPageChangeListener);
    }

    public void removeOnPageChangeListener(){
        mViewPager.removeOnPageChangeListener(mOnPageChangeListener);
    }

}
