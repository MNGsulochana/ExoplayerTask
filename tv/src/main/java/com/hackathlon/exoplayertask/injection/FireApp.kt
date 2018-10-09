package com.hackathlon.exoplayertask.injection

import android.app.Application
import com.hackathlon.exoplayertask.injection.component.AppComponent
import com.hackathlon.exoplayertask.injection.component.DaggerAppComponent
import com.hackathlon.exoplayertask.injection.module.ApiModule
import com.hackathlon.exoplayertask.injection.module.AppModule
import io.realm.Realm
import io.realm.RealmConfiguration

class FireApp : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).apiModule(ApiModule()).build()

        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
                .name("sample.realm")
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(configuration)
    }

    companion object {
        private lateinit var component: AppComponent
        fun component(): AppComponent {
            return component
        }
    }

}
