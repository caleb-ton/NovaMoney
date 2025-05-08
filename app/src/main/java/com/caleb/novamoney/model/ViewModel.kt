package com.caleb.novamoney.model

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.R
import com.caleb.novamoney.navigation.ROUTE_ACCOUNT
import com.caleb.novamoney.navigation.ROUTE_HOME
import com.caleb.novamoney.navigation.ROUTE_NOTIFICATION
import com.caleb.novamoney.navigation.ROUTE_PROFILE
import com.caleb.novamoney.navigation.ROUTE_SETTINGS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    val searchQuery by viewModel.searchQuery
    val selectedBottomItem by viewModel.selectedBottomItem
    val newsList by viewModel.newsList
    val context = LocalContext.current
    val url = "https://www.cnbc.com/world/?region=world"

    Column(modifier = Modifier.fillMaxSize()) {
        // App Logo at the top
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.aaron),
                contentDescription = "App Logo",
                modifier = Modifier.size(60.dp)
            )
        }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        TextField(
                            value = searchQuery,
                            onValueChange = { viewModel.onSearchQueryChanged(it) },
                            placeholder = { Text("Search finance news") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(ROUTE_SETTINGS) }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* handle search */ }) {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = selectedBottomItem == "Home",
                        onClick = {
                            navController.navigate(ROUTE_HOME)
                            viewModel.onBottomItemSelected("Home")
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") },
                        selected = selectedBottomItem == "Profile",
                        onClick = {
                            navController.navigate(ROUTE_PROFILE)
                            viewModel.onBottomItemSelected("Profile")
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Notifications, contentDescription = "Alerts") },
                        label = { Text("Alerts") },
                        selected = selectedBottomItem == "Alerts",
                        onClick = {
                            navController.navigate(ROUTE_NOTIFICATION)
                            viewModel.onBottomItemSelected("Alerts")
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
                        label = { Text("Account") },
                        selected = selectedBottomItem == "Account",
                        onClick = {
                            navController.navigate(ROUTE_ACCOUNT)
                            viewModel.onBottomItemSelected("Account")
                        }
                    )
                }
            },
            content = { padding ->
                LazyColumn(
                    contentPadding = padding,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(newsList) { news ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                },
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = news,
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Click to read more...",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    // Optional: Provide a fake ViewModel using a custom preview ViewModel or mock data if needed
    MainScreen(navController = navController)
}