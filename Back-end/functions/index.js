const functions = require('firebase-functions');
const admin = require('firebase-admin');
const { randomBytes } = require('crypto');
admin.initializeApp();

// var serviceAccount = require("/emergency-c2c80-57b0b6f58df3.json");
// admin.initializeApp({
//     credential: admin.credential.cert(serviceAccount),
//     databaseURL: "https://emergency-c2c80.firebaseio.com"
// });

exports.sendEmergencyMessage = functions.https.onCall((data, context) => {

    const uid = context.auth.uid
    const time = new Date().toISOString()
    const latitudeString = data.latitude
    const longitudeString = data.longitude
    const latitude = parseFloat(latitudeString)
    const longitude = parseFloat(longitudeString)

    return admin.database().ref(`users/${uid}`).once("value", function(data) {
        const userDAO = data.val()
        const contactList = userDAO.contactList
        const emergencyMessage = userDAO.emergencyMessage
        const uuid = Math.random().toString(36).slice(2) + randomBytes(8).toString('hex') + new Date().getTime()

        admin.database().ref(`users/${uid}/alertHistories/${uuid}`).set({
            time: time,
            latitude: latitude,
            longitude: longitude
        });

        contactList.forEach(function(item) {
            admin.database().ref(`users`).orderByChild("phoneNumber").equalTo(`${item.phoneNumber}`).limitToFirst(1).once("value", function(data) {

                //When fail to find user, record failing to send push notification.
                if(data === null)
                {
                    admin.database().ref(`users/${uid}/alertHistories/${uuid}/contactedUserPhoneNumber`).push({
                        name: "Unknown", 
                        phoneNumber: targetPhoneNumber,
                        method: 0,
                        result: 1
                    })
                }
                else
                {
                    data.forEach(function(item) {
                        const targetUid = item.key
                        const targetName = item.val().name
                        const targetImageUrl = item.val().imageUrl
                        const targetPhoneNumber = item.val().phoneNumber
                        const targetFcmToken = item.val().fcmToken
    
                        //Record recevied history
                        admin.database().ref(`users/${targetUid}/alertReceivedHistories/${uuid}`).set({
                            time: time,
                            latitude: latitude,
                            longitude: longitude,
                            contactedUserPhoneNumber: {
                                name: userDAO.name, 
                                phoneNumber: userDAO.phoneNumber,
                                imageUrl: userDAO.imageUrl,
                                uid: uid,
                                method: 0
                            }
                        });
    
                        //Record sent historty
                        admin.database().ref(`users/${uid}/alertHistories/${uuid}/contactedUserPhoneNumber`).push({
                            name: targetName, 
                            phoneNumber: targetPhoneNumber,
                            imageUrl: targetImageUrl,
                            uid: targetUid,
                            method: 0,
                            result: 0
                        })
    
                        //Send push notification
                        var message = {
                            notification: {
                                title: `!!SOS!! - ${userDAO.name}`,
                                body: emergencyMessage
                            },
                            data: {
                              latitude: latitudeString.toString(),
                              longitude: longitudeString.toString(),
                              emergencyMessage: emergencyMessage,
                              name: userDAO.name,
                              phoneNumber: userDAO.phoneNumber,
                              imageUrl: userDAO.imageUrl
                            },
                            android: {
                                priority: "high"
                            },
                            token: targetFcmToken
                          };
    
                        admin.messaging().send(message)
                            .then((response) => {
                                console.log('Successfully sent message:', response);
                                return
                            })
                            .catch((error) => {
                                console.log('Error sending message:', error)
                                return
                            })
                    });
                }
            });
        });
    });
});