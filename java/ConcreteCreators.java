// ConcreteCreators: cada clase extiende PaymentFactory y sobreescribe
// el Factory Method para instanciar su propio producto concreto.

public class CreditCardFactory extends PaymentFactory {
    @Override
    public PaymentProcessor createProcessor() {
        return new CreditCardProcessor();
    }
}

class PayPalFactory extends PaymentFactory {
    @Override
    public PaymentProcessor createProcessor() {
        return new PayPalProcessor();
    }
}

class BankTransferFactory extends PaymentFactory {
    @Override
    public PaymentProcessor createProcessor() {
        return new BankTransferProcessor();
    }
}
