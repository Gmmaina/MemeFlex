package com.memeapp.memeflex.ui.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.memeapp.memeflex.ui.feed.components.MemeCard
import kotlinx.coroutines.DelicateCoroutinesApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

// Sample data for UI preview
data class SampleMeme(
    val id: String,
    val username: String,
    val imageUrl: String,
    val caption: String?,
    val likesCount: Int,
    val isLiked: Boolean,
    val timeAgo: String
)

@OptIn(DelicateCoroutinesApi::class, ExperimentalMaterialApi::class) // Only for GlobalScope usage, consider using a ViewModel
@Composable
fun FeedScreen(
    onNavigateToMemeDetail: (String) -> Unit,
    onNavigateToUserProfile: (String) -> Unit
) {
    // Sample data - replace with actual data from ViewModel
    var sampleMemes by remember {
        mutableStateOf(
            listOf(
                SampleMeme(
                    id = "1",
                    username = "memeLord42",
                    imageUrl = "https://picsum.photos/400/400?random=1",
                    caption = "When you finally understand recursion 😂",
                    likesCount = 234,
                    isLiked = false,
                    timeAgo = "2h ago"
                ),
                SampleMeme(
                    id = "2",
                    username = "codingQueen",
                    imageUrl = "https://picsum.photos/400/400?random=2",
                    caption = "Me debugging at 3 AM 💀",
                    likesCount = 456,
                    isLiked = true,
                    timeAgo = "4h ago"
                ),
                SampleMeme(
                    id = "3",
                    username = "techGuru",
                    imageUrl = "https://picsum.photos/400/400?random=3",
                    caption = "Android developers when Compose was announced",
                    likesCount = 789,
                    isLiked = false,
                    timeAgo = "6h ago"
                ),
                SampleMeme(
                    id = "4",
                    username = "devLife",
                    imageUrl = "https://picsum.photos/400/400?random=4",
                    caption = null,
                    likesCount = 123,
                    isLiked = true,
                    timeAgo = "8h ago"
                ),
                SampleMeme(
                    id = "5",
                    username = "mobileFirst",
                    imageUrl = "https://picsum.photos/400/400?random=5",
                    caption = "When the build finally succeeds after 20 attempts 🎉",
                    likesCount = 567,
                    isLiked = false,
                    timeAgo = "12h ago"
                )
            )
        )
    }

    var isRefreshing by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            // TODO: Implement refresh logic
            // Simulate refresh delay
            kotlinx.coroutines.GlobalScope.launch {
                kotlinx.coroutines.delay(1500)
                isRefreshing = false
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleMemes) { meme ->
                MemeCard(
                    username = meme.username,
                    imageUrl = meme.imageUrl,
                    caption = meme.caption,
                    likesCount = meme.likesCount,
                    isLiked = meme.isLiked,
                    timeAgo = meme.timeAgo,
                    onLikeClick = {
                        // Update like status locally
                        sampleMemes = sampleMemes.map {
                            if (it.id == meme.id) {
                                it.copy(
                                    isLiked = !it.isLiked,
                                    likesCount = if (it.isLiked) it.likesCount - 1 else it.likesCount + 1
                                )
                            } else it
                        }
                    },
                    onShareClick = {
                        // TODO: Implement share functionality
                    },
                    onDownloadClick = {
                        // TODO: Implement download functionality
                    },
                    onUserClick = {
                        onNavigateToUserProfile(meme.username)
                    },
                    onMemeClick = {
                        onNavigateToMemeDetail(meme.id)
                    }
                )
            }

            // Loading indicator for pagination
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview
@Composable
private fun FeedScreenPreview() {
    FeedScreen(
        onNavigateToMemeDetail = {},
        onNavigateToUserProfile = {}
    )
}