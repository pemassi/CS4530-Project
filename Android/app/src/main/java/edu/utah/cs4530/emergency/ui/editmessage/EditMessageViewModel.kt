package edu.utah.cs4530.emergency.ui.editmessage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.utah.cs4530.emergency.const.DatabaseConst
import edu.utah.cs4530.emergency.dao.EmergencyMessageDAO
import edu.utah.cs4530.emergency.dao.UserDAO
import edu.utah.cs4530.emergency.extension.getLogger
import edu.utah.cs4530.emergency.repository.UserRepository

class EditMessageViewModel : ViewModel() {
    private val logger by getLogger()

    private val uid = UserRepository.getFirebaseUser() ?.uid?: "test"
    private val database = Firebase.database.reference
        .child(DatabaseConst.DOCUMENT_USERS)
        .child(uid)
        .child(DatabaseConst.EMERGENCY_MESSAGE)

    //HOW DO I GRAB MY INPUT STRING?
    private val _emergencyMessage = MutableLiveData<String>().apply {
        value = "This is an emergency message that will be sent to all of your contacts"
    }
    val text: LiveData<String> = _emergencyMessage

    private val emergencyMessageTextEventListener = object: ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            logger.debug("Emergency Message has been updated [$dataSnapshot]")

            _emergencyMessage.value = dataSnapshot.children.toString()
        }

        override fun onCancelled(error: DatabaseError) {
            logger.error("Failed to get emergency message", error.toException())
            throw error.toException()
        }
    }

    init{
        database.addValueEventListener(emergencyMessageTextEventListener)
    }

    fun saveEmergencyMessage(newMessage: EmergencyMessageDAO) {
        database.setValue(_emergencyMessage.value!!.apply{this.add(newMessage)})
    }

    fun getEmergencyMessage(){
        return _emergencyMessage.value
    }

    fun getEmergencyMessageLength(): Int{
        return _emergencyMessage.value!!.size
    }
}
