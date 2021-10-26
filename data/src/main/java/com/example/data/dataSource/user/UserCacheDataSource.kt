package com.example.data.dataSource.user
import com.example.data.entity.UserEntity
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCacheDataSource @Inject constructor(): UserDataSource {
    override fun getAllUsers(): Observable<List<UserEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}