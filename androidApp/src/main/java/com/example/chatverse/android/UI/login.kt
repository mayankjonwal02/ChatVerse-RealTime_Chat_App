package com.example.chatverse.android.UI

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Authentication() {


    var currentscreen = remember {
        mutableStateOf("login")
    }
    CustomBackground()
    {
        Crossfade(targetState = currentscreen.value, label = "") {
            it ->
            if(it == "login")
            {
                Login(currentscreen)
            }
            else
            {
                Signup(currentscreen)
            }
        }
    }
}

@Composable
fun Login(currentscreen: MutableState<String>) {


    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var showpassword by remember {
        mutableStateOf(false)
    }

    var context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent)
        .padding(30.dp),
        contentAlignment = Alignment.Center)
    {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.3f))
        )
        {
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Transparent)
                .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top)
            {
                Text(text = "Login", color = Color.Magenta, fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Default, fontStyle = FontStyle.Normal)
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    modifier = Modifier.padding(16.dp),
                    placeholder = { Text("Email") },
                    label = { Text(text = "Email")},
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),// Placeholder text
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Blue, // Color when the field is focused
                        unfocusedBorderColor = Color.Gray // Color when the field is not focused
                    )
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    trailingIcon = { Icon( modifier = Modifier.clickable {
                        showpassword = !showpassword
                    },imageVector = if(showpassword){ Icons.Default.VisibilityOff }else{Icons.Default.Visibility}, contentDescription ="" )},
                    modifier = Modifier.padding(16.dp),
                    visualTransformation = if(!showpassword)
                    {
                        PasswordVisualTransformation()
                    }
                    else
                    {
                        VisualTransformation.None
                    },
                    placeholder = { Text("Password") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    label = { Text(text = "Password")},// Placeholder text
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Blue, // Color when the field is focused
                        unfocusedBorderColor = Color.Gray // Color when the field is not focused
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {

                                 FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                                     .addOnSuccessListener{
                                         Toast.makeText(context,"SignIn Successful",Toast.LENGTH_SHORT).show()

                                     }
                                     .addOnFailureListener {
                                         error ->
                                         Toast.makeText(context,error.message,Toast.LENGTH_SHORT).show()
                                     }

                                 } , colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Magenta,
                    contentColor = Color.White
                )) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription ="" )
//                    Text(text = "Login" , fontSize = 25.sp, color = Color.White , fontWeight = FontWeight.ExtraBold )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Not Registered Yet! Signup" , color = Color.Magenta , fontWeight = FontWeight.ExtraBold, modifier = Modifier.clickable { currentscreen.value = "signup" } )
            }
        }
    }


}

@Composable
fun CustomBackground(content : @Composable () -> Unit) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Magenta,
                    Color.White
                )
            )
        ))
    {
        content()
    }

}