package com.example.ghusers.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import com.example.ghusers.ui.base.BaseFragment

class UsersFragment: BaseFragment() {

    companion object {
        fun newInstance(): UsersFragment {
            return UsersFragment()
        }
    }

    override fun getRootView(inflater: LayoutInflater, container: ViewGroup?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Text(text = "USERS fragment composable")
            }
        }
    }

}

//        setContent {
//            GHUsersTheme{
//                Root(Modifier.fillMaxSize())
//            }
//
//        }