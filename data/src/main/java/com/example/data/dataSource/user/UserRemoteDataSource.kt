package com.example.data.dataSource.user

import com.example.data.entity.UserEntity
import com.example.data.network.remote.user.UserRemote
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(
    private val userRemote: UserRemote
) :
    UserDataSource {
    override fun getAllUsers(): Observable<List<UserEntity>> {
        return userRemote.getAllUsers()
    }
}