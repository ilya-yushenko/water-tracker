package com.yushenko.watertracker.ui.screens.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorBackground
import com.yushenko.watertracker.theme.ColorBlue
import com.yushenko.watertracker.theme.ColorGrayDark
import com.yushenko.watertracker.theme.ColorGrayWhite
import com.yushenko.watertracker.theme.ColorWhite
import com.yushenko.watertracker.ui.components.BarChartItem
import com.yushenko.watertracker.ui.components.ChartData
import com.yushenko.watertracker.ui.components.DiagramModel
import com.yushenko.watertracker.ui.components.DonutChartWithLegend
import com.yushenko.watertracker.ui.components.HeaderScreen
import com.yushenko.watertracker.ui.components.StatisticsDetailsItem
import com.yushenko.watertracker.ui.components.TypeDrinkItem
import com.yushenko.watertracker.ui.components.TypeDrinkModel
import com.yushenko.watertracker.ui.components.TypePeriodItem
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import watertracker.composeapp.generated.resources.Inter_SemiBold
import watertracker.composeapp.generated.resources.Res
import watertracker.composeapp.generated.resources.ic_left
import watertracker.composeapp.generated.resources.ic_quick_coffee
import watertracker.composeapp.generated.resources.ic_quick_juice
import watertracker.composeapp.generated.resources.ic_quick_milk
import watertracker.composeapp.generated.resources.ic_quick_smoothie
import watertracker.composeapp.generated.resources.ic_quick_soda
import watertracker.composeapp.generated.resources.ic_quick_tea
import watertracker.composeapp.generated.resources.ic_quick_water
import watertracker.composeapp.generated.resources.ic_right
import watertracker.composeapp.generated.resources.ic_statistics_average
import watertracker.composeapp.generated.resources.ic_statistics_best
import watertracker.composeapp.generated.resources.ic_statistics_goal
import watertracker.composeapp.generated.resources.ic_statistics_measuring
import watertracker.composeapp.generated.resources.statistics_screen_all
import watertracker.composeapp.generated.resources.statistics_screen_average
import watertracker.composeapp.generated.resources.statistics_screen_best_day
import watertracker.composeapp.generated.resources.statistics_screen_goal
import watertracker.composeapp.generated.resources.statistics_screen_in_just_one
import watertracker.composeapp.generated.resources.statistics_screen_month
import watertracker.composeapp.generated.resources.statistics_screen_title
import watertracker.composeapp.generated.resources.statistics_screen_week
import watertracker.composeapp.generated.resources.statistics_screen_year

enum class PeriodType {
    Week, Month, Year
}

enum class DrinkType {
    All, Water, Coffee, Tea, Smoothie, Juice, Milk, Soda
}

