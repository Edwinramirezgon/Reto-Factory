// Creator abstracto — núcleo del patrón Factory Method creacional.
// Define el método factory abstracto que cada subclase concreta debe implementar.
public abstract class PaymentFactory {

    // Factory Method: cada ConcreteCreator decide qué producto instanciar
    public abstract PaymentProcessor createProcessor();

    // Lógica de negocio que usa el producto sin conocer su clase concreta
    public void processPayment(double amount) {
        createProcessor().process(amount);
    }
}
