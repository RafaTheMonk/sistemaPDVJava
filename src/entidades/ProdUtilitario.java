package entidades;

import java.time.LocalDate;

/**
 * Representa um produto do tipo utilitário (ex: ferramentas, utensílios). Herda de Produto.
 * A classe é 'final' para indicar que não pode ser estendida.
 */
public final class ProdUtilitario extends Produto {
	
	// Atributo específico para produtos utilitários.
	private double custoUnitario;
	
	/**
     * Construtor para ProdUtilitario. Inicializa os atributos da superclasse e os específicos.
     */
	public ProdUtilitario(String nome, LocalDate dataFabric, Integer qtdEstoque,
			Integer codBarras, double custoUnitario) {
		super(nome, dataFabric, qtdEstoque, codBarras);
		this.custoUnitario = custoUnitario;
	}

	// --- MÉTODOS GETTERS E SETTERS ---

	public double getCustoUnitario() {
		return custoUnitario;
	}

	public void setCustoUnitario(double custoUnitario) {
		this.custoUnitario = custoUnitario;
	}

	/**
	 * Implementa o cálculo de custo para produtos utilitários,
	 * que é simplesmente seu custo de aquisição direto.
	 */
	@Override
	public double calcularPrecoCusto() {
		return custoUnitario;
	}

	/**
     * Sobrescreve o toString para fornecer uma representação detalhada e formatada
     * de um produto utilitário.
     */
	@Override
	public String toString() {
		return "============================\n"
		         + "PRODUTO UTILITÁRIO\n"
		         + "Nome: " + getNome() + "\n"
		         + "Data de Fabricação: " + getDataFabric().format(fmt) + "\n"
		         + "Qtd em Estoque: " + getQtdEstoque() + "\n"
		         + "Código de Barras: " + getCodBarras() + "\n"
		         + "Custo Unitário: R$ " + String.format("%.2f", getCustoUnitario()) + "\n"
		         + "============================";
	}
}