# Comportamiento 1

En este ejemplo, queremos que dos hilos (o "fill", en el contexto del código) se ejecuten de manera ordenada, sin necesidad de usar el método `sleep`. A continuación, se muestra cómo los hilos se ejecutan y producen salida en consola.

### Descripción del Código

El código consiste en dos clases principales: `Fill` y `Principal`.

#### Clase `Fill.java`
La clase `Fill` extiende `Thread` y sobrescribe el método `run()`. Este método contiene un bucle que imprime un mensaje con el nombre del hilo (`nom`) y un número iterativo del 1 al 9.

```java
// Método run de Fill.java
@Override
public void run() {
    for (int i = 1; i < 10; i++) {
        System.out.printf("%s %d%n", this.nom, i);
    }
    System.out.println("Termina el fill " + this.nom);
}
```

# Comportamiento 2

En este ejemplo, se introduce el concepto de **prioridades de los hilos**. A cada hilo se le asigna una prioridad diferente en el código de la clase `Principal`, lo que afecta el orden en que se ejecutan en relación con otros hilos. El hilo con mayor prioridad tiene más probabilidades de ejecutarse antes que uno con menor prioridad, aunque el comportamiento final depende del planificador de hilos del sistema operativo.

### Descripción del Código

El código consta de la clase `Fill` (igual que en el comportamiento anterior) y la clase `Principal`, donde se modifica la prioridad de los hilos.

#### Clase `Principal.java`
En el método `main`, se asignan prioridades a los hilos antes de iniciarlos. A "Pepe" se le asigna una prioridad de 9 (máxima), mientras que a "Juan" se le asigna una prioridad de 1 (mínima).

```java
// Método main de Principal.java
Fill f_pepe = new Fill("Pepe");
f_pepe.setPriority(9);  // Asigna la máxima prioridad a Pepe
Fill f_juan = new Fill("Juan");
f_juan.setPriority(1);  // Asigna la mínima prioridad a Juan

f_pepe.start();  // Inicia el hilo de Pepe
f_juan.start();  // Inicia el hilo de Juan

System.out.println("Termina thread main");
```


# Comportamiento 3

En este comportamiento, se introduce el uso del método `Thread.sleep(100)` dentro del hilo para coordinar su ejecución y simular una pausa controlada entre cada iteración del bucle. Esta técnica permite que los hilos se ejecuten de forma más sincronizada, dando tiempo a que ambos compartan el CPU de manera más equitativa.

### Descripción del Código

El código sigue siendo el mismo en la clase `Principal`, pero en la clase `Fill`, se ha añadido una pausa controlada mediante `Thread.sleep(100)` dentro del bucle del método `run()`. Esto introduce una breve suspensión en cada iteración del bucle, lo que facilita la intercalación de los hilos.

#### Clase `Fill.java`
El método `run()` ahora incluye una llamada a `Thread.sleep(100)`, que detiene temporalmente la ejecución del hilo durante 100 milisegundos en cada iteración. Esto permite que el hilo de otro "fill" (si lo hubiera) tenga la oportunidad de ejecutarse.

```java
// Método run de Fill.java
@Override
public void run() {
    for (int i = 1; i < 10; i++) {
        System.out.printf("%s %d%n", this.nom, i);
        try {
            Thread.sleep(100);  // Pausa de 100 milisegundos
        } catch (Exception e) {
            e.printStackTrace();  // Manejo de excepciones si ocurre un error en sleep
        }
    }
    System.out.println("Termina el fill " + this.nom);
}
```