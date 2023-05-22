package com.example.testing

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class StoreUserEmail(private val context: Context) {

    // to make sure there's only one instance
    companion object {
        private val Context.dataStoree: DataStore<Preferences> by preferencesDataStore("userEmail")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    //get the saved email
    val getEmail: Flow<String?> = context.dataStoree.data
        .map { preferences ->
            preferences[USER_EMAIL_KEY] ?: ""
        }

    //save email into datastore
    suspend fun saveEmail(name: String) {
        context.dataStoree.edit { preferences ->
            preferences[USER_EMAIL_KEY] = name
        }
    }
    suspend fun clearDataStore() = context.dataStoree.edit {
        it.clear()
    }





//    companion object {
//        val EMAIL = stringPreferencesKey("EMAIL")
//        val MOBILE_NUMBER = stringPreferencesKey("MOBILE_NUMBER")
//        val BUSINESS_NAME = stringPreferencesKey("BUSINESS_NAME")
//        val BUSINESS_ADDRESS = stringPreferencesKey("BUSINESS_ADDRESS")
//        val BUSINESS_WEBSITE = stringPreferencesKey("BUSINESS_WEBSITE")
//        val BUSINESS_CATEGORY = stringPreferencesKey("BUSINESS_CATEGORY")
//        val BUSINESS_LOCATION = stringPreferencesKey("BUSINESS_LOCATION")
//    }
//
//    suspend fun saveToDataStore(merchantDetail: MerchantDetail) {
//        context.dataStore.edit {
//            it[EMAIL] = merchantDetail.emailAddress
//            it[MOBILE_NUMBER] = merchantDetail.mobileNumber
//            it[BUSINESS_NAME] = merchantDetail.businessName
//            it[BUSINESS_ADDRESS] = merchantDetail.businessAddress
//            it[BUSINESS_WEBSITE] = merchantDetail.businessWebsite
//            it[BUSINESS_CATEGORY] = merchantDetail.businessCategory
//            it[BUSINESS_LOCATION] = merchantDetail.businessLocation
//        }
//    }
//
//    fun getFromDataStore() = context.dataStore.data.map {
//        MerchantDetail(
//            emailAddress = it[EMAIL]?:"",
//            mobileNumber = it[MOBILE_NUMBER]?:"",
//            businessName = it[BUSINESS_NAME]?:""
//        )
//    }
//
//    suspend fun clearDataStore() = context.dataStore.edit {
//        it.clear()
//    }

}