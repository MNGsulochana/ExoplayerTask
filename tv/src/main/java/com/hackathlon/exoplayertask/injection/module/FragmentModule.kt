package com.hackathlon.exoplayertask.injection.module


import android.content.Context
import com.hackathlon.exoplayertask.injection.annotate.FragmentScope
import com.hackathlon.exoplayertask.injection.annotate.ScopeContext
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(val context: Context) {

    @FragmentScope
    @ScopeContext
    @Provides
    fun providesContext(): Context {
        return context
    }

    /* @FragmentScope
     @Provides
  fun providesFragmentContext() :Context
     {
         return context
     }*/

}
