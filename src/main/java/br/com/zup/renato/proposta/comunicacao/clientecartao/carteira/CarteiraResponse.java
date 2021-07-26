package br.com.zup.renato.proposta.comunicacao.clientecartao.carteira;

public class CarteiraResponse {

	private String resultado;
	private String id;

	public String getResultado() {
		return resultado;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CarteiraResponse [resultado=" + resultado + ", id=" + id + "]";
	}

}
