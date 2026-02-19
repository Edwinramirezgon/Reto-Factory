# Sistema de Pagos — Patrón Factory Method Creacional

## Problema que resuelve

El código original usaba `if/else` encadenados para decidir cómo procesar cada pago. Agregar un nuevo método significaba modificar la lógica central, rompiendo el principio Open/Closed.

## Qué implementa cada archivo

| Archivo | Rol en el patrón | Qué es |
|---|---|---|
| `PaymentProcessor.java` | **Producto abstracto** | Interfaz con el contrato `process(amount)` que todo procesador debe cumplir |
| `ConcreteProcessors.java` | **Productos concretos** | `CreditCardProcessor`, `PayPalProcessor`, `BankTransferProcessor` — cada uno con su lógica propia |
| `PaymentFactory.java` | **Creator abstracto** | Clase abstracta con el Factory Method `createProcessor()` y la lógica de negocio `processPayment()` |
| `ConcreteCreators.java` | **Creators concretos** | `CreditCardFactory`, `PayPalFactory`, `BankTransferFactory` — cada uno sobreescribe `createProcessor()` |
| `Main.java` | **Cliente** | GUI Swing que trabaja con `PaymentFactory` (tipo abstracto) sin conocer qué clase concreta se usa |

## Cómo se relacionan

```
Main (cliente)
    │
    └──▶ resolveCreator("Credit Card") → CreditCardFactory (extiende PaymentFactory)
                │
                └──▶ createProcessor() → new CreditCardProcessor()  ──▶ implements PaymentProcessor
                     createProcessor() → new PayPalProcessor()       ──▶ implements PaymentProcessor
                     createProcessor() → new BankTransferProcessor() ──▶ implements PaymentProcessor
```

## Por qué cumple Factory Method creacional

1. **Creator abstracto**: `PaymentFactory` define `createProcessor()` como método abstracto — las subclases deciden qué instanciar.
2. **ConcreteCreators**: cada fábrica concreta sobreescribe el Factory Method con su propio `new`.
3. **Producto abstracto**: `Main` solo conoce `PaymentFactory` y `PaymentProcessor`, nunca las clases concretas.
4. **Abierto/Cerrado**: agregar un nuevo método de pago no modifica ninguna clase existente.

## Cómo agregar un nuevo método de pago

```java
// 1. Nuevo procesador en ConcreteProcessors.java
class CryptoProcessor implements PaymentProcessor {
    public void process(double amount) { ... }
}

// 2. Nuevo ConcreteCreator en ConcreteCreators.java
class CryptoFactory extends PaymentFactory {
    public PaymentProcessor createProcessor() { return new CryptoProcessor(); }
}

// 3. Nuevo case en resolveCreator() y entrada en METHODS[] de Main.java
case "Crypto" -> new CryptoFactory();
private static final String[] METHODS = {"Credit Card", "PayPal", "Bank Transfer", "Crypto"};
```

## Cómo ejecutar

```bash
cd java
javac *.java
java Main
```
