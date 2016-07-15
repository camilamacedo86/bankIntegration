package br.com.camilamacedo.bank.integration.iban.function;

import br.com.bank.api.Parameters;
import br.com.bank.api.model.Country;
import lombok.Data;

public @Data class ParametersIban implements Parameters {

    private Country country;
    private String code;
    private Integer quantity;

}