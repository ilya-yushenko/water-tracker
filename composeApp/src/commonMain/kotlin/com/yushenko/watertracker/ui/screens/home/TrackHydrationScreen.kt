package com.yushenko.watertracker.ui.screens.home

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorBlack
import com.yushenko.watertracker.theme.ColorGrayWhite
import com.yushenko.watertracker.theme.ColorWhite
import com.yushenko.watertracker.theme.Colors
import com.yushenko.watertracker.ui.components.ButtonIcon
import com.yushenko.watertracker.ui.components.DrinkModel
import com.yushenko.watertracker.ui.components.HydrationItem
import com.yushenko.watertracker.ui.components.VolumeItem
import com.yushenko.watertracker.ui.components.VolumeModel
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import watertracker.composeapp.generated.resources.Inter_Bold
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Inter_SemiBold
import watertracker.composeapp.generated.resources.Res
import watertracker.composeapp.generated.resources.ic_confirm
import watertracker.composeapp.generated.resources.ic_minus
import watertracker.composeapp.generated.resources.ic_plus
import watertracker.composeapp.generated.resources.ic_quick_coffee
import watertracker.composeapp.generated.resources.ic_quick_juice
import watertracker.composeapp.generated.resources.ic_quick_milk
import watertracker.composeapp.generated.resources.ic_quick_smoothie
import watertracker.composeapp.generated.resources.ic_quick_soda
import watertracker.composeapp.generated.resources.ic_quick_tea
import watertracker.composeapp.generated.resources.ic_quick_water

@Composable
fun TrackHydrationScreen(
    onDismiss: () -> Unit
) {
    val drinks = listOf(
        DrinkModel("Water", "250 ml", Res.drawable.ic_quick_water),
        DrinkModel("Coffee", "200 ml", Res.drawable.ic_quick_coffee),
        DrinkModel("Tea", "200 ml", Res.drawable.ic_quick_tea),
        DrinkModel("Smoothie", "200 ml", Res.drawable.ic_quick_smoothie),
        DrinkModel("Juice", "200 ml", Res.drawable.ic_quick_juice),
        DrinkModel("Milk", "200 ml", Res.drawable.ic_quick_milk),
        DrinkModel("Soda", "200 ml", Res.drawable.ic_quick_soda)
    )

    val volumes = listOf(
        VolumeModel(100),
        VolumeModel(200),
        VolumeModel(300),
        VolumeModel(500)
    )

    val waterVolume = 250
    var currentVolume by remember { mutableStateOf(waterVolume) }
    var selectedDrink by remember { mutableStateOf(drinks[0]) }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorWhite),
    ) {
        val screenHeight = maxHeight
        val targetHeight = screenHeight * 0.5f

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(targetHeight),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(
                modifier = Modifier
                    .height(5.dp)
                    .width(90.dp)
                    .background(Colors.LightGray, RoundedCornerShape(2.dp))
            )
            Spacer(modifier = Modifier.weight(0.25f))
            Text(
                text = selectedDrink.label,
                fontSize = 28.sp,
                color = ColorBlack,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(Res.font.Inter_Bold)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                itemsIndexed(drinks, key = { _, item -> item.label }) { _, model ->
                    HydrationItem(
                        label = model.label,
                        iconRes = model.iconRes,
                        isSelected = selectedDrink == model,
                        onClick = {
                            selectedDrink = model
                            currentVolume = model.volume.removeSuffix(" ml").toInt()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.weight(0.15f))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(0.35f))
                IconButton(onClick = {
                    currentVolume--
                }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_minus),
                        contentDescription = "Minus",
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(ColorGrayWhite)
                            .size(48.dp)
                            .padding(10.dp)
                    )
                }
                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "$currentVolume",
                        fontSize = 48.sp,
                        color = ColorBlack,
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(Res.font.Inter_SemiBold))
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "ml",
                        fontSize = 20.sp,
                        color = ColorBlack,
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .padding(bottom = 6.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                IconButton(onClick = {
                    currentVolume++
                }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_plus),
                        contentDescription = "Plus",
                        modifier = Modifier
                            .clip(RoundedCornerShape(28.dp))
                            .background(ColorGrayWhite)
                            .size(48.dp)
                            .padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(0.35f))
            }
            Spacer(modifier = Modifier.weight(0.2f))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                itemsIndexed(volumes) { index, model ->
                    VolumeItem(
                        model = model,
                        onClick = { model ->
                            currentVolume = model.volume
                        }
                    )
                    if (index < volumes.size - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.3f))
            ButtonIcon(
                iconRes = Res.drawable.ic_confirm,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(48.dp),
                onClick = {
//                onAddClick(currentVolume)
                    onDismiss()
                }
            )
            Spacer(modifier = Modifier.weight(0.25f))
        }
    }
}