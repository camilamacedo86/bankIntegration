package br.com.bank.api.function;

import br.com.bank.api.Parameters;

public interface Generate {

	public abstract <T> T generate(Parameters parameters) throws Exception;

}
