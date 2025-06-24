package com.example.karyaseni.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpScreen(
    onOtpVerified: () -> Unit
) {
    var otpCode by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter OTP Code", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = otpCode,
            onValueChange = {
                if (it.length <= 4) otpCode = it
            },
            label = { Text("4-digit OTP") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                // Accept any input as valid
                Toast.makeText(context, "OTP Verified!", Toast.LENGTH_SHORT).show()
                onOtpVerified()
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
            enabled = otpCode.length == 4,
        ) {
            Text("Verify")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OTpScreenPreview() {
    OtpScreen(
        onOtpVerified = {},
    )
}