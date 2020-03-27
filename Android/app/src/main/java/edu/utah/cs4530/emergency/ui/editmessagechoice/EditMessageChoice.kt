package edu.utah.cs4530.emergency.ui.editmessagechoice

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import edu.utah.cs4530.emergency.R

class EditMessageChoice : Fragment() {

    companion object {
        fun newInstance() = EditMessageChoice()
    }

    private lateinit var viewModel: EditMessageChoiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_message_choice_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditMessageChoiceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
