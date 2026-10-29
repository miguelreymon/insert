package com.example.model

import androidx.compose.ui.graphics.Color
import com.example.R
import kotlin.math.abs

data class GameInfo(
    val title: String,
    val system: String,
    val year: String,
    val genre: String,
    val fileSize: String,
    val description: String,
    val romFileName: String,
    val webGameUrl: String,
    val coverResId: Int? = null,
    val translationInfo: String = "100% Textos y Diálogos en Español (romsenespanol.com)"
)

data class RetroSystem(
    val id: Int,
    val name: String,
    val color: Color,
    val baseHue: Float,
    val iconResId: Int,
    val games: List<String>
)

/**
 * Authentic Spanish retro ROMs catalog sourced directly from romsenespanol.com.
 * Contains 20 iconic Spanish-translated titles for each of the 6 major retro consoles.
 */
val SPECIFIC_GAMES_METADATA = listOf(
    // =========================================================================
    // --- 1. PLAYSTATION 1 (Disco 32 bits / romsenespanol.com) ---
    // =========================================================================
    GameInfo(
        title = "Crash Bandicoot (Español)",
        system = "PlayStation 1",
        year = "1996",
        genre = "Plataformas 3D",
        fileSize = "480 MB",
        description = "¡El clásico de plataformas 3D de Naughty Dog en PlayStation! Guía a Crash por las tres islas Wumpa para rescatar a Tawna y desbaratar los planes del Dr. Neo Cortex. Traducción íntegra al castellano de romsenespanol.com.",
        romFileName = "crash_bandicoot_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        coverResId = R.drawable.cover_crash_bandicoot,
        translationInfo = "Traducción completa al español / castellano (romsenespanol.com)"
    ),
    GameInfo(
        title = "Tekken 2 (Español)",
        system = "PlayStation 1",
        year = "1996",
        genre = "Lucha 3D",
        fileSize = "520 MB",
        description = "La secuela cumbre de Namco. El Torneo del Rey del Puño de Hierro regresa con 25 luchadores, finales cinemáticos y combate a 60 FPS con todos los menús e historias en español.",
        romFileName = "tekken_2_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        coverResId = R.drawable.cover_tekken_2,
        translationInfo = "Menús e historias en español"
    ),
    GameInfo(
        title = "Alundra (Español)",
        system = "PlayStation 1",
        year = "1997",
        genre = "Acción / Aventura RPG",
        fileSize = "340 MB",
        description = "Aclamado RPG de acción y puzles conocido como el 'Zelda de PlayStation'. Controla a Alundra con su habilidad única de entrar en las pesadillas de los habitantes de Inoa. Textos en perfecto español.",
        romFileName = "alundra_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        coverResId = R.drawable.cover_alundra,
        translationInfo = "Textos oficiales en español"
    ),
    GameInfo(
        title = "Bloody Roar 2 (Español)",
        system = "PlayStation 1",
        year = "1999",
        genre = "Lucha / Zoántropos",
        fileSize = "410 MB",
        description = "Combates viscerales 3D donde cada guerrero humano puede transformar su cuerpo en una feroz bestia con habilidades sobrehumanas. Parche en español de romsenespanol.com.",
        romFileName = "bloody_roar_2_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        coverResId = R.drawable.cover_bloody_roar_2,
        translationInfo = "Textos y menús en español"
    ),
    GameInfo(
        title = "Final Fantasy VII (Español)",
        system = "PlayStation 1",
        year = "1997",
        genre = "JRPG Épico",
        fileSize = "1.3 GB",
        description = "La legendaria epopeya de Cloud Strife, Avalancha y Sephiroth en Midgar. Versión revisada en español con corrección ortográfica de romsenespanol.com para una experiencia narrativa sublime.",
        romFileName = "final_fantasy_vii_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Versión revisada en español"
    ),
    GameInfo(
        title = "Metal Gear Solid (Español)",
        system = "PlayStation 1",
        year = "1998",
        genre = "Acción / Sigilo Táctico",
        fileSize = "980 MB",
        description = "La cumbre del espionaje táctico de Hideo Kojima en Shadow Moses. Incluye el legendario e inolvidable doblaje con voces en castellano de Alfonso Vallés como Solid Snake.",
        romFileName = "metal_gear_solid_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Doblaje legendario en castellano (Alfonso Vallés)"
    ),
    GameInfo(
        title = "Resident Evil 2 (Español)",
        system = "PlayStation 1",
        year = "1998",
        genre = "Survival Horror",
        fileSize = "750 MB",
        description = "Sobrevive al brote del Virus-T en Raccoon City con Leon S. Kennedy y Claire Redfield. Textos, notas de los archivadores y menús completamente traducidos al español por romsenespanol.com.",
        romFileName = "resident_evil_2_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Archivos y textos 100% en español"
    ),
    GameInfo(
        title = "Silent Hill (Español)",
        system = "PlayStation 1",
        year = "1999",
        genre = "Terror Psicológico",
        fileSize = "350 MB",
        description = "Harry Mason busca a su hija Cheryl en el pueblo maldito envuelto en niebla y sirenas de pesadilla. Traducido al español con notas y acertijos en castellano.",
        romFileName = "silent_hill_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Acertijos y textos en español"
    ),
    GameInfo(
        title = "Castlevania: SotN (Español)",
        system = "PlayStation 1",
        year = "1997",
        genre = "Metroidvania / Acción Gótica",
        fileSize = "430 MB",
        description = "Alucard despierta para explorar el castillo invertido de Drácula. Edición con parche de traducción de la comunidad de romsenespanol.com que traduce todos los diálogos y nombres de reliquias.",
        romFileName = "castlevania_sotn_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Traducción completa de la comunidad"
    ),
    GameInfo(
        title = "Dino Crisis (Español)",
        system = "PlayStation 1",
        year = "1999",
        genre = "Survival Horror / Pánico Jurásico",
        fileSize = "450 MB",
        description = "Regina investiga la base militar de Isla Ibis invadida por velociraptores y un temible T-Rex. Versión con textos y pistas de cerraduras en español.",
        romFileName = "dino_crisis_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Textos y puzles en español"
    ),
    GameInfo(
        title = "MediEvil (Español)",
        system = "PlayStation 1",
        year = "1998",
        genre = "Aventura / Fantasía Gótica",
        fileSize = "390 MB",
        description = "Sir Daniel Fortesque regresa de entre los muertos para detener al hechicero Zarok. Contiene el genial doblaje al castellano y diálogos cómicos.",
        romFileName = "medievil_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Doblaje original en castellano"
    ),
    GameInfo(
        title = "Spyro the Dragon (Español)",
        system = "PlayStation 1",
        year = "1998",
        genre = "Plataformas 3D",
        fileSize = "420 MB",
        description = "Spyro y Sparx recorren el Reino de los Dragones para descongelar a los dragones cristalizados por Gnasty Gnorc. Voces y subtítulos en español.",
        romFileName = "spyro_the_dragon_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Voces y menús en español"
    ),
    GameInfo(
        title = "Crash Team Racing (Español)",
        system = "PlayStation 1",
        year = "1999",
        genre = "Karts / Carreras Arcade",
        fileSize = "380 MB",
        description = "El mejor juego de karts de PS1. Salva la Tierra desafiando al extraterrestre Nitros Oxide con derrapes con turbo y armas locas en castellano.",
        romFileName = "crash_team_racing_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Diálogos y menús en español"
    ),
    GameInfo(
        title = "Chrono Cross (Español)",
        system = "PlayStation 1",
        year = "1999",
        genre = "JRPG Multidimensional",
        fileSize = "900 MB",
        description = "Serge viaja entre dos dimensiones paralelas para descubrir su destino y evitar la catástrofe del tiempo. Aclamado parche en español de romsenespanol.com.",
        romFileName = "chrono_cross_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Traducción fan al español de alta calidad"
    ),
    GameInfo(
        title = "Gran Turismo 2 (Español)",
        system = "PlayStation 1",
        year = "1999",
        genre = "Simulación de Carreras",
        fileSize = "1.2 GB",
        description = "El simulador de conducción real con más de 600 coches y licencias profesionales. Textos y manuales de mecánica en perfecto castellano.",
        romFileName = "gran_turismo_2_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Simulador oficial en español"
    ),
    GameInfo(
        title = "Parasite Eve (Español)",
        system = "PlayStation 1",
        year = "1998",
        genre = "RPG Cinemático / Terror",
        fileSize = "620 MB",
        description = "Aya Brea investiga el misterio mitocondrial que aterroriza la Ópera de Nueva York. Versión subtitulada al español por romsenespanol.com.",
        romFileName = "parasite_eve_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Traducción al español de romsenespanol.com"
    ),
    GameInfo(
        title = "Tomb Raider II (Español)",
        system = "PlayStation 1",
        year = "1997",
        genre = "Aventura / Arqueología",
        fileSize = "450 MB",
        description = "Lara Croft viaja por la Gran Muralla China, Venecia y plataformas petrolíferas tras la Daga de Xian. Textos y menús en castellano.",
        romFileName = "tomb_raider_2_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Textos oficiales en castellano"
    ),
    GameInfo(
        title = "Driver (Español)",
        system = "PlayStation 1",
        year = "1999",
        genre = "Acción / Persecuciones",
        fileSize = "400 MB",
        description = "Conduce como policía encubierto en Miami, San Francisco, Los Ángeles y Nueva York recreando las grandes persecuciones cinematográficas en español.",
        romFileName = "driver_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Misiones y diálogos en castellano"
    ),
    GameInfo(
        title = "Klonoa: Door to Phantomile (Español)",
        system = "PlayStation 1",
        year = "1997",
        genre = "Plataformas 2.5D",
        fileSize = "320 MB",
        description = "Poética y emotiva aventura de ensueño con mecánicas 2.5D revolucionarias. Subtítulos íntegros en español en romsenespanol.com.",
        romFileName = "klonoa_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Subtítulos en español"
    ),
    GameInfo(
        title = "Vagrant Story (Español)",
        system = "PlayStation 1",
        year = "2000",
        genre = "Aventura RPG / Fantasía Oscura",
        fileSize = "500 MB",
        description = "Ashley Riot se adentra en la ciudad maldita de Leá Monde. Complejo sistema de combate y una narrativa medieval traducida al español con maestría.",
        romFileName = "vagrant_story_es.chd",
        webGameUrl = "https://romsenespanol.com/categories/playstation-1-ps1/",
        translationInfo = "Traducción medieval en español"
    ),

    // =========================================================================
    // --- 2. NINTENDO 64 (Cartucho 64 bits / romsenespanol.com) ---
    // =========================================================================
    GameInfo(
        title = "Super Mario 64 (Español)",
        system = "Nintendo 64",
        year = "1996",
        genre = "Plataformas 3D",
        fileSize = "8 MB",
        description = "La obra maestra que revolucionó el control 3D en los videojuegos. Salta por los cuadros mágicos del castillo de Peach para recolectar las 120 estrellas. Textos completamente en español.",
        romFileName = "super_mario_64_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        coverResId = R.drawable.cover_mario_64,
        translationInfo = "Textos 100% en español (romsenespanol.com)"
    ),
    GameInfo(
        title = "Star Fox 64 (Español)",
        system = "Nintendo 64",
        year = "1997",
        genre = "Shoot 'em up / Acción Espacial",
        fileSize = "16 MB",
        description = "Ponte a los mandos del caza Arwing junto a Fox McCloud para salvar el sistema Lylat del científico Andross con diálogos traducidos al español.",
        romFileName = "star_fox_64_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        coverResId = R.drawable.cover_star_fox_64,
        translationInfo = "Subtítulos y avisos de radio en español"
    ),
    GameInfo(
        title = "Castlevania: Legacy of Darkness (Español)",
        system = "Nintendo 64",
        year = "1999",
        genre = "Acción / Aventura Gótica",
        fileSize = "16 MB",
        description = "Edición definitiva de Castlevania en N64. Controla al hombre-lobo Cornell y adéntrate en el castillo de Drácula con textos en español.",
        romFileName = "castlevania_lod_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        coverResId = R.drawable.cover_castlevania_n64,
        translationInfo = "Textos de la historia en español"
    ),
    GameInfo(
        title = "Harvest Moon 64 (Español)",
        system = "Nintendo 64",
        year = "1999",
        genre = "Simulación / Vida Rural",
        fileSize = "12 MB",
        description = "Restaura la granja de tu abuelo en Flowerbud Village cultivando campos y entablando amistad con los vecinos. Traducción completa en español de romsenespanol.com.",
        romFileName = "harvest_moon_64_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        coverResId = R.drawable.cover_harvest_moon_64,
        translationInfo = "Diálogos de aldeanos en español"
    ),
    GameInfo(
        title = "Zelda: Ocarina of Time (Español)",
        system = "Nintendo 64",
        year = "1998",
        genre = "Aventura Épica / Fantasía",
        fileSize = "32 MB",
        description = "Considerado uno de los mejores videojuegos de todos los tiempos. Viaja a través del tiempo entre la juventud y la adultez de Link. Traducción impecable al castellano.",
        romFileName = "zelda_ocarina_of_time_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Traducción completa oficial y de comunidad"
    ),
    GameInfo(
        title = "Zelda: Majora's Mask (Español)",
        system = "Nintendo 64",
        year = "2000",
        genre = "Aventura / Ciclo Temporal",
        fileSize = "32 MB",
        description = "Salva la tierra de Términa de la colisión de la Luna en un ciclo recurrente de 3 días con la ayuda de máscaras de transformación y la Ocarina. 100% en español.",
        romFileName = "zelda_majoras_mask_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Textos y diarios en castellano"
    ),
    GameInfo(
        title = "Banjo-Kazooie (Español)",
        system = "Nintendo 64",
        year = "1998",
        genre = "Plataformas / Coleccionismo",
        fileSize = "16 MB",
        description = "El oso Banjo y el ave Kazooie se unen para rescatar a Tooty de la malvada bruja Gruntilda en la guarida con piezas de puzle y notas musicales en español.",
        romFileName = "banjo_kazooie_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Rimas de Gruntilda y diálogos en español"
    ),
    GameInfo(
        title = "Super Smash Bros. (Español)",
        system = "Nintendo 64",
        year = "1999",
        genre = "Lucha / Crossover",
        fileSize = "16 MB",
        description = "Mario, Link, Pikachu, Donkey Kong, Fox y Samus combaten en el primer crossover multijugador de la historia con menús en español de romsenespanol.com.",
        romFileName = "super_smash_bros_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Menús y nombres de movimientos en español"
    ),
    GameInfo(
        title = "Mario Kart 64 (Español)",
        system = "Nintendo 64",
        year = "1996",
        genre = "Carreras Arcade",
        fileSize = "12 MB",
        description = "Carreras frenéticas a cuatro jugadores con caparazones azules, plátanos y pistas legendarias como Rainbow Road con menús y textos en español.",
        romFileName = "mario_kart_64_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Menús y pistas en español"
    ),
    GameInfo(
        title = "GoldenEye 007 (Español)",
        system = "Nintendo 64",
        year = "1997",
        genre = "Shooter en Primera Persona",
        fileSize = "12 MB",
        description = "La obra maestra de espionaje de James Bond en N64. Misiones tácticas con objetivos secretos y un mítico multijugador a pantalla dividida con textos en español.",
        romFileName = "goldeneye_007_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Briefings del MI6 y objetivos en español"
    ),
    GameInfo(
        title = "Paper Mario (Español)",
        system = "Nintendo 64",
        year = "2000",
        genre = "RPG / Humor",
        fileSize = "40 MB",
        description = "El Reino Champiñón cobra vida en estilo de libro desplegable de papel. Mario debe rescatar a los 7 Espíritus Estelares con diálogos cómicos traducidos al español.",
        romFileName = "paper_mario_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Traducción completa con humor en español"
    ),
    GameInfo(
        title = "Conker's Bad Fur Day (Español)",
        system = "Nintendo 64",
        year = "2001",
        genre = "Plataformas Adulto / Parodia",
        fileSize = "64 MB",
        description = "La ardilla Conker protagoniza la sátira más gamberra y desternillante de los videojuegos. Parche en español de romsenespanol.com con los mejores chistes adaptados.",
        romFileName = "conkers_bfd_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Chistes y sátiras adaptados al castellano"
    ),
    GameInfo(
        title = "Diddy Kong Racing (Español)",
        system = "Nintendo 64",
        year = "1997",
        genre = "Carreras / Aventura",
        fileSize = "16 MB",
        description = "Conduce coches, aerodeslizadores y aviones en Timber's Island para derrotar al hechicero Wizpig con un modo aventura completo en español.",
        romFileName = "diddy_kong_racing_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Textos de aventura en español"
    ),
    GameInfo(
        title = "Donkey Kong 64 (Español)",
        system = "Nintendo 64",
        year = "1999",
        genre = "Plataformas / Aventura",
        fileSize = "32 MB",
        description = "El juego que requería el Expansion Pak. Controla a los 5 miembros de la familia Kong para recolectar plátanos dorados con textos traducidos al español.",
        romFileName = "donkey_kong_64_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "DK Rap y subtítulos en español"
    ),
    GameInfo(
        title = "Perfect Dark (Español)",
        system = "Nintendo 64",
        year = "2000",
        genre = "FPS / Espionaje Sci-Fi",
        fileSize = "32 MB",
        description = "Joanna Dark investiga conspiraciones alienígenas entre el Instituto Carrington y dataDyne con misiones tácticas en castellano.",
        romFileName = "perfect_dark_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Instrucciones de misión en español"
    ),
    GameInfo(
        title = "Mario Party 3 (Español)",
        system = "Nintendo 64",
        year = "2000",
        genre = "Party Game / Minijuegos",
        fileSize = "32 MB",
        description = "El mejor juego de mesa y minijuegos de N64 con Waluigi y Daisy debutando por primera vez. Reglas y cartas en perfecto español de romsenespanol.com.",
        romFileName = "mario_party_3_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Reglas y minijuegos en español"
    ),
    GameInfo(
        title = "F-Zero X (Español)",
        system = "Nintendo 64",
        year = "1998",
        genre = "Carreras Futuristas a 60 FPS",
        fileSize = "16 MB",
        description = "Velocidad vertiginosa a 60 cuadros por segundo con 30 naves simultáneas en pista y el legendario circuito Death Race. Menús en español.",
        romFileName = "fzero_x_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Menús y ajustes de naves en español"
    ),
    GameInfo(
        title = "Pokémon Stadium (Español)",
        system = "Nintendo 64",
        year = "1999",
        genre = "Combates Pokémon 3D",
        fileSize = "32 MB",
        description = "Combates Pokémon en espectacular 3D con comentarista y minijuegos para cuatro jugadores con descripciones de ataques oficiales en español.",
        romFileName = "pokemon_stadium_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Ataques y comentarios en español"
    ),
    GameInfo(
        title = "Wave Race 64 (Español)",
        system = "Nintendo 64",
        year = "1996",
        genre = "Motos de Agua / Físicas Acuáticas",
        fileSize = "8 MB",
        description = "Pionero en recrear físicas realistas de olas dinámicas sobre motos acuáticas Kawasaki en Dolphin Park con menús en español.",
        romFileName = "wave_race_64_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Pistas y controles en español"
    ),
    GameInfo(
        title = "Rayman 2: The Great Escape (Español)",
        system = "Nintendo 64",
        year = "1999",
        genre = "Plataformas / Aventura 3D",
        fileSize = "32 MB",
        description = "Rayman lucha contra los piratas robot liderados por el Almirante Barbaguda para restaurar el Corazón del Mundo con textos y voces en castellano.",
        romFileName = "rayman_2_es.z64",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-64-n64/",
        translationInfo = "Textos y diálogos en castellano"
    ),

    // =========================================================================
    // --- 3. SUPER NINTENDO (16 bits / SNES / romsenespanol.com) ---
    // =========================================================================
    GameInfo(
        title = "Super Mario World (Español)",
        system = "Super Nintendo",
        year = "1990",
        genre = "Plataformas Clásico",
        fileSize = "512 KB",
        description = "El juego definitivo de lanzamiento de SNES. Descubre la Tierra de los Dinosaurios junto a Yoshi con 96 salidas secretas. Textos en español de romsenespanol.com.",
        romFileName = "super_mario_world_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        coverResId = R.drawable.cover_super_mario_world,
        translationInfo = "Traducción completa al castellano"
    ),
    GameInfo(
        title = "Zelda: A Link to the Past (Español)",
        system = "Super Nintendo",
        year = "1991",
        genre = "Acción / Aventura RPG",
        fileSize = "1.0 MB",
        description = "La obra maestra que definió la fórmula de la saga Zelda con el Mundo de la Luz y el Mundo Oscuro, la Espada Maestra y mazmorras magistrales en español.",
        romFileName = "zelda_link_to_the_past_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        coverResId = R.drawable.cover_zelda_link_to_the_past,
        translationInfo = "Traducción oficial y revisada al español"
    ),
    GameInfo(
        title = "Super Metroid (Español)",
        system = "Super Nintendo",
        year = "1994",
        genre = "Metroidvania / Acción",
        fileSize = "3.0 MB",
        description = "Samus Aran aterriza en el inhóspito planeta Zebes para rescatar a la última larva de Metroid, enfrentándose a Ridley, Kraid y Mother Brain. Textos en castellano.",
        romFileName = "super_metroid_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        coverResId = R.drawable.cover_super_metroid,
        translationInfo = "Prólogo y pantallas en español"
    ),
    GameInfo(
        title = "Donkey Kong Country (Español)",
        system = "Super Nintendo",
        year = "1994",
        genre = "Plataformas / Gráficos ACM",
        fileSize = "4.0 MB",
        description = "Revolucionario juego de Rare con gráficos 3D prerrenderizados. Donkey y Diddy Kong recorren la jungla para recuperar su tesoro de plátanos en español.",
        romFileName = "donkey_kong_country_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        coverResId = R.drawable.cover_donkey_kong_country,
        translationInfo = "Menús y consejos de Cranky en español"
    ),
    GameInfo(
        title = "Mega Man X (Español)",
        system = "Super Nintendo",
        year = "1993",
        genre = "Acción / Plataformas Sci-Fi",
        fileSize = "1.5 MB",
        description = "Capcom reinventó la fórmula del bombardero azul un siglo después con armaduras secretas, saltos en pared y combate contra Mavericks con textos en español.",
        romFileName = "mega_man_x_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        coverResId = R.drawable.cover_mega_man_x,
        translationInfo = "Cápsulas del Dr. Light y diálogos en español"
    ),
    GameInfo(
        title = "Final Fantasy VI (Español)",
        system = "Super Nintendo",
        year = "1994",
        genre = "JRPG Épico",
        fileSize = "3.0 MB",
        description = "Una de las mejores historias del JRPG de la historia. Acompaña a Terra, Locke y Celes frente al desquiciado Kefka. Traducción célebre de romsenespanol.com.",
        romFileName = "final_fantasy_6_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        coverResId = R.drawable.cover_ff6_snes,
        translationInfo = "Traducción íntegra al español de gran fidelidad"
    ),
    GameInfo(
        title = "Chrono Trigger (Español)",
        system = "Super Nintendo",
        year = "1995",
        genre = "JRPG / Viajes en el Tiempo",
        fileSize = "4.0 MB",
        description = "Desarrollado por el 'Dream Team' (Sakaguchi, Horii y Toriyama). Viaja entre distintas épocas para salvar el futuro de Lavos con múltiples finales en español.",
        romFileName = "chrono_trigger_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Traducción mítica en español de romsenespanol.com"
    ),
    GameInfo(
        title = "Donkey Kong Country 2 (Español)",
        system = "Super Nintendo",
        year = "1995",
        genre = "Plataformas de Piratas",
        fileSize = "4.0 MB",
        description = "Diddy y Dixie Kong se adentran en Crocodile Isle para rescatar a Donkey Kong con una banda sonora legendaria de David Wise y textos en castellano.",
        romFileName = "donkey_kong_country_2_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Textos de la familia Kong en español"
    ),
    GameInfo(
        title = "EarthBound (Mother 2 en Español)",
        system = "Super Nintendo",
        year = "1994",
        genre = "JRPG Moderno / Humor de Culto",
        fileSize = "3.0 MB",
        description = "Ness y sus amigos viajan por Eagleland con bates de béisbol y poderes PSI para detener a Giygas. Aclamada traducción al español de romsenespanol.com.",
        romFileName = "earthbound_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Traducción completa de culto en español"
    ),
    GameInfo(
        title = "Super Mario RPG (Español)",
        system = "Super Nintendo",
        year = "1996",
        genre = "RPG / Humor",
        fileSize = "4.0 MB",
        description = "La colaboración histórica entre Nintendo y Squaresoft. Mario, Bowser y Peach se alían con Geno y Mallow para derrotar a Smithy con diálogos en castellano.",
        romFileName = "super_mario_rpg_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Textos y menús de combate en español"
    ),
    GameInfo(
        title = "Yoshi's Island (Español)",
        system = "Super Nintendo",
        year = "1995",
        genre = "Plataformas / Estilo Pintura",
        fileSize = "2.0 MB",
        description = "Yoshi transporta a Baby Mario sobre su lomo a través de mundos dibujados con ceras de colores, usando el chip Super FX 2 con textos en español.",
        romFileName = "yoshis_island_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Guía y mensajes en español"
    ),
    GameInfo(
        title = "Secret of Mana (Español)",
        system = "Super Nintendo",
        year = "1993",
        genre = "Action RPG Cooperativo",
        fileSize = "2.0 MB",
        description = "Randi, Primm y Popoi blanden la Espada Mana para restaurar el flujo mágico mundial en combates en tiempo real traducidos al castellano.",
        romFileName = "secret_of_mana_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Traducción oficial en español"
    ),
    GameInfo(
        title = "Terranigma (Español)",
        system = "Super Nintendo",
        year = "1995",
        genre = "Action RPG Filosófico",
        fileSize = "4.0 MB",
        description = "Ark debe resucitar los continentes, la flora, la fauna y la civilización de la Tierra en una joya narrativa de Quintet traducida oficialmente al español.",
        romFileName = "terranigma_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Traducción oficial de Nintendo España"
    ),
    GameInfo(
        title = "Illusion of Time (Español)",
        system = "Super Nintendo",
        year = "1994",
        genre = "Action RPG Histórico",
        fileSize = "2.0 MB",
        description = "Will viaja a las grandes maravillas del mundo antiguo (las Pirámides, la Gran Muralla, Nazca) con poderes telequinéticos. Textos en español de España.",
        romFileName = "illusion_of_time_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Traducción oficial de Nintendo España"
    ),
    GameInfo(
        title = "Secret of Evermore (Español)",
        system = "Super Nintendo",
        year = "1995",
        genre = "Action RPG Alquímico",
        fileSize = "3.0 MB",
        description = "Un chico y su perro mascota son transportados al mundo de Evermore a través de eras prehistóricas, medievales y futuristas con fórmulas de alquimia en español.",
        romFileName = "secret_of_evermore_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Traducción oficial en español"
    ),
    GameInfo(
        title = "Super Mario Kart (Español)",
        system = "Super Nintendo",
        year = "1992",
        genre = "Carreras Modo 7",
        fileSize = "512 KB",
        description = "El nacimiento del género karting gracias al revolucionario Modo 7 con copas de 50cc, 100cc y 150cc y modo batalla en español.",
        romFileName = "super_mario_kart_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Menús y títulos en español"
    ),
    GameInfo(
        title = "Contra III: The Alien Wars (Español)",
        system = "Super Nintendo",
        year = "1992",
        genre = "Run and Gun Extremo",
        fileSize = "1.0 MB",
        description = "Bill Rizer y Lance Bean combaten la invasión extraterrestre Red Falcon en la devastada Neo City con bombas de demolición y disparos en español.",
        romFileName = "contra_3_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Menús y armas en español"
    ),
    GameInfo(
        title = "Super Castlevania IV (Español)",
        system = "Super Nintendo",
        year = "1991",
        genre = "Acción / Plataformas Gótico",
        fileSize = "1.0 MB",
        description = "Simon Belmont blande su látigo Vampire Killer en 8 direcciones con rotaciones de salas en Modo 7 y música gótica legendaria en español.",
        romFileName = "super_castlevania_4_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Introducción e historia en español"
    ),
    GameInfo(
        title = "Killer Instinct (Español)",
        system = "Super Nintendo",
        year = "1995",
        genre = "Lucha / Ultra Combos",
        fileSize = "4.0 MB",
        description = "Jago, Fulgore, Glacius y Orchid luchan en el torneo de Ultratech encadenando Combo Breakers y Ultra Combos brutales con menús en español.",
        romFileName = "killer_instinct_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Menús y biografías en español"
    ),
    GameInfo(
        title = "Street Fighter II Turbo (Español)",
        system = "Super Nintendo",
        year = "1993",
        genre = "Lucha Arcade",
        fileSize = "2.5 MB",
        description = "La versión hiper rápida de Street Fighter II con los 4 grandes jefes jugables y nuevas técnicas como el Teletransporte de Dhalsim en español.",
        romFileName = "sf2_turbo_es.sfc",
        webGameUrl = "https://romsenespanol.com/categories/super-nintendo-snes/",
        translationInfo = "Menús y victorias en español"
    ),

    // =========================================================================
    // --- 4. NINTENDO NES (8 bits / romsenespanol.com) ---
    // =========================================================================
    GameInfo(
        title = "Banana Prince (Español)",
        system = "Nintendo NES",
        year = "1991",
        genre = "Plataformas / Aventura",
        fileSize = "256 KB",
        description = "El Príncipe Plátano debe recuperar las armas sagradas robadas de su reino haciendo crecer enredaderas y respondiendo preguntas de sabios en español.",
        romFileName = "banana_prince_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        coverResId = R.drawable.cover_banana_prince,
        translationInfo = "Traducción completa al castellano (romsenespanol.com)"
    ),
    GameInfo(
        title = "Uninvited (Español)",
        system = "Nintendo NES",
        year = "1991",
        genre = "Aventura Gráfica / Terror",
        fileSize = "256 KB",
        description = "Aventura de misterio y horror sobrenatural en primera persona. Explora una mansión embrujada para rescatar a tu hermana menor con textos en español.",
        romFileName = "uninvited_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        coverResId = R.drawable.cover_uninvited,
        translationInfo = "Textos íntegros traducidos al castellano"
    ),
    GameInfo(
        title = "Wurm: Journey to the Center (Español)",
        system = "Nintendo NES",
        year = "1991",
        genre = "Acción / Disparos / Sci-Fi",
        fileSize = "256 KB",
        description = "Innovadora combinación de combate en vehículos subterráneos, exploración lateral y combates contra jefes en primera persona con diálogos en español.",
        romFileName = "wurm_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        coverResId = R.drawable.cover_wurm,
        translationInfo = "Diálogos y pistas en español"
    ),
    GameInfo(
        title = "Deja Vu (Español)",
        system = "Nintendo NES",
        year = "1990",
        genre = "Novela Gráfica / Cine Negro",
        fileSize = "256 KB",
        description = "Atmósfera detectivesca de cine negro de los años 40. Despiertas con amnesia en un baño de Chicago con una pistola con tres balas disparadas en español.",
        romFileName = "deja_vu_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        coverResId = R.drawable.cover_deja_vu,
        translationInfo = "Textos de novela negra en castellano"
    ),
    GameInfo(
        title = "Super Mario Bros. 3 (Español)",
        system = "Nintendo NES",
        year = "1988",
        genre = "Plataformas Legendario",
        fileSize = "384 KB",
        description = "La cumbre de los plataformas de 8 bits. Mario obtiene el traje Tanooki, Frog Suit y viaja en barco volador con textos y cartas de Peach en español.",
        romFileName = "super_mario_bros_3_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Textos de mundos y cartas en español"
    ),
    GameInfo(
        title = "The Legend of Zelda (Español)",
        system = "Nintendo NES",
        year = "1986",
        genre = "Acción / Aventura No Lineal",
        fileSize = "128 KB",
        description = "Explora las tierras de Hyrule, recolecta los 8 fragmentos de la Trifuerza y derrota a Ganon en la Montaña de la Muerte con las pistas del anciano en español.",
        romFileName = "the_legend_of_zelda_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Traducción al español de romsenespanol.com"
    ),
    GameInfo(
        title = "Metroid (Español)",
        system = "Nintendo NES",
        year = "1986",
        genre = "Acción / Exploración Laberíntica",
        fileSize = "128 KB",
        description = "El debut histórico de Samus Aran en las profundidades de Zebes armada con su cañón de rayos de hielo y morfosfera con introducción y final en castellano.",
        romFileName = "metroid_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Textos en español"
    ),
    GameInfo(
        title = "Mega Man 2 (Español)",
        system = "Nintendo NES",
        year = "1988",
        genre = "Acción / Plataformas",
        fileSize = "256 KB",
        description = "Derrota a los 8 Robot Masters (Metal Man, Quick Man, etc.) y asalta el castillo del Dr. Wily con textos y pantallas de armas traducidos al castellano.",
        romFileName = "mega_man_2_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Armas y diálogos en castellano"
    ),
    GameInfo(
        title = "Castlevania III: Dracula's Curse (Español)",
        system = "Nintendo NES",
        year = "1989",
        genre = "Acción / Aventura Gótica",
        fileSize = "384 KB",
        description = "Trevor Belmont une fuerzas con Sypha, Grant y Alucard a través de rutas ramificadas para derrotar al conde Drácula con textos en español.",
        romFileName = "castlevania_3_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Rutas y diálogos en español"
    ),
    GameInfo(
        title = "Contra (Español)",
        system = "Nintendo NES",
        year = "1988",
        genre = "Run and Gun Clásico",
        fileSize = "128 KB",
        description = "El épico shooter arcade cooperativo con el famoso Código Konami. Dispara sin descanso a la amenaza alienígena con menús traducidos al español.",
        romFileName = "contra_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Menús y títulos en español"
    ),
    GameInfo(
        title = "DuckTales (Español)",
        system = "Nintendo NES",
        year = "1989",
        genre = "Plataformas / Disney",
        fileSize = "128 KB",
        description = "El Tío Gilito viaja a la Luna, el Amazonas y Transilvania saltando con su bastón en busca de los tesoros legendarios con textos en español.",
        romFileName = "ducktales_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Textos de los personajes en español"
    ),
    GameInfo(
        title = "Kirby's Adventure (Español)",
        system = "Nintendo NES",
        year = "1993",
        genre = "Plataformas Encantador",
        fileSize = "768 KB",
        description = "El juego donde Kirby obtuvo por primera vez la habilidad de copiar los poderes de los enemigos. Gráficos prodigiosos de NES y textos en español.",
        romFileName = "kirbys_adventure_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Poderes y menús en español"
    ),
    GameInfo(
        title = "Mother (EarthBound Zero en Español)",
        system = "Nintendo NES",
        year = "1989",
        genre = "JRPG / Aventuras Ochenteras",
        fileSize = "384 KB",
        description = "Ninten investiga fenómenos paranormales y melodías sagradas para frenar la invasión extraterrestre. Traducción al castellano de romsenespanol.com.",
        romFileName = "mother_1_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Traducción completa fan en español"
    ),
    GameInfo(
        title = "Final Fantasy (Español)",
        system = "Nintendo NES",
        year = "1987",
        genre = "JRPG Fundacional",
        fileSize = "256 KB",
        description = "Los cuatro Guerreros de la Luz portan los orbes oscurecidos para restaurar el equilibrio en los cristales del mundo con hechizos y diálogos en español.",
        romFileName = "final_fantasy_1_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Hechizos y narrativa en español"
    ),
    GameInfo(
        title = "Ninja Gaiden (Español)",
        system = "Nintendo NES",
        year = "1988",
        genre = "Acción Ninja / Cinemáticas",
        fileSize = "256 KB",
        description = "Ryu Hayabusa viaja a América armado con la Dragon Sword para vengar a su padre. Pionero en cinemáticas animadas con textos en castellano.",
        romFileName = "ninja_gaiden_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Cinemáticas traducidas al castellano"
    ),
    GameInfo(
        title = "Batman: The Video Game (Español)",
        system = "Nintendo NES",
        year = "1989",
        genre = "Acción / Plataformas Sombrío",
        fileSize = "256 KB",
        description = "Basado en la película de Tim Burton. El Caballero Oscuro salta entre paredes por Gotham City hasta el campanario para vencer al Joker en español.",
        romFileName = "batman_nes_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Textos de historia en español"
    ),
    GameInfo(
        title = "Battletoads (Español)",
        system = "Nintendo NES",
        year = "1991",
        genre = "Beat 'em up Desafiante",
        fileSize = "256 KB",
        description = "Rash y Zitz rescatan a Pimple de la Reina Oscura en fases salvajes de motos voladoras Turbo Tunnel con instrucciones y bromas en español.",
        romFileName = "battletoads_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Instrucciones de misión en castellano"
    ),
    GameInfo(
        title = "Double Dragon II: The Revenge (Español)",
        system = "Nintendo NES",
        year = "1989",
        genre = "Beat 'em up Cooperativo",
        fileSize = "256 KB",
        description = "Billy y Jimmy Lee ejecutan la patada giratoria Cyclon Kick en las calles de Nueva York para vengar a Marian con cinemáticas en español.",
        romFileName = "double_dragon_2_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Cinemáticas y finales en español"
    ),
    GameInfo(
        title = "Sweet Home (Español)",
        system = "Nintendo NES",
        year = "1989",
        genre = "Survival Horror RPG Fundacional",
        fileSize = "384 KB",
        description = "El antepasado directo de Resident Evil creado por Capcom. Un equipo de documentalistas explora la mansión embrujada Mamiya en perfecto español.",
        romFileName = "sweet_home_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Traducción completa de culto en español"
    ),
    GameInfo(
        title = "Faxanadu (Español)",
        system = "Nintendo NES",
        year = "1987",
        genre = "Action RPG Mitológico",
        fileSize = "256 KB",
        description = "Un héroe elfo regresa al gigantesco Árbol del Mundo para salvar a su pueblo de la corrupción de los enanos con diálogos de sacerdotes en castellano.",
        romFileName = "faxanadu_es.nes",
        webGameUrl = "https://romsenespanol.com/categories/nintendo-nes/",
        translationInfo = "Traducción al castellano de romsenespanol.com"
    ),

    // =========================================================================
    // --- 5. GAME BOY ADVANCE (Portátil / GBA / romsenespanol.com) ---
    // =========================================================================
    GameInfo(
        title = "Lego Star Wars (Español)",
        system = "Game Boy Advance",
        year = "2005",
        genre = "Acción / Aventura",
        fileSize = "8 MB",
        description = "Revive los eventos de la trilogía de precuelas de Star Wars adaptados al divertido universo de bloques LEGO con sables de luz y menús en español.",
        romFileName = "lego_star_wars_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        coverResId = R.drawable.cover_lego_star_wars,
        translationInfo = "Textos y opciones en castellano"
    ),
    GameInfo(
        title = "Tomb Raider: Legend (Español)",
        system = "Game Boy Advance",
        year = "2006",
        genre = "Acción / Plataformas",
        fileSize = "8 MB",
        description = "Lara Croft viaja alrededor del mundo en busca de una reliquia mitológica ligada al Rey Arturo, con saltos acrobáticos y textos en español.",
        romFileName = "tomb_raider_legend_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        coverResId = R.drawable.cover_tomb_raider_gba,
        translationInfo = "Misiones y diario en español"
    ),
    GameInfo(
        title = "Lady Sia (Español)",
        system = "Game Boy Advance",
        year = "2001",
        genre = "Acción / Plataformas Fantasía",
        fileSize = "4 MB",
        description = "Controla a la valerosa princesa guerrera Sia armada con espada gemela y poderes mágicos de los cuatro elementos para liberar los reinos de Avan en español.",
        romFileName = "lady_sia_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        coverResId = R.drawable.cover_lady_sia,
        translationInfo = "Diálogos mágicos en español"
    ),
    GameInfo(
        title = "Kong: 8th Wonder (Español)",
        system = "Game Boy Advance",
        year = "2005",
        genre = "Acción / Aventura Isla Calavera",
        fileSize = "8 MB",
        description = "Basado en la película épica de Peter Jackson. Alterna entre los supervivientes humanos con armas y la fuerza descomunal de King Kong en español.",
        romFileName = "kong_8th_wonder_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        coverResId = R.drawable.cover_kong_gba,
        translationInfo = "Textos y cinemáticas en español"
    ),
    GameInfo(
        title = "Pokémon Esmeralda (Español)",
        system = "Game Boy Advance",
        year = "2004",
        genre = "JRPG / Captura de Criaturas",
        fileSize = "16 MB",
        description = "La versión definitiva de Hoenn. Despierta al legendario Rayquaza para detener la lucha entre Groudon y Kyogre y conquista el Frente de Batalla en español.",
        romFileName = "pokemon_esmeralda_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Versión oficial completa en castellano"
    ),
    GameInfo(
        title = "Zelda: The Minish Cap (Español)",
        system = "Game Boy Advance",
        year = "2004",
        genre = "Acción / Aventura Épica",
        fileSize = "16 MB",
        description = "Link se encoge al diminuto tamaño de los Minish con la ayuda de Ezero para forjar la Espada Cuádruple y vencer al brujo Vaati en perfecto castellano.",
        romFileName = "zelda_minish_cap_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Traducción oficial de Nintendo España"
    ),
    GameInfo(
        title = "Golden Sun (Español)",
        system = "Game Boy Advance",
        year = "2001",
        genre = "JRPG Alquímico",
        fileSize = "8 MB",
        description = "Isaac y sus amigos dominan la Psinergía y los Djinn para encender los cuatro Faros elementales de Weyard. Obra cumbre de Camelot en español.",
        romFileName = "golden_sun_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Traducción oficial de Nintendo España"
    ),
    GameInfo(
        title = "Golden Sun: La Edad Perdida (Español)",
        system = "Game Boy Advance",
        year = "2002",
        genre = "JRPG Épico",
        fileSize = "16 MB",
        description = "La continuación directa desde la perspectiva de Félix, navegando por los mares de Weyard para restaurar la Alquimia en el mundo con textos en español.",
        romFileName = "golden_sun_2_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Traducción oficial en español"
    ),
    GameInfo(
        title = "Metroid Fusion (Español)",
        system = "Game Boy Advance",
        year = "2002",
        genre = "Metroidvania / Suspenso",
        fileSize = "8 MB",
        description = "Samus Aran es infectada por el letal Parásito X en la estación B.S.L. y debe huir del implacable SA-X con diálogos en castellano.",
        romFileName = "metroid_fusion_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Traducción oficial en español"
    ),
    GameInfo(
        title = "Metroid: Zero Mission (Español)",
        system = "Game Boy Advance",
        year = "2004",
        genre = "Metroidvania de Acción",
        fileSize = "8 MB",
        description = "Reinvención moderna del Metroid original de NES con un capítulo final inédito de sigilo donde Samus lucha con traje Zero Suit en español.",
        romFileName = "metroid_zero_mission_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Traducción oficial de Nintendo España"
    ),
    GameInfo(
        title = "Castlevania: Aria of Sorrow (Español)",
        system = "Game Boy Advance",
        year = "2003",
        genre = "Metroidvania Gótico",
        fileSize = "8 MB",
        description = "Soma Cruz descubre que puede absorber las almas de los monstruos durante un eclipse solar en el castillo de Drácula en el año 2035 en español.",
        romFileName = "castlevania_aria_of_sorrow_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Almas y textos en castellano"
    ),
    GameInfo(
        title = "Fire Emblem (Español)",
        system = "Game Boy Advance",
        year = "2003",
        genre = "Estrategia Táctica por Turnos",
        fileSize = "16 MB",
        description = "Guía a Lyn, Eliwood y Hector en combates tácticos con muerte permanente de personajes en el continente de Elibe con guion traducido al español.",
        romFileName = "fire_emblem_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Traducción oficial en español"
    ),
    GameInfo(
        title = "Mario & Luigi: Superstar Saga (Español)",
        system = "Game Boy Advance",
        year = "2003",
        genre = "RPG Cómico de Acción",
        fileSize = "16 MB",
        description = "Los hermanos Mario viajan al Reino Judía para recuperar la bella voz de la Princesa Peach robada por la malvada bruja Cackletta en español.",
        romFileName = "mario_luigi_superstar_saga_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Humor y diálogos en castellano"
    ),
    GameInfo(
        title = "Pokémon Rojo Fuego (Español)",
        system = "Game Boy Advance",
        year = "2004",
        genre = "JRPG Clásico",
        fileSize = "16 MB",
        description = "Remake en 32 bits de las andanzas de Kanto con las Islas Sete añadidas, la Pokédex Nacional y combates en perfecto español de España.",
        romFileName = "pokemon_rojo_fuego_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Versión oficial completa en castellano"
    ),
    GameInfo(
        title = "Final Fantasy Tactics Advance (Español)",
        system = "Game Boy Advance",
        year = "2003",
        genre = "Estrategia Táctica / Leyes",
        fileSize = "16 MB",
        description = "Marche es transportado al mágico reino de Ivalice donde los jueces imponen leyes en combates tácticos con oficios y trabajos en español.",
        romFileName = "ff_tactics_advance_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Traducción oficial de Square Enix"
    ),
    GameInfo(
        title = "Advance Wars 2 (Español)",
        system = "Game Boy Advance",
        year = "2003",
        genre = "Estrategia Militar por Turnos",
        fileSize = "8 MB",
        description = "Andy, Sami y Max defienden Macro Land contra el ejército Black Hole liderado por Sturm con fábricas y poderes de Oficial en castellano.",
        romFileName = "advance_wars_2_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Tácticas y diálogos en español"
    ),
    GameInfo(
        title = "Kirby & The Amazing Mirror (Español)",
        system = "Game Boy Advance",
        year = "2004",
        genre = "Plataformas / Estilo Metroidvania",
        fileSize = "16 MB",
        description = "Kirby es dividido en 4 colores por Dark Meta Knight y explora el Laberinto del Mundo Espejo pudiendo llamar a sus clones con un teléfono móvil en español.",
        romFileName = "kirby_amazing_mirror_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Menús y mapas en español"
    ),
    GameInfo(
        title = "Mother 3 (Español)",
        system = "Game Boy Advance",
        year = "2006",
        genre = "JRPG Emotivo de Culto",
        fileSize = "32 MB",
        description = "Lucas protagoniza una desgarradora y poética historia en las Islas Nowhere frente al ejército de Puercaretas. Traducción legendaria al español de romsenespanol.com.",
        romFileName = "mother_3_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Traducción comunitaria maestra en español"
    ),
    GameInfo(
        title = "Mega Man Battle Network (Español)",
        system = "Game Boy Advance",
        year = "2001",
        genre = "Action RPG Cibernético",
        fileSize = "8 MB",
        description = "Lan Hikari y su NetNavi MegaMan.EXE combaten virus cibernéticos en cuadrículas estratégicas usando Battle Chips con textos en castellano.",
        romFileName = "mm_battle_network_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Chips y diálogos en español"
    ),
    GameInfo(
        title = "WarioWare, Inc. (Español)",
        system = "Game Boy Advance",
        year = "2003",
        genre = "Microjuegos Instantáneos",
        fileSize = "8 MB",
        description = "Cientos de microjuegos de 5 segundos a toda velocidad creados por Wario, Mona, Jimmy T y 9-Volt con instrucciones instantáneas en español.",
        romFileName = "warioware_inc_es.gba",
        webGameUrl = "https://romsenespanol.com/categories/game-boy-advance-gba/",
        translationInfo = "Instrucciones en español de España"
    ),

    // =========================================================================
    // --- 6. SEGA MEGA DRIVE (16 bits / SMD / romsenespanol.com) ---
    // =========================================================================
    GameInfo(
        title = "3 Ninjas Kick Back (Español)",
        system = "Sega Mega Drive",
        year = "1994",
        genre = "Beat 'em up / Ninjas",
        fileSize = "2 MB",
        description = "Únete a los tres hermanos ninja Rocky, Colt y Tum Tum defendiendo la legendaria daga samurái de Koga en templos antiguos y bosques salvajes en español.",
        romFileName = "3_ninjas_kick_back_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        coverResId = R.drawable.cover_3_ninjas,
        translationInfo = "Textos y diálogos en castellano"
    ),
    GameInfo(
        title = "Aaahh!!! Real Monsters (Español)",
        system = "Sega Mega Drive",
        year = "1995",
        genre = "Plataformas / Nickelodeon",
        fileSize = "2 MB",
        description = "Controla al trío monstruoso Ickis, Oblina y Krumm en la Academia de Monstruos del Gromble superando trampas con textos traducidos al español.",
        romFileName = "real_monsters_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        coverResId = R.drawable.cover_real_monsters,
        translationInfo = "Instrucciones y bromas en español"
    ),
    GameInfo(
        title = "A Dinosaur's Tale (Español)",
        system = "Sega Mega Drive",
        year = "1993",
        genre = "Aventura / Plataformas",
        fileSize = "2 MB",
        description = "Basado en la película producida por Steven Spielberg. Rex el tiranosaurio y sus amigos dinosaurios viajan por el tiempo hasta Nueva York en español.",
        romFileName = "dinosaurs_tale_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        coverResId = R.drawable.cover_dinosaurs_tale,
        translationInfo = "Subtítulos y menús en español"
    ),
    GameInfo(
        title = "Addams Family Values (Español)",
        system = "Sega Mega Drive",
        year = "1994",
        genre = "Acción / Aventura Vista Cenital",
        fileSize = "2 MB",
        description = "Guía al tío Fétido a través de los terrenos embrujados de la mansión Addams disparando rayos eléctricos para rescatar al bebé Pubert en español.",
        romFileName = "addams_family_values_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        coverResId = R.drawable.cover_addams_family,
        translationInfo = "Puzles y diálogos macabros en castellano"
    ),
    GameInfo(
        title = "Sonic the Hedgehog 2 (Español)",
        system = "Sega Mega Drive",
        year = "1992",
        genre = "Plataformas a Gran Velocidad",
        fileSize = "1.0 MB",
        description = "Sonic y Tails unen fuerzas por primera vez usando el Spin Dash para recolectar las 7 Esmeraldas del Caos frente al Dr. Robotnik con menús en español.",
        romFileName = "sonic_2_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Menús y zonas en español"
    ),
    GameInfo(
        title = "Streets of Rage 2 (Español)",
        system = "Sega Mega Drive",
        year = "1992",
        genre = "Beat 'em up Callejero",
        fileSize = "2.0 MB",
        description = "Axel Stone, Blaze Fielding, Skate y Max Thunder limpian las calles de la banda de Mr. X con la inolvidable música electrónica de Yuzo Koshiro en español.",
        romFileName = "streets_of_rage_2_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Textos de historia en castellano"
    ),
    GameInfo(
        title = "Phantasy Star IV (Español)",
        system = "Sega Mega Drive",
        year = "1993",
        genre = "JRPG de Ciencia Ficción",
        fileSize = "3.0 MB",
        description = "La joya de la corona del RPG en Mega Drive. Chaz Ashley investiga la oscuridad que asola el sistema solar de Algol en un cómic manga en español.",
        romFileName = "phantasy_star_4_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Traducción célebre de romsenespanol.com"
    ),
    GameInfo(
        title = "Shinobi III (Español)",
        system = "Sega Mega Drive",
        year = "1993",
        genre = "Acción Ninja Suprema",
        fileSize = "2.0 MB",
        description = "Joe Musashi cabalga a caballo, surfea sobre olas gigantes y escala muros lanzando shurikens contra el sindicato Neo Zeed con textos en español.",
        romFileName = "shinobi_3_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Ninjutsus y prólogo en castellano"
    ),
    GameInfo(
        title = "Gunstar Heroes (Español)",
        system = "Sega Mega Drive",
        year = "1993",
        genre = "Run and Gun Físico",
        fileSize = "1.5 MB",
        description = "La obra maestra de Treasure con explosiones pirotécnicas, lanzamiento de enemigos y combinación de armas elementales con textos en castellano.",
        romFileName = "gunstar_heroes_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Diálogos de héroes en español"
    ),
    GameInfo(
        title = "Golden Axe II (Español)",
        system = "Sega Mega Drive",
        year = "1991",
        genre = "Beat 'em up Espada y Brujería",
        fileSize = "1.0 MB",
        description = "Ax Battler, Tyris Flare y Gilius Thunderhead invocan magia elemental contra las hordas del tirano Dark Guld con menús en español.",
        romFileName = "golden_axe_2_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Menús y magias en español"
    ),
    GameInfo(
        title = "Aladdin (Español)",
        system = "Sega Mega Drive",
        year = "1993",
        genre = "Plataformas con Espada",
        fileSize = "2.0 MB",
        description = "Animado por los propios dibujantes de Walt Disney. Aladdin blande su cimitarra y lanza manzanas por Agrabah con canciones y textos en español.",
        romFileName = "aladdin_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Textos y diálogos del Genio en español"
    ),
    GameInfo(
        title = "Comix Zone (Español)",
        system = "Sega Mega Drive",
        year = "1995",
        genre = "Beat 'em up en Viñetas de Cómic",
        fileSize = "2.0 MB",
        description = "Sketch Turner queda atrapado dentro de las viñetas de su propio cómic dibujado por Mortus, rasgando papel y luchando con textos en bocadillos en español.",
        romFileName = "comix_zone_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Bocadillos de cómic en castellano"
    ),
    GameInfo(
        title = "Castlevania: Bloodlines (Español)",
        system = "Sega Mega Drive",
        year = "1994",
        genre = "Acción / Plataformas Sangriento",
        fileSize = "1.5 MB",
        description = "John Morris con el látigo y Eric Lecarde con la lanza Alucard recorren Europa durante la Primera Guerra Mundial para cazar a Elizabeth Bartley en español.",
        romFileName = "castlevania_bloodlines_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Textos e historia europea en español"
    ),
    GameInfo(
        title = "Landstalker (Español)",
        system = "Sega Mega Drive",
        year = "1992",
        genre = "Aventura / RPG Isométrico",
        fileSize = "2.0 MB",
        description = "Nigel el cazatesoros y el hada Friday exploran la isla de Mercator en busca de los tesoros del Rey Nole con puzles y diálogos traducidos al español.",
        romFileName = "landstalker_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Traducción completa al castellano"
    ),
    GameInfo(
        title = "Ristar (Español)",
        system = "Sega Mega Drive",
        year = "1995",
        genre = "Plataformas Cósmico",
        fileSize = "2.0 MB",
        description = "La estrella fugaz Ristar estira sus brazos elásticos para impulsarse por planetas exóticos y liberar a los líderes del tirano Greedy en español.",
        romFileName = "ristar_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Menús e historia en castellano"
    ),
    GameInfo(
        title = "Earthworm Jim (Español)",
        system = "Sega Mega Drive",
        year = "1994",
        genre = "Plataformas / Humor Absurdo",
        fileSize = "3.0 MB",
        description = "Una lombriz común encuentra un supertraje espacial cibernético y lucha contra Psy-Crow para rescatar a la Princesa What's-Her-Name en español.",
        romFileName = "earthworm_jim_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Chistes y subtítulos en español"
    ),
    GameInfo(
        title = "Soleil (Crusader of Centy en Español)",
        system = "Sega Mega Drive",
        year = "1994",
        genre = "Action RPG con Animales",
        fileSize = "2.0 MB",
        description = "Corona obtiene la espada a los 14 años pero pierde la habilidad de hablar con humanos y comprende a los animales y monstruos. Textos en español de España.",
        romFileName = "soleil_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Traducción oficial en español de España"
    ),
    GameInfo(
        title = "The Story of Thor (Español)",
        system = "Sega Mega Drive",
        year = "1994",
        genre = "Action RPG con Espíritus",
        fileSize = "3.0 MB",
        description = "El príncipe Ali descubre el Brazalete de Oro de Oasis para invocar a los cuatro espíritus elementales (Dytto, Efreet, Shade y Bow) en castellano.",
        romFileName = "story_of_thor_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Traducción oficial de Sega en castellano"
    ),
    GameInfo(
        title = "Monster World IV (Español)",
        system = "Sega Mega Drive",
        year = "1994",
        genre = "Action RPG / Aventura Árabe",
        fileSize = "2.0 MB",
        description = "Asha y su mascota voladora Pepelogoo viajan a la Ciudad de Rapadagna para rescatar a los cuatro espíritus elementales. Parche en español de romsenespanol.com.",
        romFileName = "monster_world_4_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Traducción al español de romsenespanol.com"
    ),
    GameInfo(
        title = "Vectorman (Español)",
        system = "Sega Mega Drive",
        year = "1995",
        genre = "Run and Gun Futurista 3D",
        fileSize = "2.0 MB",
        description = "En el año 2049, la Tierra ha sido abandonada y los orbot limpiadores se rebelan bajo el mando de WarHead. Vectorman se transforma para salvar el planeta en español.",
        romFileName = "vectorman_es.md",
        webGameUrl = "https://romsenespanol.com/categories/sega-mega-drive-smd/",
        translationInfo = "Textos de misión en español"
    )
)

