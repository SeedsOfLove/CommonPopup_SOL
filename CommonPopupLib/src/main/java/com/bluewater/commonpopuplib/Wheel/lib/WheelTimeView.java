package com.bluewater.commonpopuplib.Wheel.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bluewater.commonpopuplib.R;
import com.bluewater.commonpopuplib.Wheel.adapter.NumericWheelAdapter;
import com.bluewater.commonpopuplib.Wheel.listener.OnItemSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class WheelTimeView extends LinearLayout
{
    // 选择模式——年月日时分，年月日，年月日时 , 时分，月日时分 ,年月
    public enum Type
    {
        ALL, YEAR_MONTH_DAY, YEAR_MONTH_DAY_HOUR, HOURS_MINS, MONTH_DAY_HOUR_MIN, YEAR_MONTH
    }

    public static final int DEFULT_START_YEAR = 1990;
    public static final int DEFULT_END_YEAR = 2100;

    private Context mContext;
    private Type type;

    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_mins;

    private int startYear = DEFULT_START_YEAR;
    private int endYear = DEFULT_END_YEAR;

    public WheelTimeView(Context context)
    {
        this(context, null);     //调用同名构造方法
    }

    public WheelTimeView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);     //调用同名构造方法
    }

    public WheelTimeView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);    //通过上面的传参，实现无论系统调用，哪个构造方法，最终调用的是具有样式的构造方法

        this.mContext = context;
        this.type = Type.ALL;

        LayoutInflater.from(context).inflate(R.layout.time_picker_view, this);
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView()
    {
        wv_year = findViewById(R.id.wv_tpv_year);       //年
        wv_month = findViewById(R.id.wv_tpv_month);     //月
        wv_day = findViewById(R.id.wv_tpv_day);         //日
        wv_hours = findViewById(R.id.wv_tpv_hour);      //时
        wv_mins = findViewById(R.id.wv_tpv_min);        //分
    }

    /**
     * 设置日期类型
     * @param type
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /**
     * @Description: TODO 弹出日期时间选择器
     */
    public void setPicker(int year, int month, int day, int hour, int minute)
    {
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

        // 年
        wv_year.setAdapter(new NumericWheelAdapter(startYear, endYear));// 设置"年"的显示数据
        wv_year.setLabel(mContext.getString(R.string.pickerview_year));// 添加文字
        wv_year.setCurrentItem(year - startYear);// 初始化时显示的数据

        // 月
        wv_month.setAdapter(new NumericWheelAdapter(1, 12));
        wv_month.setLabel(mContext.getString(R.string.pickerview_month));
        wv_month.setCurrentItem(month);

        // 日
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (list_big.contains(String.valueOf(month + 1)))
        {
            wv_day.setAdapter(new NumericWheelAdapter(1, 31));
        }
        else if (list_little.contains(String.valueOf(month + 1)))
        {
            wv_day.setAdapter(new NumericWheelAdapter(1, 30));
        }
        else
        {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                wv_day.setAdapter(new NumericWheelAdapter(1, 29));
            else
                wv_day.setAdapter(new NumericWheelAdapter(1, 28));
        }
        wv_day.setLabel(mContext.getString(R.string.pickerview_day));
        wv_day.setCurrentItem(day - 1);

        //时
        wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
        wv_hours.setLabel(mContext.getString(R.string.pickerview_hours));// 添加文字
        wv_hours.setCurrentItem(hour);

        //分
        wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
        wv_mins.setLabel(mContext.getString(R.string.pickerview_minutes));// 添加文字
        wv_mins.setCurrentItem(minute);

        // 添加"年"监听
        OnItemSelectedListener wheelListener_year = new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(int index)
            {
                int year_num = index + startYear;
                // 判断大小月及是否闰年,用来确定"日"的数据
                int maxItem;
                if (list_big
                        .contains(String.valueOf(wv_month.getCurrentItem() + 1)))
                {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                    maxItem = 31;
                }
                else if (list_little.contains(String.valueOf(wv_month
                        .getCurrentItem() + 1)))
                {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                    maxItem = 30;
                }
                else
                {
                    if ((year_num % 4 == 0 && year_num % 100 != 0)
                            || year_num % 400 == 0)
                    {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                        maxItem = 29;
                    }
                    else
                    {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                        maxItem = 28;
                    }
                }
                if (wv_day.getCurrentItem() > maxItem - 1)
                {
                    wv_day.setCurrentItem(maxItem - 1);
                }
            }
        };

        // 添加"月"监听
        OnItemSelectedListener wheelListener_month = new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(int index)
            {
                int month_num = index + 1;
                int maxItem;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (list_big.contains(String.valueOf(month_num)))
                {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                    maxItem = 31;
                }
                else if (list_little.contains(String.valueOf(month_num)))
                {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                    maxItem = 30;
                }
                else
                {
                    if (((wv_year.getCurrentItem() + startYear) % 4 == 0 && (wv_year
                            .getCurrentItem() + startYear) % 100 != 0)
                            || (wv_year.getCurrentItem() + startYear) % 400 == 0)
                    {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                        maxItem = 29;
                    }
                    else
                    {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                        maxItem = 28;
                    }
                }
                if (wv_day.getCurrentItem() > maxItem - 1)
                {
                    wv_day.setCurrentItem(maxItem - 1);
                }

            }
        };
        wv_year.setOnItemSelectedListener(wheelListener_year);
        wv_month.setOnItemSelectedListener(wheelListener_month);

        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = 5;
        switch (type)
        {
            case ALL:
                textSize = textSize * 3;
                break;
            case YEAR_MONTH_DAY:
                textSize = textSize * 4;
                wv_hours.setVisibility(View.GONE);
                wv_mins.setVisibility(View.GONE);
                break;
            case YEAR_MONTH_DAY_HOUR:
                textSize = textSize * 3;
                wv_mins.setVisibility(View.GONE);
                break;
            case HOURS_MINS:
                textSize = textSize * 4;
                wv_year.setVisibility(View.GONE);
                wv_month.setVisibility(View.GONE);
                wv_day.setVisibility(View.GONE);
                break;
            case MONTH_DAY_HOUR_MIN:
                textSize = textSize * 3;
                wv_year.setVisibility(View.GONE);
                break;
            case YEAR_MONTH:
                textSize = textSize * 4;
                wv_day.setVisibility(View.GONE);
                wv_hours.setVisibility(View.GONE);
                wv_mins.setVisibility(View.GONE);
        }
        setTextSize(textSize);
    }

    /**
     * 设置时间字体大小
     *
     * @param textSize
     */
    public void setTextSize(float textSize)
    {
        wv_day.setTextSize(textSize);
        wv_month.setTextSize(textSize);
        wv_year.setTextSize(textSize);
        wv_hours.setTextSize(textSize);
        wv_mins.setTextSize(textSize);
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic)
    {
        wv_year.setCyclic(cyclic);
        wv_month.setCyclic(cyclic);
        wv_day.setCyclic(cyclic);
        wv_hours.setCyclic(cyclic);
        wv_mins.setCyclic(cyclic);
    }

    /**
     * 获取时间结果（含0）
     * @return
     */
    public String getTimeWithZero()
    {
        String result = "";

        String year = String.valueOf(wv_year.getCurrentItem() + startYear);
        String month = String.valueOf(wv_month.getCurrentItem() + 1);
        String day = String.valueOf(wv_day.getCurrentItem() + 1);
        String hour = String.valueOf(wv_hours.getCurrentItem());
        String mintue = String.valueOf(wv_mins.getCurrentItem());

        if (month.length() == 1)
        {
            month = "0" + month;
        }
        if (day.length() == 1)
        {
            day = "0" + day;
        }
        if (hour.length() == 1)
        {
            hour = "0" + hour;
        }
        if (mintue.length() == 1)
        {
            mintue = "0" + mintue;
        }

        switch (type)
        {
            case ALL:
                result = year + "-" + month + "-" + day + "-" + hour + "-" + mintue;
                break;
            case YEAR_MONTH_DAY:
                result = year + "-" + month + "-" + day;
                break;
            case YEAR_MONTH_DAY_HOUR:
                result = year + "-" + month + "-" + day + "-" + hour;
                break;
            case HOURS_MINS:
                result = hour + "-" + mintue;
                break;
            case MONTH_DAY_HOUR_MIN:
                result = month + "-" + day + "-" + hour + "-" + mintue;
                break;
            case YEAR_MONTH:
                result = year + "-" + month;
                break;
        }

        return result;
    }

    /**
     * 获取时间结果
     * @return
     */
    public String getTime()
    {
        String result = "";

        String year = String.valueOf(wv_year.getCurrentItem() + startYear);
        String month = String.valueOf(wv_month.getCurrentItem() + 1);
        String day = String.valueOf(wv_day.getCurrentItem() + 1);
        String hour = String.valueOf(wv_hours.getCurrentItem());
        String mintue = String.valueOf(wv_mins.getCurrentItem());

        switch (type)
        {
            case ALL:
                result = year + "-" + month + "-" + day + "-" + hour + "-" + mintue;
                break;
            case YEAR_MONTH_DAY:
                result = year + "-" + month + "-" + day;
                break;
            case YEAR_MONTH_DAY_HOUR:
                result = year + "-" + month + "-" + day + "-" + hour;
                break;
            case HOURS_MINS:
                result = hour + "-" + mintue;
                break;
            case MONTH_DAY_HOUR_MIN:
                result = month + "-" + day + "-" + hour + "-" + mintue;
                break;
            case YEAR_MONTH:
                result = year + "-" + month;
                break;
        }

        return result;
    }

    public int getStartYear()
    {
        return startYear;
    }

    public void setStartYear(int startYear)
    {
        this.startYear = startYear;
    }

    public int getEndYear()
    {
        return endYear;
    }

    public void setEndYear(int endYear)
    {
        this.endYear = endYear;
    }

}

