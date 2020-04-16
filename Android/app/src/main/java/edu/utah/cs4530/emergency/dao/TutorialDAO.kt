package edu.utah.cs4530.emergency.dao

import android.net.Uri
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class TutorialDAO (
    val title: String,
    val description: String,
    val icon: Int
)