package edu.utah.cs4530.emergency.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.telephony.TelephonyManager
import com.gun0912.tedpermission.TedPermission
import edu.utah.cs4530.emergency.EmergencyApplication


object DeviceInfo
{
    val phoneNumber: String?
        @SuppressLint("MissingPermission")
        get() {
            val context = EmergencyApplication.context
            val tMgr = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            if(!TedPermission.isGranted(context,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_PHONE_NUMBERS))
                return null
            else
                return tMgr.line1Number
        }
}