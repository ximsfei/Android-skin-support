# Android-skin-support

[![skin-support-v2.1.1](https://img.shields.io/badge/skin--support-v2.1.1-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.1.1/)
[![design-v1.2.5](https://img.shields.io/badge/skin--support--design-v1.2.5-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/1.2.5/)
[![cardview-v1.2.0](https://img.shields.io/badge/skin--support--cardview-v1.2.0-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-cardview/1.2.0/)
[![v1.0.2](https://img.shields.io/badge/skin--support--constraint--layout-v1.0.2-green.svg)](http://http://jcenter.bintray.com/skin/support/skin-support-constraint-layout/1.0.2/)

[![circleimageview-v2.2.0.2](https://img.shields.io/badge/circleimageview-v2.1.0.2-green.svg)](http://jcenter.bintray.com/skin/support/circleimageview/2.1.0.2/)
[![v2.1.2](https://img.shields.io/badge/flycotablayout-v2.1.2-green.svg)](http://jcenter.bintray.com/skin/support/flycotablayout/2.1.2/)
[![v1.4.5](https://img.shields.io/badge/androidautolayout-v1.4.5-green.svg)](http://jcenter.bintray.com/skin/support/androidautolayout/1.4.5/)

* [介绍](#介绍)
* [更新日志](ChangeLog.md)
  * [skin-support 更新日志](https://github.com/ximsfei/Android-skin-support/blob/master/ChangeLog.md#skin-support-基础控件-支持)
  * [skin-support-design 更新日志](https://github.com/ximsfei/Android-skin-support/blob/master/ChangeLog.md#skin-support-design-material-design-支持)
  * [skin-support-cardview 更新日志](https://github.com/ximsfei/Android-skin-support/blob/master/ChangeLog.md#skin-support-cardview-cardview-支持)
  * [skin-support-constraint-layout 更新日志](https://github.com/ximsfei/Android-skin-support/blob/master/ChangeLog.md#skin-support-constraint-layout-constraintlayout-支持)
* [框架用法](#用法)
  * [导入](#导入)
  * [使用](#使用)
    * [初始化](#在application的oncreate中初始化)
    * [皮肤开关](#皮肤开关)
    * [加载插件皮肤库](#加载插件皮肤库)
    * [自定义view换肤](#自定义view换肤)
  * [应用内换肤](#应用内换肤)
  * [插件式换肤](#插件式换肤)
    * [新建皮肤工程](#新建android-application工程)
    * [添加皮肤资源](#将需要换肤的资源放到res目录下同名资源)
    * [生成皮肤插件](#打包生成apk-即为皮肤包)
    * [加载皮肤插件](#加载皮肤插件)
* [第三方控件适配库](ThirdPartSupport.md)
  * [hdodenhof/CircleImageView](https://github.com/ximsfei/Android-skin-support/blob/master/ThirdPartSupport.md#hdodenhofcircleimageview)
  * [H07000223/FlycoTabLayout](https://github.com/ximsfei/Android-skin-support/blob/master/ThirdPartSupport.md#h07000223flycotablayout)
  * [hongyangAndroid/AndroidAutoLayout](https://github.com/ximsfei/Android-skin-support/blob/master/ThirdPartSupport.md#hongyangandroidandroidautolayout)
* [LICENSE](#license-mit)

## 介绍

Android-skin-support: 一款用心去做的Android 换肤框架, 极低的学习成本, 极好的用户体验.

只需要一行代码, 就可以实现换肤, 你值得拥有!!!

```java
SkinCompatManager.withoutActivity(this).loadSkin();
```

就这么简单, 你的APK已经拥有了强大的换肤功能, 当然现在是拥有了换肤功能, 别忘了[制作皮肤包](#应用内换肤).

[skin-app](skin-app)                        // 换肤demo app

[skin-night](skin-night)                    // 换肤demo 夜间模式

[skin-support](skin-support)                // 换肤框架, 基础控件支持

[skin-support-design](skin-support-design)  // 换肤框架, Material Design 支持

## 用法

### 导入:
直接添加依赖, [最新版本选择, 请查看更新日志](ChangeLog.md)
```xml
compile 'skin.support:skin-support:2.1.1'          // skin-support 基础控件支持
compile 'skin.support:skin-support-design:1.2.5'   // skin-support-design material design 控件支持[可选]
compile 'skin.support:skin-support-cardview:1.2.0' // skin-support-cardview CardView 控件支持[可选]
compile 'skin.support:skin-support-constraint-layout:1.0.2' // skin-support-constraint-layout ConstraintLayout 控件支持[可选]
```

### 使用:

#### 在Application的onCreate中初始化
    
```java
@Override
public void onCreate() {
    super.onCreate();
    SkinCompatManager.withoutActivity(this)                 // 基础控件换肤初始化
            .addInflater(new SkinMaterialViewInflater())    // material design 控件换肤初始化[可选]
            .addInflater(new SkinConstraintViewInflater())  // ConstraintLayout 控件换肤初始化[可选]
            .addInflater(new SkinCardViewInflater())        // CardView v7 控件换肤初始化[可选]
            .loadSkin();
}
```

#### 皮肤开关

如果项目中有特殊需求。例如, 股票控件: 控件颜色始终为红色或绿色, 不需要随着模式切换而换肤

那么可以使用类似的方法, 直接设置color值
```xml
setTextColor(0xce3d3a) // 不支持换肤
textColor="#ce3d3a"
```
而不是使用R.color.red
```xml
setTextColor(R.color.red)
textColor="@color/red"
```

#### 加载插件皮肤库

```java
// 指定皮肤插件
SkinCompatManager.getInstance().loadSkin("new.skin"[, SkinLoaderListener], int strategy);

// 恢复应用默认皮肤
SkinCompatManager.getInstance().restoreDefaultTheme();
```

#### 自定义View换肤

要点:

1. 实现SkinCompatSupportable接口

  1. applySkin方法中实现换肤操作

2. 在构造方法中解析出需要换肤的resId

* 自定义View可以直接继承自SkinCompatView, SkinCompatLinearLayout等已有控件

  eg: [CustomTextView](skin-app/src/main/java/com/ximsfei/skindemo/widget/CustomTextView.java)

* 不想继承自已有控件

  eg: [CustomTextView2](skin-app/src/main/java/com/ximsfei/skindemo/widget/CustomTextView2.java)

* 需要换肤自定义属性

  // 需要换肤AutoCompleteTextView的R.attr.popupBackground属性

  eg: [SkinCompatAutoCompleteTextView](skin-support/src/main/java/skin/support/widget/SkinCompatAutoCompleteTextView.java)

* 需要使用第三方库控件怎么办

  // 需要使用https://github.com/hdodenhof/CircleImageView 控件, 并且要支持换肤

  eg: [SkinCompatCircleImageView](third-part-support/circleimageview)

### 应用内换肤:

应用内换肤，皮肤名为: night; 新增需要换肤的资源添加后缀 _night。

需要换肤的资源为R.color.windowBackgroundColor, 添加对应资源R.color.windowBackgroundColor_night。

加载应用内皮肤:
```java
SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
```

### 插件式换肤:

#### 新建Android application工程

皮肤工程包名不能和宿主应用包名相同.

例如:
```xml
宿主包名: com.ximsfei.skindemo
夜间模式: com.ximsfei.skindemo.night
```

#### 将需要换肤的资源放到res目录下(同名资源)

例如 APK中窗口背景颜色为

colors.xml
```xml
<color name="background">#ffffff</color>
```
那么夜间模式你可以在skin-night工程中设置

colors.xml
```xml
<color name="background">#000000</color>
```

#### 打包生成apk, 即为皮肤包

将打包生成的apk文件, 重命名为'xxx.skin', 防止apk结尾的文件造成混淆.

#### 加载皮肤插件

加载插件式皮肤, 将皮肤包放到assets/skins目录下
```java
SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
```

## [License MIT](LICENSE)
