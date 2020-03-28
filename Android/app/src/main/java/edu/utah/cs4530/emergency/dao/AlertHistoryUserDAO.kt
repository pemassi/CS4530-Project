package edu.utah.cs4530.emergency.dao

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class AlertHistoryUserDAO(
    val name: String = "",
    val phoneNumber: String = "",
    val imageUrl: String? = null,
    val uid: String? = null,
    val method: Int = 0,
    val result: Int = 0
)