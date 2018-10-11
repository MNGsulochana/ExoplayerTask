package com.hackathlon.exoplayertask.injection.module;

import android.support.annotation.NonNull;

import com.hackathlon.exoplayertask.db.DatabaseManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class CommonModule {

  @Provides
  @NonNull
  @Singleton
  DatabaseManager providesDatabaseManager() {
    return new DatabaseManager(Realm.getDefaultInstance());
  }
}
