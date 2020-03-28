package edu.utah.cs4530.emergency.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import edu.utah.cs4530.emergency.R
import edu.utah.cs4530.emergency.component.picasso.RoundedTransformation
import edu.utah.cs4530.emergency.extension.getLogger
import edu.utah.cs4530.emergency.repository.DeviceRepository
import edu.utah.cs4530.emergency.repository.UserRepository
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity() {

    private val logger by getLogger()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel: MainViewModel

    private var isFcmTokenUpdated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_contacts,
                R.id.nav_history,
                R.id.nav_received_history
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        viewModel.userDao.observe(this, Observer {
            it?.let {
                val headerView = navView.getHeaderView(0)
                headerView.tv_name.text = it.name
                headerView.tv_email.text = it.phoneNumber
                Picasso.get().load(it.imageUrl).transform(RoundedTransformation()).into(headerView.iv_profile)

                if(!isFcmTokenUpdated){
                    it.fcmToken = DeviceRepository.fcmToken
                    UserRepository.setUser(FirebaseAuth.getInstance().uid!!, it)
                    isFcmTokenUpdated = true
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
