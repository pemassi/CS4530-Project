package edu.utah.cs4530.emergency.dao

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserDAO (
    val name: String? = null,
    val phoneNumber: String? = null,
    val imageUrl: String? = null,
    val fcmToken: String? = null,
    val contactList: ArrayList<ContactDAO>? = null,
    val alertHistories: ArrayList<AlertHistoryDAO>? = null
)