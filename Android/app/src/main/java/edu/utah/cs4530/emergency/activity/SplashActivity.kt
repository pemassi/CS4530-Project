package edu.utah.cs4530.emergency.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import edu.utah.cs4530.emergency.R

class SplashActivity: AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Check user is logged in
        if(FirebaseAuth.getInstance().currentUser == null)
        {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        else
        {
            startActivity(Intent(this, MainActivity::class.java))
        }

        finish()
    }
}