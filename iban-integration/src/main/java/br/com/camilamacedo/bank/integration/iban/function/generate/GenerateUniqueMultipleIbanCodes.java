package br.com.camilamacedo.bank.integration.iban.function.generate;

import br.com.bank.api.Parameters;
import br.com.bank.api.function.Generate;
import br.com.camilamacedo.bank.integration.iban.function.ParametersIban;
import br.com.camilamacedo.bank.integration.iban.function.context.IbanResources;
import java.util.ArrayList;
import java.util.List;

public class GenerateUniqueMultipleIbanCodes implements Generate {

    @Override
    public synchronized List<String> generate(Parameters parameters) throws Exception {
        ParametersIban parametersIban = (ParametersIban) parameters;
        List<String> all = new ArrayList<>(parametersIban.getQuantity());
        if (parametersIban == null || parametersIban.getCountry() == null) {
            throw new Exception(IbanResources.getMessage("iban-bank-integration.country.empty"));
        }
        int counter =0;
        while (counter < parametersIban.getQuantity()){
            String code = getUniqueBuildCode(parametersIban, all);
            all.add(code);
            counter++;
        }
        return all;
    }

    private String getUniqueBuildCode(ParametersIban parametersIban, List<String> all) throws Exception {
        String code = GenerateUtils.buildIbanCode(parametersIban);
        if (all.contains(code)) {
            getUniqueBuildCode(parametersIban,all);
        }
        return code;
    }

}