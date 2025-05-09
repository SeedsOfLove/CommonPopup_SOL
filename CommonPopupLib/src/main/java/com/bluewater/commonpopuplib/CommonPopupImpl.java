package com.bluewater.commonpopuplib;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bluewater.commonpopuplib.Wheel.adapter.ArrayWheelAdapter;
import com.bluewater.commonpopuplib.Wheel.lib.WheelOptionView;
import com.bluewater.commonpopuplib.Wheel.lib.WheelTimeView;
import com.bluewater.commonpopuplib.Wheel.lib.WheelView;
import com.bluewater.commonpopuplib.progress.DonutProgressBar;
import com.bluewater.commonpopuplib.progress.HorizontalProgressBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 通用弹窗实现
 */
public class CommonPopupImpl implements CommonPopup
{
    private Context mContext;
    private Dialog dialog;

    /**
     * 构造函数
     * @param context
     * @param isCancel  设置点击dialog以外区域能否取消Dialog  true：可以   false：不可以
     */
    public CommonPopupImpl(Context context, boolean isCancel)
    {
        this.mContext = context;

        // 创建Dialog
        dialog = new Dialog(mContext);

        dialog.setCancelable(isCancel);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));     //设置为圆角后有阴影
    }

    /**
     * 弹窗配置
     * @param view
     * @param animResId     动画
     * @param width         宽
     * @param height        高
     */
    private void dialogSet(View view, int animResId, int width, int height)
    {
        dialog.getWindow().setWindowAnimations(animResId);      //自定义对话框弹出、关闭动画效果
        dialog.show();
        dialog.setContentView(view);
        dialog.getWindow().setLayout(width, height);
    }

    /**
     * 弹窗消失
     */
    @Override
    public void dialogDismiss()
    {
        dialog.dismiss();
    }


    /*-----------------------------------基础弹窗----------------------------------------*/

    /**
     * 基础弹窗
     * @param strTitle  标题
     * @param strInfo   内容
     * @param drawable  图标
     */
    @Override
    public void showBasicDialog(String strTitle, String strInfo, Drawable drawable)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_basic_dialog, null);
        ImageView icon = view.findViewById(R.id.iv_basic_dialog_icon);
        TextView tvTitle = view.findViewById(R.id.tv_basic_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_basic_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_basic_dialog_ok);

        if (drawable != null)
        {
            icon.setImageDrawable(drawable);
        }

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        tvInfo.setText(strInfo);
        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();                   //对话框消失
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /**
     * 基础弹窗
     * @param strTitle  标题
     * @param strInfo   内容
     * @param drawable  图标
     * @param listener  确定按钮点击事件
     */
    @Override
    public void showBasicDialog(String strTitle, String strInfo, Drawable drawable, final OnBasicDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_basic_dialog, null);
        ImageView icon = view.findViewById(R.id.iv_basic_dialog_icon);
        TextView tvTitle = view.findViewById(R.id.tv_basic_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_basic_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_basic_dialog_ok);

        if (drawable != null)
        {
            icon.setImageDrawable(drawable);
        }

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        tvInfo.setText(strInfo);
        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onBasicDialogOkButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /*-----------------------------------基础弹窗END----------------------------------------*/



    /*-----------------------------------成功弹窗----------------------------------------*/

    /**
     * 成功弹窗
     *
     * @param strTitle      标题
     * @param strInfo       内容
     */
    @Override
    public void showSuccessDialog(String strTitle, String strInfo)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_success_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_success_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_success_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_success_dialog_ok);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        tvInfo.setText(strInfo);
        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();                   //对话框消失
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /**
     * 成功弹窗(含确认按钮点击事件)
     *
     * @param strTitle      标题
     * @param strInfo       内容
     * @param listener      确定按钮点击事件
     */
    @Override
    public void showSuccessDialog(String strTitle, String strInfo, final OnSuccessDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_success_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_success_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_success_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_success_dialog_ok);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        tvInfo.setText(strInfo);

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onSuccessDialogOkButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /*-----------------------------------成功弹窗END----------------------------------------*/



    /*-----------------------------------警告弹窗----------------------------------------*/

    /**
     * 警告弹窗(含确认按钮点击事件)
     *
     * @param strTitle      标题
     * @param strInfo       内容
     */
    @Override
    public void showWarningDialog(String strTitle, String strInfo)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_warning_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_warning_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_warning_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_warning_dialog_ok);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        tvInfo.setText(strInfo);
        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();                   //对话框消失
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /**
     * 警告弹窗
     *
     * @param strTitle      标题
     * @param strInfo       内容
     * @param listener      确定按钮点击事件
     */
    @Override
    public void showWarningDialog(String strTitle, String strInfo, final CommonPopupImpl.OnWarningDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_warning_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_warning_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_warning_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_warning_dialog_ok);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        tvInfo.setText(strInfo);
        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onWarningDialogOkButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /*-----------------------------------警告弹窗END----------------------------------------*/

    /*-----------------------------------错误弹窗----------------------------------------*/

    /**
     * 错误弹窗
     *
     * @param strTitle      标题
     * @param strInfo       内容
     */
    @Override
    public void showErrorDialog(String strTitle, String strInfo)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_error_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_error_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_error_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_error_dialog_ok);

        if (strTitle != null)
        {
            tvTitle.setText(strTitle);
        }

        tvInfo.setText(strInfo);
        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();                   //对话框消失
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /**
     * 错误弹窗
     *
     * @param strTitle      标题
     * @param strInfo       内容
     * @param listener      确定按钮点击事件
     */
    @Override
    public void showErrorDialog(String strTitle, String strInfo, final OnErrorDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_error_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_error_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_error_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_error_dialog_ok);

        if (strTitle != null)
        {
            tvTitle.setText(strTitle);
        }

        tvInfo.setText(strInfo);
        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onErrorDialogOkButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /*-----------------------------------错误弹窗END----------------------------------------*/


    /*-----------------------------------是否确认弹窗----------------------------------------*/

    /**
     * 是否确认弹窗
     *
     * @param strTitle      标题
     * @param strInfo       内容
     * @param listener      确定按钮点击事件
     */
    @Override
    public void showConfirmDialog(String strTitle, String strInfo, final OnConfirmDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_confirm_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_confirm_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_confirm_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_confirm_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_confirm_dialog_cancel);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        tvInfo.setText(strInfo);

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onConfirmDialogOkButtonClick();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onConfirmDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /**
     * 是否确认弹窗
     *
     * @param strTitle      标题
     * @param strInfo       内容
     * @param drawable      图标
     * @param listener      确定按钮点击事件
     */
    @Override
    public void showConfirmDialog(String strTitle, String strInfo, Drawable drawable, final OnConfirmDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_confirm_dialog, null);
        ImageView icon = view.findViewById(R.id.iv_confirm_dialog_icon);
        TextView tvTitle = view.findViewById(R.id.tv_confirm_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_confirm_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_confirm_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_confirm_dialog_cancel);

        if (drawable != null)
        {
            icon.setImageDrawable(drawable);
        }

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        tvInfo.setText(strInfo);

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onConfirmDialogOkButtonClick();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onConfirmDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /*-----------------------------------是否确认弹窗END----------------------------------------*/


    /*-----------------------------------加载弹窗----------------------------------------*/

    /**
     * 加载弹窗
     * @param strInfo
     */
    @Override
    public void showLoadDialog(String strInfo)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_load_dialog, null);

        TextView tvInfo = view.findViewById(R.id.tv_load_dialog_info);

        tvInfo.setText(strInfo);

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /**
     * 带进度的线型加载弹窗
     * @param strInfo
     * @return
     */
    @Override
    public HorizontalProgressBar showLineLoadDialogWithValue(String strInfo)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_line_load_dialog_with_value, null);

        TextView tvInfo  = view.findViewById(R.id.tv_line_load_dialog_with_value_info);
        HorizontalProgressBar horizontalProgressBar = view.findViewById(R.id.hsv_line_load_dialog_with_value);

        if (strInfo == null)
        {
            tvInfo.setVisibility(View.GONE);
        }
        else
        {
            tvInfo.setText(strInfo);
        }

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三

        return horizontalProgressBar;
    }

    /**
     * 带进度的圆圈加载弹窗
     * @param strInfo
     * @return
     */
    @Override
    public DonutProgressBar showDonutLoadDialogWithValue(String strInfo)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_donut_load_dialog_with_value, null);

        TextView tvInfo  = view.findViewById(R.id.tv_donut_load_dialog_with_value_info);
        DonutProgressBar donutProgressBar = view.findViewById(R.id.dpb_donut_load_dialog_with_value);

        if (strInfo == null)
        {
            tvInfo.setVisibility(View.GONE);
        }
        else
        {
            tvInfo.setText(strInfo);
        }

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三

        return donutProgressBar;
    }

    /*-----------------------------------加载弹窗END----------------------------------------*/


    /*-----------------------------------输入弹窗----------------------------------------*/

    /**
     * 输入弹窗
     * @param strTitle
     * @param listener
     */
    @Override
    public void showEditDialog(String strTitle, final OnEditDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_edit_dialog, null);

        TextView tvTitle = view.findViewById(R.id.tv_edit_dialog_title);
        final EditText editText = view.findViewById(R.id.et_edit_dialog);
        Button btnOk = view.findViewById(R.id.btn_edit_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_edit_dialog_cancel);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onEditDialogOkButtonClick(editText.getText().toString());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onEditDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /**
     * 输入弹窗
     * @param strTitle
     * @param strContent
     * @param listener
     */
    @Override
    public void showEditDialog(String strTitle, String strContent, final OnEditDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_edit_dialog, null);

        TextView tvTitle = view.findViewById(R.id.tv_edit_dialog_title);
        final EditText editText = view.findViewById(R.id.et_edit_dialog);
        Button btnOk = view.findViewById(R.id.btn_edit_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_edit_dialog_cancel);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        if (strContent != null)
        {
            editText.setText(strContent);
        }

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onEditDialogOkButtonClick(editText.getText().toString());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onEditDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /**
     * 私密输入弹窗
     * @param strTitle
     * @param listener
     */
    @Override
    public void showPrivacyEditDialog(String strTitle, final OnEditDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_edit_dialog, null);

        TextView tvTitle = view.findViewById(R.id.tv_edit_dialog_title);
        final EditText editText = view.findViewById(R.id.et_edit_dialog);
        Button btnOk = view.findViewById(R.id.btn_edit_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_edit_dialog_cancel);

           editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onEditDialogOkButtonClick(editText.getText().toString());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onEditDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /*-----------------------------------输入弹窗END----------------------------------------*/


    /*-----------------------------------下拉弹窗----------------------------------------*/

    /**
     * 下拉弹窗
     * @param strTitle
     * @param listData
     * @param listener
     */
    @Override
    public void showSpinnerDialog(String strTitle, List<String> listData, final OnSpinnerDialogClickListener listener)
    {
        final String[] strSelectValue = new String[1];

        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_spinner_dialog, null);

        TextView tvTitle = view.findViewById(R.id.tv_spinner_dialog_title);
        Spinner spinner = view.findViewById(R.id.sp_spinner_dialog);
        Button btnOk = view.findViewById(R.id.btn_spinner_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_spinner_dialog_cancel);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, listData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置spinner中每个条目的样式，引用android提供的布局文件
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                strSelectValue[0] = parent.getItemAtPosition(position).toString();   //选择的元素
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onSpinnerDialogOkButtonClick(strSelectValue[0]);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onSpinnerDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /*-----------------------------------下拉弹窗END----------------------------------------*/


    /*-----------------------------------单选弹窗----------------------------------------*/

    /**
     * 单选弹窗
     * @param strTitle
     * @param listData
     * @param listener
     */
    @Override
    public void showSingleChoiceDialog(String strTitle, List<String> listData, final OnSingleChoiceDialogClickListener listener)
    {
        final String[] strSelectValue = new String[1];

        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_single_choice_dialog, null);

        TextView tvTitle = view.findViewById(R.id.tv_single_choice_dialog_title);
        RadioGroup radioGroup = view.findViewById(R.id.rg_single_choice_dialog);
        Button btnOk = view.findViewById(R.id.btn_single_choice_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_single_choice_dialog_cancel);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        for (int i = 0; i < listData.size(); i++)
        {
            final String data = listData.get(i);

            final RadioButton radioButton = new RadioButton(mContext);

            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 20, 5, 0);    //设置RadioButton边距
            radioButton.setLayoutParams(lp);

            radioButton.setText(data);
            radioButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    strSelectValue[0] = data;
                }
            });

            radioGroup.addView(radioButton);
        }

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onSingleChoiceDialogOkButtonClick(strSelectValue[0]);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onSingleChoiceDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /**
     * 单选弹窗（带初始值）
     * @param strTitle
     * @param listData
     * @param strChoiced    已选择的项
     * @param listener
     */
    @Override
    public void showSingleChoiceDialog(String strTitle, List<String> listData, String strChoiced, final OnSingleChoiceDialogClickListener listener)
    {
        final String[] strSelectValue = new String[1];

        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_single_choice_dialog, null);

        TextView tvTitle = view.findViewById(R.id.tv_single_choice_dialog_title);
        RadioGroup radioGroup = view.findViewById(R.id.rg_single_choice_dialog);
        Button btnOk = view.findViewById(R.id.btn_single_choice_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_single_choice_dialog_cancel);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        for (int i = 0; i < listData.size(); i++)
        {
            final String data = listData.get(i);

            final RadioButton radioButton = new RadioButton(mContext);

            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 20, 5, 0);    //设置RadioButton边距
            radioButton.setLayoutParams(lp);

            radioButton.setText(data);
            radioButton.setId(i);   //每个RadioButton设置一个id, 用于实现默认选中
            radioButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    strSelectValue[0] = data;
                }
            });

            radioGroup.addView(radioButton);

            //设置默认选中项
            if (data.equals(strChoiced))
            {
                strSelectValue[0] = data;
                radioGroup.check(radioButton.getId());
            }
        }

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onSingleChoiceDialogOkButtonClick(strSelectValue[0]);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onSingleChoiceDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕寬度的四分之三
    }

    /*-----------------------------------单选弹窗END----------------------------------------*/


    /*-----------------------------------多选弹窗----------------------------------------*/

    /**
     * 多选弹窗
     * @param strTitle
     * @param listData  所有数据
     * @param listener
     */
    @Override
    public void showMultipleChoiceDialog(String strTitle, List<String> listData, final OnMultipleChoiceDialogClickListener listener)
    {
        final List<String> listResult = new ArrayList<>();

        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_multiple_choice_dialog, null);

        TextView tvTitle = view.findViewById(R.id.tv_multiple_choice_dialog_title);
        LinearLayout linearLayout = view.findViewById(R.id.ll_multiple_choice_dialog);
        Button btnOk = view.findViewById(R.id.btn_multiple_choice_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_multiple_choice_dialog_cancel);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        for (int i = 0; i < listData.size(); i++)
        {
            final String data = listData.get(i);

            final CheckBox checkBox = new CheckBox(mContext);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 0);    //设置CheckBox边距
            checkBox.setLayoutParams(lp);

            checkBox.setText(data);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                        listResult.add(data);
                    }
                    else
                    {
                        listResult.remove(data);
                    }
                }
            });

            linearLayout.addView(checkBox);
        }

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onMultipleChoiceDialogOkButtonClick(listResult);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onMultipleChoiceDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /**
     * 多选弹窗（带初始值）
     * @param strTitle
     * @param listData      所有数据
     * @param listChoiced   已选择的数据
     * @param listener
     */
    @Override
    public void showMultipleChoiceDialog(String strTitle, List<String> listData, List<String> listChoiced, final OnMultipleChoiceDialogClickListener listener)
    {
        final List<String> listResult = new ArrayList<>();

        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_multiple_choice_dialog, null);

        TextView tvTitle = view.findViewById(R.id.tv_multiple_choice_dialog_title);
        LinearLayout linearLayout = view.findViewById(R.id.ll_multiple_choice_dialog);
        Button btnOk = view.findViewById(R.id.btn_multiple_choice_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_multiple_choice_dialog_cancel);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        for (int i = 0; i < listData.size(); i++)
        {
            final String data = listData.get(i);

            boolean isChoiced = false;  //是否已经选择了
            for (int j = 0; j < listChoiced.size(); j++)
            {
                if (data.equals(listChoiced.get(j)))
                {
                    listResult.add(data);
                    isChoiced = true;
                }
            }

            final CheckBox checkBox = new CheckBox(mContext);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 0);    //设置CheckBox边距

            checkBox.setText(data);
            checkBox.setChecked(isChoiced);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                {
                    if (isChecked)
                    {
                        listResult.add(data);
                    }
                    else
                    {
                        listResult.remove(data);
                    }
                }
            });

            linearLayout.addView(checkBox);
        }

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onMultipleChoiceDialogOkButtonClick(listResult);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onMultipleChoiceDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /*-----------------------------------多选弹窗END----------------------------------------*/


    /*-----------------------------------引导提示弹窗----------------------------------------*/

    /**
     * 引导提示弹窗
     * @param imgs          图片资源
     * @param tips          提示文本资源
     * @param btnName       按钮名称
     * @param listener      按钮监听
     */
    @Override
    public void showGuidanceTipsDialog(final int[] imgs, final int[] tips, String btnName, final OnGuidanceTipsDialogClickListener listener)
    {
        ArrayList<ImageView> imageViews = new ArrayList<>();        //用于包含引导页要显示的图片
        final ImageView[] dotViews = new ImageView[imgs.length];    //用于包含底部小圆点的图片，只要设置每个imageview的图片资源为刚刚写的dot_selector，选择和没选中就会有不同的效果，实现导航的功能。


        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_guidance_tips_dialog, null);
        ViewPager viewPager = view.findViewById(R.id.vp_guidance_tips_dialog);
        final TextView textView = view.findViewById(R.id.tv_guidance_tips_dialog_tips);
        LinearLayout linearLayout = view.findViewById(R.id.ll_guidance_tips_dialog_dot);
        final Button btnOk = view.findViewById(R.id.btn_guidance_tips_dialog_ok);

        textView.setText(tips[0]);          //初始化显示第一个提示
        btnOk.setVisibility(View.GONE);     //初始化不显示按钮

        /*1.把引导页要显示的图片添加到集合中，以传递给适配器，用来显示图片*/
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < imgs.length; i++)
        {
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(mParams);                        //设置布局
            iv.setImageResource(imgs[i]);                       //为Imageview添加图片资源
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);    //设置图片显示属性
            imageViews.add(iv);

            //为最后一张添加点击事件
