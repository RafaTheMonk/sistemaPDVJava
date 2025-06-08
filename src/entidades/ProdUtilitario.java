package entidades;

import java.time.LocalDate;

public final class ProdUtilitario extends Produto {
	
	private double custoUnitario;
	
	public ProdUtilitario(String nome, LocalDate dataFabric, Integer qtdEstoque,
			Integer codBarras, double custoUnitario) {
		super(nome, dataFabric, qtdEstoque, codBarras);
		this.custoUnitario = custoUnitario;
	}

	public double getCustoUnitario() {
		return custoUnitario;
	}

	public void setCustoUnitario(double custoUnitario) {
		this.custoUnitario = custoUnitario;
	}

	@Override
	public double calcularPrecoCusto() {
		return this.custoUnitario;
	}
}