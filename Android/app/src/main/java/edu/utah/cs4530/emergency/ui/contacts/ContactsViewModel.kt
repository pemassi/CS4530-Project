package edu.utah.cs4530.emergency.ui.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.utah.cs4530.emergency.const.DatabaseConst
import edu.utah.cs4530.emergency.dao.ContactDAO
import edu.utah.cs4530.emergency.extension.getLogger
import edu.utah.cs4530.emergency.repository.UserRepository

class ContactsViewModel : ViewModel() {
    private val logger by getLogger()

    private val uid = UserRepository.getFirebaseUser()?.uid ?: "test" //throw Exception("User is not logged-in")
    private val database = Firebase.database.reference
        .child(DatabaseConst.DOCUMENT_USERS)
        .child(uid)
        .child(DatabaseConst.ITEM_CONTACT_LIST)

    val contactList = MutableLiveData<ArrayList<ContactDAO>>(ArrayList())

    private val userDaoValueEventListener = object: ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            logger.debug("Data updated [$dataSnapshot]")
            contactList.value = ArrayList(dataSnapshot.children.map {it.getValue(ContactDAO::class.java)!!})
        }

        override fun onCancelled(error: DatabaseError) {
            logger.error("Fail to get user data object.", error.toException())
            throw error.toException()
        }
    }

    init
    {
        database.addValueEventListener(userDaoValueEventListener)
    }

    fun addContactList(newContactDAO: ContactDAO)
    {
        database.setValue(contactList.value!!.apply{this.add(newContactDAO)})
    }

    fun removeContactList(pos: Int)
    {
        database.setValue(contactList.value!!.apply { this.removeAt(pos) })
    }

    override fun onCleared() {
        database.removeEventListener(userDaoValueEventListener)
    }
}