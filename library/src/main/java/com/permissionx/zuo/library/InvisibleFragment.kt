package com.permissionx.zuo.library

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import java.util.*
//指定别名
typealias PermissionsCallBack = (Boolean, List<String>) -> Unit

/**
 * 创建一个不可见的Fragment，用来封装运行时的权限
 *
 * @author zuo
 * @date 2020/7/19 20:23
 */
class InvisibleFragment : Fragment() {
    //创建一个函数类型参数的返回值
    private var callback: PermissionsCallBack? = null

    //外部调用的请求权限的函数
    fun requestNow(cb: PermissionsCallBack, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    //重写权限请求的回调
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, value) in grantResults.withIndex()) {
                if (value != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            //表示object不为null的条件下，才会去执行let函数体
            callback?.let {
                it(allGranted, deniedList)
            }
        }
    }
}