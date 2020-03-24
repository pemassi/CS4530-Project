package edu.utah.cs4530.emergency.ui.editmessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import edu.utah.cs4530.emergency.R
import edu.utah.cs4530.emergency.abstract.LiveModelFragment
import android.widget.EditText


class EditMessageFragment : LiveModelFragment<EditMessageViewModel>(EditMessageViewModel::class, R.layout.edit_message_fragment) {

    lateinit var editTextInput: EditText;
    override fun onCreateViewModel(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    {
        viewModel.text.observe(viewLifecycleOwner, Observer {
        })

    }
}

