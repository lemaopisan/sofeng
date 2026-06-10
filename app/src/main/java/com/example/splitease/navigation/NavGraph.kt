package com.example.splitease.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.splitease.ui.screens.*

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object Splash : Screen("splash")
    object Home : Screen("home")
    object ScanReceipt : Screen("scan_receipt")
    object SelectItems : Screen("select_items?roomId={roomId}") {
        fun createRoute(roomId: String) = "select_items?roomId=$roomId"
    }
    object TotalPayment : Screen("total_payment")
    object PaymentCompleted : Screen("payment_completed")
    object PaymentReceived : Screen("payment_received")
}

@Composable
fun AppNavigation(startDestination: String = Screen.Start.route) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Start.route) { StartScreen(navController) }
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.ScanReceipt.route) { ScanReceiptScreen(navController) }
        
        composable(
            route = Screen.SelectItems.route,
            arguments = listOf(navArgument("roomId") { 
                type = NavType.StringType
                nullable = true
                defaultValue = "SPLIT-123" // Default for host creation
            }),
            deepLinks = listOf(navDeepLink { 
                uriPattern = "https://splitease.app/join/{roomId}"
                action = Intent.ACTION_VIEW
            })
        ) { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId") ?: "SPLIT-123"
            SelectItemsScreen(navController = navController, roomId = roomId)
        }
        
        composable(Screen.TotalPayment.route) { TotalPaymentScreen(navController) }
        composable(Screen.PaymentCompleted.route) { PaymentCompletedScreen(navController) }
        composable(Screen.PaymentReceived.route) { PaymentReceivedScreen(navController) }
    }
}
