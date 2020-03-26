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

            //Map object to ArrayList of ContactDAO.
            contactList.value = ArrayList(dataSnapshot.children.map {it.getValue(ContactDAO::class.java) ?: throw Exception("Fail to convert object to ContactDAO")})
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

    /**
     * Add new contact
     *
     * @param pos If pos is -1, will be added at the end.
     */
    fun addContactList(newContactDAO: ContactDAO, pos: Int = -1)
    {
        database.setValue(contactList.value!!.apply{
            if(pos == -1)
                this.add(newContactDAO)
            else
                this.add(pos, newContactDAO)
        })
    }

    fun getContactList(pos: Int): ContactDAO
    {
        return contactList.value!![pos]
    }

    fun sizeContactList(): Int
    {
        return contactList.value!!.size
    }

    fun removeContactList(pos: Int)
    {
        database.setValue(contactList.value!!.apply { this.removeAt(pos) })
    }

    override fun onCleared() {
        database.removeEventListener(userDaoValueEventListener)
    }
}