val SYSTEM_LIST = listOf(
    RetroSystem(
        id = 0,
        name = "PlayStation 1",
        color = Color(0xFF0A9BE0),
        baseHue = 210f,
        iconResId = R.drawable.console_ps1,
        games = listOf(
            "Crash Bandicoot (Español)",
            "Tekken 2 (Español)",
            "Alundra (Español)",
            "Bloody Roar 2 (Español)",
            "Final Fantasy VII (Español)",
            "Metal Gear Solid (Español)",
            "Resident Evil 2 (Español)",
            "Silent Hill (Español)",
            "Castlevania: SotN (Español)",
            "Dino Crisis (Español)",
            "MediEvil (Español)",
            "Spyro the Dragon (Español)",
            "Crash Team Racing (Español)",
            "Chrono Cross (Español)",
            "Gran Turismo 2 (Español)",
            "Parasite Eve (Español)",
            "Tomb Raider II (Español)",
            "Driver (Español)",
            "Klonoa: Door to Phantomile (Español)",
            "Vagrant Story (Español)"
        )
    ),
    RetroSystem(
        id = 1,
        name = "Nintendo 64",
        color = Color(0xFFE8321A),
        baseHue = 5f,
        iconResId = R.drawable.console_n64,
        games = listOf(
            "Super Mario 64 (Español)",
            "Star Fox 64 (Español)",
            "Castlevania: Legacy of Darkness (Español)",
            "Harvest Moon 64 (Español)",
            "Zelda: Ocarina of Time (Español)",
            "Zelda: Majora's Mask (Español)",
            "Banjo-Kazooie (Español)",
            "Super Smash Bros. (Español)",
            "Mario Kart 64 (Español)",
            "GoldenEye 007 (Español)",
            "Paper Mario (Español)",
            "Conker's Bad Fur Day (Español)",
            "Diddy Kong Racing (Español)",
            "Donkey Kong 64 (Español)",
            "Perfect Dark (Español)",
            "Mario Party 3 (Español)",
            "F-Zero X (Español)",
            "Pokémon Stadium (Español)",
            "Wave Race 64 (Español)",
            "Rayman 2: The Great Escape (Español)"
        )
    ),
    RetroSystem(
        id = 2,
        name = "Super Nintendo",
        color = Color(0xFFFFC800),
        baseHue = 48f,
        iconResId = R.drawable.console_snes,
        games = listOf(
            "Super Mario World (Español)",
            "Zelda: A Link to the Past (Español)",
            "Super Metroid (Español)",
            "Donkey Kong Country (Español)",
            "Mega Man X (Español)",
            "Final Fantasy VI (Español)",
            "Chrono Trigger (Español)",
            "Donkey Kong Country 2 (Español)",
            "EarthBound (Mother 2 en Español)",
            "Super Mario RPG (Español)",
            "Yoshi's Island (Español)",
            "Secret of Mana (Español)",
            "Terranigma (Español)",
            "Illusion of Time (Español)",
            "Secret of Evermore (Español)",
            "Super Mario Kart (Español)",
            "Contra III: The Alien Wars (Español)",
            "Super Castlevania IV (Español)",
            "Killer Instinct (Español)",
            "Street Fighter II Turbo (Español)"
        )
    ),
    RetroSystem(
        id = 3,
        name = "Nintendo NES",
        color = Color(0xFF3AAA35),
        baseHue = 120f,
        iconResId = R.drawable.console_nes,
        games = listOf(
            "Banana Prince (Español)",
            "Uninvited (Español)",
            "Wurm: Journey to the Center (Español)",
            "Deja Vu (Español)",
            "Super Mario Bros. 3 (Español)",
            "The Legend of Zelda (Español)",
            "Metroid (Español)",
            "Mega Man 2 (Español)",
            "Castlevania III: Dracula's Curse (Español)",
            "Contra (Español)",
            "DuckTales (Español)",
            "Kirby's Adventure (Español)",
            "Mother (EarthBound Zero en Español)",
            "Final Fantasy (Español)",
            "Ninja Gaiden (Español)",
            "Batman: The Video Game (Español)",
            "Battletoads (Español)",
            "Double Dragon II: The Revenge (Español)",
            "Sweet Home (Español)",
            "Faxanadu (Español)"
        )
    ),
    RetroSystem(
        id = 4,
        name = "Game Boy Advance",
        color = Color(0xFFC05CFF),
        baseHue = 285f,
        iconResId = R.drawable.console_gba,
        games = listOf(
            "Lego Star Wars (Español)",
            "Tomb Raider: Legend (Español)",
            "Lady Sia (Español)",
            "Kong: 8th Wonder (Español)",
            "Pokémon Esmeralda (Español)",
            "Zelda: The Minish Cap (Español)",
            "Golden Sun (Español)",
            "Golden Sun: La Edad Perdida (Español)",
            "Metroid Fusion (Español)",
            "Metroid: Zero Mission (Español)",
            "Castlevania: Aria of Sorrow (Español)",
            "Fire Emblem (Español)",
            "Mario & Luigi: Superstar Saga (Español)",
            "Pokémon Rojo Fuego (Español)",
            "Final Fantasy Tactics Advance (Español)",
            "Advance Wars 2 (Español)",
            "Kirby & The Amazing Mirror (Español)",
            "Mother 3 (Español)",
            "Mega Man Battle Network (Español)",
            "WarioWare, Inc. (Español)"
        )
    ),
    RetroSystem(
        id = 5,
        name = "Mega Drive",
        color = Color(0xFFFF7A1A),
        baseHue = 25f,
        iconResId = R.drawable.console_megadrive,
        games = listOf(
            "3 Ninjas Kick Back (Español)",
            "Aaahh!!! Real Monsters (Español)",
            "A Dinosaur's Tale (Español)",
            "Addams Family Values (Español)",
            "Sonic the Hedgehog 2 (Español)",
            "Streets of Rage 2 (Español)",
            "Phantasy Star IV (Español)",
            "Shinobi III (Español)",
            "Gunstar Heroes (Español)",
            "Golden Axe II (Español)",
            "Aladdin (Español)",
            "Comix Zone (Español)",
            "Castlevania: Bloodlines (Español)",
            "Landstalker (Español)",
            "Ristar (Español)",
            "Earthworm Jim (Español)",
            "Soleil (Crusader of Centy en Español)",
            "The Story of Thor (Español)",
            "Monster World IV (Español)",
            "Vectorman (Español)"
        )
    )
)

