package com.bluewater.commonpopuplib;

import com.bluewater.commonpopuplib.progress.DonutProgressBar;
import com.bluewater.commonpopuplib.progress.HorizontalProgressBar;

import java.util.List;

/**
 * 通用弹窗
 */
public interface CommonPopup
{
    void dialogDismiss();   //弹窗消失

    //基础弹窗
    void showBasicDialog(String strTitle, String strInfo);
    void showBasicDialog(String strTitle, String strInfo, final CommonPopupImpl.OnBasicDialogClickListener listener);

    //错误弹窗
    void showErrorDialog(String strTitle, String strInfo);
    void showErrorDialog(String strTitle, String strInfo, final CommonPopupImpl.OnErrorDialogClickListener listener);

    //确认弹窗
    void showConfirmDialog(String strTitle, String strInfo, final CommonPopupImpl.OnConfirmDialogClickListener listener);

    //加载弹窗
    void showLoadDialog(String strInfo);
    //带进度的线型加载弹窗
    HorizontalProgressBar showLineLoadDialogWithValue(String strInfo);
    //带进度的圆圈加载弹窗
    DonutProgressBar showDonutLoadDialogWithValue(String strInfo);

    //输入弹窗
    void showEditDialog(String strTitle, final CommonPopupImpl.OnEditDialogClickListener listener);

    //下拉弹窗
    void showSpinnerDialog(String strTitle, List<String> listData, final CommonPopupImpl.OnSpinnerDialogClickListener listener);

    //单选弹窗
    void showSingleChoiceDialog(String strTitle, List<String> listData, final CommonPopupImpl.OnSingleChoiceDialogClickListener listener);

    //多选弹窗
    void showMultipleChoiceDialog(String strTitle, List<String> listData, final CommonPopupImpl.OnMultipleChoiceDialogClickListener listener);




}
