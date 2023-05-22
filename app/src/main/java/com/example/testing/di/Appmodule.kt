package com.example.testing.di

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.testing.data.apicall.UserApi
import com.taskmo.supermanager.data.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Appmodule {
    @Provides
    fun providesAuthApi(remoteDataSource: RemoteDataSource): UserApi {
        return remoteDataSource.buildApi(UserApi::class.java)
    }
    @Module
    @InstallIn(ActivityComponent::class)
    object NavigationModule {
        @Composable
        fun provideNavHostController() = rememberNavController()
    }

}