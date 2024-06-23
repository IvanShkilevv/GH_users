package com.example.ghusers

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.ghusers.di.AppComponent
import com.example.ghusers.di.AppModule
import com.example.ghusers.di.DaggerAppComponent

class GhUsersApp : Application(), Application.ActivityLifecycleCallbacks {

    var appComponent: AppComponent? = null
        private set

    companion object {
        var instance: GhUsersApp? = null
            private set
    }

    @SuppressLint("ThrowableNotAtBeginning")
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this, this)).build()
        instance = this
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        return
    }

    override fun onActivityStarted(activity: Activity) {
        return
    }

    override fun onActivityResumed(activity: Activity) {
        return
    }

    override fun onActivityPaused(activity: Activity) {
        return
    }

    override fun onActivityStopped(activity: Activity) {
        return
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        return
    }

    override fun onActivityDestroyed(activity: Activity) {
        return
    }

}