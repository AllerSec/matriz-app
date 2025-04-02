# Aplicación de Matrices en Java

## Descripción
Esta aplicación Java implementa una clase Matriz con funcionalidades para visualizar y operar sobre matrices numéricas. Desarrollada con Java Swing y Maven, permite crear matrices, visualizarlas gráficamente y calcular sus transpuestas siguiendo estrictamente los principios SOLID de diseño orientado a objetos.

## Estructura del Proyecto

```
matriz-app/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── matriz/
│   │   │           ├── app/
│   │   │           │   └── Main.java
│   │   │           ├── gui/
│   │   │           │   ├── MatrizFrame.java
│   │   │           │   └── MatrizPanel.java
│   │   │           ├── interfaces/
│   │   │           │   ├── IImprimible.java
│   │   │           │   └── ITransponible.java
│   │   │           └── modelo/
│   │   │               └── Matriz.java
│   │   └── resources/
└── target/
```

## Aplicación de Principios SOLID

### 1. Principio de Responsabilidad Única (S)
Cada clase tiene una única razón para cambiar:

- **Matriz.java**: Responsable únicamente de la representación y operaciones de matrices
    - Encapsula los datos de la matriz y operaciones matemáticas
    - No contiene lógica relacionada con interfaz gráfica o E/S

- **MatrizPanel.java**: Responsable únicamente de la visualización gráfica
    - Solo se ocupa de dibujar la matriz en un panel

- **MatrizFrame.java**: Responsable únicamente de la gestión de la interfaz de usuario
    - Maneja eventos y controla el flujo de la interfaz gráfica

- **Main.java**: Responsable únicamente de iniciar la aplicación
    - Punto de entrada único y configuración inicial

### 2. Principio Abierto/Cerrado (O)
Las clases están abiertas para extensión pero cerradas para modificación:

- **Matriz.java**:
    - Diseñada para permitir agregar nuevas operaciones sin modificar su implementación interna
    - Expone métodos bien definidos como `getElemento()`, `getFilas()`, `getColumnas()`

- **Interfaces IImprimible e ITransponible**:
    - Permiten extender el comportamiento implementando nuevas clases sin cambiar el código existente
    - La implementación de matrices puede cambiar sin afectar a los clientes que usan estas interfaces

### 3. Principio de Sustitución de Liskov (L)
Las subclases deben poder sustituir a sus clases base:

- **Matriz.java**:
    - Implementa completamente los contratos definidos por las interfaces
    - No viola las precondiciones o postcondiciones establecidas en las interfaces
    - Cualquier subclase que extienda Matriz mantendría el comportamiento esperado

### 4. Principio de Segregación de Interfaces (I)
Interfaces específicas son mejores que una interfaz general:

- **IImprimible.java**:
    - Contiene solo el método `imprimir()` relacionado con la representación textual
    - No obliga a implementar métodos no relacionados

- **ITransponible.java**:
    - Se enfoca exclusivamente en la operación matemática de transpuesta
    - Utiliza genéricos para mayor flexibilidad (`ITransponible<T>`)

### 5. Principio de Inversión de Dependencias (D)
Depender de abstracciones, no de implementaciones concretas:

- **MatrizPanel.java**:
    - Depende de la abstracción de Matriz a través de su interfaz pública
    - No depende de detalles internos de implementación

- **MatrizFrame.java**:
    - Interactúa con las matrices a través de sus interfaces
    - La lógica de presentación está desacoplada de la lógica de negocio

## Características Principales

- Creación de matrices a partir de texto ingresado por el usuario
- Visualización gráfica de matrices con celdas coloreadas
- Cálculo e implementación de la operación de transpuestas
- Representación textual de matrices originales y transpuestas
- Interfaz de usuario intuitiva con operaciones claramente definidas
- Validación de datos para asegurar la integridad de las matrices

## Requisitos

- Java 11 o superior
- Maven 3.6 o superior

## Compilación y Ejecución

Para compilar el proyecto:
```
mvn clean package
```

Para ejecutar la aplicación:
```
java -jar target/matriz-app-1.0-SNAPSHOT.jar
```

## Uso

1. Ingrese los valores de la matriz en el área de texto superior, separando las filas con saltos de línea y los valores con espacios
2. Haga clic en "Crear Matriz" para visualizar la matriz
3. Use el botón "Transponer" para calcular y visualizar la transpuesta de la matriz actual

## Licencia
[MIT License](LICENSE)

## Link 
https://github.com/AllerSec/matriz-app.git
