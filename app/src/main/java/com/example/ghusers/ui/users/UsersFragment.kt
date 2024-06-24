package com.example.ghusers.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.ghusers.ui.base.BaseFragment
import com.example.ghusers.ui.theme.GHUsersTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.ghusers.data.model.User

class UsersFragment: BaseFragment() {

    private lateinit var viewModel: UsersViewModel

    companion object {
        fun newInstance(): UsersFragment {
            return UsersFragment()
        }
    }

    override fun getRootView(inflater: LayoutInflater, container: ViewGroup?): View {
        viewModel = ViewModelProvider(this, viewModelFactory)[UsersViewModel::class.java]

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                UsersScreen(
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadUsers()
    }

}

enum class UsersUiState {
    LOADING, REFRESHING, LIST, ERROR
}

@Composable
fun UsersScreen(viewModel: UsersViewModel, modifier: Modifier = Modifier) {
    val state: UsersUiState by viewModel.uiState
    val data: State<List<User>> = viewModel.usersData.collectAsStateWithLifecycle()

    when(state) {
        UsersUiState.LOADING -> FullScreenLoading()

//        TODO refactor
        UsersUiState.REFRESHING -> FullScreenLoading()

        UsersUiState.LIST -> UsersColumn(data, modifier = Modifier.fillMaxWidth())

        //        TODO refactor
        UsersUiState.ERROR -> FullScreenLoading()
    }

}

@Composable
fun UsersColumn(
    data: State<List<User>>,
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()

    LazyColumn(
        modifier = modifier.padding(top = 20.dp, bottom = 20.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        state = state
    ) {
        items(items = data.value) { itemData: User ->
            UserItem(itemData)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun UsersScreenPreview() {
//    GHUsersTheme { UsersScreen() }
}

@Composable
fun UserItem(data: User) {
    Row(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = data.avatarUrl,
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
            Text(text = data.login ?: "",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.paddingFromBaseline(bottom = 5.dp)
            )

            Text(text = data.id,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(widthDp = 360, showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun UserItemPreview() {
    val dummyUser = User(id = "subtitleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", login = "title", avatarUrl = "https://www.drcommodore.it/wp-content/uploads/2022/02/233b624e43a04fe9bfd43ef00ebcb2c9.jpg")
    GHUsersTheme { UserItem(dummyUser) }
}

@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Blue,
            strokeWidth = 4.dp
        )
    }
}

@Preview(widthDp = 360, heightDp = 720, showBackground = true)
@Composable
fun FullScreenLoadingPreview() {
    GHUsersTheme {
        FullScreenLoading()
    }
}

