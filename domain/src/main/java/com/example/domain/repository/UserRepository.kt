package com.example.domain.repository

import com.example.domain.model.UserDomain
import io.reactivex.Observable

interface UserRepository {
    fun getAllUsers(): Observable<List<UserDomain>>
}