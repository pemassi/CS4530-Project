package edu.utah.cs4530.emergency.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import edu.utah.cs4530.emergency.R
import edu.utah.cs4530.emergency.abstract.LiveModelFragment
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : LiveModelFragment<GalleryViewModel>(GalleryViewModel::class, R.layout.fragment_gallery) {

    override fun onCreateViewModel(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    {
        viewModel.text.observe(viewLifecycleOwner, Observer {
            text_gallery.text = "TEST : $it"
        })
    }
}