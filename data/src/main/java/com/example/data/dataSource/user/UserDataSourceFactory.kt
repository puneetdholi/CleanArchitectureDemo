package com.example.data.dataSource.user

import com.example.data.dataSource.user.UserCacheDataSource
import com.example.data.dataSource.user.UserDataSource
import com.example.data.dataSource.user.UserRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSourceFactory @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource, private val userCacheDataSource: UserCacheDataSource){

    fun retrieveUserDetailsDataSource(): UserDataSource {
        // ==== Add condition for IF statement here ====
        if(true){
            return retrieveCacheDataSource()
        } else {
            return retrieveRemoteDataSource()
        }
    }

    fun retrieveRemoteDataSource(): UserRemoteDataSource {
        return userRemoteDataSource
    }

    fun retrieveCacheDataSource(): UserCacheDataSource {
        return userCacheDataSource
    }

}