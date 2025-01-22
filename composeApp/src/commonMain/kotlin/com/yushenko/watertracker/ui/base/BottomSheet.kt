package com.yushenko.watertracker.ui.base

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    backgroundColor: Color = Color.White,
    roundCorners: Shape = RectangleShape,
    elevation: Dp = 8.dp,
    onDismiss: () -> Unit,
) {
    val offsetY = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .offset { IntOffset(0, offsetY.value.roundToInt()) }  // Shifting the dialog
                .shadow(elevation = elevation, shape = roundCorners)
                .background(color = backgroundColor, shape = roundCorners)
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragEnd = {
                            if (offsetY.value > 250f) { // If you move it far enough
                                coroutineScope.launch {
                                    offsetY.animateTo(1000f, tween(300))
                                    onDismiss() // Close the dialog
                                }
                            } else {
                                coroutineScope.launch {
                                    offsetY.animateTo(0f, tween(300)) // Return back
                                }
                            }
                        },
                        onVerticalDrag = { change, dragAmount ->
                            change.consume()
                            coroutineScope.launch {
                                val newOffset = offsetY.value + dragAmount
                                if (newOffset >= 0f) {
                                    offsetY.snapTo(newOffset)
                                }
                            }
                        }
                    )
                }
        ) {
            content()
        }
    }
}