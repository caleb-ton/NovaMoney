package com.caleb.novamoney.ui.theme.screens.transaction



import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.navigation.ROUTE_ACCOUNT
import com.caleb.novamoney.navigation.ROUTE_HOME
import com.caleb.novamoney.navigation.ROUTE_NOTIFICATION
import com.caleb.novamoney.navigation.ROUTE_PROFILE
import com.caleb.novamoney.navigation.ROUTE_SETTINGS


@Stable
data class FullTransaction(
    val id: String,
    val dateTime: String,
    val amount: Double,
    val currency: String,
    val status: String,
    val merchant: String,
    val paymentMethod: String,
    val category: String,
    val notes: String,
    val receiptUrl: String?,
    val location: String?,
    val convertedAmount: String?,
    val rewardPoints: Int?,
    val isRecurring: Boolean,
    val transactionType: String
)

@Composable
fun TransactionCard(txn: FullTransaction, hideAmount: Boolean = false) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ID: ${txn.id}", fontSize = 12.sp, color = Color.Gray)
                StatusBadge(status = txn.status)
            }

            Spacer(Modifier.height(8.dp))

            Text(txn.merchant, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(txn.dateTime, fontSize = 12.sp, color = Color.Gray)

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (hideAmount) "••••" else "${txn.currency} ${"%.2f".format(txn.amount)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(Modifier.width(8.dp))
                txn.convertedAmount?.let {
                    Text("($it)", fontSize = 12.sp, color = Color.Gray)
                }
            }

            Spacer(Modifier.height(8.dp))
            Text("Payment: ${txn.paymentMethod}", fontSize = 12.sp)
            Text("Category: ${txn.category}", fontSize = 12.sp)
            txn.rewardPoints?.let {
                Text("Reward Points: $it", fontSize = 12.sp, color = Color(0xFF9C27B0))
            }
            if (txn.isRecurring) {
                Text("Recurring: Yes", fontSize = 12.sp, color = Color(0xFF3F51B5))
            }

            Spacer(Modifier.height(8.dp))
            if (txn.notes.isNotBlank()) {
                Text("Note: ${txn.notes}", fontSize = 12.sp, maxLines = 3, overflow = TextOverflow.Ellipsis)
            }

            txn.receiptUrl?.let {
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Create, contentDescription = "Receipt")
                    Text("View Receipt", color = Color.Blue, modifier = Modifier.padding(start = 4.dp))
                }
            }

            txn.location?.let {
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = "Location")
                    Text(it, fontSize = 12.sp, color = Color.Gray)
                }
            }

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ActionIcon(Icons.Default.Warning, "Report") {
                    Toast.makeText(context, "Report Issue", Toast.LENGTH_SHORT).show()
                }
                ActionIcon(Icons.Default.AddCircle, "Refund") {
                    Toast.makeText(context, "Request Refund", Toast.LENGTH_SHORT).show()
                }
                ActionIcon(Icons.Default.Share, "Share") {
                    Toast.makeText(context, "Share Details", Toast.LENGTH_SHORT).show()
                }
                ActionIcon(Icons.Default.Add, "Split") {
                    Toast.makeText(context, "Split Bill", Toast.LENGTH_SHORT).show()
                }
                ActionIcon(Icons.Default.Refresh, "Repeat") {
                    Toast.makeText(context, "Repeat Transaction", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun StatusBadge(status: String) {
    val (color, icon) = when (status.lowercase()) {
        "completed" -> Color(0xFF4CAF50) to Icons.Default.CheckCircle
        "canceled" -> Color(0xFF9E9E9E) to Icons.Default.Close
        else -> Color.Gray to Icons.Default.Add
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(16.dp))
        Text(
            text = status,
            color = color,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun ActionIcon(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() }
    ) {
        Icon(icon, contentDescription = label, tint = MaterialTheme.colorScheme.primary)
        Text(label, fontSize = 10.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullTransactionScreen(navController: NavController) {
    var hideAmount by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var selectedBottomItem by remember { mutableStateOf("Home") }
    var filteredTransactions by remember { mutableStateOf(listOf<FullTransaction>()) }

    val transactions = remember {
        listOf(
            FullTransaction(
                id = "TXN123456",
                dateTime = "2025-05-07 10:45 AM",
                amount = 59.99,
                currency = "Ksh",
                status = "Completed",
                merchant = "Amazon",
                paymentMethod = "Visa **** 1234",
                category = "Shopping",
                notes = "Birthday gift",
                receiptUrl = null,
                location = "Nairobi, Kenya",
                convertedAmount = null,
                rewardPoints = 60,
                isRecurring = false,
                transactionType = "Debit"
            ),
            FullTransaction(
                id = "TXN654321",
                dateTime = "2025-05-06 3:20 PM",
                amount = -15.00,
                currency = "Ksh",
                status = "Pending",
                merchant = "Spotify",
                paymentMethod = "Mastercard **** 5678",
                category = "Entertainment",
                notes = "",
                receiptUrl = null,
                location = null,
                convertedAmount = "EUR 13.50",
                rewardPoints = null,
                isRecurring = true,
                transactionType = "Subscription"
            )
        )
    }

    // Initial filter to show all transactions
    LaunchedEffect(Unit) {
        filteredTransactions = transactions
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search transactions...") },
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
                    IconButton(onClick = {
                        val query = searchQuery.text.trim().lowercase()
                        filteredTransactions = transactions.filter {
                            it.merchant.lowercase().contains(query) ||
                                    it.category.lowercase().contains(query) ||
                                    it.status.lowercase().contains(query) ||
                                    it.notes.lowercase().contains(query)
                        }
                    }) {
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
                        selectedBottomItem = "Home"
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedBottomItem == "Profile",
                    onClick = {
                        navController.navigate(ROUTE_PROFILE)
                        selectedBottomItem = "Profile"
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Alerts") },
                    label = { Text("Alerts") },
                    selected = selectedBottomItem == "Alerts",
                    onClick = {
                        navController.navigate(ROUTE_NOTIFICATION)
                        selectedBottomItem = "Alerts"
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
                    label = { Text("Account") },
                    selected = selectedBottomItem == "Account",
                    onClick = {
                        navController.navigate(ROUTE_ACCOUNT)
                        selectedBottomItem = "Account"
                    }
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(12.dp)
        ) {
            items(filteredTransactions) { txn ->
                TransactionCard(txn = txn, hideAmount = hideAmount)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFullTransactionScreen() {
    MaterialTheme {
        FullTransactionScreen(rememberNavController())
    }
}
