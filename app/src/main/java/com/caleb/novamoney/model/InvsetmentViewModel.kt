package com.caleb.novamoney.model


import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.ui.theme.NovaMoneyTheme
import com.caleb.novamoney.ui.theme.screens.investment.InvestmentScreenWithBottomBar
import kotlinx.coroutines.launch

data class InvestmentSummary(
    val totalInvestment: Float,
    val currentValue: Float
)

class InvestmentViewModel : ViewModel() {

    private val _investmentSummary = mutableStateOf(
        InvestmentSummary(
            totalInvestment = 10_000f,
            currentValue = 12_500f
        )
    )
    val investmentSummary: State<InvestmentSummary> = _investmentSummary

    // Example: Simulate fetching updated data
    fun fetchInvestmentData() {
        viewModelScope.launch {
            // Simulate network/API call
            _investmentSummary.value = InvestmentSummary(
                totalInvestment = 15_000f,
                currentValue = 17_800f
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InvestmentScreenWithBottomBarPreview() {
    NovaMoneyTheme {
        InvestmentScreenWithBottomBar(rememberNavController())
    }
}

