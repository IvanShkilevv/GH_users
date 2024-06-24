package com.example.ghusers.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.ghusers.R
import com.example.ghusers.navigation.Screens
import com.example.ghusers.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        router.newRootScreen(Screens.users())
    }

    override fun getFragmentContainerId(): Int {
        return R.id.fragment_container
    }

}