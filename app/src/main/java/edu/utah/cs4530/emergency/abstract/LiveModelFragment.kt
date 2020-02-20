package edu.utah.cs4530.emergency.abstract

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

abstract class LiveModelFragment<T : ViewModel>(private val modelClass: KClass<T>, @LayoutRes private val layoutRes: Int): Fragment()
{
    protected lateinit var root: View
    protected lateinit var viewModel: T

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(modelClass.java)
        root = inflater.inflate(layoutRes, container, false)

        onCreateViewModel(inflater, container, savedInstanceState)
        return root
    }

    abstract fun onCreateViewModel( inflater: LayoutInflater,
                                    container: ViewGroup?,
                                    savedInstanceState: Bundle?)


}