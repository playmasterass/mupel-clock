package com.mupel.clock.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mupel.clock.viewmodel.StopwatchViewModel

@Composable
fun StopwatchScreen(
    navController: NavHostController,
    viewModel: StopwatchViewModel = hiltViewModel()
) {
    val elapsedTime by viewModel.elapsedTime.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    val lapTimes by viewModel.lapTimes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back Button & Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Stopwatch",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Box(modifier = Modifier.size(48.dp))
        }

        // Time Display
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(32.dp)
                .padding(top = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = viewModel.formatTime(elapsedTime),
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        // Control Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.start() },
                enabled = !isRunning
            ) {
                Text("Start")
            }

            Button(
                onClick = { viewModel.pause() },
                enabled = isRunning
            ) {
                Text("Pause")
            }

            Button(
                onClick = { viewModel.resume() },
                enabled = !isRunning && elapsedTime > 0
            ) {
                Text("Resume")
            }
        }

        // Lap and Reset Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.recordLap() },
                enabled = isRunning
            ) {
                Text("Lap")
            }

            Button(
                onClick = { viewModel.reset() }
            ) {
                Text("Reset")
            }
        }

        // Lap Times List
        if (lapTimes.isNotEmpty()) {
            Text(
                text = "Lap Times",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 24.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                items(lapTimes.reversed().withIndex().toList()) { (index, lapTime) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Lap ${lapTimes.size - index}",
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = viewModel.formatTime(lapTime),
                            textAlign = TextAlign.End
                        )
                    }
                    if (index < lapTimes.size - 1) {
                        Divider()
                    }
                }
            }
        }
    }
}
