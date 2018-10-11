package com.hackathlon.exoplayertask.db

import com.hackathlon.exoplayertask.api.response.DataModel
import io.realm.Realm
import io.realm.kotlin.where

class DatabaseManager(val realm: Realm) {

    companion object {
        internal val OBJECT_ID = "id"
        internal var OBJECT_NAME = "title_contn"
    }

    fun saveDataToRealm(d: List<DataModel>) {
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
        return realm.where<DataModel>().equalTo("id", id).findFirst()?.playedDuration ?: 0
    }

    fun saveSeekPosition(id: String, position: Long) {

        realm.executeTransaction {
            val data = it.where<DataModel>().equalTo("id", id).findFirst()
            if (data != null)
                data.playedDuration = position
        }
    }


    /* fun getconinutyList(): ArrayList<Playercontinuity> {
         val f = realm!!.isEmpty

         val contlist = getconitnutyList()
         Log.d("dtaabse_contnrrealmsize", "" + contlist.size)
         val list = ArrayList<Playercontinuity>()

         list.addAll(realm.copyFromRealm(contlist))

         Log.d("dtaabse_contntysize", "" + list.size)
         return list
     }


     fun saveplayerconitnuityList(c: List<Playercontinuity>) {
         Log.d("dtaabsetag", "" + c.size)
         realm!!.refresh()
         realm.beginTransaction()
         for (coni in c) {
             realm.copyToRealm(coni)
         }
         realm.commitTransaction()
     }

     fun deleteData() {
         if (realm != null) {
             realm.beginTransaction()
             realm.delete(DataModel::class.java)
             realm.commitTransaction()
         }
     }

     fun deleteSingleItem(x: Int): Boolean {
         var tagvalue = false
         if (realm != null) {
             realm.beginTransaction()
             for (dataList in getModellistObject(x)) {
                 dataList.deleteFromRealm()
                 tagvalue = true
             }
             realm.commitTransaction()
         }
         return tagvalue
     }

     fun deleteContinutyList(x: String) {
         Log.d("dtaabsetag", "+c.size()")
         if (realm != null) {
             realm.beginTransaction()
             for (playercontn in getPlaycontnlist(x)) {
                 playercontn.deleteFromRealm()
             }
             realm.commitTransaction()
         }
     }

     internal fun getPlaycontnlist(x: String): RealmResults<Playercontinuity> {
         return realm.where(Playercontinuity::class.java).equalTo(OBJECT_NAME, x).findAll()
     }

     internal fun getModellistObject(objectid: Int): RealmResults<DataModel> {
         Log.d("dtaabsetag", "" + objectid)

         return realm.where(DataModel::class.java).equalTo(OBJECT_ID, "" + objectid).findAll()
     }

     internal fun getconitnutyList(): RealmResults<Playercontinuity> {
         return realm.where(Playercontinuity::class.java).findAll()
     }

     fun hasData(): Boolean {
         return !realm.isEmpty
     }*/
}
