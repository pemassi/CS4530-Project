package edu.utah.cs4530.emergency.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.utah.cs4530.emergency.dao.ContactDAO

class ContactsViewModel : ViewModel() {
    private val _contectList = MutableLiveData<ArrayList<ContactDAO>>()
    val contectList: LiveData<ArrayList<ContactDAO>> = _contectList


}