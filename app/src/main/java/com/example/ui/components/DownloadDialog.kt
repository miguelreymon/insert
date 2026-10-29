package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.getGameInfo
import com.example.ui.theme.LineBorder
import com.example.ui.theme.NunitoFontFamily
import com.example.ui.theme.PanelDark
import com.example.ui.theme.PressStart2PFontFamily
import com.example.ui.theme.RetroBlack
import com.example.ui.theme.RetroBlue
import com.example.ui.theme.RetroGreen
import com.example.ui.theme.RetroWhite
import com.example.ui.theme.RetroYellow
import com.example.ui.theme.TextDim
import com.example.ui.theme.TextInk
import kotlinx.coroutines.delay

@Composable
fun DownloadDialog(
    gameTitle: String,
    systemName: String,
    isAlreadyInstalled: Boolean,
    onInstallCompleted: (String) -> Unit,
    onPlayGame: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val gameInfo = remember(gameTitle) { getGameInfo(gameTitle, systemName) }

    var isInstalling by remember { mutableStateOf(!isAlreadyInstalled) }
    var progress by remember { mutableFloatStateOf(if (isAlreadyInstalled) 100f else 0f) }
    var isCompleted by remember { mutableStateOf(isAlreadyInstalled) }
    var messageText by remember {
        mutableStateOf(
            if (isAlreadyInstalled)
                "✓ INSTALADO EN MEMORIA LOCAL. Pulsa A para jugar."
            else
                "Descargando ${gameInfo.fileSize} al almacenamiento de la tele…"
        )
    }

    val playButtonFocusRequester = remember { FocusRequester() }
    val closeButtonFocusRequester = remember { FocusRequester() }

    BackHandler {
        onDismiss()
    }

    // Progress simulation when downloading to local storage (~2.5s)
    LaunchedEffect(gameTitle) {
        if (!isAlreadyInstalled) {
            progress = 0f
            isCompleted = false
            messageText = "Descargando ${gameInfo.fileSize} al almacenamiento de la tele…"

            while (progress < 100f) {
                delay(260)
                val step = (10f + (Math.random().toFloat() * 14f))
                progress = (progress + step).coerceAtMost(100f)
            }
            isCompleted = true
            isInstalling = false
            onInstallCompleted(gameTitle)
            messageText = "Listo. Guardado en almacenamiento local. Pulsa A para jugar."
            try {
                playButtonFocusRequester.requestFocus()
            } catch (_: Exception) {}
        } else {
            try {
                playButtonFocusRequester.requestFocus()
            } catch (_: Exception) {}
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f))
                .clickable(onClick = onDismiss),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .clickable(enabled = false) {}
                    .widthIn(min = 440.dp, max = 560.dp)
                    .background(PanelDark)
                    .border(width = 4.dp, color = RetroYellow, shape = RectangleShape)
                    .padding(22.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Header: Game Title
                    Text(
                        text = gameTitle,
                        style = TextStyle(
                            fontFamily = PressStart2PFontFamily,
                            fontSize = 13.sp,
                            lineHeight = 18.sp,
                            color = TextInk
                        ),
                        modifier = Modifier.testTag("dialog_game_title")
                    )

                    // Badges: System, Genre, Year, Size
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .background(RetroYellow)
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = gameInfo.system,
                                style = TextStyle(
                                    fontFamily = PressStart2PFontFamily,
                                    fontSize = 8.sp,
                                    color = RetroBlack
                                )
                            )
                        }

                        Text(
                            text = "${gameInfo.genre} • ${gameInfo.year} • ${gameInfo.fileSize}",
                            style = TextStyle(
                                fontFamily = NunitoFontFamily,
                                fontSize = 12.sp,
                                color = TextDim
                            )
                        )
                    }

                    // Description text
                    Text(
                        text = gameInfo.description,
                        style = TextStyle(
                            fontFamily = NunitoFontFamily,
                            fontSize = 13.sp,
                            lineHeight = 18.sp,
                            color = TextInk
                        )
                    )

                    // Striped Progress Bar
                    val animatedProgress by animateFloatAsState(targetValue = progress, label = "progress")
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(22.dp)
                            .background(RetroBlack)
                            .border(width = 3.dp, color = RetroWhite, shape = RectangleShape)
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val fillWidth = size.width * (animatedProgress / 100f)
                            if (fillWidth > 0f) {
                                val stripePeriod = 12f * density
                                val primaryStripeWidth = 10f * density
                                var x = 0f
                                while (x < fillWidth) {
                                    val currentWidth = minOf(primaryStripeWidth, fillWidth - x)
                                    drawRect(
                                        color = RetroGreen,
                                        topLeft = Offset(x, 0f),
                                        size = Size(currentWidth, size.height)
                                    )
                                    val gapX = x + primaryStripeWidth
                                    if (gapX < fillWidth) {
                                        val gapWidth = minOf(stripePeriod - primaryStripeWidth, fillWidth - gapX)
                                        drawRect(
                                            color = Color(0xFF2B7D28),
                                            topLeft = Offset(gapX, 0f),
                                            size = Size(gapWidth, size.height)
                                        )
                                    }
                                    x += stripePeriod
                                }
                            }
                        }
                    }

                    // Status message & location
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = messageText,
                            style = TextStyle(
                                fontFamily = NunitoFontFamily,
                                fontSize = 13.sp,
                                lineHeight = 18.sp,
                                color = if (isCompleted) RetroGreen else TextDim
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("dialog_message")
                        )
                        Text(
                            text = if (isCompleted) "✓ INSTALADO" else "${animatedProgress.toInt()}%",
                            style = TextStyle(
                                fontFamily = PressStart2PFontFamily,
                                fontSize = 9.sp,
                                color = if (isCompleted) RetroGreen else RetroYellow
                            ),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    // Action Buttons Row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    ) {
                        // "▶ Jugar"
                        var isPlayFocused by remember { mutableStateOf(false) }
                        Box(
                            modifier = Modifier
                                .focusRequester(playButtonFocusRequester)
                                .onFocusChanged { isPlayFocused = it.isFocused }
                                .alpha(if (isCompleted) 1f else 0.4f)
                                .background(RetroYellow)
                                .border(
                                    width = if (isPlayFocused) 4.dp else 3.dp,
                                    color = if (isPlayFocused) RetroWhite else RetroBlack,
                                    shape = RectangleShape
                                )
                                .clickable(enabled = isCompleted) {
                                    onPlayGame(gameTitle)
                                }
                                .focusable(enabled = isCompleted)
                                .padding(horizontal = 18.dp, vertical = 12.dp)
                                .testTag("btn_play"),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "▶ Jugar",
                                style = TextStyle(
                                    fontFamily = PressStart2PFontFamily,
                                    fontSize = 11.sp,
                                    color = RetroBlack
                                )
                            )
                        }

                        // "Cerrar"
                        var isCloseFocused by remember { mutableStateOf(false) }
                        Box(
                            modifier = Modifier
                                .focusRequester(closeButtonFocusRequester)
                                .onFocusChanged { isCloseFocused = it.isFocused }
                                .background(PanelDark)
                                .border(
                                    width = if (isCloseFocused) 4.dp else 3.dp,
                                    color = if (isCloseFocused) RetroYellow else LineBorder,
                                    shape = RectangleShape
                                )
                                .clickable { onDismiss() }
                                .focusable()
                                .padding(horizontal = 18.dp, vertical = 12.dp)
                                .testTag("btn_close"),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Cerrar",
                                style = TextStyle(
                                    fontFamily = PressStart2PFontFamily,
                                    fontSize = 11.sp,
                                    color = TextInk
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
