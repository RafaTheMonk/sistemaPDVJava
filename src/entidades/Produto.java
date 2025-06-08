package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Produto {
	private String nome;
	private LocalDate dataFabric;
	private Integer qtdEstoque, codBarras;
	private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
	
	public Produto(String nome, LocalDate dataFabric, Integer qtdEstoque,
			Integer codBarras) {
		super();
		this.nome = nome;
		this.dataFabric = dataFabric;
		this.qtdEstoque = qtdEstoque;
		this.codBarras = codBarras;
	}

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
	
	public abstract double calcularPrecoCusto();
	
	@Override
	public String toString() {
	    return "===== PRODUTO =====\n"
	         + "Nome:              " + nome + "\n"
	         + "Data de Fabricação:" + dataFabric.format(fmt) + "\n"
	         + "Qtd em Estoque:    " + qtdEstoque + "\n"
	         + "Código de Barras:  " + codBarras + "\n"
	         + "Valor Total Estoque: R$ " + String.format("%.2f", calcularPrecoCusto()) + "\n"
	         + "===================";
	}
	
}
