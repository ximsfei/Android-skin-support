# Android-skin-support
Android-skin-support: 深入Android源码, 新思路实现Android换肤框架, 极低的学习成本, 极好的用户体验. 只需要两行代码, 就可以实现换肤, 你值得拥有!!!

第一行: 在Application的onCreate中初始化

```java
SkinCompatManager.init(this).loadSkin();
```

第二行: 继承自SkinCompatActivity

```java
public class BaseActivity extends SkinCompatActivity {}
```

* [演示](#演示)
* [支持](#支持)
* [用法](#用法)
* [合作开发&交流](#合作开发&交流)
* [最佳实践](#最佳实践)
* [致谢](#致谢)
* [License-MIT](#License MIT)

## 演示

下载[demo](demo/app-debug.apk) 或者下载源码打包apk, 安装到手机上即可查看效果, 在demo apk的assets中已经包含插件皮肤库.

![demo-preview](https://github.com/ximsfei/Res/blob/master/skin/preview/skin-default-preview.gif)

## 支持

### 目前支持:

1. TextView
2. Button
3. EditText
4. ImageView
5. Toolbar
6. 未完待续...

### 将要支持:

## 用法

### 导入:

```xml
git clone https://github.com/ximsfei/Android-skin-support.git
```

作为module依赖：

```xml
// 可以打开调试日志
dependencies {
    releaseCompile project(path: ':skin-support', configuration: 'release')
    debugCompile project(path: ':skin-support', configuration: 'debug')
}
```

或者

```xml
dependencies {
    compile project(':skin-support')
}
```

### 初始化:

#### 在Application的onCreate中初始化
    
```java
@Override
    public void onCreate() {
        super.onCreate();
        SkinCompatManager.init(this)
                .setStatusBarColor("colorPrimaryDark") // 可选, 默认为colorPrimaryDark, 设置状态栏颜色@color/colorPrimaryDark
                .loadSkin();                           // 应用启动加载当前(保存在SharedPreferences中)皮肤库
    }
```

#### 继承SkinCompatActivity

让所有需要换肤的Activity继承自`skin.support.app.SkinCompatActivity`.

#### 加载插件皮肤库

```java
// 指定皮肤插件, 并且监听加载状态
SkinCompatManager.getInstance().loadSkin("new.skin", new SkinCompatManager.SkinLoaderListener() {
    @Override
    public void onStart() {
        SkinLog.d("onStart");
    }

    @Override
    public void onSuccess() {
        SkinLog.d("onSuccess");
    }

    @Override
    public void onFailed(String errMsg) {
        SkinLog.d("onFailed");
    }
});

// 恢复应用默认皮肤
SkinCompatManager.getInstance().restoreDefaultTheme();
```

### 制作皮肤插件:

#### 新建Android application工程

#### 将需要换肤的资源放到res目录下(同名资源)

#### 打包生成apk, 即为皮肤包

## 合作开发&交流:

![QQ群二维码](https://github.com/ximsfei/Res/blob/master/Android%26%2332%3B%E6%8A%80%E6%9C%AF%E4%BA%A4%E6%B5%81%E7%BE%A4%E4%BA%8C%E7%BB%B4%E7%A0%81.png))

## 最佳实践:

## [License (MIT)](LICENSE)