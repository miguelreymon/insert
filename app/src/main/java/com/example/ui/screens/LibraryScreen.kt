package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.GameInfo
import com.example.model.RetroSystem
import com.example.model.SYSTEM_LIST
import com.example.model.getGameInfo
import com.example.model.searchGames
import com.example.ui.components.CoverArt
import com.example.ui.components.RetroLogo
import com.example.ui.theme.BgDark
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

@Composable
fun LibraryScreen(
    onGameSelected: (gameTitle: String, systemName: String) -> Unit,
    onBackToHome: () -> Unit,
    isGameInstalled: (String) -> Boolean = { false },
    modifier: Modifier = Modifier
) {
    var selectedSystemIndex by remember { mutableIntStateOf(0) }
    val currentSystem = SYSTEM_LIST[selectedSystemIndex]
    val firstTabFocusRequester = remember { FocusRequester() }

    // Dialogs & Drawer state
    var isDrawerOpen by remember { mutableStateOf(false) }
    var showPremiumDialog by remember { mutableStateOf(false) }
    var showProfileDialog by remember { mutableStateOf(false) }
    var showSettingsDialog by remember { mutableStateOf(false) }

    // Search state
    var searchQuery by remember { mutableStateOf("") }

    // Handle back button: close drawer or dialogs first, or return to home
    BackHandler {
        when {
            isDrawerOpen -> isDrawerOpen = false
            searchQuery.isNotEmpty() -> searchQuery = ""
            showPremiumDialog -> showPremiumDialog = false
            showProfileDialog -> showProfileDialog = false
            showSettingsDialog -> showSettingsDialog = false
            else -> onBackToHome()
        }
    }

    LaunchedEffect(Unit) {
        try {
            firstTabFocusRequester.requestFocus()
        } catch (_: Exception) {}
    }

    // Filter games either by search query across catalog or by selected system
    val displayedGameInfos: List<GameInfo> = remember(searchQuery, selectedSystemIndex) {
        if (searchQuery.isNotBlank()) {
            searchGames(searchQuery)
        } else {
            currentSystem.games.map { title -> getGameInfo(title, currentSystem.name) }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BgDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 10.dp)
        ) {
            // =================================================================
            // 1. TOP HEADER (Exact user specification)
            // Left: Menu icon -> Premium icon -> Center: Retro Logo -> Right: Profile icon -> Settings icon
            // =================================================================
            TopHeaderBar(
                onOpenDrawer = { isDrawerOpen = true },
                onOpenPremium = { showPremiumDialog = true },
                onOpenProfile = { showProfileDialog = true },
                onOpenSettings = { showSettingsDialog = true }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // =================================================================
            // 2. BUSCADOR (Search Bar for romsenespanol.com)
            // =================================================================
            RetroSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onClear = { searchQuery = "" }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // =================================================================
            // 3. SYSTEM TABS (Visible when not actively searching)
            // =================================================================
            if (searchQuery.isBlank()) {
                val tabScrollState = rememberScrollState()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(tabScrollState)
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SYSTEM_LIST.forEachIndexed { index, system ->
                        val isSelected = (index == selectedSystemIndex)
                        SystemTabItem(
                            system = system,
                            isSelected = isSelected,
                            focusRequester = if (index == 0) firstTabFocusRequester else null,
                            onClick = { selectedSystemIndex = index }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Shelf Title & Game Count
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = currentSystem.name,
                            style = TextStyle(
                                fontFamily = PressStart2PFontFamily,
                                fontSize = 15.sp,
                                color = TextInk
                            ),
                            modifier = Modifier.testTag("shelf_title")
                        )
                        Box(
                            modifier = Modifier
                                .background(currentSystem.color.copy(alpha = 0.2f), shape = RoundedCornerShape(4.dp))
                                .border(1.dp, currentSystem.color, RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "romsenespanol.com",
                                style = TextStyle(
                                    fontFamily = NunitoFontFamily,
                                    fontSize = 11.sp,
                                    color = currentSystem.color,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                    Text(
                        text = "${displayedGameInfos.size} juegos en español",
                        style = TextStyle(
                            fontFamily = NunitoFontFamily,
                            fontSize = 13.sp,
                            color = TextDim
                        )
                    )
                }
            } else {
                // Search Results Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Resultados para '$searchQuery' (${displayedGameInfos.size} juegos)",
                        style = TextStyle(
                            fontFamily = PressStart2PFontFamily,
                            fontSize = 12.sp,
                            color = RetroYellow
                        )
                    )
                    Box(
                        modifier = Modifier
                            .clickable { searchQuery = "" }
                            .background(PanelDark)
                            .border(1.dp, RetroRed, RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Limpiar Búsqueda ✕",
                            style = TextStyle(
                                fontFamily = NunitoFontFamily,
                                fontSize = 12.sp,
                                color = RetroRed,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // =================================================================
            // 4. GRID OF GAME COVERS
            // =================================================================
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 4.dp, bottom = 12.dp)
            ) {
                itemsIndexed(displayedGameInfos) { index, gameInfo ->
                    val systemMatch = SYSTEM_LIST.find { it.name.equals(gameInfo.system, ignoreCase = true) }
                    val baseHue = systemMatch?.baseHue ?: 48f

                    GameCard(
                        gameInfo = gameInfo,
                        baseHue = baseHue,
                        gameIndex = index,
                        isInstalled = isGameInstalled(gameInfo.title),
                        onClick = { onGameSelected(gameInfo.title, gameInfo.system) }
                    )
                }

                // Bottom informational note referencing romsenespanol.com
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .background(PanelDark)
                            .border(
                                width = 3.dp,
                                color = LineBorder,
                                shape = RectangleShape
                            )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Left green vertical accent bar
                            Box(
                                modifier = Modifier
                                    .width(6.dp)
                                    .height(56.dp)
                                    .background(RetroGreen)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 14.dp, vertical = 10.dp)
                                    .weight(1f)
                            ) {
                                Text(
                                    text = "CATÁLOGO 100% EN ESPAÑOL • romsenespanol.com",
                                    style = TextStyle(
                                        fontFamily = PressStart2PFontFamily,
                                        fontSize = 9.sp,
                                        color = RetroGreen
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Todos los juegos y carátulas corresponden a traducciones al español verificadas. Voces y textos en castellano de la comunidad.",
                                    style = TextStyle(
                                        fontFamily = NunitoFontFamily,
                                        fontSize = 13.sp,
                                        lineHeight = 18.sp,
                                        color = TextDim
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        // =====================================================================
        // 5. LATERAL MENU (Drawer on Left)
        // =====================================================================
        AnimatedVisibility(
            visible = isDrawerOpen,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            // Dark Scrim Background
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f))
                    .clickable { isDrawerOpen = false }
            )
        }

        AnimatedVisibility(
            visible = isDrawerOpen,
            enter = slideInHorizontally(initialOffsetX = { -it }),
            exit = slideOutHorizontally(targetOffsetX = { -it })
        ) {
            LateralMenuDrawer(
                onClose = { isDrawerOpen = false },
                onSelectSystem = { sysIndex ->
                    selectedSystemIndex = sysIndex
                    searchQuery = ""
                    isDrawerOpen = false
                },
                onOpenPremium = {
                    isDrawerOpen = false
                    showPremiumDialog = true
                },
                onOpenProfile = {
                    isDrawerOpen = false
                    showProfileDialog = true
                },
                onOpenSettings = {
                    isDrawerOpen = false
                    showSettingsDialog = true
                },
                onBackToHome = {
                    isDrawerOpen = false
                    onBackToHome()
                }
            )
        }

        // =====================================================================
        // 6. MODAL DIALOGS (Premium, Profile, Settings)
        // =====================================================================
        if (showPremiumDialog) {
            PremiumDialog(onDismiss = { showPremiumDialog = false })
        }

        if (showProfileDialog) {
            ProfileDialog(onDismiss = { showProfileDialog = false })
        }

        if (showSettingsDialog) {
            SettingsDialog(onDismiss = { showSettingsDialog = false })
        }
    }
}

/**
 * Top Header bar strictly according to user's layout specification:
 * Far Left: Menu -> Premium icon -> Center: Logo -> Profile icon -> Settings icon
 */
@Composable
private fun TopHeaderBar(
    onOpenDrawer: () -> Unit,
    onOpenPremium: () -> Unit,
    onOpenProfile: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(PanelDark)
            .border(width = 2.dp, color = LineBorder, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        // Left side: Lateral Menu Button + Premium Options Button
        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RetroHeaderIconButton(
                icon = Icons.Default.Menu,
                label = "Menú",
                tint = RetroWhite,
                onClick = onOpenDrawer,
                testTag = "header_btn_menu"
            )
            RetroHeaderIconButton(
                icon = Icons.Default.WorkspacePremium,
                label = "VIP",
                tint = RetroYellow,
                onClick = onOpenPremium,
                testTag = "header_btn_premium"
            )
        }

        // Center: App Logo (compact single-line all yellow with drop shadow)
        Box(
            modifier = Modifier.align(Alignment.Center)
        ) {
            RetroLogo(
                fontSize = 18.sp,
                singleLine = true,
                modifier = Modifier.testTag("header_logo")
            )
        }

        // Right side: User Profile Button + Settings Button
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RetroHeaderIconButton(
                icon = Icons.Default.AccountCircle,
                label = "Perfil",
                tint = RetroWhite,
                onClick = onOpenProfile,
                testTag = "header_btn_profile"
            )
            RetroHeaderIconButton(
                icon = Icons.Default.Settings,
                label = "Ajustes",
                tint = RetroWhite,
                onClick = onOpenSettings,
                testTag = "header_btn_settings"
            )
        }
    }
}

@Composable
private fun RetroHeaderIconButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    tint: Color,
    onClick: () -> Unit,
    testTag: String,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .onFocusChanged { isFocused = it.isFocused }
            .border(
                width = 2.dp,
                color = if (isFocused) RetroYellow else LineBorder,
                shape = RoundedCornerShape(6.dp)
            )
            .background(
                if (isFocused) RetroYellow else BgDark,
                shape = RoundedCornerShape(6.dp)
            )
            .clickable { onClick() }
            .focusable()
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .testTag(testTag),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isFocused) RetroBlack else tint,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = label,
                style = TextStyle(
                    fontFamily = PressStart2PFontFamily,
                    fontSize = 9.sp,
                    color = if (isFocused) RetroBlack else RetroWhite
                )
            )
        }
    }
}

