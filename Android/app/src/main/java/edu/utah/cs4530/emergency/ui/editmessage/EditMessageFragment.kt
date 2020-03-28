package edu.utah.cs4530.emergency.ui.editmessage

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import edu.utah.cs4530.emergency.R
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gun0912.tedonactivityresult.TedOnActivityResult
import edu.utah.cs4530.emergency.abstract.LiveModelFragment
import edu.utah.cs4530.emergency.dao.EmergencyMessageDAO
import kotlinx.android.synthetic.main.edit_message_fragment.view.*


class EditMessageFragment : LiveModelFragment<EditMessageViewModel>(EditMessageViewModel::class, R.layout.edit_message_fragment) {

    private lateinit var editMessageViewModel: EditMessageViewModel
    private lateinit var editMessageInput: EditText
    override fun onCreateViewModel(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    {
        root.message_text.setText("This is an emergency message that will be sent to every one on your contact list")
        editMessageInput = root.findViewById(R.id.message_text)
        root.save_button.setOnClickListener {
            TedOnActivityResult.with(context)
                .setListener { resultCode, data ->
                    if(resultCode == RESULT_OK) {
                        viewModel.saveEmergencyMessage(
                            EmergencyMessageDAO(
                            emergencyText = editMessageInput.text.toString()
                        ))
                    }
                }
                .startActivityForResult()
        }
//        editMessageViewModel = ViewModelProviders.of(this).get(EditMessageViewModel::class.java)
//        val root = inflater.inflate(R.layout.edit_message_fragment, container, false);
//        val textView: TextView = root.findViewById(R.id.message_text);
//        editMessageViewModel.text.observe(viewLifecycleOwner, Observer {
////            textView.text = it
//        })
//
//        editMessageInput = root.findViewById(R.id.message_text);
//        editMessageInput.setText("Default Message: This message is to inform you that the user is in an emergency.")
    }
}


