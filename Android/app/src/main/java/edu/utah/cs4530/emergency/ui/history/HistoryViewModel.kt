package edu.utah.cs4530.emergency.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.utah.cs4530.emergency.const.DatabaseConst
import edu.utah.cs4530.emergency.dao.AlertHistoryDAO
import edu.utah.cs4530.emergency.extension.getLogger
import edu.utah.cs4530.emergency.repository.UserRepository

class HistoryViewModel: ViewModel()
{
    private val logger by getLogger()

    private val uid = UserRepository.getFirebaseUser()?.uid ?: "test" //throw Exception("User is not logged-in")
    private val database = Firebase.database.reference
        .child(DatabaseConst.DOCUMENT_USERS)
        .child(uid)
        .child(DatabaseConst.ITEM_HISTORIES)


    private val _histories = MutableLiveData<ArrayList<AlertHistoryDAO>>()
    val histories: LiveData<ArrayList<AlertHistoryDAO>> = _histories

    init
    {
        //Get History data from local then get update from server.
        _histories.apply {
            value = ArrayList()
        }
    }
}