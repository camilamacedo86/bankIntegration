package br.com.camilamacedo.bank.integration.iban.function.validate;

import br.com.camilamacedo.bank.integration.iban.function.ParametersIban;
import br.com.camilamacedo.bank.integration.iban.function.context.IbanContext;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class ValidateTest {

    @Test
    public void testValidIbanCode()  {
        String validGerman = "DE89370400440532013000";
        ParametersIban parametersIban = new ParametersIban();
        parametersIban.setCode(validGerman);
        try {
            assertTrue(IbanContext.validate.IBAN.validate(parametersIban));
        } catch (Exception e){
            fail("Exception was occurred :" + e.getMessage());
        }
    }

    @Test
    public void testIbanWithInvalidCodeCountry()  {
        String code = "9989370400440532013000";
        ParametersIban parametersIban = new ParametersIban();
        parametersIban.setCode(code);
        try {
            assertFalse(IbanContext.validate.IBAN.validate(parametersIban));
        } catch (Exception e){
            fail("Exception was occurred :" + e.getMessage());
        }
    }

    @Test
    public void testIbanWithInvalidSizeCode()  {
        String code = "NL89370400440532013888888888888888800000000000009999999999999777777777777000";
        ParametersIban parametersIban = new ParametersIban();
        parametersIban.setCode(code);
        try {
            assertFalse(IbanContext.validate.IBAN.validate(parametersIban));
        } catch (Exception e){
            fail("Exception was occurred :" + e.getMessage());
        }
    }

    @Test
    public void testInvalidCheckDigitIbanCode()  {
        String validGerman = "DE00370400440532013000";
        ParametersIban parametersIban = new ParametersIban();
        parametersIban.setCode(validGerman);
        try {
            assertFalse(IbanContext.validate.IBAN.validate(parametersIban));
        } catch (Exception e){
            fail("Exception was occurred :" + e.getMessage());
        }
    }
}
