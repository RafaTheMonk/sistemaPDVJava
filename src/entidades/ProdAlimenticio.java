package entidades;

import java.time.LocalDate;

/**
 * Representa um produto do tipo alimentício. Herda de Produto.
 * A classe é 'final' para indicar que não pode ser estendida.
 */
public final class ProdAlimenticio extends Produto {

    // Atributos específicos de produtos alimentícios.
    private LocalDate dataValidade;
    private Double peso;
    private Double custoPorKg;
    
    /**
     * Construtor para ProdAlimenticio. Inicializa os atributos da superclasse e os específicos.
     * A data de validade é calculada automaticamente como 1 ano após a fabricação.
     */
    public ProdAlimenticio(String nome, LocalDate dataFabric, Integer qtdEstoque,
                           Integer codBarras, Double peso, Double custoPorKg) {
        super(nome, dataFabric, qtdEstoque, codBarras);
        this.peso = peso;
        this.custoPorKg = custoPorKg;
        this.dataValidade = dataFabric.plusYears(1); // Lógica de negócio para validade.
    }

    // --- MÉTODOS GETTERS E SETTERS ---

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

    /**
     * Implementa o cálculo de custo específico para alimentos,
     * baseado no peso e no custo por quilo.
     */
    @Override
    public double calcularPrecoCusto() {
        return peso * custoPorKg;
    }

    /**
     * Sobrescreve o toString para fornecer uma representação detalhada e formatada
     * de um produto alimentício, incluindo seus atributos específicos.
     */
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
             + "Custo Unitário: R$ " + String.format("%.2f", calcularPrecoCusto()) + "\n"
             + "==============================";
    }
}