package servicos;

/**
 * Define o contrato (a "Strategy") para todos os serviços de método de pagamento.
 * Qualquer classe que represente uma forma de pagamento deve implementar esta interface,
 * garantindo que possua o método para calcular a taxa.
 */
public interface ServicoMetodoPagamento {

	/**
	 * Assinatura do método que calcula a taxa sobre um determinado valor.
	 * A lógica específica do cálculo será definida nas classes que implementam a interface.
	 * @param valorTotal O valor base sobre o qual a taxa será calculada.
	 * @return O valor da taxa calculada.
	 */
	public Double calcularTaxa(Double valorTotal);
}