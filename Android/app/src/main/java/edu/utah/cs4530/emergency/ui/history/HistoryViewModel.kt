package edu.utah.cs4530.emergency.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.utah.cs4530.emergency.dao.AlertHistoryDAO

class HistoryViewModel: ViewModel()
{
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