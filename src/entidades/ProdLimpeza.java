package entidades;

import java.time.LocalDate;

public final class ProdLimpeza extends Produto {
	
	private String subCategoria;
	private double custoBaseDeAquisicao;
	
	public ProdLimpeza(String nome, LocalDate dataFabric, Integer qtdEstoque,
			Integer codBarras, String subCategoria, double custoBaseDeAquisicao) {
		super(nome, dataFabric, qtdEstoque, codBarras);
		this.subCategoria = subCategoria;
		this.custoBaseDeAquisicao = custoBaseDeAquisicao;
	}
	
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

	@Override
	public double calcularPrecoCusto() {
		double fator;
		switch (getSubCategoria().toLowerCase()) {
			case "detergente":
				fator = 1.10;
				break;
			case "desinfetante":
				fator = 1.15;
				break;
			case "multiuso":
				fator = 1.12;
				break;
			default:
				fator = 1.05;
		}
		return this.custoBaseDeAquisicao * fator;
	}
}