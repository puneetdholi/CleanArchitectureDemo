package com.example.data.entity.mapper

import com.example.data.entity.UserEntity
import com.example.domain.model.UserDomain
import javax.inject.Inject

// ==== Check import for User in the domain --> model ====


class UserDataMapper @Inject constructor() {
    fun mapEntityToDomain(userEntity: UserEntity): UserDomain {
        return UserDomain(
            name = userEntity.name!!,
            phone = userEntity.phone,
            email = userEntity.email,
            city = userEntity.address?.city,
            street = userEntity.address?.street,
            suite = userEntity.address?.suite,
            zipCode = userEntity.address?.zipCode
        )
    }

    fun mapEntityListToDomainList(deviceEntityList: List<UserEntity>): List<UserDomain> {
        return deviceEntityList.map { deviceEntity ->
            mapEntityToDomain(deviceEntity)
        }
    }
}