import org.junit.Test;
import test.validator.*;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ChainOfResponsibilityTest {

    private static CardValidator getChainOfPaymentValidator() {
        CardValidator visaCardValidator = new VisaCardValidator(null);
        CardValidator masterCardValidator = new MasterCardValidator(visaCardValidator);
        CardValidator discoverCardValidator = new DiscoverCardValidator(masterCardValidator);
        return new AmexCardValidator(discoverCardValidator);
    }

    @Test
    public void givenCorrectMasterCardNumber_whenCheckingValidity_thenSuccess() {
        String paymentCardNumber = "5410000000000000";
        CardValidator cardValidatorChain = getChainOfPaymentValidator();
        assertTrue(cardValidatorChain.isValidCard(BigDecimal.valueOf(Long.valueOf(paymentCardNumber))));
    }

    @Test
    public void givenCorrectAmexCardNumber_whenCheckingValidity_thenSuccess() {
        String paymentCardNumber = "341000000000000";
        CardValidator cardValidatorChain = getChainOfPaymentValidator();
        assertTrue(cardValidatorChain.isValidCard(BigDecimal.valueOf(Long.valueOf(paymentCardNumber))));
    }

    @Test
    public void givenCorrectVisaCardNumber_whenCheckingValidity_thenSuccess() {
        String paymentCardNumber = "4120000000000";
        CardValidator cardValidatorChain = getChainOfPaymentValidator();
        assertTrue(cardValidatorChain.isValidCard(BigDecimal.valueOf(Long.valueOf(paymentCardNumber))));
    }

    @Test
    public void givenCorrectDiscoverCardNumber_whenCheckingValidity_thenSuccess() {
        String paymentCardNumber = "6011000000000000";
        CardValidator cardValidatorChain = getChainOfPaymentValidator();
        assertTrue(cardValidatorChain.isValidCard(BigDecimal.valueOf(Long.valueOf(paymentCardNumber))));
    }

    @Test
    public void givenWrongCardNumber_whenCheckingValidity_thenSuccess() {
        String paymentCardNumber = "1234561323130";
        CardValidator cardValidatorChain = getChainOfPaymentValidator();
        assertFalse(cardValidatorChain.isValidCard(BigDecimal.valueOf(Long.valueOf(paymentCardNumber))));
    }

}
