package com.permissionx.zuo.library

import androidx.fragment.app.FragmentActivity

/**
 * 指定对外调用的单例类
 *
 * @author zuo
 * @date 2020/7/19 21:10
 */
object PermissionX {
    private const val TAG = "PermissionX"

    fun request(
        activity: FragmentActivity,
        vararg permissions: String,
        callBack: PermissionsCallBack
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        //通过 Tag 判断当前 Activity 是否已经添加了 InvisibleFragment
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            //立即添加当前 invisibleFragment
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        //动态权限请求，*permissions 表示将一个数组转变为可变长度参数
        fragment.requestNow(callBack, *permissions)
    }
}