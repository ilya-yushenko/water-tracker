package com.yushenko.watertracker.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.tide.common.BottomSheetType
import kotlinx.coroutines.launch

@Composable
fun BottomSheetHost(
    sheetState: ModalBottomSheetState,
    currentBottomSheetContent: BottomSheetType?,
    modifier: Modifier = Modifier,
    sheetContent: @Composable (BottomSheetType) -> Unit,
    content: @Composable () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var isKeyboardHidden by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetElevation = 0.dp,
        scrimColor = Color.Black.copy(alpha = 0.32f),
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            currentBottomSheetContent?.let { sheetData ->
                sheetContent(sheetData)
                LaunchedEffect(sheetState.currentValue) {
                    if (sheetState.currentValue == ModalBottomSheetValue.Hidden) {
                        isKeyboardHidden = false
                    }
                }
                DisposableEffect(Unit) {
                    onDispose {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                }
            }
        },
        modifier = modifier.clickable(
            onClick = {
                val isKeyboardVisible = keyboardController != null && !isKeyboardHidden
                if (isKeyboardVisible) {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    isKeyboardHidden = true
                } else if (isKeyboardHidden) {
                    coroutineScope.launch { sheetState.hide() }
                }
            },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ),
        content = content
    )
}