package br.com.camilamacedo.bank.integration.iban.function.context;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;


public class IbanResources{
    private static final ResourceBundle MESSAGENS = ResourceBundle.getBundle("iban_messages", Locale.getDefault());

    private IbanResources() {

    }

    public static final String getMessage(String key, Object... params) {
        return MessageFormat.format(MESSAGENS.getString(key), params);
    }

}