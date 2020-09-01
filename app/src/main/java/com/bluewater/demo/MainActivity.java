package com.bluewater.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bluewater.commonpopuplib.CommonPopup;
import com.bluewater.commonpopuplib.CommonPopupImpl;
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












}