package aplicacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import entidades.*;
import servicos.*;

/**
 * Classe principal que orquestra a aplicação de console.
 * Controla os fluxos de menu, entrada de dados e interações de usuários.
 */
public class Programa {

    // Objeto Scanner estático para ler entradas do usuário em toda a aplicação.
    private static Scanner sc = new Scanner(System.in);
    // Lista estática que armazena os objetos Produto em memória, servindo como estoque.
    private static ArrayList<Produto> produtos = new ArrayList<>();
    // Serviço de pagamento estático, polimórfico, para calcular taxas.
    public static ServicoMetodoPagamento taxa;
    // Formatador de data estático para padronizar a conversão de/para String.
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    
    /**
     * Valida a entrada do usuário para garantir que um número inteiro seja fornecido.
     * Repete o prompt até que a entrada seja válida.
     * @param prompt Mensagem a ser exibida ao usuário.
     * @return O valor inteiro lido.
     */
    public static int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                // Tenta converter a linha lida para um inteiro.
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                // Se a conversão falhar, exibe erro e o loop continua.
                System.out.println("Erro: Por favor, digite um número inteiro válido.");
            }
        }
    }

    /**
     * Valida a entrada para garantir que um número double seja fornecido.
     * @param prompt Mensagem a ser exibida ao usuário.
     * @return O valor double lido.
     */
    public static double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                // Tenta converter a linha lida para um double.
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido (ex: 10.50).");
            }
        }
    }

    /**
     * Valida a entrada para garantir que uma data no formato "dd/MM/yyyy" seja fornecida.
     * @param prompt Mensagem a ser exibida ao usuário.
     * @return O objeto LocalDate criado a partir da entrada.
     */
    public static LocalDate lerData(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                // Tenta converter a linha lida para um LocalDate usando o formatador 'fmt'.
                return LocalDate.parse(sc.nextLine(), fmt);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Erro: Formato de data inválido. Use dd/MM/yyyy.");
            }
        }
    }

    /**
     * Valida a entrada para garantir que um texto não vazio seja fornecido.
     * @param prompt Mensagem a ser exibida ao usuário.
     * @return A String lida, sem espaços em branco no início ou fim.
     */
    public static String lerString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim();

            if (!entrada.isEmpty()) {
                return entrada;
            } else {
                System.out.println("Erro: Por favor, digite um texto não vazio.");
            }
        }
    }

    /**
     * Ponto de entrada principal do programa (main).
     * Inicia o programa, carrega dados de teste e controla o loop de reinício/saída.
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
    	// Carrega a lista de produtos com dados de exemplo.
    	mock();
        int op;
        do {
            // Controla o fluxo de login, separando ADM e Cliente.
            System.out.println("Bem-vindo ao nosso sistema!");
            int acess = lerInteiro("Digite a senha de acesso (ou qualquer outro número para entrar como cliente): " + "\n");

            if (acess == 1234) {
                iniciarLoopAdm();
            } else {
                iniciarLoopCliente();
            }
            // Controla o loop de execução principal do programa.
            op = lerInteiro("Para encerrar digite 0 (ou para reiniciar digite qualquer outro número): ");

        } while (op != 0);

        System.out.println("Obrigado por usar nosso sistema!");
        sc.close();
    }

    /**
     * Controla o loop da sessão do administrador.
     * Exibe o menu de ADM e processa as opções escolhidas.
     */
    public static void iniciarLoopAdm() {
        int op;
        do {
            exibirMenuAdm();
            op = lerInteiro("Selecione uma opção: ");

            switch (op) {
                case 1:
                    cadastrarNovoProduto();
                    break;
                case 2:
                    verEstoque();
                    break;
                case 3:
                    System.out.println("Encerrando sessão de administrador...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
            System.out.println();
        } while (op != 3);
    }

    /**
     * Imprime na tela as opções disponíveis no menu do administrador.
     */
    public static void exibirMenuAdm() {
        System.out.println("\n--- PAINEL DO ADMINISTRADOR ---");
        System.out.println("1 - Cadastrar novo produto");
        System.out.println("2 - Ver estoque completo");
        System.out.println("3 - Sair");
    }

    /**
     * Gerencia o formulário de cadastro de novos produtos.
     * Usa polimorfismo para instanciar a subclasse correta de Produto.
     */
    public static void cadastrarNovoProduto() {
        System.out.println("\n--- CADASTRO DE PRODUTO ---");
        
        int tipoProduto = lerInteiro("Qual o tipo do produto? (1-Alimento, 2-Limpeza, 3-Utilitário): ");
        
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();

        LocalDate dataFabric = lerData("Data de fabricação (dd/MM/yyyy): ");

        System.out.println("Gerando o código de barras...");
        int codBarras = ThreadLocalRandom.current().nextInt(100000, 999999);
        System.out.println("Código gerado: " + codBarras);

        Produto novoProduto = null; // Variável base para receber o objeto polimórfico.

        // Estrutura condicional para coletar dados específicos de cada subclasse.
        if (tipoProduto == 1) {
            double peso = lerDouble("Qual o peso (em Kg) do produto? ");
            double custoPorKg = lerDouble("Qual o custo por Kg? ");
            int qtdEstoque = lerInteiro("Quantidade em estoque: ");
            novoProduto = new ProdAlimenticio(nome, dataFabric, qtdEstoque, codBarras, peso, custoPorKg);
            System.out.println("Data de validade calculada: " + ((ProdAlimenticio) novoProduto).getDataValidade().format(fmt));

        } else if (tipoProduto == 2) {
            String subCategoria = lerString("Qual a subcategoria do produto de limpeza? (detergente, desinfetante, multiuso ou outro): ");
            double custoBase = lerDouble("Qual o custo unitário do produto? ");
            int qtdEstoque = lerInteiro("Quantidade em estoque: ");
            novoProduto = new ProdLimpeza(nome, dataFabric, qtdEstoque, codBarras, subCategoria, custoBase);

        } else if (tipoProduto == 3) {
            double custoUnitario = lerDouble("Qual o custo unitário do produto? ");
            int qtdEstoque = lerInteiro("Quantidade em estoque: ");
            novoProduto = new ProdUtilitario(nome, dataFabric, qtdEstoque, codBarras, custoUnitario);

        } else {
            System.out.println("Tipo de produto inválido!");
            return; // Encerra o método se o tipo for inválido.
        }

        produtos.add(novoProduto);
        System.out.println("\nProduto '" + novoProduto.getNome() + "' cadastrado com sucesso!");
    }

    /**
     * Controla o loop da sessão do cliente.
     * Exibe o menu de cliente e processa as opções escolhidas.
     */
    public static void iniciarLoopCliente() {
        int op;
        do {
            exibirMenuCliente();
            op = lerInteiro("Selecione uma opção: ");

            switch (op) {
                case 1:
                    verEstoque();
                    break;
                case 2:
                    comprarProdutos();
                    break;
                case 3:
                    System.out.println("Encerrando sessão de cliente...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
            System.out.println();
        } while (op != 3);
    }

    /**
     * Imprime na tela as opções disponíveis no menu do cliente.
     */
    public static void exibirMenuCliente() {
        System.out.println("\n--- BEM-VINDO, CLIENTE ---");
        System.out.println("1 - Ver produtos disponíveis");
        System.out.println("2 - Comprar produtos");
        System.out.println("3 - Sair");
    }

    /**
     * Exibe na tela todos os produtos contidos na lista 'produtos'.
     */
    public static void verEstoque() {
        System.out.println("\n--- PRODUTOS EM ESTOQUE ---");
        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            // Itera sobre a lista de produtos e chama o método toString() de cada um.
            for (Produto p : produtos) {
                System.out.println(p.toString());
            }
        }
    }

    /**
     * Gerencia o processo de compra de um produto.
     * Localiza o produto, valida estoque, atualiza a quantidade e gera uma nota fiscal.
     */
    public static void comprarProdutos() {
        System.out.println("\n--- CARRINHO DE COMPRAS ---");

        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio.");
            return;
        }

        String nomeProd = lerString("Digite o nome do produto que deseja: ");
        boolean encontrado = false;
        
        // Usando Iterator para permitir a remoção segura de itens durante o loop.
        Iterator<Produto> iterador = produtos.iterator();
        while (iterador.hasNext()) {
            Produto p = iterador.next();
            if (p.getNome().equalsIgnoreCase(nomeProd)) {
                encontrado = true;

                double precoCusto = p.calcularPrecoCusto();
                int qtdComprar = lerInteiro("Qual quantidade deseja desse produto? ");

                if (qtdComprar > 0 && qtdComprar <= p.getQtdEstoque()) {
                    p.setQtdEstoque(p.getQtdEstoque() - qtdComprar);

                    String metodoPagamento = lerString("Qual é a forma de pagamento? Pix, Débito ou Crédito? ").toLowerCase();
                    
                    // Estrutura Strategy: seleciona o serviço de pagamento com base na entrada.
                    if (metodoPagamento.startsWith("p")) {
                        taxa = new ServicoMetodoPagamentoPix();
                    } else if (metodoPagamento.startsWith("d")) {
                        taxa = new ServicoMetodoPagamentoDebito();
                    } else if (metodoPagamento.startsWith("c")) {
                        taxa = new ServicoMetodoPagamentoCredito();
                    } else {
                        System.out.println("Método não reconhecido, utilizando Pix por padrão.");
                        taxa = new ServicoMetodoPagamentoPix();
                    }

                    ServicoPagamento gerar = new ServicoPagamento(precoCusto, taxa);
                    NotaFiscal notaFiscal = gerar.processarNotaFiscal(p, qtdComprar);
                    
                    // Se o estoque zerar, o produto é removido da lista.
                    if(p.getQtdEstoque() == 0) {
                    	iterador.remove();
                    }
                    
                    System.out.println("\nCompra realizada com sucesso!");
                    System.out.println(notaFiscal);
                } else {
                    System.out.println("Quantidade inválida ou em estoque insuficiente!");
                }
                break; // Encerra o loop pois o produto foi encontrado e processado.
            }
        }

        if (!encontrado) {
            System.out.println("Produto não existe no estoque. Verifique se digitou o nome corretamente.");
        }
    }
    
    /**
     * Carrega a lista 'produtos' com dados de exemplo para teste.
     * Facilita a depuração e demonstração sem necessidade de cadastro manual.
     */
    public static void mock() {
    	produtos.add(new ProdAlimenticio("Arroz", LocalDate.parse("10/08/2024", fmt), 50 , 123123, 1.00, 5.50));
    	produtos.add(new ProdAlimenticio("Feijão", LocalDate.parse("15/09/2024", fmt), 80, 234234, 1.00, 7.20));
    	produtos.add(new ProdAlimenticio("Macarrão", LocalDate.parse("20/07/2024", fmt), 120, 345345, 0.50, 4.80));
    	produtos.add(new ProdAlimenticio("Café", LocalDate.parse("05/10/2024", fmt), 60, 456456, 0.50, 15.00));
    	produtos.add(new ProdAlimenticio("Açúcar", LocalDate.parse("30/06/2024", fmt), 150, 567567, 1.00, 3.90));
    	produtos.add(new ProdAlimenticio("Óleo de Soja", LocalDate.parse("12/05/2025", fmt), 90, 678678, 0.90, 8.50));
    	produtos.add(new ProdAlimenticio("Sal", LocalDate.parse("18/11/2025", fmt), 200, 789789, 1.00, 2.00));
    	
    	produtos.add(new ProdLimpeza("Sabão Brilux", LocalDate.parse("12/08/2025", fmt), 25, 109302, "detergente", 4.00));
    	produtos.add(new ProdLimpeza("Água Sanitária Suprema", LocalDate.parse("25/07/2025", fmt), 40, 218413, "desinfetante", 5.50));
    	produtos.add(new ProdLimpeza("Veja Multiuso Power", LocalDate.parse("10/06/2025", fmt), 60, 327524, "multiuso", 7.80));
    	produtos.add(new ProdLimpeza("Amaciante Conforto", LocalDate.parse("05/08/2025", fmt), 35, 436635, "lava-roupas", 12.00));
    	produtos.add(new ProdLimpeza("Lava-Louças Ypê Maçã", LocalDate.parse("20/07/2025", fmt), 75, 545746, "detergente", 3.20));
    	produtos.add(new ProdLimpeza("Limpador Pinho Sol", LocalDate.parse("15/05/2025", fmt), 50, 654857, "desinfetante", 9.50));
    	produtos.add(new ProdLimpeza("Limpa Vidros Cristalino", LocalDate.parse("30/08/2025", fmt), 30, 763968, "limpa-vidros", 8.00));
    	
    	produtos.add(new ProdUtilitario("Pá de madeira", LocalDate.parse("15/06/2025", fmt), 15, 123456, 10.00));
    	produtos.add(new ProdUtilitario("Vassoura de Pelo Sintético", LocalDate.parse("20/05/2025", fmt), 30, 987654, 15.50));
    	produtos.add(new ProdUtilitario("Rodo Duplo de Plástico", LocalDate.parse("01/06/2025", fmt), 25, 876543, 12.00));
    	produtos.add(new ProdUtilitario("Balde de Plástico 10L", LocalDate.parse("10/04/2025", fmt), 50, 765432, 8.75));
    	produtos.add(new ProdUtilitario("Pano de Chão Algodão", LocalDate.parse("05/03/2025", fmt), 100, 654321, 4.20));
    	produtos.add(new ProdUtilitario("Escova para Vaso Sanitário", LocalDate.parse("22/02/2025", fmt), 40, 543210, 9.00));
    	produtos.add(new ProdUtilitario("Saco de Lixo Reforçado 50L", LocalDate.parse("18/05/2025", fmt), 80, 432109, 18.30));
    }
}