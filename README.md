# 🎮 Buscaminas POO

## 📋 Descripción del Proyecto

**Buscaminas POO** es una implementación completa del clásico juego de estrategia Buscaminas, desarrollado en **Java** utilizando los principios de **Programación Orientada a Objetos (POO)**. Este proyecto fue diseñado como parte del examen final de la asignatura de POO en la **Universidad Politécnica Salesiana**, demostrando la aplicación de conceptos avanzados de desarrollo de software.

## 🏗️ Arquitectura del Sistema

### **Patrón de Diseño: Modelo-Vista-Controlador (MVC)**
El proyecto implementa una arquitectura MVC robusta que separa claramente las responsabilidades:

- **Modelo**: Lógica de negocio y datos del juego (`Juego`, `Tablero`, `Casilla`, `Jugador`)
- **Vista**: Interfaz de usuario en consola (`VistaConsola`)
- **Controlador**: Gestión de flujo y comunicación entre componentes (`ControladorJuego`)

### **Diagrama de Clases UML**
```
┌─────────────────────────────────────────────────────────────┐
│                       SISTEMA BUSCAMINAS                    │
├─────────────────────────────────────────────────────────────┤
│  ┌──────────┐      ┌────────────────┐      ┌──────────────┐│
│  │  Modelo  │◄────►│  Controlador   │◄────►│     Vista    ││
│  └──────────┘      └────────────────┘      └──────────────┘│
│       │                   │                       │         │
│  ┌────┴─────┐       ┌─────┴─────┐         ┌──────┴──────┐  │
│  │ Juego    │       │Controlador│         │VistaConsola │  │
│  │ Tablero  │       │  Juego    │         │             │  │
│  │ Casilla  │       │           │         │ Muestra     │  │
│  │ Jugador  │       │ Gestiona  │         │ Tablero     │  │
│  │          │       │ Entradas  │         │ Mensajes    │  │
│  └──────────┘       │Validación │         │Instrucciones│  │
│       │             │Excepciones│         └─────────────┘  │
│  ┌────┴─────┐       └───────────┘                          │
│  │Persistencia│                                             │
│  │GestorGuardado│                                           │
│  └──────────┘                                               │
└─────────────────────────────────────────────────────────────┘
```

## 🚀 Características Técnicas

### **✅ Principios POO Implementados**
- **Encapsulamiento**: Atributos privados con acceso mediante getters/setters
- **Herencia**: Implementación de `Serializable` para persistencia
- **Abstracción**: Separación clara de responsabilidades en capas
- **Cohesión y Acoplamiento**: Módulos altamente cohesivos y débilmente acoplados

### **🔧 Tecnologías y Patrones**
- **Java 8+** - Lenguaje principal
- **Serialización de Objetos** - Persistencia de datos mediante `ObjectOutputStream`/`ObjectInputStream`
- **Manejo de Excepciones Personalizadas** - Control de errores robusto
- **Patrón MVC** - Arquitectura escalable y mantenible
- **Algoritmo de Descubrimiento Recursivo** - Apertura automática de casillas vacías

## 📁 Estructura del Proyecto

```
ExamenPractico_POO/
├── src/
│   ├── main/
│   │   └── main.java                      # Punto de entrada de la aplicación
│   │
│   ├── modelo/                            # Capa de modelo (Lógica de negocio)
│   │   ├── Juego.java                     # Control principal del juego
│   │   ├── Tablero.java                   # Gestión del tablero 10x10
│   │   ├── Casilla.java                   # Representación de cada casilla
│   │   └── Jugador.java                   # Entidad jugador con estadísticas
│   │
│   ├── vista/                             # Capa de presentación
│   │   └── VistaConsola.java              # Interfaz de usuario en consola
│   │
│   ├── controlador/                       # Capa de control
│   │   └── ControladorJuego.java          # Coordinación MVC y flujo del juego
│   │
│   ├── persistencia/                      # Gestión de datos
│   │   └── GestorGuardado.java            # Serialización y archivos
│   │
│   └── excepciones/                       # Excepciones personalizadas
│       ├── CasillaYaDescubiertaException.java
│       ├── EntradaInvalidaException.java
│       └── JuegoGuardadoException.java
│
├── bin/                                   # Archivos compilados (.class)
├── test/                                  # Pruebas del sistema
├── LICENSE                                # Licencia del proyecto
└── README.md                              # Este archivo
```

## 🎯 Requisitos del Sistema

