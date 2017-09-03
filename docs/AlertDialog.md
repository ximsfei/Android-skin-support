# AlertDialog 换肤支持

AlertDialog的使用可参考demo中的[AlertDialogActivity](../demo/skin-app/src/main/java/com/ximsfei/skindemo/alert/AlertDialogActivity.java)

## 使用最新的 skin-support 框架

## 使用v7包中的AlertDialog

将`android.app.AlertDialog`替换成`android.support.v7.app.AlertDialog`

## 在需要使用 AlertDialog 的 Activity 的 Theme 中添加以下属性

```xml
<style name="xxx" parent="BaseAppTheme">
    <!-- AlertDialog.SkinCompat 在框架中已经定义，直接写死即可，也可以参考框架自行实现 -->
    <item name="alertDialogStyle">@style/AlertDialog.SkinCompat</item>
    <!-- 以下属性需要用户自行实现，并在皮肤包中提供对应值 -->
    <item name="skinAlertDialogBackground">@color/background</item>
    <item name="skinAlertDialogTitleTextColor">@color/text_color</item>
    <item name="skinAlertDialogMessageTextColor">@color/text_color</item>
    <item name="skinAlertDialogNeutralButtonTextColor">@color/text_color</item>
    <item name="skinAlertDialogNegativeButtonTextColor">@color/text_color</item>
    <item name="skinAlertDialogPositiveButtonTextColor">@color/text_color_tip</item>
    <item name="skinAlertDialogControlHighlightColor">@color/colorAccent</item>
    <item name="skinAlertDialogListDivider">@null</item>
    <item name="skinAlertDialogListItemTextColor">@color/text_color</item>
    <!-- 这两个值在框架中已经定义，用户需要在皮肤包中提供对应值 -->
    <!-- 这两个值也可以参考框架自行实现，并在皮肤包中提供对应值 -->
    <item name="skinListChoiceIndicatorMultiple">@drawable/skin_btn_check</item>
    <item name="skinListChoiceIndicatorSingle">@drawable/skin_btn_radio</item>
</style>
```