package test.validator;

import java.math.BigDecimal;

public class AmexCardValidator<T> extends CardValidator{

    public AmexCardValidator(CardValidator nextCardValidator) {
        super(nextCardValidator);
    }

    @Override
    public boolean isValidCard(BigDecimal paymentCardNumber) {
        String cardNumber = paymentCardNumber.toPlainString();
        int secondDigit = cardNumber.toCharArray()[1]-'0';
        if (cardNumber.startsWith("3") && cardNumber.length() == 15 &&  (secondDigit== 4 || secondDigit == 7)) {
            return true;
        } else if (nextCardValidator != null) {
            return nextCardValidator.isValidCard(paymentCardNumber);
        }
        return false;
    }

}
