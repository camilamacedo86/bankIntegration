package br.com.bank.api.function;

import br.com.bank.api.Parameters;

public interface Validate {

	public abstract <T> T validate(Parameters parameters) throws Exception;

}
