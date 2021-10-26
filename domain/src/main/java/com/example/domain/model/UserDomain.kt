package com.example.domain.model

data class UserDomain(
    val name: String,
    val email: String?,
    val phone: String?,
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipCode: String?
)