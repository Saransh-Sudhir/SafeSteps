package com.safesteps.app.screens

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.safesteps.app.ui.theme.EmergencyRed
import com.safesteps.app.ui.theme.EmergencyRedDark

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    var isAlertTriggered by remember { mutableStateOf(false) }

    val smsPermissionState = rememberPermissionState(
        permission = Manifest.permission.SEND_SMS
    )
    val locationPermissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )

    val scale by animateFloatAsState(
        targetValue = if (isAlertTriggered) 0.95f else 1f,
        label = "sos_scale"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text(
                text = "SafeSteps",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Your Personal Safety Companion",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // SOS Button
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    isAlertTriggered = true
                    triggerSOSAlert(context, smsPermissionState.status.isGranted)
                    // Reset after animation
                    android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                        isAlertTriggered = false
                    }, 200)
                },
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = EmergencyRed
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 16.dp
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "SOS",
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "SOS",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "TAP FOR HELP",
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Quick Actions Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Button(
                    onClick = { callEmergencyServices(context) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EmergencyRedDark
                    )
                ) {
                    Text("Call Emergency Services (112)")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { shareLocation(context) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = locationPermissionState.status.isGranted
                ) {
                    Text(
                        if (locationPermissionState.status.isGranted)
                            "Share Live Location"
                        else
                            "Enable Location Permission"
                    )
                }
            }
        }
    }
}

private fun triggerSOSAlert(context: Context, hasSmsPermission: Boolean) {
    Toast.makeText(context, "SOS Alert Triggered!", Toast.LENGTH_LONG).show()

    // In a real implementation, this would:
    // 1. Get current location
    // 2. Send SMS to emergency contacts
    // 3. Optionally call emergency services

    if (hasSmsPermission) {
        // Send SMS to emergency contacts (placeholder)
        // This would be implemented with actual contact data from DataStore
        Toast.makeText(context, "Alert sent to emergency contacts", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "SMS permission required to send alerts", Toast.LENGTH_LONG).show()
    }
}

private fun callEmergencyServices(context: Context) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:112")
    }
    context.startActivity(intent)
}

private fun shareLocation(context: Context) {
    // In a real implementation, this would:
    // 1. Get current location
    // 2. Create a shareable link or message
    // 3. Open share dialog

    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "My Location - SafeSteps")
        putExtra(Intent.EXTRA_TEXT, "I'm sharing my location with you via SafeSteps app. " +
                "Location: [Latitude, Longitude] - Map: https://maps.google.com/?q=0,0")
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share Location"))
}