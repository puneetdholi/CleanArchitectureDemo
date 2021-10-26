package com.example.data.entity
import com.google.gson.annotations.SerializedName

data class SingleDeviceResponseEntity(
    @SerializedName("address")
    val device: UserAddressEntity?
)