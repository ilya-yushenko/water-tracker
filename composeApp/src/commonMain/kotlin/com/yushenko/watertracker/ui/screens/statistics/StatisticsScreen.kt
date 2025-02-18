package com.yushenko.watertracker.ui.screens.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import com.yushenko.watertracker.theme.ColorBackground
import com.yushenko.watertracker.theme.ColorBlack
import com.yushenko.watertracker.ui.components.BarChartItem
import com.yushenko.watertracker.ui.components.ChartData
import com.yushenko.watertracker.ui.components.DiagramModel
import com.yushenko.watertracker.ui.components.DonutChartWithLegend
import com.yushenko.watertracker.ui.components.HeaderScreen
import com.yushenko.watertracker.ui.components.StatisticsDetailsItem
import com.yushenko.watertracker.ui.components.TypeDrinkItem
import com.yushenko.watertracker.ui.components.TypeDrinkModel
import com.yushenko.watertracker.ui.components.TypePeriodItem
import com.yushenko.watertracker.ui.components.TypePeriodModel
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import watertracker.composeapp.generated.resources.Inter_SemiBold
import watertracker.composeapp.generated.resources.Res
import watertracker.composeapp.generated.resources.ic_quick_coffee
import watertracker.composeapp.generated.resources.ic_quick_juice
import watertracker.composeapp.generated.resources.ic_quick_milk
import watertracker.composeapp.generated.resources.ic_quick_smoothie
import watertracker.composeapp.generated.resources.ic_quick_soda
import watertracker.composeapp.generated.resources.ic_quick_tea
import watertracker.composeapp.generated.resources.ic_quick_water
import watertracker.composeapp.generated.resources.ic_statistics_average
import watertracker.composeapp.generated.resources.ic_statistics_best
import watertracker.composeapp.generated.resources.ic_statistics_goal
import watertracker.composeapp.generated.resources.ic_statistics_measuring
import watertracker.composeapp.generated.resources.statistics_screen_all
import watertracker.composeapp.generated.resources.statistics_screen_average
import watertracker.composeapp.generated.resources.statistics_screen_best_day
import watertracker.composeapp.generated.resources.statistics_screen_by_last_days
import watertracker.composeapp.generated.resources.statistics_screen_day
import watertracker.composeapp.generated.resources.statistics_screen_goal
import watertracker.composeapp.generated.resources.statistics_screen_in_just_one
import watertracker.composeapp.generated.resources.statistics_screen_month
import watertracker.composeapp.generated.resources.statistics_screen_title
import watertracker.composeapp.generated.resources.statistics_screen_week
import watertracker.composeapp.generated.resources.statistics_screen_year

