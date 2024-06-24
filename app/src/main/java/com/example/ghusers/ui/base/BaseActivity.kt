package com.example.ghusers.ui.base

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ghusers.GhUsersApp
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

abstract class BaseActivity : FragmentActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GhUsersApp.instance?.appComponent?.inject(this)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(AppNavigator(this, getFragmentContainerId()))
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    abstract fun getFragmentContainerId(): Int

}