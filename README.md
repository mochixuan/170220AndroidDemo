## AndroidDemo (个人练习的Demo)#### 7静默安装和卸载> 到目前位置搞过了Android 4.2.2(MTK) 4.4.2(T3) 6.0(T3)
> 所以方法都是在拥有签名的情况下
> 一段血泪史啊，写下经验
> 下面6.0的方法在6.0一下是成功的，试过了
``` java	android 4.2.2 4.4.2 实现方法 	1.添加aidl 只要6个就可以	IPackageDataObserver.aidl  IPackageDeleteObserver.aidl  IPackageInstallObserver.aidl	IPackageManager.aidl  IPackageMoveObserver.aidl  IPackageStatsObserver.aidl		2.权限	<manifest android:sharedUserId="android.uid.system">	//最上面	<uses-permission android:name="android.permission.INSTALL_PACKAGES" />    <uses-permission android:name="android.permission.DELETE_PACKAGES" />    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />	3.安装	Class<?> clazz = Class.forName("android.os.ServiceManager");            Method method_getService = clazz.getMethod("getService", String.class);            IBinder bind = (IBinder) method_getService.invoke(null, "package");            IPackageManager iPm = IPackageManager.Stub.asInterface(bind);            iPm.installPackage(Uri.fromFile(apkFile), new IPackageInstallObserver.Stub(){                @Override                public void packageInstalled(String packageName, int returnCode) throws RemoteException {                }            }, 2, apkFile.getName());	4.卸载	Class<?> clazz = Class.forName("android.os.ServiceManager");    Method method_getService = clazz.getMethod("getService",String.class);    IBinder bind = (IBinder) method_getService.invoke(null, "package");    IPackageManager iPm = IPackageManager.Stub.asInterface(bind);    iPm.deletePackageAsUser(packageName,null,0,2);	5.签名再放到system/app 或者system/priv-app``````java	android6.0,被它坑了一天半	1.权限	<manifest android:sharedUserId="android.uid.system">	//最上面	<uses-permission android:name="android.permission.INSTALL_PACKAGES" />    <uses-permission android:name="android.permission.DELETE_PACKAGES" />    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />	2.安装方法1 (最好放在线程中操作)		File file = new File(filePath);        if (filePath == null || filePath.length() == 0 || file == null) {            return 0;        }        String[] args = { "pm", "install", "-r", filePath };        ProcessBuilder processBuilder = new ProcessBuilder(args);        Process process = null;        BufferedReader successResult = null;        BufferedReader errorResult = null;        StringBuilder successMsg = new StringBuilder();        StringBuilder errorMsg = new StringBuilder();        try {            process = processBuilder.start();            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));            String s;            while ((s = successResult.readLine()) != null) {                successMsg.append(s);            }            while ((s = errorResult.readLine()) != null) {                errorMsg.append(s);            }        } catch (IOException e1) {            e1.printStackTrace();        } finally {            try {                if (successResult != null) {                    successResult.close();                }                if (errorResult != null) {                    errorResult.close();                }            } catch (IOException e1) {                e1.printStackTrace();            }            if (process != null) {                process.destroy();            }        }        if (successMsg.toString().contains("Success") || successMsg.toString().contains("success")) {            return 2;        } else {            return 1;        }	2.安装方法2 (最好放在线程中操作)			----------------------源码方法---------------------------			 public abstract void installPackage(Uri packageURI, IPackageInstallObserver observer, 					int flags,String installerPackageName);			--------------------------------------------------------			PackageManager pm = context.getPackageManager();            Method[] methods = pm!=null?pm.getClass().getDeclaredMethods():null;            Method mDel = null;            if (methods != null && methods.length>0) {                for (Method method : methods) {                    if (method.getName().toString().equals("installPackage")) {                        mDel = method;						break;                    }                }            }            if (mDel != null) {                mDel.setAccessible(true);                mDel.invoke(pm,Uri.fromFile(apkFile),null,2,apkFile.getName);            }	3.卸载			----------------------源码方法---------------------------			 public abstract void deletePackage(String packageName, 					IPackageDeleteObserver observer, int flags);			--------------------------------------------------------			PackageManager pm = context.getPackageManager();            Method[] methods = pm!=null?pm.getClass().getDeclaredMethods():null;            Method mDel = null;            if (methods != null && methods.length>0) {                for (Method method : methods) {                    if (method.getName().toString().equals("deletePackage")) {                        mDel = method;                        break;                    }                }            }            if (mDel != null) {                mDel.setAccessible(true);                mDel.invoke(pm,packageName,null,2);            }```#### 6.RecyclerView复杂布局 (UiMaster)> 本App全部使用KotLin进行编写<br/>> 使用一个RecyclerView实现复制布局 <br/>> 学习使用天猫的Vlayout解决不瀑流布局，很好用，性能也不错。 <br/>> 左图原生一个RecyclerView实现，右边为天猫Vlayout一个RecyclerView实现。<br/>```kotlin主要实现SpanSizeLookup方法override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {        super.onAttachedToRecyclerView(recyclerView)        val manager: RecyclerView.LayoutManager = recyclerView!!.layoutManager        if (manager is GridLayoutManager) {            val gridManager: GridLayoutManager = manager            gridManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {                override fun getSpanSize(position: Int): Int {                    val type = getItemViewType(position)                    when (type) {                        BANNER , RECOMMEND_HEAD , ACTION_HEAD , ACTION_HEAD , LIST_HEAD , LIST-> return 6                        NAV , RECOMMEND -> return 3                        ACTION -> return 2                        else -> return 6                    }                }            }        }    }```![image](https://github.com/mochixuan/170220AndroidDemo/blob/master/Android-Demo/UiMaster/img/img1.jpg)	- - - ![image](https://github.com/mochixuan/170220AndroidDemo/blob/master/Android-Demo/UiMaster/img/img2.jpg)#### 5.又看了一下MD	(MaterialDesign)![image](https://github.com/mochixuan/170220AndroidDemo/blob/master/Android-Demo/MaterialDesign/MDMaster/img/img1.jpg)![image](https://github.com/mochixuan/170220AndroidDemo/blob/master/Android-Demo/MaterialDesign/MDMaster/img/img2.jpg)#### 4.学习动态注解	(AnnotattionDemo)> 重新复习了一下反射机制(过几天不用又要忘记) <br/>> 安卓的注解(appcompat-v7自带)自定义好像不支持原因未查(不支持5.0以下的) <br/>> 大致看了一下注解实现过程，并没有去看那些注解框架源码(Dagger2等)如何实现的。<br/>> [相关资料1](http://blog.csdn.net/wzgiceman/article/details/53483665)	 [相关资料2](http://www.cnblogs.com/whoislcj/category/845938.html)####  3.使用DataBinding,Kotlin LBS(高德)练习Kotlin与熟悉高德地图	(LBSMap) > 本来Kotlin的防止非空异常是它的一个优点，在Json数据的非空判断时确实非常高效，但个人感觉有点鸡肋，有时候多了很多非空判断尤其是在已经if(x != null)里面还要判断非空!!.####  2.使用Kotlin练习高德地图模仿单车	(LBSMap)``` javakolin与DataBinding有冲突  kapt "com.android.databinding:compiler:2.2.3"kapt {	generateStubs = true}```![image](https://github.com/mochixuan/170220AndroidDemo/blob/master/Android-Demo/LBSMap/GaodeMaster/img/img1.jpg)#### 1.学习Realm	(Realm)> 虽然支持RxJava但在被订阅时onComplete()好像没有调用 <br/>> 方便简单速度快。<br/>> 不能实现线程切换操作数据，哪里生成realm在那个线程处理。<br/>