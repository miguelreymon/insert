package com.example

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.ui.components.DownloadDialog
import com.example.ui.screens.EmulatorScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LibraryScreen
import com.example.ui.theme.BgDark
import com.example.ui.theme.PixelBoxTheme

enum class Screen {
    HOME,
    LIBRARY,
    EMULATOR
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PixelBoxTheme {
                PixelBoxApp()
            }
        }
    }

    /**
     * Map controller buttons A and B to DPAD_CENTER and BACK for retro controllers.
     */
    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (event.keyCode) {
                KeyEvent.KEYCODE_BUTTON_B -> {
                    onBackPressedDispatcher.onBackPressed()
                    return true
                }
            }
        }
        return super.dispatchKeyEvent(event)
    }
}

@Composable
fun PixelBoxApp() {
    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    var selectedGameData by remember { mutableStateOf<Pair<String, String>?>(null) }
    var activeGameForEmulator by remember { mutableStateOf<String?>(null) }
    var installedGames by remember { mutableStateOf(setOf<String>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .safeDrawingPadding()
    ) {
        when (currentScreen) {
            Screen.HOME -> {
                HomeScreen(
                    onNavigateToLibrary = { currentScreen = Screen.LIBRARY }
                )
            }
            Screen.LIBRARY -> {
                LibraryScreen(
                    onGameSelected = { gameTitle, systemName ->
                        selectedGameData = Pair(gameTitle, systemName)
                    },
                    onBackToHome = { currentScreen = Screen.HOME },
                    isGameInstalled = { title -> installedGames.contains(title) }
                )
            }
            Screen.EMULATOR -> {
                activeGameForEmulator?.let { gameTitle ->
                    EmulatorScreen(
                        gameTitle = gameTitle,
                        onExitEmulator = {
                            currentScreen = Screen.LIBRARY
                        }
                    )
                } ?: run {
                    currentScreen = Screen.LIBRARY
                }
            }
        }

        // Modal Download & Info Dialog
        selectedGameData?.let { (gameTitle, systemName) ->
            DownloadDialog(
                gameTitle = gameTitle,
                systemName = systemName,
                isAlreadyInstalled = installedGames.contains(gameTitle),
                onInstallCompleted = { installedTitle ->
                    installedGames = installedGames + installedTitle
                },
                onPlayGame = { playTitle ->
                    selectedGameData = null
                    activeGameForEmulator = playTitle
                    currentScreen = Screen.EMULATOR
                },
                onDismiss = { selectedGameData = null }
            )
        }
    }
}
