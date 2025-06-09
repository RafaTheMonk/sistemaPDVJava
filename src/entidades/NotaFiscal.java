package entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Modela os dados de uma Nota Fiscal gerada após uma compra.
 * É uma classe de entidade, responsável por armazenar informações.
 */
public class NotaFiscal {
	
	// Formatador estático para padronizar a exibição de data e hora.
	private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	// Atributos que representam os dados da nota fiscal.
	private Integer codNota;            // Código único da nota.
	private LocalDateTime dataHoraCompra; // Data e hora exatas da transação.
	private Double valorTotal;          // Valor final pago pelo cliente.
	private Double impostos;            // Parcela do valor total que corresponde a impostos.
	
	/**
	 * Construtor padrão para criar uma instância de NotaFiscal.
	 * Inicializa o objeto com todos os dados necessários.
	 * @param codNota Código da nota.
	 * @param dataHoraCompra Data e hora da compra.
	 * @param valorTotal Valor total pago.
	 * @param impostos Valor dos impostos incluídos.
	 */
	public NotaFiscal(Integer codNota, LocalDateTime dataHoraCompra, Double valorTotal, Double impostos) {
		super(); // Chamada ao construtor da superclasse (Object).
		this.codNota = codNota;
		this.dataHoraCompra = dataHoraCompra;
		this.valorTotal = valorTotal;
		this.impostos = impostos;
	}
	
	// --- MÉTODOS GETTERS E SETTERS ---
	// Permitem o acesso e a modificação controlada dos atributos privados,
	// seguindo o princípio do encapsulamento.
	
	public Integer getCodNota() {
		return codNota;
	}
	public void setCodNota(Integer codNota) {
		this.codNota = codNota;
	}
	public LocalDateTime getDataHoraCompra() {
		return dataHoraCompra;
	}
	public void setDataHoraCompra(LocalDateTime dataHoraCompra) {
		this.dataHoraCompra = dataHoraCompra;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Double getImpostos() {
		return impostos;
	}
	public void setImpostos(Double impostos) {
		this.impostos = impostos;
	}
	
	/**
	 * Sobrescreve o método toString padrão para fornecer uma representação textual
	 * formatada e legível da nota fiscal, ideal para exibição no console.
	 * @return Uma String com todos os dados da nota fiscal formatados.
	 */
	@Override
	public String toString() {
	    return "===== NOTA FISCAL =====\n"
	         + "Código da Nota:      " + codNota + "\n"
	         + "Data/Hora da Compra: " + fmt.format(dataHoraCompra) + "\n"
	         + "Valor Total:         R$ " + String.format("%.2f", valorTotal) + "\n"
	         + "Impostos:            R$ " + String.format("%.2f", impostos) + "\n"
	         + "========================";
	}
}