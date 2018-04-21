# 更新日志

## skin-support: 基础控件 支持


* [![v2.2.0](https://img.shields.io/badge/skin--support-v2.2.0-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.2.0/) bugfix
  * bugfix:
    * issue #68 5.0以下支持svg换肤

* [![v2.1.10](https://img.shields.io/badge/skin--support-v2.1.10-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.1.10/) bugfix
  * bugfix:
    * 设置Background导致Padding无效问题
    * CheckedTextView 低版本手机崩溃问题

* [![v2.1.9](https://img.shields.io/badge/skin--support-v2.1.9-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.1.9/) bugfix 新功能开发
  * 使用Application Context换肤支持
  * 新增应用内换肤前缀加载策略
  * ViewGroup:
    * android:background
  * bugfix:
    * issue #91
  * 5.0以下，临时解决矢量图崩溃问题，尚未解决5.0以下矢量图换肤问题

* [![v2.1.5](https://img.shields.io/badge/skin--support-v2.1.5-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.1.5/) bugfix 新功能开发
  * fix: issue #87

* [![v2.1.4](https://img.shields.io/badge/skin--support-v2.1.4-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.1.4/) bugfix 新功能开发
  * android.support.v7.app.AlertDialog [换肤支持](AlertDialog.md)
  * fix: issue #77

* [![v2.1.3](https://img.shields.io/badge/skin--support-v2.1.3-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.1.3/) bugfix
  * 先写SharedPreferences, 再通知UI更新

* [![v2.1.2](https://img.shields.io/badge/skin--support-v2.1.2-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.1.2/) bugfix
  * 换肤失败异常处理

* [![v2.1.1](https://img.shields.io/badge/skin--support-v2.1.1-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.1.1/) 新功能开发 依赖 appcompat-v7:25.1.0
  * 新增皮肤包加载策略
    * 应用内资源换肤
    * 插件式换肤
    * 用户自定义换肤策略
  * CheckBox:
    * buttonTint
  * 加速View创建


* [![v2.0.4](https://img.shields.io/badge/skin--support-v2.0.4-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.0.4/) 新功能开发 依赖 appcompat-v7:25.1.0
  * ImageView:
    * app:srcCompat
  * ImageButton:
    * app:srcCompat
  * FloatingActionButton
    * app:srcCompat

* [![v2.0.3](https://img.shields.io/badge/skin--support-v2.0.3-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.0.3/) 新功能开发 依赖 appcompat-v7:25.1.0
  * public 方法

* [![v2.0.2](https://img.shields.io/badge/skin--support-v2.0.2-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.0.2/) 新功能开发 依赖 appcompat-v7:25.1.0
  * 状态栏换肤异常

* [![v2.0.1](https://img.shields.io/badge/skin--support-v2.0.1-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/2.0.1/) 新功能开发 依赖 appcompat-v7:25.1.0
  * 弃用SkinCompatActivity, 换肤框架集成更方便

* [![v1.2.8](https://img.shields.io/badge/skin--support-v1.2.8-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.2.8/) 新功能开发 依赖 appcompat-v7:25.1.0
  * ScrollView:
    * android:background
  * ProgressBar:
    * android:IndeterminateTint

* [![v1.2.7](https://img.shields.io/badge/skin--support-v1.2.7-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.2.7/) bugfix 依赖 appcompat-v7:25.1.0
  * fix: issue #29
  * fix: issue #33

* [![v1.2.6](https://img.shields.io/badge/skin--support-v1.2.6-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.2.6/) 新功能开发 依赖 appcompat-v7:25.1.0
  * android:statusBarColor   // 5.0+状态栏换肤, 先取statusBarColor
  * android:colorPrimaryDark // 5.0+状态栏换肤, 未设置statusBarColor, 通过colorPrimaryDark换肤

* [![v1.2.5](https://img.shields.io/badge/skin--support-v1.2.5-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.2.5/) 新功能开发 依赖 appcompat-v7:25.1.0
  * RadioGroup
    * android:background
  * RadioButton
    * android:background
  * 通过代码设置drawable start left top end right bottom
    * setCompoundDrawablesRelativeWithIntrinsicBounds
    * setCompoundDrawablesWithIntrinsicBounds

* [![v1.2.4](https://img.shields.io/badge/skin--support-v1.2.4-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.2.4/) bugfix 依赖 appcompat-v7:25.1.0
  * 修复View, RelativeLayout, LinearLayout, FrameLayout在代码中setBackgroundResource换肤失效的问题

* [![v1.2.3](https://img.shields.io/badge/skin--support-v1.2.3-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.2.3/) 新功能开发 依赖 appcompat-v7:25.1.0
  * Hook support. 通过addHookInflater方法, 支持开发者定制基础控件换肤.

* [![v1.2.1](https://img.shields.io/badge/skin--support-v1.2.1-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.2.1/) bugfix 依赖 appcompat-v7:25.1.0

  * bugfix 5.0以下TabLayout换肤失败

* [![v1.2.0](https://img.shields.io/badge/skin--support-v1.2.0-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.2.0/) 新功能开发 依赖 appcompat-v7:25.1.0
  * TextHelper 支持drawableLeft, drawableTop, drawableRight, drawableBottom 换肤
  * remove skinSupport

* [![v1.1.2](https://img.shields.io/badge/skin--support-v1.1.2-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.1.2/) 优化 依赖 appcompat-v7:25.1.0

  * bugfix 连续切换皮肤导致的崩溃问题
  * 优化module初始化方式

* [![v1.1.0](https://img.shields.io/badge/skin--support-v1.1.0-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.1.0/) 新功能开发 依赖 appcompat-v7:25.1.0

  * 支持开发者标记不换肤控件

* [![v1.0.2](https://img.shields.io/badge/skin--support-v1.0.2-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.0.2/) bugfix 依赖 appcompat-v7:25.1.0

  * 解决RecyclerView中item无法回收导致的OutOfMemory问题

  * 关闭Debug Log

* [![v1.0.1](https://img.shields.io/badge/skin--support-v1.0.1-green.svg)](http://jcenter.bintray.com/skin/support/skin-support/1.0.1/) 支持所有基础控件换肤 依赖 appcompat-v7:25.1.0
  * View
    * android:background
  * Button
    * android:background
    * android:textColor
    * android:textColorHint
    * android:drawableLeft
    * android:drawableTop
    * android:drawableRight
    * android:drawableBottom
  * Spinner
    * android:popupBackground
    * android:background
  * SeekBar
    * android:thumb
    * android:indeterminateDrawable
    * android:progressDrawable
  * CheckBox
    * android:button
  * EditText
    * android:background
    * android:textColor
    * android:textColorHint
    * android:drawableLeft
    * android:drawableTop
    * android:drawableRight
    * android:drawableBottom
  * TextView
    * android:background
    * android:textColor
    * android:textColorHint
    * android:drawableLeft
    * android:drawableTop
    * android:drawableRight
    * android:drawableBottom
  * RatingBar
    * android:indeterminateDrawable
    * android:progressDrawable
  * ImageView
    * android:background
    * android:src
  * ProgressBar
    * android:indeterminateDrawable
    * android:progressDrawable
  * ImageButton
    * android:background
    * android:src
  * RadioButton
    * android:button
    * android:drawableLeft
    * android:drawableTop
    * android:drawableRight
    * android:drawableBottom
  * FrameLayout
    * android:background
  * LinearLayout
    * android:background
  * RelativeLayout
    * android:background
  * CheckedTextView
    * android:checkMark
    * android:background
    * android:textColor
    * android:textColorHint
    * android:drawableLeft
    * android:drawableTop
    * android:drawableRight
    * android:drawableBottom
  * AutoCompleteTextView
    * android:popupBackground
    * android:background
    * android:textColor
    * android:textColorHint
    * android:drawableLeft
    * android:drawableTop
    * android:drawableRight
    * android:drawableBottom
  * MultiAutoCompleteTextView
    * android:popupBackground
    * android:background
    * android:textColor
    * android:textColorHint
    * android:drawableLeft
    * android:drawableTop
    * android:drawableRight
    * android:drawableBottom
  * android.support.v7.widget.Toolbar
    * android:background
    * app:navigationIcon
    * app:titleTextColor
    * app:subtitleTextColor

## skin-support-design: material design 支持


* [![v1.3.0](https://img.shields.io/badge/skin--support--design-v1.3.0-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/1.3.0/) 依赖skin-support:1.2.7 design:25.1.0
  * bugfix: issue #68

* [![v1.2.6](https://img.shields.io/badge/skin--support--design-v1.2.6-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/1.2.6/) 依赖skin-support:1.2.7 design:25.1.0
  * bugfix: issue #105

* [![v1.2.5](https://img.shields.io/badge/skin--support--design-v1.2.5-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/1.2.5/) 依赖skin-support:1.2.7 design:25.1.0
  * 加速View创建

* [![v1.2.4](https://img.shields.io/badge/skin--support--design-v1.2.4-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/1.2.4/) 依赖skin-support:1.2.7 design:25.1.0
  * fix: issue #29
  * fix: issue #33

* [![v1.2.3](https://img.shields.io/badge/skin--support--design-v1.2.3-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/1.2.3/) 依赖skin-support:1.2.5 design:25.1.0
  * NavigationView
    * app:itemIconTint
    * app:itemTextColor
    * app:itemBackground

* [![v1.2.2](https://img.shields.io/badge/skin--support--design-v1.2.2-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/1.2.2/) bugfix 依赖skin-support:1.2.5 design:25.1.0

* [![v1.2.1](https://img.shields.io/badge/skin--support--design-v1.2.1-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/1.2.1/) bugfix 依赖skin-support:1.2.1 design:25.1.0

  * bugfix 5.0以下TabLayout换肤失败

* [![v1.2.0](https://img.shields.io/badge/skin--support--design-v1.2.0-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/1.2.0/) 新功能开发 依赖skin-support:1.2.0 design:25.1.0
  * remove skinSupport

* [![v1.0.0](https://img.shields.io/badge/skin--support--design-v1.0.0-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/1.0.0/) 新功能开发 依赖skin-support:1.1.2 design:25.1.0
  * BottomNavigationView
    * app:itemIconTint
    * app:itemTextColor
  * CollapsingToolbarLayout
    * android:background
    * app:contentScrim
    * app:statusBarScrim
  * CoordinatorLayout
    * android:background
  * FloatingActionButton
    * android:src
    * app:backgroundTint
    * app:rippleColor
  * TextInputEditText
    * android:background
    * android:textColor
    * android:textColorHint
    * android:drawableLeft
    * android:drawableTop
    * android:drawableRight
    * android:drawableBottom
  * TextInputLayout
    * android:background
    * android:textColorHint
    * app:errorTextAppearance
    * app:counterTextAppearance

* [![v0.1.0](https://img.shields.io/badge/skin--support--design-v0.1.0-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/0.1.0/) 新功能开发 依赖skin-support:1.1.0 design:25.1.0

  * 支持开发者标记不换肤控件

* [![v0.0.2](https://img.shields.io/badge/skin--support--design-v0.0.2-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-design/0.0.2/) 支持以下三个控件换肤 依赖skin-support:1.0.2 design:25.1.0
  * TabLayout
    * app:tabIndicatorColor
    * app:tabSelectedTextColor
    * app:tabTextColor
  * AppBarLayout
    * android:background
  * NavigationView
    * android:background

## skin-support-cardview: CardView 支持


* [![v1.2.1](https://img.shields.io/badge/skin--support--cardview-v1.2.1-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-cardview/1.2.1/) CardView 支持 依赖skin-support:1.2.1 cardview-v7:25.1.0
  * bugfix: #105

* [![v1.2.0](https://img.shields.io/badge/skin--support--cardview-v1.2.0-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-cardview/1.2.0/) CardView 支持 依赖skin-support:1.2.0 cardview-v7:25.1.0
  * remove skinSupport

* [![v1.0.0](https://img.shields.io/badge/skin--support--cardview-v1.0.0-green.svg)](http://jcenter.bintray.com/skin/support/skin-support-cardview/1.0.0/) CardView 支持 依赖skin-support:1.1.2 cardview-v7:25.1.0
  * CardView
    * app:cardBackgroundColor

## skin-support-constraint-layout: ConstraintLayout 支持


* [![v1.0.2.1](https://img.shields.io/badge/skin--support--constraint--layout-v1.0.2.1-green.svg)](http://http://jcenter.bintray.com/skin/support/skin-support-constraint-layout/1.0.2.1/) ConstraintLayout 支持 依赖skin-support:1.2.7 constraint-layout:1.0.2
  * bugfix: #105

* [![v1.0.2](https://img.shields.io/badge/skin--support--constraint--layout-v1.0.2-green.svg)](http://http://jcenter.bintray.com/skin/support/skin-support-constraint-layout/1.0.2/) ConstraintLayout 支持 依赖skin-support:1.2.7 constraint-layout:1.0.2
  * ConstraintLayout
    * android:background
