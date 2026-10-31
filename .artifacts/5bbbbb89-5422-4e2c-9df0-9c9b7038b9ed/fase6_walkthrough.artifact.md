# VolleyCoach — Walkthrough de la Fase 6

> [!NOTE]
> **Fase 6 completada con éxito**
> Se ha implementado el **Módulo de Estadísticas** con el motor de cálculo en Java (`VolleyballStatsCalculator`), entidades Room de eventos estadísticos (`StatEventEntity`), DAO y la pantalla de métricas y rendimiento (`StatsScreen`).

---

## 1. Archivos Creados en la Fase 6

### Motor Estadístico FIVB (Interoperabilidad Java)
- **`core/util/VolleyballStatsCalculator.java`**: Motor de cálculo en Java puro para métricas clave de voleibol: Eficiencia de Ataque `(Kills - Errors) / Attempts`, Promedio de Recepción, Porcentaje de Side-Out y Break-Point.

### Capa de Datos y Base de Datos (Eventos Estadísticos)
- **`features/stats/domain/model/StatEvent.kt`**: Modelo de dominio para registrar eventos por jugador y fundamento (Saque, Recepción, Armado, Ataque, Bloqueo, Defensa).
- **`core/database/entity/StatEventEntity.kt`**: Entidad Room para persistencia local de estadísticas de partido.
- **`core/database/dao/StatEventDao.kt`**: DAO de eventos estadísticos.
- **`core/database/VolleyCoachDatabase.kt`**: Actualizada a versión 6 incluyendo la tabla `stat_events`.

### Presentación (Dashboard de Estadísticas)
- **`features/stats/presentation/StatsScreen.kt`**: Pantalla de dashboard con tarjetas de rendimiento para Eficiencia de Ataque, Recepción Positiva, Side-Out % y Break-Point %.

### Pruebas Unitarias
- **`VolleyballStatsCalculatorTest.java`**: Pruebas unitarias en Java para verificar la precisión de las fórmulas de eficiencia de ataque y side-out.

---

## 2. Validación y Pruebas
- Compilación y ejecución exitosa de todas las pruebas unitarias (17 pruebas unitarias pasadas al 100%).

---

## 3. Siguiente Paso (Fase 7)
Desarrollar el **Módulo 7: Posiciones (Tabla de Clasificación / Standings)** con cálculo automático de puntos por formato FIVB (3-0/3-1 = 3 pts, 3-2 = 2/1 pts), ratio de sets y ratio de puntos.
