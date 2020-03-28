package edu.utah.cs4530.emergency.dao

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class EmergencyMessageDAO (
    var emergencyText: String? = null
)