// Productos concretos: cada clase implementa la lógica de su método de pago

// Pago con tarjeta de crédito
class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void process(double amount) {
        System.out.println("Processing credit card payment...");
        System.out.printf("Charging $%.2f to credit card%n", amount);
    }
}

// Pago vía PayPal
class PayPalProcessor implements PaymentProcessor {
    @Override
    public void process(double amount) {
        System.out.println("Processing PayPal payment...");
        System.out.printf("Charging $%.2f via PayPal%n", amount);
    }
}

// Pago por transferencia bancaria
class BankTransferProcessor implements PaymentProcessor {
    @Override
    public void process(double amount) {
        System.out.println("Processing bank transfer...");
        System.out.printf("Transferring $%.2f from bank account%n", amount);
    }
}
