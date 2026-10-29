package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.getGameInfo
import com.example.ui.theme.LineBorder
import com.example.ui.theme.NunitoFontFamily
import com.example.ui.theme.PanelDark
import com.example.ui.theme.PressStart2PFontFamily
import com.example.ui.theme.RetroBlack
import com.example.ui.theme.RetroBlue
import com.example.ui.theme.RetroGreen
import com.example.ui.theme.RetroRed
import com.example.ui.theme.RetroWhite
import com.example.ui.theme.RetroYellow
import com.example.ui.theme.TextDim
import com.example.ui.theme.TextInk
import kotlinx.coroutines.delay
import kotlin.math.sin

@Composable
fun EmulatorScreen(
    gameTitle: String,
    onExitEmulator: () -> Unit,
    modifier: Modifier = Modifier
) {
    val gameInfo = remember(gameTitle) { getGameInfo(gameTitle, "16 bits (SNES)") }
    val focusRequester = remember { FocusRequester() }

    // Game state
    var playerX by remember { mutableFloatStateOf(100f) }
    var playerY by remember { mutableFloatStateOf(145f) }
    var isJumping by remember { mutableStateOf(false) }
    var isAttacking by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(1500) }
    var itemsCount by remember { mutableIntStateOf(7) }
    var lives by remember { mutableIntStateOf(3) }
    var isPaused by remember { mutableStateOf(false) }
    var enemyX by remember { mutableFloatStateOf(190f) }
    var enemyDirection by remember { mutableFloatStateOf(-1f) }

    BackHandler {
        onExitEmulator()
    }

    LaunchedEffect(Unit) {
        try {
            focusRequester.requestFocus()
        } catch (_: Exception) {}
    }

    // Enemy patrol loop
    LaunchedEffect(isPaused) {
        while (!isPaused) {
            delay(40)
            enemyX += enemyDirection * 1.5f
            if (enemyX < 140f) {
                enemyDirection = 1f
            } else if (enemyX > 220f) {
                enemyDirection = -1f
            }
        }
    }

    // Jump loop
    LaunchedEffect(isJumping) {
        if (isJumping) {
            val startY = 145f
            for (step in 0..12) {
                playerY = startY - (48f * sin(step * Math.PI / 12f).toFloat())
                delay(26)
            }
            playerY = startY
            isJumping = false
            // Check reward
            score += 100
            itemsCount += 1
        }
    }

    // Attack cooldown
    LaunchedEffect(isAttacking) {
        if (isAttacking) {
            delay(280)
            isAttacking = false
            score += 250
        }
    }

    // CRT scanline animation
    val infiniteTransition = rememberInfiniteTransition(label = "crt_scanline")
    val scanlineY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "scanline_beam"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .focusRequester(focusRequester)
            .focusable()
            .onKeyEvent { event ->
                when (event.key) {
                    Key.DirectionLeft -> {
                        playerX = (playerX - 10f).coerceAtLeast(24f)
                        true
                    }
                    Key.DirectionRight -> {
                        playerX = (playerX + 10f).coerceAtMost(232f)
                        true
                    }
                    Key.DirectionUp, Key.ButtonA, Key.Enter, Key.NumPadEnter -> {
                        if (!isJumping) isJumping = true
                        true
                    }
                    Key.ButtonX, Key.ButtonY, Key.Spacebar, Key.DirectionDown -> {
                        if (!isAttacking) isAttacking = true
                        true
                    }
                    Key.Menu, Key.Escape -> {
                        isPaused = !isPaused
                        true
                    }
                    Key.Back, Key.ButtonB -> {
                        onExitEmulator()
                        true
                    }
                    else -> false
                }
            }
            .testTag("emulator_view"),
        contentAlignment = Alignment.Center
    ) {
        // 4:3 CRT Retro TV Arcade Display
        Box(
            modifier = Modifier
                .aspectRatio(4f / 3f)
                .fillMaxSize()
                .padding(12.dp)
                .border(width = 5.dp, color = RetroYellow, shape = RectangleShape)
                .background(PanelDark)
        ) {
            // Native 60 FPS Retro Stage Canvas tailored per game
            Canvas(modifier = Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height
                val sx = w / 256f
                val sy = h / 224f

                // 1. Theme-Specific Background
                when {
                    gameTitle.contains("Mario", ignoreCase = true) -> {
                        // Mushroom Kingdom Sky & Hills
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF5C94FC), Color(0xFF90CAF9))
                            ),
                            size = Size(w, h)
                        )
                        // Green rolling hills
                        drawCircle(
                            color = Color(0xFF00A800),
                            radius = 90f * sx,
                            center = Offset(60f * sx, 200f * sy)
                        )
                        drawCircle(
                            color = Color(0xFF007800),
                            radius = 70f * sx,
                            center = Offset(180f * sx, 200f * sy)
                        )
                        // Warp Pipe
                        drawRect(
                            color = Color(0xFF00A800),
                            topLeft = Offset(190f * sx, 130f * sy),
                            size = Size(28f * sx, 45f * sy)
                        )
                        drawRect(
                            color = Color(0xFF00D800),
                            topLeft = Offset(186f * sx, 122f * sy),
                            size = Size(36f * sx, 12f * sy)
                        )
                        // ? Question Block
                        drawRect(
                            color = RetroYellow,
                            topLeft = Offset(110f * sx, 85f * sy),
                            size = Size(20f * sx, 20f * sy)
                        )
                    }
                    gameTitle.contains("Zelda", ignoreCase = true) -> {
                        // Hyrule Dungeon / Overworld Stone and Forest
                        drawRect(
                            color = Color(0xFF1B3D2F),
                            size = Size(w, h)
                        )
                        // Stone dungeon grid pattern
                        for (gx in 0 until 8) {
                            for (gy in 0 until 7) {
                                drawRect(
                                    color = Color(0xFF2E5D46),
                                    topLeft = Offset(gx * 32f * sx + 2f, gy * 32f * sy + 2f),
                                    size = Size(28f * sx, 28f * sy)
                                )
                            }
                        }
                        // Triforce pedestal
                        val triPath = Path().apply {
                            moveTo(128f * sx, 60f * sy)
                            lineTo(144f * sx, 88f * sy)
                            lineTo(112f * sx, 88f * sy)
                            close()
                        }
                        drawPath(triPath, color = RetroYellow)
                    }
                    gameTitle.contains("Metroid", ignoreCase = true) -> {
                        // Zebes Dark Alien Cavern
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF0F0A1C), Color(0xFF261238))
                            ),
                            size = Size(w, h)
                        )
                        // Cavern stalactites
                        drawRect(
                            color = Color(0xFF6B3074),
                            topLeft = Offset(40f * sx, 0f),
                            size = Size(22f * sx, 40f * sy)
                        )
                        drawRect(
                            color = Color(0xFF6B3074),
                            topLeft = Offset(150f * sx, 0f),
                            size = Size(30f * sx, 50f * sy)
                        )
                    }
                    gameTitle.contains("Donkey Kong", ignoreCase = true) -> {
                        // Kongo Jungle Canopy
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF0F381E), Color(0xFF1E6038))
                            ),
                            size = Size(w, h)
                        )
                        // Jungle foliage & vines
                        drawRect(
                            color = Color(0xFF0B2915),
                            topLeft = Offset(0f, 0f),
                            size = Size(w, 40f * sy)
                        )
                        // Wooden DK Barrel
                        drawCircle(
                            color = Color(0xFF8D6E63),
                            radius = 12f * sx,
                            center = Offset(60f * sx, 155f * sy)
                        )
                    }
                    gameTitle.contains("Crash", ignoreCase = true) -> {
                        // Wumpa Island Beach & Jungle
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF0277BD), Color(0xFF29B6F6))
                            ),
                            size = Size(w, h)
                        )
                        // Wooden Crates stack
                        drawRect(
                            color = Color(0xFFD84315),
                            topLeft = Offset(150f * sx, 135f * sy),
                            size = Size(22f * sx, 22f * sy)
                        )
                        drawRect(
                            color = Color(0xFFE65100),
                            topLeft = Offset(150f * sx, 113f * sy),
                            size = Size(22f * sx, 22f * sy)
                        )
                    }
                    else -> {
                        // Futuristic Mega Man / Arcade Neon Cyber Stage
                        drawRect(
                            color = Color(0xFF090D1A),
                            size = Size(w, h)
                        )
                        // Neon Grid lines
                        for (lineIndex in 0..12) {
                            val ly = 100f * sy + (lineIndex * 8f * sy)
                            drawRect(
                                color = Color(0xFF00E5FF).copy(alpha = 0.25f),
                                topLeft = Offset(0f, ly),
                                size = Size(w, 1.5f)
                            )
                        }
                    }
                }

                // 2. Ground Terrain
                drawRect(
                    color = Color(0xFF4CAF50),
                    topLeft = Offset(0f, 175f * sy),
                    size = Size(w, 8f * sy)
                )
                drawRect(
                    color = Color(0xFF3E2723),
                    topLeft = Offset(0f, 183f * sy),
                    size = Size(w, 41f * sy)
                )

                // 3. Enemy Patrol Sprite
                val ex = enemyX * sx
                val ey = 160f * sy
                drawCircle(
                    color = RetroRed,
                    radius = 9f * sx,
                    center = Offset(ex, ey)
                )
                drawCircle(
                    color = Color.White,
                    radius = 3f * sx,
                    center = Offset(ex - 3f * sx, ey - 2f * sy)
                )

                // 4. Player Sprite (Customized per game)
                val px = playerX * sx
                val py = playerY * sy

                when {
                    gameTitle.contains("Mario", ignoreCase = true) -> {
                        // Red cap
                        drawRect(color = RetroRed, topLeft = Offset(px - 6f * sx, py - 18f * sy), size = Size(14f * sx, 6f * sy))
                        // Face
                        drawRect(color = Color(0xFFFFCC80), topLeft = Offset(px - 4f * sx, py - 12f * sy), size = Size(10f * sx, 6f * sy))
                        // Blue Overalls & Red Shirt
                        drawRect(color = RetroRed, topLeft = Offset(px - 8f * sx, py - 6f * sy), size = Size(16f * sx, 8f * sy))
                        drawRect(color = RetroBlue, topLeft = Offset(px - 6f * sx, py + 2f * sy), size = Size(12f * sx, 10f * sy))
                    }
                    gameTitle.contains("Zelda", ignoreCase = true) -> {
                        // Green Tunic & Cap
                        drawRect(color = RetroGreen, topLeft = Offset(px - 6f * sx, py - 18f * sy), size = Size(12f * sx, 8f * sy))
                        // Face
                        drawRect(color = Color(0xFFFFCC80), topLeft = Offset(px - 4f * sx, py - 10f * sy), size = Size(8f * sx, 6f * sy))
                        // Tunic & Shield
                        drawRect(color = RetroGreen, topLeft = Offset(px - 6f * sx, py - 4f * sy), size = Size(12f * sx, 12f * sy))
                        drawRect(color = RetroBlue, topLeft = Offset(px - 10f * sx, py - 2f * sy), size = Size(4f * sx, 10f * sy))
                        if (isAttacking) {
                            // Sword slash beam
                            drawRect(color = RetroWhite, topLeft = Offset(px + 8f * sx, py - 2f * sy), size = Size(16f * sx, 4f * sy))
                        }
                    }
                    gameTitle.contains("Metroid", ignoreCase = true) -> {
                        // Samus Varia Suit Helmet
                        drawRect(color = Color(0xFFFF9800), topLeft = Offset(px - 6f * sx, py - 18f * sy), size = Size(12f * sx, 8f * sy))
                        // Green Visor
                        drawRect(color = Color(0xFF00E676), topLeft = Offset(px - 2f * sx, py - 14f * sy), size = Size(8f * sx, 3f * sy))
                        // Golden Torso & Arm Cannon
                        drawRect(color = Color(0xFFFFB300), topLeft = Offset(px - 8f * sx, py - 10f * sy), size = Size(16f * sx, 14f * sy))
                        drawRect(color = Color(0xFF00E676), topLeft = Offset(px + 8f * sx, py - 6f * sy), size = Size(8f * sx, 6f * sy))
                        if (isAttacking) {
                            // Plasma laser beam
                            drawCircle(color = RetroYellow, radius = 5f * sx, center = Offset(px + 24f * sx, py - 3f * sy))
                        }
                    }
                    gameTitle.contains("Crash", ignoreCase = true) -> {
                        // Orange Bandicoot Head & Ears
                        drawRect(color = Color(0xFFFF6D00), topLeft = Offset(px - 6f * sx, py - 18f * sy), size = Size(12f * sx, 10f * sy))
                        // Muzzle
                        drawRect(color = Color(0xFFFFCC80), topLeft = Offset(px - 4f * sx, py - 10f * sy), size = Size(8f * sx, 5f * sy))
                        // Blue Jeans & Red Shoes
                        drawRect(color = Color(0xFF2979FF), topLeft = Offset(px - 6f * sx, py - 2f * sy), size = Size(12f * sx, 12f * sy))
                        drawRect(color = RetroRed, topLeft = Offset(px - 7f * sx, py + 10f * sy), size = Size(14f * sx, 4f * sy))
                        if (isAttacking) {
                            // Tornado Spin effect
                            drawCircle(color = RetroWhite.copy(alpha = 0.7f), radius = 16f * sx, center = Offset(px, py))
                        }
                    }
                    else -> {
                        // Mega Man / 16-bit Hero Default
                        drawRect(color = RetroBlue, topLeft = Offset(px - 6f * sx, py - 18f * sy), size = Size(12f * sx, 8f * sy))
                        drawRect(color = Color(0xFFFFCC80), topLeft = Offset(px - 3f * sx, py - 12f * sy), size = Size(8f * sx, 5f * sy))
                        drawRect(color = Color(0xFF00E5FF), topLeft = Offset(px - 7f * sx, py - 6f * sy), size = Size(14f * sx, 12f * sy))
                        drawRect(color = RetroBlue, topLeft = Offset(px - 6f * sx, py + 6f * sy), size = Size(12f * sx, 8f * sy))
                        if (isAttacking) {
                            // Mega Buster Shot
                            drawCircle(color = Color(0xFF00E5FF), radius = 6f * sx, center = Offset(px + 20f * sx, py - 2f * sy))
                        }
                    }
                }

                // 5. CRT Scanline Raster lines
                for (lineIndex in 0 until 112) {
                    val lineY = (lineIndex * 2) * (h / 224f)
                    drawRect(
                        color = Color.Black.copy(alpha = 0.20f),
                        topLeft = Offset(0f, lineY),
                        size = Size(w, 1.4f)
                    )
                }

                // CRT Moving Phosphor Beam
                val beamOffset = (scanlineY * h)
                drawRect(
                    color = Color.White.copy(alpha = 0.04f),
                    topLeft = Offset(0f, beamOffset),
                    size = Size(w, 24f)
                )
            }

            // Top Retro Game OSD Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.85f))
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = gameInfo.title.uppercase(),
                    style = TextStyle(
                        fontFamily = PressStart2PFontFamily,
                        fontSize = 10.sp,
                        color = RetroYellow
                    )
                )
                Text(
                    text = "SCORE: $score",
                    style = TextStyle(
                        fontFamily = PressStart2PFontFamily,
                        fontSize = 10.sp,
                        color = RetroWhite
                    )
                )
                Text(
                    text = "★ x$itemsCount",
                    style = TextStyle(
                        fontFamily = PressStart2PFontFamily,
                        fontSize = 10.sp,
                        color = RetroGreen
                    )
                )
                Text(
                    text = "♥ x$lives",
                    style = TextStyle(
                        fontFamily = PressStart2PFontFamily,
                        fontSize = 10.sp,
                        color = RetroRed
                    )
                )
                Text(
                    text = "60 FPS",
                    style = TextStyle(
                        fontFamily = PressStart2PFontFamily,
                        fontSize = 9.sp,
                        color = Color(0xFF00FF66)
                    )
                )
            }

            // Bottom Controller Legend & Exit Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.Black.copy(alpha = 0.90f))
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🎮 MANDO: ◄ ► Mover | Botón A: Saltar | Botón X: Acción | Atrás: Salir",
                    style = TextStyle(
                        fontFamily = NunitoFontFamily,
                        fontSize = 12.sp,
                        color = TextDim
                    )
                )

                Box(
                    modifier = Modifier
                        .background(PanelDark)
                        .border(width = 2.dp, color = RetroYellow, shape = RectangleShape)
                        .clickable { onExitEmulator() }
                        .focusable()
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                        .testTag("btn_exit_emulator")
                ) {
                    Text(
                        text = "✕ Salir",
                        style = TextStyle(
                            fontFamily = PressStart2PFontFamily,
                            fontSize = 9.sp,
                            color = RetroYellow
                        )
                    )
                }
            }

            // Pause Overlay
            if (isPaused) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.75f)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .background(PanelDark)
                            .border(width = 3.dp, color = RetroYellow, shape = RectangleShape)
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "PAUSA",
                            style = TextStyle(
                                fontFamily = PressStart2PFontFamily,
                                fontSize = 18.sp,
                                color = RetroYellow
                            )
                        )
                        Box(
                            modifier = Modifier
                                .background(RetroYellow)
                                .clickable { isPaused = false }
                                .padding(horizontal = 16.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = "▶ Continuar",
                                style = TextStyle(
                                    fontFamily = PressStart2PFontFamily,
                                    fontSize = 11.sp,
                                    color = RetroBlack
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .border(width = 2.dp, color = LineBorder, shape = RectangleShape)
                                .clickable { onExitEmulator() }
                                .padding(horizontal = 16.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = "✕ Salir a PixelBox",
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
