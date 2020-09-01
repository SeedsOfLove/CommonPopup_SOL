package com.bluewater.commonpopuplib;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class PopupViewPageAdapter extends PagerAdapter
{
    private ArrayList<ImageView> imageViews;

    public PopupViewPageAdapter(ArrayList<ImageView> imageViews)
    {
        this.imageViews = imageViews;
    }

    /**
     * 获取当前要显示对象的数量
     */
    @Override
    public int getCount()
    {
        return imageViews.size();
    }

    /**
     * 判断是否用对象生成界面
     */
    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0 == arg1;
    }

    /**
     * 从ViewGroup中移除当前对象（图片）
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView(imageViews.get(position));
    }

    /**
     * 当前要显示的对象（图片）
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

}
