package com.caleb.novamoney.ui.theme.screens.transaction



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

// Data model
data class Transaction(
    val id: Int,
    val category: String,
    val amount: Float,
    val date: String,
    val merchant: String,
    val isRecurring: Boolean = false,
    val note: String = ""
)

// Summary at top
@Composable
fun TransactionSummary(transactions: List<Transaction>) {
    val totalSpent = transactions.sumOf { it.amount.toDouble() }
    val totalIncome = transactions.filter { it.amount < 0 }.sumOf { it.amount.toDouble() }
    val balance = totalIncome + totalSpent

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Summary", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Total Spent: \$${totalSpent}")
        Text("Total Income: \$${-totalIncome}")
        Text("Balance: \$${balance}")
    }
}

// Category breakdown bar
@Composable
fun CategoryBreakdown(transactions: List<Transaction>) {
    val categoryTotals = transactions.groupBy { it.category }
        .mapValues { entry -> entry.value.sumOf { it.amount.toDouble() } }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("Category Breakdown", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        categoryTotals.forEach { (category, total) ->
            Text("$category: \$${total}")
            LinearProgressIndicator(
                progress = (total / transactions.sumOf { it.amount.toDouble() }).toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(vertical = 4.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

// Transaction row with swipe actions
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionRow(
    transaction: Transaction,
    onDelete: (Transaction) -> Unit,
    onEdit: (Transaction) -> Unit,
    onClick: (Transaction) -> Unit
) {
    val dismissState = rememberDismissState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == DismissValue.DismissedToEnd) {
            scope.launch { onEdit(transaction) }
            dismissState.reset()
        } else if (dismissState.currentValue == DismissValue.DismissedToStart) {
            scope.launch { onDelete(transaction) }
            dismissState.reset()
        }
    }

    SwipeToDismiss(
        state = dismissState,
        background = {
            val color = when (dismissState.dismissDirection) {
                DismissDirection.StartToEnd -> Color.Green
                DismissDirection.EndToStart -> Color.Red
                null -> Color.Transparent
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                val icon = when (dismissState.dismissDirection) {
                    DismissDirection.StartToEnd -> Icons.Default.Edit
                    DismissDirection.EndToStart -> Icons.Default.Delete
                    null -> null
                }
                icon?.let {
                    Icon(imageVector = it, contentDescription = null, tint = Color.White)
                }
            }
        },
        dismissContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onClick(transaction) }
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("${transaction.category} - \$${transaction.amount}", fontWeight = FontWeight.Bold)
                        Text("${transaction.merchant} - ${transaction.date}", fontSize = 12.sp, color = Color.Gray)
                        if (transaction.isRecurring) {
                            Text("Recurring", fontSize = 10.sp, color = Color.Magenta)
                        }
                    }
                }
            }
        }
    )
}

// Main Transaction Screen
@Composable
fun TransactionScreen() {
    var transactions by remember {
        mutableStateOf(
            listOf(
                Transaction(1, "Food", 25f, "2025-05-01", "McDonald's", true),
                Transaction(2, "Transport", 15f, "2025-05-02", "Uber"),
                Transaction(3, "Entertainment", 50f, "2025-05-03", "Netflix", true),
                Transaction(4, "Groceries", 80f, "2025-05-04", "Walmart")
            )
        )
    }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Transactions") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Add transaction logic
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Transaction")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TransactionSummary(transactions)
            CategoryBreakdown(transactions)

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Transactions") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            val filteredTransactions = transactions.filter {
                it.category.contains(searchQuery, ignoreCase = true) ||
                        it.merchant.contains(searchQuery, ignoreCase = true)
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(filteredTransactions, key = { it.id }) { txn ->
                    TransactionRow(
                        transaction = txn,
                        onDelete = { transactions = transactions - it },
                        onEdit = { /* Navigate to edit screen */ },
                        onClick = { /* Navigate to details screen */ }
                    )
                }
            }
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    MaterialTheme {
        TransactionScreen()
    }
}
