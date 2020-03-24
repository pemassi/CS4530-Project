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
                AlertHistoryDAO("TEST", Date("20200301120000", "yyyyMMddHHmmss"), 0.0, 0.0,  arrayOf(UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"))),
                AlertHistoryDAO("TEST", Date("20200301120000", "yyyyMMddHHmmss"), 0.0, 0.0,  arrayOf(UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"))),
                AlertHistoryDAO("TEST", Date("20200301120000", "yyyyMMddHHmmss"), 0.0, 0.0,  arrayOf(UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"))),
                AlertHistoryDAO("TEST", Date("20200301120000", "yyyyMMddHHmmss"), 0.0, 0.0,  arrayOf(UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"))),
                AlertHistoryDAO("TEST", Date("20200301120000", "yyyyMMddHHmmss"), 0.0, 0.0,  arrayOf(UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"))),
                AlertHistoryDAO("TEST", Date("20200301120000", "yyyyMMddHHmmss"), 0.0, 0.0,  arrayOf(UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim"), UserDAO("1", "https://lh3.googleusercontent.com/6GYJAocOJPSbTbcdvr7NLaw0xOH_STqQS_VybH8aVfGCe_wAoeRS8fHvR_l1afVGS97IyVIQ=w16383", "test@utah.edu", "Kyungyoon Kim")))
            ).sortedWith(compareBy(AlertHistoryDAO::time))
        }
    }
}