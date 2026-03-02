package com.example.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.vknewsclient.ui.elements.NewsCard
import com.example.vknewsclient.ui.theme.VkNewsClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkNewsClientTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewsCard(
                        titleIcon = null,
                        title = "уволено",
                        time = "14:00",
                        description = "hjb j,yf,j cghvjbk chgfjk yfjhgio kdfguhkjl cgtfhyji nhgfygjuhijo fcghjuji cghfjk",
                        cardImage = null,
                        seeCount = 206,
                        shareCount = 206,
                        commentCount = 11,
                        likeCount = 491,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
