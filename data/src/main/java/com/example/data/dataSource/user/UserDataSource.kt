package com.example.data.dataSource.user
import com.example.data.entity.UserEntity
import io.reactivex.Observable
import java.util.*

interface UserDataSource {
    fun getAllUsers(): Observable<List<UserEntity>>
}