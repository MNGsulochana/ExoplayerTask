package com.hackathlon.exoplayertask.db

import android.util.Log
import com.hackathlon.exoplayertask.api.response.DataModel
import io.realm.Realm
import io.realm.kotlin.where

class DatabaseManager(val realm: Realm) {


    fun saveDataToRealm(d: List<DataModel>) {

        if (getUnManagedDataList().isEmpty())
            realm.executeTransaction {
                it.copyToRealmOrUpdate(d)
            }
    }

    fun getDataList(): List<DataModel> {
        return realm.where<DataModel>().findAll()
    }

    fun getUnManagedDataList(): List<DataModel> {
        return realm.copyFromRealm(getDataList())
    }


    fun getContinuityList(): List<DataModel> {

        return realm.where<DataModel>().greaterThan("playedDuration", 0).findAll()
    }

    fun getData(id: String): DataModel? {
        return realm.where<DataModel>().equalTo("id", id).findFirst()
    }

    fun getUnManagedData(id: String): DataModel? {
        return getData(id)?.let {
            realm.copyFromRealm(it)
        }
    }


    fun getSeekPosition(id: String): Long {
        val duration: Long = realm.where<DataModel>().equalTo("id", id).findFirst()?.playedDuration
                ?: 0

        Log.d("saveseek ", "$duration..... $id")
        return duration
    }

    fun saveSeekPosition(id: String, position: Long) {
        Log.d("saveseek ", "$position..... $id")
        realm.executeTransaction {
            val data = it.where<DataModel>().equalTo("id", id).findFirst()
            if (data != null)
                data.playedDuration = position
            Log.d("getduration", "" + data?.playedDuration)
        }
    }


}
