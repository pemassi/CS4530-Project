package edu.utah.cs4530.emergency.dao

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserDAO (
    var name: String? = null,
    var phoneNumber: String? = null,
    var emergencyMessage: EmergencyMessageDAO? = null,
    var imageUrl: String? = null,
    var fcmToken: String? = null,
    var contactList: ArrayList<ContactDAO> = ArrayList(),
    var alertHistories: ArrayList<AlertHistoryDAO> = ArrayList()
)