# Android-skin-support

[中文](../README.md) | In English 

[![skin-support](https://img.shields.io/badge/release-v3.1.2-green.svg)](http://jcenter.bintray.com/skin/support)
![build](https://img.shields.io/badge/build-passing-green.svg)
![license](https://img.shields.io/badge/license-mit-blue.svg)

Android-skin-support is an easy to use skin framework for Android. The best case, Only one line of code to integrate the framework.

```java
SkinCompatManager.withoutActivity(this).loadSkin();
```

Now, you have a strong skinning feature. What you need to do is [make a skin](#make-a-skin).

![default](https://github.com/ximsfei/Res/blob/master/skin/preview/default.png)
![app-in](https://github.com/ximsfei/Res/blob/master/skin/preview/app-in.png)
![plug-in](https://github.com/ximsfei/Res/blob/master/skin/preview/plug-in.png)

## Table of Contents

* [Gradle Dependencies](#gradle-dependencies)
* [Integration](#integration)
  * [Initialization](#initialization)
  * [Load Skin](#load-skin)
  * [Custom View skin support](#custom-view-skin-support)
* [Make a skin](#make-a-skin)
  * [BuildIn Skin](#buildin-skin)
  * [Plug-In Skin](#plug-in-skin)
* [Change Log](ChangeLog.md)
* [Who Use It](#who-use-it)
* [About Author](#about-author)
* [LICENSE](#license-mit)

## Gradle Dependencies

The Gradle Dependency is available via [jCenter](https://bintray.com/pengfeng/skin-support),

Add dependencies directly, [For the latest version, please refer to change log](ChangeLog.md)

```xml
implementation 'skin.support:skin-support:3.1.2'                   // skin-support basic widget
implementation 'skin.support:skin-support-design:3.1.2'            // skin-support-design material design support [selectable]
implementation 'skin.support:skin-support-cardview:3.1.2'          // skin-support-cardview CardView support [selectable]
implementation 'skin.support:skin-support-constraint-layout:3.1.2' // skin-support-constraint-layout ConstraintLayout support [selectable]
```

## Integration

### Initialization

Only one line of code to integrate the framework.

```java
@Override
public void onCreate() {
    super.onCreate();
    SkinCompatManager.withoutActivity(this)                         // Basic Widget support
            .addInflater(new SkinMaterialViewInflater())            // material design support           [selectable]
            .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout support          [selectable]
            .addInflater(new SkinCardViewInflater())                // CardView v7 support               [selectable]
            .setSkinStatusBarColorEnable(false)                     // Disable statusBarColor skin support，default true   [selectable]
            .setSkinWindowBackgroundEnable(false)                   // Disable windowBackground skin support，default true [selectable]
            .loadSkin();
}
```

> If your Activity inherits from AppCompatActivity, you need to override the getDelegate() method.

```java
@NonNull
@Override
public AppCompatDelegate getDelegate() {
    return SkinAppCompatDelegateImpl.get(this, this);
}
```

### Load Skin

```java
// Load the specified skin
SkinCompatManager.getInstance().loadSkin("new.skin"[, SkinLoaderListener], int strategy);

// restore default skin
SkinCompatManager.getInstance().restoreDefaultTheme();
```

### Custom View skin support

1. Implement the `SkinCompatSupportable` interface

  1. Apply skin resource in the applySkin method

2. Resolve the skin resource id in the constructor

* Custom View can inherit directly from existing widget, such as `SkinCompatView`, `SkinCompatLinearLayout`, etc.

  eg: [CustomTextView](../demo/skin-app/src/main/java/com/ximsfei/skindemo/widget/CustomTextView.java)

* if you don't want to inherit from existing widget.

  eg: [CustomTextView2](../demo/skin-app/src/main/java/com/ximsfei/skindemo/widget/CustomTextView2.java)

* If you need to skin the custom attributes.

  // such as AutoCompleteTextView's R.attr.popupBackground attribute

  eg: [SkinCompatAutoCompleteTextView](../android-support/skin-support/src/main/java/skin/support/widget/SkinCompatAutoCompleteTextView.java)

* If you need to use third-party library.

  // need to use https://github.com/hdodenhof/CircleImageView widget

  eg: [SkinCompatCircleImageView](../third-part-support/circleimageview/src/main/java/skin/support/circleimageview/widget/SkinCompatCircleImageView.java)

## Make a skin

### BuildIn Skin:

BuildIn Skin，if the skin name is `night`; Add a resource that needs to be skinned with a suffix `_night` or prefix `night_`.

if the default resource is `R.color.windowBackgroundColor`, then you can add a resource `R.color.windowBackgroundColor_night`。

load buildIn skin:

```java
SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN); // load by suffix
SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_PREFIX_BUILD_IN); // load by prefix
```

### Plug-in Skin:

#### New Android Application Project

#### Put the skin resources into the `res` directory

If the original window background is

colors.xml
```xml
<color name="background">#ffffff</color>
```

for night-mode you can add this in the `skin-night` project

colors.xml
```xml
<color name="background">#000000</color>
```

#### Generated apk is the skin package

You can rename night.apk to night.skin by yourself.

#### Load plug-in skin package

You can put the skin package into the assets/skins directory.

```java
SkinCompatManager.getInstance().loadSkin("night.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
```

Or you can customize the loading strategy:

For example:

Inherit from `SkinSDCardLoader`，Override the `getSkinPath`、`getType` methods。

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

Register this strategy in Application's `onCreate`

```java
SkinCompatManager.withoutActivity(this).addStrategy(new CustomSDCardLoader());
```

Use this strategy to load skin:

```java
SkinCompatManager.getInstance().loadSkin("night.skin", null, CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD);
```

## Who use it

If you want to list your apk here. Welcome to commit [pull request](who-use-it) or Contact the [author](#about-author).

| icon | name | description |
| :--: | :--- | :---------- |
| [<img width="96" height="96" src="https://github.com/ximsfei/Android-skin-support/blob/master/docs/who-use-it/sohunews_explore-icon.png"/>](https://k.sohu.com/) | [sohu newsclient lite](https://k.sohu.com/) | 
| [<img width="96" height="96" src="https://github.com/ximsfei/Android-skin-support/blob/master/docs/who-use-it/vflynote-icon.png"/>](http://www.iyuji.cn/iyuji/home) | [voice note](http://www.iyuji.cn/iyuji/home) |
| [<img width="96" height="96" src="https://github.com/ximsfei/Android-skin-support/blob/master/docs/who-use-it/qoo-app.png"/>](https://news.qoo-app.com/) | [qoo app](https://news.qoo-app.com/) |
| [<img width="96" height="96" src="https://github.com/ximsfei/Android-skin-support/blob/master/docs/who-use-it/dwnews.png"/>](http://www.dwnews.com/) | [dwnews](http://www.dwnews.com/)|

## About Author

Pengfeng Wang(王鹏锋)

email: ximsfei@gmail.com

## [License MIT](LICENSE)