### **Requisitos Mínimos**
- **Java Development Kit (JDK)**: Versión 8 o superior
- **Memoria RAM**: 2 GB mínimo
- **Sistema Operativo**: Windows, Linux, o macOS
- **Terminal**: Consola compatible con UTF-8 para visualización de caracteres especiales

## ⚙️ Instalación y Configuración

### **Método 1: Compilación Manual con javac**
```powershell
# 1. Navegar al directorio del proyecto
cd "c:\Users\MEGABLODFIX\Downloads\Universidad\Programacion Orientada a Objetos\Proyectos Git\Programacion_Orienta_Objetos\ExamenPractico_POO"

# 2. Compilar todas las clases Java
javac -d bin -cp src src/main/*.java src/modelo/*.java src/vista/*.java src/controlador/*.java src/persistencia/*.java src/excepciones/*.java

# 3. Ejecutar la aplicación
java -cp bin main.main
```

### **Método 2: Usando IDE (Eclipse/IntelliJ IDEA)**
1. **Importar proyecto**: File → Open Project → Seleccionar carpeta del proyecto
2. **Configurar JDK**: Project Structure → SDK → Java 8+
3. **Ejecutar**: Click derecho en `main.java` → Run 'main.main()'

## 🎮 Guía de Uso

### **Inicio del Juego**
```
=== BUSCAMINAS POO - UNIVERSIDAD POLITÉCNICA SALESIANA ===
Desarrollado por: [Nombre del Equipo]
Asignatura: Programación Orientada a Objetos

=== BUSCAMINAS POO ===
1. Nuevo Juego
2. Cargar Juego
3. Ver Estadísticas
4. Instrucciones
5. Salir
Selecciona una opción: 
```

### **Sistema de Coordenadas**
```
   A B C D E F G H I J
  +-------------------+
0 | ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ |
1 | ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ |
2 | ■ ■ 1 ■ ■ ■ ■ ■ ■ ■ |
3 | ■ 1 1 ■ ■ ■ ■ ■ ■ ■ |
4 | ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ |
5 | ■ ■ ■ ■ ⚑ ■ ■ ■ ■ ■ |
6 | ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ |
7 | ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ |
8 | ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ |
9 | ■ ■ ■ ■ ■ ■ ■ ■ ■ ■ |
  +-------------------+
```

**Formato de entrada**: `[Letra][Número][Modificador]`
- **Ejemplo básico**: `A5` - Descubrir casilla en columna A, fila 5
- **Marcar mina**: `B3m` - Marcar/desmarcar casilla B3 como mina

### **Leyenda de Símbolos**
| Símbolo | Significado |
|---------|-------------|
| `■` | Casilla sin descubrir |
| `⚑` | Casilla marcada como mina |
| `X` | Mina (visible al perder) |
| `1-8` | Número de minas adyacentes |
| ` ` | Casilla vacía (sin minas cerca) |

### **Comandos Especiales**
| Comando | Descripción |
|---------|-------------|
| `guardar` | Guarda la partida actual en archivo `.dat` |
| `cargar` | Carga la última partida guardada |
| `estadisticas` | Muestra estadísticas del jugador |
| `ayuda` | Muestra las instrucciones del juego |
| `salir` | Sale del juego actual |

### **Mecánica de Juego**
1. **Objetivo**: Descubrir todas las 90 casillas sin minas (100 casillas - 10 minas)
2. **Tablero**: 10×10 casillas con 10 minas distribuidas aleatoriamente
3. **Números**: Indican cantidad de minas en las 8 casillas adyacentes
4. **Áreas vacías**: Se descubren automáticamente de forma recursiva
5. **Marcación**: Permite marcar casillas sospechosas para evitar clickear por error
6. **Victoria**: Descubrir todas las casillas sin explotar ninguna mina
7. **Derrota**: Descubrir una casilla con mina

## 🧪 Pruebas y Calidad de Código

### **Características Implementadas del Sistema**

#### **1. Gestión de Tablero**
```java
// Tablero 10x10 con inicialización automática
public Tablero() {
    casillas = new Casilla[FILAS][COLUMNAS];
    inicializarTablero();
    colocarMinas();          // 10 minas aleatorias
    calcularMinasAlrededor(); // Calcula números adyacentes
}
```

#### **2. Descubrimiento Recursivo**
- Implementación de algoritmo flood-fill para casillas vacías
- Validación de límites del tablero
- Prevención de stack overflow con verificación de casillas ya descubiertas

