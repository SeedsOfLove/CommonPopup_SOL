package com.bluewater.commonpopuplib;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bluewater.commonpopuplib.progress.DonutProgressBar;
import com.bluewater.commonpopuplib.progress.HorizontalProgressBar;
import com.bluewater.toolutilslib.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用弹窗实现
 */
public class CommonPopupImpl implements CommonPopup
{
    private Context mContext;
    private AlertDialog dialog;

    /**
     * 构造函数
     * @param context
     * @param isCancel  设置点击dialog以外区域能否取消Dialog  true：可以   false：不可以
     */
    public CommonPopupImpl(Context context, boolean isCancel)
    {
        this.mContext = context;

        // 创建Dialog
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(isCancel);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));     //设置为圆角后有阴影
        dialog.getWindow().setWindowAnimations(R.style.DialogAnimStyle);                    //自定义对话框弹出、关闭动画效果

    }

    private void dialogSet(View view)
    {
        dialog.show();
        dialog.setContentView(view);
        dialog.getWindow().setLayout(AppUtils.getScreenWidthPX(mContext) / 4 * 3, LinearLayout.LayoutParams.WRAP_CONTENT);//设置弹出框宽度为屏幕高度的四分之三
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
     *
     * @param strTitle      标题
     * @param strInfo       内容
     */
    @Override
    public void showBasicDialog(String strTitle, String strInfo)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_basic_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_basic_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_basic_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_basic_dialog_ok);

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

        dialogSet(view);
    }

    /**
     * 基础弹窗(含确认按钮点击事件)
     *
     * @param strTitle      标题
     * @param strInfo       内容
     * @param listener      确定按钮点击事件
     */
    @Override
    public void showBasicDialog(String strTitle, String strInfo, final OnBasicDialogClickListener listener)
    {
        // 加载布局文件
        View view = View.inflate(mContext, R.layout.common_popup_basic_dialog, null);
        TextView tvTitle = view.findViewById(R.id.tv_basic_dialog_title);
        TextView tvInfo = view.findViewById(R.id.tv_basic_dialog_info);
        Button btnOk = view.findViewById(R.id.btn_basic_dialog_ok);

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

        dialogSet(view);
    }

    /*-----------------------------------基础弹窗END----------------------------------------*/


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

        dialogSet(view);
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

        dialogSet(view);
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

        dialogSet(view);
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

        dialogSet(view);
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

        dialogSet(view);

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

        dialogSet(view);

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
                dialog.dismiss();
            }
        });

        dialogSet(view);
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
                dialog.dismiss();
            }
        });

        dialogSet(view);
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
            lp.setMargins(0, 0, 0, 10);    //设置RadioButton边距

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
                dialog.dismiss();
            }
        });

        dialogSet(view);
    }

    /*-----------------------------------单选弹窗END----------------------------------------*/


    /*-----------------------------------多选弹窗----------------------------------------*/

    /**
     * 多选弹窗
     * @param strTitle
     * @param listData
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
                dialog.dismiss();
            }
        });

        dialogSet(view);
    }


    /*-----------------------------------多选弹窗END----------------------------------------*/









    /**
     * 基础弹窗-按钮点击事件接口
     */
    public interface OnBasicDialogClickListener
    {
        void onBasicDialogOkButtonClick();          //基础弹窗-确定按钮点击事件
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
    }

    /**
     * 下拉弹窗-按钮点击事件接口
     */
    public interface OnSpinnerDialogClickListener
    {
        void onSpinnerDialogOkButtonClick(String value);        //下拉弹窗-确定按钮点击事件
    }

    /**
     * 单选弹窗-按钮点击事件接口
     */
    public interface OnSingleChoiceDialogClickListener
    {
        void onSingleChoiceDialogOkButtonClick(String value);        //单选弹窗-确定按钮点击事件
    }

    /**
     * 多选弹窗-按钮点击事件接口
     */
    public interface OnMultipleChoiceDialogClickListener
    {
        void onMultipleChoiceDialogOkButtonClick(List<String> listResult);        //多选弹窗-确定按钮点击事件
    }
}
