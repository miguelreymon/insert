package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.hslToColor
import com.example.ui.theme.PressStart2PFontFamily

fun splitTitleIntoLines(title: String, maxChars: Int = 9): List<String> {
    val words = title.split(" ")
    val lines = mutableListOf<String>()
    var cur = ""
    for (word in words) {
        val candidate = if (cur.isEmpty()) word else "$cur $word"
        if (candidate.length > maxChars && cur.isNotEmpty()) {
            lines.add(cur)
            cur = word
        } else {
            cur = candidate
        }
    }
    if (cur.isNotEmpty()) {
        lines.add(cur)
    }
    return lines
}

@Composable
fun CoverArt(
    gameTitle: String,
    baseHue: Float,
    gameIndex: Int,
    coverResId: Int? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.aspectRatio(200f / 270f)
    ) {
        if (coverResId != null) {
            // Render real scraped box art image
            Image(
                painter = painterResource(id = coverResId),
                contentDescription = "Carátula de $gameTitle",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Subtle dark vignette at bottom for readability
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f)
                    .align(Alignment.BottomCenter)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.65f))
                        )
                    )
                }
            }
        } else {
            // Fallback: Procedural geometric retro canvas
            val lines = remember(gameTitle) { splitTitleIntoLines(gameTitle, 9) }
            val colorA = remember(baseHue) { hslToColor(baseHue, 0.80f, 0.52f) }
            val colorB = remember(baseHue) { hslToColor((baseHue + 50f) % 360f, 0.85f, 0.22f) }
            val colorS = remember(baseHue) { hslToColor((baseHue + 160f) % 360f, 0.90f, 0.62f) }

            Canvas(modifier = Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height
                val sx = w / 200f
                val sy = h / 270f
                val sAvg = (sx + sy) / 2f

                // 1. Vertical Gradient Background
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(colorA, colorB),
                        startY = 0f,
                        endY = h
                    ),
                    size = Size(w, h)
                )

                // 2. Shapes depending on gameIndex % 4
                when (gameIndex % 4) {
                    0 -> {
                        // Circle at (100, 95), r = 52
                        drawCircle(
                            color = colorS,
                            radius = 52f * sAvg,
                            center = Offset(100f * sx, 95f * sy)
                        )
                        // Semi-transparent rect at (0, 130), w=200, h=40
                        drawRect(
                            color = colorB.copy(alpha = 0.7f),
                            topLeft = Offset(0f, 130f * sy),
                            size = Size(w, 40f * sy)
                        )
                    }
                    1 -> {
                        // Outer Triangle: (100,30), (160,140), (40,140)
                        val outerPath = Path().apply {
                            moveTo(100f * sx, 30f * sy)
                            lineTo(160f * sx, 140f * sy)
                            lineTo(40f * sx, 140f * sy)
                            close()
                        }
                        drawPath(outerPath, color = colorS)

                        // Inner Triangle: (100,60), (135,125), (65,125)
                        val innerPath = Path().apply {
                            moveTo(100f * sx, 60f * sy)
                            lineTo(135f * sx, 125f * sy)
                            lineTo(65f * sx, 125f * sy)
                            close()
                        }
                        drawPath(innerPath, color = colorA)
                    }
                    2 -> {
                        // Outer Rect: (45, 45, 110, 90)
                        drawRect(
                            color = colorS,
                            topLeft = Offset(45f * sx, 45f * sy),
                            size = Size(110f * sx, 90f * sy)
                        )
                        // Middle Rect: (65, 65, 70, 50)
                        drawRect(
                            color = colorB,
                            topLeft = Offset(65f * sx, 65f * sy),
                            size = Size(70f * sx, 50f * sy)
                        )
                        // Inner Rect: (85, 85, 30, 10)
                        drawRect(
                            color = colorS,
                            topLeft = Offset(85f * sx, 85f * sy),
                            size = Size(30f * sx, 10f * sy)
                        )
                    }
                    3 -> {
                        // Mountains path: M0 140 L50 80 L90 120 L140 50 L200 130 V170 H0Z
                        val mountainPath = Path().apply {
                            moveTo(0f, 140f * sy)
                            lineTo(50f * sx, 80f * sy)
                            lineTo(90f * sx, 120f * sy)
                            lineTo(140f * sx, 50f * sy)
                            lineTo(200f * sx, 130f * sy)
                            lineTo(200f * sx, 170f * sy)
                            lineTo(0f, 170f * sy)
                            close()
                        }
                        drawPath(mountainPath, color = colorS)

                        // White circle at (150, 40), r=18
                        drawCircle(
                            color = Color.White,
                            radius = 18f * sAvg,
                            center = Offset(150f * sx, 40f * sy)
                        )
                    }
                }

                // 3. Dark Overlay Band in lower third: y = 170 to 270 (height 100)
                drawRect(
                    color = Color.Black.copy(alpha = 0.55f),
                    topLeft = Offset(0f, 170f * sy),
                    size = Size(w, 100f * sy)
                )
            }

            // Title text in bottom band
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(100f / 270f)
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    lines.forEach { line ->
                        Box(contentAlignment = Alignment.Center) {
                            for (dx in listOf(-1, 0, 1)) {
                                for (dy in listOf(-1, 0, 1)) {
                                    if (dx != 0 || dy != 0) {
                                        Text(
                                            text = line,
                                            style = TextStyle(
                                                fontFamily = PressStart2PFontFamily,
                                                fontSize = 9.sp,
                                                color = Color.Black,
                                                textAlign = TextAlign.Center
                                            ),
                                            modifier = Modifier.offset(x = (dx * 1.5f).dp, y = (dy * 1.5f).dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = line,
                                style = TextStyle(
                                    fontFamily = PressStart2PFontFamily,
                                    fontSize = 9.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
