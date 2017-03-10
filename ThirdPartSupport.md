# 第三方控件库

* [hdodenhof/CircleImageView](#hdodenhofcircleimageview)
* [H07000223/FlycoTabLayout](#h07000223/flycotablayout)

## hdodenhof/CircleImageView

[项目原地址](https://github.com/hdodenhof/CircleImageView)

![v2.1.0.1](https://img.shields.io/badge/circleimageview-v2.1.0.1-green.svg)

```xml
compile 'de.hdodenhof:circleimageview:2.1.0'
compile 'skin.support:circleimageview:2.1.0.1'
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

![v2.1.2](https://img.shields.io/badge/flycotablayout-v2.1.2-green.svg)

```xml
compile 'com.flyco.tablayout:FlycoTabLayout_Lib:2.1.2@aar'
compile 'skin.support:flycotablayout:2.1.2'
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