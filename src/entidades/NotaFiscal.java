package entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotaFiscal {
	
	private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
	private Integer codNota;
	private LocalDateTime dataHoraCompra;
	private Double valorTotal, impostos;
	
	public NotaFiscal(Integer codNota, LocalDateTime dataHoraCompra, Double valorTotal, Double impostos) {
		super();
		this.codNota = codNota;
		this.dataHoraCompra = dataHoraCompra;
		this.valorTotal = valorTotal;
		this.impostos = impostos;
	}
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
