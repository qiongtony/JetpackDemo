package com.example.navigationdemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import java.lang.Exception
import java.util.ArrayDeque

// 替换FragmentNavigator，修复了在onStop或saveStated时调用navigate不生效的问题
@Navigator.Name("fragment")
class FixFragmentNavigator(val context: Context, val manager: FragmentManager, val containerId: Int) : FragmentNavigator(
    context,
    manager,
    containerId
){
    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        /**
         *  通过反射的方式获取 mBackStack，如果拿不到，用原方法执行
         */
        val mBackStack: ArrayDeque<Int>
        try {
            val field = FragmentNavigator::class.java.getDeclaredField("mBackStack")
            field.isAccessible = true
            mBackStack = field.get(this) as ArrayDeque<Int>
        }catch (exception : Exception){
            exception.printStackTrace()
            return super.navigate(destination, args, navOptions, navigatorExtras)
        }
        var className = destination.className
        if (className[0] == '.') {
            className = context.packageName + className
        }
        val frag = instantiateFragment(
            context, manager,
            className, args
        )
        frag.arguments = args
        val ft = manager.beginTransaction()

        var enterAnim = navOptions?.enterAnim ?: -1
        var exitAnim = navOptions?.exitAnim ?: -1
        var popEnterAnim = navOptions?.popEnterAnim ?: -1
        var popExitAnim = navOptions?.popExitAnim ?: -1
        if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
            enterAnim = if (enterAnim != -1) enterAnim else 0
            exitAnim = if (exitAnim != -1) exitAnim else 0
            popEnterAnim = if (popEnterAnim != -1) popEnterAnim else 0
            popExitAnim = if (popExitAnim != -1) popExitAnim else 0
            ft.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim)
        }

        ft.replace(containerId, frag)
        ft.setPrimaryNavigationFragment(frag)

        @IdRes val destId = destination.id
        val initialNavigation = mBackStack.isEmpty()
        // TODO Build first class singleTop behavior for fragments
        val isSingleTopReplacement = (navOptions != null && !initialNavigation
                && navOptions.shouldLaunchSingleTop()
                && mBackStack.peekLast() == destId)

        val isAdded: Boolean
        isAdded = if (initialNavigation) {
            true
        } else if (isSingleTopReplacement) {
            // Single Top means we only want one instance on the back stack
            if (mBackStack.size > 1) {
                // If the Fragment to be replaced is on the FragmentManager's
                // back stack, a simple replace() isn't enough so we
                // remove it from the back stack and put our replacement
                // on the back stack in its place
                manager.popBackStack(
                    generateBackStackName(mBackStack.size, mBackStack.peekLast()),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                ft.addToBackStack(generateBackStackName(mBackStack.size, destId))
            }
            false
        } else {
            ft.addToBackStack(generateBackStackName(mBackStack.size + 1, destId))
            true
        }
        if (navigatorExtras is Extras) {
            for ((key, value) in navigatorExtras.sharedElements) {
                ft.addSharedElement(key!!, value!!)
            }
        }
        ft.setReorderingAllowed(true)
        // TODO：不使用commit，转而使用不会报错的commitAllowingStateLoss
        ft.commitAllowingStateLoss()
        // The commit succeeded, update our view of the world
        return if (isAdded) {
            mBackStack.add(destId)
            destination
        } else {
            null
        }



    }

    /*
        方式2：修改isStateSaved的值为false，try-catch异常，处理完后恢复值
    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        var navDestination : NavDestination? = null
        var originalValue = false
        var originalStateSavedValue = false
        try {
            val managerCls = Class.forName("androidx.fragment.app.FragmentManager")
            val stoppedField = managerCls.getDeclaredField("mStopped")
            val stateSavedField = managerCls.getDeclaredField("mStateSaved")
            stateSavedField.isAccessible = true;
            stoppedField.isAccessible = true
            originalValue = stoppedField.getBoolean(manager)
            originalStateSavedValue = stateSavedField.getBoolean(manager)
            Log.e(
                "WWS",
                "before set isStateSaved = ${manager.isStateSaved} stopped = ${originalValue} stateSaved = ${
                    stateSavedField.get(
                        manager
                    )
                }"
            )
            stoppedField.set(manager, false)
            stateSavedField.set(manager, false)
            Log.e("WWS", "after set isStateSaved = ${manager.isStateSaved}")
            navDestination = super.navigate(destination, args, navOptions, navigatorExtras)
        }catch (exception: Exception){
            Log.w("WWS", "exception = $exception")
            exception.printStackTrace()
        }finally {
            try {
                val cls = Class.forName("androidx.fragment.app.FragmentManager")
                val stoppedField = cls.getDeclaredField("mStopped")
                val stateSavedField = cls.getDeclaredField("mStateSaved")
                stoppedField.isAccessible = true
                stateSavedField.isAccessible = true
                stoppedField.set(manager, originalValue)
                stateSavedField.set(manager, originalStateSavedValue)
            }catch (exception: Exception){
                Log.w("WWS", "exception = $exception")
                exception.printStackTrace()
            }
        }
        return navDestination
    }*/

    /**
     * 在父类是 private的  直接定义一个方法即可
     */
    private fun generateBackStackName(backIndex: Int, destid: Int): String {
        return "$backIndex - $destid"
    }
}