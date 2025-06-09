package servicos;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import entidades.NotaFiscal;
import entidades.Produto;

/**
 * Classe de serviço que atua como o "Contexto" no padrão Strategy.
 * Ela utiliza um objeto 'ServicoMetodoPagamento' para processar o pagamento
 * e gerar a nota fiscal, sem conhecer os detalhes do cálculo da taxa.
 */
public class ServicoPagamento {
	
	// O preço de custo base dos produtos na transação.
	private Double precoCusto;
	
	// A instância da estratégia de pagamento injetada (pode ser Pix, Debito, Credito, etc.).
	private ServicoMetodoPagamento metodoPagamento;

	/**
	 * Construtor que recebe as dependências necessárias.
	 * @param precoCusto O valor de custo da compra.
	 * @param metodoPagamento A estratégia de pagamento escolhida.
	 */
	public ServicoPagamento(Double precoCusto, ServicoMetodoPagamento metodoPagamento) {
		super();
		this.precoCusto = precoCusto;
		this.metodoPagamento = metodoPagamento;
	}

	// --- MÉTODOS GETTERS E SETTERS ---
	// Permitem o acesso e a modificação dos atributos privados.

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
	
	/**
	 * Processa os dados da compra e gera o objeto NotaFiscal.
	 * @param produto O produto que está sendo comprado.
	 * @param qtdComprada A quantidade de itens comprados.
	 * @return Um objeto NotaFiscal preenchido com os dados da transação.
	 */
	public NotaFiscal processarNotaFiscal(Produto produto, int qtdComprada) {
		// Define a data e hora exatas da transação.
	    LocalDateTime dataHoraCompra = LocalDateTime.now();
		// Gera um código numérico aleatório para a nota fiscal.
	    int codNota = ThreadLocalRandom.current().nextInt(10000, 99999);
		// Calcula o valor bruto da compra (custo * quantidade).
	    double precoBruto = this.precoCusto;
		// DELEGA o cálculo da taxa para a estratégia de pagamento injetada.
	    double impostos = metodoPagamento.calcularTaxa(precoBruto);
		// Calcula o valor final a ser pago.
	    double valorTotal = precoBruto + impostos;

		// Instancia e retorna o objeto NotaFiscal com todos os dados calculados.
	    return new NotaFiscal(codNota, dataHoraCompra, valorTotal, impostos);
	}

	/**
	 * Sobrescreve o toString para fornecer uma representação textual
	 * do serviço de pagamento, útil para depuração.
	 */
	@Override
	public String toString() {
		return "ServicoPagamento [precoCusto=" + precoCusto + ", metodoPagamento=" + metodoPagamento + "]";
	}
}