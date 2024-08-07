package com.example.stock.features.auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.stock.features.auth.domain.User

@Composable
fun MyApp(userViewModel: UserViewModel) {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = firstName,
            onValueChange = { firstName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        BasicTextField(
            value = lastName,
            onValueChange = { lastName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Button(
            onClick = {
                userViewModel.addUser(firstName.text, lastName.text)
                firstName = TextFieldValue("")
                lastName = TextFieldValue("")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Add User")
        }
        UserList(users = userViewModel.users)
    }
}

@Composable
fun UserList(users: List<User>) {
    Column {
        for (user in users) {
            Text(text = "${user.firstName} ${user.lastName}", modifier = Modifier.padding(8.dp))
        }
    }
}