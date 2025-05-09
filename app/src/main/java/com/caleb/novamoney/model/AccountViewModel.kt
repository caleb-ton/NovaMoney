package com.caleb.novamoney.model



import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.ui.theme.screens.account.AccountScreenContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class UserProfile(
    val name: String,
    val email: String,
    val username: String,
    val accountId: String,
    val profileImageRes: Int // Resource ID for the profile picture
)

data class SettingsSection(
    val title: String,
    val options: List<String>
)

class Account_ViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow(
        UserProfile(
            name = "John Doe",
            email = "john.doe@example.com",
            username = "@johndoe",
            accountId = "123456789",
            profileImageRes = com.caleb.novamoney.R.drawable.profile
        )
    )
    val userProfile: StateFlow<UserProfile> = _userProfile

    private val _settingsSections = MutableStateFlow(
        listOf(
            SettingsSection(
                title = "Account Settings",
                options = listOf(
                    "Change Password",
                    "Manage Payment Methods",
                    "Subscription & Plan Info",
                    "Linked Accounts"
                )
            ),
            SettingsSection(
                title = "Preferences",
                options = listOf(
                    "Language & Region",
                    "Notification Preferences",
                    "Privacy Settings",
                    "Theme",
                    "Data & Personalization"
                )
            ),
            SettingsSection(
                title = "Security",
                options = listOf(
                    "Two-Factor Authentication",
                    "Login Activity / Devices",
                    "Deactivate / Delete Account"
                )
            )
        )
    )
    val settingsSections: StateFlow<List<SettingsSection>> = _settingsSections

    // Optional: Function to update user profile (e.g., after editing)
    fun updateProfile(newProfile: UserProfile) {
        _userProfile.value = newProfile
    }
}


@Preview(showBackground = true)
@Composable
fun AccountScreenWithViewModelPreview() {
    val navController = rememberNavController()
    AccountScreenContent(
        navController = navController,

    )
}