@Composable
fun StatisticsScreen() {

    val drinks = listOf(
        TypeDrinkModel(stringResource(Res.string.statistics_screen_all), DrinkType.All),
        TypeDrinkModel("Water", DrinkType.Water, iconRes = Res.drawable.ic_quick_water),
        TypeDrinkModel("Coffee", DrinkType.Coffee, iconRes = Res.drawable.ic_quick_coffee),
        TypeDrinkModel("Tea", DrinkType.Tea, iconRes = Res.drawable.ic_quick_tea),
        TypeDrinkModel("Smoothie", DrinkType.Smoothie, iconRes = Res.drawable.ic_quick_smoothie),
        TypeDrinkModel("Juice", DrinkType.Juice, iconRes = Res.drawable.ic_quick_juice),
        TypeDrinkModel("Milk", DrinkType.Milk, iconRes = Res.drawable.ic_quick_milk),
        TypeDrinkModel("Soda", DrinkType.Soda, iconRes = Res.drawable.ic_quick_soda)
    )

    val statisticsWeek = listOf(
        DiagramModel(value = 1200, header = "Mon"),
        DiagramModel(value = 1500, header = "Tue"),
        DiagramModel(value = 800, header = "Wed"),
        DiagramModel(value = 1600, header = "Thu"),
        DiagramModel(value = 1400, header = "Fri"),
        DiagramModel(value = 1500, header = "Sat"),
        DiagramModel(value = 1300, header = "Sun"),
    )

    var selectedPeriodType by remember { mutableStateOf(PeriodType.Week) }
    var selectedDrinkType by remember { mutableStateOf(DrinkType.All) }

    val chartData = listOf(
        ChartData("Water", 65f, Color(0xFF2B7FFF)),
        ChartData("Coffee", 15f, Color(0xFF6D4C41)),
        ChartData("Tea", 10f, Color(0xFFFFA000)),
        ChartData("Juice", 7f, Color(0xFF66BB6A)),
        ChartData("Other", 3f, Color(0xFFAB47BC))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
    ) {
        HeaderScreen(
            label = stringResource(Res.string.statistics_screen_title),
            modifier = Modifier.height(106.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 36.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ColorWhite)
                ) {
                    Spacer(modifier = Modifier.height(36.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TypePeriodItem(
                            label = stringResource(Res.string.statistics_screen_week),
                            type = PeriodType.Week,
                            isSelected = selectedPeriodType == PeriodType.Week,
                            modifier = Modifier.weight(1f),
                            onClick = { type -> selectedPeriodType = type }
                        )
                        TypePeriodItem(
                            label = stringResource(Res.string.statistics_screen_month),
                            type = PeriodType.Month,
                            isSelected = selectedPeriodType == PeriodType.Month,
                            modifier = Modifier.weight(1f),
                            onClick = { type -> selectedPeriodType = type }
                        )
                        TypePeriodItem(
                            label = stringResource(Res.string.statistics_screen_year),
                            type = PeriodType.Year,
                            isSelected = selectedPeriodType == PeriodType.Year,
                            modifier = Modifier.weight(1f),
                            onClick = { type -> selectedPeriodType = type }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_left),
                                contentDescription = "Minus",
                                tint = ColorBlue,
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(2.dp)
                            )
                        }
                        Text(
                            text = "Feb 17 - Feb 22",
                            fontSize = 16.sp,
                            color = ColorGrayDark,
                            fontFamily = FontFamily(Font(Res.font.Inter_SemiBold))
                        )
                        IconButton(onClick = {

                        }) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_right),
                                contentDescription = "Minus",
                                tint = ColorGrayWhite,
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(2.dp)
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        itemsIndexed(drinks, key = { _, item -> item.label }) { index, model ->
                            TypeDrinkItem(
                                model = model,
                                isSelected = selectedDrinkType == model.type,
                                onClick = { type -> selectedDrinkType = type }
                            )
                            if (index < drinks.size - 1) {
                                Spacer(modifier = Modifier.width(12.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Box {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 16.dp)
                        ) {
                            itemsIndexed(statisticsWeek) { index, model ->
                                BarChartItem(model = model)
                                if (index < statisticsWeek.size - 1) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            item {

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    StatisticsDetailsItem(
                        title = stringResource(Res.string.statistics_screen_average),
                        data = "2.1L",
                        iconRes = Res.drawable.ic_statistics_average,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    StatisticsDetailsItem(
                        title = stringResource(Res.string.statistics_screen_best_day),
                        data = "Tue (2.5L)",
                        iconRes = Res.drawable.ic_statistics_best,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    StatisticsDetailsItem(
                        title = stringResource(Res.string.statistics_screen_in_just_one),
                        data = "2.1L",
                        iconRes = Res.drawable.ic_statistics_measuring,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    StatisticsDetailsItem(
                        title = stringResource(Res.string.statistics_screen_goal),
                        data = "5/7 days",
                        iconRes = Res.drawable.ic_statistics_goal,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
                DonutChartWithLegend(data = chartData)
            }
        }
    }
}