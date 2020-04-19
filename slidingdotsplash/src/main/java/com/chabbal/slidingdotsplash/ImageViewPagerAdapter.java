package com.chabbal.slidingdotsplash;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Size;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


/**
 * Created by Johny on 11/01/2017.
 */

public class ImageViewPagerAdapter extends ViewPagerAdapter {

    private Context mContext;
    private OnItemClickListener mOnPagerItemClick;
    private OnSetImageListener mOnSetImageListener;
    private String mImageResources[];

    public ImageViewPagerAdapter(Context context,@NonNull OnSetImageListener onSetImageListener ) {
        mContext = context;
        mOnSetImageListener = onSetImageListener;
        mImageResources = new String[]{};
    }

    @Override
    public View getItem(final int position) {
        ImageView imageView = (ImageView)LayoutInflater.from(mContext).inflate(R.layout.item_view_pager_image,null,false);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnPagerItemClick != null){
                    mOnPagerItemClick.onPagerItemClick(v,position);
                }
            }
        });
        if(mOnSetImageListener != null){
            mOnSetImageListener.setImage(imageView,position);
        }
        else{
            Glide.with(mContext).load(mImageResources[position]).apply(new RequestOptions().placeholder(R.drawable.ic_photo_size_select_actual_black_24dp)).into(imageView);

        }
        return imageView;
    }


    @Override
    public int getCount() {
        return mImageResources.length;
    }

    public void setImageResources(String[] imageResources){
        mImageResources = imageResources;

        notifyDataSetChanged();
    }

    public void setOnPagerItemClick(@NonNull OnItemClickListener onPagerItemClickListener){
        mOnPagerItemClick = onPagerItemClickListener;
    }
}
