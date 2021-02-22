package com.vn.fsoft.gstlib.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.vn.fsoft.gstlib.core.utils.Logger

/**
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */
abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("LifeCycle -> onCreate ")
        subscribeObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Logger.d("LifeCycle -> onCreateView ")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.d("LifeCycle -> onViewCreated ")
//        ToolbarManager(toolbarBuilder(), view, this).prepareToolbar()
    }

    override fun onDestroyView() {
        Logger.d("LifeCycle -> onDestroyView ")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Logger.d("LifeCycle -> onDestroy ")
        super.onDestroy()
    }

    protected abstract fun subscribeObservers()

    protected abstract fun toolbarBuilder(): FragmentToolbar

}
