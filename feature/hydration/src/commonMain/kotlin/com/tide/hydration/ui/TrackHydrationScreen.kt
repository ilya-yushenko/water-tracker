package com.tide.hydration.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.appColorPalette
import com.tide.common.components.ButtonIcon
import com.tide.hydration.ui.components.DrinkModel
import com.tide.hydration.ui.components.HydrationItem
import com.tide.hydration.ui.components.VolumeItem
import com.tide.hydration.ui.components.VolumeModel
import org.jetbrains.compose.resources.painterResource
import watertracker.feature.hydration.generated.resources.Res
import watertracker.feature.hydration.generated.resources.ic_confirm
import watertracker.feature.hydration.generated.resources.ic_minus
import watertracker.feature.hydration.generated.resources.ic_plus
import watertracker.feature.hydration.generated.resources.ic_quick_coffee
import watertracker.feature.hydration.generated.resources.ic_quick_juice
import watertracker.feature.hydration.generated.resources.ic_quick_milk
import watertracker.feature.hydration.generated.resources.ic_quick_smoothie
import watertracker.feature.hydration.generated.resources.ic_quick_soda
import watertracker.feature.hydration.generated.resources.ic_quick_tea
import watertracker.feature.hydration.generated.resources.ic_quick_water


@Composable
fun TrackHydrationScreen(
    onDismiss: () -> Unit
) {
    val colors = appColorPalette()

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

    var currentVolume by remember { mutableStateOf(250) }
    var selectedDrink by remember { mutableStateOf(drinks[0]) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.surface)
            .clickable(
                onClick = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
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
                    .background(colors.gray20, RoundedCornerShape(2.dp))
            )
            Spacer(modifier = Modifier.weight(0.25f))
            Text(
                text = selectedDrink.label,
                fontSize = 28.sp,
                textAlign = TextAlign.Start,
                fontFamily = AppTypography.bold(),
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
                Spacer(modifier = Modifier.weight(0.25f))
                IconButton(onClick = {
                    currentVolume--
                }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_minus),
                        contentDescription = "Minus",
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(colors.gray30)
                            .size(48.dp)
                            .padding(10.dp)
                    )
                }
                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    BasicTextField(
                        value = currentVolume.toString(),
                        onValueChange = { newValue ->
                            if (newValue.length <= 4) {
                                if (currentVolume == 0) {
                                    val trimmedValue = newValue.trimEnd('0')
                                    currentVolume = trimmedValue.toIntOrNull() ?: 0
                                } else {
                                    val trimmedValue = newValue.trimStart('0')
                                    currentVolume = trimmedValue.toIntOrNull() ?: 0
                                }
                            }
                        },
                        textStyle = TextStyle(
                            fontSize = 48.sp,
                            color = colors.onSurface,
                            textAlign = TextAlign.Start,
                            fontFamily = AppTypography.semiBold()
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.align(Alignment.Bottom).width(IntrinsicSize.Min)
                            .onFocusChanged { focusState ->
                                if (!focusState.isFocused) {
                                    keyboardController?.hide()
                                }
                            }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "ml",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        fontFamily = AppTypography.medium(),
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
                            .background(colors.gray30)
                            .size(48.dp)
                            .padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(0.25f))
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

    DisposableEffect(Unit) {
        onDispose {
            keyboardController?.hide()
            focusManager.clearFocus()
        }
    }
}