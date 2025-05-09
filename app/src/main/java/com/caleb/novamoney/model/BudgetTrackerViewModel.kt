package com.caleb.novamoney.model



import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.caleb.novamoney.R
import com.caleb.novamoney.navigation.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// User profile data model
data class Profile(
    val name: String,
    val email: String,
    val username: String,
    val accountId: String,
    val profileImageRes: Int // Resource ID for the profile picture
)

// Settings section data model
data class Settings(
    val title: String,
    val options: List<String>
)

// ViewModel that manages the user profile and settings data
class AccountViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow(
        UserProfile(
            name = "John Doe",
            email = "john.doe@example.com",
            username = "@johndoe",
            accountId = "123456789",
            profileImageRes = R.drawable.profile // Make sure the resource exists
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

    // Function to update user profile (e.g., after editing)
    fun updateProfile(newProfile: UserProfile) {
        _userProfile.value = newProfile
    }
}

// Composable for the Account Screen content
@Composable
fun AccountScreenContent(
    navController: NavController,
    viewModel: AccountViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val userProfile by viewModel.userProfile.collectAsState()
    val settingsSections by viewModel.settingsSections.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Profile Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = userProfile.profileImageRes),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(userProfile.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(userProfile.email, fontSize = 14.sp)
                Text(userProfile.username, fontSize = 14.sp)
                Text(
                    "Account ID: ${userProfile.accountId}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { navController.navigate(ROUTE_EDIT_PROFILE) }) {
                    Text("Edit Profile")
                }
            }
        }

        // Settings Sections
        settingsSections.forEach { section ->
            SettingsCard(title = section.title, options = section.options)
        }

        // Navigation Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.navigate(ROUTE_INVESTMENT) }, modifier = Modifier.weight(1f)) {
                Text("Investment")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { navController.navigate(ROUTE_BUDGET_TRACKER) }, modifier = Modifier.weight(1f)) {
                Text("Budget Tracker")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.navigate(ROUTE_TRANSACTION) }, modifier = Modifier.weight(1f)) {
                Text("Transaction")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { navController.navigate(ROUTE_PROFILE) }, modifier = Modifier.weight(1f)) {
                Text("Profile")
            }
        }
    }
}

// Composable for displaying settings options in a card
@Composable
fun SettingsCard(title: String, options: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            options.forEach { option ->
                Button(
                    onClick = { /* Handle click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(option)
                }
            }
        }
    }
}

@Preview
@Composable
private fun AccountViewModelPrev() {
    AccountScreenContent(rememberNavController())
}


