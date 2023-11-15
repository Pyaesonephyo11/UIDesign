package com.uitest.uidesign

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uitest.uidesign.screen.DetailScreen
import com.uitest.uidesign.screen.MainScreen

enum class UIDesignScreen {
  Main, Detail
}

@Composable
fun UIDesignApp() {

  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = UIDesignScreen.Main.name) {

    composable(route = UIDesignScreen.Main.name) {
      MainScreen(navController = navController)
    }

    composable(route = UIDesignScreen.Detail.name) {
      DetailScreen(navController = navController)
    }
  }
}