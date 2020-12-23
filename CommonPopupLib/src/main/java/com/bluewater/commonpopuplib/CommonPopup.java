package com.bluewater.commonpopuplib;

import com.bluewater.commonpopuplib.Wheel.lib.WheelTimeView;
import com.bluewater.commonpopuplib.progress.DonutProgressBar;
import com.bluewater.commonpopuplib.progress.HorizontalProgressBar;

import java.util.ArrayList;
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

    //警告弹窗
    void showWarningDialog(String strTitle, String strInfo);
    void showWarningDialog(String strTitle, String strInfo, final CommonPopupImpl.OnWarningDialogClickListener listener);

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
    void showEditDialog(String strTitle, String strContent, final CommonPopupImpl.OnEditDialogClickListener listener);

    //下拉弹窗
    void showSpinnerDialog(String strTitle, List<String> listData, final CommonPopupImpl.OnSpinnerDialogClickListener listener);

    //单选弹窗
    void showSingleChoiceDialog(String strTitle, List<String> listData, final CommonPopupImpl.OnSingleChoiceDialogClickListener listener);

    //多选弹窗
    void showMultipleChoiceDialog(String strTitle, List<String> listData, final CommonPopupImpl.OnMultipleChoiceDialogClickListener listener);

    //引导提示弹窗
    void showGuidanceTipsDialog(final int[] imgs, final int[] tips, String btnName, final CommonPopupImpl.OnGuidanceTipsDialogClickListener listener);

    //单项选择列表滚轮弹窗
    void showSingleChoiceWheelDialog(String strTitle, ArrayList<String> listData, final CommonPopupImpl.OnSingleChoiceWheelDialogClickListener listener);

    //时间选择器弹窗
    void showTimePickerWheelDialog(String strTitle, WheelTimeView.Type type, final CommonPopupImpl.OnTimePickerDialogClickListener listener);

    //选项选择器弹窗
    void showOptionPickerWheelDialog(String strTitle,
                                     ArrayList<String> optionsItems_1,
                                     ArrayList<ArrayList<String>> optionsItems_2,
                                     ArrayList<ArrayList<ArrayList<String>>> optionsItems_3,
                                     String label1,
                                     String label2,
                                     String label3,
                                     final CommonPopupImpl.OnOptionPickerDialogClickListener listener);

    //底部选择弹窗
    void showBottomSelectDialog(List<String> listItem, final CommonPopupImpl.OnBottomSelectDialogClickListener listener);














}
