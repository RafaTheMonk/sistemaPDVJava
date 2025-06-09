package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe abstrata que serve como modelo base para todos os tipos de produtos.
 * Define os atributos e comportamentos comuns a qualquer produto no sistema.
 */
public abstract class Produto {
	
	// Atributos comuns a todos os produtos.
	private String nome;
	private LocalDate dataFabric;
	private Integer qtdEstoque, codBarras;
	
	// Formatador de data compartilhado por todas as subclasses para consistência.
	protected static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
	
	/**
	 * Construtor que inicializa os atributos base de um produto.
	 * É chamado pelas subclasses através do comando 'super()'.
	 */
	public Produto(String nome, LocalDate dataFabric, Integer qtdEstoque,
			Integer codBarras) {
		this.nome = nome;
		this.dataFabric = dataFabric;
		this.qtdEstoque = qtdEstoque;
		this.codBarras = codBarras;
	}

	// --- MÉTODOS GETTERS E SETTERS ---
	// Fornecem acesso controlado aos atributos privados da classe.

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataFabric() {
		return dataFabric;
	}

	public void setDataFabric(LocalDate dataFabric) {
		this.dataFabric = dataFabric;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Integer getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(Integer codBarras) {
		this.codBarras = codBarras;
	}
	
	/**
	 * Método abstrato que força todas as subclasses a implementarem sua própria
	 * lógica de cálculo para o preço de custo.
	 * @return O valor do preço de custo do produto.
	 */
	public abstract double calcularPrecoCusto();
	
	/**
	 * Sobrescreve o método toString para fornecer uma representação textual padrão
	 * de um produto, que pode ser utilizada ou sobrescrita pelas subclasses.
	 */
	@Override
	public String toString() {
	    return "===== PRODUTO =====\n"
	         + "Nome:              " + nome + "\n"
	         + "Data de Fabricação:" + dataFabric.format(fmt) + "\n"
	         + "Qtd em Estoque:    " + qtdEstoque + "\n"
	         + "Código de Barras:  " + codBarras + "\n"
	         + "Valor Total Estoque: R$ " + String.format("%.2f", calcularPrecoCusto() * getQtdEstoque()) + "\n"
	         + "===================";
	}
}