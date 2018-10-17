# 更新日志

## skin-support

* ![v3.1.1](https://img.shields.io/badge/skin--support-v3.1.1-green.svg)
  * Add Android P Support

* ![v3.1.0-beta1](https://img.shields.io/badge/skin--support-v3.1.0--beta1-green.svg)
  * bugfix:
    * issue #151

* ![v3.1.0-beta](https://img.shields.io/badge/skin--support-v3.1.0--beta-green.svg)
  * 动态修改主题颜色值。
    可以让用户设置任何color属性的颜色值(包括ColorStateList, [具体使用细节](demo/skin-app/src/main/java/com/ximsfei/skindemo/picker/ColorPickerActivity.java)): `SkinCompatUserThemeManager.get().addColorState(R.color.colorPrimary, #ffff0000)`。
  * 动态修改Drawable。
    可以让用户设置任何drawable属性的图片([具体使用细节](demo/skin-app/src/main/java/com/ximsfei/skindemo/picker/DrawablePickerActivity.java)): `SkinCompatUserThemeManager.get().addDrawablePath(R.drawable.windowBackground, path, angle)`。
  * 兼容低版本support library。已向下兼容到support library 25.1.0
  * 更灵活的资源加载策略，开发者可配置任意资源获取方式(Zip/Apk/Json...)。 
    资源加载优先级: 动态设置资源-加载策略中的资源-插件式换肤/应用内换肤-应用资源。
  * 换肤框架性能优化。[#141](https://github.com/ximsfei/Android-skin-support/issues/141)
  * 默认关闭状态栏换肤。
  * 修复CardView中TypedArray资源未回收问题。
  * 修复加载策略SharedPreferences中默认值错误问题。

* ![v3.0.0](https://img.shields.io/badge/skin--support-v3.0.0-green.svg)
  * Android studio 3.1.1 & sdk version 27 适配。
  * 依赖 android support library version 27.1.1。

* ![v2.2.3](https://img.shields.io/badge/skin--support-v2.2.3-green.svg)
  * bugfix:
    * issue #110
  * AsyncTask使用不当引起的部分情况下皮肤加载过慢的问题

* ![v2.2.2](https://img.shields.io/badge/skin--support-v2.2.2-green.svg)
  * bugfix:
    * issue #107
  * add interface: `setSkinAllActivityEnable(boolean enable)`

Please check previous change logs from [here](ChangeLog_before_v2.2.2.md).
