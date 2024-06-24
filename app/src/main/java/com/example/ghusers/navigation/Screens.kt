package com.example.ghusers.navigation

import com.example.ghusers.ui.user_details.UserDetailsFragment
import com.example.ghusers.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun users() = FragmentScreen {
        UsersFragment.newInstance()
    }

    fun userDetails(name: String?) = FragmentScreen {
        UserDetailsFragment.newInstance(name)
    }

}