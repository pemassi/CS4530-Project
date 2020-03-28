package edu.utah.cs4530.emergency.service

import android.app.Notification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tcodevice.card.tada.consts.NotificationConst
import edu.utah.cs4530.emergency.R
import edu.utah.cs4530.emergency.activity.SplashActivity
import edu.utah.cs4530.emergency.extension.getLogger
import edu.utah.cs4530.emergency.repository.DeviceRepository
import edu.utah.cs4530.emergency.util.EzNotification


class FirebaseMessagingService : FirebaseMessagingService() {

    private val logger by getLogger()

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
       logger.info("From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        remoteMessage.data.isNotEmpty().let {
            logger.debug("Message data payload: " + remoteMessage.data)
            val latitude = remoteMessage.data["latitude"]
            val longitude = remoteMessage.data["longitude"]
            val emergencyMessage = remoteMessage.data["emergencyMessage"]
            val name = remoteMessage.data["name"]
            val phoneNumber = remoteMessage.data["phoneNumber"]
            val imageUrl = remoteMessage.data["imageUrl"]
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            logger.debug("Message Notification Body: ${it.body}")

            EzNotification(applicationContext, NotificationConst.EMERGENCY).apply {
                setIcon(R.drawable.ic_announcement_black_24dp)
                setTitle(it.title!!)
                setMessage(it.body!!)
                setVisibility(Notification.VISIBILITY_PUBLIC)
                setIntent(SplashActivity::class.java)
                setNotiToUser(true)
                setWakeUp()
            }.show()
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        logger.info("Refreshed token: $token")
        DeviceRepository.fcmToken = token
    }

}