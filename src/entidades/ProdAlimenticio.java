package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class ProdAlimenticio extends Produto {

    private LocalDate dataValidade;
    private Double peso;
    private Double custoPorKg;
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public ProdAlimenticio(String nome, LocalDate dataFabric, Integer qtdEstoque,
                           Integer codBarras, Double peso, Double custoPorKg) {
        super(nome, dataFabric, qtdEstoque, codBarras);
        this.peso = peso;
        this.custoPorKg = custoPorKg;
        this.dataValidade = dataFabric.plusYears(1);
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getCustoPorKg() {
        return custoPorKg;
    }

    public void setCustoPorKg(Double custoPorKg) {
        this.custoPorKg = custoPorKg;
    }

    @Override
    public double calcularPrecoCusto() {
        return peso * custoPorKg;
    }

    @Override
    public String toString() {
        return "==============================\n"
             + "PRODUTO ALIMENTÍCIO\n"
             + "Nome: " + getNome() + "\n"
             + "Data de Fabricação: " + getDataFabric().format(fmt) + "\n"
             + "Data de Validade: " + dataValidade.format(fmt) + "\n"
             + "Peso: " + String.format("%.2f", peso) + " Kg\n"
             + "Custo por Kg: R$ " + String.format("%.2f", custoPorKg) + "\n"
             + "Quantidade em Estoque: " + getQtdEstoque() + "\n"
             + "Código de Barras: " + getCodBarras() + "\n"
             + "Valor Total em Produtos: R$ " + String.format("%.2f", calcularPrecoCusto()) + "\n"
             + "==============================";
    }
}
