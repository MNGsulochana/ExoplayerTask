package com.hackathlon.exoplayertask.injection.module

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.hackathlon.exoplayertask.injection.annotate.ActivityScope
import com.hackathlon.exoplayertask.injection.annotate.ScopeContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: Activity) {

    @ActivityScope
    @ScopeContext
    @Provides
    internal fun providesContext(): Context {
        return activity
    }

}
