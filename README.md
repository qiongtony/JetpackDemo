# JetpackDemo
针对《Android Jetpack应用指南》编写的相应demo

## 第二章Lifecycle
出现的由来：某些类与系统组件的生命周期是相关的，在之前只能在系统组件的相应生命周期回调去手动调用类的相应方法，导致该类与系统组件的生命周期耦合在一起。

作用：通过Lifecycle可以在系统组件的相应生命周期到来时自动执行类的相应逻辑

Activity/Fragment实现了Observable（被观察者）接口，所以需要监听生命周期的类只需要实现Observer类并添加到Activity/Fragment的观察者列表就可以了

Service需要继承LifecycleService类，该类实现了Observable接口，然后才能添加Observer监听生命周期

Application的用法特殊一点，通过ProcessLifecycleOwner.get()获取应用的LifecycleObservable然后添加Observable监听应用的生命周期

## 第七章WorkManager
应用场景：不需要立即执行的任务，用于替代后台Service（有点鸡肋吧，用处相对小）

特点：
1. 会根据API版本，23+用JobScheduler实现，小于使用AlarmManager+Broadcast Receiver实现
2. 对于原生系统一定会执行（国内就算了吧，一堆魔改）
3. 兼容范围光<=14