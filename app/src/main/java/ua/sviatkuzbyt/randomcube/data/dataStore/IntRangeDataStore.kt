package ua.sviatkuzbyt.randomcube.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import ua.sviatkuzbyt.randomcube.ui.main.intRangeDataStore

class IntRangeDataStore(private val context: Context) {
    private val startNumberKey = intPreferencesKey("startNumber")
    private val endNumberKey = intPreferencesKey("endNumber")

    suspend fun getStartNumber(): Int{
        return context.intRangeDataStore.data.map {
            it[startNumberKey] ?: 1
        }.first()
    }

    suspend fun getEndNumber(): Int{
        return context.intRangeDataStore.data.map {
            it[endNumberKey] ?: 100
        }.first()
    }

    suspend fun setStartNumber(value: Int){
        context.intRangeDataStore.edit {
            it[startNumberKey] = value
        }
    }

    suspend fun setEndNumber(value: Int){
        context.intRangeDataStore.edit {
            it[endNumberKey] = value
        }
    }
}