package br.com.bank.api.model;

public enum Country {

    GERMANY("DE", 8, 10, false),
    AUSTRIA("AT", 5, 11, false),
    NETHERLANDS("NL", 4, 10, false);

    private final String code;
    private final Integer quantityPositionAccount;
    private final Integer quantityPositionBankIndentification;
    private final boolean alfanumericBankIndentification;


    Country(String code, Integer quantityPositionAccount, Integer quantityPositionBankIndentification, boolean alfanumericBankIndentification) {
        this.code = code;
        this.quantityPositionAccount = quantityPositionAccount;
        this.quantityPositionBankIndentification = quantityPositionBankIndentification;
        this.alfanumericBankIndentification = alfanumericBankIndentification;
    }

    public String getCode() {
        return code;
    }

    public Integer getQuantityPositionAccount() {
        return quantityPositionAccount;
    }

    public Integer getQuantityPositionBankIndentification() {
        return quantityPositionBankIndentification;
    }

    public boolean isAlfanumericBankIndentification() {
        return alfanumericBankIndentification;
    }
}
