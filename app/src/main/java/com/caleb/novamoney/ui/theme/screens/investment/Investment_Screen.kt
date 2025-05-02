package com.caleb.novamoney.ui.theme.screens.investment


//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CornerSize
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Clear
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.TextFieldValue
//
//@Composable
//fun InvestmentScreen() {
//    var searchText by remember { mutableStateOf(TextFieldValue("")) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Screen Title
//        Text(
//            text = "Investment Portfolio",
//            style = MaterialTheme.typography.headlineMedium,
//            modifier = Modifier.padding(bottom = 24.dp),
//            fontWeight = FontWeight.Bold
//        )
//
//        // Search Bar
//        InvestmentSearchBar(searchText) {
//
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Investment Summary
//        InvestmentSummaryCard(
//            totalInvestment = 10000f,
//            currentValue = 15000f,
//            growth = 50f
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Investment List (for demo, it will just be a placeholder)
//        InvestmentList()
//    }
//}
//
//@Composable
//fun InvestmentSearchBar(x0: TextFieldValue, content: @Composable () -> Unit) {
//    TODO("Not yet implemented")
//}
//
//@Composable
//fun CatchySearchBar(
//    searchText: TextFieldValue,
//    onSearchTextChange: (TextFieldValue) -> Unit,
//    onClearSearch: () -> Unit
//) {
//    // Row to contain the search bar and clear icon
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp)
//            .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(50)) // Rounded shape
//            .shadow(8.dp, shape = RoundedCornerShape(50)) // Elevation for a modern look
//            .padding(horizontal = 16.dp, vertical = 12.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        // Search Icon
//        Icon(
//            imageVector = Icons.Filled.Search, // Material icon for search
//            contentDescription = "Search",
//            modifier = Modifier.size(24.dp),
//            tint = Color.Gray
//        )
//
//        Spacer(modifier = Modifier.width(12.dp))
//
//        // The TextField for user input
//        BasicTextField(
//            value = searchText,
//            onValueChange = onSearchTextChange,
//            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
//            modifier = Modifier
//                .weight(1f)
//                .padding(0.dp), // Removing extra padding so icon and text are aligned properly
//            singleLine = true
//        )
//
//        // Clear icon if there's text in the search field
//        if (searchText.text.isNotEmpty()) {
//            Spacer(modifier = Modifier.width(8.dp))
//            IconButton(onClick = onClearSearch) {
//                Icon(
//                    imageVector = Icons.Filled.Clear, // Material icon for clear
//                    contentDescription = "Clear Search",
//                    modifier = Modifier.size(20.dp),
//                    tint = Color.Gray
//                )
//            }
//        }
//    }
//}
//
////@Preview(showBackground = true)
////@Composable
////fun CatchySearchBarPreview() {
////    // Use a TextFieldValue state for the preview
////    val searchText = TextFieldValue("Sample search")
////    CatchySearchBar(
////        searchText = searchText,
////        onSearchTextChange = {},
////        onClearSearch = {}
////    )
////}
//@Composable
//fun InvestmentSummaryCard(totalInvestment: Float, currentValue: Float, growth: Float) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = MaterialTheme.shapes.medium.copy(CornerSize(16.dp)),
//
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .background(Color.Green.copy(alpha = 0.1f))
//        ) {
//            Text(
//                text = "Total Investment",
//                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//            Text(
//                text = "$${"%.2f".format(totalInvestment)}",
//                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//
//            Text(
//                text = "Current Value",
//                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//            Text(
//                text = "$${"%.2f".format(currentValue)}",
//                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//
//            ProgressBar(growth = growth)
//        }
//    }
//}
//
//@Composable
//fun ProgressBar(growth: Float) {
//    LinearProgressIndicator(
//        progress = growth / 100,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 12.dp)
//            .height(8.dp)
//            .background(Color.Gray.copy(alpha = 0.2f), shape = MaterialTheme.shapes.small),
//        color = Color.Green
//    )
//}
//
//@Composable
//fun InvestmentList() {
//    Column {
//        listOf("Stock 1", "Stock 2", "Stock 3", "Stock 4").forEach { investment ->
//            Text(
//                text = investment,
//                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun InvestmentScreenPreview() {
//        InvestmentScreen()
//    val searchText = TextFieldValue("Sample search")
//    CatchySearchBar(
//        searchText = searchText,
//        onSearchTextChange = {},
//        onClearSearch = {}
//    )
//
//}