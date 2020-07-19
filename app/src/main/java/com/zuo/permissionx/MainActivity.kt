package com.zuo.permissionx

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.permissionx.zuo.library.PermissionX

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        call_tel.setOnClickListener {
            PermissionX.request(
                this,
                Manifest.permission.CALL_PHONE
            ) { allGranted: Boolean, deniedList: List<String> ->
                if (allGranted) {
                    callTel()
                } else {
                    Toast.makeText(this, "您拒绝了以下权限：\n${deniedList}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //拨打电话
    private fun callTel() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:10086")
        startActivity(intent)
    }
}