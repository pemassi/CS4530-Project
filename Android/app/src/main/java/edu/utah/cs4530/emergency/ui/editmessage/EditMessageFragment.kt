package edu.utah.cs4530.emergency.ui.editmessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import edu.utah.cs4530.emergency.R
import edu.utah.cs4530.emergency.abstract.LiveModelFragment
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


class EditMessageFragment : Fragment() {

    private lateinit var editMessageViewModel: EditMessageViewModel
    private lateinit var editMessageInput: EditText
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        editMessageViewModel = ViewModelProviders.of(this).get(EditMessageViewModel::class.java)
        val root = inflater.inflate(R.layout.edit_message_fragment, container, false);
        val textView: TextView = root.findViewById(R.id.message_text);
        editMessageViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        editMessageInput = root.findViewById(R.id.message_text);
        editMessageInput.setText("Default Message: This message is to inform you that the user is in an emergency.")
        return root
    }
}


