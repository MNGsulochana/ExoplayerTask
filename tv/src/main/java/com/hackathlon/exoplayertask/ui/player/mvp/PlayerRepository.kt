package com.hackathlon.exoplayertask.ui.player.mvp

import com.hackathlon.exoplayertask.api.response.ApiHandler
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.base.RepositoryImpl
import com.hackathlon.exoplayertask.db.DatabaseManager

class PlayerRepository(private val apiHandler: ApiHandler, private val databaseManager: DatabaseManager) : RepositoryImpl<PlayerContract.Presenter>(), PlayerContract.Repositor {

    override fun getData(id: String): DataModel? {
        return databaseManager.getUnManagedData(id)
    }


    override fun getDataList(): List<DataModel> {
        return databaseManager.getUnManagedDataList()
    }

    override fun saveSeekTime(id: String, position: Long) {
        databaseManager.saveSeekPosition(id, position)
    }

    override fun getSeekTime(id: String): Long {
        return databaseManager.getSeekPosition(id)
    }

    override fun cancel() {


    }
}