/**
 * Retro-styled search bar for romsenespanol.com catalog
 */
@Composable
private fun RetroSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        placeholder = {
            Text(
                text = "Buscar en romsenespanol.com (ej: Mario, Zelda, Crash, Castlevania)...",
                style = TextStyle(
                    fontFamily = NunitoFontFamily,
                    fontSize = 14.sp,
                    color = TextDim
                )
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar",
                tint = RetroYellow,
                modifier = Modifier.size(22.dp)
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Limpiar",
                        tint = RetroWhite,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        textStyle = TextStyle(
            fontFamily = NunitoFontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = RetroWhite
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = RetroYellow,
            unfocusedBorderColor = LineBorder,
            focusedContainerColor = BgDark,
            unfocusedContainerColor = PanelDark,
            cursorColor = RetroYellow
        ),
        modifier = modifier
            .fillMaxWidth()
            .testTag("game_search_bar")
    )
}

/**
 * Lateral Navigation Drawer (Side Menu)
 */
@Composable
private fun LateralMenuDrawer(
    onClose: () -> Unit,
    onSelectSystem: (Int) -> Unit,
    onOpenPremium: () -> Unit,
    onOpenProfile: () -> Unit,
    onOpenSettings: () -> Unit,
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(320.dp)
            .background(PanelDark)
            .border(width = 3.dp, color = LineBorder, shape = RectangleShape)
            .padding(18.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "INSERT COINS",
                            style = TextStyle(
                                fontFamily = PressStart2PFontFamily,
                                fontSize = 13.sp,
                                color = RetroYellow
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "romsenespanol.com",
                            style = TextStyle(
                                fontFamily = NunitoFontFamily,
                                fontSize = 12.sp,
                                color = RetroGreen,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clickable { onClose() }
                            .border(1.dp, LineBorder, RoundedCornerShape(4.dp))
                            .background(BgDark)
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "✕",
                            style = TextStyle(
                                fontFamily = PressStart2PFontFamily,
                                fontSize = 12.sp,
                                color = RetroWhite
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(LineBorder)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Quick Console Jumps
            item {
                Text(
                    text = "CONSOLAS RETRO",
                    style = TextStyle(
                        fontFamily = PressStart2PFontFamily,
                        fontSize = 9.sp,
                        color = TextDim
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            items(SYSTEM_LIST.size) { idx ->
                val sys = SYSTEM_LIST[idx]
                DrawerSystemItem(
                    system = sys,
                    onClick = { onSelectSystem(idx) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(LineBorder)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "OPCIONES & CUENTA",
                    style = TextStyle(
                        fontFamily = PressStart2PFontFamily,
                        fontSize = 9.sp,
                        color = TextDim
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            // Premium VIP
            item {
                DrawerActionItem(
                    title = "Opciones Premium VIP",
                    icon = Icons.Default.WorkspacePremium,
                    tint = RetroYellow,
                    onClick = onOpenPremium
                )
            }

            // User Profile
            item {
                DrawerActionItem(
                    title = "Perfil de Usuario",
                    icon = Icons.Default.AccountCircle,
                    tint = RetroBlue,
                    onClick = onOpenProfile
                )
            }

            // Settings
            item {
                DrawerActionItem(
                    title = "Ajustes de Emulación",
                    icon = Icons.Default.Settings,
                    tint = RetroWhite,
                    onClick = onOpenSettings
                )
            }

            // Exit
            item {
                Spacer(modifier = Modifier.height(10.dp))
                DrawerActionItem(
                    title = "Cerrar y Volver a Inicio",
                    icon = Icons.Default.Close,
                    tint = RetroRed,
                    onClick = onBackToHome
                )
            }
        }
    }
}

@Composable
private fun DrawerSystemItem(
    system: RetroSystem,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = it.isFocused }
            .border(
                width = 2.dp,
                color = if (isFocused) RetroYellow else LineBorder,
                shape = RoundedCornerShape(4.dp)
            )
            .background(
                if (isFocused) system.color else BgDark,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClick() }
            .focusable()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = system.iconResId),
                contentDescription = system.name,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = system.name,
                style = TextStyle(
                    fontFamily = PressStart2PFontFamily,
                    fontSize = 9.sp,
                    color = if (isFocused) RetroBlack else TextInk
                )
            )
        }
    }
}

@Composable
private fun DrawerActionItem(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    tint: Color,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = it.isFocused }
            .border(
                width = 2.dp,
                color = if (isFocused) RetroYellow else LineBorder,
                shape = RoundedCornerShape(4.dp)
            )
            .background(
                if (isFocused) RetroYellow else BgDark,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClick() }
            .focusable()
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (isFocused) RetroBlack else tint,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = PressStart2PFontFamily,
                    fontSize = 9.sp,
                    color = if (isFocused) RetroBlack else TextInk
                )
            )
        }
    }
}

