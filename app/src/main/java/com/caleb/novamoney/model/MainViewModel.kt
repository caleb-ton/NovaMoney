package com.caleb.novamoney.model


import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // Search query state
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    // Selected bottom item state
    private val _selectedBottomItem = mutableStateOf("Home")
    val selectedBottomItem: State<String> = _selectedBottomItem

    // News list state
    private val _newsList = mutableStateOf(
        listOf(
            "Markets Rally as Tech Stocks Surge",
            "Federal Reserve Holds Interest Rates",
            "Bitcoin Hits New High",
            "Oil Prices Drop Amid Global Uncertainty",
            "Major Banks Announce Quarterly Earnings"
        )
    )
    val newsList: State<List<String>> = _newsList

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onBottomItemSelected(item: String) {
        _selectedBottomItem.value = item
    }

    // Example: load news dynamically in future (optional)
    fun fetchNews() {
        viewModelScope.launch {
            // You can replace this with a real API call
            _newsList.value = listOf(
                "Updated News 1",
                "Updated News 2",
                "Updated News 3"
            )
        }
    }
}
