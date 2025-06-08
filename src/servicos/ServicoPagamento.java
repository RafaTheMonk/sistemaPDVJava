package servicos;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import entidades.NotaFiscal;
import entidades.Produto;

public class ServicoPagamento {
	private Double precoCusto;
	private ServicoMetodoPagamento metodoPagamento;

	public ServicoPagamento(Double precoCusto, ServicoMetodoPagamento metodoPagamento) {
		super();
		this.precoCusto = precoCusto;
		this.metodoPagamento = metodoPagamento;
	}

	public Double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public ServicoMetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(ServicoMetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
	
	public NotaFiscal processarNotaFiscal(Produto produto, int qtdComprada) {
	    LocalDateTime dataHoraCompra = LocalDateTime.now();
	    int codNota = ThreadLocalRandom.current().nextInt(10000, 99999);	    
	    double precoBruto = precoCusto * qtdComprada;
	    double impostos = metodoPagamento.calcularTaxa(precoBruto);
	    double valorTotal = precoBruto + impostos;

	    return new NotaFiscal(codNota, dataHoraCompra, valorTotal, impostos);
	}

	@Override
	public String toString() {
		return "ServicoPagamento [precoCusto=" + precoCusto + ", metodoPagamento=" + metodoPagamento + "]";
	}
}