@Composable
private fun SystemTabItem(
    system: RetroSystem,
    isSelected: Boolean,
    focusRequester: FocusRequester?,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    val bgColor = if (isSelected) system.color else PanelDark
    val textColor = if (isSelected) RetroBlack else TextInk

    Box(
        modifier = Modifier
            .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier)
            .onFocusChanged {
                isFocused = it.isFocused
                if (it.isFocused) {
                    onClick()
                }
            }
            .background(bgColor)
            .border(
                width = 3.dp,
                color = if (isFocused) RetroYellow else system.color,
                shape = RectangleShape
            )
            .clickable { onClick() }
            .focusable()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .testTag("tab_${system.id}"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            Image(
                painter = painterResource(id = system.iconResId),
                contentDescription = system.name,
                modifier = Modifier.size(42.dp)
            )
            Text(
                text = system.name,
                style = TextStyle(
                    fontFamily = PressStart2PFontFamily,
                    fontSize = 10.sp,
                    color = textColor
                )
            )
        }
    }
}

@Composable
private fun GameCard(
    gameInfo: GameInfo,
    baseHue: Float,
    gameIndex: Int,
    isInstalled: Boolean,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.03f else 1.0f,
        label = "card_scale"
    )
    val translateY by animateDpAsState(
        targetValue = if (isFocused) (-6).dp else 0.dp,
        label = "card_translation"
    )

    Box(
        modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.translationY = translateY.toPx()
            }
            .background(PanelDark)
            .border(
                width = 3.dp,
                color = if (isFocused) RetroYellow else LineBorder,
                shape = RectangleShape
            )
            .clickable { onClick() }
            .focusable()
            .onFocusChanged { isFocused = it.isFocused }
            .padding(8.dp)
            .testTag("game_card_$gameIndex")
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                // Cover Art (Scraped box art or procedural geometric canvas)
                CoverArt(
                    gameTitle = gameInfo.title,
                    baseHue = baseHue,
                    gameIndex = gameIndex,
                    coverResId = gameInfo.coverResId,
                    modifier = Modifier.fillMaxWidth()
                )

                // Spanish translation badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(4.dp)
                        .background(Color.Black.copy(alpha = 0.85f))
                        .border(width = 1.dp, color = RetroYellow, shape = RectangleShape)
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "ESPAÑOL",
                        style = TextStyle(
                            fontFamily = PressStart2PFontFamily,
                            fontSize = 6.sp,
                            color = RetroYellow
                        )
                    )
                }

                if (isInstalled) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                            .background(Color.Black.copy(alpha = 0.85f))
                            .border(width = 1.dp, color = RetroGreen, shape = RectangleShape)
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "✓ LISTO",
                            style = TextStyle(
                                fontFamily = PressStart2PFontFamily,
                                fontSize = 7.sp,
                                color = RetroGreen
                            )
                        )
                    }
                }
            }

            // Game title in Press Start 2P 9sp
            Text(
                text = gameInfo.title,
                style = TextStyle(
                    fontFamily = PressStart2PFontFamily,
                    fontSize = 9.sp,
                    lineHeight = 15.sp,
                    color = TextInk
                ),
                minLines = 2,
                maxLines = 2,
                modifier = Modifier.padding(top = 10.dp, bottom = 4.dp)
            )

            // System name & genre in small dim text
            Text(
                text = "${gameInfo.system} • ${gameInfo.genre}",
                style = TextStyle(
                    fontFamily = NunitoFontFamily,
                    fontSize = 11.sp,
                    color = TextDim
                ),
                maxLines = 1
            )
        }
    }
}

