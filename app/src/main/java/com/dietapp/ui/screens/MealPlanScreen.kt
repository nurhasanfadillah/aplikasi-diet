package com.dietapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Breakfast
import androidx.compose.material.icons.filled.Dinner
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dietapp.ui.theme.*

data class Meal(
    val name: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int
)

data class MealCategory(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val meals: List<Meal>,
    val targetCalories: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealPlanScreen() {
    val mealCategories = remember {
        listOf(
            MealCategory(
                title = "Sarapan",
                icon = Icons.Default.Breakfast,
                color = OrangePrimary,
                targetCalories = 400,
                meals = listOf(
                    Meal("Oatmeal dengan Buah", 320, 12, 58, 6),
                    Meal("Telur Rebus (2 butir)", 140, 12, 1, 10)
                )
            ),
            MealCategory(
                title = "Makan Siang",
                icon = Icons.Default.LunchDining,
                color = GreenPrimary,
                targetCalories = 600,
                meals = listOf(
                    Meal("Nasi Merah (1 cup)", 220, 5, 45, 2),
                    Meal("Ayam Panggang (100g)", 165, 31, 0, 4),
                    Meal("Sayur Bayam", 23, 3, 4, 0)
                )
            ),
            MealCategory(
                title = "Makan Malam",
                icon = Icons.Default.Dinner,
                color = BluePrimary,
                targetCalories = 500,
                meals = listOf(
                    Meal("Ikan Salmon (100g)", 208, 22, 0, 13),
                    Meal("Kentang Rebus (150g)", 87, 2, 20, 0),
                    Meal("Brokoli Kukus", 34, 3, 7, 0)
                )
            )
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
                text = "Rencana Makan Hari Ini",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }
        
        item {
            // Daily Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = GreyLight)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    NutritionSummary("Kalori", "1,250", "/ 2,000", OrangePrimary)
                    NutritionSummary("Protein", "85g", "/ 120g", GreenPrimary)
                    NutritionSummary("Karbo", "135g", "/ 200g", BluePrimary)
                    NutritionSummary("Lemak", "35g", "/ 60g", RedPrimary)
                }
            }
        }
        
        items(mealCategories) { category ->
            MealCategoryCard(category = category)
        }
        
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun NutritionSummary(
    label: String,
    current: String,
    target: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
        )
        Row(
            verticalAlignment = Alignment.Baseline
        ) {
            Text(
                text = current,
                style = MaterialTheme.typography.titleMedium,
                color = color,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = target,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealCategoryCard(category: MealCategory) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = category.title,
                        tint = category.color,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = category.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
                
                val totalCalories = category.meals.sumOf { it.calories }
                Text(
                    text = "$totalCalories / ${category.targetCalories} kal",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (totalCalories <= category.targetCalories) GreenPrimary else RedPrimary,
                    fontWeight = FontWeight.Medium
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Meals List
            category.meals.forEach { meal ->
                MealItem(meal = meal)
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            // Add Meal Button
            OutlinedButton(
                onClick = { /* TODO: Add meal */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = category.color
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Makanan",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Tambah Makanan")
            }
        }
    }
}

@Composable
fun MealItem(meal: Meal) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = meal.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = TextPrimary
            )
            Text(
                text = "P: ${meal.protein}g • K: ${meal.carbs}g • L: ${meal.fat}g",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
        
        Text(
            text = "${meal.calories} kal",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = OrangePrimary
        )
    }
}