package com.caleb.novamoney.model

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.ui.theme.screens.settings.SettingsScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

// Define the events that the ViewModel can emit
sealed class SettingsEvent {
    object AccountClicked : SettingsEvent()
    object NotificationsClicked : SettingsEvent()
    object LogoutClicked : SettingsEvent()
    // You can add more events here if needed (e.g., HelpClicked, AboutClicked)
}

class SettingsViewModel : ViewModel() {

    // A SharedFlow to emit events to the UI
    private val _events = MutableSharedFlow<SettingsEvent>()
    val events = _events.asSharedFlow()

    // Trigger when Account is clicked
    fun onAccountClick() {
        viewModelScope.launch {
            _events.emit(SettingsEvent.AccountClicked)
        }
    }

    // Trigger when Notifications is clicked
    fun onNotificationsClick() {
        viewModelScope.launch {
            _events.emit(SettingsEvent.NotificationsClicked)
        }
    }

    // Trigger when Logout is clicked
    fun onLogoutClick() {
        viewModelScope.launch {
            _events.emit(SettingsEvent.LogoutClicked)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    MaterialTheme {
        SettingsScreen(rememberNavController())
    }
}
