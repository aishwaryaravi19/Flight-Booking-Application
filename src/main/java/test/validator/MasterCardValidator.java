package test.validator;

import java.math.BigDecimal;

public class MasterCardValidator<T> extends CardValidator {

    public MasterCardValidator(CardValidator nextCardValidator) {
        super(nextCardValidator);
    }

    @Override
    public boolean isValidCard(BigDecimal paymentCardNumber) {
        String cardNumber = paymentCardNumber.toPlainString();
        int secondDigit = cardNumber.toCharArray()[1]-'0';
        if (cardNumber.startsWith("5") && cardNumber.length() == 16 && (secondDigit >= 1 && secondDigit <=5)) {
            return true;
        } else if (nextCardValidator != null) {
            return nextCardValidator.isValidCard(paymentCardNumber);
        }
        return false;
    }
}
