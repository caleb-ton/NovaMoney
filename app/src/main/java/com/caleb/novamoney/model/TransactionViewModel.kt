package com.caleb.novamoney.model


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.ui.theme.screens.transaction.FullTransaction
import com.caleb.novamoney.ui.theme.screens.transaction.FullTransactionScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class TransactionViewModel : ViewModel() {

    // Sample static transactions (same as your screen)
    private val _transactions = MutableStateFlow(
        listOf(
            FullTransaction(
                id = "TXN123456",
                dateTime = "2025-05-07 10:45 AM",
                amount = 59.99,
                currency = "USD",
                status = "Completed",
                merchant = "Amazon",
                paymentMethod = "Visa **** 1234",
                category = "Shopping",
                notes = "Birthday gift",
                receiptUrl = null,
                location = "New York, USA",
                convertedAmount = null,
                rewardPoints = 60,
                isRecurring = false,
                transactionType = "Debit"
            ),
            FullTransaction(
                id = "TXN654321",
                dateTime = "2025-05-06 3:20 PM",
                amount = -15.00,
                currency = "USD",
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
    )
    val transactions: StateFlow<List<FullTransaction>> = _transactions

    // Filters
    val search = MutableStateFlow("")
    val dateRange = MutableStateFlow("")
    val amountRange = MutableStateFlow("")
    val typeFilter = MutableStateFlow("")

    // Filtered transactions
    val filteredTransactions: StateFlow<List<FullTransaction>> = combine(
        _transactions, search, dateRange, amountRange, typeFilter
    ) { txns, search, _, _, type ->
        txns.filter {
            (search.isBlank() || it.merchant.contains(search, ignoreCase = true) || it.category.contains(search, ignoreCase = true)) &&
                    (type.isBlank() || it.transactionType.contains(type, ignoreCase = true))
            // You can enhance date/amount filters here as needed.
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _transactions.value)
}



@Preview(showBackground = true)
@Composable
fun PreviewFullTransactionScreen() {
    MaterialTheme {
        FullTransactionScreen(
            navController = rememberNavController(),
//            viewModel = TransactionViewModel() // Manual VM in preview
        )
    }
}
