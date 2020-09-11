package com.bluewater.commonpopuplib.Wheel.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bluewater.commonpopuplib.R;
import com.bluewater.commonpopuplib.Wheel.adapter.ArrayWheelAdapter;
import com.bluewater.commonpopuplib.Wheel.listener.OnItemSelectedListener;

import java.util.ArrayList;

public class WheelOptionView extends LinearLayout
{
    private Context mContext;

    private int textSize = 16;

    private WheelView wv_option1;
    private WheelView wv_option2;
    private WheelView wv_option3;

    private ArrayList<String> mOptionsItems_1;
    private ArrayList<ArrayList<String>> mOptionsItems_2;
    private ArrayList<ArrayList<ArrayList<String>>> mOptionsItems_3;

    private boolean linkage = false;        //是否联动

    private OnItemSelectedListener wheelListener_option1;
    private OnItemSelectedListener wheelListener_option2;

    public WheelOptionView(Context context)
    {
        this(context, null);     //调用同名构造方法
    }

    public WheelOptionView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);     //调用同名构造方法
    }

    public WheelOptionView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);    //通过上面的传参，实现无论系统调用，哪个构造方法，最终调用的是具有样式的构造方法

        this.mContext = context;

        LayoutInflater.from(context).inflate(R.layout.option_pick_view, this);
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView()
    {
        wv_option1 = findViewById(R.id.wv_opv_options1);        //选项1
        wv_option2 = findViewById(R.id.wv_opv_options2);        //选项2
        wv_option3 = findViewById(R.id.wv_opv_options3);        //选项3
    }

    public void setPicker(ArrayList<String> optionsItems)
    {
        setPicker(optionsItems, null, null, false);
    }

    public void setPicker(ArrayList<String> options1Items,
                          ArrayList<ArrayList<String>> options2Items,
                          boolean linkage)
    {
        setPicker(options1Items, options2Items, null, linkage);
    }

    public void setPicker(ArrayList<String> options1Items,
                          ArrayList<ArrayList<String>> options2Items,
                          ArrayList<ArrayList<ArrayList<String>>> options3Items,
                          boolean linkage)
    {
        this.linkage = linkage;
        this.mOptionsItems_1 = options1Items;
        this.mOptionsItems_2 = options2Items;
        this.mOptionsItems_3 = options3Items;

        int len = ArrayWheelAdapter.DEFAULT_LENGTH;
        if (this.mOptionsItems_3 == null)
            len = 8;
        if (this.mOptionsItems_2 == null)
            len = 12;

        // 选项1
        wv_option1.setAdapter(new ArrayWheelAdapter(mOptionsItems_1, len));// 设置显示数据
        wv_option1.setCurrentItem(0);// 初始化时显示的数据

        // 选项2
        if (mOptionsItems_2 != null)
            wv_option2.setAdapter(new ArrayWheelAdapter(mOptionsItems_2.get(0)));// 设置显示数据
        wv_option2.setCurrentItem(wv_option1.getCurrentItem());// 初始化时显示的数据

        // 选项3
        if (mOptionsItems_3 != null)
            wv_option3.setAdapter(new ArrayWheelAdapter(mOptionsItems_3.get(0).get(0)));// 设置显示数据
        wv_option3.setCurrentItem(wv_option3.getCurrentItem());// 初始化时显示的数据

        wv_option1.setTextSize(textSize);
        wv_option2.setTextSize(textSize);
        wv_option3.setTextSize(textSize);

        if (this.mOptionsItems_2 == null)
            wv_option2.setVisibility(View.GONE);
        if (this.mOptionsItems_3 == null)
            wv_option3.setVisibility(View.GONE);

        // 联动监听器
        wheelListener_option1 = new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(int index)
            {
                int opt2Select = 0;

                if (mOptionsItems_2 != null)
                {
                    opt2Select = wv_option2.getCurrentItem();   //上一个opt2的选中位置
                    //新opt2的位置，判断如果旧位置没有超过数据范围，则沿用旧位置，否则选中最后一项
                    opt2Select = opt2Select >= mOptionsItems_2.get(index).size() - 1 ? mOptionsItems_2.get(index).size() - 1 : opt2Select;

                    wv_option2.setAdapter(new ArrayWheelAdapter(mOptionsItems_2.get(index)));
                    wv_option2.setCurrentItem(opt2Select);
                }

                if (mOptionsItems_3 != null)
                {
                    wheelListener_option2.onItemSelected(opt2Select);
                }
            }
        };

        wheelListener_option2 = new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(int index)
            {
                if (mOptionsItems_3 != null)
                {
                    int opt1Select = wv_option1.getCurrentItem();
                    opt1Select = opt1Select >= mOptionsItems_3.size() - 1 ? mOptionsItems_3.size() - 1 : opt1Select;
                    index = index >= mOptionsItems_2.get(opt1Select).size() - 1 ? mOptionsItems_2.get(opt1Select).size() - 1 : index;
                    int opt3 = wv_option3.getCurrentItem();//上一个opt3的选中位置
                    //新opt3的位置，判断如果旧位置没有超过数据范围，则沿用旧位置，否则选中最后一项
                    opt3 = opt3 >= mOptionsItems_3.get(opt1Select).get(index).size() - 1 ? mOptionsItems_3.get(opt1Select).get(index).size() - 1 : opt3;

                    wv_option3.setAdapter(new ArrayWheelAdapter(mOptionsItems_3.get(wv_option1.getCurrentItem()).get(index)));
                    wv_option3.setCurrentItem(opt3);
                }
            }
        };

		// 添加联动监听
        if (options2Items != null && linkage)
            wv_option1.setOnItemSelectedListener(wheelListener_option1);
        if (options3Items != null && linkage)
            wv_option2.setOnItemSelectedListener(wheelListener_option2);
    }

    /**
     * 设置选项的单位
     *
     * @param label1
     * @param label2
     * @param label3
     */
    public void setLabels(String label1, String label2, String label3)
    {
        if (label1 != null)
            wv_option1.setLabel(label1);
        if (label2 != null)
            wv_option2.setLabel(label2);
        if (label3 != null)
            wv_option3.setLabel(label3);
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic)
    {
        wv_option1.setCyclic(cyclic);
        wv_option2.setCyclic(cyclic);
        wv_option3.setCyclic(cyclic);
    }

    /**
     * 分别设置第一二三级是否循环滚动
     *
     * @param cyclic1,cyclic2,cyclic3
     */
    public void setCyclic(boolean cyclic1, boolean cyclic2, boolean cyclic3)
    {
        wv_option1.setCyclic(cyclic1);
        wv_option2.setCyclic(cyclic2);
        wv_option3.setCyclic(cyclic3);
    }

    /**
     * 设置第二级是否循环滚动
     *
     * @param cyclic
     */
    public void setOption2Cyclic(boolean cyclic)
    {
        wv_option2.setCyclic(cyclic);
    }

    /**
     * 设置第三级是否循环滚动
     *
     * @param cyclic
     */
    public void setOption3Cyclic(boolean cyclic)
    {
        wv_option3.setCyclic(cyclic);
    }

    /**
     * 返回当前选中的结果
     * 三级联动效果，分三个级别索引，0，1，2
     *
     * @return
     */
    public String[] getResult()
    {
        int[] currentItems = new int[3];
        currentItems[0] = wv_option1.getCurrentItem();
        currentItems[1] = wv_option2.getCurrentItem();
        currentItems[2] = wv_option3.getCurrentItem();


        if (this.mOptionsItems_3 == null)
        {
            String[] result = new String[2];
            result[0] = mOptionsItems_1.get(currentItems[0]);
            result[1] = mOptionsItems_2.get(currentItems[0]).get(currentItems[1]);

            return result;
        }
        if (this.mOptionsItems_2 == null)
        {
            String[] result = new String[1];
            result[0] = mOptionsItems_1.get(currentItems[0]);

            return result;
        }


        String[] result = new String[3];
        result[0] = mOptionsItems_1.get(currentItems[0]);
        result[1] = mOptionsItems_2.get(currentItems[0]).get(currentItems[1]);
        result[2] = mOptionsItems_3.get(currentItems[0]).get(currentItems[1]).get(currentItems[2]);

        return result;
    }

    public void setCurrentItems(int option1, int option2, int option3)
    {
        if (linkage)
        {
            itemSelected(option1, option2, option3);
        }
        wv_option1.setCurrentItem(option1);
        wv_option2.setCurrentItem(option2);
        wv_option3.setCurrentItem(option3);
    }

    private void itemSelected(int opt1Select, int opt2Select, int opt3Select)
    {
        if (mOptionsItems_2 != null)
        {
            wv_option2.setAdapter(new ArrayWheelAdapter(mOptionsItems_2.get(opt1Select)));
            wv_option2.setCurrentItem(opt2Select);
        }
        if (mOptionsItems_3 != null)
        {
            wv_option3.setAdapter(new ArrayWheelAdapter(mOptionsItems_3.get(opt1Select).get(opt2Select)));
            wv_option3.setCurrentItem(opt3Select);
        }
    }

    /**
     * 设置单前文字
     *
     * @param textSize
     */
    public void setTextSize(float textSize)
    {
        wv_option1.setTextSize(textSize);
        wv_option2.setTextSize(textSize);
        wv_option3.setTextSize(textSize);
    }

}

