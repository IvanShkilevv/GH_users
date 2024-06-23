package com.example.ghusers.di

import com.example.ghusers.ui.base.BaseActivity
import com.example.ghusers.ui.base.BaseFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NavigationModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)
}