//            if (i == imgs.length - 1)
//            {
//                iv.setOnClickListener(new View.OnClickListener()
//                {
//
//                    @Override
//                    public void onClick(View v)
//                    {
//                        ToastUtils.onWarnShowToast("qqqqqq");
//                    }
//                });
//            }
        }

        /*2.根据引导页的数量，动态生成相应数量的导航小圆点，并添加到LinearLayout中显示*/
        LinearLayout.LayoutParams mParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mParams2.setMargins(10, 0, 10, 0);       //设置小圆点左右之间的间隔

        for (int i = 0; i < imageViews.size(); i++)
        {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(mParams2);
            imageView.setImageResource(R.drawable.popup_circle_selector);
            if (i == 0)
            {
                imageView.setSelected(true);    //默认启动时，选中第一个小圆点
            }
            else
            {
                imageView.setSelected(false);
            }
            dotViews[i] = imageView;            //得到每个小圆点的引用，用于滑动页面时，（onPageSelected方法中）更改它们的状态。
            linearLayout.addView(imageView);    //添加到布局里面显示
        }

        /*3.viewPager配置*/
        PopupViewPageAdapter pageAdapter = new PopupViewPageAdapter(imageViews);
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                for (int i = 0; i < dotViews.length; i++)
                {
                    if (position == i)
                    {
                        dotViews[i].setSelected(true);
                    }
                    else
                    {
                        dotViews[i].setSelected(false);
                    }
                }

                textView.setText(tips[position]);

                //最后一个页面显示按钮
                if (position == (imgs.length - 1))
                {
                    btnOk.setVisibility(View.VISIBLE);
                }
                else
                {
                    btnOk.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

        /*4.完成按钮点击事件*/
        if (btnName != null)
        {
            btnOk.setText(btnName);
        }

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onGuidanceTipsDialogOkButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /*-----------------------------------引导提示弹窗END----------------------------------------*/


    /*-----------------------------------滚轮弹窗----------------------------------------*/

    /**
     * 单项选择列表滚轮弹窗
     * @param strTitle      标题
     * @param listData      数据源
     * @param listener      按钮事件
     */
    @Override
    public void showSingleChoiceWheelDialog(String strTitle, final ArrayList<String> listData, final OnSingleChoiceWheelDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_single_choice_wheel_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_single_choice_wheel_dialog_title);
        final WheelView wheelView = view.findViewById(R.id.wv_single_choice_wheel_dialog);
        Button btnOk = view.findViewById(R.id.btn_single_choice_wheel_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_single_choice_wheel_dialog_cancel);

        wheelView.setAdapter(new ArrayWheelAdapter(listData));
        wheelView.setCyclic(false);
        wheelView.setTextSize(18);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onSingleChoiceWheelDialogOkButtonClick(listData.get(wheelView.getCurrentItem()));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onSingleChoiceWheelDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的四分之三
    }

    /**
     * 时间选择器弹窗
     * @param strTitle
     * @param type       时间类型
     * @param listener
     */
    @Override
    public void showTimePickerWheelDialog(String strTitle, WheelTimeView.Type type, final OnTimePickerDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_time_picker_wheel_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_time_picker_wheel_dialog_title);
        final WheelTimeView wheelTimeView = view.findViewById(R.id.wtv_time_picker_wheel_dialog);
        Button btnOk = view.findViewById(R.id.btn_time_picker_wheel_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_time_picker_wheel_dialog_cancel);

        //默认选中当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        wheelTimeView.setType(type);
        wheelTimeView.setPicker(year, month, day, hours, minute);
        wheelTimeView.setCyclic(false);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onTimePickerDialogOkButtonClick(wheelTimeView.getTimeWithZero());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onTimePickerDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 6 * 5, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的六分之五
    }

    /**
     * 选项选择器弹窗
     * @param strTitle
     * @param listener
     */
    @Override
    public void showOptionPickerWheelDialog(String strTitle,
                                            ArrayList<String> optionsItems_1,
                                            ArrayList<ArrayList<String>> optionsItems_2,
                                            ArrayList<ArrayList<ArrayList<String>>> optionsItems_3,
                                            String label1,
                                            String label2,
                                            String label3,
                                            final OnOptionPickerDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_option_picker_wheel_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_option_picker_wheel_dialog_title);
        final WheelOptionView wheelOptionView = view.findViewById(R.id.wov_option_picker_wheel_dialog);
        Button btnOk = view.findViewById(R.id.btn_option_picker_wheel_dialog_ok);
        Button btnCancel = view.findViewById(R.id.btn_option_picker_wheel_dialog_cancel);

        wheelOptionView.setPicker(optionsItems_1, optionsItems_2, optionsItems_3, true);
        wheelOptionView.setLabels(label1, label2, label3);
        wheelOptionView.setCyclic(false);

        if (strTitle == null)
        {
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            tvTitle.setText(strTitle);
        }

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onOptionPickerDialogOkButtonClick(wheelOptionView.getResult());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onOptionPickerDialogCancelButtonClick();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle1,
                Utils.getScreenWidthPX(mContext) / 6 * 5, LinearLayout.LayoutParams.WRAP_CONTENT); //设置弹出框宽度为屏幕高度的六分之五
    }

    /*-----------------------------------滚轮弹窗END----------------------------------------*/

    /*-----------------------------------底部选择弹窗----------------------------------------*/

    @Override
    public void showBottomSelectDialog(List<String> listItem, final OnBottomSelectDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_bottom_select_dialog, null);

        LinearLayout linearLayout = view.findViewById(R.id.ll_bottom_select_dialog);
        TextView tvCancel = view.findViewById(R.id.tv_bottom_select_dialog_cancel);

        for (int i = 0; i < listItem.size(); i++)
        {
            String itemName = listItem.get(i);

            final TextView textView = new TextView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(layoutParams);

            textView.setText(itemName);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(18);
            textView.setTextColor(mContext.getResources().getColor(R.color.cp_color_pale_blue));
            textView.setPadding(10, 30, 10, 30);

            //分割线
            if (i == 0)
            {
                layoutParams.setMargins(0, 0, 0, 0);
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.round_corner_bg_white2_up, null));    //第一个上半部分圆角
            }
            else
            {
                layoutParams.setMargins(0, 2, 0, 0);

                if (i == (listItem.size() - 1))     //
                {
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.round_corner_bg_white2_down, null));  //最后一个下半部分圆角
                }
                else
                {
                    textView.setBackgroundColor(mContext.getResources().getColor(R.color.cp_white));
                }
            }

            textView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    listener.onBottomSelectDialogItemClick(textView.getText().toString());
                }
            });

            linearLayout.addView(textView);
        }

        tvCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();
            }
        });

        dialogSet(view, R.style.DialogAnimStyle2,
                Utils.getScreenWidthPX(mContext) / 8 * 7, LinearLayout.LayoutParams.MATCH_PARENT); //设置弹出框宽度为屏幕高度的六分之五
    }
    /*-----------------------------------底部选择弹窗END----------------------------------------*/












































    /**
     * 基础弹窗-按钮点击事件接口
     */
    public interface OnBasicDialogClickListener
    {
        void onBasicDialogOkButtonClick();          //基础弹窗-确定按钮点击事件
    }

    /**
     * 成功弹窗-按钮点击事件接口
     */
    public interface OnSuccessDialogClickListener
    {
        void onSuccessDialogOkButtonClick();          //成功弹窗-确定按钮点击事件
    }

    /**
     * 警告弹窗-按钮点击事件接口
     */
    public interface OnWarningDialogClickListener
    {
        void onWarningDialogOkButtonClick();          //警告弹窗-确定按钮点击事件
    }

    /**
     * 错误弹窗-按钮点击事件接口
     */
    public interface OnErrorDialogClickListener
    {
        void onErrorDialogOkButtonClick();          //错误弹窗-确定按钮点击事件
    }

    /**
     * 确认弹窗-按钮点击事件接口
     */
    public interface OnConfirmDialogClickListener
    {
        void onConfirmDialogOkButtonClick();        //确认弹窗-确定按钮点击事件
        void onConfirmDialogCancelButtonClick();    //确认弹窗-取消按钮点击事件
    }

    /**
     * 输入弹窗-按钮点击事件接口
     */
    public interface OnEditDialogClickListener
    {
        void onEditDialogOkButtonClick(String editBack);        //输入弹窗-确定按钮点击事件
        void onEditDialogCancelButtonClick();                   //输入弹窗-取消按钮点击事件
    }

    /**
     * 下拉弹窗-按钮点击事件接口
     */
    public interface OnSpinnerDialogClickListener
    {
        void onSpinnerDialogOkButtonClick(String value);        //下拉弹窗-确定按钮点击事件
        void onSpinnerDialogCancelButtonClick();                //下拉弹窗-取消按钮点击事件
    }

    /**
     * 单选弹窗-按钮点击事件接口
     */
    public interface OnSingleChoiceDialogClickListener
    {
        void onSingleChoiceDialogOkButtonClick(String value);        //单选弹窗-确定按钮点击事件
        void onSingleChoiceDialogCancelButtonClick();               //单选弹窗-取消按钮点击事件
    }

    /**
     * 多选弹窗-按钮点击事件接口
     */
    public interface OnMultipleChoiceDialogClickListener
    {
        void onMultipleChoiceDialogOkButtonClick(List<String> listResult);      //多选弹窗-确定按钮点击事件
        void onMultipleChoiceDialogCancelButtonClick();                         //多选弹窗-取消按钮点击事件
    }

    /**
     * 引导提示弹窗-按钮点击事件接口
     */
    public interface OnGuidanceTipsDialogClickListener
    {
        void onGuidanceTipsDialogOkButtonClick();        //引导提示弹窗-确定按钮点击事件
    }

    /**
     * 单项选择列表滚轮弹窗-按钮点击事件接口
     */
    public interface OnSingleChoiceWheelDialogClickListener
    {
        void onSingleChoiceWheelDialogOkButtonClick(String value);      //单项选择列表滚轮弹窗-确定按钮点击事件
        void onSingleChoiceWheelDialogCancelButtonClick();              //单项选择列表滚轮弹窗-取消按钮点击事件
    }

    /**
     * 时间选择器弹窗-按钮点击事件接口
     */
    public interface OnTimePickerDialogClickListener
    {
        void onTimePickerDialogOkButtonClick(String value);     //时间选择器弹窗-确定按钮点击事件
        void onTimePickerDialogCancelButtonClick();             //时间选择器弹窗-取消按钮点击事件
    }

    /**
     * 选项选择器弹窗-按钮点击事件接口
     */
    public interface OnOptionPickerDialogClickListener
    {
        void onOptionPickerDialogOkButtonClick(String[] value);     //选项选择器弹窗-确定按钮点击事件
        void onOptionPickerDialogCancelButtonClick();               //选项选择器弹窗-取消按钮点击事件
    }

    /**
     * 底部选择弹窗-按钮点击事件接口
     */
    public interface OnBottomSelectDialogClickListener
    {
        void onBottomSelectDialogItemClick(String value);        //底部选择弹窗-item点击事件
    }
}
