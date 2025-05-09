package com.caleb.novamoney.navigation

import androidx.compose.foundation.magnifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.ui.theme.screens.budgettracker.BudgetTrackerApp
import com.caleb.novamoney.ui.theme.screens.home.MainScreen
import com.caleb.novamoney.ui.theme.screens.investment.InvestmentScreen
import com.caleb.novamoney.ui.theme.screens.login.LoginScreen
import com.caleb.novamoney.ui.theme.screens.profile.EditProfileScreenWithBottomBar
import com.caleb.novamoney.ui.theme.screens.profile.ProfileScreen
import com.caleb.novamoney.ui.theme.screens.register.SignUpScreen
import com.caleb.novamoney.ui.theme.screens.transaction.FullTransactionScreen


@Composable
fun AppNavHost(modifier: Modifier=Modifier,navController:NavHostController= rememberNavController(),startDestination:String= ROUTE_REGISTER) {

    NavHost(navController = navController, modifier=modifier, startDestination = startDestination ){
        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_REGISTER){
            SignUpScreen(navController)
        }

        composable(ROUTE_HOME){
            MainScreen(navController)
        }
//        composable(ROUTE_INVESTMENT) {
//            InvestmentScreen(navController)
//        }
        composable(ROUTE_BUDGET_TRACKER){
            BudgetTrackerApp(navController)
        }
//        composable(ROUTE_PROFILE){
//            ProfileScreen(navController)
//        }
        composable(ROUTE_EDIT_PROFILE){
            EditProfileScreenWithBottomBar(navController)
        }
        composable(ROUTE_TRANSACTION){
            FullTransactionScreen(navController)
        }
    }

}