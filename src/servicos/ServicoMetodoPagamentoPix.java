package servicos;

/**
 * Implementação concreta da Strategy 'ServicoMetodoPagamento' para pagamentos via Pix.
 */
public class ServicoMetodoPagamentoPix implements ServicoMetodoPagamento {

	/**
	 * Calcula a taxa para Pix. Nesta regra de negócio, a taxa é zero.
	 * @param valorTotal O valor da compra (não utilizado neste cálculo).
	 * @return Retorna sempre 0.0.
	 */
	@Override
	public Double calcularTaxa(Double valorTotal) {
		return 0.0;
	}
}