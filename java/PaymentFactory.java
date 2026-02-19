// Fábrica única — núcleo del patrón Factory Method.
// Centraliza la creación de procesadores: el cliente nunca usa "new" directamente.
// Para agregar un nuevo método de pago solo se añade un case aquí.
public class PaymentFactory {

    public static PaymentProcessor create(String method) {
        return switch (method) {
            case "Credit Card"   -> new CreditCardProcessor();
            case "PayPal"        -> new PayPalProcessor();
            case "Bank Transfer" -> new BankTransferProcessor();
            default -> throw new IllegalArgumentException("Unknown payment method: " + method);
        };
    }
}
