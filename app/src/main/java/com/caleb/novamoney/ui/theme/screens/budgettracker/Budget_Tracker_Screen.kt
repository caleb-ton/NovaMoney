package com.caleb.novamoney.ui.theme.screens.budgettracker



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight

@Composable
fun BudgetTrackerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Transparent)
    ) {
        Text(
            text = "Budget Overview",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Budget Info Card
        BudgetInfoCard(
            title = "Income",
            amount = 5000f,
            color = Color.Green,
        )
        Spacer(modifier = Modifier.height(16.dp))

        BudgetInfoCard(
            title = "Expenses",
            amount = 1200f,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))

        BudgetInfoCard(
            title = "Remaining Balance",
            amount = 3800f,
            color = Color.Blue
        )
    }
}

@Composable
fun BudgetInfoCard(title: String, amount: Float, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium.copy(CornerSize(12.dp)),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color.copy(alpha = 0.1f))
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$${"%.2f".format(amount)}",
                style = MaterialTheme.typography.headlineMedium.copy(color = color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetTrackerScreenPreview() {
        BudgetTrackerScreen()
}