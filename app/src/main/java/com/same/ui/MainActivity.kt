package com.same.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.timecat.identity.readonly.RouterHub
import com.xiaojinzi.component.impl.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.addView(createButton("登录", RouterHub.LOGIN_LoginActivity))
        linearLayout.addView(createButton("添加插件", RouterHub.USER_AddPluginAppActivity))
        linearLayout.addView(createButton("添加动态", RouterHub.USER_AddMomentActivity))
        linearLayout.addView(createButton("背包", RouterHub.USER_BagActivity))
        setContentView(linearLayout)
    }

    private fun createButton(name: String, path: String): Button {
        val button = createButton(name)
        button.setOnClickListener { go(path) }
        return button
    }

    private fun createButton(name: String, onClickListener: View.OnClickListener): Button {
        val button = createButton(name)
        button.setOnClickListener(onClickListener)
        return button
    }

    private fun createButton(name: String): Button {
        val button = Button(this)
        button.text = name
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        button.layoutParams = layoutParams
        button.gravity = Gravity.CENTER
        return button
    }

    private fun go(path: String) {
        Router.with().hostAndPath(path)
            .forward(object : Callback {
                override fun onSuccess(result: RouterResult) {}
                override fun onEvent(successResult: RouterResult?, errorResult: RouterErrorResult?) {}
                override fun onCancel(originalRequest: RouterRequest?) {}
                override fun onError(errorResult: RouterErrorResult) {
                    Log.e("ui", errorResult.error.toString())
                }
            })
    }
}