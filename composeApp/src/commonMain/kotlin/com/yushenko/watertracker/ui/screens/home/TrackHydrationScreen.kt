package com.yushenko.watertracker.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import com.yushenko.watertracker.theme.ColorBlue10
import com.yushenko.watertracker.theme.ColorGray
import com.yushenko.watertracker.theme.ColorGrayWhite
import com.yushenko.watertracker.theme.Colors
import com.yushenko.watertracker.ui.components.ButtonIcon
import com.yushenko.watertracker.ui.components.DrinkModel
import com.yushenko.watertracker.ui.components.VolumeItem
import com.yushenko.watertracker.ui.components.VolumeModel
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import watertracker.composeapp.generated.resources.Inter_Bold
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Inter_Regular
import watertracker.composeapp.generated.resources.Inter_SemiBold
import watertracker.composeapp.generated.resources.Res
import watertracker.composeapp.generated.resources.add_hydration_bottom_sheet_contribution
import watertracker.composeapp.generated.resources.add_hydration_bottom_sheet_hydration
import watertracker.composeapp.generated.resources.ic_confirm
import watertracker.composeapp.generated.resources.ic_minus
import watertracker.composeapp.generated.resources.ic_plus

@Composable
fun TrackHydrationScreen(
    data: DrinkModel,
    onDismiss: () -> Unit
) {

    val waterVolume = 250
    var currentVolume by remember { mutableStateOf(waterVolume) }

    val volumes = listOf(
        VolumeModel(100),
        VolumeModel(200),
        VolumeModel(300),
        VolumeModel(500)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(
            modifier = Modifier
                .height(5.dp)
                .width(90.dp)
                .background(Colors.LightGray, RoundedCornerShape(2.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
//                        .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(ColorBlue10),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(Res.string.add_hydration_bottom_sheet_contribution),
                fontSize = 16.sp,
                color = ColorBlack,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = data.label,
                fontSize = 28.sp,
                color = ColorBlack,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(Res.font.Inter_Bold)),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(
                    Res.string.add_hydration_bottom_sheet_hydration,
                    data.volume
                ),
                fontSize = 14.sp,
                color = ColorGray,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(Res.font.Inter_Regular)),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
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

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "$currentVolume",
                fontSize = 48.sp,
                color = ColorBlack,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(Res.font.Inter_SemiBold)),
            )
            Spacer(modifier = Modifier.width(6.dp))
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

            Spacer(modifier = Modifier.width(16.dp))

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
        }

        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth()
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

        Spacer(modifier = Modifier.height(32.dp))

        ButtonIcon(
            iconRes = Res.drawable.ic_confirm,
            onClick = {
//                onAddClick(currentVolume)
                onDismiss()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}