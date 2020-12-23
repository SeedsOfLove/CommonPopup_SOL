package com.bluewater.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bluewater.commonpopuplib.CommonPopup;
import com.bluewater.commonpopuplib.CommonPopupImpl;
import com.bluewater.commonpopuplib.Wheel.lib.WheelTimeView;
import com.bluewater.commonpopuplib.progress.DonutProgressBar;
import com.bluewater.commonpopuplib.progress.HorizontalProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mContext = this;
    }

    public void onClick3(View view)
    {
        final CommonPopup popup = new CommonPopupImpl(mContext, false);
        popup.showBasicDialog("标题", "基础弹窗", new CommonPopupImpl.OnBasicDialogClickListener()
        {
            @Override
            public void onBasicDialogOkButtonClick()
            {
                Toast.makeText(mContext, "点击了确定按钮", Toast.LENGTH_SHORT).show();
                popup.dialogDismiss();      //弹窗消失
            }
        });
    }

    public void onClick4(View view)
    {
        final CommonPopup popup = new CommonPopupImpl(mContext, false);
        popup.showErrorDialog("标题", "错误弹窗", new CommonPopupImpl.OnErrorDialogClickListener()
        {
            @Override
            public void onErrorDialogOkButtonClick()
            {
                Toast.makeText(mContext, "点击了确定按钮", Toast.LENGTH_SHORT).show();
                popup.dialogDismiss();      //弹窗消失
            }
        });
    }

    public void onClick5(View view)
    {
        final CommonPopup popup = new CommonPopupImpl(mContext, false);
        popup.showConfirmDialog("标题", "确认弹窗", new CommonPopupImpl.OnConfirmDialogClickListener()
        {
            @Override
            public void onConfirmDialogOkButtonClick()
            {
                Toast.makeText(mContext, "点击了确定按钮", Toast.LENGTH_SHORT).show();
                popup.dialogDismiss();      //弹窗消失
            }

            @Override
            public void onConfirmDialogCancelButtonClick()
            {
                Toast.makeText(mContext, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                popup.dialogDismiss();      //弹窗消失
            }
        });

    }

    public void onClick6(View view)
    {
        CommonPopup popup = new CommonPopupImpl(mContext, true);
        popup.showLoadDialog("加载弹窗");
    }

    public void onClick7(View view)
    {
        final CommonPopup popup = new CommonPopupImpl(mContext, false);
        popup.showEditDialog("输入弹窗", new CommonPopupImpl.OnEditDialogClickListener()
        {
            @Override
            public void onEditDialogOkButtonClick(String editBack)
            {
                Toast.makeText(mContext, editBack, Toast.LENGTH_SHORT).show();
                popup.dialogDismiss();      //弹窗消失
            }
        });
    }

    public void onClick8(View view)
    {
        List<String> listData = new ArrayList<>();
        listData.add("条目1");
        listData.add("条目2");
        listData.add("条目3");
        listData.add("条目4");

        final CommonPopup popup = new CommonPopupImpl(mContext, false);
        popup.showSpinnerDialog("下拉弹窗", listData, new CommonPopupImpl.OnSpinnerDialogClickListener()
        {
            @Override
            public void onSpinnerDialogOkButtonClick(String value)
            {
                Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
                popup.dialogDismiss();      //弹窗消失
            }
        });
    }

    public void onClick9(View view)
    {
        List<String> listData = new ArrayList<>();
        listData.add("条目1");
        listData.add("条目2");
        listData.add("条目3");
        listData.add("条目4");

        final CommonPopup popup = new CommonPopupImpl(mContext, false);
        popup.showSingleChoiceDialog("单选弹窗", listData, new CommonPopupImpl.OnSingleChoiceDialogClickListener()
        {
            @Override
            public void onSingleChoiceDialogOkButtonClick(String value)
            {
                Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
                popup.dialogDismiss();      //弹窗消失
            }
        });
    }

    public void onClick10(View view)
    {
        List<String> listData = new ArrayList<>();
        listData.add("条目1");
        listData.add("条目2");
        listData.add("条目3");
        listData.add("条目4");

        final CommonPopup popup = new CommonPopupImpl(mContext, false);
        popup.showMultipleChoiceDialog("多选弹窗", listData, new CommonPopupImpl.OnMultipleChoiceDialogClickListener()
        {
            @Override
            public void onMultipleChoiceDialogOkButtonClick(List<String> listResult)
            {
                String result = "";
                for (int i = 0; i < listResult.size(); i++)
                {
                    result = result + listResult.get(i);
                }

                Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
                popup.dialogDismiss();      //弹窗消失
            }
        });
    }

    public void onClick11(View view)
    {
        final CommonPopup popup = new CommonPopupImpl(mContext, true);
        final HorizontalProgressBar progressBar = popup.showLineLoadDialogWithValue(null);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int ss = 0;
                while (ss <= 100)
                {
                    try
                    {
                        progressBar.setCurrentProgress(ss);     //设置进度

                        Thread.sleep(500); //设置暂停的时间 0.5 秒
                        ss++ ;

                        if (ss == 101)
                        {
                            popup.dialogDismiss();
                        }

                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void onClick12(View view)
    {
        final CommonPopup popup = new CommonPopupImpl(mContext, true);
        final DonutProgressBar progressBar = popup.showDonutLoadDialogWithValue(null);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int ss = 0;
                while (ss <= 100)
                {
                    try
                    {
                        progressBar.setProgress(ss);     //设置进度

                        Thread.sleep(500); //设置暂停的时间 0.5 秒
                        ss++ ;

                        if (ss == 101)
                        {
                            popup.dialogDismiss();
                        }

                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void onClick13(View view)
    {
        int[] imgs = {R.mipmap.guidance_tips_img1, R.mipmap.guidance_tips_img2};    //要显示的图片资源
        int[] tips = {R.string.guidance_tips1, R.string.guidance_tips2};                //对应的提示

        final CommonPopup popup = new CommonPopupImpl(mContext, true);
        popup.showGuidanceTipsDialog(imgs, tips, "进入", new CommonPopupImpl.OnGuidanceTipsDialogClickListener()
        {
            @Override
            public void onGuidanceTipsDialogOkButtonClick()
            {
                popup.dialogDismiss(); //弹窗消失
            }
        });
    }

    public void onClick14(View view)
    {
        ArrayList<String> listData = new ArrayList<>();
        listData.add("条目1");
        listData.add("条目2");
        listData.add("条目3");
        listData.add("条目4");
        listData.add("条目5");
        listData.add("条目6");

        final CommonPopup popup = new CommonPopupImpl(mContext, false);
        popup.showSingleChoiceWheelDialog("条目列表", listData, new CommonPopupImpl.OnSingleChoiceWheelDialogClickListener()
        {
            @Override
            public void onSingleChoiceWheelDialogOkButtonClick(String value)
            {
                Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
                popup.dialogDismiss(); //弹窗消失
            }
        });

    }

    public void onClick15(View view)
    {
        final CommonPopup popup = new CommonPopupImpl(mContext, false);
        popup.showTimePickerWheelDialog("请选择日期", WheelTimeView.Type.ALL, new CommonPopupImpl.OnTimePickerDialogClickListener()
        {
            @Override
            public void onTimePickerDialogOkButtonClick(String value)
            {
                Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
                popup.dialogDismiss(); //弹窗消失
            }
        });

    }

    public void onClick16(View view)
    {
        ArrayList<String> optionsItems_1 = new ArrayList<>();
        ArrayList<ArrayList<String>> optionsItems_2 = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<String>>> optionsItems_3 = new ArrayList<>();

        //选项1
        optionsItems_1.add("江苏");
        optionsItems_1.add("重庆");
        optionsItems_1.add("广西");

        //选项2
        ArrayList<String> optionsItems_2_01 = new ArrayList<>();
        optionsItems_2_01.add("南京");
        optionsItems_2_01.add("淮安");
        optionsItems_2_01.add("常州");
        optionsItems_2_01.add("苏州");
        ArrayList<String> optionsItems_2_02 = new ArrayList<>();
        optionsItems_2_02.add("南岸");
        optionsItems_2_02.add("江北");
        optionsItems_2_02.add("渝中");
        ArrayList<String> optionsItems_2_03 = new ArrayList<>();
        optionsItems_2_03.add("桂林");
        optionsItems_2_03.add("南宁");
        optionsItems_2.add(optionsItems_2_01);
        optionsItems_2.add(optionsItems_2_02);
        optionsItems_2.add(optionsItems_2_03);

        //选项3
        ArrayList<ArrayList<String>> optionsItems_3_01 = new ArrayList<>();
        ArrayList<ArrayList<String>> optionsItems_3_02 = new ArrayList<>();
        ArrayList<ArrayList<String>> optionsItems_3_03 = new ArrayList<>();

        ArrayList<String> optionsItems_3_01_01 = new ArrayList<>();
        optionsItems_3_01_01.add("南京县城1");
        optionsItems_3_01_01.add("南京县城2");
        optionsItems_3_01_01.add("南京县城3");
        optionsItems_3_01_01.add("南京县城4");
        ArrayList<String> optionsItems_3_01_02 = new ArrayList<>();
        optionsItems_3_01_02.add("淮安县城1");
        optionsItems_3_01_02.add("淮安县城2");
        optionsItems_3_01_02.add("淮安县城3");
        optionsItems_3_01_02.add("淮安县城4");
        ArrayList<String> optionsItems_3_01_03 = new ArrayList<>();
        optionsItems_3_01_03.add("常州县城1");
        optionsItems_3_01_03.add("常州县城2");
        optionsItems_3_01_03.add("常州县城3");
        ArrayList<String> optionsItems_3_01_04 = new ArrayList<>();
        optionsItems_3_01_04.add("苏州县城1");
        optionsItems_3_01_04.add("苏州县城2");
        optionsItems_3_01_04.add("苏州县城3");

        optionsItems_3_01.add(optionsItems_3_01_01);
        optionsItems_3_01.add(optionsItems_3_01_02);
        optionsItems_3_01.add(optionsItems_3_01_03);
        optionsItems_3_01.add(optionsItems_3_01_04);

        ArrayList<String> optionsItems_3_02_01 = new ArrayList<>();
        optionsItems_3_02_01.add("南岸县城1");
        optionsItems_3_02_01.add("南岸县城2");
        optionsItems_3_02_01.add("南岸县城3");
        optionsItems_3_02_01.add("南岸县城4");
        optionsItems_3_02_01.add("南岸县城5");
        ArrayList<String> optionsItems_3_02_02 = new ArrayList<>();
        optionsItems_3_02_02.add("江北县城1");
        optionsItems_3_02_02.add("江北县城2");
        optionsItems_3_02_02.add("江北县城3");
        optionsItems_3_02_02.add("江北县城4");
        ArrayList<String> optionsItems_3_02_03 = new ArrayList<>();
        optionsItems_3_02_03.add("渝中县城1");
        optionsItems_3_02_03.add("渝中县城2");
        optionsItems_3_02_03.add("渝中县城3");
        optionsItems_3_02_03.add("渝中县城4");

        optionsItems_3_02.add(optionsItems_3_02_01);
        optionsItems_3_02.add(optionsItems_3_02_02);
        optionsItems_3_02.add(optionsItems_3_02_03);

        ArrayList<String> optionsItems_3_03_01 = new ArrayList<>();
        optionsItems_3_03_01.add("桂林县城");
        ArrayList<String> optionsItems_3_03_02 = new ArrayList<>();
        optionsItems_3_03_02.add("南宁县城");
        optionsItems_3_03.add(optionsItems_3_03_01);
        optionsItems_3_03.add(optionsItems_3_03_02);

        optionsItems_3.add(optionsItems_3_01);
        optionsItems_3.add(optionsItems_3_02);
        optionsItems_3.add(optionsItems_3_03);

        final CommonPopup popup = new CommonPopupImpl(mContext, false);
        popup.showOptionPickerWheelDialog("请选择", optionsItems_1, optionsItems_2, optionsItems_3,
                "省", "市", null, new CommonPopupImpl.OnOptionPickerDialogClickListener()
                {
                    @Override
                    public void onOptionPickerDialogOkButtonClick(String[] value)
                    {
                        String result = value[0] + "省" + value[1] + "市" + value[2];
                        Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
                        popup.dialogDismiss(); //弹窗消失
                    }
                });

    }

    public void onClick17(View view)
    {
        final CommonPopup popup = new CommonPopupImpl(mContext, false);
        popup.showWarningDialog("标题", "警告弹窗", new CommonPopupImpl.OnWarningDialogClickListener()
        {
            @Override
            public void onWarningDialogOkButtonClick()
            {
                Toast.makeText(mContext, "点击了确定按钮", Toast.LENGTH_SHORT).show();
                popup.dialogDismiss();      //弹窗消失
            }
        });
    }

    public void onClick18(View view)
    {
        final CommonPopup popup = new CommonPopupImpl(mContext, false);

        List<String> listItem = new ArrayList<>();
        listItem.add("拍照");
        listItem.add("从相册选择");
        listItem.add("自定义");

        popup.showBottomSelectDialog(listItem, new CommonPopupImpl.OnBottomSelectDialogClickListener()
        {
            @Override
            public void onBottomSelectDialogItemClick(String value)
            {
                Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
                popup.dialogDismiss();      //弹窗消失
            }
        });
    }
}