package com.example.splitease.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
fun TotalPaymentScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Total Payment", fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Box(modifier = Modifier.padding(24.dp).fillMaxWidth()) {
                Button(
                    onClick = { navController.navigate(Screen.PaymentCompleted.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(4.dp, RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C84FA)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Complete Payment", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        },
        containerColor = Color.White
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            PaymentRow("Total Bill", "Rp 150.000", isBold = true)
            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray)
            PaymentRow("Tax", "Rp 10.000")
            PaymentRow("Service", "Rp 5.000")
            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray)
            PaymentRow("Total", "Rp 165.000", isBold = true, size = 20)
            
            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.Black)
            
            PaymentRow("Jonathan", "Rp 55.000", isBold = true)
            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray)
            PaymentRow("Michael", "Rp 55.000", isBold = true)
            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray)
            PaymentRow("Sarah", "Rp 55.000", isBold = true)

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Your Total", fontSize = 16.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Rp 55.000", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Includes tax & services", fontSize = 14.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun PaymentRow(label: String, value: String, isBold: Boolean = false, size: Int = 18) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = size.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            color = if (isBold) Color(0xFF1E3A8A) else Color.DarkGray
        )
        Text(
            text = value,
            fontSize = size.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Medium,
            color = if (isBold) Color.Black else Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TotalPaymentScreenPreview() {
    SplitEaseTheme {
        TotalPaymentScreen(navController = rememberNavController())
    }
}
