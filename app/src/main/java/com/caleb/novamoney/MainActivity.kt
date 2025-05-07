//package com.caleb.novamoney
//
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.caleb.novamoney.ui.theme.screens.home.MainScreen
//import com.caleb.novamoney.ui.theme.screens.login.LoginScreen
//import com.caleb.novamoney.ui.theme.screens.register.SignUpScreen
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AppNavigation()
//        }
//    }
//}
//
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
