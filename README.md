# Android-skin-support

中文 | [In English](docs/README.md) 

[![skin-support](https://img.shields.io/badge/release-v3.1.2-green.svg)](http://jcenter.bintray.com/skin/support)
![build](https://img.shields.io/badge/build-passing-green.svg)
![license](https://img.shields.io/badge/license-mit-blue.svg)

* [介绍](#介绍)
  * [功能](#功能)
  * [TODO](#todo)
  * [目录结构](#目录结构)
* [Demo](#demo)
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
  * [自定义加载策略](#自定义加载策略)
* [AlertDialog换肤](docs/AlertDialog.md)
* [更新日志](docs/ChangeLog.md)
  * [skin-support 更新日志](docs/ChangeLog.md#skin-support-基础控件-支持)
  * [skin-support-design 更新日志](docs/ChangeLog.md#skin-support-design-material-design-支持)
  * [skin-support-cardview 更新日志](docs/ChangeLog.md#skin-support-cardview-cardview-支持)
  * [skin-support-constraint-layout 更新日志](docs/ChangeLog.md#skin-support-constraint-layout-constraintlayout-支持)
* [第三方控件适配库](docs/ThirdPartSupport.md)
  * [hdodenhof/CircleImageView](docs/ThirdPartSupport.md#hdodenhofcircleimageview)
  * [H07000223/FlycoTabLayout](docs/ThirdPartSupport.md#h07000223flycotablayout)
  * [hongyangAndroid/AndroidAutoLayout](docs/ThirdPartSupport.md#hongyangandroidandroidautolayout)
* [谁在使用](#谁在使用)
* [FAQ](docs/FAQ.md)
* [技术交流](#技术交流)
* [LICENSE](#license-mit)

## 介绍

Android-skin-support: 一款用心去做的Android 换肤框架, 极低的学习成本, 极好的用户体验.

只需要一行代码, 就可以实现换肤, 你值得拥有!!!

```java
SkinCompatManager.withoutActivity(this).loadSkin();
```

就这么简单, 你的APK已经拥有了强大的换肤功能, 当然现在是拥有了换肤功能, 别忘了[制作皮肤包](#应用内换肤).

### 功能

* [x] 布局中用到的资源换肤实现。
* [x] 代码中设置的资源换肤实现。
* [x] 默认支持大部分基础控件，Material Design换肤。
* [x] 支持动态设置主题[颜色值](demo/skin-app/src/main/java/com/ximsfei/skindemo/picker/ColorPickerActivity.java)，支持选择sdcard上的图片作为[drawable](demo/skin-app/src/main/java/com/ximsfei/skindemo/picker/DrawablePickerActivity.java)换肤资源。
* [x] 支持多种加载策略([应用内](#应用内换肤)/[插件式](#插件式换肤)/[自定义sdcard路径](#自定义加载策略)/[zip等资源](demo/skin-app/src/main/java/com/ximsfei/skindemo/loader/ZipSDCardLoader.java)等)。
* [x] 资源加载优先级: 动态设置资源-加载策略中的资源-插件式换肤/应用内换肤-应用资源。
* [x] 支持定制化，选择需要的模块加载。
* [x] 支持矢量图(vector/svg)换肤。

*[详细内容, 请查看更新日志](docs/ChangeLog.md)，那里有所有功能提交记录*

### TODO

* [ ] `AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);`开关使用错误问题修正
* [ ] 支持多语言。
* [ ] 支持多字体。
* [ ] 支持Preference。
* [ ] skin-sample 实现:
  * [x] 动态修改主题颜色值[案例](demo/skin-sample/src/main/java/com/ximsfei/skin/sample/activities/ColorPickerActivity.java)
  * [ ] 控件使用案例
  * [ ] 更新日志tab
  * [ ] 关于页面
* [ ] Wiki

### 目录结构

> [demo](demo)                                                        // 换肤demo 集合

>> [skin-sample](demo/skin-sample)([skin-app](demo/skin-app))         // demo app

>> [skin-night](demo/skin-night)                                      // 夜间模式皮肤工程

>> [skin-autolayout-app](demo/skin-autolayout-app)                    // autolayout demo app

> [android-support](android-support)                                  // Android 原生控件

>> [skin-support](android-support／skin-support)                      // 换肤框架, 基础控件支持

>> [skin-support-cardview](android-support／skin-support-cardview)    // 换肤框架, CardView 支持

>> [skin-support-design](android-support／skin-support-design)        // 换肤框架, Material Design 支持

>> [skin-support-constraint-layout](android-support／skin-support-constraint-layout)  // 换肤框架, ConstraintLayout 支持

> [third-part-support](third-part-support)           // 第三方控件换肤支持

## Demo

![default](https://github.com/ximsfei/Res/blob/master/skin/preview/default.png)
![app-in](https://github.com/ximsfei/Res/blob/master/skin/preview/app-in.png)
![plug-in](https://github.com/ximsfei/Res/blob/master/skin/preview/plug-in.png)

## 用法

### 导入:
直接添加依赖, [最新版本选择, 请查看更新日志](docs/ChangeLog.md)
```xml
implementation 'skin.support:skin-support:3.1.2'                   // skin-support 基础控件支持
implementation 'skin.support:skin-support-design:3.1.2'            // skin-support-design material design 控件支持[可选]
implementation 'skin.support:skin-support-cardview:3.1.2'          // skin-support-cardview CardView 控件支持[可选]
implementation 'skin.support:skin-support-constraint-layout:3.1.2' // skin-support-constraint-layout ConstraintLayout 控件支持[可选]
```

### 使用:

#### 在Application的onCreate中初始化
    
```java
@Override
public void onCreate() {
    super.onCreate();
    SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
            .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
            .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
            .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
            .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
            .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
            .loadSkin();
}
```

> 如果项目中使用的Activity继承自AppCompatActivity，需要重载getDelegate()方法

```java
@NonNull
@Override
public AppCompatDelegate getDelegate() {
    return SkinAppCompatDelegateImpl.get(this, this);
}
```

#### 皮肤开关

如果项目中有特殊需求。例如, 股票控件: 控件颜色始终为红色或绿色, 不需要随着模式切换而换肤

那么可以使用类似的方法, 直接设置drawable
```xml
setBackgroundDrawable(redDrawable) // 不支持换肤
background="#ce3d3a"
```
而不是使用R.drawable.red
```xml
setBackgroundResource(R.drawable.red)
background="@drawable/red"
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

  eg: [CustomTextView](demo/skin-app/src/main/java/com/ximsfei/skindemo/widget/CustomTextView.java)

* 不想继承自已有控件

  eg: [CustomTextView2](demo/skin-app/src/main/java/com/ximsfei/skindemo/widget/CustomTextView2.java)

* 需要换肤自定义属性

  // 需要换肤AutoCompleteTextView的R.attr.popupBackground属性

  eg: [SkinCompatAutoCompleteTextView](android-support/skin-support/src/main/java/skin/support/widget/SkinCompatAutoCompleteTextView.java)

* 需要使用第三方库控件怎么办

  // 需要使用https://github.com/hdodenhof/CircleImageView 控件, 并且要支持换肤

  eg: [SkinCompatCircleImageView](third-part-support/circleimageview/src/main/java/skin/support/circleimageview/widget/SkinCompatCircleImageView.java)

### 应用内换肤:

应用内换肤，皮肤名为: night; 新增需要换肤的资源添加后缀 _night或者前缀 night_。

需要换肤的资源为R.color.windowBackgroundColor, 添加对应资源R.color.windowBackgroundColor_night。

加载应用内皮肤:
```java
SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN); // 后缀加载
SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_PREFIX_BUILD_IN); // 前缀加载
```

推荐将应用内换肤相关的皮肤资源放到单独的目录中

eg: [res-night](https://github.com/ximsfei/Android-skin-support/tree/master/demo/skin-app/src/main/res-night)

*注: 如果使用这种方式来增加换肤资源，记得在build.gradle 中配置一下这个资源目录 sourceSets {main {res.srcDirs = ['src/main/res', 'src/main/res-night']}}*


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
SkinCompatManager.getInstance().loadSkin("night.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
```

### 自定义加载策略:

以自定义SDCard加载路径为例:

继承自`SkinSDCardLoader`，通过`getSkinPath`方法指定皮肤加载路径，通过`getType`方法指定加载器type。

```java
public class CustomSDCardLoader extends SkinSDCardLoader {
    public static final int SKIN_LOADER_STRATEGY_SDCARD = Integer.MAX_VALUE;

    @Override
    protected String getSkinPath(Context context, String skinName) {
        return new File(SkinFileUtils.getSkinDir(context), skinName).getAbsolutePath();
    }

    @Override
    public int getType() {
        return SKIN_LOADER_STRATEGY_SDCARD;
    }
}
```

*注: 自定义加载器type 值最好从整数最大值开始递减，框架的type值从小数开始递增，以免将来框架升级造成type 值冲突*

在Application中，添加自定义加载策略:

```java
SkinCompatManager.withoutActivity(this)
        .addStrategy(new CustomSDCardLoader());          // 自定义加载策略，指定SDCard路径
```

*注: 自定义加载器必须在Application中注册，皮肤切换后，重启应用需要根据当前策略加载皮肤*

使用自定义加载器加载皮肤:

```java
SkinCompatManager.getInstance().loadSkin("night.skin", null, CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD);
```

## 谁在使用

如果你想提交作品，欢迎提出 [PR](https://github.com/ximsfei/Android-skin-support/tree/master/docs/who-use-it) 或联系[作者](#技术交流)。

| icon | name | description |
| :--: | :--- | :---------- |
| [<img width="96" height="96" src="https://github.com/ximsfei/Android-skin-support/blob/master/docs/who-use-it/sohunews_explore-icon.png"/>](https://k.sohu.com/) | [搜狐新闻探索版](https://k.sohu.com/) | 
| [<img width="96" height="96" src="https://github.com/ximsfei/Android-skin-support/blob/master/docs/who-use-it/vflynote-icon.png"/>](http://www.iyuji.cn/iyuji/home) | [讯飞语记](http://www.iyuji.cn/iyuji/home) |
| [<img width="96" height="96" src="https://github.com/ximsfei/Android-skin-support/blob/master/docs/who-use-it/qoo-app.png"/>](https://news.qoo-app.com/) | [qoo app](https://news.qoo-app.com/) |
| [<img width="96" height="96" src="https://github.com/ximsfei/Android-skin-support/blob/master/docs/who-use-it/dwnews.png"/>](http://www.dwnews.com/) | [多维新闻](http://www.dwnews.com/)| 需要翻墙 |


## 技术交流

![](https://github.com/ximsfei/Res/blob/master/Android%26%2332%3B%E6%8D%A2%E8%82%A4.png)

## [License MIT](LICENSE)
