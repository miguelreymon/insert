package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.SYSTEM_LIST
import com.example.ui.components.splitTitleIntoLines
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Insert Coins", appName)
    }

    @Test
    fun `retro systems contain expected count and games`() {
        assertEquals(6, SYSTEM_LIST.size)
        SYSTEM_LIST.forEach { system ->
            assertTrue(system.games.size >= 20)
        }
    }

    @Test
    fun `cover title breaks into lines of max 9 characters`() {
        val lines = splitTitleIntoLines("Guerra de Pelusas", 9)
        assertEquals(2, lines.size)
        assertEquals("Guerra de", lines[0])
        assertEquals("Pelusas", lines[1])
        assertTrue(lines.all { it.length <= 9 })
    }

    @Test
    fun `searchGames correctly filters romsenespanol games`() {
        val marioGames = com.example.model.searchGames("Mario")
        assertTrue(marioGames.isNotEmpty())
        assertTrue(marioGames.any { it.title.contains("Super Mario", ignoreCase = true) })

        val zeldaGames = com.example.model.searchGames("Zelda")
        assertTrue(zeldaGames.isNotEmpty())
        assertTrue(zeldaGames.any { it.title.contains("Ocarina of Time", ignoreCase = true) })

        val ps1Crash = com.example.model.getGameInfo("Crash Bandicoot (Español)", "PlayStation 1")
        assertEquals("PlayStation 1", ps1Crash.system)
        assertTrue(ps1Crash.translationInfo.contains("español", ignoreCase = true))
    }
}
