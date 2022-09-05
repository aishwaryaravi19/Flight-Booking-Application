package test.validator;

import java.math.BigDecimal;

public abstract class CardValidator{

    public CardValidator nextCardValidator;

    public CardValidator(CardValidator nextCardValidator) {
        this.nextCardValidator = nextCardValidator;
    }

    public abstract boolean isValidCard(BigDecimal paymentCardNumber);
}