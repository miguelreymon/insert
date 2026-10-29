package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.PressStart2PFontFamily
import com.example.ui.theme.RetroYellow

private data class LogoLetter(
    val char: Char,
    val color: Color
)

private val INSERT_LETTERS = "INSERT".map { LogoLetter(it, RetroYellow) }

private val COINS_LETTERS = "COINS".map { LogoLetter(it, RetroYellow) }

@Composable
private fun RetroLetter(
    letter: LogoLetter,
    fontSize: TextUnit = 50.sp,
    strokeRadius: Dp = 4.dp,
    shadowOffset: Dp = 7.dp,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.padding(horizontal = 2.dp)
    ) {
        val baseStyle = TextStyle(
            fontFamily = PressStart2PFontFamily,
            fontSize = fontSize,
            lineHeight = fontSize
        )

        // Downward drop shadow in pure black
        Text(
            text = letter.char.toString(),
            style = baseStyle.copy(color = Color.Black),
            modifier = Modifier.offset(y = shadowOffset)
        )
        Text(
            text = letter.char.toString(),
            style = baseStyle.copy(color = Color.Black),
            modifier = Modifier.offset(y = shadowOffset - 2.dp)
        )

        // Thick black stroke around letter in 8 cardinal and diagonal directions
        val offsets = listOf(
            Pair(-strokeRadius, -strokeRadius),
            Pair(0.dp, -strokeRadius),
            Pair(strokeRadius, -strokeRadius),
            Pair(-strokeRadius, 0.dp),
            Pair(strokeRadius, 0.dp),
            Pair(-strokeRadius, strokeRadius),
            Pair(0.dp, strokeRadius),
            Pair(strokeRadius, strokeRadius)
        )
        for ((dx, dy) in offsets) {
            Text(
                text = letter.char.toString(),
                style = baseStyle.copy(color = Color.Black),
                modifier = Modifier.offset(x = dx, y = dy)
            )
        }

        // Foreground colored character
        Text(
            text = letter.char.toString(),
            style = baseStyle.copy(color = letter.color)
        )
    }
}

@Composable
fun RetroLogo(
    fontSize: TextUnit = 50.sp,
    singleLine: Boolean = false,
    modifier: Modifier = Modifier
) {
    val stroke = if (singleLine) 2.dp else 4.dp
    val shadow = if (singleLine) 4.dp else 7.dp

    if (singleLine) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                INSERT_LETTERS.forEach { letter ->
                    RetroLetter(
                        letter = letter,
                        fontSize = fontSize,
                        strokeRadius = stroke,
                        shadowOffset = shadow
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                COINS_LETTERS.forEach { letter ->
                    RetroLetter(
                        letter = letter,
                        fontSize = fontSize,
                        strokeRadius = stroke,
                        shadowOffset = shadow
                    )
                }
            }
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = modifier
        ) {
            // Line 1: "INSERT"
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                INSERT_LETTERS.forEach { letter ->
                    RetroLetter(letter = letter, fontSize = fontSize)
                }
            }

            // Line 2: "COINS"
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                COINS_LETTERS.forEach { letter ->
                    RetroLetter(letter = letter, fontSize = fontSize)
                }
            }
        }
    }
}
