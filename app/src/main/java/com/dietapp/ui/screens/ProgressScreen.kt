package com.dietapp.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dietapp.ui.theme.*
import kotlin.math.cos
import kotlin.math.sin

data class WeightEntry(
    val date: String,
    val weight: Float
)

data class ProgressStats(
    val currentWeight: Float,
    val targetWeight: Float,
    val startWeight: Float,
    val weightLost: Float,
    val daysActive: Int,
    val avgCaloriesPerDay: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen() {
    val weightData = remember {
        listOf(
            WeightEntry("1 Jan", 72.5f),
            WeightEntry("8 Jan", 71.8f),
            WeightEntry("15 Jan", 71.2f),
            WeightEntry("22 Jan", 70.5f),
            WeightEntry("29 Jan", 69.8f),
            WeightEntry("5 Feb", 69.2f),
            WeightEntry("12 Feb", 68.5f)
        )
    }
    
    val progressStats = remember {
        ProgressStats(
            currentWeight = 68.5f,
            targetWeight = 65.0f,
            startWeight = 72.5f,
            weightLost = 4.0f,
            daysActive = 42,
            avgCaloriesPerDay = 1850
        )
    }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Progress Diet Anda",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }
        
        item {
            // Current Progress Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = GreenPrimary)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Berat Badan Saat Ini",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        text = "${progressStats.currentWeight} kg",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.TrendingDown,
                            contentDescription = "Turun",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "-${progressStats.weightLost} kg dari awal",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                    }
                }
            }
        }
        
        item {
            // Progress to Target
            val progressPercentage = ((progressStats.startWeight - progressStats.currentWeight) / 
                    (progressStats.startWeight - progressStats.targetWeight)) * 100
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Progress ke Target",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    LinearProgressIndicator(
                        progress = progressPercentage / 100f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(CircleShape),
                        color = GreenPrimary,
                        trackColor = GreyLight
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${progressStats.startWeight} kg",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary
                        )
                        Text(
                            text = "${progressPercentage.toInt()}%",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = GreenPrimary
                        )
                        Text(
                            text = "${progressStats.targetWeight} kg",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary
                        )
                    }
                }
            }
        }
        
        item {
            // Weight Chart
            Text(
                text = "Grafik Berat Badan",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }
        
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                WeightChart(
                    data = weightData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp)
                )
            }
        }
        
        item {
            // Statistics
            Text(
                text = "Statistik",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }
        
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Hari Aktif",
                    value = "${progressStats.daysActive}",
                    subtitle = "hari",
                    color = BluePrimary
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Rata-rata Kalori",
                    value = "${progressStats.avgCaloriesPerDay}",
                    subtitle = "per hari",
                    color = OrangePrimary
                )
            }
        }
        
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Sisa Target",
                    value = "${progressStats.currentWeight - progressStats.targetWeight}",
                    subtitle = "kg lagi",
                    color = RedPrimary
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Rata-rata/Minggu",
                    value = "0.6",
                    subtitle = "kg turun",
                    color = GreenPrimary
                )
            }
        }
        
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    subtitle: String,
    color: Color
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                color = color,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}

@Composable
fun WeightChart(
    data: List<WeightEntry>,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val padding = 40.dp.toPx()
        
        val chartWidth = canvasWidth - 2 * padding
        val chartHeight = canvasHeight - 2 * padding
        
        if (data.isEmpty()) return@Canvas
        
        val minWeight = data.minOf { it.weight }
        val maxWeight = data.maxOf { it.weight }
        val weightRange = maxWeight - minWeight
        
        val points = data.mapIndexed { index, entry ->
            val x = padding + (index.toFloat() / (data.size - 1)) * chartWidth
            val y = padding + chartHeight - ((entry.weight - minWeight) / weightRange) * chartHeight
            Offset(x, y)
        }
        
        // Draw grid lines
        for (i in 0..4) {
            val y = padding + (i.toFloat() / 4) * chartHeight
            drawLine(
                color = Color.Gray.copy(alpha = 0.3f),
                start = Offset(padding, y),
                end = Offset(canvasWidth - padding, y),
                strokeWidth = 1.dp.toPx()
            )
        }
        
        // Draw line chart
        val path = Path()
        points.forEachIndexed { index, point ->
            if (index == 0) {
                path.moveTo(point.x, point.y)
            } else {
                path.lineTo(point.x, point.y)
            }
        }
        
        drawPath(
            path = path,
            color = GreenPrimary,
            style = Stroke(width = 3.dp.toPx())
        )
        
        // Draw points
        points.forEach { point ->
            drawCircle(
                color = GreenPrimary,
                radius = 6.dp.toPx(),
                center = point
            )
            drawCircle(
                color = Color.White,
                radius = 3.dp.toPx(),
                center = point
            )
        }
    }
}