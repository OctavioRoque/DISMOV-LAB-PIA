# VolleyCoach — Walkthrough de la Fase 3

> [!NOTE]
> **Fase 3 completada con éxito**
> Se ha implementado el motor de reglas de rotaciones en Java (`RotationRuleEngine`), las entidades y DAO de alineaciones (`LineupEntity`, `LineupDao`), y las pruebas unitarias de rotación horaria y posiciones en cancha (15 pruebas unitarias exitosas en total).

---

## 1. Archivos Creados en la Fase 3

### Motor de Rotaciones (Interoperabilidad Java)
- **`core/util/RotationRuleEngine.java`**: Motor de lógica pura en Java que implementa la rotación horaria de los jugadores en las 6 posiciones de la cancha (I a VI) según las reglas oficiales de la FIVB, además de validaciones de posiciones de zagueros vs delanteros.

### Capa de Datos y Base de Datos (Alineaciones)
- **`core/database/entity/LineupEntity.kt`**: Entidad Room para guardar alineaciones iniciales por equipo y sistemas tácticos (5-1, 6-2, 4-2).
- **`core/database/dao/LineupDao.kt`**: DAO de acceso a datos para alineaciones.
- **`core/database/VolleyCoachDatabase.kt`**: Actualizada a versión 3 incluyendo la entidad `Lineups`.

### Pruebas Unitarias
- **`RotationRuleEngineTest.java`**: Pruebas unitarias en Java para verificar la rotación horaria precisa y la identificación de posiciones de zaguero aptas para sustitución de líbero.

---

## 2. Validación de Pruebas Unitarias

Todas las pruebas unitarias pasaron con éxito (`BUILD SUCCESSFUL`):
```bash
> Task :app:testDebugUnitTest
15 tests completed successfully.
```

---

## 3. Siguiente Paso (Fase 4)
Desarrollar el **Módulo 5: Pizarra Táctil** con Canvas interactivo de 9m × 18m, fichas arrastrables de jugadores, trazos a mano alzada, modo animación por fotogramas y exportación de jugadas.
