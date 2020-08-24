package com.bluewater.commonpopuplib;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Utils
{
    /**
     * 获得屏幕宽度px(像素)
     *
     * @param context
     * @return
     */
    public static int getScreenWidthPX(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        int width = outMetrics.widthPixels;         // 屏幕宽度（像素）
        return width;
    }

    /**
     * sp转px
     * @param resources
     * @param sp
     * @return
     */
    public static float sp2px(Resources resources, float sp)
    {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    /**
     * dp转px
     * @param resources
     * @param dp
     * @return
     */
    public static float dp2px(Resources resources, float dp)
    {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

}
