package com.vn.fsoft.gstlib.core.platform

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController

class ToolbarManager constructor(
    private var builder: FragmentToolbar,
    private var container: View,
    private var fragment: Fragment,
) {

    @SuppressLint("UseCompatLoadingForDrawables")
    fun prepareToolbar() {
        if (builder.resId != FragmentToolbar.NO_TOOLBAR) {
            val fragmentToolbar = container.findViewById(builder.resId) as Toolbar

            if (builder.title != -1) {
                fragmentToolbar.setTitle(builder.title)
            }

            if (builder.menuId != -1) {
                fragmentToolbar.inflateMenu(builder.menuId)
            }

            if (builder.menuItems.isNotEmpty() && builder.menuClicks.isNotEmpty()) {
                val menu = fragmentToolbar.menu
                for ((index, menuItemId) in builder.menuItems.withIndex()) {
                    (menu.findItem(menuItemId) as MenuItem).setOnMenuItemClickListener(builder.menuClicks[index])
                }
            }
            if (builder.isHideBackButton) {
                fragmentToolbar.navigationIcon = null
            }
            fragmentToolbar.setNavigationOnClickListener {
                findNavController(fragment).popBackStack()
            }
        }
    }
}