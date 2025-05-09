package com.caleb.novamoney


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.caleb.novamoney.navigation.AppNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()
        }
    }
}

//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "login") {
//        composable("login") {
//            LoginScreen(
//                onLoginSuccess = { navController.navigate("home") },
//                onSignUpClick = { navController.navigate("register") }
//            )
//        }
//        composable("register") {
//            SignUpScreen(
//                onSignUpSuccess = { navController.navigate("home") },
//                onBackToLogin = { navController.popBackStack("login", inclusive = false) }
//            )
//        }
//        composable("home") {
//            MainScreen()
//        }
//    }
//}
//
