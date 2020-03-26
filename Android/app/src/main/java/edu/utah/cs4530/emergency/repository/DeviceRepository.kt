package edu.utah.cs4530.emergency.repository

import edu.utah.cs4530.emergency.util.WSharedPref

object DeviceRepository
{
    private const val KEY_FCM_TOKEN = "FCM_TOKEN"
    var fcmToken: String?
        get() {
            return WSharedPref.getString(KEY_FCM_TOKEN, null)
        }
        set(newValue) {
            WSharedPref.setString(KEY_FCM_TOKEN, newValue)
        }


}