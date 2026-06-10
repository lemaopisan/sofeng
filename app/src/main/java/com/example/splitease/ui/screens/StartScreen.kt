package com.example.splitease.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.res.stringResource
import com.example.splitease.R
import com.example.splitease.navigation.Screen
import com.example.splitease.ui.theme.SplitEaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6B9BFA), // Light blue
                        Color(0xFF8FB8FF)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            // Enhanced Receipt Icon Graphic with Checkmark
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(80.dp))
                    .background(Color(0xFF4C84FA)),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.size(90.dp)) {
                    Icon(
                        imageVector = Icons.Default.ReceiptLong,
                        contentDescription = stringResource(R.string.receipt_logo_desc),
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.White
                    )
                    // Checkmark overlay
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = 8.dp, y = 8.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(Color(0xFF8DCB8B)), // Light green
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = stringResource(R.string.check_icon_desc),
                            modifier = Modifier.size(24.dp),
                            tint = Color(0xFF6B9BFA) // Darker green/blue inside
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = stringResource(R.string.app_name),
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(64.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(stringResource(R.string.enter_name), color = Color.White) },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { 
                    if (name.isNotBlank()) {
                        val sharedPref = context.getSharedPreferences("SplitEasePrefs", Context.MODE_PRIVATE)
                        sharedPref.edit().putString("user_name", name).apply()
                        navController.navigate(Screen.Splash.route) {
                            popUpTo(Screen.Start.route) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0F4FF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(stringResource(R.string.get_started), color = Color(0xFF4C84FA), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    SplitEaseTheme {
        StartScreen(navController = rememberNavController())
    }
}
