package servicos;

/**
 * Implementação concreta da Strategy 'ServicoMetodoPagamento' para pagamentos via Cartão de Débito.
 */
public class ServicoMetodoPagamentoDebito implements ServicoMetodoPagamento {

	/**
	 * Calcula a taxa para Débito. A regra de negócio define a taxa como 2% do valor total.
	 * @param valorTotal O valor da compra.
	 * @return O valor da taxa (2% do valorTotal).
	 */
	@Override
	public Double calcularTaxa(Double valorTotal) {
		double taxa = (valorTotal * 0.02);
		return taxa;
	}
}