package com.example.fyp;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mImageIds = new int[]{R.drawable.welcomepage1, R.drawable.welcomepage2};
    ImageAdapter(Context context) {
        mContext = context;
    }
    @Override
    public int getCount() {
        return mImageIds.length;
    }





    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView, 0);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}