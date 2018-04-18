# 第三方控件库

* [hdodenhof/CircleImageView](#hdodenhofcircleimageview)
* [H07000223/FlycoTabLayout](#h07000223flycotablayout)
* [hongyangAndroid/AndroidAutoLayout](#hongyangandroidandroidautolayout)

## hdodenhof/CircleImageView

[项目原地址](https://github.com/hdodenhof/CircleImageView)

[![v2.1.0.2](https://img.shields.io/badge/circleimageview-v2.1.0.2-green.svg)](http://jcenter.bintray.com/skin/support/circleimageview/2.1.0.2/)

```xml
implementation 'de.hdodenhof:circleimageview:2.1.0'
implementation 'skin.support:circleimageview:2.1.0.4'
```

### 使用方法一

* application onCreate中初始化

```java
SkinCompatManager.init(this)
        .addInflater(new SkinCircleImageViewInflater()) // hdodenhof/CircleImageView
        .loadSkin();
```
或者
```java
SkinCircleImageViewManager.init(this);
```

布局文件中引用
```xml
<de.hdodenhof.circleimageview.CircleImageView
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/profile_image"
    android:layout_width="96dp"
    android:layout_height="96dp"
    android:src="@drawable/profile"
    app:civ_border_width="2dp"
    app:civ_border_color="@color/border_color"/>
```

### 使用方法二

直接在布局文件中引用
```xml
<skin.support.circleimageview.widget.SkinCompatCircleImageView
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/profile_image"
    android:layout_width="96dp"
    android:layout_height="96dp"
    android:src="@drawable/profile"
    app:civ_border_width="2dp"
    app:civ_border_color="@color/border_color"/>
```

## H07000223/FlycoTabLayout

[项目原地址](https://github.com/H07000223/FlycoTabLayout)

[![v2.1.2](https://img.shields.io/badge/flycotablayout-v2.1.2-green.svg)](http://jcenter.bintray.com/skin/support/flycotablayout/2.1.2/)

```xml
implementation 'com.flyco.tablayout:FlycoTabLayout_Lib:2.1.2@aar'
implementation 'skin.support:flycotablayout:2.1.2.2'
```

### 使用方法

* application onCreate中初始化

```java
SkinCompatManager.init(this)
        .addInflater(new SkinFlycoTabLayoutInflater()) // H07000223/FlycoTabLayout
        .loadSkin();
```
或者
```java
SkinFlycoTabLayoutManager.init(this);
```

## hongyangAndroid/AndroidAutoLayout

[项目原地址](https://github.com/hongyangAndroid/AndroidAutoLayout)

[![v1.4.5](https://img.shields.io/badge/androidautolayout-v1.4.5-green.svg)](http://jcenter.bintray.com/skin/support/androidautolayout/1.4.5/)

```xml
implementation 'com.zhy:autolayout:1.4.5'
implementation 'skin.support:androidautolayout:1.4.5.2'
```

### 使用方法

* application onCreate中初始化

```java
SkinCompatManager.init(this)
        .addHookInflater(new SkinHookAutoLayoutViewInflater()) // hongyangAndroid/AndroidAutoLayout
        .loadSkin();
```
或者
```java
SkinHookAutoLayoutManager.init(this);
```