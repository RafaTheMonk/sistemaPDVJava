package entidades;

import java.time.LocalDate;

/**
 * Representa um produto do tipo limpeza. Herda de Produto.
 * A classe é 'final' para indicar que não pode ser estendida.
 */
public final class ProdLimpeza extends Produto {
	
	// Atributos específicos de produtos de limpeza.
	private String subCategoria;
	private double custoBaseDeAquisicao;
	
	/**
     * Construtor para ProdLimpeza. Inicializa os atributos da superclasse e os específicos.
     */
	public ProdLimpeza(String nome, LocalDate dataFabric, Integer qtdEstoque,
			Integer codBarras, String subCategoria, double custoBaseDeAquisicao) {
		super(nome, dataFabric, qtdEstoque, codBarras);
		this.subCategoria = subCategoria;
		this.custoBaseDeAquisicao = custoBaseDeAquisicao;
	}
	
	// --- MÉTODOS GETTERS E SETTERS ---

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public double getCustoBaseDeAquisicao() {
		return custoBaseDeAquisicao;
	}

	public void setCustoBaseDeAquisicao(double custoBaseDeAquisicao) {
		this.custoBaseDeAquisicao = custoBaseDeAquisicao;
	}

	/**
	 * Implementa o cálculo de custo para produtos de limpeza, aplicando um fator
	 * de custo (ex: impostos) que varia de acordo com a subcategoria.
	 */
	@Override
	public double calcularPrecoCusto() {
		double fator;
		switch (getSubCategoria().toLowerCase()) {
			case "detergente":
				fator = 1.10; // Fator de 10%
				break;
			case "desinfetante":
				fator = 1.15; // Fator de 15%
				break;
			case "multiuso":
				fator = 1.12; // Fator de 12%
				break;
			default:
				fator = 1.05; // Fator padrão
		}
		return custoBaseDeAquisicao * fator;
	}

	/**
     * Sobrescreve o toString para fornecer uma representação detalhada e formatada
     * de um produto de limpeza.
     */
	@Override
	public String toString() {
		    return "============================\n"
		         + "PRODUTO LIMPEZA\n"
		         + "Nome: " + getNome() + "\n"
		         + "Subcategoria: " + getSubCategoria() + "\n"
		         + "Data de Fabricação: " + getDataFabric().format(fmt) + "\n"
		         + "Qtd em Estoque: " + getQtdEstoque() + "\n"
		         + "Código de Barras: " + getCodBarras() + "\n"
		         + "Custo de Aquisição: R$ " + String.format("%.2f", getCustoBaseDeAquisicao()) + "\n"
		         + "Custo Unitário (c/ fator): R$ " + String.format("%.2f", calcularPrecoCusto()) + "\n"
		         + "============================";
	}
}