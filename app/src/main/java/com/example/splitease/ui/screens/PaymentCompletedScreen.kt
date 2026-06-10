package com.example.splitease.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splitease.navigation.Screen
import com.example.splitease.ui.theme.SplitEaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentCompletedScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Payment Completed", fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            
            // Big Green Checkmark
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF10B981)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Check, contentDescription = "Success", tint = Color.White, modifier = Modifier.size(100.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))
            
            Text("Payment Completed", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A))
            Spacer(modifier = Modifier.height(8.dp))
            Text("Rp55.000", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF10B981))
            Spacer(modifier = Modifier.height(16.dp))
            Text("Sent To:", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("Daniel", fontSize = 28.sp, fontWeight = FontWeight.Normal)
            
            Spacer(modifier = Modifier.height(32.dp))
            Divider(color = Color.Black)
            Spacer(modifier = Modifier.height(16.dp))
            
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Date: 10 March 2026", fontSize = 20.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Time: 14.35 PM", fontSize = 20.sp, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Upload Receipt Logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F5F9)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Upload Receipt", color = Color(0xFF1E3A8A), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { 
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C84FA)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Back to Home", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCompletedScreenPreview() {
    SplitEaseTheme {
        PaymentCompletedScreen(navController = rememberNavController())
    }
}
