package br.com.camilamacedo.bank.integration.iban.function.context;

import br.com.bank.api.Parameters;
import br.com.bank.api.function.Generate;
import br.com.bank.api.function.Validate;
import br.com.camilamacedo.bank.integration.iban.function.ParametersIban;
import br.com.camilamacedo.bank.integration.iban.function.generate.GenerateIban;
import br.com.camilamacedo.bank.integration.iban.function.generate.GenerateIbanWithBankAccount;
import br.com.camilamacedo.bank.integration.iban.function.generate.GenerateUniqueMultipleIbanCodes;
import br.com.camilamacedo.bank.integration.iban.function.validate.ValidateIban;
import br.com.camilamacedo.bank.integration.iban.function.validate.ValidateIbanWithCountry;

public class IbanContext {

    public enum generate implements Generate {
        IBAN(new GenerateIban()),
        IBAN_MULTI(new GenerateUniqueMultipleIbanCodes()),
        IBAN_WITH_BANK_ACCOUNT(new GenerateIbanWithBankAccount());
        private Generate delegate;

        private generate(Generate delegate) {
            this.delegate = delegate;
        }

        @Override
        public <T> T generate(Parameters parameters) throws Exception {
            return this.delegate.generate(parameters);
        }
    }

    public enum validate implements Validate {
        IBAN(new ValidateIban()),
        IBAN_WITH_COUNTRY(new ValidateIbanWithCountry());

        private Validate delegate;

        private validate(Validate delegate) {
            this.delegate = delegate;
        }

        @Override
        public <T> T validate(Parameters parameters) throws Exception {
            return this.delegate.validate(parameters);
        }
    }

    public static final ParametersIban getParameters() {
        return new ParametersIban();
    }
}
