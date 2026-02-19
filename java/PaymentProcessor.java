// Producto abstracto: contrato que todo procesador de pago debe cumplir
public interface PaymentProcessor {
    void process(double amount);
}
