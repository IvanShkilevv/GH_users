package com.example.ghusers.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ghusers.GhUsersApp
import com.github.terrakok.cicerone.Screen
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun getRootView(inflater: LayoutInflater, container: ViewGroup?): View

    protected fun navigateTo(screen: Screen) {
        (activity as? BaseActivity)?.router?.navigateTo(screen)
    }

    protected fun back() {
        (activity as? BaseActivity)?.router?.exit()
    }

    override fun onAttach(context: Context) {
        GhUsersApp.instance?.appComponent?.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getRootView(inflater = inflater, container = container)
    }

}