/**
 * Premium VIP perks modal dialog
 */
@Composable
private fun PremiumDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Box(
            modifier = Modifier
                .widthIn(min = 460.dp, max = 560.dp)
                .background(PanelDark)
                .border(width = 4.dp, color = RetroYellow, shape = RectangleShape)
                .padding(22.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "👑 INSERT COINS VIP",
                        style = TextStyle(
                            fontFamily = PressStart2PFontFamily,
                            fontSize = 13.sp,
                            color = RetroYellow
                        )
                    )
                    Text(
                        text = "romsenespanol.com",
                        style = TextStyle(
                            fontFamily = NunitoFontFamily,
                            fontSize = 12.sp,
                            color = RetroGreen,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Text(
                    text = "Accede a características exclusivas diseñadas para amantes de los videojuegos retro en Android TV:",
                    style = TextStyle(
                        fontFamily = NunitoFontFamily,
                        fontSize = 13.sp,
                        color = TextDim
                    )
                )

                // Perks
                val perks = listOf(
                    "⚡ Descargas ultra-rápidas directas sin límites",
                    "☁️ Nube de partidas guardadas (Cloud Save States)",
                    "📺 Shaders CRT 4K retro avanzados y filtros scanline",
                    "🌐 Netplay multijugador online sin lag con mandos TV",
                    "⭐ Acceso anticipado a nuevas traducciones de ROMs"
                )
                perks.forEach { perk ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = perk,
                            style = TextStyle(
                                fontFamily = NunitoFontFamily,
                                fontSize = 13.sp,
                                color = TextInk,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(RetroYellow)
                            .clickable { onDismiss() }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "ACTIVAR VIP GRATIS",
                            style = TextStyle(
                                fontFamily = PressStart2PFontFamily,
                                fontSize = 10.sp,
                                color = RetroBlack
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(BgDark)
                            .border(2.dp, LineBorder, RectangleShape)
                            .clickable { onDismiss() }
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "CERRAR",
                            style = TextStyle(
                                fontFamily = PressStart2PFontFamily,
                                fontSize = 10.sp,
                                color = TextDim
                            )
                        )
                    }
                }
            }
        }
    }
}

