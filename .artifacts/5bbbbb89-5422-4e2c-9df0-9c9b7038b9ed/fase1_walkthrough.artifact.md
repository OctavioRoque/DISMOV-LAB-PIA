# VolleyCoach — Walkthrough de la Fase 1

> [!NOTE]
> **Fase 1 completada con éxito**
> Se ha implementado el proyecto base, tema Material 3, navegación, Hilt, Room, interoperabilidad Java-Kotlin y el módulo completo de Gestión de Equipos y Jugadores con pruebas unitarias exitosas.

---

## 1. Cambios Realizados y Archivos Creados

### Configuración e Infraestructura Base
- **`gradle/libs.versions.toml`**: Version Catalog configurado con Compose BOM, Hilt, Room, Navigation, Coroutines, WorkManager, Timber y Testing (JUnit, MockK, Turbine).
- **`gradle/wrapper/gradle-wrapper.properties`**: Gradle 8.10.2 con JDK 17.
- **`app/build.gradle.kts`**: Dependencias completas, Material 3, Hilt y Room con KAPT.
- **`VolleyCoachApp.kt`**: Aplicación Hilt (`@HiltAndroidApp`) con inicialización de Timber.
- **`MainActivity.kt`**: Actividad Hilt (`@AndroidEntryPoint`) con Compose `VolleyCoachTheme` y `NavGraph`.

### Diseño UI / UX & Navegación
- **`core/designsystem/theme/`**: `Color.kt` (Paleta Azul Voleibol y Acento Dorado), `Type.kt` (Tipografía M3), `Theme.kt` (Modo claro/oscuro dinámico).
- **`core/navigation/`**: `Screen.kt`, `BottomNavBar.kt`, `NavGraph.kt`.

### Capa de Datos (Room) y Interoperabilidad Java
- **`core/database/entity/`**: `TeamEntity.kt`, `PlayerEntity.kt` (con clave foránea en cascada e índice único por equipo y número de camiseta).
- **`core/database/dao/`**: `TeamDao.kt`, `PlayerDao.kt`.
- **`core/database/VolleyCoachDatabase.kt`**: Base de datos Room principal.
- **`core/database/mapper/`**: `TeamMapper.kt`, `PlayerMapper.kt`.
- **`core/util/PlayerValidationUtil.java`**: **Clase en Java puro** que valida rangos de camiseta FIVB (1-99), unicidad de dorsales, nombres y alturas. Demuestra la **interoperabilidad Java-Kotlin**.

### Módulo de Equipos y Jugadores (Domain, Data, Presentation)
- **Domain Models**: `Team.kt`, `Player.kt`, `Position.kt`, `DominantHand.kt`, `PlayerStatus.kt`.
- **Repository**: `TeamRepository.kt` & `TeamRepositoryImpl.kt`.
- **Use Cases**: `GetTeamsUseCase`, `SaveTeamUseCase`, `DeleteTeamUseCase`, `GetPlayersUseCase`, `SavePlayerUseCase` (valida con la utilidad Java), `DeletePlayerUseCase`, `ImportPlayersCsvUseCase`, `ExportPlayersCsvUseCase`.
- **Presentation**: `TeamsViewModel.kt`, `TeamsUiState.kt`, `TeamsScreen.kt`, `TeamCard.kt`, `PlayerCard.kt`, `AddEditTeamDialog.kt`, `AddEditPlayerDialog.kt`, `CsvImportExportDialog.kt`.

### Pruebas Unitarias Exitosas
- **`PlayerValidationUtilTest.java`**: Pruebas unitarias en Java para validación de dorsales y nombres.
- **`SavePlayerUseCaseTest.kt`**: Pruebas unitarias con MockK para casos de éxito y error al guardar jugadores.
- **`TeamsViewModelTest.kt`**: Pruebas unitarias de ViewModel con Turbine y Coroutines Test para verificar flujos de estado.

---

## 2. Validación de Pruebas Unitarias

Todas las pruebas unitarias pasaron con éxito (`BUILD SUCCESSFUL`):
```bash
> Task :app:testDebugUnitTest
10 tests completed successfully.
```

---

## 3. Siguiente Paso (Fase 2)
Iniciar el desarrollo del **Módulo 3: Marcador Digital (Score)** con reglas FIVB automáticas, cronómetro de tiempos fuera, historial punto por punto e interfaz de tablero a pantalla completa.
