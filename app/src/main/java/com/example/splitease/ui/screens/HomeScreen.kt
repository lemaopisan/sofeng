package com.example.splitease.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splitease.navigation.Screen
import com.example.splitease.ui.theme.SplitEaseTheme

data class SplitHistoryItem(
    val title: String,
    val status: String,
    val statusColor: Color,
    val amount: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    // Safely get SharedPreferences in the main composable
    val sharedPref = remember { context.getSharedPreferences("SplitEasePrefs", Context.MODE_PRIVATE) }
    val name = remember { sharedPref.getString("user_name", "User") ?: "User" }

    // Placeholder history items. Set to empty list to show empty state.
    val historyItems = listOf<SplitHistoryItem>()

    HomeScreenContent(navController = navController, name = name, historyItems = historyItems)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    navController: NavController,
    name: String,
    historyItems: List<SplitHistoryItem>
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("SplitEase", fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A)) },
                navigationIcon = {
                    // Placeholder icon or logo can go here if needed
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(horizontal = 24.dp).fillMaxSize()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Hi, $name \uD83D\uDC4B", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1E3A8A))
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = { navController.navigate(Screen.ScanReceipt.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C84FA)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Create New Split", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text("Recent Splits", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
            
            if (historyItems.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.ReceiptLong,
                            contentDescription = "Empty History",
                            modifier = Modifier.size(64.dp),
                            tint = Color.LightGray
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No recent splits yet.",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                        Text(
                            text = "Tap 'Create New Split' to get started!",
                            fontSize = 14.sp,
                            color = Color.LightGray,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(historyItems) { item ->
                        SplitHistoryCard(
                            title = item.title,
                            status = item.status,
                            statusColor = item.statusColor,
                            amount = item.amount
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SplitHistoryCard(title: String, status: String, statusColor: Color, amount: String) {
    Card(
        modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(text = "Status: ", fontSize = 14.sp, color = Color.Gray)
                    Text(text = status, fontSize = 14.sp, color = statusColor, fontWeight = FontWeight.Medium)
                }
            }
            Text(text = amount, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SplitEaseTheme {
        HomeScreenContent(
            navController = rememberNavController(),
            name = "Jonathan",
            historyItems = emptyList() // Previewing the empty state
        )
    }
}
