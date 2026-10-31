# VolleyCoach — Walkthrough de la Fase 4

> [!NOTE]
> **Fase 4 completada con éxito**
> Se ha implementado la **Pizarra Táctil** con cancha de voleibol a escala (9m × 18m, líneas de ataque y red), fichas de jugadores interactivas y arrastrables, herramienta de dibujo a mano alzada y persistencia con Room.

---

## 1. Archivos Creados en la Fase 4

### Capa de Dominio y Datos (Tácticas y Pizarra)
- **`features/tactics/domain/model/`**: `TacticalToken.kt`, `TacticBoard.kt`, `TacticFrame.kt`, `TokenType`.
- **`core/database/entity/`**: `TacticBoardEntity.kt`, `TacticFrameEntity.kt`.
- **`core/database/dao/`**: `TacticBoardDao.kt`, `TacticFrameDao.kt`.
- **`core/database/VolleyCoachDatabase.kt`**: Actualizada a versión 4 incluyendo las tablas de pizarras tácticas y cuadros.

### Presentación (Pizarra Táctil interactiva)
- **`features/tactics/presentation/TacticsScreen.kt`**:
  - Cancha de voleibol 9m × 18m con proporciones oficiales, red central y líneas de ataque a 3m.
  - Fichas de jugadores (1 al 6) y balón arrastrables táctilmente en tiempo real.
  - Modo de dibujo a mano alzada para trazar trayectorias de balón y movimientos.
  - Botón de limpieza rápida de trazos.
  - Integrada en la navegación principal mediante la barra inferior (pestaña Pizarra).

---

## 2. Validación y Pruebas
- Compilación y ejecución exitosa de todas las pruebas unitarias existentes (15 pruebas unitarias pasadas al 100%).

---

## 3. Siguiente Paso (Fase 5)
Desarrollar el **Módulo 6: Plan de Entrenamiento**, que incluye creación de sesiones por semana/mes, biblioteca de 30 ejercicios en español precargados, control de asistencia y cronómetro de sesión.
