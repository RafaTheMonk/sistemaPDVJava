package servicos;

/**
 * Implementação concreta da Strategy 'ServicoMetodoPagamento' para pagamentos via Cartão de Crédito.
 */
public class ServicoMetodoPagamentoCredito implements ServicoMetodoPagamento {

	/**
	 * Calcula a taxa para Crédito. A regra define a taxa como 5% do valor total mais um valor fixo de R$ 1.00.
	 * @param valorTotal O valor da compra.
	 * @return O valor da taxa (5% do valorTotal + 1.00).
	 */
	@Override
	public Double calcularTaxa(Double valorTotal) {
		double taxa = (valorTotal * 0.05) + 1.00;
		return taxa;
	}
}