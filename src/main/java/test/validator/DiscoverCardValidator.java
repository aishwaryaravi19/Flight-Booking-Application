package test.validator;

import java.math.BigDecimal;

public class DiscoverCardValidator extends CardValidator{

    public DiscoverCardValidator(CardValidator nextCardValidator) {
        super(nextCardValidator);
    }

    @Override
    public boolean isValidCard(BigDecimal paymentCardNumber) {
        String cardNumber = paymentCardNumber.toPlainString();
        if (cardNumber.startsWith("6011") && cardNumber.length() == 16) {
            return true;
        } else if (nextCardValidator != null) {
            return nextCardValidator.isValidCard(paymentCardNumber);
        }
        return false;
    }

}
