package aplicacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import entidades.*;
import servicos.*;

public class Programa {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Produto> produtos = new ArrayList<>();
    public static ServicoMetodoPagamento taxa;
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    
    public static int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número inteiro válido.");
            }
        }
    }

    public static double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido (ex: 10.50).");
            }
        }
    }

    public static LocalDate lerData(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(sc.nextLine(), fmt);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Erro: Formato de data inválido. Use dd/MM/yyyy.");
            }
        }
    }

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

    public static void main(String[] args) {
    	
    	mock();
        int op;
        do {
            System.out.println("Bem-vindo ao nosso sistema!");
            int acess = lerInteiro("Digite a senha de acesso (ou qualquer outro número para entrar como cliente): " + "\n");

            if (acess == 1234) {
                iniciarLoopAdm();
            } else {
                iniciarLoopCliente();
            }
            op = lerInteiro("Para encerrar digite 0 (ou para reiniciar digite qualquer outro número): ");

        } while (op != 0);

        System.out.println("Obrigado por usar nosso sistema!");
        sc.close();
    }

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

    public static void exibirMenuAdm() {
        System.out.println("\n--- PAINEL DO ADMINISTRADOR ---");
        System.out.println("1 - Cadastrar novo produto");
        System.out.println("2 - Ver estoque completo");
        System.out.println("3 - Sair");
    }

    public static void cadastrarNovoProduto() {
        System.out.println("\n--- CADASTRO DE PRODUTO ---");
        
        int tipoProduto = lerInteiro("Qual o tipo do produto? (1-Alimento, 2-Limpeza, 3-Utilitário): ");
        
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();

        LocalDate dataFabric = lerData("Data de fabricação (dd/MM/yyyy): ");

        System.out.println("Gerando o código de barras...");
        int codBarras = ThreadLocalRandom.current().nextInt(100000, 999999);
        System.out.println("Código gerado: " + codBarras);

        Produto novoProduto = null;

        if (tipoProduto == 1) {
            double peso = lerDouble("Qual o peso (em Kg) do produto? ");
            double custoPorKg = lerDouble("Qual o custo por Kg? ");
            int qtdEstoque = lerInteiro("Quantidade em estoque: ");

            novoProduto = new ProdAlimenticio(nome, dataFabric, qtdEstoque, codBarras, peso, custoPorKg);

            System.out.println("Data de validade calculada: " + ((ProdAlimenticio) novoProduto).getDataValidade().format(fmt));

        } else if (tipoProduto == 2) {
            System.out.println("Qual a subcategoria do produto de limpeza? ");
            String subCategoria = lerString(("detergente, desinfetante, multiuso ou outro: "));
            double custoBase = lerDouble("Qual o custo base de aquisição? ");
            int qtdEstoque = lerInteiro("Quantidade em estoque: ");
            
            novoProduto = new ProdLimpeza(nome, dataFabric, qtdEstoque, codBarras, subCategoria, custoBase);

        } else if (tipoProduto == 3) {
            double custoUnitario = lerDouble("Qual o custo unitário do produto? ");
            int qtdEstoque = lerInteiro("Quantidade em estoque: ");
            
            novoProduto = new ProdUtilitario(nome, dataFabric, qtdEstoque, codBarras, custoUnitario);

        } else {
            System.out.println("Tipo de produto inválido!");
            return;
        }

        produtos.add(novoProduto);
        System.out.println("\nProduto '" + novoProduto.getNome() + "' cadastrado com sucesso!");
    }

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

    public static void exibirMenuCliente() {
        System.out.println("\n--- BEM-VINDO, CLIENTE ---");
        System.out.println("1 - Ver produtos disponíveis");
        System.out.println("2 - Comprar produtos");
        System.out.println("3 - Sair");
    }

    public static void verEstoque() {
        System.out.println("\n--- PRODUTOS EM ESTOQUE ---");
        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p.toString());
            }
        }
    }

    public static void comprarProdutos() {
        System.out.println("\n--- CARRINHO DE COMPRAS ---");

        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio.");
            return;
        }

        String nomeProd = lerString("Digite o nome do produto que deseja: ");
        boolean encontrado = false;

        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nomeProd)) {
                encontrado = true;

                double precoCusto = p.calcularPrecoCusto();

                int qtdComprar = lerInteiro("Qual quantidade deseja desse produto? ");
                if (qtdComprar <= p.getQtdEstoque()) {
                    p.setQtdEstoque(p.getQtdEstoque() - qtdComprar);

                    String metodoPagamento = lerString("Qual é a forma de pagamento? Pix, Débito ou Crédito? ");

                    metodoPagamento = metodoPagamento.trim().toLowerCase();
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

                    ServicoPagamento gerar = new ServicoPagamento(precoCusto * qtdComprar, taxa);
                    NotaFiscal notaFiscal = gerar.processarNotaFiscal(p, qtdComprar);
                    
                    if(p.getQtdEstoque()==0) {
                    	produtos.remove(p);
                    }
                    
                    System.out.println("\nCompra realizada com sucesso!");
                    System.out.println(notaFiscal);
                } else {
                    System.out.println("Quantidade em estoque insuficiente!");
                }

                break;
            }
        }

        if (!encontrado) {
            System.out.println("Produto não existe no estoque!");
            System.out.println("Verifique se digitou o nome corretamente.");
        }
    }
    
    public static void mock() {
    	
    	//ESSE MÉTODO INSTANCIA 7 OBJ DE CADA CLASSE PRODUTO(FILHAS)
    	
    	Produto mockProduto = null;
    	
    	mockProduto = new ProdAlimenticio("Arroz", LocalDate.parse("10/08/2025", fmt), 50 , 123123123, 1.00, 5.50);
    	produtos.add(mockProduto);
    	
    	mockProduto = new ProdAlimenticio("Feijão", LocalDate.parse("15/09/2025", fmt), 80, 234234234, 1.00, 7.20);
    	produtos.add(mockProduto);

    	mockProduto = new ProdAlimenticio("Macarrão", LocalDate.parse("20/07/2025", fmt), 120, 345345345, 0.50, 4.80);
    	produtos.add(mockProduto);

    	mockProduto = new ProdAlimenticio("Café", LocalDate.parse("05/10/2025", fmt), 60, 456456456, 0.50, 15.00);
    	produtos.add(mockProduto);

    	mockProduto = new ProdAlimenticio("Açúcar", LocalDate.parse("30/06/2025", fmt), 150, 567567567, 1.00, 3.90);
    	produtos.add(mockProduto);

    	mockProduto = new ProdAlimenticio("Óleo de Soja", LocalDate.parse("12/05/2025", fmt), 90, 678678678, 0.90, 8.50);
    	produtos.add(mockProduto);

    	mockProduto = new ProdAlimenticio("Sal", LocalDate.parse("18/11/2025", fmt), 200, 789789789, 1.00, 2.00);
    	produtos.add(mockProduto);
    	
    	mockProduto = new ProdLimpeza("Sabão Brilux", LocalDate.parse("12/08/2025", fmt), 25, 109302909, "detergente", 4.00);
    	produtos.add(mockProduto);
    	
    	mockProduto = new ProdLimpeza("Água Sanitária Suprema", LocalDate.parse("25/07/2025", fmt), 40, 218413818, "desinfetante", 5.50);
    	produtos.add(mockProduto);

    	mockProduto = new ProdLimpeza("Veja Multiuso Power", LocalDate.parse("10/06/2025", fmt), 60, 327524727, "multiuso", 7.80);
    	produtos.add(mockProduto);

    	mockProduto = new ProdLimpeza("Amaciante Conforto", LocalDate.parse("05/08/2025", fmt), 35, 436635636, "lava-roupas", 12.00);
    	produtos.add(mockProduto);

    	mockProduto = new ProdLimpeza("Lava-Louças Ypê Maçã", LocalDate.parse("20/07/2025", fmt), 75, 545746545, "detergente", 3.20);
    	produtos.add(mockProduto);

    	mockProduto = new ProdLimpeza("Limpador Pinho Sol", LocalDate.parse("15/05/2025", fmt), 50, 654857454, "desinfetante", 9.50);
    	produtos.add(mockProduto);

    	mockProduto = new ProdLimpeza("Limpa Vidros Cristalino", LocalDate.parse("30/08/2025", fmt), 30, 763968363, "limpa-vidros", 8.00);
    	produtos.add(mockProduto);
    	
    	mockProduto = new ProdUtilitario("Pá de madeira", LocalDate.parse("15/06/2025", fmt), 15, 123456789, 10.00);
    	produtos.add(mockProduto);
    	
    	mockProduto = new ProdUtilitario("Vassoura de Pelo Sintético", LocalDate.parse("20/05/2025", fmt), 30, 987654321, 15.50);
    	produtos.add(mockProduto);

    	mockProduto = new ProdUtilitario("Rodo Duplo de Plástico", LocalDate.parse("01/06/2025", fmt), 25, 876543210, 12.00);
    	produtos.add(mockProduto);

    	mockProduto = new ProdUtilitario("Balde de Plástico 10L", LocalDate.parse("10/04/2025", fmt), 50, 765432109, 8.75);
    	produtos.add(mockProduto);

    	mockProduto = new ProdUtilitario("Pano de Chão Algodão", LocalDate.parse("05/03/2025", fmt), 100, 654321098, 4.20);
    	produtos.add(mockProduto);

    	mockProduto = new ProdUtilitario("Escova para Vaso Sanitário", LocalDate.parse("22/02/2025", fmt), 40, 543210987, 9.00);
    	produtos.add(mockProduto);

    	mockProduto = new ProdUtilitario("Saco de Lixo Reforçado 50L", LocalDate.parse("18/05/2025", fmt), 80, 432109876, 18.30);
    	produtos.add(mockProduto);
    	
    }
    
}
