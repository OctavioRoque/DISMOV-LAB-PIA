package com.example.pia_labdismov.core.util

import com.example.pia_labdismov.core.database.entity.ExerciseEntity

object PreloadedExercises {
    val exercises = listOf(
        // Calentamiento
        ExerciseEntity(name = "Movilidad articular general", category = "Calentamiento", durationMinutes = 10, intensity = 1, equipment = "Ninguno", description = "Rotación de hombros, muñecas, caderas y tobillos.", instructions = "Realizar 15 repeticiones por articulación.", isPreloaded = true),
        ExerciseEntity(name = "Carrera continua y desplazamientos laterales", category = "Calentamiento", durationMinutes = 10, intensity = 3, equipment = "Conos", description = "Trote suave con cambios de dirección y pasos laterales.", instructions = "Mantener postura baja durante los desplazamientos.", isPreloaded = true),
        ExerciseEntity(name = "Estiramientos dinámicos", category = "Calentamiento", durationMinutes = 5, intensity = 2, equipment = "Ninguno", description = "Zancadas con torsión y patadas frontales controladas.", instructions = "Movimientos fluidos sin rebotes bruscos.", isPreloaded = true),
        ExerciseEntity(name = "Activación con balón medicinal", category = "Calentamiento", durationMinutes = 5, intensity = 3, equipment = "Balón medicinal", description = "Lanzamientos de pecho y rotaciones de tronco.", instructions = "3 series de 10 lanzamientos.", isPreloaded = true),

        // Saque
        ExerciseEntity(name = "Saque de abajo de precisión", category = "Saque", durationMinutes = 10, intensity = 2, equipment = "Balones", description = "Saques hacia zonas específicas de la cancha contraria (zonas 1, 5, 6).", instructions = "10 saques por jugador buscando zona de fondo.", isPreloaded = true),
        ExerciseEntity(name = "Saque flotado táctico", category = "Saque", durationMinutes = 15, intensity = 4, equipment = "Balones", description = "Saque por arriba flotado apuntando al pasador rival.", instructions = "Impacto seco en el centro del balón con la palma firme.", isPreloaded = true),
        ExerciseEntity(name = "Saque en potencia (Jump Serve)", category = "Saque", durationMinutes = 15, intensity = 5, equipment = "Balones", description = "Práctica de lanzamiento alto, carrera de aceleración y salto con potencia.", instructions = "Enfoque en la altura del lanzamiento y contacto con máxima extensión.", isPreloaded = true),
        ExerciseEntity(name = "Competencia de saques bajo presión", category = "Saque", durationMinutes = 10, intensity = 4, equipment = "Balones", description = "Cada jugador debe acertar 3 saques seguidos a zonas marcadas.", instructions = "Si falla, repite la serie.", isPreloaded = true),

        // Recepción
        ExerciseEntity(name = "Pase de antebrazos en pareja", category = "Recepción", durationMinutes = 10, intensity = 2, equipment = "Balones", description = "Autopase y pase continuo a distancia media.", instructions = "Brazos extendidos, hombros orientados al objetivo.", isPreloaded = true),
        ExerciseEntity(name = "Recepción de saque flotado", category = "Recepción", durationMinutes = 15, intensity = 4, equipment = "Balones, Entrenador", description = "Recepción orientada hacia la zona 2 (pasador) ante saques del entrenador.", instructions = "Lectura temprana de la trayectoria y absorción con piernas.", isPreloaded = true),
        ExerciseEntity(name = "Defensa y recepción en W", category = "Recepción", durationMinutes = 15, intensity = 3, equipment = "Balones", description = "Formación en W recibiendo balones divididos.", instructions = "Comunicación constante y cobertura de zonas débiles.", isPreloaded = true),
        ExerciseEntity(name = "Control de balón alto en suspensión", category = "Recepción", durationMinutes = 10, intensity = 3, equipment = "Balones", description = "Recepción de balones profundos sobre la cabeza.", instructions = "Uso de manos altas o antebrazos según velocidad.", isPreloaded = true),

        // Armado
        ExerciseEntity(name = "Precisión de armado a zona 4", category = "Armado", durationMinutes = 10, intensity = 2, equipment = "Balones", description = "Armado repetitivo desde zona 3 hacia zona 4.", instructions = "Toque rápido con yemas de los dedos sobre la frente.", isPreloaded = true),
        ExerciseEntity(name = "Armado en suspensión (Jump Set)", category = "Armado", durationMinutes = 15, intensity = 4, equipment = "Balones", description = "Armado en el punto más alto del salto.", instructions = "Coordinación precisa con el tempo del atacante.", isPreloaded = true),
        ExerciseEntity(name = "Armado a pierna cambiada / zaguero", category = "Armado", durationMinutes = 15, intensity = 4, equipment = "Balones", description = "Pases largos hacia zona 1 y zona 6 (Pipe).", instructions = "Incrementar fuerza de muñecas y extensión corporal.", isPreloaded = true),
        ExerciseEntity(name = "Toma de decisiones del armador", category = "Armado", durationMinutes = 15, intensity = 4, equipment = "Balones, Atacantes", description = "Situación 3v3 con bloqueo rival para elegir opción de ataque.", instructions = "Leer el bloqueo del oponente antes de decidir.", isPreloaded = true),

        // Ataque
        ExerciseEntity(name = "Carrera de impulso y batida", category = "Ataque", durationMinutes = 10, intensity = 3, equipment = "Balones", description = "Práctica del último paso de freno y doble impulso de brazos.", instructions = "De izquierdo-derecho (diestros) a la verticalidad.", isPreloaded = true),
        ExerciseEntity(name = "Ataque contra pared / suelo", category = "Ataque", durationMinutes = 10, intensity = 4, equipment = "Balones", description = "Golpeo picado contra el suelo con muñuequeo.", instructions = "Codo alto y flexión de muñeca al contacto.", isPreloaded = true),
        ExerciseEntity(name = "Remate de 4 y de 2 con pase de plantilla", category = "Ataque", durationMinutes = 20, intensity = 4, equipment = "Balones", description = "Ataques de ala tras armado alto y medio.", instructions = "Coordinar temporización con la trayectoria del balón.", isPreloaded = true),
        ExerciseEntity(name = "Ataque de zaguero (Pipe)", category = "Ataque", durationMinutes = 15, intensity = 5, equipment = "Balones", description = "Entrada desde zaguero por detrás de la línea de 3 metros.", instructions = "Salto potente sin pisar la línea de ataque.", isPreloaded = true),

        // Bloqueo
        ExerciseEntity(name = "Desplazamientos y salto de bloqueo", category = "Bloqueo", durationMinutes = 10, intensity = 3, equipment = "Ninguno", description = "Pasos cruzados y salto con penetración de manos al campo contrario.", instructions = "Manos firmes y separadas el ancho de los hombros.", isPreloaded = true),
        ExerciseEntity(name = "Bloqueo doble coordinado", category = "Bloqueo", durationMinutes = 15, intensity = 4, equipment = "Balones", description = "Sincronización entre central y lateral ante atacante rival.", instructions = "Cerrar el espacio entre ambos bloqueadores.", isPreloaded = true),
        ExerciseEntity(name = "Lectura de colocador y bloqueo individual", category = "Bloqueo", durationMinutes = 15, intensity = 4, equipment = "Balones", description = "Anticipación al destino del balón según las manos del armador.", instructions = "Evitar manos hacia atrás en la red.", isPreloaded = true),

        // Defensa
        ExerciseEntity(name = "Planchas y caídas defensivas", category = "Defensa", durationMinutes = 10, intensity = 4, equipment = "Rodilleras", description = "Técnica de amortiguación en el suelo tras recuperación.", instructions = "Deslizamiento controlado para evitar lesiones.", isPreloaded = true),
        ExerciseEntity(name = "Defensa de campo perimetral", category = "Defensa", durationMinutes = 15, intensity = 3, equipment = "Balones", description = "Defensa de remates desde la red en zona 1 y 5.", instructions = "Centro de gravedad bajo y anticipación.", isPreloaded = true),
        ExerciseEntity(name = "Cobertura de bloqueo", category = "Defensa", durationMinutes = 15, intensity = 3, equipment = "Balones", description = "Jugadores cercanos cubriendo los rechaces del bloqueo propio.", instructions = "Posición de alerta agachados bajo la línea de bloqueo.", isPreloaded = true),

        // Físico
        ExerciseEntity(name = "Plyometrics y saltos pliométricos", category = "Físico", durationMinutes = 15, intensity = 5, equipment = "Cajones de salto", description = "Saltos continuos a cajón y desde altura con caída amortiguada.", instructions = "Rebote rápido y explosivo.", isPreloaded = true),
        ExerciseEntity(name = "Trabajo de core y abdominales", category = "Físico", durationMinutes = 10, intensity = 3, equipment = "Esterillas", description = "Planchas frontales, laterales y elevación de piernas.", instructions = "3 series de 45 segundos por ejercicio.", isPreloaded = true),

        // Juego
        ExerciseEntity(name = "Juego 6v6 condicionado (Solo ataque de zaguero)", category = "Juego", durationMinutes = 20, intensity = 4, equipment = "Balones", description = "Partido formal con regla de que todos los puntos deben ser de zaguero.", instructions = "Aplicación táctica de sistemas.", isPreloaded = true),
        ExerciseEntity(name = "Partido formal 6v6 mejor de 3 sets", category = "Juego", durationMinutes = 30, intensity = 5, equipment = "Balones, Marcador", description = "Simulación completa de partido oficial con rotaciones y cambios.", instructions = "Aplicación de reglamento FIVB completo.", isPreloaded = true)
    )
}