/**
 * User Profile modal dialog
 */
@Composable
private fun ProfileDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Box(
            modifier = Modifier
                .widthIn(min = 440.dp, max = 540.dp)
                .background(PanelDark)
                .border(width = 4.dp, color = RetroBlue, shape = RectangleShape)
                .padding(22.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "👤 PERFIL DE USUARIO",
                        style = TextStyle(
                            fontFamily = PressStart2PFontFamily,
                            fontSize = 13.sp,
                            color = RetroBlue
                        )
                    )
                    Box(
                        modifier = Modifier
                            .background(RetroGreen.copy(alpha = 0.2f))
                            .border(1.dp, RetroGreen, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "ONLINE",
                            style = TextStyle(
                                fontFamily = PressStart2PFontFamily,
                                fontSize = 8.sp,
                                color = RetroGreen
                            )
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .background(BgDark, shape = CircleShape)
                            .border(2.dp, RetroYellow, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "👾",
                            fontSize = 28.sp
                        )
                    }

                    Column {
                        Text(
                            text = "PixelGamer_ES",
                            style = TextStyle(
                                fontFamily = PressStart2PFontFamily,
                                fontSize = 12.sp,
                                color = TextInk
                            )
                        )
                        Text(
                            text = "Nivel 42 • Maestro Retro 16-Bits",
                            style = TextStyle(
                                fontFamily = NunitoFontFamily,
                                fontSize = 13.sp,
                                color = RetroYellow,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(LineBorder)
                )

                // Stats
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "ROMs Jugadas",
                            style = TextStyle(fontFamily = NunitoFontFamily, fontSize = 12.sp, color = TextDim)
                        )
                        Text(
                            text = "34 en español",
                            style = TextStyle(fontFamily = PressStart2PFontFamily, fontSize = 10.sp, color = TextInk)
                        )
                    }
                    Column {
                        Text(
                            text = "Horas de Juego",
                            style = TextStyle(fontFamily = NunitoFontFamily, fontSize = 12.sp, color = TextDim)
                        )
                        Text(
                            text = "128 horas",
                            style = TextStyle(fontFamily = PressStart2PFontFamily, fontSize = 10.sp, color = TextInk)
                        )
                    }
                    Column {
                        Text(
                            text = "Favorito",
                            style = TextStyle(fontFamily = NunitoFontFamily, fontSize = 12.sp, color = TextDim)
                        )
                        Text(
                            text = "Super Nintendo",
                            style = TextStyle(fontFamily = PressStart2PFontFamily, fontSize = 10.sp, color = RetroYellow)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BgDark)
                        .border(2.dp, LineBorder, RectangleShape)
                        .clickable { onDismiss() }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CERRAR",
                        style = TextStyle(
                            fontFamily = PressStart2PFontFamily,
                            fontSize = 10.sp,
                            color = TextDim
                        )
                    )
                }
            }
        }
    }
}