@Composable
fun StatisticsScreen() {
    val periods = listOf(
        TypePeriodModel(stringResource(Res.string.statistics_screen_day), true),
        TypePeriodModel(stringResource(Res.string.statistics_screen_week)),
        TypePeriodModel(stringResource(Res.string.statistics_screen_month)),
        TypePeriodModel(stringResource(Res.string.statistics_screen_year))
    )

    val drinks = listOf(
        TypeDrinkModel(stringResource(Res.string.statistics_screen_all), true),
        TypeDrinkModel("Wather", iconRes = Res.drawable.ic_quick_water),
        TypeDrinkModel("Coffe", iconRes = Res.drawable.ic_quick_coffee),
        TypeDrinkModel("Tea", iconRes = Res.drawable.ic_quick_tea),
        TypeDrinkModel("Smoothie", iconRes = Res.drawable.ic_quick_smoothie),
        TypeDrinkModel("Juice", iconRes = Res.drawable.ic_quick_juice),
        TypeDrinkModel("Milk", iconRes = Res.drawable.ic_quick_milk),
        TypeDrinkModel("Soda", iconRes = Res.drawable.ic_quick_soda)
    )

    val statisticsDay = listOf(
        DiagramModel(
            value = 1200,
            header = "00:00"
        ),
        DiagramModel(
            value = 1500,
            header = "01:00"
        ),
        DiagramModel(
            value = 800,
            header = "02:00"
        ),
        DiagramModel(
            value = 1600,
            header = "03:00"
        ),
        DiagramModel(
            value = 1400,
            header = "04:00"
        ),
        DiagramModel(
            value = 1500,
            header = "05:00"
        ),
        DiagramModel(
            value = 1300,
            header = "06:00"
        ),
        DiagramModel(
            value = 1200,
            header = "07:00"
        ),
        DiagramModel(
            value = 1500,
            header = "08:00"
        ),
        DiagramModel(
            value = 800,
            header = "09:00"
        ),
        DiagramModel(
            value = 1600,
            header = "10:00"
        ),
        DiagramModel(
            value = 1400,
            header = "11:00"
        ),
        DiagramModel(
            value = 1500,
            header = "12:00"
        ),
        DiagramModel(
            value = 1300,
            header = "13:00"
        ),
        DiagramModel(
            value = 1200,
            header = "14:00"
        ),
        DiagramModel(
            value = 1500,
            header = "15:00"
        ),
        DiagramModel(
            value = 800,
            header = "16:00"
        ),
        DiagramModel(
            value = 1600,
            header = "17:00"
        ),
        DiagramModel(
            value = 1400,
            header = "18:00"
        ),
        DiagramModel(
            value = 1500,
            header = "19:00"
        ),
        DiagramModel(
            value = 1300,
            header = "20:00"
        ),
        DiagramModel(
            value = 1200,
            header = "21:00"
        ),
        DiagramModel(
            value = 1500,
            header = "22:00"
        ),
        DiagramModel(
            value = 800,
            header = "23:00"
        )
    )

    val statisticsWeek = listOf(
        DiagramModel(
            value = 1200,
            header = "Mon"
        ),
        DiagramModel(
            value = 1500,
            header = "Tue"
        ),
        DiagramModel(
            value = 800,
            header = "Wed"
        ),
        DiagramModel(
            value = 1600,
            header = "Thu"
        ),
        DiagramModel(
            value = 1400,
            header = "Fri"
        ),
        DiagramModel(
            value = 1500,
            header = "Sat"
        ),
        DiagramModel(
            value = 1300,
            header = "Sun"
        ),
    )

    val statisticsMonth = listOf(
        DiagramModel(
            value = 1200,
            header = "Mon"
        ),
        DiagramModel(
            value = 1500,
            header = "Tue"
        ),
        DiagramModel(
            value = 800,
            header = "Wed"
        ),
        DiagramModel(
            value = 1600,
            header = "Thu"
        ),
        DiagramModel(
            value = 1400,
            header = "Fri"
        ),
        DiagramModel(
            value = 1500,
            header = "Sat"
        ),
        DiagramModel(
            value = 1300,
            header = "Sun"
        ),
        DiagramModel(
            value = 1200,
            header = "Mon"
        ),
        DiagramModel(
            value = 1500,
            header = "Tue"
        ),
        DiagramModel(
            value = 800,
            header = "Wed"
        ),
        DiagramModel(
            value = 1600,
            header = "Thu"
        ),
        DiagramModel(
            value = 1400,
            header = "Fri"
        ),
        DiagramModel(
            value = 1500,
            header = "Sat"
        ),
        DiagramModel(
            value = 1300,
            header = "Sun"
        )
    )

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
            contentPadding = PaddingValues(vertical = 36.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    itemsIndexed(periods) { index, model ->
                        TypePeriodItem(
                            model = model,
                            onClick = { model ->

                            }
                        )
                        if (index < periods.size - 1) {
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(Res.string.statistics_screen_by_last_days),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(Font(Res.font.Inter_SemiBold)),
                    color = ColorBlack,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    itemsIndexed(drinks) { index, model ->
                        TypeDrinkItem(
                            model = model,
                            onClick = { model ->

                            }
                        )
                        if (index < drinks.size - 1) {
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

//                BarChartWithCalculatedSpacing(statisticsDay)
//                BarChartWithCalculatedSpacing(statisticsWeek)
//                AdaptiveBarChart(data = chartData)

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

@Composable
fun BarChartWithCalculatedSpacing(statistics: List<DiagramModel>) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val screenWidthPx = (maxWidth.value - 32)  // Ширина экрана в пикселях

        Logger.i("DEB_TAG") { "screenWidthPx $screenWidthPx" }


        val barWidthPx = 35       // Ширина одного столбика
        val totalBars = statistics.size                // Количество столбиков
        val totalSpacing = (screenWidthPx - (barWidthPx * totalBars)) / (totalBars - 1)

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
//            userScrollEnabled = false
        ) {
            itemsIndexed(statistics) { index, model ->
                BarChartItem(
                    model = model,
                    widthColumn = barWidthPx.dp
                )
                if (index < totalBars - 1) {
//                    Spacer(modifier = Modifier.width(totalSpacing.dp))
                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
        }
    }
}