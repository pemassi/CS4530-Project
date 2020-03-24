package edu.utah.cs4530.emergency.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.telcuon.appcard.restful.extension.Date
import edu.utah.cs4530.emergency.dao.AlertHistoryDAO
import edu.utah.cs4530.emergency.dao.UserDAO

class HistoryViewModel: ViewModel()
{
    private val _histories = MutableLiveData<List<AlertHistoryDAO>>()
    val histories: LiveData<List<AlertHistoryDAO>> = _histories

    init
    {
        //Get History data from local then get update from server.
        _histories.apply {
            value = arrayOf(
                AlertHistoryDAO("TEST", Date("20200301100000", "yyyyMMddHHmmss"), 0.0, 0.0,  arrayOf(UserDAO("1",  "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.quotes.net%2Fauthors%2FUnknown&psig=AOvVaw2uVJzkafwJNVDGJFGmLU0_&ust=1585117487075000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIj47pK9sugCFQAAAAAdAAAAABAR","test@utah.edu", "Kyungyoon Kim"))),
                AlertHistoryDAO("TEST", Date("20200301110000", "yyyyMMddHHmmss"), 0.0, 0.0,  arrayOf(UserDAO("1", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.quotes.net%2Fauthors%2FUnknown&psig=AOvVaw2uVJzkafwJNVDGJFGmLU0_&ust=1585117487075000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIj47pK9sugCFQAAAAAdAAAAABAR","test@utah.edu", "Kyungyoon Kim"))),
                AlertHistoryDAO("TEST", Date("20200301120000", "yyyyMMddHHmmss"), 0.0, 0.0,  arrayOf(UserDAO("1", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.quotes.net%2Fauthors%2FUnknown&psig=AOvVaw2uVJzkafwJNVDGJFGmLU0_&ust=1585117487075000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIj47pK9sugCFQAAAAAdAAAAABAR", "test@utah.edu", "Kyungyoon Kim")))
            ).sortedWith(compareBy(AlertHistoryDAO::time))
        }
    }
}