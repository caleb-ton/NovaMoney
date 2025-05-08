package com.caleb.novamoney.model


import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caleb.novamoney.ui.theme.screens.notification.Notification
import kotlinx.coroutines.launch

class NotificationViewModel : ViewModel() {

    // Notification state list
    private val _notifications = mutableStateOf(
        listOf(
            Notification(1, "New Message Received", "You have a new message from John Doe.", "info", "2 minutes ago"),
            Notification(2, "Payment Successful", "Your payment of $50 has been processed.", "success", "1 hour ago"),
            Notification(3, "Server Downtime", "The server will be down for maintenance at midnight.", "warning", "3 hours ago")
        )
    )
    val notifications: State<List<Notification>> = _notifications

    // Simulate clearing/marking all as read (optional)
    fun clearNotifications() {
        viewModelScope.launch {
            _notifications.value = emptyList()
        }
    }

    // Simulate adding a new notification dynamically (optional)
    fun addNotification(notification: Notification) {
        viewModelScope.launch {
            _notifications.value = _notifications.value + notification
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationViewModel()
//    NotificationScreen()
}