/**
 * Settings modal dialog
 */
@Composable
private fun SettingsDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Box(
            modifier = Modifier
                .widthIn(min = 460.dp, max = 560.dp)
                .background(PanelDark)
                .border(width = 4.dp, color = RetroWhite, shape = RectangleShape)
                .padding(22.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "⚙️ AJUSTES DEL SISTEMA",
                    style = TextStyle(
                        fontFamily = PressStart2PFontFamily,
                        fontSize = 13.sp,
                        color = RetroWhite
                    )
                )

                val settings = listOf(
                    Pair("Resolución de Emulación", "1080p Nativo / 60 FPS"),
                    Pair("Filtro de Pantalla", "Scanlines CRT Suave (Retro)"),
                    Pair("Audio", "Estéreo Alta Fidelidad 44.1 kHz"),
                    Pair("Configuración de Mandos", "Gamepad Bluetooth / Mando TV"),
                    Pair("Servidor de Descargas", "romsenespanol.com (España/LatAm)"),
                    Pair("Guardado de Estados", "Automático al salir")
                )

                settings.forEach { (label, value) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = label,
                            style = TextStyle(fontFamily = NunitoFontFamily, fontSize = 13.sp, color = TextInk)
                        )
                        Box(
                            modifier = Modifier
                                .background(BgDark)
                                .border(1.dp, LineBorder, RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = value,
                                style = TextStyle(fontFamily = NunitoFontFamily, fontSize = 12.sp, color = RetroYellow, fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(RetroWhite)
                        .clickable { onDismiss() }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "GUARDAR Y SALIR",
                        style = TextStyle(
                            fontFamily = PressStart2PFontFamily,
                            fontSize = 10.sp,
                            color = RetroBlack
                        )
                    )
                }
            }
        }
    }
}
