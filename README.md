# CommonPopup_SOL
通用弹窗工具类

>提供Android常用弹窗

**效果展示**

基础弹窗

![0.jpg](./img/0.jpg)

成功弹窗

![1.jpg](./img/1.jpg)

警告弹窗

![2.jpg](./img/2.jpg)

错误弹窗

![3.jpg](./img/3.jpg)

确认弹窗

![4.jpg](./img/4.jpg)

加载弹窗

![5.jpg](./img/5.jpg)

带进度的线型加载弹窗

![6.jpg](./img/6.jpg)

带进度的圆圈加载弹窗

![7.jpg](./img/7.jpg)

输入弹窗

![8.jpg](./img/8.jpg)

下拉弹窗

![9.jpg](./img/9.jpg)

单选弹窗

![10.jpg](./img/10.jpg)

多选弹窗

![11.jpg](./img/11.jpg)

引导提示弹窗

![12.jpg](./img/12.jpg)

单项选择列表滚轮弹窗

![13.jpg](./img/13.jpg)

时间选择器弹窗

![14.jpg](./img/14.jpg)

选项选择器弹窗

![15.jpg](./img/15.jpg)

底部选择弹窗

![16.jpg](./img/16.jpg)

## 使用方式
### Step 1. Add the JitPack repository to your build file
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
### Step 2. Add the dependency
```
dependencies {
        implementation 'com.github.SeedsOfLove:CommonPopup_SOL:1.1.2'
	}
```
### Step 3. Using
```
CommonPopup popup = new CommonPopupImpl(mContext, false);

popup.showBasicDialog();				//基础弹窗

popup.showSuccessDialog();				//成功弹窗

popup.showWarningDialog();				//警告弹窗

popup.showErrorDialog();				//错误弹窗

popup.showConfirmDialog();				//确认弹窗

popup.showLoadDialog();					//加载弹窗

popup.showLineLoadDialogWithValue();	//带进度的线型加载弹窗

popup.showDonutLoadDialogWithValue();	//带进度的圆圈加载弹窗

popup.showEditDialog();					//输入弹窗

popup.showSpinnerDialog();				//下拉弹窗

popup.showSingleChoiceDialog();			//单选弹窗

popup.showMultipleChoiceDialog();		//多选弹窗

popup.showGuidanceTipsDialog();		    //引导提示弹窗

popup.showSingleChoiceWheelDialog();    //单项选择列表滚轮弹窗

popup.showTimePickerWheelDialog();		//时间选择器弹窗

popup.showOptionPickerWheelDialog();	//选项选择器弹窗

popup.showBottomSelectDialog();	        //底部选择弹窗

popup.dialogDismiss();	//弹窗消失
```




















