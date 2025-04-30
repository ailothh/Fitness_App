package com.example.fitnessapp3.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnessapp3.ui.components.PieChartSection
import com.example.fitnessapp3.ui.components.SummaryCard
import com.example.fitnessapp3.ui.viewmodel.HomeViewModel
import com.example.fitnessapp3.ui.viewmodel.HomeViewModelFactory

@Composable
fun HomeScreen(context: Context) {
    //instance viewmadel
    val viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(context))

    //layout
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            //title
            Text(
                "Today's Summary",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))
            PieChartSection()

            Spacer(modifier = Modifier.height(24.dp))
            //dynamic now
            SummaryCard(label = "Steps", value = "${viewModel.steps}")
            SummaryCard(label = "Calories", value = "${viewModel.calories} kcal")
            SummaryCard(label = "Distance", value = "%.1f km".format(viewModel.distance))
        }
    }
}