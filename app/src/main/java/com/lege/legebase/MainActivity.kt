package com.lege.legebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lege.android.base.PreferencesManager
import com.lege.android.base.ui.BaseActivity
import com.lege.android.base.ui.DialogUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        PreferencesManager.init(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        root.setOnClickListener {
//            DialogUtil.showConfirmDialogForChildrenTomato(this,title="测试dialog",content = "asaaaaaa",onOk = {},onCancel = {})
            DialogUtil.showCustomDialog(this,title="测试dialog",content = "asaaaaaa",onOk = {},onCancel = {})
        }

    }
}
