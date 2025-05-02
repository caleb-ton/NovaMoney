package com.caleb.novamoney.ui.theme.screens.transaction


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

data class Transaction(
    val type: String,
    val amount: Float,
    val date: String,
    val category: String
)

@Composable
fun TransactionScreen() {
    val transactions = listOf(
        Transaction("Purchase", 45.50f, "2025-05-01", "Groceries"),
        Transaction("Deposit", 200.00f, "2025-04-30", "Income"),
        Transaction("Subscription", 15.00f, "2025-04-29", "Entertainment"),
        Transaction("Withdrawal", 100.00f, "2025-04-28", "ATM")
    )

    // State to manage the search query
    val searchText = remember { TextFieldValue("") }

    // Filtered transactions based on search text
    val filteredTransactions = transactions.filter {
        it.category.contains(searchText.text, ignoreCase = true) ||
                it.type.contains(searchText.text, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        SearchBar(
            searchText = searchText,
            onSearchTextChange = { newText -> searchText.copy(text = newText) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Screen Title
        Text(
            text = "Recent Transactions",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Transaction List
        filteredTransactions.forEach { transaction ->
            TransactionCard(transaction = transaction)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun SearchBar(searchText: TextFieldValue, onSearchTextChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(50)) // Rounded shape
            .shadow(8.dp, shape = RoundedCornerShape(50)) // Elevation for modern look
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search Icon
        Icon(
            imageVector = Icons.Filled.Search, // Material icon for search
            contentDescription = "Search",
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )

        Spacer(modifier = Modifier.width(12.dp))

        // The TextField for user input
        BasicTextField(
            value = searchText,
            onValueChange = { onSearchTextChange(it.text) },
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
            modifier = Modifier
                .weight(1f)
                .padding(0.dp), // Removing extra padding so icon and text are aligned properly
            singleLine = true
        )
    }
}

@Composable
fun TransactionCard(transaction: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(12.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White)
        ) {
            // Icon based on transaction type (using Material Icons)
            TransactionIcon(transactionType = transaction.type)

            Spacer(modifier = Modifier.width(16.dp))

            // Transaction Details
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = transaction.category,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = transaction.date,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }

            // Transaction Amount
            Text(
                text = "$${"%.2f".format(transaction.amount)}",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (transaction.type == "Deposit") Color.Green else Color.Red
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun TransactionIcon(transactionType: String) {
    val icon = when (transactionType) {
        "Purchase" -> Icons.Filled.ShoppingCart // Use shopping cart icon for Purchase
//        "Deposit" -> Icons.Filled.Add // Use bank icon for Deposit
//        "Subscription" -> Icons.Filled.Subscriptions // Use subscriptions icon for Subscription
//        "Withdrawal" -> Icons.Filled.AttachMoney // Use money icon for Withdrawal
        else -> Icons.Filled.Add // Default icon (can be changed)
    }

    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier.size(40.dp),
        tint = Color.Gray
    )
}

@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
        TransactionScreen()
}

