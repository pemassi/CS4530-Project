package edu.utah.cs4530.emergency

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp

class EmergencyApplication: MultiDexApplication()
{
    override fun onCreate() {
        super.onCreate()

        //Use vector image
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        //Firebase initialize
        FirebaseApp.initializeApp(this)

        //Set global context
        context = applicationContext
    }

    companion object
    {
        lateinit var context: Context
    }
}