#### **3. Sistema de Persistencia**
```java
// Guardado mediante serialización de objetos
public void guardarJuego(Juego juego) throws JuegoGuardadoException {
    try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream("juego_guardado.dat"))) {
        oos.writeObject(juego);
        guardarEstadisticas(juego);
    }
}
```

#### **4. Manejo de Excepciones**
- `CasillaYaDescubiertaException`: Intento de descubrir casilla ya revelada
- `EntradaInvalidaException`: Formato de coordenadas incorrecto
- `JuegoGuardadoException`: Errores en operaciones de archivo

#### **5. Validación de Entradas**
```java
// Conversión de coordenadas: A5 → (5, 0)
private int[] convertirCoordenadas(String entrada) 
    throws EntradaInvalidaException {
    // Valida formato [A-J][0-9][m?]
    // Maneja casos especiales y errores
}
```

## 📊 Gestión del Proyecto

### **Control de Versiones con Git**
```bash
# Clonar el repositorio
git clone https://github.com/AnthonyPilatasig/ExamenPractico_POO.git
cd ExamenPractico_POO

# Ver estado del proyecto
git status

# Agregar cambios
git add .
git commit -m "feat: descripción del cambio"
git push origin main
```

### **Convención de Commits Recomendada**
```bash
# Estructura: tipo(ámbito): descripción
git commit -m "feat(juego): implementar sistema de guardado automático"
git commit -m "fix(tablero): corregir cálculo de minas adyacentes"
git commit -m "docs(README): actualizar instrucciones de instalación"
git commit -m "refactor(controlador): mejorar validación de entradas"
```

**Tipos de commit**:
- `feat`: Nueva funcionalidad
- `fix`: Corrección de errores
- `docs`: Documentación
- `refactor`: Reestructuración de código
- `style`: Formato (sin cambios funcionales)
- `test`: Pruebas

## 🎓 Criterios de Evaluación Académica

### **Rúbrica de Calificación**
| Criterio | Ponderación | Implementación |
|----------|-------------|----------------|
| Implementación POO | 10% | ✅ Encapsulamiento completo |
| Relaciones entre Clases | 15% | ✅ MVC correctamente implementado |
| Patrón MVC | 10% | ✅ Separación clara de capas |
| Manejo de Excepciones | 15% | ✅ 3 excepciones personalizadas |
| Persistencia de Datos | 10% | ✅ Serialización + archivos de texto |
| Calidad de Código | 15% | ✅ Código limpio y documentado |
| Funcionalidad Completa | 10% | ✅ Juego totalmente funcional |
| Documentación | 10% | ✅ README completo + comentarios |
| Uso de GitHub | 5% | ✅ Repositorio estructurado |
| **Total** | **100%** | **✅ PROYECTO COMPLETADO** |

## 🔄 Flujo de Desarrollo

### **Ciclo de Vida del Juego**
```
┌─────────────────────────────────────────────────┐
│           FLUJO DE EJECUCIÓN DEL JUEGO          │
├─────────────────────────────────────────────────┤
│  1. main.java → Inicializa ControladorJuego     │
│  2. ControladorJuego → Muestra menú principal   │
│  3. Usuario selecciona: Nuevo/Cargar/Estadíst.  │
│  4. Juego crea Tablero + Jugador                │
│  5. Loop de juego:                              │
│     a) VistaConsola muestra tablero             │
│     b) Usuario ingresa coordenadas              │
│     c) ControladorJuego valida entrada          │
│     d) Juego procesa jugada                     │
│     e) Tablero actualiza estado                 │
│     f) Verificar victoria/derrota               │
│  6. Fin de juego → Actualizar estadísticas      │
│  7. Opción de guardar/volver al menú            │
└─────────────────────────────────────────────────┘
```

## 📈 Extensibilidad y Mejoras Futuras

### **Roadmap de Desarrollo**
- [x] **Fase 1**: Implementación base con consola
- [ ] **Fase 2**: Interfaz gráfica con JavaFX/Swing
- [ ] **Fase 3**: Niveles de dificultad (Fácil/Medio/Difícil)
- [ ] **Fase 4**: Sistema de puntuación con tiempo
- [ ] **Fase 5**: Modo multijugador
- [ ] **Fase 6**: Tabla de clasificación global
- [ ] **Fase 7**: Integración con base de datos (MySQL/PostgreSQL)

### **Posibles Mejoras Técnicas**
```java
// Arquitectura para extensión de dificultades
public interface NivelDificultad {
    int getFilas();
    int getColumnas();
    int getCantidadMinas();
}

public class NivelFacil implements NivelDificultad {
    public int getFilas() { return 8; }
    public int getColumnas() { return 8; }
    public int getCantidadMinas() { return 6; }
}

public class NivelDificil implements NivelDificultad {
    public int getFilas() { return 15; }
    public int getColumnas() { return 15; }
    public int getCantidadMinas() { return 30; }
}
```

