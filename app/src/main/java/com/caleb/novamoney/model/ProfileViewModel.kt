package com.caleb.novamoney.model


import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.caleb.novamoney.ui.theme.screens.profile.EditProfileScreenWithBottomBar
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    // Profile information state
    var name = mutableStateOf("John Doe")
    var email = mutableStateOf("johndoe@example.com")
    var profileImageUri = mutableStateOf<Uri?>(null)

    // Function to save profile (simulate saving process)
    fun saveProfile() {
        viewModelScope.launch {
            // Simulating a save to a database or API
            println("Profile saved: Name=${name.value}, Email=${email.value}, ImageUri=${profileImageUri.value}")
        }
    }

    // Function to update name
    fun updateName(newName: String) {
        name.value = newName
    }

    // Function to update email
    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    // Function to update profile image URI
    fun updateProfileImage(uri: Uri?) {
        profileImageUri.value = uri
    }
}


@Preview(showBackground = true)
@Composable
fun EditProfileScreenWithBottomBarPreview() {
    EditProfileScreenWithBottomBar(rememberNavController())
}