package edu.utah.cs4530.emergency.ui.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.utah.cs4530.emergency.dao.ContactDAO
import edu.utah.cs4530.emergency.dao.UserDAO
import edu.utah.cs4530.emergency.repository.UserRepository

class ContactsViewModel : ViewModel() {
    private val _contactList = ArrayList<ContactDAO>()
    val contactList = MutableLiveData<ArrayList<ContactDAO>>()

    init {
        UserRepository.getFirebaseUser()?.let {
            val uid = it.uid

            UserRepository.getUserOnce(uid) { result: Boolean, userDAO: UserDAO?, exception: Exception? ->
                if (result)
                {
                    userDAO?.contactList?.forEach { addContactList(it) }
                }
                else
                {
                    TODO("ALERT TO USER")
                }
            }
        }
    }

    fun addContactList(newContactDAO: ContactDAO)
    {
        _contactList.add(newContactDAO)
        contactList.value = _contactList
    }
}