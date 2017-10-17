# Frequently Asked Questions

## 控件资源在换肤之前是正常的，但是切换皮肤后，控件资源恢复成默认的资源了?

  肯定是布局文件里写了`android:src="@drawable/default"`，从服务端获取数据后，手动调用了`setImageDrawable(serverDrawable)`。解决办法是，去掉布局文件重的`android:src="@drawable/default"`，直接在代码中设置默认图片`setImageDrawable(getDrawable(R.drawable.default)`

  问题解析:

  该框架是基于Resource Id来进行换肤的。控件初始化时，会解析布局文件中设置的资源(或者有业务代码直接设置`setImageResource(R.drawable.default)`)，例如本例中的`R.drawable.default`，在切换皮肤时，根据资源名default，去查找需要换肤的资源。所以当获取到服务端数据后，更新了drawable，但是框架所知道的需要换肤的资源还是`R.drawable.default`，在切换皮肤的时候，找不到对应的皮肤资源，就是用了默认的资源。