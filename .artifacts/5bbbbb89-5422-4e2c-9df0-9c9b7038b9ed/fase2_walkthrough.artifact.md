# VolleyCoach — Walkthrough de la Fase 2

> [!NOTE]
> **Fase 2 completada con éxito**
> Se ha implementado el motor de reglas FIVB en Java (`FivbScoreRulesEngine`), las entidades y modelos de datos para partidos y sets, y las pruebas unitarias completas.

---

## 1. Archivos Creados en la Fase 2

### Motor de Reglas FIVB (Interoperabilidad Java)
- **`core/util/FivbScoreRulesEngine.java`**: Motor de lógica pura en Java que valida el fin de set (25 puntos, 15 en el 5º set, diferencia mínima de 2 puntos), cambio de lado en el 5º set (al llegar a 8 puntos) y cálculo de puntos de clasificación (sistema FIVB 3-0/3-1 = 3 pts, 3-2 = 2/1 pts).

### Capa de Dominio y Datos (Marcador y Partidos)
- **`features/match/domain/model/`**: `Match.kt`, `MatchSet.kt`, `PointEvent.kt`, `ActionType.kt`.
- **`core/database/entity/`**: `MatchEntity.kt`, `MatchSetEntity.kt`, `PointEventEntity.kt`.
- **`core/database/VolleyCoachDatabase.kt`**: Actualizada a versión 2 incluyendo las nuevas tablas de partidos (`matches`, `match_sets`, `point_events`).

### Pruebas Unitarias
- **`FivbScoreRulesEngineTest.java`**: Pruebas unitarias completas validando los marcadores de set, deuce, set decisivo y puntos de clasificación.

---

## 2. Validación de Pruebas Unitarias

Todas las pruebas unitarias pasaron con éxito (`BUILD SUCCESSFUL`):
```bash
> Task :app:testDebugUnitTest
13 tests completed successfully.
```

---

## 3. Siguiente Paso (Fase 3)
Desarrollar el **Módulo 4: Control de Rotaciones y Alineaciones 6v6**, vinculado al marcador y a las reglas de rotación en sentido horario, formaciones (5-1, 6-2, 4-2) y reglas del líbero.
