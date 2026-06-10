package com.example.splitease.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splitease.navigation.Screen
import com.example.splitease.ui.theme.SplitEaseTheme
import androidx.compose.foundation.background

data class ReceiptItem(
    val id: Int,
    val name: String,
    val price: String,
    val assignedTo: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectItemsScreen(navController: NavController, roomId: String = "SPLIT-123") {
    val context = LocalContext.current
    val roomCode = roomId

    // Mock Data: Simulating the output from Google Vision OCR after parsing
    val mockOcrData = listOf(
        ReceiptItem(1, "Burger", "Rp 45.000", "Sarah"),
        ReceiptItem(2, "Pizza", "Rp 80.000", "Jonathan"),
        ReceiptItem(3, "Drinks", "Rp 25.000", "Michael")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Select Items", fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A)) },
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
                    onClick = { navController.navigate(Screen.TotalPayment.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(4.dp, RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C84FA)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Confirm Split", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        },
        containerColor = Color.White
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            
            // Session Link Card
            Card(
                modifier = Modifier.fillMaxWidth().shadow(2.dp, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F4FF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Room: $roomCode", fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A), fontSize = 16.sp)
                        Text(text = "Invite friends to pick items", fontSize = 14.sp, color = Color.Gray)
                    }
                    IconButton(
                        onClick = { 
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, "Join SplitEase Session")
                                putExtra(Intent.EXTRA_TEXT, "Join my SplitEase session to pick your items! Room Code: $roomCode\n\nLink: https://splitease.app/join/$roomCode")
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share Room Link"))
                        }, 
                        modifier = Modifier.background(Color.White, RoundedCornerShape(8.dp)).size(40.dp)
                    ) {
                        Icon(Icons.Default.Share, contentDescription = "Share", tint = Color(0xFF4C84FA), modifier = Modifier.size(20.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Extracted from receipt:",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(mockOcrData) { item ->
                    ItemCard(name = item.name, assignedTo = item.assignedTo, price = item.price)
                }
                item { Spacer(modifier = Modifier.height(80.dp)) } // Padding for bottom bar
            }
        }
    }
}

@Composable
fun ItemCard(name: String, assignedTo: String, price: String) {
    var checked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF4C84FA))
            )
            Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Row {
                    Text(text = "Assigned to: ", fontSize = 14.sp, color = Color.Gray)
                    Text(text = assignedTo, fontSize = 14.sp, color = Color(0xFF22C55E))
                }
            }
            Text(text = price, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectItemsScreenPreview() {
    SplitEaseTheme {
        SelectItemsScreen(navController = rememberNavController())
    }
}
