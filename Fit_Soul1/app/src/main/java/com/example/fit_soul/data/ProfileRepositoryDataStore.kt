
//package com.example.fit_soul.data
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//import java.util.prefs.Preferences
//
//const val PROFILE_DATASTORE ="profile_datastore"
//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PROFILE_DATASTORE)
//class ProfileRepositoryDataStore (private val context: Context) : ProfileRepository  {
//    companion object {
//        val NAME = stringPreferencesKey("NAME")
//        val COUNTER = intPreferencesKey("COUNTER")
//    }
//
//    /** Update the values in the DataStore. */
//    override suspend fun saveProfile(profileData: ProfileData) {
//        context.dataStore.edit {
//            it[NAME] = profileData.name
//            it[COUNTER] = profileData.counter
//        }
//    }
//    /** Get the data in the DataStore as a flow.  Since the store may have never
//     *     been used yet, handle the null case with default values. */
//    override fun getProfile(): Flow<ProfileData> = context.dataStore.data.map {
//        ProfileData(
//            name = it[NAME] ?: "",
//            counter = it[COUNTER] ?: 0
//        )
//    }
//
//    override suspend fun clear() {
//        context.dataStore.edit {
//            it.clear()
//        }
//    }
//
//}
