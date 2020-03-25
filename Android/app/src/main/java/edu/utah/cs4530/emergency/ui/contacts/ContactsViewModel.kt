package edu.utah.cs4530.emergency.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.utah.cs4530.emergency.dao.ContactDAO
import edu.utah.cs4530.emergency.dao.UserDAO
import edu.utah.cs4530.emergency.repository.UserRepository

class ContactsViewModel : ViewModel() {
    private val _contactList = MutableLiveData<ArrayList<ContactDAO>>()
    val contactList: LiveData<ArrayList<ContactDAO>> = _contactList

    init {
        UserRepository.getFirebaseUser()?.let {
            val uid = it.uid

            UserRepository.getUserOnce(uid) { result: Boolean, userDAO: UserDAO?, exception: Exception? ->
                if (result)
                {
                    userDAO?.contactList?.let {
                        _contactList.apply { value!! + it }
                    }
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
        _contactList.apply { value?.add(newContactDAO) }
    }
}