# Android-skin-support

* [介绍](#介绍)
* [最佳实践](#最佳实践)
* [支持](#支持)
* [用法](#用法)
* [交流合作](#交流合作)
* [致谢](#致谢)

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

## 最佳实践

[仿网易云音乐皮肤切换](https://github.com/ximsfei/Skin-Demo)

[下载](https://github.com/ximsfei/Res/blob/master/skin-demo/app-debug.apk)

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

## 支持

### 目前支持:

* 支持Android 4.x, 5.x, 6.x, 7.x

* [支持自定义View换肤](#自定义view换肤)

* skin-support: 基础控件 支持

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

* skin-support-design: material design 支持

  * TabLayout
  * AppBarLayout
  * NavigationView

### 将要支持:

  * Menu
  * 状态栏换肤
  * ...

## 用法

### 导入:

```xml
git clone https://github.com/ximsfei/Android-skin-support.git
```

选择需要的module依赖：

```xml
// aar
dependencies {
    compile(name: 'skin-support-release', ext: 'aar') // Android 基础控件, 及V7包中的一些控件
    compile(name: 'skin-support-design-release', ext: 'aar') // Android support design包中的一些控件
}
```

或者

```xml
dependencies {
    compile project(':skin-support')
    compile project(':skin-support-design')
}
```

或者

```xml
// 可以打开调试日志
dependencies {
    releaseCompile project(path: ':skin-support', configuration: 'release')
    debugCompile project(path: ':skin-support', configuration: 'debug')
    releaseCompile project(path: ':skin-support-design', configuration: 'release')
    debugCompile project(path: ':skin-support-design', configuration: 'debug')
}
```

### 初始化:

#### 在Application的onCreate中初始化
    
```java
@Override
public void onCreate() {
    super.onCreate();
    SkinCompatManager.init(this).loadSkin(); // 应用启动加载当前(保存在SharedPreferences中)皮肤库
}
```

#### 继承SkinCompatActivity

让所有需要换肤的Activity继承自`skin.support.app.SkinCompatActivity`.

```java
public class BaseActivity extends SkinCompatActivity {}
```

#### 加载插件皮肤库

```java
// 指定皮肤插件, 并且监听加载状态
SkinCompatManager.getInstance().loadSkin("new.skin");

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

#### 将需要换肤的资源放到res目录下(同名资源)

#### 打包生成apk, 即为皮肤包

## 交流合作

深入研究发现Android换肤原理其实不难, 难在要实现一个完善的换肤框架所带来的工作量, 所以在没有特殊原因的前提下, 希望大家多多使用, 多多交流, 为本项目贡献自己的力量, 为所有Android开发者带来福音, 减少没必要的工作量

![QQ群二维码](https://github.com/ximsfei/Res/blob/master/android_group.png)

## 致谢

* [Android-Skin-Loader](https://github.com/fengjundev/Android-Skin-Loader)

* android com.android.support:appcompat-v7源码

## [License MIT](LICENSE)