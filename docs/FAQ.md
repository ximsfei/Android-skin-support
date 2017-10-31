# Frequently Asked Questions

## 控件资源在换肤之前是正常的，但是切换皮肤后，控件资源恢复成默认的资源了?

  肯定是布局文件里写了`android:src="@drawable/default"`，从服务端获取数据后，手动调用了`setImageDrawable(serverDrawable)`。解决办法是，去掉布局文件重的`android:src="@drawable/default"`，直接在代码中设置默认图片`setImageDrawable(getDrawable(R.drawable.default)`

  问题解析:

  该框架是基于Resource Id来进行换肤的。控件初始化时，会解析布局文件中设置的资源(或者有业务代码直接设置`setImageResource(R.drawable.default)`)，例如本例中的`R.drawable.default`，在切换皮肤时，根据资源名default，去查找需要换肤的资源。所以当获取到服务端数据后，更新了drawable，但是框架所知道的需要换肤的资源还是`R.drawable.default`，在切换皮肤的时候，找不到对应的皮肤资源，就是用了默认的资源。
  
## 代码内实例化的view对象，不能更新皮肤?

  代码中使用new View()创建对象，框架无法知道该控件的存在。可以在代码中使用View.inflate()来实例化view对象。
  
  问题解析:
  
  该框架是基于LayoutInfalter在实例化View的时候，将View替换成自定义View的方式实现的，所以代码中创建的实例对象，框架无法感知。原本可以通过提供一个接口，让业务层代码在创建View的时候，主动通知框架，但是这样，在接入框架时会对业务代码有所侵入，所以没有采取该种方案。而是让业务代码统一使用View.inflate()的方式来实例化View对象。
  
## 为什么有些控件设置了`android:background="@drawable/background"`，切换皮肤后，背景却没有改变(其他属性均可参考)?

  可以通过查看[更新日志](ChangeLog.md)，确定该控件的该属性，框架是否提供默认换肤支持。

  问题解析:

  该框架默认支持了一些常用的属性换肤。出于对换肤性能的考虑，不常用的属性，没有做默认支持，业务层可根据自己的需求，参考[自定义View换肤](https://github.com/ximsfei/Android-skin-support#自定义view换肤)来扩展。
