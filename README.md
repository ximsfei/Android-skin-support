# Android-skin-support

[![v1.0.1](https://img.shields.io/badge/skin--support-v1.0.1-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.0.1/)
[![v0.0.2](https://img.shields.io/badge/skin--support--design-v0.0.2-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/0.0.2/)

* [介绍](#介绍)
* [更新日志](#更新日志)
  * [skin-support 更新日志](#skin-support-基础控件-支持)
  * [skin-support-design 更新日志](#skin-support-design-material-design-支持)
* [最佳实践](#最佳实践)
  * [网易云音乐换肤实践](#仿网易云音乐)
* [框架用法](#用法)
  * [导入](#导入)
  * [使用](#使用)
    * [初始化](#在application的oncreate中初始化)
    * [继承SkinCompatActivity](#继承skincompatactivity)
    * [加载插件皮肤库](#加载插件皮肤库)
    * [自定义view换肤](#自定义view换肤)
  * [制作皮肤插件](#制作皮肤插件)
    * [新建皮肤工程](#新建android-application工程)
    * [添加皮肤资源](#将需要换肤的资源放到res目录下同名资源)
    * [生成皮肤插件](#打包生成apk-即为皮肤包)
* [交流&打赏](#交流支持)
  * [加群交流](#加群交流)
  * [打赏支持](#打赏支持)
    * [支付宝](#支付宝扫码打赏)
    * [微信](#微信扫码打赏)
* [致谢](#致谢)
* [LICENSE](#license-mit)

## 介绍

Android-skin-support: 一款用心去做的Android 换肤框架, 极低的学习成本, 极好的用户体验.

只需要两行代码, 就可以实现换肤, 你值得拥有!!!

第一行: 在Application的onCreate中初始化

```java
SkinCompatManager.init(this).loadSkin();
```

第二行: 继承自SkinCompatActivity

```java
public class BaseActivity extends SkinCompatActivity {}
```

就这么简单, 你的APK已经拥有了强大的换肤功能.

[skin-app](skin-app)                        // 换肤demo app

[skin-night](skin-night)                    // 换肤demo 夜间模式

[skin-support](skin-support)                // 换肤框架, 基础控件支持

[skin-support-design](skin-support-design)  // 换肤框架, Material Design 支持

## 更新日志

### skin-support: 基础控件 支持

* [![v1.0.1](https://img.shields.io/badge/skin--support-v1.0.1-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.0.1/) 支持所有基础控件换肤
  * View
  * Button
  * Spinner
  * SeekBar
  * CheckBox
  * EditText
  * TextView
  * RatingBar
  * ImageView
  * ProgressBar
  * ImageButton
  * RadioButton
  * FrameLayout
  * LinearLayout
  * RelativeLayout
  * CheckedTextView
  * AutoCompleteTextView
  * MultiAutoCompleteTextView
  * android.support.v7.widget.Toolbar

### skin-support-design: material design 支持

* [![v0.0.2](https://img.shields.io/badge/skin--support--design-v0.0.2-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/0.0.2/) 支持以下三个控件换肤
  * TabLayout
  * AppBarLayout
  * NavigationView

## 最佳实践

### 仿网易云音乐
[仿网易云音乐皮肤切换](https://github.com/ximsfei/Skin-Demo)

[仿网易云音乐应用下载](https://github.com/ximsfei/Res/blob/master/skin-demo/app-debug.apk)

![red 1](https://github.com/ximsfei/Res/blob/master/skin-demo/red_1.png)
![red 2](https://github.com/ximsfei/Res/blob/master/skin-demo/red_2.png)
![red 3](https://github.com/ximsfei/Res/blob/master/skin-demo/red_3.png)

![white 1](https://github.com/ximsfei/Res/blob/master/skin-demo/white_1.png)
![white 2](https://github.com/ximsfei/Res/blob/master/skin-demo/white_2.png)
![white 3](https://github.com/ximsfei/Res/blob/master/skin-demo/white_3.png)

![night 1](https://github.com/ximsfei/Res/blob/master/skin-demo/night_1.png)
![night 2](https://github.com/ximsfei/Res/blob/master/skin-demo/night_2.png)
![night 3](https://github.com/ximsfei/Res/blob/master/skin-demo/night_3.png)

Testin 测试结果:
![Testin](https://github.com/ximsfei/Res/blob/master/skin-demo/testin_1.png)

## 用法

### 导入:
可以直接下载源码, 选择需要的module依赖：
```xml
git clone https://github.com/ximsfei/Android-skin-support.git
```
也可以直接添加依赖, [最新版本选择, 请查看更新日志](#更新日志)
```xml
compile 'skin.support:skin-support:1.0.1'        // skin-support 基础控件支持
compile 'skin.support:skin-support-design:0.0.2' // skin-support-design material design 控件支持
```

### 使用:

#### 在Application的onCreate中初始化
    
```java
@Override
public void onCreate() {
    super.onCreate();
    SkinCompatManager.init(this).loadSkin(); // 应用启动时初始化换肤框架并且加载当前(保存在SharedPreferences中)皮肤库
}
```

#### 继承SkinCompatActivity

让所有需要换肤的Activity继承自`skin.support.app.SkinCompatActivity`.

```java
public class BaseActivity extends SkinCompatActivity {}
```

#### 加载插件皮肤库

```java
// 指定皮肤插件
SkinCompatManager.getInstance().loadSkin("new.skin"[, SkinLoaderListener]);

// 恢复应用默认皮肤
SkinCompatManager.getInstance().restoreDefaultTheme();
```

#### 自定义View换肤

要点:

1. 实现SkinCompatSupportable接口
2. 在构造方法中解析出需要换肤的resId
3. 在applySkin方法中实现换肤

* 自定义View可以直接继承自SkinCompatView, SkinCompatLinearLayout等已有控件

  eg: [CustomTextView](skin-app/src/main/java/com/ximsfei/dynamicskindemo/widget/CustomTextView.java)

* 不想继承自已有控件

  eg: [CustomTextView2](skin-app/src/main/java/com/ximsfei/dynamicskindemo/widget/CustomTextView2.java)

* 需要换肤自定义属性

  // 需要换肤AutoCompleteTextView的R.attr.popupBackground属性

  eg: [SkinCompatAutoCompleteTextView](skin-support/src/main/java/skin/support/widget/SkinCompatAutoCompleteTextView.java)

* 需要使用第三方库控件怎么办

  // 需要使用https://github.com/hdodenhof/CircleImageView 控件, 并且要支持换肤

  eg: [CustomCircleImageView](skin-app/src/main/java/com/ximsfei/dynamicskindemo/widget/CustomCircleImageView.java)

### 制作皮肤插件:

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

## 交流&支持

### 加群交流

如有任何疑问, 可扫码加入QQ群进行技术交流:

![QQ群二维码](https://github.com/ximsfei/Res/blob/master/android_group.png)

### 打赏支持

如果觉得换肤框架很好用, 对您有所帮助, 请随意打赏, 或者分享转发给更多朋友.

您的支持与认可, 将使我在开源的道路上越走越远, 谢谢您！

#### 支付宝扫码打赏

![支付宝](https://github.com/ximsfei/Res/blob/master/ds/zfb_small.png)

#### 微信扫码打赏

![微信](https://github.com/ximsfei/Res/blob/master/ds/wx_small.png)

## 致谢

* [Android-Skin-Loader](https://github.com/fengjundev/Android-Skin-Loader)

* android com.android.support:appcompat-v7源码

## [License MIT](LICENSE)
