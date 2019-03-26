package com.hackathlon.exoplayertask.injection.component

import com.hackathlon.exoplayertask.injection.annotate.FragmentScope
import com.hackathlon.exoplayertask.injection.module.FragmentModule
import com.hackathlon.exoplayertask.injection.module.FragmentPresenterModule
import com.hackathlon.exoplayertask.ui.home.homefragment.HomeFragment
import com.hackathlon.exoplayertask.ui.leanback.LeanBackFragment
import com.hackathlon.exoplayertask.ui.player.playlistfragment.PlayListRowFragment

import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = arrayOf(FragmentModule::class, FragmentPresenterModule::class))
interface FragmentComponent {
    fun inject(homeFragment: HomeFragment)
    fun inject(playListRowFragment: PlayListRowFragment)
    fun inject(leanBackFragment: LeanBackFragment)
}