/**
 * Searches games by title, system, genre, or translation info.
 */
fun searchGames(query: String, filterSystem: String? = null): List<GameInfo> {
    val cleanQuery = query.trim().lowercase()
    if (cleanQuery.isEmpty()) {
        return if (filterSystem != null) {
            SPECIFIC_GAMES_METADATA.filter { it.system.equals(filterSystem, ignoreCase = true) }
        } else {
            SPECIFIC_GAMES_METADATA
        }
    }

    return SPECIFIC_GAMES_METADATA.filter { game ->
        val matchesSystem = filterSystem == null || game.system.equals(filterSystem, ignoreCase = true)
        val matchesQuery = game.title.lowercase().contains(cleanQuery) ||
                game.system.lowercase().contains(cleanQuery) ||
                game.genre.lowercase().contains(cleanQuery) ||
                game.description.lowercase().contains(cleanQuery)
        matchesSystem && matchesQuery
    }
}

fun getGameInfo(title: String, systemName: String): GameInfo {
    SPECIFIC_GAMES_METADATA.find {
        it.title.equals(title, ignoreCase = true) ||
        it.title.contains(title, ignoreCase = true) ||
        title.contains(it.title, ignoreCase = true)
    }?.let { return it }

    val cleanSlug = title.lowercase()
        .replace(" (español)", "")
        .replace(" ", "_")
        .replace(":", "")
        .replace("'", "")
    val yearFallback = when {
        systemName.contains("NES", ignoreCase = true) -> "1988"
        systemName.contains("Super", ignoreCase = true) || systemName.contains("Mega", ignoreCase = true) -> "1993"
        systemName.contains("64", ignoreCase = true) || systemName.contains("PlayStation", ignoreCase = true) -> "1997"
        else -> "2003"
    }
    val sizeFallback = when {
        systemName.contains("NES", ignoreCase = true) -> "256 KB"
        systemName.contains("Super", ignoreCase = true) || systemName.contains("Mega", ignoreCase = true) -> "2.0 MB"
        systemName.contains("64", ignoreCase = true) -> "16 MB"
        systemName.contains("PlayStation", ignoreCase = true) -> "450 MB"
        else -> "8 MB"
    }
    val ext = when {
        systemName.contains("NES", ignoreCase = true) -> "nes"
        systemName.contains("Super", ignoreCase = true) -> "sfc"
        systemName.contains("Mega", ignoreCase = true) -> "md"
        systemName.contains("64", ignoreCase = true) -> "z64"
        systemName.contains("PlayStation", ignoreCase = true) -> "chd"
        else -> "gba"
    }

    return GameInfo(
        title = title,
        system = systemName,
        year = yearFallback,
        genre = "Aventura / Clásico en Español",
        fileSize = sizeFallback,
        description = "Título legendario con textos en español disponible en el catálogo de romsenespanol.com.",
        romFileName = "$cleanSlug.$ext",
        webGameUrl = "https://romsenespanol.com/",
        translationInfo = "Traducción completa en español de romsenespanol.com"
    )
}

/**
 * Converts HSL (hue: 0-360, saturation: 0-1, lightness: 0-1) to Compose Color.
 */
fun hslToColor(hue: Float, saturation: Float, lightness: Float, alpha: Float = 1f): Color {
    val hNorm = ((hue % 360f) + 360f) % 360f
    val c = (1f - abs(2f * lightness - 1f)) * saturation
    val x = c * (1f - abs((hNorm / 60f) % 2f - 1f))
    val m = lightness - c / 2f
    val (r1, g1, b1) = when {
        hNorm < 60f -> Triple(c, x, 0f)
        hNorm < 120f -> Triple(x, c, 0f)
        hNorm < 180f -> Triple(0f, c, x)
        hNorm < 240f -> Triple(0f, x, c)
        hNorm < 300f -> Triple(x, 0f, c)
        else -> Triple(c, 0f, x)
    }
    return Color(
        red = (r1 + m).coerceIn(0f, 1f),
        green = (g1 + m).coerceIn(0f, 1f),
        blue = (b1 + m).coerceIn(0f, 1f),
        alpha = alpha
    )
}
