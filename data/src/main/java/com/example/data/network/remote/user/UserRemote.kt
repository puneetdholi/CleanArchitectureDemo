package com.example.data.network.remote.user

import com.example.data.entity.UserEntity
import io.reactivex.Observable

interface UserRemote {
    fun getAllUsers(): Observable<List<UserEntity>>
}