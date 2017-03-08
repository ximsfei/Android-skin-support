# 第三方控件库

## hdodenhof/CircleImageView

[项目原地址](https://github.com/hdodenhof/CircleImageView)

![v2.1.0.1](https://img.shields.io/badge/circleimageview-v2.1.0.1-green.svg)

```xml
compile 'de.hdodenhof:circleimageview:2.1.0'
compile 'skin.support:circleimageview:2.1.0.1'
```

### 使用方法一

* application onCreate中初始化

```xml
SkinCompatManager.init(this)
        .addInflater(new SkinCircleImageViewInflater()) // hdodenhof/CircleImageView
        .loadSkin();
```
或者
```xml
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