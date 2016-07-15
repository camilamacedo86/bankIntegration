package br.com.camilamacedo.bank.integration.iban.function.validate;

import br.com.bank.api.Parameters;
import br.com.bank.api.function.Validate;
import br.com.camilamacedo.bank.integration.iban.function.ParametersIban;
import br.com.camilamacedo.bank.integration.iban.function.generate.GenerateUtils;

import java.util.logging.Logger;


public class ValidateIban implements Validate {

    @Override
    public Boolean validate(Parameters parameters) {
        ParametersIban parametersIban = (ParametersIban) parameters;
        String code = treatCode(parametersIban.getCode());

        if (isInGeneralInvalid(code)) return false;
        if (isInValidForTheCountry(code)) return false;


        return true;
    }

    private boolean isInvalidCheckDigits(String code) {
        String check = code.substring(2,4); // CHECKSTYLE IGNORE MagicNumber
        if ("00".equals(check) || "01".equals(check) || "99".equals(check)) {
            return true;
        }
        try {
            int modulusResult = GenerateUtils.calculateMod(code);
            if (modulusResult == 1){
                return false;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    private boolean isInGeneralInvalid(String code) {
        if (isInvalidSize(code)) {
            return true;
        }
        if (isInvalidCountryCode(code)){
            return true;
        }
        if (isInvalidCheckDigits(code)){
            return true;
        }
        return false;
    }

    private boolean isInValidForTheCountry(String code){
        // TODO: Implement
        return false;
    }

    private boolean isInvalidSize(String code) {
        if ( code.length() < 0 || code.length() > 34) {
            return true;
        }
        return false;
    }

    private boolean isInvalidCountryCode(String code) {
        if ( code.subSequence(0,2).chars().filter(c-> !Character.isAlphabetic(c)).count() > 0){
            return true;
        }
        return false;
    }

    /**
     * @param words
     * @return code without spaces and special characters
     */
    private String treatCode(String words) {
        words = words.replaceAll("[!,?._'@]", "");
        words = words.replaceAll("^ +| +$|( )+", " ");
        words = words.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
        return words;
    }
}