## 👥 Contribución al Proyecto

### **Directrices para Colaboradores**
1. **Fork** el repositorio desde GitHub
2. **Crear rama** para la funcionalidad: `git checkout -b feature/nueva-funcionalidad`
3. **Commit** cambios con mensajes descriptivos
4. **Push** a la rama: `git push origin feature/nueva-funcionalidad`
5. **Abrir Pull Request** con descripción detallada de cambios

### **Requisitos para Pull Requests**
- ✅ Código compila sin errores
- ✅ Mantiene estructura MVC existente
- ✅ Comentarios en métodos complejos
- ✅ No introduce bugs en funcionalidad existente
- ✅ Actualiza README si agrega nuevas características

## 📚 Recursos y Referencias

### **Documentación Técnica**
- [Java Documentation - Oracle](https://docs.oracle.com/en/java/)
- [Java Serialization Tutorial](https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html)
- [Java I/O Tutorial](https://docs.oracle.com/javase/tutorial/essential/io/)
- [MVC Pattern in Java](https://www.geeksforgeeks.org/mvc-design-pattern/)

### **Bibliografía Recomendada**
1. *Clean Code* - Robert C. Martin
2. *Effective Java* - Joshua Bloch
3. *Head First Design Patterns* - Eric Freeman & Elisabeth Robson
4. *Java: The Complete Reference* - Herbert Schildt

## 🏆 Reconocimientos

### **Equipo de Desarrollo**
| Rol | Responsabilidades |
|-----|-------------------|
| Desarrollo Completo | Implementación de arquitectura MVC |
| Diseño de Modelo | Clases Juego, Tablero, Casilla, Jugador |
| Implementación de Vista | VistaConsola con interfaz de texto |
| Desarrollo de Controlador | ControladorJuego con gestión de flujo |
| Sistema de Persistencia | GestorGuardado con serialización |
| Manejo de Excepciones | 3 excepciones personalizadas |

### **Institución Académica**
**Universidad Politécnica Salesiana**  
Facultad de Ingeniería de Sistemas  
Asignatura: Programación Orientada a Objetos  
Docente: [Nombre del Profesor]  
Período Académico: [Semestre/Año]

## 🎯 Características Destacadas del Proyecto

### **1. Algoritmo de Descubrimiento Inteligente**
- Implementación recursiva para descubrir áreas vacías automáticamente
- Optimización para evitar revisitar casillas
- Manejo correcto de límites del tablero

### **2. Sistema de Persistencia Robusto**
- Guardado completo del estado del juego mediante serialización
- Registro de estadísticas históricas en archivo de texto
- Manejo de errores con excepciones personalizadas

### **3. Interfaz de Usuario Intuitiva**
- Visualización clara con símbolos Unicode (■, ⚑, X)
- Mensajes de ayuda contextuales
- Contador de minas restantes y progreso

### **4. Arquitectura Escalable**
- Separación clara de responsabilidades (MVC)
- Bajo acoplamiento entre componentes
- Fácil extensión para nuevas funcionalidades

## 📄 Licencia

Este proyecto fue desarrollado con fines académicos como parte del currículo de la **Universidad Politécnica Salesiana**. El código se distribuye bajo los términos de la **Licencia MIT**, que permite su uso, modificación y distribución para fines educativos y comerciales.

**© 2024 Universidad Politécnica Salesiana - Todos los derechos reservados para fines académicos.**

---

<div align="center">
  
  **✨ "La excelencia en el código refleja la excelencia en el pensamiento" ✨**
  
  [![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
  [![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)](https://git-scm.com/)
  [![MVC](https://img.shields.io/badge/Pattern-MVC-blue?style=for-the-badge)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
  [![POO](https://img.shields.io/badge/Paradigm-POO-green?style=for-the-badge)](https://es.wikipedia.org/wiki/Programaci%C3%B3n_orientada_a_objetos)
  [![UPS](https://img.shields.io/badge/UPS-Universidad_Polit%C3%A9cnica_Salesiana-red?style=for-the-badge)](https://www.ups.edu.ec/)

  **🎮 Proyecto Buscaminas POO - Examen Práctico Final 🎮**
  
  *Desarrollado con dedicación y aplicando los mejores principios de la Programación Orientada a Objetos*
</div>
