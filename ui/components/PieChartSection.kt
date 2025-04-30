package com.example.fitnessapp3.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PieChartSection() {
    val activityData = listOf(
        ActivityEntry("Walking", 40f, Color(0xFF8E24AA)),
        ActivityEntry("Running", 30f, Color(0xFFBA68C8)),
        ActivityEntry("Cycling", 20f, Color(0xFFD1C4E9)),
        ActivityEntry("Other", 10f, Color(0xFFF3E5F5))
    )
//STATIC PIE CHART
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Activity Breakdown",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF6A1B9A)
                )
            )

            Canvas(modifier = Modifier.size(140.dp)) {
                val total = activityData.sumOf { it.value.toDouble() }.toFloat()
                var startAngle = -90f

                activityData.forEach { entry ->
                    val sweepAngle = 360 * (entry.value / total)
                    drawArc(
                        color = entry.color,
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = true,
                        topLeft = Offset.Zero,
                        size = Size(size.width, size.height)
                    )
                    startAngle += sweepAngle
                }
            }

            // Simple Legend
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                activityData.forEach {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(it.color)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(it.label, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

data class ActivityEntry(val label: String, val value: Float, val color: Color)