package br.com.camilamacedo.bank.integration.iban.function.generate;

import br.com.camilamacedo.bank.integration.iban.function.ParametersIban;
import br.com.camilamacedo.bank.integration.iban.function.context.IbanResources;

import java.util.Random;
import java.util.UUID;

public class GenerateUtils {

    private static final long MAX = 999999999;
    private static final long MOD = 97;

    private static final String buildCheckDigits(String code) throws Exception{
        int modulusResult = calculateMod(code);
        int charValue = (98 - modulusResult);
        String checkDigit = Integer.toString(charValue);
        return (charValue > 9 ? checkDigit : "0" + checkDigit);
    }

    public static int calculateMod(String code) throws Exception {
        long total = 0;
        for (int i = 0; i < code.length(); i++) {
            int value = Character.getNumericValue(code.charAt(i));
            if (value < 0 || value > 35) {
                throw new Exception(IbanResources.getMessage("iban-bank-integration.build.mod.invalidcharacter", i, value));
            }
            total = (value > 9 ? total * 100 : total * 10) + value;
            if (total > MAX) {
                total = (total % MOD);
            }
        }
        return (int)(total % MOD);
    }


    public static String buildRandomNumberWithNPositions(int quantity){
        Random random = new Random();
        StringBuffer bi = new StringBuffer(quantity);
        for (int i=0; i<quantity; i++) {
            bi.append(random.nextInt(10));
        }
        return bi.toString();
    }

    public static String buildRandomAlfaWithNPositions(int quantity){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0,quantity).toUpperCase();
    }

    public static String buildIbanCode(ParametersIban parametersIban) throws Exception {
        String countryCode = parametersIban.getCountry().getCode();
        String checkDigits =  buildCheckDigits(parametersIban.getCountry().getCode());
        String bi = buildbankIdentification(parametersIban);
        String account = buildAccountCode(parametersIban);
        return countryCode + checkDigits + bi + account;
    }

    private static String buildAccountCode(ParametersIban parametersIban) {
        return buildRandomNumberWithNPositions(parametersIban.getCountry().getQuantityPositionAccount());
    }

    private static String buildbankIdentification(ParametersIban parametersIban) {
        if (parametersIban.getCountry().isAlfanumericBankIndentification() ){
            return buildRandomAlfaWithNPositions(parametersIban.getCountry().getQuantityPositionBankIndentification());
        } else {
            return buildRandomNumberWithNPositions(parametersIban.getCountry().getQuantityPositionBankIndentification());
        }
    }


}
