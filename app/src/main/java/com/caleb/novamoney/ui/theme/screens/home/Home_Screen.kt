package com.caleb.novamoney.ui.theme.screens.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.material3.MaterialTheme

@Composable
fun FinanceHomeScreen() {
    Scaffold(
        topBar = {
                Text(text = "NovaMoney")
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                BalanceCard(balance = 3421.56)
                Spacer(modifier = Modifier.height(24.dp))
                Text("Recent Transactions", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                TransactionList(transactions = sampleTransactions)
            }
        }
    )
}

@Composable
fun BalanceCard(balance: Double) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Total Balance", color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$${"%.2f".format(balance)}",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
        }
    }
}

@Composable
fun TransactionList(transactions: List<Transaction>) {
    LazyColumn {
        items(transactions) { transaction ->
            TransactionItem(transaction)
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(transaction.title, style = MaterialTheme.typography.bodyMedium)
                Text(transaction.date, style = MaterialTheme.typography.bodySmall)
            }
            Text(
                text = "$${transaction.amount}",
                color = if (transaction.amount < 0) Color.Red else Color.Green,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

data class Transaction(
    val title: String,
    val amount: Double,
    val date: String
)

val sampleTransactions = listOf(
    Transaction("Grocery", -45.0, "Apr 30"),
    Transaction("Salary", 3000.0, "Apr 28"),
    Transaction("Coffee", -3.5, "Apr 27"),
    Transaction("Electricity Bill", -120.0, "Apr 25")
)


@Preview(showBackground = true)
@Composable
fun PreviewFinanceHomeScreen() {
    FinanceHomeScreen()
}
//import android.text.style.BackgroundColorSpan
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.caleb.novamoney.ui.theme.screens.home.Transaction
//
//
//@Composable
//fun FinanceHomeScreen() {
//    Scaffold(
//        topBar = {
//                Text(text = "NovaMoney")
//        },
//        content = { padding ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//                    .padding(16.dp)
//            ) {
//                BalanceCard(balance = 3421.56)
//                Spacer(modifier = Modifier.height(24.dp))
//                Text("Recent Transactions", style = MaterialTheme.typography.titleMedium)
//                Spacer(modifier = Modifier.height(8.dp))
//                TransactionList(transactions = sampleTransactions)
//            }
//        }
//    )
//}
//
//
//@Composable
//fun BalanceCard(balance: Double) {
//    Card(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text("Total Balance", color = Color.White)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "$${"%.2f".format(balance)}",
//                style = MaterialTheme.typography.headlineLarge,
//                color = Color.White
//            )
//        }
//    }
//}
//
//@Composable
//fun TransactionList(transactions: List<Transaction>) {
//    LazyColumn {
////        items(transactions) { transaction ->
////            TransactionItem(transaction)
//        }
//    }
//
//
//@Composable
//fun GroupedTransactionList(transactions: List<Transaction>) {
//    // Group transactions by date
//    val groupedTransactions = transactions.groupBy { it.date }
//
//    LazyColumn {
//        groupedTransactions.forEach { (date, transactionsForDate) ->
//            item {
//                // Header for the grouped date
//                Text(
//                    text = date,
//                    style = MaterialTheme.typography.titleLarge,
//                    modifier = Modifier.padding(16.dp)
//                )
//            }
//
////            items(transactionsForDate) { transaction ->
////                TransactionItem(transaction)
////            }
//        }
//    }
//}
//
//@Composable
//fun TransactionItem(transaction: Transaction) {
//    Card(
//        shape = RoundedCornerShape(8.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(12.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Column {
//                Text(transaction.title, style = MaterialTheme.typography.bodyLarge)
//                Text(transaction.date, style = MaterialTheme.typography.bodyMedium)
//            }
//            Text(
//                text = "$${transaction.amount}",
//                color = if (transaction.amount < 0) Color.Red else Color.Green,
//                style = MaterialTheme.typography.bodyLarge
//            )
//        }
//    }
//}
//
//data class Transaction(
//    val title: String,
//    val amount: Double,
//    val date: String
//)
//
//val sampleTransactions = listOf(
//    Transaction("Grocery", -45.0, "Apr 30"),
//    Transaction("Salary", 3000.0, "Apr 28"),
//    Transaction("Coffee", -3.5, "Apr 27"),
//    Transaction("Electricity Bill", -120.0, "Apr 25")
//)
//
//@Preview
//@Composable
//private fun Finance() {
//    FinanceHomeScreen()
//
//}