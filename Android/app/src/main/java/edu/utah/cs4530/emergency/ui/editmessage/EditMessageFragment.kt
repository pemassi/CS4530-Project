package edu.utah.cs4530.emergency.ui.editmessage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import edu.utah.cs4530.emergency.R
import edu.utah.cs4530.emergency.abstract.LiveModelFragment
import kotlinx.android.synthetic.main.edit_message_fragment.*


class EditMessageFragment : LiveModelFragment<EditMessageViewModel>(EditMessageViewModel::class, R.layout.edit_message_fragment) {

    override fun onCreateViewModel(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    {
        viewModel.text.observe(viewLifecycleOwner, Observer {
        })
    }
}

