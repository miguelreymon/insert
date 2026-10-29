package com.example.ui.screens

import android.graphics.ImageDecoder
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.widget.ImageView
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.R
import com.example.ui.components.RetroLogo
import com.example.ui.theme.BgDark
import com.example.ui.theme.LineBorder
import com.example.ui.theme.PanelDark
import com.example.ui.theme.PressStart2PFontFamily
import com.example.ui.theme.RetroBlack
import com.example.ui.theme.RetroGreen
import com.example.ui.theme.RetroRed
import com.example.ui.theme.RetroWhite
import com.example.ui.theme.RetroYellow
import com.example.ui.theme.TextDim
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onNavigateToLibrary: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val playButtonFocusRequester = remember { FocusRequester() }
    val keypadFocusRequester = remember { FocusRequester() }

    var isButtonFocused by remember { mutableStateOf(false) }
    var isPinMode by remember { mutableStateOf(false) }
    var pinValue by remember { mutableStateOf("") }
    var pinError by remember { mutableStateOf<String?>(null) }
    var pinSuccess by remember { mutableStateOf(false) }
    var isCheckingPin by remember { mutableStateOf(false) }

    // Pulsing retro animation for "JUGAR" button
    val infiniteTransition = rememberInfiniteTransition(label = "play_blink")
    val blinkAlpha by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 0.35f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 550, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "blink_alpha"
    )

    // Focus management
    LaunchedEffect(isPinMode) {
        if (!isPinMode) {
            try {
                playButtonFocusRequester.requestFocus()
            } catch (_: Exception) {}
        } else {
            try {
                keypadFocusRequester.requestFocus()
            } catch (_: Exception) {}
        }
    }

    // Handle Back press in PIN mode to return to "JUGAR"
    BackHandler(enabled = isPinMode) {
        isPinMode = false
        pinValue = ""
        pinError = null
        pinSuccess = false
    }

    // Verification logic
    fun checkPin(code: String) {
        if (isCheckingPin) return
        isCheckingPin = true

        coroutineScope.launch {
            if (code == "1111") {
                pinSuccess = true
                pinError = null
                delay(350)
                onNavigateToLibrary()
            } else {
                pinSuccess = false
                pinError = "CLAVE INCORRECTA"
                delay(1200)
                pinValue = ""
                pinError = null
                isCheckingPin = false
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BgDark)
            .onKeyEvent { keyEvent ->
                if (!isPinMode || isCheckingPin) return@onKeyEvent false
                when (keyEvent.key) {
                    Key.Enter, Key.NumPadEnter -> {
                        if (pinValue.isNotEmpty()) {
                            checkPin(pinValue)
                            true
                        } else false
                    }
                    Key.Escape -> {
                        isPinMode = false
                        pinValue = ""
                        pinError = null
                        true
                    }
                    else -> false
                }
            },
        contentAlignment = Alignment.Center
    ) {
        // Layer 1: Animated Arcade GIF Background
        AndroidView(
            factory = { context ->
                ImageView(context).apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        try {
                            val source = ImageDecoder.createSource(resources, R.drawable.bg_arcade_home)
                            val drawable = ImageDecoder.decodeDrawable(source)
                            if (drawable is AnimatedImageDrawable) {
                                drawable.repeatCount = AnimatedImageDrawable.REPEAT_INFINITE
                            }
                            setImageDrawable(drawable)
                            (drawable as? Animatable)?.start()
                        } catch (_: Throwable) {
                            setImageResource(R.drawable.bg_arcade_home_poster)
                        }
                    } else {
                        setImageResource(R.drawable.bg_arcade_home_poster)
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Foreground Content (no dark overlay so animated background GIF is fully visible)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // Hero Brand Logo: "INSERT COINS" (all yellow retro letters)
            RetroLogo(
                fontSize = 46.sp,
                modifier = Modifier.testTag("home_logo")
            )

            // Subtitle Taglines (kept as requested, with crisp drop shadow)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Tus juegos retro en la tele.",
                    style = TextStyle(
                        fontFamily = PressStart2PFontFamily,
                        fontSize = 11.sp,
                        lineHeight = 18.sp,
                        color = RetroWhite,
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Black,
                            offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                            blurRadius = 4f
                        ),
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = "Instala la app, conecta un mando y juega.",
                    style = TextStyle(
                        fontFamily = PressStart2PFontFamily,
                        fontSize = 11.sp,
                        lineHeight = 18.sp,
                        color = RetroWhite,
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Black,
                            offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                            blurRadius = 4f
                        ),
                        textAlign = TextAlign.Center
                    )
                )
            }

            // Animated Button Container: transforms in-place into text entry field
            AnimatedContent(
                targetState = isPinMode,
                transitionSpec = {
                    fadeIn(animationSpec = tween(220)) togetherWith
                        fadeOut(animationSpec = tween(180))
                },
                label = "play_to_pin_transition"
            ) { inPinMode ->
                if (!inPinMode) {
                    // Initial State: "JUGAR" Button
                    val currentAlpha = if (isButtonFocused) 1.0f else blinkAlpha
                    val bgColor = if (isButtonFocused) RetroYellow else RetroBlack
                    val textColor = if (isButtonFocused) RetroBlack else RetroYellow

                    Box(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .focusRequester(playButtonFocusRequester)
                            .onFocusChanged { isButtonFocused = it.isFocused }
                            .alpha(currentAlpha)
                            .border(width = 4.dp, color = Color.Black, shape = RectangleShape)
                            .padding(4.dp)
                            .border(
                                width = 4.dp,
                                color = if (isButtonFocused) RetroYellow else RetroWhite,
                                shape = RectangleShape
                            )
                            .background(bgColor)
                            .clickable {
                                isPinMode = true
                                pinValue = ""
                                pinError = null
                            }
                            .focusable()
                            .padding(horizontal = 36.dp, vertical = 16.dp)
                            .testTag("btn_play_home"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "JUGAR",
                            style = TextStyle(
                                fontFamily = PressStart2PFontFamily,
                                fontSize = 18.sp,
                                color = textColor,
                                letterSpacing = 2.sp
                            )
                        )
                    }
                } else {
                    // Typical 4-character text input console
                    RetroTextEntryConsole(
                        pinValue = pinValue,
                        pinError = pinError,
                        pinSuccess = pinSuccess,
                        isCheckingPin = isCheckingPin,
                        initialFocusRequester = keypadFocusRequester,
                        onValueChange = { newVal ->
                            if (!isCheckingPin && newVal.length <= 4) {
                                pinValue = newVal
                                pinError = null
                                if (newVal.length == 4) {
                                    checkPin(newVal)
                                }
                            }
                        },
                        onSubmit = { checkPin(pinValue) },
                        onCancelClick = {
                            isPinMode = false
                            pinValue = ""
                            pinError = null
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun RetroTextEntryConsole(
    pinValue: String,
    pinError: String?,
    pinSuccess: Boolean,
    isCheckingPin: Boolean,
    initialFocusRequester: FocusRequester,
    onValueChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = when {
        pinSuccess -> RetroGreen
        pinError != null -> RetroRed
        else -> LineBorder
    }

    Box(
        modifier = modifier
            .widthIn(max = 440.dp)
            .border(width = 3.dp, color = borderColor, shape = RoundedCornerShape(8.dp))
            .background(PanelDark.copy(alpha = 0.95f), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 24.dp, vertical = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header Prompt
            Text(
                text = "INTRODUCE LA CLAVE",
                style = TextStyle(
                    fontFamily = PressStart2PFontFamily,
                    fontSize = 11.sp,
                    color = RetroYellow
                )
            )

            // Typical Text Input Field (accepts any character, max 4 chars)
            androidx.compose.material3.OutlinedTextField(
                value = pinValue,
                onValueChange = onValueChange,
                singleLine = true,
                maxLines = 1,
                enabled = !isCheckingPin,
                textStyle = TextStyle(
                    fontFamily = PressStart2PFontFamily,
                    fontSize = 20.sp,
                    letterSpacing = 8.sp,
                    textAlign = TextAlign.Center,
                    color = when {
                        pinSuccess -> RetroGreen
                        pinError != null -> RetroRed
                        else -> RetroYellow
                    }
                ),
                placeholder = {
                    Text(
                        text = "____",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(
                            fontFamily = PressStart2PFontFamily,
                            fontSize = 20.sp,
                            letterSpacing = 8.sp,
                            textAlign = TextAlign.Center,
                            color = TextDim
                        )
                    )
                },
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = androidx.compose.ui.text.input.KeyboardType.Text,
                    imeAction = androidx.compose.ui.text.input.ImeAction.Done
                ),
                keyboardActions = androidx.compose.foundation.text.KeyboardActions(
                    onDone = { onSubmit() }
                ),
                colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = RetroYellow,
                    unfocusedBorderColor = LineBorder,
                    errorBorderColor = RetroRed,
                    focusedContainerColor = Color.Black,
                    unfocusedContainerColor = Color.Black,
                    cursorColor = RetroYellow
                ),
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .focusRequester(initialFocusRequester)
                    .testTag("pin_text_input")
            )

            // Status message
            val statusText = when {
                pinSuccess -> "¡ACCESO PERMITIDO!"
                pinError != null -> "¡CLAVE INCORRECTA!"
                else -> "Solo 4 caracteres (ej: 1111)"
            }
            val statusColor = when {
                pinSuccess -> RetroGreen
                pinError != null -> RetroRed
                else -> TextDim
            }

            Text(
                text = statusText,
                style = TextStyle(
                    fontFamily = PressStart2PFontFamily,
                    fontSize = 9.sp,
                    color = statusColor,
                    textAlign = TextAlign.Center
                )
            )

            // Action Buttons Row: ENTRAR & CANCELAR
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RetroActionKey(
                    label = "ENTRAR ▶",
                    isPrimary = true,
                    onClick = onSubmit
                )
                RetroActionKey(
                    label = "CANCELAR ✕",
                    isPrimary = false,
                    onClick = onCancelClick
                )
            }
        }
    }
}

@Composable
private fun RetroActionKey(
    label: String,
    onClick: () -> Unit,
    isPrimary: Boolean = false,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .onFocusChanged { isFocused = it.isFocused }
            .border(
                width = 2.dp,
                color = when {
                    isFocused -> RetroYellow
                    isPrimary -> RetroYellow.copy(alpha = 0.7f)
                    else -> LineBorder
                },
                shape = RoundedCornerShape(4.dp)
            )
            .background(
                when {
                    isFocused -> RetroYellow
                    isPrimary -> PanelDark
                    else -> BgDark
                },
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClick() }
            .focusable()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = PressStart2PFontFamily,
                fontSize = 10.sp,
                color = when {
                    isFocused -> RetroBlack
                    isPrimary -> RetroYellow
                    else -> TextDim
                }
            )
        )
    }
}
