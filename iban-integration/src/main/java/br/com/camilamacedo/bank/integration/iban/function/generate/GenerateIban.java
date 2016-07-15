package br.com.camilamacedo.bank.integration.iban.function.generate;

import br.com.bank.api.Parameters;
import br.com.bank.api.function.Generate;
import br.com.camilamacedo.bank.integration.iban.function.ParametersIban;
import br.com.camilamacedo.bank.integration.iban.function.context.IbanResources;

public class GenerateIban implements Generate {

    @Override
    public String generate(Parameters parameters) throws Exception {
        ParametersIban parametersIban = (ParametersIban) parameters;
        if (parametersIban == null || parametersIban.getCountry() == null) {
            throw new Exception(IbanResources.getMessage("iban-bank-integration.country.empty"));
        }
        return GenerateUtils.buildIbanCode(parametersIban);
    }


}