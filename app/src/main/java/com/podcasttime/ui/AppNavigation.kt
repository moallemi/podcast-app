package com.podcasttime.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.podcasttime.ui.detail.PodcastDetailScreen
import com.podcasttime.ui.home.HomeScreen

@Composable
fun AppNavigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = "home") {
    composable("home") {
      HomeScreen(
        onPodcastClick = { podcastId ->
          navController.navigate("podcastDetail/$podcastId")
        },
      )
    }
    composable(
      route = "podcastDetail/{podcastId}",
      arguments = listOf(
        navArgument("podcastId") {
          type = NavType.StringType
        },
      ),
    ) {
      val podcastId = it.arguments?.getString("podcastId")!!
      PodcastDetailScreen(
        podcastId = podcastId,
      )
    }
  }
}
