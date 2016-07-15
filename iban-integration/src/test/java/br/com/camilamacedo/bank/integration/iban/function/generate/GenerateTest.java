package br.com.camilamacedo.bank.integration.iban.function.generate;

import br.com.bank.api.model.Country;
import br.com.camilamacedo.bank.integration.iban.function.ParametersIban;
import br.com.camilamacedo.bank.integration.iban.function.context.IbanContext;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;

public class GenerateTest {

    @Test
    public void testGenerateValidIbanForGerman()  {
        ParametersIban parametersIban = new ParametersIban();
        parametersIban.setCountry(Country.GERMANY);
        try {
            String generatedValue = IbanContext.generate.IBAN.generate(parametersIban);
            assertNotNull(generatedValue);
            parametersIban.setCode(generatedValue);
            assertTrue(IbanContext.validate.IBAN.validate(parametersIban));
        } catch (Exception e){
            fail("Exception was occurred :" + e.getMessage());
        }
    }

    @Test
    public void testGenerateUniqueMultiIbanCodes()  {
        ParametersIban parametersIban = new ParametersIban();
        parametersIban.setCountry(Country.GERMANY);
        parametersIban.setQuantity(1000);
        try {
            List<String> all = IbanContext.generate.IBAN_MULTI.generate(parametersIban);
            assertTrue(all.size() == parametersIban.getQuantity());
            assertTrue(isAllUnique(all));
            //all.stream().forEach(item -> System.out.println(item));
        } catch (Exception e){
            fail("Exception was occurred :" + e.getMessage());
        }
    }

    @Test
    public void isAllUnique()  {
        ParametersIban parametersIban = new ParametersIban();
        parametersIban.setCountry(Country.GERMANY);
        parametersIban.setQuantity(2);
        try {
            List<String> all = IbanContext.generate.IBAN_MULTI.generate(parametersIban);
            List<String> tempTest = new ArrayList<>();
            tempTest.addAll(all);
            tempTest.add(all.get(0));
            assertFalse(isAllUnique(tempTest));
        } catch (Exception e){
            fail("Exception was occurred :" + e.getMessage());
        }
    }

    private boolean isAllUnique(List<String> all) {
        List<String> allTemp = new ArrayList<>(all);
        for (int i=0; i < all.size(); i++){
            String selected = all.get(i);
            allTemp.remove(selected);
            if ( allTemp.contains(selected)){
                return false;
            }
        }
        return true;
    }

    @Test
    public void testGenerateUniqueMultiIbanCodesIsThreadSafe()  {
        Thread t1 = new Thread(new TaskGenerateMultiIBanA());
        Thread t2 = new Thread(new TaskGenerateMultiIBanB());
        t1.start();
        t2.start();
        assertTrue(t1.getState().equals(Thread.State.BLOCKED) || t2.getState().equals(Thread.State.BLOCKED) );
        t1.interrupt();
        t2.interrupt();
    }

    private class TaskGenerateMultiIBanA implements Runnable {
        public TaskGenerateMultiIBanA() {

        }

        @Override
        public void run() {
            ParametersIban parametersIban = new ParametersIban();
            parametersIban.setCountry(Country.GERMANY);
            parametersIban.setQuantity(10000);
            try {
                IbanContext.generate.IBAN_MULTI.generate(parametersIban);
            } catch (Exception e){
                fail("Exception was occurred :" + e.getMessage());
            }
        }


    }


    private class TaskGenerateMultiIBanB implements Runnable {
        public TaskGenerateMultiIBanB() {

        }

        @Override
        public void run() {
            ParametersIban parametersIban = new ParametersIban();
            parametersIban.setCountry(Country.NETHERLANDS);
            parametersIban.setQuantity(20000);
            try {
                IbanContext.generate.IBAN_MULTI.generate(parametersIban);
            } catch (Exception e){
                fail("Exception was occurred :" + e.getMessage());
            }
        }
    }
}
